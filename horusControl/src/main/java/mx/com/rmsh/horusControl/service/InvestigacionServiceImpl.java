package mx.com.rmsh.horusControl.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import com.google.gson.Gson;

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
import mx.com.rmsh.horusControl.vo.Masiva;
import mx.com.rmsh.horusControl.vo.MasivaRequest;
import mx.com.rmsh.horusControl.vo.Mentions;
import mx.com.rmsh.horusControl.vo.Origen;
import mx.com.rmsh.horusControl.vo.OrigenClasificator;
import mx.com.rmsh.horusControl.vo.OrigenesBorradoRequest;
import mx.com.rmsh.horusControl.vo.ReportTransferObjectPDF;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.RiesgoRequest;
import mx.com.rmsh.horusControl.vo.Tabla;
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
		
		//Integer riesgoEditado = dao.getRiesgoFInal(lambdaQuery.getIdInvestigacion());
		List<Investigacion> reportes = dao.getReportes(request);
			

		for (int i = 0; i < reportes.size(); i++) {
			
			try {
				
				Gson gson = new Gson();
				
				InvestigacionLAMBDA lambda = gson.fromJson(reportes.get(i).getJson(), InvestigacionLAMBDA.class);	
				
				lambda.getBody().setEliminadosOrigenes(reportes.get(i).getOrigenesEliminados());
				lambda.getBody().setEliminadosMentions(reportes.get(i).getMencionesEliminadas());
				
				String fromArrayOrigenes = lambda.getBody().getEliminadosOrigenes();		
				List<String> origenesEliminados = Arrays.asList( fromArrayOrigenes.split("\\s*,\\s*") );//Arrays.asList(fromArray.split("\\s*,\\s*"));
				String fromArrayMentions = lambda.getBody().getEliminadosMentions();
				List<String> mentionsEliminados = Arrays.asList( fromArrayMentions.split("\\s*,\\s*") );//Arrays.asList(fromArray.split("\\s*,\\s*"));
				
				
				int intcump = 0;
				int natcump = 0;
				int pep = 0;
				int others = 0;
				int mentions = 0;			
						
				for (Origen origen : lambda.getBody().getOrigen()) {
								
					
					if( !origenesEliminados.contains( origen.getId().toString() ) ){
						
						if ("intcump".equals(origen.getCategory())) {
							intcump++;
						}
						if ("natcump".equals(origen.getCategory())) {
							natcump++;
						}
						if ("PEP".equals(origen.getCategory())) {
							pep++;
						}
						if ("others".equals(origen.getCategory())) {
							others++;
						}
						
					}
					
				}
				
				for (Mentions mention : lambda.getBody().getMentions()) {
					
					
					
					//System.out.println("mentionsEliminados " + mentionsEliminados);
					//System.out.println("mentionsEliminados " + mention.getId());
					
					if( !mentionsEliminados.contains( mention.getId().toString() ) ){				
					
						mentions++;					
						
					}
					
				}
				
				String riesgoAcumulado = "";
				
				if( intcump>0 )
					riesgoAcumulado = riesgoAcumulado + "Listado de Cumplimientos Internacionales<br/>";
				if( natcump>0 )
					riesgoAcumulado = riesgoAcumulado + "Listado de Cumplimientos Nacionales<br/>";
				if( pep>0 )
					riesgoAcumulado = riesgoAcumulado + "Personas Politicamente Expuestas<br/>";
				if( others>0 )
					riesgoAcumulado = riesgoAcumulado + "Otras Bases de Datos<br/>";
				if( mentions>0 )
					riesgoAcumulado = riesgoAcumulado + "Menciones Adversas";
								
				reportes.get(i).setRiesgoAcumulado(riesgoAcumulado);
								

			} catch (Exception e) {
				System.out.println( e );
			}
            
        }
		
		//System.out.println( reportes.toString() );
		
		return reportes;
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
	public Long guardaInvestigacionMasiva(MasivaRequest masivaRequest) throws IOException {

		List<InvestigacionRequest> listaRetorno = new ArrayList<InvestigacionRequest>();
		
		Long idMasiva = 0L;

		// Variables Excel
		
		XSSFWorkbook excelPeticion;
		String lowerCaseFileName = masivaRequest.getFile().getOriginalFilename().toLowerCase();

		System.out.println(" ");

		if (lowerCaseFileName.endsWith(".xlsx")) {

			excelPeticion = new XSSFWorkbook(masivaRequest.getFile().getInputStream());
			excelPeticion.setMissingCellPolicy(MissingCellPolicy.RETURN_BLANK_AS_NULL);

			XSSFSheet sheet = excelPeticion.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			
			idMasiva = dao.guardaMasiva(masivaRequest);

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

						if (cellActual == 3)
							investigacion.setLastname(fmt.formatCellValue(cell));
						if (cellActual == 4)
							investigacion.setFirstname(fmt.formatCellValue(cell));
						if (cellActual == 5)
							investigacion.setFirstname(
									TextoHandler.sumaNombres(investigacion.getFirstname(), fmt.formatCellValue(cell)));
						if (cellActual == 9)
							investigacion.setPais(Pais.getIdByName(fmt.formatCellValue(cell)));
						if (cellActual == 16)
							investigacion.setRfc(fmt.formatCellValue(cell));

						investigacion.setIdUsuario(masivaRequest.getIdUsuario());
						investigacion.setNivel_riesgo(null);
						investigacion.setIdMasiva(idMasiva);
						investigacion.setInvestigacionJson("");
						investigacion.setIdEstatus(EstatusInvestigacion.PENDIENTE.getIdEstatus());

						cellActual++;
					}

					// Campos vacios o nul
					if (!(investigacion.getFirstname() == null && investigacion.getLastname() == null)) {
						if (!("".equals(investigacion.getFirstname()) && "".equals(investigacion.getLastname())))
							listaRetorno.add(investigacion);
					}
				}
				esCabecera = false;
			}
		}

		System.out.println("Archivos procesados");

		for (InvestigacionRequest model : listaRetorno) {
			System.out.println();
			System.out.println(model.toString());
		}
		
		
		System.out.println("Let job works");
		
		dao.guardaInvestigacionMasiva(listaRetorno);

		return idMasiva;
	}

	@Override
	public ResponseEntity<ByteArrayResource> getPDFInvestigacion(Long idInvestigacion, String campaign, boolean s3Exists) {

		System.out.println("********* [Service] getPDFInvestigacion : " +  idInvestigacion + " campaign: " +  campaign +" s3Exists: " + s3Exists );

		boolean linux = true;

		// Delete first File.separator for Windows
		String reportFolderPath = (linux ? File.separator : "") + "src" + File.separator + "main" + File.separator
				+ "java" + File.separator + "mx" + File.separator + "com" + File.separator + "rmsh" + File.separator
				+ "horusControl" + File.separator + "reportes" + File.separator;

		// Nombres reportes
		String reportName = reportFolderPath + "reporteInvestigacion";
		String reportName2 = reportFolderPath + "reporteInvestigacion2";
		String reportName3 = reportFolderPath + "reporteInvestigacion3";

		// NombresSuubreportes

		InvestigacionRequest reguest = new InvestigacionRequest();
		reguest.setIdInvestigacion(idInvestigacion);
		InvestigacionLAMBDA lambdaQuery = awsService.getJSONFromBD(reguest);
		lambdaQuery.setIdInvestigacion(idInvestigacion);		
		
		Long idUsuario = dao.getIdUserByInvestigacionId(idInvestigacion);
		
		lambdaQuery.setNombreEmpresa( getNombreClientePorUsuario(idUsuario));
		

		try {

			// *************************************************************************Primer
			// Reporte
			// Consigue los datos para llenar parametros y los carga al DS del reporte
			ArrayList<FillReportInvestigacionVO> miListaObject = new ArrayList<FillReportInvestigacionVO>();

			ReportTransferObjectPDF datosParam = fillReport(lambdaQuery);
			FillReportInvestigacionVO nuevo = new FillReportInvestigacionVO();
			miListaObject.add(nuevo);

			JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(miListaObject);

			// Create 5 subreports Checklist
			JasperReport subInt = JasperCompileManager.compileReport(reportFolderPath + "intcump" + ".jrxml");
			JasperReport subNac = JasperCompileManager.compileReport(reportFolderPath + "natcump" + ".jrxml");
			JasperReport subPep = JasperCompileManager.compileReport(reportFolderPath + "pep" + ".jrxml");
			JasperReport subOth = JasperCompileManager.compileReport(reportFolderPath + "others" + ".jrxml");
			JasperReport submen = JasperCompileManager.compileReport(reportFolderPath + "mentions" + ".jrxml");

			// generate the PDF 1
			Map<String, Object> parameter = datosParam.getParameter();

			parameter.put("subreportParameter", subInt);
			parameter.put("nombre_logo", "LOGO_"+lambdaQuery.getNombreEmpresa());
			parameter.put("subreportParameter2", subNac);
			parameter.put("subreportParameter3", subPep);
			parameter.put("subreportParameter4", subOth);
			parameter.put("subreportParameter5", submen);
			parameter.put("SUBREPORT_DATA", datosParam.getObjetosCount());
			parameter.put("SUBREPORT_DATA2", datosParam.getObjetosResultado());

			// compiles jrxml
			JasperCompileManager.compileReportToFile(reportName + ".jrxml");
			JasperPrint print = JasperFillManager.fillReport(reportName + ".jasper", parameter,
					beanCollectionDataSource);

			// exports report to pdf
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(reportName + ".pdf"));
			exporter.exportReport();

			// *************************************************************************Segundo
			// Reporte

			ArrayList<FillReportInvestigacionResultadoVO> miListaObject2 = new ArrayList<FillReportInvestigacionResultadoVO>();
			FillReportInvestigacionResultadoVO nuevoResultado = new FillReportInvestigacionResultadoVO();
			miListaObject2.add(nuevoResultado);

			// Create 5 subreports Checklist
			JasperReport subIntGlobal = JasperCompileManager
					.compileReport(reportFolderPath + "intcumpGlobal" + ".jrxml");
		

			parameter.put("subreportParameter6", subIntGlobal);

			JRDataSource beanCollectionDataSource2 = new JRBeanCollectionDataSource(miListaObject2);

			// compiles jrxml
			JasperCompileManager.compileReportToFile(reportName2 + ".jrxml");
			// fills compiled report with parameters and a connection
			JasperPrint print2 = JasperFillManager.fillReport(reportName2 + ".jasper", parameter,
					beanCollectionDataSource2);
			// exports report to pdf
			JRExporter exporter2 = new JRPdfExporter();
			exporter2.setParameter(JRExporterParameter.JASPER_PRINT, print2);
			exporter2.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(reportName2 + ".pdf"));
			exporter2.exportReport();

			// *************************************************************************Tercer
			// Reporte

			// generate the PDF 3
			Map<String, Object> parameters3 = new HashMap<String, Object>();
			parameters3.put("clienteName", lambdaQuery.getNombreEmpresa());
			parameters3.put("idInvestigacion", idInvestigacion.toString());
			parameters3.put("nombreCompleto", lambdaQuery.getBody().getParametros_busqueda().get(0) + " "
					+ lambdaQuery.getBody().getParametros_busqueda().get(1));
			//parameters3.put("nivelRiesgoTexto", NivelRiesgo.getNameyId(lambdaQuery.getBody().getNivel_riesgo()));

			// compiles jrxml
			JasperCompileManager.compileReportToFile(reportName3 + ".jrxml");
			// fills compiled report with parameters and a connection
			JasperPrint print3 = JasperFillManager.fillReport(reportName3 + ".jasper", parameter,
					new JREmptyDataSource());
			// exports report to pdf
			JRExporter exporter3 = new JRPdfExporter();
			exporter3.setParameter(JRExporterParameter.JASPER_PRINT, print3);
			exporter3.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(reportName3 + ".pdf"));
			exporter3.exportReport();

			// *************************************************************************Genera
			// PDF
			// Add Pdfs
			String todayAsString = "_" + (new SimpleDateFormat("ddMMyyyy").format(new Date()) );
			
			String finalReportName = reportFolderPath +  ((String)parameter.get("nombreCompleto")).replaceAll("\\s+","") + "_" + idInvestigacion.toString() + ".pdf";
			
			PDFMergerUtility ut = new PDFMergerUtility();
			ut.addSource(reportName + ".pdf");
			ut.addSource(reportName2 + ".pdf");
			ut.addSource(reportName3 + ".pdf");
			ut.setDestinationFileName(finalReportName);
			ut.mergeDocuments();

			// Create Final PDF
			File file = new File(finalReportName);
			System.out.println("Archivo abierto PDF");
			Path path = Paths.get(file.getAbsolutePath());
			byte[] data = Files.readAllBytes(path);
			ByteArrayResource resource = new ByteArrayResource(data);

			System.out.println("fileName: " + file.getName());
			
			if(!s3Exists) {
				
				System.out.println("Guardando en S3");
				
				String newFolder = lambdaQuery.getNombreEmpresa() + campaign;
				
				String urlFolder = awsService.createFolder(newFolder);
				String urlFile =   awsService.saveFile(newFolder,file);
				
				System.out.println("URL " + urlFolder + " is ready.");
				System.out.println("URL " + urlFile + " is ready.");
				
				
			}

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

	private ReportTransferObjectPDF fillReport(InvestigacionLAMBDA lambdaQuery) {

		ReportTransferObjectPDF reportTransferObjectPDF = new ReportTransferObjectPDF();

		Map<String, Object> parameter = new HashMap<String, Object>();
		ArrayList<FillReportInvestigacionVO> listaObjetos = new ArrayList<FillReportInvestigacionVO>();
		ArrayList<FillReportInvestigacionResultadoVO> listaObjetosResultado = new ArrayList<FillReportInvestigacionResultadoVO>();

		// Objeto vacio para evitar el reporte blanco
		listaObjetos.add(new FillReportInvestigacionVO());
		//listaObjetosResultado.add(new FillReportInvestigacionResultadoVO());
		
		
		// Datos Usuario
		parameter.put("clienteName", lambdaQuery.nombreEmpresa);
		parameter.put("idInvestigacion", String.valueOf(lambdaQuery.getIdInvestigacion().toString()));
		parameter.put("nombreCompleto", lambdaQuery.getBody().getParametros_busqueda().get(0) + " "
				+ lambdaQuery.getBody().getParametros_busqueda().get(1));
		
		
		Integer riesgoEditado = dao.getRiesgoFInal(lambdaQuery.getIdInvestigacion());
		
		if( ! (riesgoEditado == null) ) {
			lambdaQuery.getBody().setNivel_riesgo(riesgoEditado);
			lambdaQuery.getBody().setNivel_riesgo_editado(riesgoEditado);
		}
		else
			lambdaQuery.getBody().setNivel_riesgo_editado( 0 );
		
		parameter.put("nivelRiesgoTexto", NivelRiesgo.getNameyId(lambdaQuery.getBody().getNivel_riesgo()));

		String hayintcump = "I_GREEN";
		String haynatcump = "I_GREEN";
		String hayintPEP = "I_GREEN";
		String hayOthers = "I_GREEN";
		String hayMentions = "I_GREEN";
		
		String fromArrayOrigenes = lambdaQuery.getBody().getEliminadosOrigenes();
		List<String> origenesEliminados = Arrays.asList( fromArrayOrigenes.split("\\s*,\\s*") );//Arrays.asList(fromArray.split("\\s*,\\s*"));
		String fromArrayMentions = lambdaQuery.getBody().getEliminadosMentions();
		List<String> mentionsEliminados = Arrays.asList( fromArrayMentions.split("\\s*,\\s*") );//Arrays.asList(fromArray.split("\\s*,\\s*"));
		
		// Origenes		
		for (Origen origen : lambdaQuery.getBody().getOrigen()) {
			
			if( !origenesEliminados.contains( origen.getId().toString() ) ){

					if ("intcump".equals(origen.getCategory())) {
						FillReportInvestigacionVO origenFill = new FillReportInvestigacionVO();
						hayintcump = "I_RED";
						origenFill.setListaCumIn( "•	" + origen.getFuente());
						listaObjetos.add(origenFill);
					}
					if ("natcump".equals(origen.getCategory())) {
						FillReportInvestigacionVO origenFill = new FillReportInvestigacionVO();
						haynatcump = "I_RED";
						origenFill.setListaCumNac( "•	" + origen.getFuente());
						listaObjetos.add(origenFill);
					}
					if ("PEP".equals(origen.getCategory())) {
						FillReportInvestigacionVO origenFill = new FillReportInvestigacionVO();
						hayintPEP = "I_RED";
						origenFill.setListaPEP( "•	" + origen.getFuente());
						listaObjetos.add(origenFill);
					}
					if ("others".equals(origen.getCategory())) {
						FillReportInvestigacionVO origenFill = new FillReportInvestigacionVO();
						hayOthers = "I_GREEN";
						origenFill.setListaOthers( "•	" + origen.getFuente());
						listaObjetos.add(origenFill);
					}
			
			}

		}
		
		Integer numeroOrigenes= 0;
		for (Mentions mention : lambdaQuery.getBody().getMentions()) {
			
			//System.out.println("mentionsEliminados " + mentionsEliminados);
			//System.out.println("mentionsEliminados " + mention.getId());
			
			if( !mentionsEliminados.contains( mention.getId().toString() ) ){				
			
				hayMentions = "I_RED";
				numeroOrigenes++;
				
				
				
			}
			
		}
		
		FillReportInvestigacionVO origenFill = new FillReportInvestigacionVO();
		origenFill.setListaMentions("•	" + "Entradas Identificadas ( " + numeroOrigenes + " )");
		listaObjetos.add(origenFill);

		// Mentions
//		if (lambdaQuery.getBody().getMentions().size() > 0) {
//
//			hayMentions = "I_RED";
//
//			FillReportInvestigacionVO origenFill = new FillReportInvestigacionVO();
//			origenFill.setListaMentions("Entradas Identificadas ( " + lambdaQuery.getBody().getMentions().size() + " )");
//			listaObjetos.add(origenFill);
//
//		}

		parameter.put("hayintcump", hayintcump); // I_GREEN,I_RED
		parameter.put("haynatcump", haynatcump); // I_GREEN,I_RED
		parameter.put("hayintPEP", hayintPEP); // I_GREEN,I_RED
		parameter.put("hayOthers", hayOthers); // I_GREEN,I_RED
		parameter.put("hayMentions", hayMentions); // I_GREEN,I_RED

		// Datos reporte Resultados
		OrigenClasificator reultados = new OrigenClasificator();
		reultados = clasifica(lambdaQuery.getBody());

		// Título 1
		FillReportInvestigacionResultadoVO titulo1 = new FillReportInvestigacionResultadoVO();
		if(reultados.getInternacional().size()>0) {
			titulo1.setG_titulo(null);
			titulo1.setG_origen(null);
			titulo1.setG_freeText(null);
			titulo1.setG_keyword(null);
			titulo1.setG_titulo("Listados de Cumplimiento Internacionales:");
			listaObjetosResultado.add(titulo1);
			listaObjetosResultado.addAll(reultados.getInternacional());
		}

		// Título 2
		FillReportInvestigacionResultadoVO titulo2 = new FillReportInvestigacionResultadoVO();
		if(reultados.getNacional().size()>0) {
			titulo2.setG_titulo("Listados de Cumplimiento Nacionales:");
			listaObjetosResultado.add(titulo2);
			listaObjetosResultado.addAll(reultados.getNacional());
		}
		// Título 3
		FillReportInvestigacionResultadoVO titulo3 = new FillReportInvestigacionResultadoVO();
		if(reultados.getPep().size()>0) {
			titulo3.setG_titulo("Personas Políticamente Expuestas:");
			listaObjetosResultado.add(titulo3);
			listaObjetosResultado.addAll(reultados.getPep());
		}
		// Título 4
		FillReportInvestigacionResultadoVO titulo4 = new FillReportInvestigacionResultadoVO();
		if(reultados.getOtros().size()>0) {
			titulo4.setG_titulo("Otras Bases de datos:");
			listaObjetosResultado.add(titulo4);
			listaObjetosResultado.addAll(reultados.getOtros());
		}
		// Título 5
		if(reultados.getMenciones().size()>0) {
			FillReportInvestigacionResultadoVO titulo5 = new FillReportInvestigacionResultadoVO();
			titulo5.setG_titulo("Menciones:");
			listaObjetosResultado.add(titulo5);
			listaObjetosResultado.addAll(reultados.getMenciones());
		}
		// Resumen
		reportTransferObjectPDF.setObjetosCount(listaObjetos);
		reportTransferObjectPDF.setObjetosResultado(listaObjetosResultado);
		reportTransferObjectPDF.setParameter(parameter);

		return reportTransferObjectPDF;

	}

	public OrigenClasificator clasifica(Body body) {

		OrigenClasificator resultadoFInal = new OrigenClasificator();

		// Vacios para no afectar el data_source
		ArrayList<FillReportInvestigacionResultadoVO> internacional = new ArrayList<FillReportInvestigacionResultadoVO>();

		ArrayList<FillReportInvestigacionResultadoVO> nacional = new ArrayList<FillReportInvestigacionResultadoVO>();
		//nacional.add(new FillReportInvestigacionResultadoVO());
		ArrayList<FillReportInvestigacionResultadoVO> pep = new ArrayList<FillReportInvestigacionResultadoVO>();
		//pep.add(new FillReportInvestigacionResultadoVO());
		ArrayList<FillReportInvestigacionResultadoVO> otros = new ArrayList<FillReportInvestigacionResultadoVO>();
		//otros.add(new FillReportInvestigacionResultadoVO());
		ArrayList<FillReportInvestigacionResultadoVO> menciones = new ArrayList<FillReportInvestigacionResultadoVO>();
		//menciones.add(new FillReportInvestigacionResultadoVO());
		
		String fromArrayOrigenes = body.getEliminadosOrigenes();		
		List<String> origenesEliminados = Arrays.asList( fromArrayOrigenes.split("\\s*,\\s*") );//Arrays.asList(fromArray.split("\\s*,\\s*"));
		String fromArrayMentions = body.getEliminadosMentions();
		List<String> mentionsEliminados = Arrays.asList( fromArrayMentions.split("\\s*,\\s*") );//Arrays.asList(fromArray.split("\\s*,\\s*"));

		for (Origen origen : body.getOrigen()) {

			if (!origenesEliminados.contains(origen.getId().toString())) {

				if ("intcump".equals(origen.getCategory())) {
					FillReportInvestigacionResultadoVO nuevo = new FillReportInvestigacionResultadoVO();
					nuevo.setG_origen(origen.getFuente());

					if (0 == origen.getIsJSON()) {
						String cadena3Espacios= "";
						cadena3Espacios = cadena3Espacios + origen.getFree_text();
						nuevo.setG_freeText(cadena3Espacios.replaceAll("\\n\\n\\n","\\n"));
						nuevo.setG_link(origen.getUrl() + "\n" );
					}
					else {
						nuevo.setRowsOK(createTable(origen.getTexto()));
						nuevo.setG_link(origen.getUrl() + "\n");
						nuevo.setG_origen2(origen.getFuente());
					}

					internacional.add(nuevo);
				}
				if ("natcump".equals(origen.getCategory())) {
					FillReportInvestigacionResultadoVO nuevo = new FillReportInvestigacionResultadoVO();
					nuevo.setG_origen(origen.getFuente());

					if (0 == origen.getIsJSON()){
						String cadena3Espacios= "";
						cadena3Espacios = cadena3Espacios + origen.getFree_text();
						nuevo.setG_freeText(cadena3Espacios.replaceAll("\\n\\n\\n","\\n"));
						nuevo.setG_link(origen.getUrl()+ "\n");
					}
					else{
						nuevo.setRowsOK(createTable(origen.getTexto()));
						nuevo.setG_link(origen.getUrl()+ "\n");
						nuevo.setG_origen2(origen.getFuente());
					}

					nacional.add(nuevo);
				}
				if ("PEP".equals(origen.getCategory())) {
					FillReportInvestigacionResultadoVO nuevo = new FillReportInvestigacionResultadoVO();
					nuevo.setG_origen(origen.getFuente());

					if (0 == origen.getIsJSON()) {
						String cadena3Espacios= "";
						cadena3Espacios = cadena3Espacios + origen.getFree_text();
						nuevo.setG_freeText(cadena3Espacios.replaceAll("\\n\\n\\n","\\n"));
						nuevo.setG_link(origen.getUrl()+ "\n");
					}
					else{
						nuevo.setRowsOK(createTable(origen.getTexto()));
						nuevo.setG_link(origen.getUrl()+ "\n");
						nuevo.setG_origen2(origen.getFuente());
					}

					pep.add(nuevo);
				}
				if ("others".equals(origen.getCategory())) {
					FillReportInvestigacionResultadoVO nuevo = new FillReportInvestigacionResultadoVO();
					nuevo.setG_origen(origen.getFuente());

					if (0 == origen.getIsJSON()){
						String cadena3Espacios= "";
						cadena3Espacios = cadena3Espacios + origen.getFree_text();
						nuevo.setG_freeText(cadena3Espacios.replaceAll("\\n\\n\\n","\\n"));
						nuevo.setG_link(origen.getUrl()+ "\n");
					}
					else{
						nuevo.setRowsOK(createTable(origen.getTexto()));
						nuevo.setG_link(origen.getUrl()+ "\n");
						nuevo.setG_origen2(origen.getFuente());
					}

					otros.add(nuevo);
				}

			}

		}

		for (Mentions origen : body.getMentions()) {
			
			if( !mentionsEliminados.contains( origen.getId().toString() ) ){
				
			FillReportInvestigacionResultadoVO nuevo = new FillReportInvestigacionResultadoVO();
			nuevo.setG_origen(origen.getTitle());
			nuevo.setG_freeText(origen.getDescription());
			nuevo.setG_link(origen.getLink());
			nuevo.setG_keyword(origen.getKeyword());
			nuevo.setG_mentionImage(origen.getEngine());
			menciones.add(nuevo);
			
			}

		}

		if (internacional.size() == 0) {
			//internacional.add(new FillReportInvestigacionResultadoVO());
		}
		if (nacional.size() == 0) {
			//nacional.add(new FillReportInvestigacionResultadoVO());
		}
		if (pep.size() == 0) {
			//pep.add(new FillReportInvestigacionResultadoVO());
		}
		if (otros.size() == 0) {
			//otros.add(new FillReportInvestigacionResultadoVO());
		}
		if (menciones.size() == 0) {
			//menciones.add(new FillReportInvestigacionResultadoVO());
		}
		System.out.println(  "Tamaño internacional" + internacional.size());
		System.out.println(  "Tamaño nacional " + nacional.size());
		System.out.println(  "Tamaño pep" + pep.size());
		System.out.println(  "Tamaño otros " + otros.size());
		System.out.println(  "Tamaño menciones " + menciones.size());
		
		resultadoFInal.setInternacional(internacional);
		resultadoFInal.setNacional(nacional);
		resultadoFInal.setPep(pep);
		resultadoFInal.setOtros(otros);
		resultadoFInal.setMenciones(menciones);

		return resultadoFInal;
	}

	public ArrayList<Tabla> createTable(Map<String, String> texto) {
		
		ArrayList<Tabla> returning = new ArrayList<Tabla>();

		for (Map.Entry<String, String> entry : texto.entrySet()) {
			
			Tabla row = new Tabla( entry.getKey() , entry.getValue());
			returning.add(row);

		}

		//System.out.println("Creating table " + texto);

		return returning;
	}
	
	public Long demoJob() throws Exception {
		return null;

	}
	
	public void eliminaRegistrioById(RiesgoRequest request) {
		// TODO Auto-generated method stub
		dao.eliminaRegistrioById(request);
	}

	@Override
	public List<Masiva> getMasivas(MasivaRequest request) {
		// TODO Auto-generated method stub
		return dao.getMasivas(request);
	}
	
	@Override
	public void setRiesgoById(RiesgoRequest request) {
		 dao.setRiesgoInicialById(request);
		
	}

	@Override
	public void updateRiesgoFinalById(RiesgoRequest request) {
		 dao.updateRiesgoFinalById(request);
		
	}

	@Override
	public void updateEliminadosOrigen(OrigenesBorradoRequest request) {
		dao.updateEliminadosOrigen(request);
		
	}

	@Override
	public void updateEliminadosMention(OrigenesBorradoRequest request) {
		dao.updateEliminadosMention(request);
		
	}

	@Override
	public String getNombreClientePorUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
		return dao.getNombreClientePorUsuario(idUsuario);
	}

	@Override
	public List<InvestigacionRequest> getMasivasToAWS(Integer limit) {
		// TODO Auto-generated method stub
		return dao.getMasivasToAWS(limit);
	}

	@Override
	public void setJsonById(Long idInvestigacion, String json) {
		dao.setJsonById(idInvestigacion,json);
		
	}

	@Override
	public void finishInvestigacionTask(Long idInvestigacion) {
		dao.finishInvestigacionTask(idInvestigacion);
		
	}
	
	@Override
	public void errorInvestigacionTask(Long idInvestigacion) {
		dao.errorInvestigacionTask(idInvestigacion);
		
	}

	@Override
	public void updateCampanias() {
		dao.updateCampanias();
		
	}

}
