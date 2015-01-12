import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.Viewer;


public class TSP_GA {


	    public static void main(String[] args) {

	    	Graph graph = new SingleGraph("Genetic TSP");

	        // Create and add our cities
	        City city = new City(60, 200,0);
	        TourManager.addCity(city);
	        City city2 = new City(180, 200,1);
	        TourManager.addCity(city2);
	        City city3 = new City(80, 180,2);
	        TourManager.addCity(city3);
	        City city4 = new City(140, 180,3);
	        TourManager.addCity(city4);
	        City city5 = new City(20, 160,4);
	        TourManager.addCity(city5);
	        City city6 = new City(100, 160,5);
	        TourManager.addCity(city6);
	        City city7 = new City(200, 160,6);
	        TourManager.addCity(city7);
	        City city8 = new City(140, 140,7);
	        TourManager.addCity(city8);
	        City city9 = new City(40, 120,8);
	        TourManager.addCity(city9);
	        City city10 = new City(100, 120,9);
	        TourManager.addCity(city10);
	        City city11 = new City(180, 100,10);
	        TourManager.addCity(city11);
	        City city12 = new City(60, 80,11);
	        TourManager.addCity(city12);
	        City city13 = new City(120, 80,12);
	        TourManager.addCity(city13);
	        City city14 = new City(180, 60,13);
	        TourManager.addCity(city14);
	        City city15 = new City(20, 40,14);
	        TourManager.addCity(city15);
	        City city16 = new City(100, 40,15);
	        TourManager.addCity(city16);
	        City city17 = new City(200, 40,16);
	        TourManager.addCity(city17);
	        City city18 = new City(20, 20,17);
	        TourManager.addCity(city18);
	        City city19 = new City(60, 20,18);
	        TourManager.addCity(city19);
	        City city20 = new City(160, 20,19);
	        TourManager.addCity(city20);

	        
	        //create graph
	        
	        for(int j =0;j < TourManager.numberOfCities(); j++)
	        {
	        	graph.addNode(Integer.toString(j)).setAttribute("xy",TourManager.getCity(j).getX(),TourManager.getCity(j).getY());
	        }
	        
	        graph.addAttribute("ui.quality");
	        graph.addAttribute("ui.antiallias");
	        graph.addAttribute("ui.stylesheet", "url('C:/Users/tsou/tsp_gen/src/ui.css')");
	        for(Node node: graph.getEachNode())
	        {
	        	node.addAttribute("layout.frozen");
	        	node.addAttribute("ui.label", node.getId());
	        }

			Viewer viewer = graph.display();

            try{
                    Thread.sleep(2500);
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }
	        //graph creation

	        // Initialize population
	        Population pop = new Population(700, true);
	        System.out.println("Initial distance: " + pop.getFittest().getDistance());

	        // Evolve population foreach generation
	        pop = GA.evolvePopulation(pop);
	        for (int j = 0; j < 5000; j++) {
	            pop = GA.evolvePopulation(pop);

	            Tour bestTour = pop.getFittest();

	            //sleeping for a while
	            try{
                    if(j<100)
	            	    Thread.sleep(50);
                    else
                        Thread.sleep(0);
	            }catch(InterruptedException e)
	            {
	            	e.printStackTrace();
	            }
	           //

	            //delete all edges to visualize the next step
	            for(Node node1: graph.getEachNode())
	            {
	            	for(Node node2: graph.getEachNode())
	            	{
	            		if(node2.hasEdgeBetween(node1))
	            		{
	            			Edge e = node2.getEdgeBetween(node1);
	            			graph.removeEdge(e);
	            		}
	            	}
	            }
	            
	            //make the edges
		        for(int i = 0; i < bestTour.tourSize(); i++)
		        {
		        	Node startNode = graph.getNode(bestTour.getCity(i).getId());
		        	Node endNode = graph.getNode(bestTour.getCity((i+1)%(bestTour.tourSize())).getId());
		        	graph.addEdge(Integer.toString(i), startNode, endNode, false);
		        }
	        }

	        // Print final results
	        System.out.println("Finished");
	        System.out.println("Final distance: " + pop.getFittest().getDistance());
	        System.out.println("Solution:");
	        System.out.println(pop.getFittest());
	    }
}


