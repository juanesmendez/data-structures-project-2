package model.logic;

import api.ITaxiTripsManager;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;

public class TaxiTripsManager implements ITaxiTripsManager 
{

	public static final String DIRECCION_SMALL_JSON = "./data/taxi-trips-wrvz-psew-subset-small.json";
	public static final String DIRECCION_MEDIUM_JSON = "./data/taxi-trips-wrvz-psew-subset-medium.json";
	public static final String DIRECCION_LARGE_JSON = "./data/taxi-trips-wrvz-psew-subset-large.json";
	public static final String DIRECCION_LARGE_JSON_DIA_1 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-02-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_2 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-03-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_3 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-04-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_4 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-05-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_5 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-06-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_6 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-07-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_7 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-08-02-2017.json";
	

	@Override //1C
	public boolean cargarSistema(String direccionJsonArrar[]) 
	{
		// TODO Auto-generated method stub
		return false;
	}

}
