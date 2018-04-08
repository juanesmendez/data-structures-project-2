package model.vo;

import model.data_structures.HashTable;
import model.data_structures.IHashTable;
import model.data_structures.LinkedList;
import model.data_structures.List;

public class Taxi implements Comparable<Taxi>{

	private String taxiId;
	private Company company;
	private LinkedList<Service> services;
	private IHashTable<Integer,LinkedList<Service>> hashTableServicesByPickupArea;
	//Only used in requirement 2A
	private float puntaje;
	
	public Taxi(String taxiId) {
		this.taxiId = taxiId;
		this.company = null;
		this.services = new List<Service>();
		this.hashTableServicesByPickupArea = new HashTable<Integer,LinkedList<Service>>();
		this.puntaje = 0;
	}
	
	/**
	 * @return id - taxi_id
	 */
	public String getTaxiId() {
		// TODO Auto-generated method stub
		return this.taxiId;
	}

	/**
	 * @return company
	 */
	public Company getCompany() {
		// TODO Auto-generated method stub
		return this.company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}


	public LinkedList<Service> getServices() {
		return services;
	}
	
	public IHashTable<Integer, LinkedList<Service>> getHashTableServicesByPickupArea() {
		return hashTableServicesByPickupArea;
	}
	

	public float getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(float puntaje) {
		this.puntaje = puntaje;
	}

	@Override
	public int compareTo(Taxi o) {
		// TODO Auto-generated method stub
		if(this.puntaje < o.getPuntaje()) {
			return -1;
		}else if(this.puntaje > o.getPuntaje()) {
			return 1;
		}
		return 0;
	}	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Taxi ID: "+this.taxiId+"\n"+this.company.toString()+"\nNumber of services: "+this.services.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((taxiId == null) ? 0 : taxiId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Taxi other = (Taxi) obj;
		if(this.taxiId == other.taxiId) {
			return true;
		}
		return false;
	}
	
	public void addService(Service service) {
		//Check if its necessary to verify if the Service already exists;
		
		//this.services.add(service);
		this.services.add(service); //Order doesn't matter, nust adds them to the list
	}
}

