import scala.Int;

import java.util.ArrayList;
import java.util.Random;


public class ACO {

	private Population population;
	private Random rand = new Random();
	private double alpha = 0.21;
	private double beta = 5.5;
	private double evaporation = 0.55;
	private double Q = 210;
    public double minDist = 2000;
    public Tour minTour;

    public double[][] getPherormoneMap() {
        return pherormoneMap;
    }

    private double pherormoneMap[][];
	private int numberOfCities;
	private TourManager tourManager;
	private double pr = 0.01;
	
	public ACO(TourManager tourManager)
	{
        this.minTour = new Tour(tourManager);
		this.tourManager = tourManager;
		this.numberOfCities = tourManager.numberOfCities();
		this.pherormoneMap = new double[numberOfCities][numberOfCities];
	
		
	}
	
	public double[] calculateProbabilities(Ant ant)
	{
		double prob[] = new double[numberOfCities];  
		City lastCity = ant.getLastCity();
		
		//calculate denominator
		double denom = 0.0;
		
		for(int i = 0; i < numberOfCities ; i++)
		{
			if(!ant.getTour().containsCity(tourManager.getCity(i)))
			{
				denom += Math.pow(pherormoneMap[lastCity.getId()][i], alpha)*(Math.pow((1/(lastCity.distanceTo(tourManager.getCity(i)))), beta));
			}
		}
		
		//double test = 0.0;
		for(int i = 0; i < numberOfCities ; i++)
		{
			if(!ant.getTour().containsCity(tourManager.getCity(i)))
			{
				double numerator = Math.pow(pherormoneMap[lastCity.getId()][i], alpha)
						*Math.pow(1/(lastCity.distanceTo(tourManager.getCity(i))), beta);
				prob[i] = numerator/denom;
				
				//test += prob[i];
			}
			else
			{
				prob[i] = 0;
			}
		}
		return prob;
	}
	
	
	public void setPopulation(Population pop)
	{
		this.population = pop;
	}
	
	public void initializePhMap()
	{
		for(int i = 0;i <numberOfCities;i++)
		{
			for(int j=0;j < numberOfCities; j++)
			{
				pherormoneMap[i][j]=1.0;
			}
		}
	}

	
	public City selectNextCity(Ant ant,double[] prob)
	{
		City city = null;
	
		
		if(rand.nextDouble() < pr)
		{
			ArrayList notVis = notVisited(prob);
			int size = notVis.size();
			int index = rand.nextInt(size);
			city = tourManager.getCity((Integer)notVis.get(index));
		}
		
		else
		{
			double r = rand.nextDouble();
			double tot = 0;
			for(int i=0; i<numberOfCities;i++ )
			{
				tot +=prob[i];
				if(tot >= r)
				{
					city = tourManager.getCity(i);
					break;
				}
			}
		}
		
		return city;
	}
	
	public ArrayList<Integer> notVisited(double[] probs)
	{
		ArrayList<Integer> notVis = new ArrayList<Integer>();
		for(int i = 0; i <numberOfCities;i++)
		{
			if(probs[i]!=0)
			{
				notVis.add(i);
			}
		}
		return notVis;
	}
	
	
	public void moveAnts()
	{

		for(Ant a: population.getAnts())
		{
			for(int i = 0; i < numberOfCities-1; i++)
			{
				double probs[] = calculateProbabilities(a);
				City city =  selectNextCity(a,probs);
				a.visitCity(city);
			}
            double distance = a.getTour().getDistance();
            if(distance < minDist)
            {
                minDist = distance;
                minTour = a.getTour();
            }
            a.getTour().getFitness();
            System.out.println(a.getTour());

		}
	}

    public void updatePherormone(int elitismCount)
    {
        for(int i = 0;i <numberOfCities;i++)
        {
            for(int j=0;j < numberOfCities; j++)
            {
                pherormoneMap[i][j]*=evaporation;
            }
        }

        int counter = 0;
        for (Ant a : population.bestAnts()) {
            counter ++ ;
            if(counter == elitismCount)
                break;
            //System.out.println(a.getTour().getDistance());
            double contribution = Q / a.getTour().getDistance();
            double maxPherormone = max(pherormoneMap);
            for (int i = 0; i < numberOfCities - 1; i++) {
                pherormoneMap[a.getTour().getCity(i).getId()][a.getTour().getCity(i+1).getId()] += (contribution);
                pherormoneMap[a.getTour().getCity(i).getId()][a.getTour().getCity(i+1).getId()]/= maxPherormone ;
            }
            pherormoneMap[a.getTour().getCity(numberOfCities - 1).getId()][a.getTour().getCity(0).getId()] += (contribution);
            pherormoneMap[a.getTour().getCity(numberOfCities - 1).getId()][a.getTour().getCity(0).getId()] /= maxPherormone;
        }
    }

    private  double max(double[][] matrix) {
        double max = matrix[0][0];
        for (int col = 0; col < matrix.length; col++) {
            for (int row = 0; row < matrix[col].length; row++) {
                if (max < matrix[col][row]) {
                    max = matrix[col][row];
                }
            }
        }
        return max;
    }
}
