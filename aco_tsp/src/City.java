
public class City {

	int x;
	int y;
	int id;
	
	public City()
	{
		this.x = (int)(Math.random()*200);
		this.y = (int)(Math.random()*200);
	}
	
	public City(int x, int y,int id ){
        this.x = x;
        this.y = y;
        this.id = id;
    }
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public double distanceTo(City city)
	{
		double xDistance = Math.abs(getX() - city.getX());
        double yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        
        return distance;
    }
	
	public int getId()
	{
		return this.id;
	}
	
	public String toString()
	{
		return getId()+"";
		//return getX()+","+getY();
	}
}
