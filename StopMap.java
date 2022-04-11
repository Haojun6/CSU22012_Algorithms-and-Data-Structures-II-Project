import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StopMap
{
	
    Map<String, Integer> stops;
    
    Map<Integer, Integer> stopIndexes;
    Map<Integer, String> indexToName;
    ArrayList<Edge>[] edge;
    int number;
    Dijkstra dij;
    
    public StopMap(String stopsFile, String stopTimesFile, String transfersFile) throws IOException
    {
        stops = new HashMap<String, Integer>();
        stopIndexes = new HashMap<Integer, Integer>();
        indexToName = new HashMap<Integer, String>();
        try
        {      
	        BufferedReader reader = new BufferedReader(new FileReader(stopsFile));
	        reader.readLine();
	        String line = null;
	        String[] splitLine = null;
	        int vertex = 0;
	        int ID = 0;
	        String name = null;
	        while((line = reader.readLine()) != null)
	        {
	            splitLine = line.split(",");
	            name = splitLine[2];
	            ID = Integer.parseInt(splitLine[0]);
	            stops.put(name, ID);
	            stopIndexes.put(ID, vertex);
	            indexToName.put(vertex, name);
	            vertex++;
	            
	        }
	        reader.close();
	        number = vertex;
	        edge = new ArrayList[number];
	        for(int i = 0; i < number; i++) 
	        	edge[i] = new ArrayList<Edge>();
	
	       
	        int fromID = 0;
	        int toID = 0;
	        int fromIndex = 0;
	        int toIndex = 0;
	        double weight = 0;
	
	        reader = new BufferedReader(new FileReader(transfersFile));
	        //The first line in the file simply contains the order of the data, which we can ignore.
	        reader.readLine();
	        while((line = reader.readLine()) != null)
	        {
	            splitLine = line.split(",");
	            fromID = Integer.parseInt(splitLine[0]);
	            toID = Integer.parseInt(splitLine[1]);
	            fromIndex = stopIndexes.get(fromID);
	            toIndex = stopIndexes.get(toID);
	            weight = (Integer.parseInt(splitLine[2]) == 0) ? 2.0 : Double.parseDouble(splitLine[3])/100;
	            edge[fromIndex].add(new Edge(fromIndex, toIndex, weight));
	           
	        }
	        reader.close();
	        reader = new BufferedReader(new FileReader(stopTimesFile));
	        reader.readLine();
	        int lastTripID = -1;
	        int currentTripID = 0;
	        while((line = reader.readLine()) != null)
	        {
	            splitLine = line.split(",");
	            try
	            {
	                LocalTime.parse(splitLine[1].replaceAll("\\s+", "0"), DateTimeFormatter.ofPattern("H:mm:ss"));
	                LocalTime.parse(splitLine[2].replaceAll("\\s+", "0"), DateTimeFormatter.ofPattern("H:mm:ss"));
	            }
	            catch (Exception x) 
	            {
	            	continue;
	            }
	            
	            currentTripID = Integer.parseInt(splitLine[0]);
	            toID = Integer.parseInt(splitLine[3]);
	            if(currentTripID == lastTripID)
	            {
	                fromIndex = stopIndexes.get(fromID);
	                toIndex = stopIndexes.get(toID);
	                edge[fromIndex].add(new Edge(fromIndex,toIndex, 1.0));
	            }
	            lastTripID = currentTripID;
	            fromID = toID;
	        }
	        reader.close();
        }
        catch (Exception IllegalArgumentException) 
        {
        	System.out.println("Error: IllegalArgumentException");
        }
    }

    public Double getCost(String destinationStop)
    {
        if(stops.containsKey(destinationStop))
        {
            int stopID = stops.get(destinationStop);
            return dij.distTo(stopIndexes.get(stopID));
        }
        else throw new IllegalArgumentException();
    }

    public void getStops(String destinationStop, double cost)
    {
        try
	    {
        	if(stops.containsKey(destinationStop))
	        {
	            int stopID = stops.get(destinationStop);
	            ArrayList<Integer> vertexPath = dij.getPath(stopIndexes.get(stopID));
	            if (vertexPath.size() > 0)
	            {
	                ArrayList<String> stopPath = new ArrayList<String>();
	                int vertex = 0;
	                String name = null;
	                
	                for (int i = vertexPath.size() - 1; i >= 0; i--)
	                {
	                    vertex = vertexPath.get(i);
	                    name = indexToName.get(vertex);
	                    stopPath.add(name);
	                }
	                
	                System.out.println(String.join("", Collections.nCopies(30,"*")) + " SHORTEST-ROUTE " + String.join("", Collections.nCopies(30,"*")));
	                for(int i = 0; i < stopPath.size(); i++)
	                {
	                	System.out.println("["+ (i+1) + "] " + stopPath.get(i));
	                }
	                System.out.println(String.join("", Collections.nCopies(80,"*")));
	                System.out.println("\n" + String.join("", Collections.nCopies(2," ")) + "With a cost of " + cost  + ", the shortest route between those two bus stops is as above:" + String.join("", Collections.nCopies(2," ")) + "\n");
	            }
	        }
        } 
        catch (Exception IllegalArgumentException) 
        {
        	System.out.println("Error: IllegalArgumentException");
        }
    }

    public void makePaths(String sourceStop)
    {
        try
        {
        	if(stops.containsKey(sourceStop))
        	{
        		int stopID = stops.get(sourceStop);
        		dij = new Dijkstra(this, stopIndexes.get(stopID));
        	}
        } 
        catch (Exception IllegalArgumentException) 
        {
        	System.out.println("Error: IllegalArgumentException");
        }
    }
    
   
    public int number()
    {
    	return number; 
    }

    public ArrayList<Edge> adjacent(int vertex)
    { 
    	return edge[vertex]; 
    }
}