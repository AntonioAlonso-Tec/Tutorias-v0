package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public class Sesiones {
	private int capacidad;
	private int tamano;
	private Sesion[] coleccionSesiones;
	
	public Sesiones(int capacidad) {
		if (capacidad<1) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}else {
			this.capacidad=capacidad;
			tamano=0;
			coleccionSesiones=new Sesion[capacidad];
		}
	}
	
	public Sesion[] get() {
		
		return copiaProfundaSesiones();
		}
	
	private Sesion[] copiaProfundaSesiones() {
		Sesion[] copiaSesiones=new Sesion[capacidad];
		int indice=0;
		
		for(indice=0;!tamanoSuperado(indice);indice++) {
			copiaSesiones[indice]=new Sesion(coleccionSesiones[indice]);
		}
			
		return copiaSesiones;
		
	}
	
	public Sesion[] get(Tutoria tutoria) {
		Sesion[] copiaPorTutoria=new Sesion[capacidad];
		
		for(int i=0;!tamanoSuperado(i);i++) {
			if(coleccionSesiones[i].getTutoria().equals(tutoria)) {
				copiaPorTutoria[i]=new Sesion(coleccionSesiones[i]);
			}
		}
		return copiaPorTutoria;
	}

	
	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}
	public void insertar(Sesion sesion) throws OperationNotSupportedException {
		if (sesion==null) {
			throw new NullPointerException("ERROR: No se puede insertar una sesión nula.");
		}
		
		if (capacidadSuperada(buscarIndice(sesion))) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más sesiones.");
		}else if (!tamanoSuperado(buscarIndice(sesion))) {
			throw new OperationNotSupportedException("ERROR: Ya existe una sesión con esa fecha.");
		}else {
			coleccionSesiones[buscarIndice(sesion)]=new Sesion(sesion);
			tamano++;
		}
	}
	private int buscarIndice(Sesion sesion) {
		int indiceSesion=0;
		boolean indiceEncontrado = false;

		while (!tamanoSuperado(indiceSesion) && !indiceEncontrado) {
			if (coleccionSesiones[indiceSesion].equals(sesion)) {
				indiceEncontrado = true;
			} else {
				indiceSesion++;
			}

		}
		return indiceSesion;
	}
	public boolean tamanoSuperado(int indice) {
		return indice>=tamano;
	}
	
	public boolean capacidadSuperada(int indice) {
		return indice>=capacidad;
	}
public Sesion buscar(Sesion sesion) {
		
		if(sesion==null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una sesión nula.");
		}
		
		if(tamanoSuperado(buscarIndice(sesion))){
			return null;
		}else {
			return new Sesion(coleccionSesiones[buscarIndice(sesion)]);
		}
		
		
	}
	
	public void borrar(Sesion sesion) throws OperationNotSupportedException {
		int indice=buscarIndice(sesion);
		
		if(sesion==null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una sesión nula.");
		}
		
		if(tamanoSuperado(indice)){
			throw new OperationNotSupportedException("ERROR: No existe ninguna sesión con esa fecha.");
		}else {
			desplazarUnaPosicionHaciaIzquierda(indice);
			}
			
		}
	public void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int indiceArray;
		for(indiceArray=indice;!tamanoSuperado(indiceArray);indiceArray++) {
			coleccionSesiones[indiceArray]=coleccionSesiones[indiceArray+1];
		}
		coleccionSesiones[indiceArray]=null;
		tamano--;
	}
}
