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
        
        <nav class="context-menu" id="context-menu">
            <ul class="context-menu__items">
                <li class="context-menu__item">
                    <a href="#" class="context-menu__link">
                        <i class="fa fa-eye"></i> View Task
                    </a>
                </li>
                <li class="context-menu__item">
                    <a href="#" class="context-menu__link">
                        <i class="fa fa-edit"></i> Edit Task
                    </a>
                </li>
                <li class="context-menu__item">
                    <a href="#" class="context-menu__link">
                        <i class="fa fa-times"></i> Delete Task
                    </a>
                </li>
            </ul>
        </nav>
    </body>

    <script src='js/display_calendar.js'></script>
    <script>
        addEvents(${events});
    </script>

</html>