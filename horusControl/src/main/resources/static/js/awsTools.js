var investigacionGlobal;
var gaugeGobal;

/**
 * 
 */
function consumeAws() {

	console.log('inicia aws');

	var uri = "consumeLambda";

	var var_firstname = $("#form_firstName").val();
	var var_lastname = $("#form_lastName").val();
	var var_rfc = $("#form_rfc").val();
	var var_pais = $("#form_pais").val();
	var var_id_usuario = $("#idUserHorus").val();

	if (var_lastname === "" || var_pais === "") {
		return;
	}


	var investigacionRequest = {
		firstname: var_firstname,
		lastname: var_lastname,
		rfc: var_rfc,
		pais: var_pais,
		idUsuario:var_id_usuario
	};

	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: investigacionRequest,
		success: function(data) {

			$("#lambdaText").empty();

			investigacionGlobal = data;
			console.log('El FOLIO GENERADO ES ' + data.created);

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

				$('#mentionsText').append('<br><img src="images/icons/' + data.body.mentions[x].engine + '.png" width="40" height="40"><div class="badge badge-info">' + data.body.mentions[x].title + '</a>');
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
			$('#noFolio').append('<strong> ' + data.created + ' </strong>');
			$('#noSanciones').append('<strong>( ' + data.body.origen.length + ' )</strong>');
			$('#noMenciones').append('<strong>( ' + data.body.mentions.length + ' )</strong>');
			$('#nivelRiesgoLabel').append('<strong> ' + data.body.nivel_riesgo + ' </strong>');


			gaugeGobal.set(data.body.nivel_riesgo > 0 ? (data.body.nivel_riesgo - 0.5) : data.body.nivel_riesgo);

			var textoRegExpFirstname = new RegExp(var_firstname + ' ', 'gi');
			var textoRegExpLastname = new RegExp(var_lastname, 'gi');

			var html = $('#lambdaText').html();

			$('#lambdaText').html(
				html.replace(textoRegExpFirstname, '<strong> ' + var_firstname.toUpperCase() + '</strong>').replace(textoRegExpLastname, '<strong> ' + var_lastname.toUpperCase() + '</strong>')

			);



		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});

}

function guardaInvestigacion() {

	console.log('inicia guardardo');

	var uri = "guardaInvestigacion";

	var var_firstname = $("#form_firstName").val();
	var var_lastname = $("#form_lastName").val();
	var var_rfc = $("#form_rfc").val();
	var var_pais = $("#form_pais").val();

	if (!confirm("¡Deseas guardar la invesigacion?")) {
		return;
	}

	var investigacionRequest = {
		firstname: var_firstname,
		lastname: var_lastname,
		rfc: var_rfc,
		pais: var_pais,
		investigacionJson: JSON.stringify(investigacionGlobal)
	};



	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: investigacionRequest,
		success: function(data) {

			alert('El FOLIO GENERADO ES ' + data);


			$("#form_firstName").prop('disabled', true);
			$("#form_lastName").prop('disabled', true);
			$("#form_rfc").prop('disabled', true);
			$("#form_pais").prop('disabled', true);

			$("#rowFolio").show(); //hide

			$("#noFolio").empty();
			$('#noFolio').append('<strong> ' + data + ' </strong>');


		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});

}



$('#uploadButton').click(function() {
	
	var formData = new FormData();	
	var idUserHorus = $("#idUserHorus").val();
	var form_nombreCampana = $("#form_nombreCampana").val();
	
	formData.append('file', $('#fileInput')[0].files[0]);
	formData.append('idUserHorus', idUserHorus );
	formData.append('nombreCampaña', form_nombreCampana );
	

	if (form_nombreCampana === "") {
		return;
	}

	$.ajax({
		type: 'POST',
		url: 'uploadMasiva',
		data: formData,
		processData: false,
		contentType: false,
		success: function(response) {
			$('#response').text('File uploaded successfully: ' + response);
		},
		error: function(xhr, status, error) {
			$('#response').text('Error uploading file: ' + error);
		}
	});
	
	
});
