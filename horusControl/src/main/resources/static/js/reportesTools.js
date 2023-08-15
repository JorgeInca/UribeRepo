/**
 * 
 */
function cargaListaInvestigaciones(){
	alert("inicia load");
	
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
			
			alert(JSON.stringify(data))



		},
		error: function(xhr, ajaxOptions, thrownError) {

		}

	});
	
	
}