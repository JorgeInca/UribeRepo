package mx.com.rmsh.horusControl.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SensorRow {

	private Long id_dac;
	private Float value;
	private Float value2;
	private Float value3;
	private Date date;
	private String dateString;

	public SensorRow() {
		super();
	}

	public SensorRow(Long id_dac, Float value, Date date) {
		this.setId_dac(id_dac);
		this.setValue(value);
		this.setDate(date);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String strDate = formatter.format(date);

		this.setDateString(strDate);

	}

	public SensorRow(Long id_dac, Float value, Float value2, Float value3, Date date) {
		super();
		this.id_dac = id_dac;
		this.value = value;
		this.value2 = value2;
		this.value3 = value3;
		this.date = date;

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		String strDate = formatter.format(date);

		this.setDateString(strDate);

	}

	public Long getId_dac() {
		return id_dac;
	}

	public void setId_dac(Long id_dac) {
		this.id_dac = id_dac;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Float getValue2() {
		return value2;
	}

	public void setValue2(Float value2) {
		this.value2 = value2;
	}

	public Float getValue3() {
		return value3;
	}

	public void setValue3(Float value3) {
		this.value3 = value3;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	@Override
	public String toString() {
		return "SensorRow [id_dac=" + id_dac + ", value=" + value + ", value2=" + value2 + ", value3=" + value3
				+ ", date=" + date + ", dateString=" + dateString + "]";
	}

}
