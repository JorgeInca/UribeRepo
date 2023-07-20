//Humedad GLobal
var globalHumData;
var chartHum;
var optionsHum;
//OPCOM GLobal
var globalOpcomData;
var chartOpcom;
var optionsOpcom;
//Balluf Presion y Temp Global
var globalDataCircle;
var globalDataCircle2;
var chartCircle;
var chartCircle2;
var optionsCircle;
var optionsCircle2;


var chartColors = {
	red: 'rgb(255, 99, 132)',
	orange: 'rgb(255, 159, 64)',
	yellow: 'rgb(255, 205, 86)',
	green: 'rgb(75, 192, 192)',
	blue: 'rgb(54, 162, 235)',
	purple: 'rgb(153, 102, 255)',
	grey: 'rgb(201, 203, 207)'
};
function mainF() {
	calculaHumedad();
	calculaOpcom();
	calculaTemp();
}

function calculaHumedad() {

	var uri = "getSensorValues";
	var result = new google.visualization.DataTable();

	result.addColumn('string', 'Fecha y hora');
	result.addColumn('number', 'Humedad');


	var sensorRequest = {
		fechaInicio: "fech",
		tipoSensor: "temp"
	};

	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: sensorRequest,
		success: function(data) {

			console.log(JSON.stringify(data));

			var json_data = data;
			var x = 0;
			for (var i in json_data) {

				result.addRows([
					[json_data[i].dateString, json_data[i].value]
				]);
				console.log('iterations' + x);
				x++;
			}

			console.log('$toChart' + JSON.stringify(result));


			chartHum = new google.charts.Line(document.getElementById('line_hum'));
			chartHum.draw(result, google.charts.Line.convertOptions(optionsHum));

		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});

}

function calculaOpcom() {

	var uri = "getSensorValues";
	var result = new google.visualization.DataTable();

	result.addColumn('string', 'Fecha y hora');
	result.addColumn('number', 'ISO 1');
	result.addColumn('number', 'ISO 2');
	result.addColumn('number', 'ISO 3');


	var sensorRequest = {
		fechaInicio: "fech",
		tipoSensor: "opcom"
	};

	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: sensorRequest,
		success: function(data) {

			console.log(JSON.stringify(data));

			var json_data = data;
			var x = 0;
			for (var i in json_data) {

				result.addRows([
					[json_data[i].dateString, json_data[i].value, json_data[i].value2, json_data[i].value3]
				]);
				console.log('iterations' + x);
				x++;
			}

			console.log('$toChart' + JSON.stringify(result));


			chartOpcom = new google.charts.Line(document.getElementById('line_OP'));
			chartOpcom.draw(result, google.charts.Line.convertOptions(optionsOpcom));

		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});

}


function calculaTemp() {

	var uri = "getSensorValues";

	var sensorRequest = {
		fechaInicio: "fech",
		tipoSensor: "tempAndPressure"
	};

	//tempAndPressure

	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: sensorRequest,
		success: function(data) {

			console.log(JSON.stringify(data));

			var json_data = data;
			var x = 0;

			for (var i in json_data) {

				dataCircle = google.visualization.arrayToDataTable([
					['Label', 'Value'],
					['Temperatura', json_data[i].value]
				]);

				dataCircle2 = google.visualization.arrayToDataTable([
					['Label', 'Value'],
					['Presion', json_data[i].value2]
				]);

				console.log('iterations' + x);
				x++;
			}

			chartCircle = new google.visualization.Gauge(document.getElementById('chart_circle_div'));
			chartCircle.draw(dataCircle, optionsCircle);

			chartCircle2 = new google.visualization.Gauge(document.getElementById('chart_circle2_div'));
			chartCircle2.draw(dataCircle2, optionsCircle2);


		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});



}


function configCHarts() {

	globalHumData = new google.visualization.DataTable();
	globalHumData.addColumn('number', 'Fecha y hora');
	globalHumData.addColumn('number', 'Humedad Relativa');

	globalOpcomData = new google.visualization.DataTable();
	globalOpcomData.addColumn('number', 'Fecha y hora');
	globalOpcomData.addColumn('number', 'ISO 1');
	globalOpcomData.addColumn('number', 'ISO 2');
	globalOpcomData.addColumn('number', 'ISO 3');


	globalHumData.addRows([
		[0, 0]
	]);

	globalOpcomData.addRows([
		[0, 0, 0, 0]
	]);

	globalDataCircle = google.visualization.arrayToDataTable([
		['Label', 'Value'],
		['Temperatura', 0],
		['Presion', 0]
	]);

	optionsHum = {
		chart: {
			title: 'Humedad Relativa',
			subtitle: 'Argos || Sensor 0-100%'
		},
		width: 900,
		height: 500,
		axes: {
			x: {
				0: { side: 'top' }
			}
		}
	};

	optionsOpcom = {
		chart: {
			title: 'Sensor OPCOM II',
			subtitle: 'OPCOM Sensor 0-20'
		},
		width: 900,
		height: 500,
		axes: {
			x: {
				0: { side: 'top' }
			}
		}
	};

	optionsCircle = {
		min: 20, max: 90,
		width: 800, height: 240,
		redFrom: 55, redTo: 90,
		yellowFrom: 45, yellowTo: 55,
		greenFrom: 20, greenTo: 45,
		minorTicks: 5
	};

	optionsCircle2 = {
		min: 0, max: 10,
		width: 800, height: 240,
		redFrom: 8, redTo: 10,
		yellowFrom: 6, yellowTo: 8,
		greenFrom: 0, greenTo: 6,
		minorTicks: 5
	};

	chartHum = new google.charts.Line(document.getElementById('line_hum'));
	chartHum.draw(globalHumData, google.charts.Line.convertOptions(optionsHum));

	chartHum = new google.charts.Line(document.getElementById('line_OP'));
	chartHum.draw(globalOpcomData, google.charts.Line.convertOptions(optionsOpcom));

	chartCircle = new google.visualization.Gauge(document.getElementById('chart_circle_div'));
	chartCircle.draw(globalDataCircle, optionsCircle);

	chartCircle2 = new google.visualization.Gauge(document.getElementById('chart_circle2_div'));
	chartCircle2.draw(globalDataCircle2, optionsCircle2);
}