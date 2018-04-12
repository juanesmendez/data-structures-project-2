package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.javafx.binding.StringFormatter;

import api.ITaxiTripsManager;
import javafx.util.converter.LocalDateStringConverter;
import model.data_structures.HashTable;
import model.data_structures.IHashTable;
import model.data_structures.IQueue;
import model.data_structures.IRedBlackBST;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
import model.data_structures.List;
import model.data_structures.RedBlackBST;
import model.sort.Heap;
import model.vo.*;

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
	
	
	private IRedBlackBST<Company,LinkedList<Taxi>> treeCompanies; //Tree requirement 1A
	private IHashTable<Integer,LinkedList<Service>> hashTableServicesByTripSeconds; //HashTable requirement 2A
	private IRedBlackBST<Integer,LinkedList<Service>> treeServicesByMiles;; // Tree requirement 1B
	private IHashTable<String,LinkedList<Service>> hashTableServicesByPickupDroppoffArea; //HashTable requirement 2B
	private LinkedList<Taxi> taxis; //List requirement 1C
	//private IHashTable<String,RedBlackBST<Service>> hashTableTreeOfServices; //HashTable requirement 2C
	private IRedBlackBST<MyDateTime,LinkedList<Service>> treeServicesByTimeRange; //Tree requirement 3C
	
	
	
	public TaxiTripsManager() {
		
		this.treeCompanies = new RedBlackBST<Company,LinkedList<Taxi>>();
		this.hashTableServicesByTripSeconds = new HashTable<Integer,LinkedList<Service>>();
		this.treeServicesByMiles = new RedBlackBST<Integer, LinkedList<Service>>();
		this.hashTableServicesByPickupDroppoffArea = new HashTable<String, LinkedList<Service>>();
		this.taxis = new List<Taxi>();
		//this. instantaite missing hashtable 2C
		this.treeServicesByTimeRange = new RedBlackBST<MyDateTime, LinkedList<Service>>();
		
	}
	

	@Override //1C
	public boolean cargarSistema(String serviceFilesArray[]) 
	{
		// TODO Auto-generated method stub
		boolean cargo = false;
		JSONParser parser = new JSONParser();
		int contServicios = 0;
		
		
		//I instantiate de data structures
		
		this.treeCompanies = new RedBlackBST<Company,LinkedList<Taxi>>();
		this.hashTableServicesByTripSeconds = new HashTable<Integer,LinkedList<Service>>();
		this.treeServicesByMiles = new RedBlackBST<Integer, LinkedList<Service>>();
		this.hashTableServicesByPickupDroppoffArea = new HashTable<String, LinkedList<Service>>();
		this.taxis = new List<Taxi>();
		//Instantiate the hashtable missing
		this.treeServicesByTimeRange = new RedBlackBST<MyDateTime, LinkedList<Service>>();
		
		for(int i=0;i<serviceFilesArray.length;i++) {


			try {
				Object obj = parser.parse(new FileReader(serviceFilesArray[i]));


				JSONArray jsonArray = (JSONArray)obj;
				JSONObject jsonObject;

				String aux;

				String companyName;
				String dropoffCensusTract; // Verify if I need to change it to double
				double dropoffLatitude;
				//Add declaration for saving dropoff_centroid_location in a variable
				double dropoffLongitude;
				int dropoffCommunityArea;
				float extras; //Check if leaving it as an integer or if it is better to change it to another type
				float fare;
				String paymentType;
				String pickupCensusTract;
				double pickupLatitude;
				//Add variable declaration for saving pickup_centroid_location
				double pickupLongitude;
				int pickupCommunityArea;
				String idTaxi;
				float tips;
				float tolls;
				LocalDateTime tripEnd;
				String idTrip;
				float tripMiles;
				int tripSeconds;
				LocalDateTime tripStart;
				float tripTotal;
				MyDateTime tripStartAux;
				MyDateTime tripEndAux;


				String auxDate;
				int year;
				int month;
				int day;
				int hour;
				int minutes;
				int seconds;
				int nanoseconds;

				//World classes
				Company company;
				Taxi taxi;
				Service service;

				Iterator<JSONObject> iterator = jsonArray.iterator();
				while(iterator.hasNext()) {
					jsonObject = (JSONObject) iterator.next();
					companyName = (String) jsonObject.get("company");
					if(companyName == null) {
						companyName = "Independent Owner";
					}
					//System.out.println("Company: "+companyName);
					dropoffCensusTract = (String) jsonObject.get("dropoff_census_tract");
					//System.out.println("Dropoff Census Tract: "+dropoffCensusTract);
					String auxDropoffLatitude = (String) jsonObject.get("dropoff_centroid_latitude");
					if(auxDropoffLatitude == null) {
						auxDropoffLatitude = "0";
					}
					dropoffLatitude = Double.parseDouble(auxDropoffLatitude);
					//System.out.println("Dropoff Centroid Latitude: "+dropoffCentroidLatitude);
					//Add dropoff_centroid_location which is an array
					String auxDropoffLongitude = (String) jsonObject.get("dropoff_centroid_longitude");
					if(auxDropoffLongitude == null) {
						auxDropoffLongitude = "0";
					}
					dropoffLongitude = Double.parseDouble(auxDropoffLongitude);
					//System.out.println("Dropoff Centroid Longitude: "+dropoffCentroidLongitude);
					aux = (String) jsonObject.get("dropoff_community_area");
					if(aux!=null) {
						dropoffCommunityArea = Integer.parseInt(aux);
						//System.out.println("Dropoff Community Area: "+dropoffCommunityArea);
					}else {
						dropoffCommunityArea = 0; //If community area is null assign 0, it means that there is no information about the community area
						//System.out.println("Dropoff Community Area: "+dropoffCommunityArea);
					}
					aux = (String) jsonObject.get("extras");
					if(aux==null) {
						aux = "0";
					}
					extras = Float.parseFloat(aux);
					//System.out.println("Extras: "+extras);
					aux = (String) jsonObject.get("fare");
					if(aux==null) {
						aux = "0";
					}
					fare = Float.parseFloat(aux);
					//System.out.println("Fare: "+fare);
					paymentType = (String) jsonObject.get("payment_type");
					//System.out.println("Payment Type: "+paymentType);
					pickupCensusTract = (String) jsonObject.get("pickup_census_tract");
					//System.out.println("Pickup Census Tract: "+pickupCensusTract);
					
					String auxPickupLatitude = (String) jsonObject.get("pickup_centroid_latitude");
					if(auxPickupLatitude == null) {
						auxPickupLatitude = "0";
					}
					
					pickupLatitude = Double.parseDouble(auxPickupLatitude);
					//System.out.println("Pickup Centroid Latitude: "+pickupCentroidLatitude);
					// Add pickup_centroid_location which is an array
					
					String auxPickupLongitude = (String) jsonObject.get("pickup_centroid_longitude");
					if(auxPickupLongitude == null) {
						auxPickupLongitude="0";
					}
					pickupLongitude = Double.parseDouble(auxPickupLongitude);
					//System.out.println("Pickup Centroid Longitude: "+pickupCentroidLongitude);
					aux = (String) jsonObject.get("pickup_community_area");
					if(aux!=null) {
						pickupCommunityArea = Integer.parseInt(aux);
						//	System.out.println("Pickup Community Area: "+pickupCommunityArea);
					}else {
						pickupCommunityArea = 0; //If community area is null assign 0, it means that there is not enough information about the community area.
						//	System.out.println("Pickup Community Area: "+pickupCommunityArea);
					}
					idTaxi = (String) jsonObject.get("taxi_id");
					//System.out.println("Taxi ID: "+idTaxi);
					aux = (String) jsonObject.get("tips");
					if(aux == null) {
						aux = "0";
					}
					tips = Float.parseFloat(aux);
					//System.out.println("Tips: "+tips);
					aux = (String) jsonObject.get("tolls");
					if(aux == null) {
						aux = "0";
					}
					tolls = Float.parseFloat(aux);
					//System.out.println("Tolls: "+tolls);
					//Continue here
					//Parsing date timestamp
					auxDate = (String) jsonObject.get("trip_end_timestamp");
					if(auxDate == null) {
						tripEnd = LocalDateTime.now(); //Assigning actual date
						//	System.out.println("Trip end timestamp: NO HAY INFORMACION");
						tripEndAux = new MyDateTime(tripEnd.getYear(),tripEnd.getMonthValue(),tripEnd.getDayOfMonth(),tripEnd.getHour(),tripEnd.getMinute(),tripEnd.getSecond(),tripEnd.getNano());
					}else {
						StringTokenizer tokenizer = new StringTokenizer(auxDate, "-");
						year = Integer.parseInt(tokenizer.nextToken());
						month = Integer.parseInt(tokenizer.nextToken());
						aux = tokenizer.nextToken();
						tokenizer = new StringTokenizer(aux, ":");
						day = Integer.parseInt(tokenizer.nextToken("T"));
						aux = tokenizer.nextToken();
						tokenizer = new StringTokenizer(aux, ":");
						hour = Integer.parseInt(tokenizer.nextToken());
						minutes = Integer.parseInt(tokenizer.nextToken());
						aux=tokenizer.nextToken();
						tokenizer = new StringTokenizer(aux, ".");
						seconds = Integer.parseInt(tokenizer.nextToken());
						nanoseconds = Integer.parseInt(tokenizer.nextToken());
						tripEnd = LocalDateTime.of(year, month, day, hour, minutes, seconds, nanoseconds);
						//	System.out.println("Trip end timestamp: "+tripEnd.toString());
						tripEndAux = new MyDateTime(year, month, day, hour, minutes, seconds, nanoseconds);
						
					}

					idTrip = (String) jsonObject.get("trip_id");
					//System.out.println("Trip id: "+idTrip);
					aux = (String) jsonObject.get("trip_miles");
					if(aux == null) {
						aux = "0";
					}
					tripMiles = Float.parseFloat(aux);
					//System.out.println("Trip Miles: "+tripMiles);
					aux = (String) jsonObject.get("trip_seconds");
					if(aux == null) {
						aux = "0";
					}
					tripSeconds = Integer.parseInt(aux);
					//System.out.println("Trip seconds: "+tripSeconds);
					auxDate = (String) jsonObject.get("trip_start_timestamp");

					if(auxDate == null) {
						tripStart = LocalDateTime.now();
						//Trip start Aux is auxiliary date time object instantiated with MyDateTime class implementing comparable:
						tripStartAux = new MyDateTime(tripStart.getYear(),tripStart.getMonthValue(),tripStart.getDayOfMonth(),tripStart.getHour(),tripStart.getMinute(),tripStart.getSecond(),tripStart.getNano());
					}else {
						StringTokenizer tokenizer;
						tokenizer = new StringTokenizer(auxDate, "-");
						year = Integer.parseInt(tokenizer.nextToken());
						month = Integer.parseInt(tokenizer.nextToken());
						aux = tokenizer.nextToken();
						tokenizer = new StringTokenizer(aux, ":");
						day = Integer.parseInt(tokenizer.nextToken("T"));
						aux = tokenizer.nextToken();
						tokenizer = new StringTokenizer(aux, ":");
						hour = Integer.parseInt(tokenizer.nextToken());
						minutes = Integer.parseInt(tokenizer.nextToken());
						aux=tokenizer.nextToken();
						tokenizer = new StringTokenizer(aux, ".");
						seconds = Integer.parseInt(tokenizer.nextToken());
						nanoseconds = Integer.parseInt(tokenizer.nextToken());
						tripStart = LocalDateTime.of(year, month, day, hour, minutes, seconds, nanoseconds);
						
						//Trip start Aux is auxiliary date time object instantiated with MyDateTime class implementing comparable:
						tripStartAux = new MyDateTime(year, month, day, hour, minutes, seconds, nanoseconds);
					}
					
					

					//System.out.println("Trip start timestamp: " +tripStart.toString());
					aux = (String) jsonObject.get("trip_total");
					if(aux==null) {
						aux = "0";
					}
					tripTotal = Float.parseFloat(aux);
					//System.out.println("Trip total: "+tripTotal);

					// From here I start CREATING WORLD CLASSES
					company = new Company(companyName);
					
					taxi = new Taxi(idTaxi);
					this.associateCompanyToTaxi(taxi, company);
					
					service = new Service(idTrip, companyName, extras, fare, paymentType, tips, tolls, tripEnd, tripStart, dropoffCommunityArea, pickupCommunityArea, tripSeconds, tripMiles, tripTotal, dropoffLatitude, dropoffLongitude, pickupLatitude, pickupLongitude,tripStartAux, tripEndAux);
					this.associateTaxiToService(taxi, service);
					
					//Requirement 1A
					this.constructTreeR1A(company, taxi, service, pickupCommunityArea);
					
					//Requirement 2A
					
					this.constructHashTableR2A(service);
					
					//Requirement 1B
					
					this.constructTreeR1B(service);
					
					//Requirement 2B
					
					this.constructHashTable2B(service);
					
					//Requirement 1C
					this.taxis.add(taxi);
					
					//Requirement 3C
					this.constructTreeR3C(service, tripStartAux);
					
					//CONTINUAR CON VERIFICAR ORDENAMIENTO DE LISTA DE COMPAÑIAS 
					
					contServicios++;
					//System.out.println();
				}
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}catch(ParseException e) {
				e.printStackTrace();
			} catch(Exception e){
				e.printStackTrace();
			}finally {
				cargo = true;
			}
			
			System.out.println();
			System.out.println("Se cargaron "+contServicios+ " datos del .JSON.");
			System.out.println();
			
		
			
			
			
		}
		
		//Imprimo el arbol inicial:
		
		
		/*
		Iterable<Company> keys = this.treeCompanies.keys();
		
		
		for(Company c:keys) {
			System.out.println(c.toString());
			LinkedList<Taxi> taxis= this.treeCompanies.get(c);
			for(Taxi t:taxis) {
				System.out.println("TAXI ID: "+t.getTaxiId());
				System.out.println();
				Iterable<Integer> keysInteger = t.getHashTableServicesByPickupArea().keys();
				for(Integer in:keysInteger) {
					LinkedList<Service> services = t.getHashTableServicesByPickupArea().get(in);
					System.out.println("CODIGO AREA:"+in.toString());
					for(Service s:services) {
						System.out.println(s.toString());
						System.out.println();
					}
				}
				
			}
			System.out.println();
		}
		*/
		if(this.hashTableServicesByTripSeconds.keys() != null) {
			Iterable<Integer> keys = this.hashTableServicesByTripSeconds.keys();
			int contadoooor=0;
			for(Integer inte:keys) {
				System.out.println(inte.toString());
				contadoooor++;
				
			}
			System.out.println("#LLAVES EN HASH: "+ contadoooor);
		}else {
			System.out.println("HASH NULA!!!!");
		}
		return cargo;
	}

	public void constructTreeR1A(Company company, Taxi taxi, Service service, int pickupCommunityArea) {
		LinkedList<Taxi> taxis = this.treeCompanies.get(company);
		if(taxis == null) {
			taxis = new List<Taxi>();
			taxis.add(taxi);
			this.treeCompanies.put(company, taxis);
			LinkedList<Service> services = new List<Service>();
			Comparator comparator = new Service.TripStartComparator();
			services.add(service, comparator);
			//services.add(service);
			taxi.getHashTableServicesByPickupArea().put(pickupCommunityArea, services);
		}else {
			Taxi taxiAux = taxis.get(taxi);
			if(taxiAux == null) {
				taxis.add(taxi);
			}else {
				taxi = taxiAux;
			}
			LinkedList<Service> services = taxi.getHashTableServicesByPickupArea().get(pickupCommunityArea);
			Comparator comparator = new Service.TripStartComparator();
			if(services == null) {
				services = new List<Service>();
				services.add(service,comparator);
				taxi.getHashTableServicesByPickupArea().put(pickupCommunityArea, services);
			}else {
				//Services never repeat so:
				services.add(service,comparator);
			}
		}
	}
	
	public void constructHashTableR2A(Service service) {
		int key = service.getTripSeconds() % 60;
		key = service.getTripSeconds() - key;
		LinkedList<Service> services = this.hashTableServicesByTripSeconds.get(key);
		if(services == null) {
			services = new List<Service>();
			services.add(service);
			this.hashTableServicesByTripSeconds.put(key, services);
		}else {
			services.add(service);
		}
	}
	public void constructTreeR1B (Service service)
	{
		
		Integer key = (int) (service.getTripMiles() % 1);
		key = (int) (service.getTripMiles () - key);
		LinkedList<Service> services = this.treeServicesByMiles.get(key);
		
		if (services == null)
		{
			services = new List<Service>();
			services.add(service);
			this.treeServicesByMiles.put(key, services);

		}
		else 
		{
			services.add(service);
		}
		
	}
	public void constructHashTable2B (Service service)
	{
		String key = service.getPickupCommunityArea()+"-"+service.getDropoffCommunityArea();
		LinkedList <Service> services = this.hashTableServicesByPickupDroppoffArea.get(key);
		
		if (services == null)
		{
			services = new List <Service>();
			services.add(service);
			this.hashTableServicesByPickupDroppoffArea.put(key, services);
		}
		else
		{
			services.add(service);
		}
	}
	
	public void constructTreeR3C(Service service,MyDateTime tripStartAux ) {
		LinkedList<Service> services = this.treeServicesByTimeRange.get(tripStartAux);
		
		if(services == null) {
			services = new List<Service>();
			services.add(service);
			this.treeServicesByTimeRange.put(tripStartAux, services);
		}else {
			services.add(service);
		}
	}
	
	public void associateCompanyToTaxi(Taxi taxi,Company company) {

		if(taxi.getCompany() == null) {
			taxi.setCompany(company);
		}

	}
	
	public void associateTaxiToService(Taxi taxi, Service service) {
		if(service.getTaxi() == null) {
			service.setTaxi(taxi);
		}
	}


	@Override
	public LinkedList<Taxi> A1TaxiConMasServiciosEnZonaParaCompania(int zonaInicio, String compania) {
		// TODO Auto-generated method stub
		Company company = new Company(compania);
		LinkedList<Taxi> taxis = this.treeCompanies.get(company);
		int mayor = 0;
		Taxi mayorTaxi = null;
		LinkedList<Taxi> taxisResultantes = new List<Taxi>();
		if(taxis != null) {
			for(Taxi t:taxis) {
				LinkedList<Service> services = t.getHashTableServicesByPickupArea().get(zonaInicio);
				if(services !=null) {
					int size = services.size();
					if(size>mayor) {
						mayor = size;
						mayorTaxi = t;
					}
				}
				
			}
			if(mayorTaxi !=null) {
				for(Taxi t:taxis) {
					if(t.getHashTableServicesByPickupArea().get(zonaInicio) !=null) {
						if(t.getHashTableServicesByPickupArea().get(zonaInicio).size() == mayorTaxi.getHashTableServicesByPickupArea().get(zonaInicio).size()) {
							taxisResultantes.add(t);
						}
					}
				}
				
			}
		}else {
			return null;
		}
		
		
		return taxisResultantes;
	}


	@Override
	public LinkedList<Service> A2ServiciosPorDuracion(int duracion) {
		// TODO Auto-generated method stub
		LinkedList<Service> services=null;
		
		duracion = duracion - (duracion%60);
		services = this.hashTableServicesByTripSeconds.get(duracion);
		
		
		return services;
	}


	@Override
	public LinkedList<Service> B1ServiciosPorDistancia(double distanciaMinima, double distanciaMaxima) {
		// TODO Auto-generated method stub
		LinkedList <Service> services = null;
		
		LinkedList<Service> respuesta= new List<Service>();
		distanciaMinima = distanciaMinima - (distanciaMinima % 1);
		distanciaMaxima = distanciaMaxima - (distanciaMaxima % 1);
		
		
		for(int i= (int) distanciaMinima;i<distanciaMaxima;i++){
			services = this.treeServicesByMiles.get(i);
			
			
			if (services!=null)
			{
				
				for(Service s:services)
				{
					respuesta.add(s);
				}
			}
			else
			{
				System.out.println("Esta vacia");
			}
		}
		
		return respuesta;
	}


	@Override
	public LinkedList<Service> B2ServiciosPorZonaRecogidaYLlegada(int zonaInicio, int zonaFinal, String fechaI,
			String fechaF, String horaI, String horaF) {
		// TODO Auto-generated method stub
		LinkedList <Service> services = new List<Service>();
		LinkedList <Service> respuesta =new List<Service>();
		
		String key = zonaInicio+"-"+zonaFinal ;
		
		services = this.hashTableServicesByPickupDroppoffArea.get(key);
		
		StringTokenizer tokenizer = new StringTokenizer(fechaI, "-");
		int year = Integer.parseInt(tokenizer.nextToken());
		int month = Integer.parseInt(tokenizer.nextToken());
		int day = Integer.parseInt(tokenizer.nextToken());
		
		tokenizer = new StringTokenizer(horaI, ":");
		int hour = Integer.parseInt(tokenizer.nextToken());
		int minutes = Integer.parseInt(tokenizer.nextToken());
		
		String aux=tokenizer.nextToken();
		tokenizer = new StringTokenizer(aux, ".");
		int seconds = Integer.parseInt(tokenizer.nextToken());
		int nanoseconds = Integer.parseInt(tokenizer.nextToken());
		
		StringTokenizer tokenizer2 = new StringTokenizer(fechaF, "-");
		int year2 = Integer.parseInt(tokenizer2.nextToken());
		int month2 = Integer.parseInt(tokenizer2.nextToken());
		int day2 = Integer.parseInt(tokenizer2.nextToken());
		
		tokenizer2 = new StringTokenizer(horaF, ":");
		int hour2 = Integer.parseInt(tokenizer2.nextToken());
		int minutes2 = Integer.parseInt(tokenizer2.nextToken());
		
		String aux2 = tokenizer2.nextToken();
		tokenizer2 = new StringTokenizer(aux, ".");
		int seconds2 = Integer.parseInt(tokenizer2.nextToken());
		int nanoseconds2 = Integer.parseInt(tokenizer2.nextToken());
		
		MyDateTime datetimeToSearch = new MyDateTime(year, month, day, hour, minutes, seconds, nanoseconds);
		MyDateTime datetimeToSearch2 = new MyDateTime(year2, month2, day2, hour2, minutes2, seconds2, nanoseconds2);
	
		
		
		if (services==null)
		{
			System.out.println("Lista Vacia2");
		}else{
		for(Service s: services)
		{
			
			if (s.getTripStartAux()==datetimeToSearch&&s.getTripEndAux()==datetimeToSearch2)
			{
				respuesta.add(s);
				System.out.println("agrego " + s);
			}
			
		
		}
		}
		
		
		return respuesta;
	}


	@Override
	public Taxi[] R1C_OrdenarTaxisPorPuntos() {
		// TODO Auto-generated method stub
		double totalMiles=0;
		double totalIncome = 0;
		int numServices=0;
		double puntaje=0;
		Taxi[] arrayTaxis = new Taxi[this.taxis.size()];
		int pos=0;
		for(Taxi taxi: this.taxis) {
			arrayTaxis[pos] = taxi;
			pos++;
			Iterable<Integer> keys = taxi.getHashTableServicesByPickupArea().keys();
			for(Integer i: keys) {
				LinkedList<Service> services= taxi.getHashTableServicesByPickupArea().get(i);
				for(Service s:services) {
					if(s.getTripMiles() > 0 && s.getTripTotal() >0) {
						numServices++;
						totalMiles = totalMiles+s.getTripMiles();
						totalIncome = totalIncome + s.getTripTotal();
					}
				}
			}
			
			if((totalIncome != 0) && (totalMiles!=0)) {
				puntaje = (totalIncome / totalMiles) * numServices;
			}else {
				puntaje = 0;
			}
			taxi.setPuntaje((float)puntaje);
			totalIncome=0;
			totalMiles=0;
			numServices = 0;
			puntaje = 0;
		}
		
		Heap.sort(arrayTaxis);
		
		
		return arrayTaxis;
	}


	@Override
	public LinkedList<Service> R2C_LocalizacionesGeograficas(String taxiIDReq2C, double millas, double latitud,
			double longitud) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public LinkedList<Service> R3C_ServiciosEn15Minutos(String fecha, String hora) {
		// TODO Auto-generated method stub
		StringTokenizer tokenizer = new StringTokenizer(fecha, "-");
		int year = Integer.parseInt(tokenizer.nextToken());
		int month = Integer.parseInt(tokenizer.nextToken());
		int day = Integer.parseInt(tokenizer.nextToken());
		tokenizer = new StringTokenizer(hora, ":");
		int hour = Integer.parseInt(tokenizer.nextToken());
		int minutes = Integer.parseInt(tokenizer.nextToken());
		String aux=tokenizer.nextToken();
		tokenizer = new StringTokenizer(aux, ".");
		int seconds = Integer.parseInt(tokenizer.nextToken());
		int nanoseconds = Integer.parseInt(tokenizer.nextToken());
		
		int minutesAux = minutes - (minutes%15);
		//seconds and nanoseconds will always be cero because of the way te information was rounded by the government
		MyDateTime datetimeToSearch = new MyDateTime(year, month, day, hour, minutesAux, seconds, nanoseconds);
		
		LinkedList<Service> services = this.treeServicesByTimeRange.get(datetimeToSearch);
		
		return services;
	}
}
