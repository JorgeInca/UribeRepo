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
						data[x].id_empresa,
						data[x].nombre,
						data[x].email,
						data[x].phone,				
						data[x].estatus
					])
					.order( [0,'desc'] )
					.draw(false);

			}
			

		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});
}