
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

function initCalendar(themeSystem) {
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
        eventRender: function (info) {
            $(function () {
                $(info.el).contextMenu({
                    selector: 'div',
                    callback: function (key, options) {
                        if (key == "edit") {
                            $.ajax({
                                url: "editEvent.htm?eventId=" + info.event.id,
                                type: "GET",
                                success: function (response)
                                {
                                    console.log("sucess!");
                                },
                                error: function (e) {
                                    console.log("ERROR: ", e);
                                }
                            });
                        } else if (key == "delete") {
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
        	$(function() {
                $(info.el.parentElement).contextMenu({
                    selector: 'td',
                    callback: function (key, options) {
                        if (key == "add") {
                            $.ajax();
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

