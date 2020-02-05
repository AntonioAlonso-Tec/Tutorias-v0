package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

public class Alumnos {
	private int capacidad;
	private int tamano;
	private Alumno[] coleccionAlumnos;

	public Alumnos(int capacidad) {
		if (capacidad < 1) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		} else {
			this.capacidad = capacidad;
			tamano = 0;
			this.coleccionAlumnos = new Alumno[capacidad];
		}
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	private Alumno[] copiaProfundaAlumno() {
		Alumno[] copiaAlumnos = new Alumno[capacidad];
		int indice = 0;

		for (indice = 0; !tamanoSuperado(indice); indice++) {
			copiaAlumnos[indice] = new Alumno(coleccionAlumnos[indice]);
		}

		return copiaAlumnos;

	}

	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}

		if (capacidadSuperada(buscarIndice(alumno))) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
		} else if (!tamanoSuperado(buscarIndice(alumno))) {
			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese expediente.");
		} else {
			coleccionAlumnos[buscarIndice(alumno)] = new Alumno(alumno);
			tamano++;
		}
	}

	private int buscarIndice(Alumno alumno) {
		int indiceAlumno = 0;
		boolean indiceEncontrado = false;

		while (!tamanoSuperado(indiceAlumno) && !indiceEncontrado) {
			if (coleccionAlumnos[indiceAlumno].equals(alumno)) {
				indiceEncontrado = true;
			} else {
				indiceAlumno++;
			}

		}
		return indiceAlumno;
	}

	public boolean tamanoSuperado(int indice) {
		return indice >= tamano;
	}

	public boolean capacidadSuperada(int indice) {
		return indice >= capacidad;
	}

	public Alumno[] get() {

		return copiaProfundaAlumno();
	}

	public Alumno buscar(Alumno alumno) {

		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
		}

		if (tamanoSuperado(buscarIndice(alumno))) {
			return null;
		} else {
			return new Alumno(coleccionAlumnos[buscarIndice(alumno)]);
		}

	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
		}

		if (tamanoSuperado(buscarIndice(alumno))) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese expediente.");
		} else {
			desplazarUnaPosicionHaciaIzquierda(buscarIndice(alumno));
		}

	}

	public void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int indiceArray;

		for (indiceArray = indice; !tamanoSuperado(indiceArray); indiceArray++) {
			coleccionAlumnos[indiceArray] = coleccionAlumnos[indiceArray + 1];
		}
		coleccionAlumnos[indiceArray] = null;
		tamano--;
	}

}
