package mx.com.rmsh.horusControl.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.com.rmsh.horusControl.dao.InvestigacionDao;
import mx.com.rmsh.horusControl.dao.SecurityDao;
import mx.com.rmsh.horusControl.enums.EstatusInvestigacion;
import mx.com.rmsh.horusControl.enums.NivelRiesgo;
import mx.com.rmsh.horusControl.enums.Pais;
import mx.com.rmsh.horusControl.utils.TextoHandler;
import mx.com.rmsh.horusControl.vo.Body;
import mx.com.rmsh.horusControl.vo.FillReportInvestigacionResultadoVO;
import mx.com.rmsh.horusControl.vo.FillReportInvestigacionVO;
import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.InvestigacionLAMBDA;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.MasivaRequest;
import mx.com.rmsh.horusControl.vo.Mentions;
import mx.com.rmsh.horusControl.vo.Origen;
import mx.com.rmsh.horusControl.vo.OrigenClasificator;
import mx.com.rmsh.horusControl.vo.ReportTransferObjectPDF;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Service
public class InvestigacionServiceImpl implements InvestigacionService {

	@Autowired
	InvestigacionDao dao;

	@Autowired
	SecurityDao daoUser;
	
	@Autowired
	AWSService awsService;

	@Override
	public List<Investigacion> getReportes(ReporteRequest request) {

		return dao.getReportes(request);
	}

	@Override
	public Long guardaInvestigacion(InvestigacionRequest request) {
		// TODO Auto-generated method stub
		return dao.guardaInvestigacion(request);
	}

	@Override
	public List<UserHorus> getUser() {
		return daoUser.getUser();
	}

	@Override
	public Long guardaUsuario(UserHorus user) {
		return daoUser.guardaUsuario(user); 
	}
	
	@Override
	public UserHorus getUsuarioById(Long id_usuario) {
		return daoUser.getUsuarioById(id_usuario);
	}
	
	@Override
	public long eliminiarUsuario(Long id_usuario) {
		return daoUser.eliminiarUsuario(id_usuario);
	}
	
	@Override
	public UserHorus editUser(Long id_usuario) {
		// TODO Auto-generated method stub
		return daoUser.editUser(id_usuario);
	}
	
	@Override
	public long updateUser(UserHorus user) {
		return daoUser.updateUser(user);
	}


	


	@Override
	public List<InvestigacionRequest> guardaInvestigacionMasiva(MasivaRequest masivaRequest) throws IOException {

		List<InvestigacionRequest> listaRetorno = new ArrayList<InvestigacionRequest>();

		// Variables Excel
		String UPLOAD_FOLDER = "C://test//";
		XSSFWorkbook excelPeticion;
		String lowerCaseFileName = masivaRequest.getFile().getOriginalFilename().toLowerCase();
		
		System.out.println( " " );

		if (lowerCaseFileName.endsWith(".xlsx")) {

			excelPeticion = new XSSFWorkbook(masivaRequest.getFile().getInputStream());
			excelPeticion.setMissingCellPolicy(MissingCellPolicy.RETURN_BLANK_AS_NULL);

			XSSFSheet sheet = excelPeticion.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			boolean esCabecera = true;

			while (rowIterator.hasNext()) {
				
				Row row = rowIterator.next();
				
				if (!esCabecera) {
					
					System.out.println("** SI ENTRA" + esCabecera);			
					InvestigacionRequest investigacion = new InvestigacionRequest();
					
					
					DataFormatter fmt = new DataFormatter();

					// For each row, iterate through all the
					// columns
					Iterator<Cell> cellIterator = row.cellIterator();

					int cellActual = 1;

					while (cellIterator.hasNext()) {
						System.out.println("** SI ASDENTRA" + cellActual);
						
						Cell cell = (Cell) cellIterator.next();

						if( cellActual == 3 ) 							
							investigacion.setLastname(fmt.formatCellValue(cell));
						if( cellActual == 4 )
							investigacion.setFirstname(fmt.formatCellValue(cell));
						if( cellActual == 5 ) 
							investigacion.setFirstname( TextoHandler.sumaNombres(investigacion.getFirstname(), fmt.formatCellValue(cell))	);
						if( cellActual == 9 ) 
							investigacion.setPais( Pais.getIdByName(fmt.formatCellValue(cell)) );
						if( cellActual == 16 ) 
							investigacion.setRfc(fmt.formatCellValue(cell));
						
						investigacion.setIdUsuario(masivaRequest.getIdUsuario());
						investigacion.setNivel_riesgo(0);
						investigacion.setIdMasiva(0L);
						investigacion.setInvestigacionJson("");
						investigacion.setIdEstatus( EstatusInvestigacion.PENDIENTE.getIdEstatus() );
											
						
						cellActual++;						
					}
					
					//Campos vacios
					if( !( "".equals(investigacion.getFirstname()) && "".equals(investigacion.getLastname()) ) )
							listaRetorno.add(investigacion);
				}
				esCabecera = false;
			}
		}
		
		System.out.println("Archivos procesados");
		
		for(InvestigacionRequest model : listaRetorno) {
			System.out.println();
            System.out.println(model.toString());
        }
		
		dao.guardaInvestigacionMasiva(listaRetorno);

		return listaRetorno;
	}

