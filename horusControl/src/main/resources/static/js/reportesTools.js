/**
 * 
 */

var lastClick = 0;
var delay = 20;


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
						creaLinkSiFinalizada( data[x].idInvestigacion , data[x].estatusText),						
						data[x].nombreUsuario,
						data[x].nombreEmpresa,
						data[x].nombreCampania,
						data[x].apellidos,
						data[x].primer_nombre,						
						data[x].estatusText,
						getRiesgoTexto(data[x].riesgoTexto),
						data[x].riesgoAcumulado,
						data[x].fechaCreacionTexto
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
	
	$('#idInvestigacionGlobal').val( idInvestigacion );

	var uri = "cargaInvestigacionId";
	
	//$('#modalReportes').modal('show');
	$('#modalReportes').modal('toggle'); 
	

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
			
			//alert( "nivel de riesgo editado " +  data.body.nivel_riesgo_editado)
			
			$("#mentionsText").empty();
			
			//
			$("#lambdaTextintcump").empty();
			$("#lambdaTextnatcump").empty();
			$("#lambdaTextPEP").empty();
			$("#lambdaTextOthers").empty();	
			
			//Llena campos Modal:
			$( "#form_firstName" ).val(  data.body.parametros_busqueda[0] );
			$( "#form_lastName" ).val(  data.body.parametros_busqueda[1] );
			$( "#form_rfc" ).val(  data.body.parametros_busqueda[2] );
			$( "#form_pais" ).val( 14 );

			investigacionGlobal = data;
			console.log('El FOLIO GENERADO ES ' + investigacionRequest);
			
			var countInt = 0;
			var countNat = 0;
			var countPEP = 0;
			var countOth = 0;
			
			//Ocultar Borrados
			const origenesBorrador = data.body.eliminadosOrigenes.split(',').map(Number);
			const mencionesBorradas = data.body.eliminadosMentions.split(',').map(Number);		
			
			for (let x in data.body.origen) {

				if (!origenesBorrador.includes(data.body.origen[x].id)) {
					
					if (data.body.origen[x].category == "intcump") {
						fillLambdaText('lambdaTextintcump', data.body.origen[x], true, data.body.eliminadosOrigenes);
						countInt++;
					}

					if (data.body.origen[x].category == "natcump") {
						fillLambdaText('lambdaTextnatcump', data.body.origen[x], true, data.body.eliminadosOrigenes);
						countNat++;
					}

					if (data.body.origen[x].category == "PEP") {
						fillLambdaText('lambdaTextPEP', data.body.origen[x], true, data.body.eliminadosOrigenes);
						countPEP++;
					}

					if (data.body.origen[x].category == "others") {
						fillLambdaText('lambdaTextOthers', data.body.origen[x], true, data.body.eliminadosOrigenes);
						countOth++;
					}
					
				}

			}
			
			
			var countMentions = 0;
			for (let x in data.body.mentions) {
				
				if (!mencionesBorradas.includes(data.body.mentions[x].id)) {

					$('#mentionsText').append('<br><img src="images/icons/' + data.body.mentions[x].engine + '.png" width="40" height="40"><div class="badge badge-info">' + data.body.mentions[x].title + '</div>');
					$('#mentionsText').append('<br>');
					$('#mentionsText').append('<br>...<div>' + data.body.mentions[x].description + '</div><div class="badge badge-danger">' + data.body.mentions[x].keyword + '</div>');
					$('#mentionsText').append('<br><a href="' + data.body.mentions[x].link + '">' + data.body.mentions[x].link + '</a>');
					$('#mentionsText').append('<br>');
					$('#mentionsText').append('<br><button class="btn btn-danger" type="button" onclick="borraOrigen(\'true\',\'' + data.body.mentions[x].id + '\',\'' + data.body.eliminadosMentions + '\')"><img src="images/icons/eliminar.png" width="35" height="35">');
					$('#mentionsText').append('<br><hr class="my-4">');
					countMentions++;
				}
				
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
			
			$("#nointer").empty();
			$("#noNacionales").empty();
			$("#noPEPS").empty();
			$("#noOthers").empty();
			
			$("#noMenciones").empty();
			$("#nivelRiesgoLabel").empty();
			
			//Etiquetas
			$('#noFolio').append('<strong> ' + idInvestigacion + ' </strong>');
			
			$('#nointer').append('<strong>( ' + countInt + ' )</strong>');
			$('#noNacionales').append('<strong>( ' + countNat + ' )</strong>');
			$('#noPEPS').append('<strong>( ' + countPEP + ' )</strong>');
			$('#noOthers').append('<strong>( ' + countOth + ' )</strong>');
			
			$('#noMenciones').append('<strong>( ' + countMentions + ' )</strong>');
				
			
			$('#valorRiesgoEditable').val( data.body.nivel_riesgo_editado );
			$('#nivelRiesgoLabel').append('<strong> ' + data.body.nivel_riesgo + ' </strong>');
			
			$('#idInvestigacionGlobal').val( idInvestigacion );
	
			loadCHart();
			gaugeGobal.set( data.body.nivel_riesgo > 0 ? (data.body.nivel_riesgo - 0.5) : data.body.nivel_riesgo  );

			var textoRegExpFirstname = new RegExp(data.body.parametros_busqueda[0] + ' ', 'gi');
			var textoRegExpLastname = new RegExp(data.body.parametros_busqueda[1], 'gi');

						

			//$('#lambdaText').html(
				//html.replace(textoRegExpFirstname, '<strong>' + data.body.parametros_busqueda[0].toUpperCase() + '</strong> ').replace(textoRegExpLastname, '<strong> ' + data.body.parametros_busqueda[1].toUpperCase() + '</strong>')

			//);



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
					var target = document.getElementById('miGauge'); // your canvas element
					gaugeGobal = new Gauge(target).setOptions(opts); // create sexy gauge!
					gaugeGobal.maxValue = 3; // set max gauge value
					gaugeGobal.setMinValue(0);  // Prefer setter over gauge.minValue = 0
					gaugeGobal.animationSpeed = 75; // set animation speed (32 is default value)
					gaugeGobal.set(1.5); // set actual value
	
}

