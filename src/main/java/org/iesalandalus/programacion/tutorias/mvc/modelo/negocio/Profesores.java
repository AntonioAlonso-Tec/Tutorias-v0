package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

public class Profesores {
	private int capacidad;
	private int tamano;
	private Profesor[] coleccionProfesores;

	public Profesores(int capacidad) {
		if (capacidad < 1) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		} else {
			this.capacidad = capacidad;
			tamano = 0;
			coleccionProfesores = new Profesor[capacidad];
		}
	}

	public Profesor[] get() {

		return copiaProfundaProfesores();
	}

	private Profesor[] copiaProfundaProfesores() {
		Profesor[] copiaProfesores = new Profesor[capacidad];
		int indice = 0;

		for (indice = 0; !tamanoSuperado(indice); indice++) {
			copiaProfesores[indice] = new Profesor(coleccionProfesores[indice]);
		}

		return copiaProfesores;

	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}

		if (capacidadSuperada(buscarIndice(profesor))) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		} else if (!tamanoSuperado(buscarIndice(profesor))) {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese DNI.");
		} else {
			coleccionProfesores[buscarIndice(profesor)] = new Profesor(profesor);
			tamano++;
		}
	}

	private int buscarIndice(Profesor profesor) {
		int indiceProfesor = 0;
		boolean indiceEncontrado = false;

		while (!tamanoSuperado(indiceProfesor) && !indiceEncontrado) {
			if (coleccionProfesores[indiceProfesor].equals(profesor)) {
				indiceEncontrado = true;
			} else {
				indiceProfesor++;
			}

		}
		return indiceProfesor;
	}

	public boolean tamanoSuperado(int indice) {
		return indice >= tamano;
	}

	public boolean capacidadSuperada(int indice) {
		return indice >= capacidad;
	}

	public Profesor buscar(Profesor profesor) {

		if (profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un profesor nulo.");
		}

		if (tamanoSuperado(buscarIndice(profesor))) {
			return null;
		} else {
			return new Profesor(coleccionProfesores[buscarIndice(profesor)]);
		}

	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		int indice = buscarIndice(profesor);

		if (profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un profesor nulo.");
		}

		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese DNI.");
		} else {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}

	}

	public void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int indiceArray;
		for (indiceArray = indice; !tamanoSuperado(indiceArray); indiceArray++) {
			coleccionProfesores[indiceArray] = coleccionProfesores[indiceArray + 1];
		}
		coleccionProfesores[indiceArray] = null;
		tamano--;
	}
}
