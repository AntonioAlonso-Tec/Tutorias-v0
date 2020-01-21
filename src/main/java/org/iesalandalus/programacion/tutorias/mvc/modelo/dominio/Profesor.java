package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profesor {
	private static final String ER_NOMBRE = "([A-Zu00C0-\\u017F]{1}[a-zu00C0-\\u017F]+ )+([A-Zu00C0-\\u017F]{1}[a-zu00C0-\\u017F]+)";
	private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private static final String ER_CORREO = "([A-Za-z0-9]+[.]?)([A-Za-z0-9]+)[@]([A-Za-z0-9]+[.])([a-z]{2,3})";
	private String nombre;
	private String dni;
	private String correo;
	
	public Profesor(String nombre,String dni,String correo) {
		setNombre(formateaNombre(nombre));
		setDni(dni);
		setCorreo(correo);
	}
	
	public Profesor(Profesor profesor) {
		if (profesor==null) {
			throw new NullPointerException("ERROR: No es posible copiar un profesor nulo.");
		}else {
			setNombre(profesor.nombre);
			setDni(profesor.dni);
			setCorreo(profesor.correo);
		}
	}

	
	public static Profesor getProfesorFicticio(String dni) {
		String nombre="Perico palotes";
		String correo=("capullazo@dinomail.com");
		Profesor profesor=new Profesor(nombre,dni,correo);
		
		if(dni==null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo");
		}else if (dni.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El DNI no puede estar vacío");
		}else if (!dni.equals(profesor.dni)) {
			throw new IllegalArgumentException("ERROR: Este profesor no existe");
		}else {
			return profesor;
		}
		
		
	}
	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if(nombre==null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}else if(nombre.equals("")||!nombre.matches(ER_NOMBRE)){
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
/*
	private String formateaNombre(String nombre) {
		String nombreFormateado;
		String nombreSinEspacios;
		String nombreCasiFormateado = null;
		int indice = 0;
		
			//Elimina los espacios innecesarios
			nombreSinEspacios = nombre.trim().replaceAll("\\s{2,}", "\\s");
			
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
				nombreCasiFormateado=nombreDivididoSB[i].toString();
			}
			
			System.out.println(nombreDivididoSB.toString());
			
			nombreFormateado=nombreCasiFormateado.trim();
		
			return nombreFormateado;
	}
	*/
	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null){
		throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}else if(dni.trim().equals("")||!dni.matches(ER_DNI)){
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}else {
			this.dni = comprobarLetraDni(dni);
		}
	}

	private String comprobarLetraDni(String dni) {
		int numeroDni;
		char letraDni;
		char[] arrayLetras = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' };
		Pattern patron;
		Matcher comparar;
		if (dni == null||dni.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El DNI no puede ser nulo.");
		} else {
			patron = Pattern.compile(ER_DNI);
			if (!dni.matches(ER_DNI)) {
				throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
			} else {
				comparar = patron.matcher(dni);
				comparar.matches();
				numeroDni = Integer.parseInt(comparar.group(1));
				letraDni = comparar.group(2).charAt(0);
			}
			if (letraDni != arrayLetras[numeroDni % 23]) {
				throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
			} else {
				return dni;
			}
		}
	}
	
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

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Profesor)) {
			return false;
		}
		Profesor other = (Profesor) obj;
		return Objects.equals(dni, other.dni);
	}

	private String getIniciales() {
		String iniciales = "";
		char inicial;

		for (int i = 0; i < formateaNombre(nombre).length(); i++) {
			if (Character.isUpperCase(formateaNombre(nombre).charAt(i))) {
				inicial = formateaNombre(nombre).charAt(i);
				iniciales += inicial;
			}
		}
		return iniciales;
	}
	
	@Override
	public String toString() {
		return "nombre=" + nombre + " ("+getIniciales()+")"+ ", DNI=" + dni + ", correo=" + correo;
	}
	
	
	
}
