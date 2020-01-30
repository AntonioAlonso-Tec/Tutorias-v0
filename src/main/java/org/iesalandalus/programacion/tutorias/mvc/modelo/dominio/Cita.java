package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cita {
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	private LocalTime hora;
	private Sesion sesion;
	private Alumno alumno;
	
	public Cita(Alumno alumno,Sesion sesion,LocalTime hora) {
		setAlumno(alumno);
		setSesion(sesion);
		setHora(hora);
	}
	
	public Cita (Cita cita) {
		if(cita==null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula");
		}else {
			setAlumno(cita.getAlumno());
			setSesion(cita.getSesion());
			setHora(cita.getHora());
		}
		
	}

	public LocalTime getHora() {
		return hora;
	}

	private void setHora(LocalTime hora) {
		if(hora==null) {
			throw new NullPointerException("ERROR: La hora no puede ser nula");
		}else {
			this.hora = hora;
		}
	}

	public Sesion getSesion() {
		return sesion;
	}

	private void setSesion(Sesion sesion) {
		if(sesion==null) {
			throw new NullPointerException("ERROR: La sesión no puede ser nula");
		}else{
			this.sesion = new Sesion(sesion);
		}
	}

	public Alumno getAlumno() {
		return alumno;
	}

	private void setAlumno(Alumno alumno) {
		if(alumno==null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo");
		}else {
			this.alumno = new Alumno(alumno);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(alumno, hora, sesion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Cita)) {
			return false;
		}
		Cita other = (Cita) obj;
		return Objects.equals(alumno, other.alumno) && Objects.equals(hora, other.hora)
				&& Objects.equals(sesion, other.sesion);
	}

	@Override
	public String toString() {
		return String.format("alumno=%s, sesion=%s, hora=%s", getAlumno(),getSesion(),getHora().format(FORMATO_HORA));
	}
	
	
}
