package mx.com.rmsh.horusControl.vo;

public class FillReportInvestigacionResultadoVO {

	public String g_titulo = "";
	public String g_origen = "";
	public String g_freeText = "";
	public String g_keyword = "";
	public String g_table = "";
	public String g_mentionImage = "w";

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

	@Override
	public String toString() {
		return "FillReportInvestigacionResultadoVO [g_titulo=" + g_titulo + ", g_origen=" + g_origen + ", g_freeText="
				+ g_freeText + ", g_keyword=" + g_keyword + ", g_table=" + g_table + ", g_mentionImage="
				+ g_mentionImage + "]";
	}

}