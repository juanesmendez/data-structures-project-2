package utils;
public class Utils {

	public static double getDistance (double lat1, double lon1, double lat2, double lon2)
	{
		// TODO Auto-generated method stub
		final int R = 6371*1000; // Radious of the earth in meters
		Double latDistance = Math.toRadians(lat2-lat1);
		Double lonDistance = Math.toRadians(lon2-lon1);
		Double a = Math.sin(latDistance/2) * Math.sin(latDistance/2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance/2) * Math.sin(lonDistance/2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		Double distance = R * c;
		return distance;
	}
	
}
