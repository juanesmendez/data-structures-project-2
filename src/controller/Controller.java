package controller;

import api.ITaxiTripsManager;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
import model.logic.TaxiTripsManager;

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

}
