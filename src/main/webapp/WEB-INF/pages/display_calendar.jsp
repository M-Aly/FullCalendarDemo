<!DOCTYPE html>
<html lang='en'>

<head>
<meta charset='utf-8' />

<link href='fonts/font-awesome-4.7.0/css/font-awesome.css' rel='stylesheet'>
<link href='css/full_calendar/core/main.css' rel='stylesheet' />
<link href='css/full_calendar/daygrid/main.css' rel='stylesheet' />
<link href='css/full_calendar/timegrid/main.css' rel='stylesheet' />
<link href='css/full_calendar/list/main.css' rel='stylesheet' />
<link href='css/full_calendar/bootstrap/main.css' rel='stylesheet' />
<link href='css/display_calendar.css' rel='stylesheet' />
<link href='css/jquery.contextMenu.css' rel='stylesheet' />

<script src='js/jquery-3.3.1.min.js'></script>
<script src='js/core/main.js'></script>
<script src='js/moment/main.js'></script>
<script src='js/daygrid/main.js'></script>
<script src='js/timegrid/main.js'></script>
<script src='js/interaction/main.js'></script>
<script src='js/list/main.js'></script>
<script src='js/core/locales-all.js'></script>
<script src='js/bootstrap/main.js'></script>
<script src='js/theme-chooser.js'></script>
<script src='js/jquery.ui.position.js'></script>
<script src='js/jquery.contextMenu.js'></script>
<script>
var events;
var calendar;
</script>
</head>

<body>

	<div id='top'>
		<div class='left'>
			<span id='theme-system-selector' class='selector'>
				Theme System:
				<select>
					<option value='bootstrap' selected>Bootstrap 4</option>
					<option value='standard'>unthemed</option>
				</select>
			</span>
			<span data-theme-system="bootstrap" class='selector' style='display: none'>
				Theme:
				<select>
					<option value='' selected>Default</option>
					<option value='cerulean'>Cerulean</option>
					<option value='cosmo'>Cosmo</option>
					<option value='cyborg'>Cyborg</option>
					<option value='darkly'>Darkly</option>
					<option value='flatly'>Flatly</option>
					<option value='journal'>Journal</option>
					<option value='litera'>Litera</option>
					<option value='lumen'>Lumen</option>
					<option value='lux'>Lux</option>
					<option value='materia'>Materia</option>
					<option value='minty'>Minty</option>
					<option value='pulse'>Pulse</option>
					<option value='sandstone'>Sandstone</option>
					<option value='simplex'>Simplex</option>
					<option value='sketchy'>Sketchy</option>
					<option value='slate'>Slate</option>
					<option value='solar'>Solar</option>
					<option value='spacelab'>Spacelab</option>
					<option value='superhero'>Superhero</option>
					<option value='united'>United</option>
					<option value='yeti'>Yeti</option>
				</select>
			</span>
			<span id='loading' style='display: none'>loading theme...</span>
		</div>
		<div class='clear'></div>
	</div>

	<div id='calendar'></div>

</body>

<script src='js/display_calendar.js'></script>
<script>
	events = ${sessionScope.events};
</script>

</html>