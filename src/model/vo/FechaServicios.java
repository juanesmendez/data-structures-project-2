package model.vo;

import model.data_structures.LinkedList;

public class FechaServicios implements Comparable<FechaServicios>{
	
	private String fecha;
	private LinkedList<Servicio> serviciosAsociados;
	private int numServicios;
	
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public LinkedList<Servicio> getServiciosAsociados() {
		return serviciosAsociados;
	}
	public void setServiciosAsociados(LinkedList<Servicio> serviciosAsociados) {
		this.serviciosAsociados = serviciosAsociados;
	}
	public int getNumServicios() {
		return numServicios;
	}
	public void setNumServicios(int numServicios) {
		this.numServicios = numServicios;
	}
	@Override
	public int compareTo(FechaServicios o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
