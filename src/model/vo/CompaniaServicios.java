package model.vo;

import model.data_structures.LinkedList;

public class CompaniaServicios implements Comparable<CompaniaServicios> {
	
	private String nomCompania;
	
	private LinkedList<Servicio> servicios;

	public String getNomCompania() {
		return nomCompania;
	}

	public void setNomCompania(String nomCompania) {
		this.nomCompania = nomCompania;
	}

	public LinkedList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(LinkedList<Servicio> servicios) {
		this.servicios = servicios;
	}

	@Override
	public int compareTo(CompaniaServicios o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
