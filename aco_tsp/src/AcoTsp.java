import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.Viewer;

public class AcoTsp {

    public static void main(String args[]) {

        Graph graph = new SingleGraph("ACO TSP");
        TourManager tourManager = new TourManager();

        City city = new City(60, 200, 0);
        tourManager.addCity(city);
        City city2 = new City(180, 200, 1);
        tourManager.addCity(city2);
        City city3 = new City(80, 180, 2);
        tourManager.addCity(city3);
        City city4 = new City(140, 180, 3);
        tourManager.addCity(city4);
        City city5 = new City(20, 160, 4);
        tourManager.addCity(city5);
        City city6 = new City(100, 160, 5);
        tourManager.addCity(city6);
        City city7 = new City(200, 160, 6);
        tourManager.addCity(city7);
        City city8 = new City(140, 140, 7);
        tourManager.addCity(city8);
        City city9 = new City(40, 120, 8);
        tourManager.addCity(city9);
        City city10 = new City(100, 120, 9);
        tourManager.addCity(city10);
        City city11 = new City(180, 100, 10);
        tourManager.addCity(city11);
        City city12 = new City(60, 80, 11);
        tourManager.addCity(city12);
        City city13 = new City(120, 80, 12);
        tourManager.addCity(city13);
        City city14 = new City(180, 60, 13);
        tourManager.addCity(city14);
        City city15 = new City(20, 40, 14);
        tourManager.addCity(city15);
        City city16 = new City(100, 40, 15);
        tourManager.addCity(city16);
        City city17 = new City(200, 40, 16);
        tourManager.addCity(city17);
        City city18 = new City(20, 20, 17);
        tourManager.addCity(city18);
        City city19 = new City(60, 20, 18);
        tourManager.addCity(city19);
        City city20 = new City(160, 20, 19);
        tourManager.addCity(city20);

        for (int j = 0; j < TourManager.numberOfCities(); j++) {
            graph.addNode(Integer.toString(j)).setAttribute("xy", TourManager.getCity(j).getX(), TourManager.getCity(j).getY());
        }

        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antiallias");
        graph.addAttribute("ui.stylesheet", "url('C:/users/tsou/aco_tsp/src/ui.css')");
        for (Node node : graph.getEachNode()) {
            node.addAttribute("layout.frozen");
            node.addAttribute("ui.label", node.getId());
        }

        int k = 0;
        for(Node node1: graph.getEachNode())
        {
            for(Node node2: graph.getEachNode())
            {
                if(node1 != node2 && !node2.hasEdgeBetween(node1)) {
                    graph.addEdge(k + "", node1, node2);
                    k++;
                }
            }
        }




        Viewer viewer = graph.display();

        try{
            Thread.sleep(2500);
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        double PhMap[][];
        ACO aco = new ACO(tourManager);
        aco.initializePhMap();
        for (int i = 0; i < 150;i++) {
            Population pop = new Population(tourManager);
            pop.setupAnts(20);

            aco.setPopulation(pop);
            aco.moveAnts();
            aco.updatePherormone(pop.getAnts().length/4);
            PhMap = aco.getPherormoneMap();
            //resetEdge(graph);
            MapToEdge(PhMap,graph);

            try{
                Thread.sleep(120);
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }

        }
        PhMap = aco.getPherormoneMap();
        Tour min = aco.minTour;
        double mindist = aco.minDist;
    }

    private static void MapToEdge(double[][] PhMap,Graph graph)
    {
        double max = max(PhMap);
        //System.out.println(Double.toString(max));

        for(Node n1: graph.getEachNode())
        {
            for(Node n2 : graph.getEachNode())
            {
                double value = PhMap[Integer.parseInt(n1.getId())][Integer.parseInt(n2.getId())];
                double width = 1 + (9 /(max)) * value;

                if (n2.hasEdgeBetween(n1)) {
                    Edge e = n2.getEdgeBetween(n1);
                    e.setAttribute("ui.class", intToString((int) width));
                }
                if (n1.hasEdgeBetween(n2)) {
                    Edge e = n1.getEdgeBetween(n2);
                    e.setAttribute("ui.class", intToString((int) width));
                }

            }
        }
    }

    private static void resetEdge(Graph graph)
    {
        for(Node n1: graph.getEachNode()) {
            for (Node n2 : graph.getEachNode()) {
                if (n2.hasEdgeBetween(n1)) {
                    Edge e = n2.getEdgeBetween(n1);
                    e.setAttribute("ui.class", "one");
                }
                if (n1.hasEdgeBetween(n2)) {
                    Edge e = n1.getEdgeBetween(n2);
                    e.setAttribute("ui.class", "one");
                }
            }
        }
    }

    private static double max(double[][] matrix) {
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

    private static String intToString(int value)
    {
        String out = "";
        switch (value)
        {
            case 1:
                out = "one";
                break;
            case 2:
                out = "two";
                break;
            case 3:
                out = "three";
                break;
            case 4:
                out = "four";
                break;
            case 5:
                out = "five";
                break;
            case 6:
                out = "six";
                break;
            case 7:
                out = "seven";
                break;
            case 8:
                out = "eight";
                break;
            case 9:
                out = "nine";
                break;
            case 10:
                out = "ten";
                break;
            default:
                out = "ten";

        }
        return out;
    }

}
