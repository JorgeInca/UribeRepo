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