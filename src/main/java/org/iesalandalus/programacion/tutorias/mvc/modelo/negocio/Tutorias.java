package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public class Tutorias {
	private int capacidad;
	private int tamano;
	private Tutoria[] coleccionTutorias;
	
	public Tutorias(int capacidad) {
		if (capacidad<1) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}else {
			this.capacidad=capacidad;
			tamano=0;
			coleccionTutorias=new Tutoria[capacidad];
		}
	}
	
	public Tutoria[] get() {
		
		return copiaProfundaTutorias();
		}
	
	private Tutoria[] copiaProfundaTutorias() {
		Tutoria[] copiaTutorias=new Tutoria[capacidad];
		int indice=0;
		
		for(indice=0;!tamanoSuperado(indice);indice++) {
			copiaTutorias[indice]=new Tutoria(coleccionTutorias[indice]);
		}
			
		return copiaTutorias;
		
	}
	
	public Tutoria[] get(Profesor profesor) {
		Tutoria[] copiaPorProfesor=new Tutoria[capacidad];
		
		for(int i=0;!tamanoSuperado(i);i++) {
			if(coleccionTutorias[i].getProfesor().equals(profesor)) {
				copiaPorProfesor[i]=new Tutoria(coleccionTutorias[i]);
			}
		}
		return copiaPorProfesor;
	}

	
	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}
	public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
		if (tutoria==null) {
			throw new NullPointerException("ERROR: No se puede insertar una tutoría nula.");
		}
		
		if (capacidadSuperada(buscarIndice(tutoria))) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más tutorías.");
		}else if (!tamanoSuperado(buscarIndice(tutoria))) {
			throw new OperationNotSupportedException("ERROR: Ya existe una tutoría con ese identificador.");
		}else {
			coleccionTutorias[buscarIndice(tutoria)]=new Tutoria(tutoria);
			tamano++;
		}
	}
	private int buscarIndice(Tutoria tutoria) {
		int indiceTutoria=0;
		boolean indiceEncontrado = false;

		while (!tamanoSuperado(indiceTutoria) && !indiceEncontrado) {
			if (coleccionTutorias[indiceTutoria].equals(tutoria)) {
				indiceEncontrado = true;
			} else {
				indiceTutoria++;
			}

		}
		return indiceTutoria;
	}
	public boolean tamanoSuperado(int indice) {
		return indice>=tamano;
	}
	
	public boolean capacidadSuperada(int indice) {
		return indice>=capacidad;
	}
public Tutoria buscar(Tutoria tutoria) {
		
		if(tutoria==null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una tutoría nula.");
		}
		
		if(tamanoSuperado(buscarIndice(tutoria))){
			return null;
		}else {
			return new Tutoria(coleccionTutorias[buscarIndice(tutoria)]);
		}
		
		
	}
	
	public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
		int indice=buscarIndice(tutoria);
		
		if(tutoria==null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una tutoría nula.");
		}
		
		if(tamanoSuperado(indice)){
			throw new OperationNotSupportedException("ERROR: No existe ninguna tutoría con ese identificador.");
		}else {
			desplazarUnaPosicionHaciaIzquierda(indice);
			}
			
		}
	public void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int indiceArray;
		for(indiceArray=indice;!tamanoSuperado(indiceArray);indiceArray++) {
			coleccionTutorias[indiceArray]=coleccionTutorias[indiceArray+1];
		}
		coleccionTutorias[indiceArray]=null;
		tamano--;
	}
}
