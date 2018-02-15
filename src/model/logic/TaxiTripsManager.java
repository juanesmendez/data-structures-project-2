package model.logic;

import api.ITaxiTripsManager;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
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

public class TaxiTripsManager implements ITaxiTripsManager 
{
	public static final String DIRECCION_SMALL_JSON = "./data/taxi-trips-wrvz-psew-subset-small.json";
	public static final String DIRECCION_MEDIUM_JSON = "./data/taxi-trips-wrvz-psew-subset-medium.json";
	public static final String DIRECCION_LARGE_JSON = "./data/taxi-trips-wrvz-psew-subset-large.json";

	@Override //1C
	public boolean cargarSistema(String direccionJson) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override //1A
	public IQueue <Servicio> darServiciosEnPeriodo(RangoFechaHora rango)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override //2A
	public Taxi darTaxiConMasServiciosEnCompaniaYRango(RangoFechaHora rango, String company)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override //3A
	public InfoTaxiRango darInformacionTaxiEnRango(String id, RangoFechaHora rango)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override //4A
	public LinkedList<RangoDistancia> darListaRangosDistancia(String fecha, String horaInicial, String horaFinal) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override //1B
	public LinkedList<Compania> darCompaniasTaxisInscritos() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override //2B
	public Taxi darTaxiMayorFacturacion(RangoFechaHora rango, String nomCompania) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override //3B
	public ServiciosValorPagado[] darServiciosZonaValorTotal(RangoFechaHora rango, String idZona)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override //4B
	public LinkedList<ZonaServicios> darZonasServicios(RangoFechaHora rango)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override //2C
	public LinkedList<CompaniaServicios> companiasMasServicios(RangoFechaHora rango, int n)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override //3C
	public LinkedList<CompaniaTaxi> taxisMasRentables()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override //4C
	public IStack <Servicio> darServicioResumen(String taxiId, String horaInicial, String horaFinal, String fecha) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
