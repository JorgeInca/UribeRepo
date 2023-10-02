package mx.com.rmsh.horusControl.vo;

public class FillReportInvestigacionVO {
	
	public String listaCumIn = "";
	public String   listaCumNac= "";
	public String listaPEP = "";
	public String   listaOthers= "";
	public String listaMentions= "";
	
	public String getListaCumIn() {
		return listaCumIn;
	}
	public void setListaCumIn(String listaCumIn) {
		this.listaCumIn = listaCumIn;
	}
	public String getListaCumNac() {
		return listaCumNac;
	}
	public void setListaCumNac(String listaCumNac) {
		this.listaCumNac = listaCumNac;
	}
	public String getListaPEP() {
		return listaPEP;
	}
	public void setListaPEP(String listaPEP) {
		this.listaPEP = listaPEP;
	}
	public String getListaOthers() {
		return listaOthers;
	}
	public void setListaOthers(String listaOthers) {
		this.listaOthers = listaOthers;
	}
	public String getListaMentions() {
		return listaMentions;
	}
	public void setListaMentions(String listaMentions) {
		this.listaMentions = listaMentions;
	}
	
	@Override
	public String toString() {
		return "FillReportInvestigacionVO [listaCumIn=" + listaCumIn + ", listaCumNac=" + listaCumNac + ", listaPEP="
				+ listaPEP + ", listaOthers=" + listaOthers + ", listaMentions=" + listaMentions + "]";
	}	
	

}
