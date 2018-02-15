package model.vo;

/**
 * Representation of a taxi object
 */
public class Taxi implements Comparable<Taxi>
{
	/**
	 * @return id - taxi_id
	 */
	public String getTaxiId()
	{
		// TODO Auto-generated method stub
		return "taxi Id";
	}

	/**
	 * @return company
	 */
	public String getCompany()
	{
		// TODO Auto-generated method stub
		return "company";
	}
	
	@Override
	public int compareTo(Taxi o) 
	{
		// TODO Auto-generated method stub
		return 0;
	}	
}