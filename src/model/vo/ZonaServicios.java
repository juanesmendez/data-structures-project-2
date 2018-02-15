package model.vo;

import model.data_structures.LinkedList;

public class ZonaServicios implements Comparable<ZonaServicios>{

	private String idZona;
	
	private LinkedList<FechaServicios> fechasServicios;
	
	

	public String getIdZona() {
		return idZona;
	}



	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}



	public LinkedList<FechaServicios> getFechasServicios() {
		return fechasServicios;
	}



	public void setFechasServicios(LinkedList<FechaServicios> fechasServicios) {
		this.fechasServicios = fechasServicios;
	}



	@Override
	public int compareTo(ZonaServicios o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