	@Override
	public ResponseEntity<ByteArrayResource> getPDFInvestigacion(Long idInvestigacion) {
		
		System.out.println("********* [Service] getPDFInvestigacion : ");
		
		boolean linux = false; 
		
		//Delete first File.separator for Windows
		String reportFolderPath =  ( linux ? File.separator : "" ) + "src" + File.separator + "main" + File.separator + "java" + File.separator + "mx"
				+ File.separator + "com" + File.separator + "rmsh" + File.separator + "horusControl" + File.separator + "reportes"+ File.separator;
	
		//Nombres reportes
		String reportName =   reportFolderPath +"reporteInvestigacion";
		String reportName2 =   reportFolderPath +"reporteInvestigacion2";
		String reportName3 =   reportFolderPath +"reporteInvestigacion3";
		
		//NombresSuubreportes
		
		InvestigacionRequest reguest = new InvestigacionRequest();
		reguest.setIdInvestigacion(idInvestigacion);		
		InvestigacionLAMBDA lambdaQuery = awsService.getJSONFromBD(reguest);
		lambdaQuery.setIdInvestigacion(idInvestigacion);


		try {
			
			//*************************************************************************Primer Reporte
			//Consigue los datos para llenar parametros y los carga al DS del reporte
			ArrayList <FillReportInvestigacionVO> miListaObject = new ArrayList<FillReportInvestigacionVO>();	
			
			ReportTransferObjectPDF datosParam = fillReport( lambdaQuery );
			FillReportInvestigacionVO nuevo = new FillReportInvestigacionVO();
			miListaObject.add(nuevo);
					
			JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(miListaObject);
			
			//Create 5 subreports Checklist
			JasperReport subInt = JasperCompileManager.compileReport(reportFolderPath +"intcump"	+ ".jrxml");
			JasperReport subNac = JasperCompileManager.compileReport(reportFolderPath +"natcump"	+ ".jrxml");
			JasperReport subPep = JasperCompileManager.compileReport(reportFolderPath +"pep"		+ ".jrxml");
			JasperReport subOth = JasperCompileManager.compileReport(reportFolderPath +"others"		+ ".jrxml");
			JasperReport submen = JasperCompileManager.compileReport(reportFolderPath +"mentions"	+ ".jrxml");
			
			// generate the PDF 1
			Map<String, Object> parameter = datosParam.getParameter();	
			
			parameter.put("subreportParameter", subInt);
			parameter.put("subreportParameter2", subNac);
			parameter.put("subreportParameter3", subPep);
			parameter.put("subreportParameter4", subOth);
			parameter.put("subreportParameter5", submen);
			parameter.put("SUBREPORT_DATA", datosParam.getObjetosCount());
			parameter.put("SUBREPORT_DATA2", datosParam.getObjetosResultado());

			
			// compiles jrxml
			JasperCompileManager.compileReportToFile(reportName + ".jrxml");
			JasperPrint print = JasperFillManager.fillReport(reportName + ".jasper", parameter,beanCollectionDataSource);
			
			// exports report to pdf
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(reportName + ".pdf"));
			exporter.exportReport();


			
			//*************************************************************************Segundo Reporte
			
			ArrayList <FillReportInvestigacionResultadoVO> miListaObject2 = new ArrayList<FillReportInvestigacionResultadoVO>();
			FillReportInvestigacionResultadoVO nuevoResultado = new FillReportInvestigacionResultadoVO();
			miListaObject2.add(nuevoResultado);
			
			//Create 5 subreports Checklist
			JasperReport subIntGlobal = JasperCompileManager.compileReport(reportFolderPath +"intcumpGlobal"	+ ".jrxml");
			
			parameter.put("subreportParameter6", subIntGlobal);
			
			JRDataSource beanCollectionDataSource2 = new JRBeanCollectionDataSource(miListaObject2);
			
			// compiles jrxml
			JasperCompileManager.compileReportToFile(reportName2 + ".jrxml");
			// fills compiled report with parameters and a connection
			JasperPrint print2 = JasperFillManager.fillReport(reportName2 + ".jasper", parameter,beanCollectionDataSource2);
			// exports report to pdf
			JRExporter exporter2 = new JRPdfExporter();
			exporter2.setParameter(JRExporterParameter.JASPER_PRINT, print2);
			exporter2.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(reportName2 + ".pdf"));
			exporter2.exportReport();
			
			
			//*************************************************************************Tercer Reporte
			
