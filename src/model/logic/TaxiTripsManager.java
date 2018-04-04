package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import api.ITaxiTripsManager;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
import model.data_structures.List;
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
	
	private LinkedList<Service> services;
	private LinkedList<Company> companies;
	private LinkedList<Taxi> taxis;
	
	public TaxiTripsManager() {
		this.services = new List<>();
		this.companies = new List<>();
		this.taxis = new List<>();
	}
	

	@Override //1C
	public boolean cargarSistema(String direccionJsonArrar[]) 
	{
		// TODO Auto-generated method stub
		
		boolean cargo = false;
		JSONParser parser = new JSONParser();
		int contServicios = 0;
		
		this.services = new List<>();
		this.companies = new List<>();
		this.taxis = new List<>();
		for(int i=0;i<serviceFilesArray.length;i++) {


			try {
				Object obj = parser.parse(new FileReader(serviceFilesArray[i]));


				JSONArray jsonArray = (JSONArray)obj;
				JSONObject jsonObject;

				String aux;

				String companyName;
				String dropoffCensusTract; // Verify if I need to change it to double
				String dropoffCentroidLatitude;
				//Add declaration for saving dropoff_centroid_location in a variable
				String dropoffCentroidLongitude;
				int dropoffCommunityArea;
				float extras; //Check if leaving it as an integer or if it is better to change it to another type
				float fare;
				String paymentType;
				String pickupCensusTract;
				String pickupCentroidLatitude;
				//Add variable declaration for saving pickup_centroid_location
				String pickupCentroidLongitude;
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
						companyName = "Independent";
					}
					//System.out.println("Company: "+companyName);
					dropoffCensusTract = (String) jsonObject.get("dropoff_census_tract");
					//System.out.println("Dropoff Census Tract: "+dropoffCensusTract);
					dropoffCentroidLatitude = (String) jsonObject.get("dropoff_centroid_latitude");
					//System.out.println("Dropoff Centroid Latitude: "+dropoffCentroidLatitude);
					//Add dropoff_centroid_location which is an array
					dropoffCentroidLongitude = (String) jsonObject.get("dropoff_centroid_longitude");
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
					pickupCentroidLatitude = (String) jsonObject.get("pickup_centroid_latitude");
					//System.out.println("Pickup Centroid Latitude: "+pickupCentroidLatitude);
					// Add pickup_centroid_location which is an array
					pickupCentroidLongitude = (String) jsonObject.get("pickup_centroid_longitude");
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
					}

					//System.out.println("Trip start timestamp: " +tripStart.toString());
					aux = (String) jsonObject.get("trip_total");
					if(aux==null) {
						aux = "0";
					}
					tripTotal = Float.parseFloat(aux);
					//System.out.println("Trip total: "+tripTotal);

					// From here I start CREATING WORLD CLASSES

					//Add to list in alphabetical order.
					company = this.addCompany(companyName); //Return object always gonna be different to null
					taxi = this.addTaxi(idTaxi); //Return object always gonna be different to null
					//Associating company object to taxi object and adding taxi to company's list:
					this.associateCompanyToTaxi(taxi, company);
					this.addTaxiToCompany(taxi, company);

					service = this.addService(idTrip, companyName, extras, fare, paymentType, tips, tolls, tripEnd, tripStart, dropoffCommunityArea, pickupCommunityArea, tripSeconds, tripMiles, tripTotal);
					this.addServiceToTaxi(taxi, service);
					this.associateTaxiToService(taxi, service);


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
		return cargo;
	}

}
