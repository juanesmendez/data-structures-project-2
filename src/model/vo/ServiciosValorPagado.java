package model.vo;

import model.data_structures.LinkedList;

public class ServiciosValorPagado {
	
	private LinkedList<Servicio> serviciosAsociados;
	private double valorAcumulado;
	public LinkedList<Servicio> getServiciosAsociados() {
		return serviciosAsociados;
	}
	public void setServiciosAsociados(LinkedList<Servicio> serviciosAsociados) {
		this.serviciosAsociados = serviciosAsociados;
	}
	public double getValorAcumulado() {
		return valorAcumulado;
	}
	public void setValorAcumulado(double valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}
	
	

}
