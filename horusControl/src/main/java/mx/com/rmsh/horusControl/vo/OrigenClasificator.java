package mx.com.rmsh.horusControl.vo;

import java.util.ArrayList;

public class OrigenClasificator {

	ArrayList<FillReportInvestigacionResultadoVO> internacional = new ArrayList<FillReportInvestigacionResultadoVO>();
	ArrayList<FillReportInvestigacionResultadoVO> nacional = new ArrayList<FillReportInvestigacionResultadoVO>();
	ArrayList<FillReportInvestigacionResultadoVO> pep = new ArrayList<FillReportInvestigacionResultadoVO>();
	ArrayList<FillReportInvestigacionResultadoVO> otros = new ArrayList<FillReportInvestigacionResultadoVO>();
	ArrayList<FillReportInvestigacionResultadoVO> menciones = new ArrayList<FillReportInvestigacionResultadoVO>();

	public ArrayList<FillReportInvestigacionResultadoVO> getInternacional() {
		return internacional;
	}

	public void setInternacional(ArrayList<FillReportInvestigacionResultadoVO> internacional) {
		this.internacional = internacional;
	}

	public ArrayList<FillReportInvestigacionResultadoVO> getNacional() {
		return nacional;
	}

	public void setNacional(ArrayList<FillReportInvestigacionResultadoVO> nacional) {
		this.nacional = nacional;
	}

	public ArrayList<FillReportInvestigacionResultadoVO> getPep() {
		return pep;
	}

	public void setPep(ArrayList<FillReportInvestigacionResultadoVO> pep) {
		this.pep = pep;
	}

	public ArrayList<FillReportInvestigacionResultadoVO> getOtros() {
		return otros;
	}

	public void setOtros(ArrayList<FillReportInvestigacionResultadoVO> otros) {
		this.otros = otros;
	}

	public ArrayList<FillReportInvestigacionResultadoVO> getMenciones() {
		return menciones;
	}

	public void setMenciones(ArrayList<FillReportInvestigacionResultadoVO> menciones) {
		this.menciones = menciones;
	}

	@Override
	public String toString() {
		return "OrigenClasificator [internacional=" + internacional + ", nacional=" + nacional + ", pep=" + pep
				+ ", otros=" + otros + ", menciones=" + menciones + "]";
	}

}
