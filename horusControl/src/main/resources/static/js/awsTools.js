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

	if (var_rfc === "" || var_lastname === "" || var_rfc === "" || var_pais === "") {
		return;
	}


	var investigacionRequest = {
		firstname: var_firstname,
		lastname: var_lastname,
		rfc: var_rfc,
		pais: var_pais
	};

	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: investigacionRequest,
		success: function(data) {

			$("#lambdaText").empty();

			console.log(JSON.stringify(data));

			$('#lambdaText').text(data.mensaje);



		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});

}