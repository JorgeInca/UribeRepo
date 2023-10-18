var investigacionGlobalUsuarios;
var usuariosGlobal;


function guardaUsuario() {

	console.log('Inicia guardardo');

	var uri = "guardaUsuario";

	var var_firstname = $("#form-name").val();
	var var_email = $("#form-email").val();
	var var_estatus = $("#form-estatus").val();
	var var_id_empresa = $("#form-id_empresa").val();
	var var_rol = $("#form-rol").val();
	var var_password = $("#form-password").val();
	
	if (var_firstname === "" || var_email === "" || var_estatus === "" || var_id_empresa === "" || var_rol === "" || var_password === "") {
		return;
	}

	if (!confirm("¡Deseas agregar al nuevo usuario?")) {
		return;
	}

	var UserHorus = {
	    name: var_firstname,
		email: var_email,
		estatus: var_estatus,
		idEmpresa: var_id_empresa,
		rol: var_rol,
		password: var_password
	};



	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: UserHorus,
		success: function(data) {

			alert('El ID GENERADO DEL NUEVO USUARIO ES ' + data);

		    $("#noFolio1").prop('disabled', true);
			$("#form-name").prop('disabled', true);
			$("#form-email").prop('disabled', true);
			$("#form-fecha_creacion").prop('disabled', true);
			$("#form-estatus").prop('disabled', true);
			$("#form-id_empresa").prop('disabled', true);
			$("#form-rol").prop('disabled', true);
			$("#form-password").prop('disabled', true);
			
			$("#rowFolio").show(); //hide
			
			$("#noFolio").empty();
			$('#noFolio').append('<strong> '+data + ' </strong>');
			
			
			//Habilita los botones
		    $("#updateUsuarios").prop('disabled', true);
		    
		    //Actualiza la lista 
		    tableGlobal.clear().draw();
			cargaListaUsuarios();


		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});
	
}	