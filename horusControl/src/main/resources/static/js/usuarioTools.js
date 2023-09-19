/**
 * 
 */
function cargaListaUsuarios() {


	var uri = "consultaUsuarios";

	var userHorus = {
		user: "jorge"

	};

	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: userHorus,
		success: function(data) {

			$("#lambdaText").empty();


			for (let x in data) {

				tableGlobal.row
					.add([
						'<a href="#" onclick="cargaUsuarioId('+data[x].id_usuario +')" ><strong>' + data[x].id_usuario + '</strong></a>',
						data[x].name,
						data[x].nombreEmpresa,
						data[x].email,
						data[x].rol,
						'<a href="#"><i class="bi bi-pencil-square"></i></a>'
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

function cargaUsuarioId(id_usuario) {
	
	alert('Revisando el id ' + id_usuario);

	console.log('inicia cargaUsarioId');

	var uri = "cargaUsuarioId";
	
	$('#ModalUsuarios').modal('show');
	

	var UserHorus = {
		id_usuario: id_usuario
	};

	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: UserHorus,
		success: function(data) {

			$("#lambdaText").empty();
			
			for (let x in data.body.origen) {
			
			//Llena campos Modal de Usuarios:
			$( "#noFolio" ).val(  data.body.parametros_busqueda[0] );
			$( "#form-name" ).val(  data.body.parametros_busqueda[1] );
			$( "#form-email" ).val(  data.body.parametros_busqueda[2] );
			$( "#form-fecha_creacion" ).val(  data.body.parametros_busqueda[3] );
			$( "#form-estatus" ).val(  data.body.parametros_busqueda[4] );
			$( "#form-id_empresa" ).val(  data.body.parametros_busqueda[5] );
			$( "#form-rol" ).val(  data.body.parametros_busqueda[6] );
			$( "#form-password" ).val(  data.body.parametros_busqueda[7] );
			

			investigacionGlobalUsuarios = data;
			console.log('El ID GENERADO ES ' + UserHorus);

			}
			//Bloquea los botones
			$("#noFolio").prop('disabled', true);
			$("#form-name").prop('disabled', true);
			$("#form-email").prop('disabled', true);
			$("#form-fecha_creacion").prop('disabled', true);
			$("#form-estatus").prop('disabled', true);
			$("#form-id_empresa").prop('disabled', true);
			$("#form-rol").prop('disabled', true);
			$("#form-password").prop('disabled', true);
			$("#rowFolio").show(); //hide

			//Habilita los botones
			$("#limpiaButtonUsuarios").prop('disabled', false);


			$("#noFolio").empty();

			//Etiquetas
			$('#noFolio').append('<strong> ' + id_usuario + ' </strong>');

			var textoRegExpFirstname = new RegExp(data.body.parametros_busqueda[1] + ' ', 'gi');
			var textoRegExpEmail = new RegExp(data.body.parametros_busqueda[2], 'gi');
			var textoRegExpPassword = new RegExp(data.body.parametros_busqueda[7], 'gi');

			var html = $('#lambdaText').html();

			$('#lambdaText').html(
				html.replace(textoRegExpFirstname, '<strong>' + data.body.parametros_busqueda[1].toUpperCase() + '</strong> ').replace(textoRegExpEmail, '<strong> ' + data.body.parametros_busqueda[2].toUpperCase() + '</strong>')
				.replace(textoRegExpPassword, '<strong>' + data.body.parametros_busqueda[7].toUpperCase() + '</strong> ')

			);



		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});
	
  }


