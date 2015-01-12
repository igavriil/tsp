import java.util.ArrayList;


public class TourManager {

	
	@SuppressWarnings("rawtypes")
	private static ArrayList destinationCities = new ArrayList<City>();
	
	@SuppressWarnings("unchecked")
	public static void addCity(City city)
	{
		destinationCities.add(city);
	}
	
	
	public static City getCity(int index)
	{
		return (City)destinationCities.get(index);
	}
	
	public static int numberOfCities()
	{
		return destinationCities.size();
	}
}
