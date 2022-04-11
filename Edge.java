public class Edge
{
    int n;
    int m;
    double weight;

    public Edge(int v, int w, double weight)
    {
        n = v;
        m = w;
        this.weight = weight;
    }

    public double weight()
    {
        return this.weight;
    }

    public int from()
    { 
    	return n; 
    }

    public int to()
    { 
    	return m; 
    }
}