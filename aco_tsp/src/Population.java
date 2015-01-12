import java.util.Arrays;
import java.util.Random;


public class Population {

	private Ant[] ants;
	private TourManager tourManager;
	private Random rand = new Random();
	
	public Population(TourManager tourManager)
	{
		this.tourManager = tourManager;
		
	}
	
	public Ant[] getAnts()
	{
		return ants;
	}

    public Ant[] bestAnts()
    {
        for(int i = 0 ; i < this.getAnts().length ; i++)
        {
            for(int j = i; j <this.getAnts().length; j++)
            {
                if(this.getAnts()[i].getTour().getDistance()>this.getAnts()[j].getTour().getDistance())
                {
                    Ant temp = this.getAnts()[i];
                    this.getAnts()[i] = this.getAnts()[j];
                    this.getAnts()[j] = temp;
                }
            }
        }
        return this.getAnts();
    }
	
	public void setupAnts(int populationSize)
	{
		ants = new Ant[populationSize];
		for(int i=0;i < populationSize ; i++)
		{
			ants[i] = new Ant(i,tourManager);
			setRandomCity(ants[i]);
		}
	}
	
	private void setRandomCity(Ant ant)
	{
		int numberOfCities = tourManager.numberOfCities();
		City startingCity = tourManager.getCity(rand.nextInt(numberOfCities));
		ant.setStartingCity(startingCity);
	}
}
