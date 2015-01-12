
public class Ant {
	
	private Tour tour;
	private int index;
	
	
	public Ant(int index,TourManager tourManager)
	{
		this.setIndex(index);
		this.tour = new Tour(tourManager);
	}
	
	public void setStartingCity(City city)
	{
		this.tour.setCity(0, city);
   	}
	
	public void visitCity(City city)
	{
		int i = 1;
		while(this.tour.getCity(i) != null)
		{
			i++;
		}
		this.tour.setCity(i,city);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public Tour getTour(){
		return this.tour;
	}
	
	public City getLastCity()
	{
		int i = 0;
		
		while(this.tour.getCity(i)!=null && this.tour.getCity(i+1)!=null)
		{
			i++;
		}
		return this.tour.getCity(i);
		
	}
	

}
