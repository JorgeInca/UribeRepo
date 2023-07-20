package mx.com.rmsh.horusControl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.com.rmsh.horusControl.vo.SensorRequest;
import mx.com.rmsh.horusControl.vo.SensorRow;

@Repository
public class SensorDaoImpl implements SensorDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("deprecation")
	@Override
	public List<SensorRow> getSensorArgosTemp(SensorRequest request) {
		
		 return jdbcTemplate.query(
	                "select * from MEASURE_ARGOS_TEMP ORDER BY DATE_M DESC LIMIT 1",
	               // new Object[]{"%" + name + "%", price},
	                (rs, rowNum) ->
	                        new SensorRow(
	                                rs.getLong("id_measure"),
	                                rs.getFloat("data_m"),
	                                rs.getTimestamp("date_m")
	                        )
	        );
	}
	 
	@SuppressWarnings("deprecation")
	@Override
	public List<SensorRow> getSensorArgosHum(SensorRequest request) {
		System.out.println("*********getSensorArgosHum [DAO] ");
		 return jdbcTemplate.query(
	                "SELECT * FROM MEASURE_ARGOS_HUM ORDER BY DATE_M DESC LIMIT 20",
	               // new Object[]{"%" + name + "%", price},
	                (rs, rowNum) ->
	                        new SensorRow(
	                                rs.getLong("id_measure"),
	                                rs.getFloat("data_m"),
	                                rs.getTimestamp("date_m")
	                        )
	        );
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<SensorRow> getSensorOpcomLSO(SensorRequest request) {
		
		System.out.println("*********getSensorOpcomLSO [DAO] ");
		
		 return jdbcTemplate.query(
	                "SELECT * FROM MEASURE_OPCOM ORDER BY DATE_M DESC LIMIT 20",
	               // new Object[]{"%" + name + "%", price},
	                (rs, rowNum) ->
	                        new SensorRow(
	                                rs.getLong("id_measure"),
	                                rs.getFloat("data_w"),
	                                rs.getFloat("data_x"),
	                                rs.getFloat("data_y"),
	                                rs.getTimestamp("date_m")
	                        )
	        );
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<SensorRow> getSensorBallufPresion(SensorRequest request) {
		
		 return jdbcTemplate.query(
	                "SELECT * FROM MEASURE_BALLUF ORDER BY DATE_M DESC LIMIT 1",
	               // new Object[]{"%" + name + "%", price},
	                (rs, rowNum) ->
	                        new SensorRow(
	                                rs.getLong("id_measure"),
	                                rs.getFloat("data_m"),
	                                rs.getTimestamp("date_m")
	                        )
	        );
	}

	

}
