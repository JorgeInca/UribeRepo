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
						'<a href="#"><i class="bi bi-pencil-square"></i></a>',
					    '<a href="#" onclick="eliminarUsuarioId('+data[x].id_usuario +')" ><i class="bi bi-trash"></i></a>'
					])
					.order( [0,'desc'] )
					.draw(false);

			}
			

		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});
}

function cargaUsuarioId(id_usuario) {
	
	//alert('Revisando el Id :' + id_usuario);

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
			
			//Llena campos Modal de Usuarios:
			$("#noFolio").val( data.id_usuario );
			$("#noFolio1").val( data.id_usuario );
			$("#form-name" ).val( data.name );
			$("#form-email").val( data.email );
			
			$("#form-fecha_creacion").val( data.fecha_creacion);
			$("#form-estatus").val( data.estatus);
			$("#form-id_empresa").val( data.idEmpresa);
			$("#form-rol").val( data.rol);
			$("#form-password").val( data.password);
			

		//	investigacionGlobalUsuarios = data;
			console.log('El ID GENERADO ES ' + UserHorus);

			
			//Bloquea los botones
			$("#noFolio1").prop('disabled', true);
			$("#form-name").prop('disabled', true);
			$("#form-email").prop('disabled', true);
			$("#form-fecha_creacion").prop('disabled', true);
			$("#form-estatus").prop('disabled', true);
			$("#form-id_empresa").prop('disabled', true);
			$("#form-rol").prop('disabled', true);
			$("#form-password").prop('disabled', true);
			$("#rowFolio").show(); //hide

			//Habilita los botones
			$("#limpiarUsuarios").prop('disabled', true);
			$("#EnviarUsuarios").prop('disabled', true);


			$("#noFolio").empty();

			//Etiquetas
			$('#noFolio').append('<strong> ' + id_usuario + ' </strong>');


		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});
	
  }
  
  function eliminarUsuarioId(id_usuario) {
	  
     alert('Eliminando el id ' + id_usuario);

	console.log('Inicia eliminarUsuarioId');
	
	var uri = "eliminarUsuarioId";
	
	if (!confirm("¡Seguro quieres eliminar al usuario?")) {
		return;
	}
	
	var UserHorus = {
		id_usuario: id_usuario
	};
	
	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: UserHorus,
		success: function(data) {
			
		
			alert('El Id eliminado fue :' + id_usuario);

		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

   	});

}