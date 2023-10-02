/**
 * 
 */
function getUserData() {


	console.log('getUserdata');

	var uri = "getUserdataByName";

	var nameUser = $("#nameUserHorus").val();

	var usuarioDataRequest = {
		name: nameUser
	};

	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: usuarioDataRequest,
		success: function(data) {
			
			$("#idUserHorus").val( data.id_usuario );
			$("#emailUserHorus").val( data.email );
			
			$("#userNameGlobal").empty();
			$('#userNameGlobal').append('Bienvenido ' + data.name);


		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});


}

function createTableFromMap(myMap) {

	let table = '<table>';
	//table += '<tr><th>ID</th><th>Name</th><th>Rank</th></tr>';

	for (const myKey of Object.keys(myMap)) {
				
		table = table + '<tr>';
			table = table + '<td>' + myKey + '</td>';
			table = table + '<td>' + myMap[myKey] + '</td>';
			table = table + '</tr>';
			
	}

	
	table += "</table>"
	

	return table;
}

function cargaReporteInvestigacion() {

	let table = '<table>';
	//table += '<tr><th>ID</th><th>Name</th><th>Rank</th></tr>';

	for (const myKey of Object.keys(myMap)) {
				
		table = table + '<tr>';
			table = table + '<td>' + myKey + '</td>';
			table = table + '<td>' + myMap[myKey] + '</td>';
			table = table + '</tr>';
			
	}

	
	table += "</table>"
	

	return table;
}

$('#pdfButtonInvestigacion').on('click', function() {
	
	var investigacionId = $("#idInvestigacionGlobal").val();
	
	window.open('generaReporteByID/'+investigacionId);
	
});


function fillLambdaText( lambdaId , origen ){
	
	$('#'+lambdaId).append('<br><div class="badge badge-info">' + origen.fuente + '</div>');
	$('#'+lambdaId).append('<br>');
	
	if(origen.isJSON == "0")
		$('#'+lambdaId).append('<br>...<div>' + origen.free_text + '</div>...');
	else
		$('#'+lambdaId).append('<br>...<div>' + createTableFromMap( origen.texto) + '</div>...');
		$('#'+lambdaId).append('<br><a href="' + origen.url + '">' + origen.url + '</div>');
		$('#'+lambdaId).append('<br><hr class="my-4">');		
	
}