function editaRiesgo() {

	var valorRiesgoEditable = $("#valorRiesgoEditable").val();
	var idInvestigacionGlobal = $("#idInvestigacionGlobal").val();
	var idUserHorus = $("#idUserHorus").val();

	if (valorRiesgoEditable === "") {
		return;
	}

	var riesgoRequest = {
		riesgo: valorRiesgoEditable,
		idInvestigacion: idInvestigacionGlobal,
		idUsuario: idUserHorus
	};

	var uri = "actualizaRiesgoById";


	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: riesgoRequest,
		success: function(data) {
			
			alert("Valor actualizado");	
			cargaInvestigacionId( idInvestigacionGlobal ) ;
				
			
			
			
		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert("ERROR");	
		}

	});
}

function borraOrigen(esMention, porBorrar, borrados) {
	
	var delayInMilliseconds = 1000; //1 second

	if (lastClick >= (Date.now() - delay))
		return;
  	lastClick = Date.now();
  	
	setTimeout(function() {
	  //your code to be executed after 1 second
	}, delayInMilliseconds);	 

	var idInvestigacionGlobal = $("#idInvestigacionGlobal").val();
	var idUserHorus = $("#idUserHorus").val();
	var uri = "actualizaOrigenMention";

	var queBorras = esMention ? 'la MENCION' : 'el ORIGEN';
		
	if ( confirm('¿Estás seguro que desear borrar ' + queBorras + '?') ) {
		// Save it!
		const arr = borrados.split(',').map(Number);
		arr.push(porBorrar);


		var origenesBorradoRequest = {
			nuevoValor: arr,
			idInvestigacion: idInvestigacionGlobal,
			idUsuario: idUserHorus,
			esMention: esMention
		};

		$.ajax({
			url: uri,
			type: 'POST',
			dataType: 'json',
			data: origenesBorradoRequest,
			success: function(data) {

				alert("Valor actualizado");
				cargaInvestigacionId(idInvestigacionGlobal);

			},
			error: function(xhr, ajaxOptions, thrownError) {
				alert("ERROR");
			}

		});
		
	} else {
		// Do nothing!
		alert('Cancelado....');
	}


}

/*
$("#modalReportes").on('shown.bs.modal', function() {
	loadCHart();	
});
*/


function pruebaAWSSave() {

	var valorRiesgoEditable = $("#valorRiesgoEditable").val();
	var idInvestigacionGlobal = $("#idInvestigacionGlobal").val();
	var idUserHorus = $("#idUserHorus").val();



	var riesgoRequest = {
		riesgo: valorRiesgoEditable,
		idInvestigacion: idInvestigacionGlobal,
		idUsuario: idUserHorus
	};

	var uri = "pruebaAWS";


	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: riesgoRequest,
		success: function(data) {
			
	
			
		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert("ERROR");	
		}

	});
}


function getRiesgoTexto(riesgoTexto) {

		if( riesgoTexto === "ALTO" ){
			return '<span class="badge badge-phoenix fs--2 badge-phoenix-danger">' + riesgoTexto + 	'</span>';
		}
		if( riesgoTexto === "MEDIO" ){
			return '<span class="badge badge-phoenix fs--2 badge-phoenix-warning">' + riesgoTexto + 	'</span>';
		}
		if( riesgoTexto === "BAJO" ){
			return '<span class="badge badge-phoenix fs--2 badge-phoenix-success">' + riesgoTexto + 	'</span>';
		}
		if( riesgoTexto === "PENDIENTE" ){
			return '<span class="badge badge-phoenix fs--2 badge-phoenix-primary">' + riesgoTexto + 	'</span>';
		}
	
}

function creaLinkSiFinalizada(idInvestigacion , estatusText ) {

		if( estatusText === "FINALIZADO" ){
			return '<a href="#" onclick="cargaInvestigacionId(' + idInvestigacion + ')" ><strong>' + idInvestigacion + '</strong></a>';
		}else{
			return '<strong>' + idInvestigacion + '</strong>';	
		}
	
}

function eliminarRegistro() {
	
	var idInvestigacionGlobal = $("#idInvestigacionGlobal").val();
	var idUserHorus = $("#idUserHorus").val();


	var eliminaRequest = {
		riesgo: 0,
		idInvestigacion: idInvestigacionGlobal,
		idUsuario: idUserHorus
	};

	var uri = "eliminaRegistroById";


	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: eliminaRequest,
		success: function(data) {
			
			alert("Valor actualizado");	
			cargaListaInvestigaciones();
				
			
			
			
		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert("ERROR");	
		}

	});
}