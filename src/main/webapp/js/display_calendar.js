/*
 * Authors:M. ALI, Hamada Abdrabou
 */

function getString(number) {
    var str = number;
    if (number < 9) {
        str = "0" + number;
    }
    return str;
}

function stringDate(date) {
    return getString(date.getFullYear()) + "-" + getString(date.getMonth() + 1) + "-" + getString(date.getDate());
}

function addEvent(event) {
    calendar.addEvent({
        id: event.uuid,
        title: event.name,
        start: stringDate(new Date(event.startDate)),
        end: stringDate(new Date(event.endDate))
    });
}

function addEvents(events) {
    for (var i = 0; i < events.length; i++) {
        addEvent(events[i]);
    }
}

function getForm(formId) {
	var form = {};
    $.map($('#'+formId).serializeArray(), function(n, i) {
        form[n['name']] = n['value'];
    });
    return JSON.stringify(form);
}

function displayFormErrors(errorMessages) {
	$.each(errorMessages, function(key, value) {
        $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
    });
}

$('#addEventSubmit').click(function(e) {
    e.preventDefault();
    $('input').next().remove();
    $.ajax({
       type: 'POST',
       url : 'addEvent.htm',
       data : getForm("addEventForm"),
       contentType: "application/json",
       dataType: "json",
       success : function(response) {
          if(response.validated) {
        	  addEvent(response.event);
        	  swal(response.event.name, " is added to calendar", "success");
          }
          else {
            displayFormErrors(response.errorMessages);
          }
       },
       error: function(response) {
    	   console.log("Error");
       }
    });
 });

function initCalendar(themeSystem) {
	var containerEl = document.getElementById('external-events-list');
	new FullCalendarInteraction.Draggable(containerEl, {
		itemSelector : '.fc-event',
		eventData : function(eventEl) {
			return {
				title : eventEl.innerText.trim()
			};
		}
	});
    var calendarEl = document.getElementById('calendar');
    calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: ['bootstrap', 'interaction', 'dayGrid', 'timeGrid', 'list'],
        themeSystem: themeSystem,
        locale: 'en',
        firstDay: 6,
        dir: 'ltr',
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek',
        },
        editable:true,
        droppable : true,
		drop : function(dropInfo) {
			dropInfo.draggedEl.parentNode.removeChild(dropInfo.draggedEl);
		},
        eventRender: function (info) {
            $(function () {
                $(info.el).contextMenu({
                    selector: 'div',
                    callback: function (key, options) {
                        if (key == "edit") {
                        	$.ajax({
                        	       type: 'POST',
                        	       url : 'editEvent.htm',
                        	       data : getForm("editEventForm"),
                        	       contentType: "application/json",
                        	       dataType: "json",
                        	       success : function(response) {
                        	          if(response.validated) {
                        	        	  addEvent(response.event);
                        	        	  swal(response.event.name, " is edited successfully", "success");
                        	          }
                        	          else {
                        	            displayFormErrors(response.errorMessages);
                        	          }
                        	       },
                        	       error: function(response) {
                        	    	   console.log("Error");
                        	       }
                        	    });
                        }
                        else if (key == "delete") {
                            $.ajax({
                                url: "deleteEvent.htm?eventId=" + info.event.id,
                                type: "POST",
                                success: function (response)
                                {
                                    info.event.remove();
                                },
                                error: function (e) {
                                    console.log("ERROR: ", e);
                                }
                            });
                        }
                        else if (key == "info") {
                        }
                    },
                    items: {
                        "edit": {name: "Edit event", icon: "edit"},
                        "delete": {name: "Delete event", icon: "delete"},
                        "info": {name: "Event information", icon: "fa-info"}
                    }
                });
            });
        },
        dayRender: function(info) {
        	var dateEl = document.createElement("div");
        	dateEl.style.width = "100%";
        	dateEl.style.height = "100%";
        	info.el.appendChild(dateEl);
        	$(function() {
                $(info.el).contextMenu({
                    selector: 'div',
                    callback: function (key, options) {
                        if (key == "add") {
                        	var date = $(info.el).attr("data-date");
                        	$("#addEvent").modal();
                        	$("#startDate").val(date);
                        	$("#endDate").val(date);
                        }
                    },
                    items: {
                        "add": {name: "Add event", icon: "add"}
                    }
                });
            });
        }
    });
    addEvents(events);
    calendar.render();
}

initThemeChooser({
    init: initCalendar,
    change: function (themeSystem) {
        calendar.setOption('themeSystem', themeSystem);
    }
});
$(document).ready(function () {
    $("#testAjax").click(function () {
        $.ajax({
            type: "POST",
            url: "/test.htm?testNum=" + 1,
            sucess: function (e) {
                console.log("Successsssssss")

            },
            error: function (e) {
                console.log("ERRORRRRRR" + e)
            }
        });
    });
});

