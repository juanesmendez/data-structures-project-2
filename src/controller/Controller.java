package controller;

import api.ITaxiTripsManager;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
import model.logic.TaxiTripsManager;
import model.vo.*;

public class Controller 
{
	/**
	 * modela el manejador de la clase l�gica
	 */
	private static ITaxiTripsManager  manager = new TaxiTripsManager();
	
	public static boolean cargarSistema(String serviceFile) {
		//Chequear  como cambiar de archivo
		
		//Cambiar la siguiente linea:
		//serviceFile = "/Users/juanestebanmendez/Documents/Los Andes/Estructuras de Datos/proyecto_201810_sec_3_team_9/data/taxi-trips-wrvz-psew-subset-small.json";
		boolean cargo = false;
		String[] serviceFilesArray = null;
		if(serviceFile == TaxiTripsManager.DIRECCION_LARGE_JSON) {
			serviceFilesArray = new String[7];
			serviceFilesArray[0] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_1;
			serviceFilesArray[1] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_2;
			serviceFilesArray[2] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_3;
			serviceFilesArray[3] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_4;
			serviceFilesArray[4] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_5;
			serviceFilesArray[5] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_6;
			serviceFilesArray[6] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_7;
		}else if(serviceFile == TaxiTripsManager.DIRECCION_SMALL_JSON) {
			serviceFilesArray = new String[1];
			serviceFilesArray[0] = TaxiTripsManager.DIRECCION_SMALL_JSON;
		}else if(serviceFile == TaxiTripsManager.DIRECCION_MEDIUM_JSON) {
			serviceFilesArray = new String[1];
			serviceFilesArray[0] = TaxiTripsManager.DIRECCION_MEDIUM_JSON;
		}
		
		cargo = manager.cargarSistema(serviceFilesArray);
		return cargo;
	}
	
	//1A
		public static LinkedList<Taxi> R1A(int zonaInicio, String compania)
		{
			return manager.A1TaxiConMasServiciosEnZonaParaCompania(zonaInicio, compania);
		}

		//2A
		public static LinkedList<Service> R2A(int duracion)
		{
			return manager.A2ServiciosPorDuracion(duracion);
		}

		//1B
		public static LinkedList<Service> R1B(double distanciaMinima, double distanciaMaxima)
		{
			return manager.B1ServiciosPorDistancia(distanciaMinima, distanciaMaxima);
		}

		//2B
		public static LinkedList<Service> R2B(int zonaInicio, int zonaFinal, String fechaI, String fechaF, String horaI, String horaF)
		{
			return manager.B2ServiciosPorZonaRecogidaYLlegada(zonaInicio, zonaFinal, fechaI, fechaF, horaI, horaF);
		}	
		//1C
		public static Taxi[] R1C()
		{
			return manager.R1C_OrdenarTaxisPorPuntos();
		}	
		//2C
		public static LinkedList<Service> R2C(String taxiIDReq2C, double millas, double latitud, double longitud) throws Exception
		{
			return manager.R2C_LocalizacionesGeograficas(taxiIDReq2C, millas, latitud, longitud);
		}
		//3C
		public static LinkedList<Service> R3C(String fecha, String hora) 
		{
			return manager.R3C_ServiciosEn15Minutos(fecha, hora);
		}	

}
