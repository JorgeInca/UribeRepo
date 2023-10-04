package mx.com.rmsh.horusControl.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportTransferObjectPDF {

	Map<String, Object> parameter = new HashMap<String, Object>();
	ArrayList<FillReportInvestigacionVO> objetosCount = new ArrayList<FillReportInvestigacionVO>();
	ArrayList<FillReportInvestigacionResultadoVO> objetosResultado = new ArrayList<FillReportInvestigacionResultadoVO>();

	public Map<String, Object> getParameter() {
		return parameter;
	}

	public void setParameter(Map<String, Object> parameter) {
		this.parameter = parameter;
	}

	public ArrayList<FillReportInvestigacionVO> getObjetosCount() {
		return objetosCount;
	}

	public void setObjetosCount(ArrayList<FillReportInvestigacionVO> objetosCount) {
		this.objetosCount = objetosCount;
	}

	public ArrayList<FillReportInvestigacionResultadoVO> getObjetosResultado() {
		return objetosResultado;
	}

	public void setObjetosResultado(ArrayList<FillReportInvestigacionResultadoVO> objetosResultado) {
		this.objetosResultado = objetosResultado;
	}

	@Override
	public String toString() {
		return "ReportTransferObjectPDF [parameter=" + parameter + ", objetosCount=" + objetosCount
				+ ", objetosResultado=" + objetosResultado + "]";
	}

}