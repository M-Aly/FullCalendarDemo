
function getString(number) {
    var str = number;
    if(number < 9) {
        str = "0" + number;
    }
    return str;
}

function stringDate(date) {
    return getString(date.getFullYear()) + "-" + getString(date.getMonth() + 1) + "-" + getString(date.getDate());
}

function addEvent(eventTitle, eventStartDate, eventEndDate) {
    calendar.addEvent({
        title: eventTitle,
        start: stringDate(new Date(eventStartDate)),
        end: stringDate(new Date(eventEndDate))
    });
}

function addEvents(events) {
    for(var i = 0 ; i < events.length ; i++) {
        addEvent(events[i].name, events[i].startDate, events[i].endDate);
    }
}

function initCalendar(themeSystem) {
    var calendarEl = document.getElementById('calendar');
    calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: [ 'bootstrap', 'interaction', 'dayGrid', 'timeGrid', 'list' ],
        themeSystem: themeSystem,
        locale: 'en',
        firstDay: 6,
        dir: 'ltr',
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
        },
        color: "red"
    });
    addEvents(events);
    calendar.render();
}

initThemeChooser({
	init: initCalendar,
    change: function(themeSystem) {
      calendar.setOption('themeSystem', themeSystem);
    }
});

