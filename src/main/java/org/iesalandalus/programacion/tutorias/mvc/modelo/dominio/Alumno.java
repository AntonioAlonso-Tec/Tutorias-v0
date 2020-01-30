package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.util.Objects;

public class Alumno {
	private static final String ER_NOMBRE = "([A-Zu00C0-\\u017F]{1}[a-zu00C0-\\u017F]+ )+([A-Zu00C0-\\u017F]{1}[a-zu00C0-\\u017F]+)";
	private static final String PREFIJO_EXPEDIENTE="SP_";
	private static final String ER_CORREO = "([A-Za-z0-9]+[.]?)([A-Za-z0-9]+)[@]([A-Za-z0-9]+[.])([a-z]{2,3})";
	private static int ultimoIdentificador = 0;
	private String nombre;
	private String correo;
	private String expediente; 

	public Alumno(String nombre, String correo) {
		setNombre(formateaNombre(nombre));
		setCorreo(correo);
		ultimoIdentificador++;
		setExpediente(expediente);
	}

	public Alumno(Alumno alumno) {
		if (alumno==null) {
			throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
		}else {
			setNombre(alumno.nombre);
			setCorreo(alumno.correo);
			setExpediente(alumno.expediente);
		}

	}
	
	public static Alumno getAlumnoFicticio(String correo) {
		String nombre="Perico palotes";
		Alumno alumno=new Alumno(nombre,correo);
		
		if (!correo.equals(alumno.correo)) {
			throw new IllegalArgumentException("ERROR: Este alumno no existe");
		}else {
			return alumno;
		}
		
		
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if(nombre==null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}else if(nombre.trim().equals("")||!nombre.matches(ER_NOMBRE)){
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}else {
			this.nombre=nombre;
		}
	}
	
	private String formateaNombre(String nombre) {
		String nombreFormateado = "";
		String nombreConEspacios = "";
		int indice = 0;

		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		} else if(nombre.trim().equals("")){
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		} else {
			StringBuilder formato = new StringBuilder(nombre);
			for (indice = 0; indice < formato.length(); indice++) {
				if (Character.isUpperCase(formato.charAt(indice))) {
					formato.setCharAt(indice, Character.toLowerCase(formato.charAt(indice)));
				}
			}

			for (int i = 1; i < formato.length(); i++) {
				if (Character.isLowerCase(formato.charAt(i)) && formato.charAt(i) > 1 && formato.charAt(i - 1) == ' ') {
					formato.setCharAt(i, Character.toUpperCase(formato.charAt(i)));
				} else if (Character.isUpperCase(formato.charAt(i)) && formato.charAt(i) < 1) {
					Character.toUpperCase(formato.charAt(i));
				}
			}
			if (Character.isLowerCase(formato.charAt(0))) {
				formato.setCharAt(0, Character.toUpperCase(formato.charAt(0)));
			}
			nombreConEspacios = formato.toString();
			nombreFormateado = nombreConEspacios.trim().replaceAll("\\s{2,}", " ");
			return nombreFormateado;
		}
	}

	/*private String formateaNombre(String nombre) {
		String nombreFormateado;
		String nombreSinEspacios;
		String nombreCasiFormateado = null;
		int indice = 0;
		
			//Elimina los espacios innecesarios
			nombreSinEspacios = nombre.trim().replaceAll(" {2,}", " ");
			
			//Divide el nombre en partes
			String[] nombreDividido=nombreSinEspacios.split(" ");
			
			
			StringBuilder[] nombreDivididoSB=new StringBuilder[nombreDividido.length];
			
			for(int i=0;i<nombreDivididoSB.length;i++) {
				nombreDivididoSB[i]=new StringBuilder(nombreDividido[i]);
			}
			
			//Formatear todo el nombre en minuscula
			for (int i=0;i<nombreDivididoSB.length;i++) {
				for (int j=0;j<nombreDivididoSB[i].length();j++) {
					if(Character.isUpperCase(nombreDivididoSB[i].charAt(j))) {
						Character.toLowerCase(nombreDivididoSB[i].charAt(j));
					}
				}
			}
			
			//Letra inicial de cada palabra en mayuscula
			for (int i=0;i<nombreDivididoSB.length;i++) {
					if (Character.isLowerCase(nombreDivididoSB[i].charAt(0))){
						Character.toUpperCase(nombreDivididoSB[i].charAt(0));
				}
			}
			
			for (int i=0;i<nombreDivididoSB.length;i++) {
				nombreCasiFormateado=nombreDivididoSB[i].toString()+" ";
			}
			
			System.out.println(nombreDivididoSB.toString());
			
			nombreFormateado=nombreCasiFormateado.trim();
		
			return nombreFormateado;
	}*/

	public String getCorreo() {
		return correo;
	}

	private void setCorreo(String correo) {
		if(correo==null) {
			throw new NullPointerException("ERROR: El correo no puede ser nulo.");
		}else if(correo.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El formato del correo no es válido.");
		}
			else if(!correo.matches(ER_CORREO)) {
		
			throw new IllegalArgumentException("ERROR: El formato del correo no es válido.");
		}else {
			this.correo = correo;
		}
	}

	public String getExpediente() {
		return expediente;
	}

	private void setExpediente(String expediente) {
		if(ultimoIdentificador<1) {
			throw new IllegalArgumentException("ERROR: No puede existir menos de 1 alumno");
		}else {
		
		this.expediente = PREFIJO_EXPEDIENTE+getIniciales()+"_"+ultimoIdentificador;;
		}
	}
	
	private String getIniciales() {
		nombre=formateaNombre(nombre);
		String iniciales = "";
		char inicial;

		if (nombre==null) {
			System.out.println("No hay nombre del que coger las iniciales");
		}
		
		for (int i = 0; i < nombre.length(); i++) {
			if (Character.isUpperCase(nombre.charAt(i))) {
				inicial = nombre.charAt(i);
				iniciales += inicial;
			}
		}
		return iniciales;
	}
	
	private static void incrementaUltimoModificador() {
		ultimoIdentificador++;
	}

	@Override
	public int hashCode() {
		return Objects.hash(correo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Alumno)) {
			return false;
		}
		Alumno other = (Alumno) obj;
		return Objects.equals(correo, other.correo);
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + " ("+getIniciales()+")" + ", correo=" + correo + ", expediente=" + expediente;
	}

}

