package mx.com.rmsh.horusControl.utils;

public class TextoHandler {
	
	 public static String sumaNombres( String primerNombre , String segundoNombre ) {
		 
		 
		 if( !"".equals(primerNombre) && !"".equals(segundoNombre) ) {
			 return ( primerNombre + " " +  segundoNombre);
		 }
		 
		 if( "".equals(primerNombre) && "".equals(segundoNombre) ) {
			 return "";
		 }
		 
		 if( "".equals(primerNombre) && !"".equals(segundoNombre) ) {
			 return segundoNombre;
		 }
		 
		 if( !"".equals(primerNombre) && "".equals(segundoNombre) ) {
			 return primerNombre;
		 }
		 
		 
		 return "";
		  
	 }

}
