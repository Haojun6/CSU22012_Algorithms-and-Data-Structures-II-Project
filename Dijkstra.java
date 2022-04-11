import java.util.ArrayList;

public class Dijkstra
{
	StopMap graph;
    Double[] distTo;
    Integer[] edgeTo;

    public Dijkstra(StopMap graph, int number)
    {
        this.graph = graph;
        boolean[] visited = new boolean[graph.number()];
        int i = 0;
        while(i < graph.number())
        {
            visited[i] = false;
            i++;
        }

        distTo = new Double[graph.number()];
        i = 0;
        while(i < graph.number())
        {
            distTo[i] = null;
            i++;
        }

        edgeTo = new Integer[graph.number()];
        i = 0;
        while(i < graph.number())
        {
            edgeTo[i] = null;
            i++;
        }

        PriorityQueue verticesToRelax = new PriorityQueue(3);
        distTo[number] = 0.0;
        verticesToRelax.insert(distTo[number], number);
        int currentnumberertex;

        while(!verticesToRelax.isEmpty())
        {
            currentnumberertex = verticesToRelax.nextVertex();
            if(!visited[currentnumberertex])
            {
                visited[currentnumberertex] = true;
                for(Object o : graph.adjacent(currentnumberertex))
                {
                    Edge e = (Edge) o;
                   
                    if(distTo[e.to()] != null)
                    {
                        if((e.weight() + distTo[currentnumberertex]) < distTo[e.to()])
                        {
                            distTo[e.to()] = e.weight() + distTo[currentnumberertex];
                            edgeTo[e.to()] = currentnumberertex;
                        }
                    }
                    else
                    {
                        distTo[e.to()] = e.weight() + distTo[currentnumberertex];
                        edgeTo[e.to()] = currentnumberertex;
                    }
                   
                    verticesToRelax.insert(distTo[e.to()], e.to());
                }
            }
        }
    }
    
   
    public Double distTo(int number)
    { 
    	return distTo[number];
    }

   
    public ArrayList<Integer> getPath(int number)
    {
        ArrayList<Integer> path = new ArrayList<Integer>();
        int vertex = number;
        path.add(vertex);
        while(edgeTo[vertex] != null)
        {
        	path.add(edgeTo[vertex]);
            vertex = edgeTo[vertex];
        }
        return path;
    }
}