import java.util.ArrayList;
import java.util.Collections;


public class Tour {

	
	private ArrayList tour = new ArrayList<City>();
	
	private double fitness = 0;
	private int distance = 0;
	
	//blank tour
	public Tour(TourManager tourManager)
	{
		for(int i = 0; i < tourManager.numberOfCities();i++)
		{
			tour.add(null);
		}
	}
	
	public Tour(ArrayList tour)
	{
		this.tour = tour;
	}
	
	public void generateIndividual()
	{
		for(int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++)
		{
			setCity(cityIndex, TourManager.getCity(cityIndex));
		}
		
		  Collections.shuffle(tour);
    }
	
	 public City getCity(int tourPosition) {
	        return (City)tour.get(tourPosition);
	    }
	
	 public void setCity(int tourPosition, City city) {
	        tour.set(tourPosition, city);
	        // If the tours been altered we need to reset the fitness and distance
	        fitness = 0;
	        distance = 0;
	    }
	 
	 public double getFitness() {
	        if (fitness == 0) {
	            fitness = 1/(double)getDistance();
	        }
	        return fitness;
	    }
	 
	 public double getDistance(){
	        if (distance == 0) {
	            int tourDistance = 0;
	            // Loop through our tour's cities
	            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
	                // Get city we're travelling from
	                City fromCity = getCity(cityIndex);
	                // City we're travelling to
	                City destinationCity;
	                // Check we're not on our tour's last city, if we are set our
	                // tour's final destination city to our starting city
	                if(cityIndex+1 < tourSize()){
	                    destinationCity = getCity(cityIndex+1);
	                }
	                else{
	                    destinationCity = getCity(0);
	                }
	                // Get the distance between the two cities
	                tourDistance += fromCity.distanceTo(destinationCity);
	            }
	            distance = tourDistance;
	        }
	        return distance;
	    }

	 
	 public int tourSize() {
	        return tour.size();
	    }
	 
	 public boolean containsCity(City city){
	        return tour.contains(city);
	    }
	 
	 public String toString() {
	        String geneString = "|";
	        for (int i = 0; i < tourSize(); i++) {
	            geneString += getCity(i)+" | ";
	        }
	        return geneString;
	    }
	 
	 
		 
	 
	 
}
