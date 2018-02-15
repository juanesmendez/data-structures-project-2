package model.vo;

import model.data_structures.LinkedList;

public class Compania implements Comparable<Compania> {
	
	private String nombre;
	
	private LinkedList<Taxi> taxisInscritos;	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LinkedList<Taxi> getTaxisInscritos() {
		return taxisInscritos;
	}

	public void setTaxisInscritos(LinkedList<Taxi> taxisInscritos) {
		this.taxisInscritos = taxisInscritos;
	}

	@Override
	public int compareTo(Compania o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
