function cargaListaEmpresas() {


	var uri = "consultaEmpresas";

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
						'<a href="#" onclick="cargaEmpresaId('+data[x].id_empresa +')" ><strong>' + data[x].id_empresa + '</strong></a>',
						data[x].nombre,
						data[x].email,
						data[x].phone,				
						data[x].estatus,
						'<a href="#" onclick="editarUsuarioId('+data[x].id_usuario +')" ><i class="bi bi-pencil-square"></i></a>'
					])
					.order( [0,'desc'] )
					.draw(false);

			}
			

		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});
}



function cargaEmpresaId(id_empresa) {
	

	console.log('inicia cargaEmpresaId');

	var uri = "cargaEmpresaId";
	
	$('#ModalEmpresas').modal('show');
	

	var Empresas = {
		id_empresa: id_empresa
	};

	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: Empresas,
		success: function(data) {
			
			//Llena campos Modal de Usuarios:
			$("#noFolio").val( data.id_empresa );
			$("#noFolio1").val( data.id_empresa );
			$("#form-name" ).val( data.nombre );
			$("#form-email").val( data.email );
			$("#form-phone").val( data.phone );
			$("#form-estatus").val( data.estatus);
			
			
			

		//	investigacionGlobalUsuarios = data;
			console.log('El ID GENERADO ES ' + Empresas);

			
			//Bloquea los botones
			$("#noFolio1").prop('disabled', true);
			$("#form-name").prop('disabled', true);
			$("#form-email").prop('disabled', true);
			$("#form-phone").prop('disabled', true);
			$("#form-estatus").prop('disabled', true);
			$("#rowFolio").show(); //hide

			//Habilita los botones
			$("#limpiarEmpresas").prop('disabled', true);
			$("#EnviarUsuarios").prop('disabled', true);
			$("#updateUsuarios").prop('disabled', true);


			$("#noFolio").empty();

			//Etiquetas
			$('#noFolio').append('<strong> ' + id_empresa + ' </strong>');


		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});
	
  }









