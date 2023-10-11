package mx.com.rmsh.horusControl.service;

import java.util.List;
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

import mx.com.rmsh.horusControl.vo.Empresas;


@Service
public class CatalagosServiceImp implements CatalagosService {
	
	@Autowired
	InvestigacionDao dao;

	@Autowired
	SecurityDao daoUser;

	@Autowired
	AWSService awsService;

	@Override
	public List<Empresas> getEmpresa(Empresas request) {
		return daoUser.getEmpresa(request);
	}



	

}
