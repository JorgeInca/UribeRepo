/**
 * 
 */

var gaugeGobal;

function cargaListaInvestigaciones() {


	var uri = "consultaReportes";

	var reporteRequest = {
		user: "jorge"

	};

	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: reporteRequest,
		success: function(data) {

			$("#lambdaText").empty();

			//alert(JSON.stringify(data));

			for (let x in data) {

				/*let tableRef = document.getElementById("horus");

				// Insert a row at the end of the table
				let newRow = tableRef.insertRow(-1);

				// Insert a cell in the row at index 0
				let newCell = newRow.insertCell(0);

				// Append a text node to the cell
				let newText = document.createTextNode(data[x].nombreUsuario);

				newCell.appendChild(newText);*/

				tableGlobal.row
					.add([
						'<a href="#" onclick="cargaInvestigacionId('+data[x].idInvestigacion+')" ><strong>' + data[x].idInvestigacion + '</strong></a>',
						data[x].nombreUsuario,
						data[x].nombreEmpresa,
						data[x].apellidos,
						data[x].primer_nombre,						
						data[x].estatusText,
						data[x].riesgoInicial,
						data[x].fechaCreacion
					])
					.order( [0,'desc'] )
					.draw(false);

			}
			
			$( "#buttontest" ).prop( "disabled", true );



		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});


}

function cargaInvestigacionId( idInvestigacion ) {

	console.log('inicia cargaInvestigacionId');

	var uri = "cargaInvestigacionId";
	
	$('#modalReportes').modal('show');
	

/*	var var_firstname = $("#form_firstName").val();
	var var_lastname = $("#form_lastName").val();
	var var_rfc = $("#form_rfc").val();
	var var_pais = $("#form_pais").val();

	if (var_rfc === "" || var_rfc === "" || var_pais === "") {
		return;
	}*/


	var investigacionRequest = {
		idInvestigacion: idInvestigacion
	};

	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: investigacionRequest,
		success: function(data) {

			$("#lambdaText").empty();
			
			//Llena campos Modal:
			$( "#form_firstName" ).val(  data.body.parametros_busqueda[0] );
			$( "#form_lastName" ).val(  data.body.parametros_busqueda[1] );
			$( "#form_rfc" ).val(  data.body.parametros_busqueda[2] );
			$( "#form_pais" ).val( 1 );

			investigacionGlobal = data;
			console.log('El FOLIO GENERADO ES ' + investigacionRequest);

			for (let x in data.body.origen) {

				$('#lambdaText').append('<br><div class="badge badge-info">' + data.body.origen[x].fuente + '</a>');
				$('#lambdaText').append('<br>');
				
				if(data.body.origen[x].isJSON == "0")
					$('#lambdaText').append('<br>...<div>' + data.body.origen[x].free_text + '</a>...');
				else
					$('#lambdaText').append('<br>...<div>' + createTableFromMap(data.body.origen[x].texto) + '</a>...');					
				
				$('#lambdaText').append('<br><a href="' + data.body.origen[x].url + '">' + data.body.origen[x].url + '</a>');
				$('#lambdaText').append('<br><hr class="my-4">');

				//console.log(x + ": " + data.body.origen[x].url)

				//alert( JSON.stringify(investigacionGlobal) );

			}
			
			for (let x in data.body.mentions) {

				$('#mentionsText').append('<br><img src="images/icons/'+ data.body.mentions[x].engine +'.png" width="40" height="40"><div class="badge badge-info">' + data.body.mentions[x].title + '</a>');
				$('#mentionsText').append('<br>');
				$('#mentionsText').append('<br>...<div>' + data.body.mentions[x].description + '</a>...');
				$('#mentionsText').append('<br><a href="' + data.body.mentions[x].link + '">' + data.body.mentions[x].link + '</a>');
				$('#mentionsText').append('<br><hr class="my-4">');				

				//console.log(x + ": " + data.body.origen[x].url)
				//alert( JSON.stringify(investigacionGlobal) );
			}
			
			//Bloquea los botones
			$("#form_firstName").prop('disabled', true);
			$("#form_lastName").prop('disabled', true);
			$("#form_rfc").prop('disabled', true);
			$("#form_pais").prop('disabled', true);
			$("#rowFolio").show(); //hide

			//Habilida los botones
			$("#limpiaButtonInvestigacion").prop('disabled', false);
			$("#pdfButtonInvestigacion").prop('disabled', false);

			$("#noFolio").empty();
			$("#noSanciones").empty();
			$("#noMenciones").empty();
			$("#nivelRiesgoLabel").empty();

			//Etiquetas
			$('#noFolio').append('<strong> ' + idInvestigacion + ' </strong>');
			$('#noSanciones').append('<strong>( ' + data.body.origen.length + ' )</strong>');
			$('#noMenciones').append('<strong>( ' + data.body.mentions.length + ' )</strong>');
			$('#nivelRiesgoLabel').append('<strong> ' + data.body.nivel_riesgo + ' </strong>');
	
			loadCHart();
			gaugeGobal.set( data.body.nivel_riesgo > 0 ? (data.body.nivel_riesgo - 0.5) : data.body.nivel_riesgo  );

			var textoRegExpFirstname = new RegExp(data.body.parametros_busqueda[0] + ' ', 'gi');
			var textoRegExpLastname = new RegExp(data.body.parametros_busqueda[1], 'gi');

			var html = $('#lambdaText').html();

			$('#lambdaText').html(
				html.replace(textoRegExpFirstname, '<strong>' + data.body.parametros_busqueda[0].toUpperCase() + '</strong> ').replace(textoRegExpLastname, '<strong> ' + data.body.parametros_busqueda[1].toUpperCase() + '</strong>')

			);



		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});

}

function loadCHart(){
	
	//https://bernii.github.io/gauge.js/
		
					var opts = {
						angle: -0.08, // The span of the gauge arc
						lineWidth: 0.51, // The line thickness
						radiusScale: 1, // Relative radius
						pointer: {
							length: 0.74, // // Relative to gauge radius
							strokeWidth: 0.06, // The thickness
							color: '#000000' // Fill color
						},
						staticZones: [
							{strokeStyle: "#30B32D", min: 0, max: 1}, // Red from 100 to 130
							{strokeStyle: "#FFDD00", min: 1, max: 2}, // Yellow
							{strokeStyle: "#F03E3E", min: 2, max: 3}
						],
						limitMax: false,     // If false, max value increases automatically if value > maxValue
						limitMin: false,     // If true, the min value of the gauge will be fixed
						colorStart: '#6F6EA0',   // Colors
						colorStop: '#C0C0DB',    // just experiment with them
						strokeColor: '#EEEEEE',  // to see which ones work best for you
						generateGradient: true,
						highDpiSupport: true,     // High resolution support

					};
					var target = document.getElementById('foo'); // your canvas element
					gaugeGobal = new Gauge(target).setOptions(opts); // create sexy gauge!
					gaugeGobal.maxValue = 3; // set max gauge value
					gaugeGobal.setMinValue(0);  // Prefer setter over gauge.minValue = 0
					gaugeGobal.animationSpeed = 75; // set animation speed (32 is default value)
					gaugeGobal.set(1.5); // set actual value
	
}