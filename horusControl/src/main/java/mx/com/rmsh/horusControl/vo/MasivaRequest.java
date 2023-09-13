package mx.com.rmsh.horusControl.vo;

import org.springframework.web.multipart.MultipartFile;

public class MasivaRequest {
	
	MultipartFile file;
	Long idUsuario;
	String nombreCampaña;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreCampaña() {
		return nombreCampaña;
	}
	public void setNombreCampaña(String nombreCampaña) {
		this.nombreCampaña = nombreCampaña;
	}
	@Override
	public String toString() {
		return "MasivaRequest [file=" + file + ", idUsuario=" + idUsuario + ", nombreCampaña=" + nombreCampaña + "]";
	}
	
	

}
