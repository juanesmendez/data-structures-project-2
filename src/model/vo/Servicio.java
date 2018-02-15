package model.vo;

/**
 * Representation of a Service object
 */
public class Servicio implements Comparable<Servicio>
{	
	/**
	 * @return id - Trip_id
	 */
	public String getTripId()
	{
		// TODO Auto-generated method stub
		return "trip Id";
	}	
	
	/**
	 * @return id - Taxi_id
	 */
	public String getTaxiId() {
		// TODO Auto-generated method stub
		return "taxi Id";
	}	
	
	/**
	 * @return time - Time of the trip in seconds.
	 */
	public int getTripSeconds() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return miles - Distance of the trip in miles.
	 */
	public double getTripMiles() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return total - Total cost of the trip
	 */
	public double getTripTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Servicio arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
