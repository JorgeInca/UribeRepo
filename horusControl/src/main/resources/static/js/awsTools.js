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

	if (var_rfc === "" || var_rfc === "" || var_pais === "") {
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

				$('#lambdaText').append('<br><div class="badge badge-info">' + data.body.origen[x].fuente + '</a>');
				$('#lambdaText').append('<br>');
				$('#lambdaText').append('<br>...<div>' + data.body.origen[x].texto + '</a>...');
				$('#lambdaText').append('<br><a href="' + data.body.origen[x].url + '">' + data.body.origen[x].url + '</a>');
				$('#lambdaText').append('<br><hr class="my-4">');

				//console.log(x + ": " + data.body.origen[x].url)

			}

			var textoRegExpFirstname = new RegExp( var_firstname+ ' ' , 'gi' );
			var textoRegExpLastname = new RegExp( var_lastname , 'gi' );
			
			var html = $('#lambdaText').html();
			
			$('#lambdaText').html(
				html.replace(textoRegExpFirstname, '<strong>'+var_firstname.toUpperCase()+'</strong>').replace(textoRegExpLastname, '<strong>'+var_lastname.toUpperCase()+'</strong>')
			
			);



		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});

}

