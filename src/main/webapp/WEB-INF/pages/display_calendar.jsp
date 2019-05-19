<!DOCTYPE html>
<html lang='en'>

<head>
    <meta charset='utf-8' />

    <link href='css/full_calendar/core/main.css' rel='stylesheet' />
    <link href='css/full_calendar/daygrid/main.css' rel='stylesheet' />
    <link href='css/full_calendar/timegrid/main.css' rel='stylesheet' />
    <link href='css/full_calendar/list/main.css' rel='stylesheet' />

    <script src='js/core/main.js'></script>
    <script src='js/daygrid/main.js'></script>
    <script src='js/timegrid/main.js'></script>
    <script src='js/interaction/main.js'></script>
    <script src='js/list/main.js'></script>
    <script src='js/core/locales-all.js'></script>
</head>

<body>
    
    <div id='calendar'></div>

</body>

<script src='js/display_calendar.js'></script>
<script>
    addEvents(${events});
</script>

</html>