
var calendar;

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

function addEvent(eventId, eventTitle, eventStartDate, eventEndDate) {
    calendar.addEvent({
        title: eventTitle,
        start: stringDate(new Date(eventStartDate)),
        end: stringDate(new Date(eventEndDate))
    });
}

function addEvents(events) {
    for(var i = 0 ; i < events.length ; i++) {
        addEvent(events[i].Uuid, events[i].name, events[i].startDate, events[i].endDate);
    }
}

var calendarEl = document.getElementById('calendar');
calendar = new FullCalendar.Calendar(calendarEl, {
    plugins: ['dayGrid', 'timeGrid', 'list', 'interaction'],
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
calendar.render();

document.addEventListener("contextmenu", function(e){
    console.log(e);
});

function contextMenuListener(eventElement){
    eventElement.addEventListener("contextmenu", function(){
        console.log(e, el);
        toggleMenuOn();
    });
}

