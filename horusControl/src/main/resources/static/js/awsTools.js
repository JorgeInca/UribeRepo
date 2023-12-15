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

	if (var_firstname === "") {
		return;
	}

	$('body').removeClass('loaded');

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

			$("#mentionsText").empty();
			
			//
			$("#lambdaTextintcump").empty();
			$("#lambdaTextnatcump").empty();
			$("#lambdaTextPEP").empty();
			$("#lambdaTextOthers").empty();			
			

			investigacionGlobal = data;
			console.log('El FOLIO GENERADO ES ' + data.created);
			
			var countInt = 0;
			var countNat = 0;
			var countPEP = 0;
			var countOth = 0;
			
			

			for (let x in data.body.origen) {
				
				if( data.body.origen[x].category == "intcump"	){
					fillLambdaText( 'lambdaTextintcump' , data.body.origen[x] , false , '0' );
					countInt++;
				}
				
				if( data.body.origen[x].category == "natcump"	){
					fillLambdaText( 'lambdaTextnatcump' , data.body.origen[x] , false , '0');
					countNat++;
				}
				
				if( data.body.origen[x].category == "PEP"	){
					fillLambdaText( 'lambdaTextPEP' , data.body.origen[x] , false , '0');
					countPEP++;
				}
				
				if( data.body.origen[x].category == "others"	){
					fillLambdaText( 'lambdaTextOthers' , data.body.origen[x] , false , '0');
					countOth++;
				}
				 

				//console.log(x + ": " + data.body.origen[x].url)
				//alert( JSON.stringify(investigacionGlobal) );
			}

			for (let x in data.body.mentions) {

				$('#mentionsText').append('<br><img src="images/icons/' + data.body.mentions[x].engine + '.png" width="40" height="40"><div class="badge badge-info">' + data.body.mentions[x].title + '</div>');
				$('#mentionsText').append('<br>');
				$('#mentionsText').append('<br>...<div>' + data.body.mentions[x].description + '</div><div class="badge badge-danger">'+data.body.mentions[x].keyword+'</div>');
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
			$("#busquedaMainButton").prop('disabled', true);
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
			$('#noFolio').append('<strong> ' + data.created + ' </strong>');
			
			$('#nointer').append('<strong>( ' + countInt + ' )</strong>');
			$('#noNacionales').append('<strong>( ' + countNat + ' )</strong>');
			$('#noPEPS').append('<strong>( ' + countPEP + ' )</strong>');
			$('#noOthers').append('<strong>( ' + countOth + ' )</strong>');
			
			
			$('#noMenciones').append('<strong>( ' + data.body.mentions.length + ' )</strong>');
			$('#nivelRiesgoLabel').append('<strong> ' + data.body.nivel_riesgo + ' </strong>');
			
			$('#idInvestigacionGlobal').val( data.created );


			gaugeGobal.set(data.body.nivel_riesgo > 0 ? (data.body.nivel_riesgo - 0.5) : data.body.nivel_riesgo);

			var textoRegExpFirstname = new RegExp(var_firstname + ' ', 'gi');
			var textoRegExpLastname = new RegExp(var_lastname, 'gi');
		

			$('body').addClass('loaded');



		},
		error: function(xhr, ajaxOptions, thrownError) {
			$('body').addClass('loaded');
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


//Botón de carga masiva
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
			
			$("#rowMasiva").show(); //hide
			
			$('#response').text('File uploaded successfully: ' + response);
			
			$("#noMasiva").empty();
			$('#noMasiva').append('<strong> ' + response + ' </strong>');
			
			tableGlobal.clear().draw();
			cargaListaMasivas();
			
		},
		error: function(xhr, status, error) {
			$('#response').text('Error uploading file: ' + error);
		}
	});
	
	
});


function limpiaInvestigacion(){

				
			gaugeGobal.set(0);
			
			$("#form_firstName").val("");
			$("#form_lastName").val("");
			$("#form_rfc").val("");
			$("#form_pais").val(14);
			
			
			$("#rowFolio").hide(); //hide
			
			$("#form_firstName").prop('disabled', false);
			$("#form_lastName").prop('disabled', false);
			$("#form_rfc").prop('disabled', false);
			$("#form_pais").prop('disabled', false);
			
			$("#noFolio").empty();
			
			$("#nointer").empty();
			$("#noNacionales").empty();
			$("#noPEPS").empty();
			$("#noOthers").empty();			
			$("#noMenciones").empty();
			
			$("#nivelRiesgoLabel").empty();			
			
			$("#lambdaTextintcump").empty();
			$("#lambdaTextnatcump").empty();
			$("#lambdaTextPEP").empty();
			$("#lambdaTextOthers").empty();
			$("#mentionsText").empty();
	
			$("#pdfButtonInvestigacion").prop('disabled', true);
			$("#limpiaButtonInvestigacion").prop('disabled', true);
			$("#busquedaMainButton").prop('disabled', false);
	
}



function cargaListaMasivas() {


	var uri = "consultaMasivas";
	var idUserHorus = $("#idUserHorus").val();

	var masivaRequest = {
		idUsuario: idUserHorus

	};

	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: masivaRequest,
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
						'<a href="#" onclick="cargaInvestigacionId('+data[x].idInvestigacionMasiva+')" ><strong>' + data[x].idInvestigacionMasiva + '</strong></a>',
						data[x].nombre,
						data[x].nombreUsuario,
						data[x].fechaCreacion,											
						data[x].estatusText
					])
					.order( [0,'desc'] )
					.draw(false);

			}		



		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});


}