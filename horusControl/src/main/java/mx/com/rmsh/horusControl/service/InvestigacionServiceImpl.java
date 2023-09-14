package mx.com.rmsh.horusControl.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.rmsh.horusControl.dao.InvestigacionDao;
import mx.com.rmsh.horusControl.dao.SecurityDao;
import mx.com.rmsh.horusControl.enums.EstatusInvestigacion;
import mx.com.rmsh.horusControl.enums.Pais;
import mx.com.rmsh.horusControl.utils.TextoHandler;
import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.MasivaRequest;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;

@Service
public class InvestigacionServiceImpl implements InvestigacionService {

	@Autowired
	InvestigacionDao dao;

	@Autowired
	SecurityDao daoUser;

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
		// TODO Auto-generated method stub
		return daoUser.guardaUsuario(user);
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

}
