package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;


public class Citas {
	private int capacidad;
	private int tamano;
	private Cita[] coleccionCitas;
	
	public Citas(int capacidad) {
		if (capacidad<1) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}else {
			this.capacidad=capacidad;
			tamano=0;
			coleccionCitas=new Cita[capacidad];
		}
	}
	
	public Cita[] get() {
		
		return copiaProfundaCitas();
		}
	
	private Cita[] copiaProfundaCitas() {
		Cita[] copiaCitas=new Cita[capacidad];
		int indice=0;
		
		for(indice=0;!tamanoSuperado(indice);indice++) {
			copiaCitas[indice]=new Cita(coleccionCitas[indice]);
		}
			
		return copiaCitas;
		
	}
	
	public Cita[] get(Alumno alumno) {
		Cita[] copiaPorAlumno=new Cita[capacidad];
		int indiceCopia=0;
		
		if(alumno==null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}else {
		for(int i=0;!tamanoSuperado(i);i++) {
			if(coleccionCitas[i].getAlumno().equals(alumno)) {
				copiaPorAlumno[indiceCopia]=new Cita(coleccionCitas[i]);
				indiceCopia++;
			}
		}
		return copiaPorAlumno;
		}
	}
	
	public Cita[] get(Sesion sesion) {
		Cita[] copiaPorSesion=new Cita[capacidad];
		int indiceCopia=0;
		
		if(sesion==null) {
			throw new NullPointerException("ERROR: La sesión no puede ser nula.");
		}else {
		for(int i=0;!tamanoSuperado(i);i++) {
			if(coleccionCitas[i].getSesion().equals(sesion)) {
				copiaPorSesion[indiceCopia]=new Cita(coleccionCitas[i]);
				indiceCopia++;
			}
		}
		return copiaPorSesion;
		}
	}

	
	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}
	public void insertar(Cita cita) throws OperationNotSupportedException {
		if (cita==null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}
		
		if (capacidadSuperada(buscarIndice(cita))) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");
		}else if (!tamanoSuperado(buscarIndice(cita))) {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita con esa hora.");
		}else {
			coleccionCitas[buscarIndice(cita)]=new Cita(cita);
			tamano++;
		}
	}
	private int buscarIndice(Cita cita) {
		int indiceCita=0;
		boolean indiceEncontrado = false;

		while (!tamanoSuperado(indiceCita) && !indiceEncontrado) {
			if (coleccionCitas[indiceCita].equals(cita)) {
				indiceEncontrado = true;
			} else {
				indiceCita++;
			}

		}
		return indiceCita;
	}
	public boolean tamanoSuperado(int indice) {
		return indice>=tamano;
	}
	
	public boolean capacidadSuperada(int indice) {
		return indice>=capacidad;
	}
public Cita buscar(Cita cita) {
		
		if(cita==null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una cita nula.");
		}
		
		if(tamanoSuperado(buscarIndice(cita))){
			return null;
		}else {
			return new Cita(coleccionCitas[buscarIndice(cita)]);
		}
		
		
	}
	
	public void borrar(Cita cita) throws OperationNotSupportedException {
		int indice=buscarIndice(cita);
		
		if(cita==null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}
		
		if(tamanoSuperado(indice)){
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita con esa hora.");
		}else {
			desplazarUnaPosicionHaciaIzquierda(indice);
			}
			
		}
	public void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int indiceArray;
		for(indiceArray=indice;!tamanoSuperado(indiceArray);indiceArray++) {
			coleccionCitas[indiceArray]=coleccionCitas[indiceArray+1];
		}
		coleccionCitas[indiceArray]=null;
		tamano--;
	}
}
