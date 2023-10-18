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
						'<a href="#" onclick="editarEmpresaId('+data[x].id_empresa +')" ><i class="bi bi-pencil-square"></i></a>'
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


function Empresas(){
	
	$('#ModalEmpresas').modal('show');
	
	
	//Habilita los botones
			$("#rowFolio").show(); //hide
			
			$("#noFolio1").prop('disabled', true);
			$("#form-name").prop('disabled', false);
			$("#form-email").prop('disabled', false);
			$("#form-phone").prop('disabled', false);
			$("#form-estatus").prop('disabled', false);

			//Habilita los botones
			$("#limpiarUsuarios").prop('disabled', false);
			$("#EnviarUsuarios").prop('disabled', false);	
        	$("#updateUsuarios").prop('disabled', true);
	
}

function editarEmpresaId(id_empresa) {
	
	alert('Podra editar la informacion de la Empresa id ' + id_empresa);
	
	console.log('inicia editarEmpresaId');

	var uri = "editarEmpresaId";
	
	$('#ModalEmpresas').modal('show');
	
	var editarEmpresas = {
		id_empresa: id_empresa
	};
	
	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: editarEmpresas,
		success: function(data) {
			
			//Llena campos Modal de Usuarios:
			$("#noFolio").val( data.id_empresa );
			$("#noFolio1").val( data.id_empresa );
			$("#form-name").val( data.nombre );
			$("#form-email").val( data.email );
			$("#form-phone").val( data.phone );
			$("#form-estatus").val( data.estatus );
			

		//	investigacionGlobalUsuarios = data;
			console.log('El ID GENERADO ES ' + editarEmpresas);

			
			//Bloquea los botones
			$("#noFolio1").prop('disabled', true);
			$("#form-name").prop('disabled', false);
			$("#form-email").prop('disabled', false);
			$("#form-phone").prop('disabled', false);
			$("#form-estatus").prop('disabled', false);
			$("#rowFolio").show(); //hide

			//Habilita los botones
			$("#limpiarEmpresas").prop('disabled', true);
			$("#EnviarUsuarios").prop('disabled', true);
			$("#updateUsuarios").prop('disabled', false);


			$("#noFolio").empty();

			//Etiquetas
			$('#noFolio').append('<strong> ' + id_usuario + ' </strong>');


		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});
	
	
}


function actualizaEmpresa(){
	
	console.log('Inicia actualizacion');

	var uri = "actualizaEmpresa";
	
	var id_empresa = $("#noFolio1").val();
	var var_firstname = $("#form-name").val();
	var var_email = $("#form-email").val();
	var var_phone = $("#form-phone").val();
	var var_estatus = $("#form-estatus").val();
	
	if (!confirm("¡Deseas actualizar la informacion de la Empresa?")) {
		return;
	}
	
	var Empresas = {
		id_empresa: id_empresa,
	    name: var_firstname,
		email: var_email,
		phone: var_phone,
		estatus: var_estatus
		
	};
	

	
	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: Empresas,
		success: function(data) {
			
			//window.location.reload()
		
			alert('La informacion de la Empresa con Id: ' + id_empresa + ' se actualizo con exito!');
			cargaEmpresaId(id_empresa);
			tableGlobal.clear().draw();
			cargaListaEmpresas();
			

			
			
			},
		error: function(xhr, ajaxOptions, thrownError) {

		   }
		
		});
	
}




function guardaEmpresa() {

	console.log('Inicia guardardo');

	var uri = "guardaEmpresa";

	var var_firstname = $("#form-name").val();
	var var_email = $("#form-email").val();
	var var_phone = $("#form-phone").val();
	var var_estatus = $("#form-estatus").val();
	
	if (var_firstname === "" || var_email === "" || var_phone === "" || var_estatus === "" ) {
		return;
	}

	if (!confirm("¡Deseas agregar la nueva Empresa ?")) {
		return;
	}

	var Empresas = {
	    name: var_firstname,
		email: var_email,
		phone: var_phone,
		estatus: var_estatus
	};



	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: Empresas,
		success: function(data) {

			alert('El ID GENERADO DE LA NUEVA EMPRESA ES ' + data);
			
			$("#noFolio1").prop('disabled', true);
			$("#form-name").prop('disabled', true);
			$("#form-email").prop('disabled', true);
			$("#form-phone").prop('disabled', true);
			$("#form-estatus").prop('disabled', true);
			$("#rowFolio").show(); //hide
			
			$("#noFolio").empty();
			$('#noFolio').append('<strong> '+data + ' </strong>');
			
			
			//Habilita los botones
		    $("#updateUsuarios").prop('disabled', true);
		    
		    //Actualiza la lista 
		    tableGlobal.clear().draw();
			cargaListaEmpresas();


		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});
	
}	





