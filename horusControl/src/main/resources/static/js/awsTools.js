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

	if (var_rfc === ""  || var_rfc === "" || var_pais === "") {
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

			for (let x in data.body.origen) {
				
				$('#lambdaText').append('<br><div class="badge badge-info">'+data.body.origen[x].fuente+'</a>');
				$('#lambdaText').append('<br>');
				$('#lambdaText').append('<br>...<div>'+data.body.origen[x].texto+'</a>...');
				$('#lambdaText').append('<br><a href="'+data.body.origen[x].url+'">'+data.body.origen[x].url+'</a>');
				$('#lambdaText').append('<br><hr class="my-4">');
					
				//console.log(x + ": " + data.body.origen[x].url)
				
			}
			
			//console.log(JSON.stringify(data));



			//var obj = $('#lambdaText').text(JSON.stringify(data));
			//obj.html(obj.html().replace(/\n/g, '<br/>'));



		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});

}

