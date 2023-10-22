package mx.com.rmsh.horusControl.vo;

import java.util.ArrayList;

public class FillReportInvestigacionResultadoVO {

	public String g_titulo = "";
	public String g_origen = "";
	public String g_origen2 = null;
	public String g_freeText = "";
	public String g_keyword = "";
	public String g_table = "";
	public String g_mentionImage = "";
	public String g_link = "";
	public ArrayList<Tabla> rowsOK = null;

	public String getG_titulo() {
		return g_titulo;
	}

	public void setG_titulo(String g_titulo) {
		this.g_titulo = g_titulo;
	}

	public String getG_origen() {
		return g_origen;
	}

	public void setG_origen(String g_origen) {
		this.g_origen = g_origen;
	}

	public String getG_freeText() {
		return g_freeText;
	}

	public void setG_freeText(String g_freeText) {
		this.g_freeText = g_freeText;
	}

	public String getG_keyword() {
		return g_keyword;
	}

	public void setG_keyword(String g_keyword) {
		this.g_keyword = g_keyword;
	}

	public String getG_table() {
		return g_table;
	}

	public void setG_table(String g_table) {
		this.g_table = g_table;
	}

	public String getG_mentionImage() {
		return g_mentionImage;
	}

	public void setG_mentionImage(String g_mentionImage) {
		this.g_mentionImage = g_mentionImage;
	}

	public ArrayList<Tabla> getRowsOK() {
		return rowsOK;
	}

	public void setRowsOK(ArrayList<Tabla> rowsOK) {
		this.rowsOK = rowsOK;
	}

	public String getG_link() {
		return g_link;
	}

	public void setG_link(String g_link) {
		this.g_link = g_link;
	}

	public String getG_origen2() {
		return g_origen2;
	}

	public void setG_origen2(String g_origen2) {
		this.g_origen2 = g_origen2;
	}

	@Override
	public String toString() {
		return "FillReportInvestigacionResultadoVO [g_titulo=" + g_titulo + ", g_origen=" + g_origen + ", g_origen2="
				+ g_origen2 + ", g_freeText=" + g_freeText + ", g_keyword=" + g_keyword + ", g_table=" + g_table
				+ ", g_mentionImage=" + g_mentionImage + ", g_link=" + g_link + ", rowsOK=" + rowsOK + "]";
	}

}