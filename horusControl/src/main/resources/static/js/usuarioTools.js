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

			//alert(JSON.stringify(data));

			for (let x in data) {

				/*let tableRef = document.getElementById("horus");

				// Insert a row at the end of the table
				let newRow = tableRef.insertRow(-1);

				// Insert a cell in the row at index 0
				let newCell = newRow.insertCell(0);

				// Append a text node to the cell
				let newText = document.createTextNode(data[x].nombreUsuario);

				newCell.appendChild(newText);*/

				tableGlobal.row
					.add([
						'<a href="#"><strong>' + data[x].id_usuario + '</strong></a>',
						data[x].name,
						data[x].email,
						data[x].rol,
						data[x].email,
						'<a href="#"><i class="bi bi-cloud-download"></i></a>'
					])
					.draw(false);

			}
			
			$( "#buttontest" ).prop( "disabled", true );



		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});


}