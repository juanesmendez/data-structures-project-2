package controller;

import api.ITaxiTripsManager;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
import model.logic.TaxiTripsManager;
import model.vo.Compania;
import model.vo.CompaniaServicios;
import model.vo.CompaniaTaxi;
import model.vo.InfoTaxiRango;
import model.vo.RangoDistancia;
import model.vo.RangoFechaHora;
import model.vo.Servicio;
import model.vo.ServiciosValorPagado;
import model.vo.Taxi;
import model.vo.ZonaServicios;

public class Controller 
{
	/**
	 * modela el manejador de la clase l�gica
	 */
	private static ITaxiTripsManager manager = new TaxiTripsManager();

	//1C
	public static boolean cargarSistema(String direccionJson)
	{
		return manager.cargarSistema(direccionJson);
	}
	//A1
	public static IQueue<Servicio> darServiciosEnRango(RangoFechaHora rango)
	{
		return manager.darServiciosEnPeriodo(rango);
	}

	//2A
	public static Taxi darTaxiConMasServiciosEnCompaniaYRango(RangoFechaHora rango, String company)
	{
		return manager.darTaxiConMasServiciosEnCompaniaYRango(rango, company);
	}

	//3A
	public static InfoTaxiRango darInformacionTaxiEnRango(String id, RangoFechaHora rango)
	{
		return manager.darInformacionTaxiEnRango(id, rango);
	}

	//4A
	public static LinkedList<RangoDistancia> darListaRangosDistancia(String fecha, String horaInicial, String horaFinal) 
	{
		return manager.darListaRangosDistancia(fecha, horaInicial, horaFinal);
	}
	
	//1B
	public static LinkedList<Compania> darCompaniasTaxisInscritos()
	{
		return manager.darCompaniasTaxisInscritos();
	}
	
	//2B
	public static Taxi darTaxiMayorFacturacion(RangoFechaHora rango, String nomCompania)
	{
		return manager.darTaxiMayorFacturacion(rango, nomCompania);
	}
	
	//3B
	public static ServiciosValorPagado[] darServiciosZonaValorTotal(RangoFechaHora rango, String idZona)
	{
		return manager.darServiciosZonaValorTotal(rango, idZona);
	}
	
	//4B
	public static LinkedList<ZonaServicios> darZonasServicios (RangoFechaHora rango)
	{
		return manager.darZonasServicios(rango);
	}
	
	//2C
	public static LinkedList<CompaniaServicios> companiasMasServicios(RangoFechaHora rango, int n)
	{
		return manager.companiasMasServicios(rango, n);
	}

	//3C
	public static LinkedList<CompaniaTaxi> taxisMasRentables()
	{
		return manager.taxisMasRentables();
	}

	//4C
	public static IStack <Servicio> darServicioResumen(String taxiId, String horaInicial, String horaFinal, String fecha)
	{
		return manager.darServicioResumen(taxiId,horaInicial,horaFinal,fecha);
	}



}