			// generate the PDF 3
			Map<String, Object> parameters3 = new HashMap<String, Object>();
			parameters3.put("clienteName", "Coca-Cola");
			parameters3.put("idInvestigacion", idInvestigacion.toString());
			parameters3.put("nombreCompleto", lambdaQuery.getBody().getParametros_busqueda().get(0) + " " + lambdaQuery.getBody().getParametros_busqueda().get(1));
			parameters3.put("nivelRiesgoTexto", NivelRiesgo.getNameyId( lambdaQuery.getBody().getNivel_riesgo() ) );
			
			// compiles jrxml
			JasperCompileManager.compileReportToFile(reportName3 + ".jrxml");
			// fills compiled report with parameters and a connection
			JasperPrint print3 = JasperFillManager.fillReport(reportName3 + ".jasper", parameters3,
					new JREmptyDataSource());
			// exports report to pdf
			JRExporter exporter3 = new JRPdfExporter();
			exporter3.setParameter(JRExporterParameter.JASPER_PRINT, print3);
			exporter3.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(reportName3 + ".pdf"));
			exporter3.exportReport();
			
			//*************************************************************************Genera PDF
			//Add Pdfs
			String finalReportName = reportFolderPath + "Investigacion" + parameter.get("idInvestigacion") + ".pdf";
			PDFMergerUtility ut = new PDFMergerUtility();
			ut.addSource(reportName + ".pdf");
			ut.addSource(reportName2 + ".pdf");
			ut.addSource(reportName3 + ".pdf");
			ut.setDestinationFileName( finalReportName );
			ut.mergeDocuments();
			
			//Create Final PDF			
			File file = new File(finalReportName);
			System.out.println("Archivo abierto PDF");
			Path path = Paths.get(file.getAbsolutePath());
			byte[] data = Files.readAllBytes(path);			
			ByteArrayResource resource = new ByteArrayResource(data);
			
			System.out.println("fileName: " + file.getName());
			
			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
					// Content-Type
					.contentType(MediaType.APPLICATION_JSON_UTF8) //
					// Content-Lengh
					.contentLength(data.length) //
					.body(resource);

		} catch (Exception e) {
			throw new RuntimeException("It's not possible to generate the pdf report.", e);
		} finally {

		}
	}

	 
	private ReportTransferObjectPDF fillReport( InvestigacionLAMBDA lambdaQuery ) {
		
		ReportTransferObjectPDF reportTransferObjectPDF = new ReportTransferObjectPDF();
		
		Map<String, Object> parameter= new HashMap<String, Object>();
		ArrayList<FillReportInvestigacionVO> listaObjetos = new ArrayList<FillReportInvestigacionVO>();
		ArrayList<FillReportInvestigacionResultadoVO> listaObjetosResultado = new ArrayList<FillReportInvestigacionResultadoVO>();
		
		//Objeto vacio para evitar el reporte blanco
		listaObjetos.add(new FillReportInvestigacionVO());
		listaObjetosResultado.add(new FillReportInvestigacionResultadoVO());
		
		//Datos Usuario
		parameter.put("clienteName",      "Coca-Cola");
		parameter.put("idInvestigacion",   String.valueOf( lambdaQuery.getIdInvestigacion().toString() ));
		parameter.put("nombreCompleto",   lambdaQuery.getBody().getParametros_busqueda().get(0) + " " + lambdaQuery.getBody().getParametros_busqueda().get(1));
		parameter.put("nivelRiesgoTexto", NivelRiesgo.getNameyId( lambdaQuery.getBody().getNivel_riesgo() ) );
		
		String hayintcump = "I_GREEN";
		String haynatcump = "I_GREEN";
		String hayintPEP = "I_GREEN";
		String hayOthers = "I_GREEN";
		String hayMentions = "I_GREEN";
		
		//Origenes
		for (Origen origen : lambdaQuery.getBody().getOrigen()) {			
			
			if( "intcump".equals(origen.getCategory()) ) {
				FillReportInvestigacionVO origenFill= new FillReportInvestigacionVO();
				hayintcump = "I_RED";
				origenFill.setListaCumIn(origen.getFuente()  );
				listaObjetos.add(origenFill);
			}
			if( "natcump".equals(origen.getCategory()) ) {
				FillReportInvestigacionVO origenFill= new FillReportInvestigacionVO();
				haynatcump = "I_RED";
				origenFill.setListaCumNac(origen.getFuente());
				listaObjetos.add(origenFill);
			}
			if( "PEP".equals(origen.getCategory()) ) {
				FillReportInvestigacionVO origenFill= new FillReportInvestigacionVO();
				hayintPEP = "I_RED";
				origenFill.setListaPEP(origen.getFuente());
				listaObjetos.add(origenFill);
			}
			if( "others".equals(origen.getCategory()) ) {
				FillReportInvestigacionVO origenFill= new FillReportInvestigacionVO();
				hayOthers = "I_RED";
				origenFill.setListaOthers(origen.getFuente());
				listaObjetos.add(origenFill);
			}			
			
		}
		
		//Mentions
		if (lambdaQuery.getBody().getMentions().size()>0) {
			
			hayMentions = "I_RED";
			
			FillReportInvestigacionVO origenFill= new FillReportInvestigacionVO();
			origenFill.setListaMentions("Entradas Identificadas ( "+ lambdaQuery.getBody().getMentions().size() +" )");
			listaObjetos.add(origenFill);
		
		}
		
		parameter.put("hayintcump", hayintcump); //I_GREEN,I_RED
		parameter.put("haynatcump", haynatcump); //I_GREEN,I_RED
		parameter.put("hayintPEP",  hayintPEP); //I_GREEN,I_RED
		parameter.put("hayOthers",  hayOthers); //I_GREEN,I_RED
		parameter.put("hayMentions",hayMentions); //I_GREEN,I_RED
		
		//Datos reporte Resultados
		OrigenClasificator reultados = new OrigenClasificator();
		reultados = clasifica(lambdaQuery.getBody());

		//Título 1
		FillReportInvestigacionResultadoVO titulo1 = new FillReportInvestigacionResultadoVO();
		titulo1.setG_titulo("Listados de Cumplimiento Internacionales:");
		listaObjetosResultado.add(titulo1);
		listaObjetosResultado.addAll(reultados.getInternacional());
		
		//Título 2
		FillReportInvestigacionResultadoVO titulo2 = new FillReportInvestigacionResultadoVO();
		titulo2.setG_titulo("Listados de Cumplimiento Nacionales:");
		listaObjetosResultado.add(titulo2);
		listaObjetosResultado.addAll(reultados.getNacional());
		
		//Título 3
		FillReportInvestigacionResultadoVO titulo3 = new FillReportInvestigacionResultadoVO();
		titulo3.setG_titulo("Personas Políticamente Expuestas:");
		listaObjetosResultado.add(titulo3);		
		listaObjetosResultado.addAll(reultados.getPep());

		//Título 4
		FillReportInvestigacionResultadoVO titulo4 = new FillReportInvestigacionResultadoVO();
		titulo4.setG_titulo("Otras Bases de datos:");
		listaObjetosResultado.add(titulo4);
		listaObjetosResultado.addAll(reultados.getOtros());
		
		//Título 5
		FillReportInvestigacionResultadoVO titulo5 = new FillReportInvestigacionResultadoVO();
		titulo5.setG_titulo("Menciones:");
		listaObjetosResultado.add(titulo5);
		listaObjetosResultado.addAll(reultados.getMenciones());
		
		//Resumen
		reportTransferObjectPDF.setObjetosCount(listaObjetos);
		reportTransferObjectPDF.setObjetosResultado(listaObjetosResultado);
		reportTransferObjectPDF.setParameter(parameter);
		
		return reportTransferObjectPDF;
		
	}
	
	public OrigenClasificator clasifica(Body body) {
		
		OrigenClasificator resultadoFInal =  new OrigenClasificator();
		
		//Vacios para no afectar el data_source
		ArrayList<FillReportInvestigacionResultadoVO> internacional = new ArrayList<FillReportInvestigacionResultadoVO>();
		
		ArrayList<FillReportInvestigacionResultadoVO> nacional = new ArrayList<FillReportInvestigacionResultadoVO>();
		nacional.add(new FillReportInvestigacionResultadoVO());
		ArrayList<FillReportInvestigacionResultadoVO> pep = new ArrayList<FillReportInvestigacionResultadoVO>();
		pep.add(new FillReportInvestigacionResultadoVO());
		ArrayList<FillReportInvestigacionResultadoVO> otros = new ArrayList<FillReportInvestigacionResultadoVO>();
		otros.add(new FillReportInvestigacionResultadoVO());
		ArrayList<FillReportInvestigacionResultadoVO> menciones = new ArrayList<FillReportInvestigacionResultadoVO>();
		menciones.add(new FillReportInvestigacionResultadoVO());

		

		for (Origen origen : body.getOrigen()) {

			if ("intcump".equals(origen.getCategory())) {
				FillReportInvestigacionResultadoVO nuevo =new FillReportInvestigacionResultadoVO();
				nuevo.setG_origen(origen.getFuente());				
				
				if( 0 == origen.getIsJSON() )
					nuevo.setG_freeText(origen.getFree_text());
				else
					nuevo.setG_table( createTable( origen.getTexto()) );
				
				internacional.add(nuevo);
			}
			if ("natcump".equals(origen.getCategory())) {
				FillReportInvestigacionResultadoVO nuevo =new FillReportInvestigacionResultadoVO();
				nuevo.setG_origen(origen.getFuente());
				
				if( 0 == origen.getIsJSON() )
					nuevo.setG_freeText(origen.getFree_text());
				else
					nuevo.setG_table( createTable( origen.getTexto()) );
				
				nacional.add(nuevo);
			}
			if ("PEP".equals(origen.getCategory())) {
				FillReportInvestigacionResultadoVO nuevo =new FillReportInvestigacionResultadoVO();
				nuevo.setG_origen(origen.getFuente());
				
				if( 0 == origen.getIsJSON() )
					nuevo.setG_freeText(origen.getFree_text());
				else
					nuevo.setG_table( createTable( origen.getTexto()) );
				
				pep.add(nuevo);
			}
			if ("others".equals(origen.getCategory())) {
				FillReportInvestigacionResultadoVO nuevo =new FillReportInvestigacionResultadoVO();
				nuevo.setG_origen(origen.getFuente());
				
				if( 0 == origen.getIsJSON() )
					nuevo.setG_freeText(origen.getFree_text());
				else
					nuevo.setG_table( createTable( origen.getTexto()) );
				
				otros.add(nuevo);
			}

		}
		
		for (Mentions origen : body.getMentions()) {

			FillReportInvestigacionResultadoVO nuevo =new FillReportInvestigacionResultadoVO();
			nuevo.setG_origen(origen.getTitle());
			nuevo.setG_freeText(origen.getDescription() + " \n \n " + origen .getLink());
			nuevo.setG_keyword(origen.getKeyword());
			nuevo.setG_mentionImage(origen.getEngine());
			menciones.add(nuevo);

		}
		
		if( internacional.size() == 0) {
			internacional.add(new FillReportInvestigacionResultadoVO());
		}
		if( nacional.size() == 0) {
			nacional.add(new FillReportInvestigacionResultadoVO());
		}
		if( pep.size() == 0) {
			pep.add(new FillReportInvestigacionResultadoVO());
		}
		if( otros.size() == 0) {
			otros.add(new FillReportInvestigacionResultadoVO());
		}
		if( menciones.size() == 0) {
			menciones.add(new FillReportInvestigacionResultadoVO());
		}
		
		
		resultadoFInal.setInternacional(internacional);
		resultadoFInal.setNacional(nacional);
		resultadoFInal.setPep(pep);
		resultadoFInal.setOtros(otros);
		resultadoFInal.setMenciones(menciones);

		return resultadoFInal;
	}
	
	public String createTable(Map<String, String> texto) {
		
		
		String tablaHtml = "<table border=1 cellspacing=0><tr>"
				+ "<th>Dato</th><th>Valor</th>"
				+ "</tr>";
		
		for (Map.Entry<String, String> entry : texto.entrySet()) {
			
			tablaHtml = tablaHtml + "<tr>";
			tablaHtml = tablaHtml +"<td>" + entry.getKey() + "</td>";
			tablaHtml = tablaHtml +"<td>" + entry.getValue() + "</td>";
			tablaHtml = tablaHtml + "</tr>";
			
		}
		
		
		tablaHtml = tablaHtml + "</table>";
		
		System.out.println(tablaHtml);
		
		return tablaHtml;
	}

	

}
