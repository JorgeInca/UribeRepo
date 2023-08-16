/**
 * 
 */
function cargaListaInvestigaciones() {


	var uri = "consultaReportes";

	var reporteRequest = {
		user: "jorge"

	};

	$.ajax({
		url: uri,
		type: 'POST',
		dataType: 'json',
		data: reporteRequest,
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
						'<a href="#"><strong>' + data[x].idInvestigacion + '</strong></a>',
						data[x].nombreUsuario,
						data[x].nombreEmpresa,
						data[x].apellidos,
						data[x].primer_nombe,
						data[x].riesgoInicial,
						data[x].fechaCreacion,
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