public class PriorityQueue
{
    Nodes[] arr;
    int size;

    public PriorityQueue(int capacity)
    {
        arr = new Nodes[capacity+1];
        for(int i = 0; i < arr.length; i++)
        {
            arr[i] = null;
        }
        size = 0;
    }

  
    public void insert(double d, int v)
    {
        if(++size >= arr.length) 
        {
        	larger();
        }
        arr[size] = new Nodes(d, v);
        jump(size);
    }

    public int nextVertex()
    {
        int nextV = arr[1].vertex;
        arr[1] = arr[size--];
        arr[size+1] = null;
        sink(1);
        if(size <= arr.length/4 && arr.length > 4) 
        {
        	smaller();
        }
        return nextV;
    }

    public boolean isEmpty()
    { 
    	return size == 0;
    }

    
    public void jump(int k)
    {
        Nodes temp;
        while(k > 1)
        {
            if(arr[k/2].dist < arr[k].dist) 
            {
            	break;
            }
            temp = arr[k/2];
            arr[k/2] = arr[k];
            arr[k] = temp;
            k = k/2;
        }
    }

    public void sink(int k)
    {
        Nodes temp;
        while(2*k <= size)
        {
            int j = 2*k;
            if(j < size && arr[j].dist > arr[j+1].dist) 
            	j++;
            if(arr[k].dist < arr[j].dist) 
            	break;
            temp = arr[j];
            arr[j] = arr[k];
            arr[k] = temp;
            k = j;
        }
    }

    public void larger()
    {
        Nodes[] newArr = new Nodes[arr.length*2];
        for(int i = 0; i < newArr.length; i++)
        {
            if(i < arr.length) 
            {
            	newArr[i] = arr[i];
            }
            else 
            {
            	newArr[i] = null;
            }
        }
        arr = newArr;
    }

    
    public void smaller()
    {
        Nodes[] newArr = new Nodes[arr.length/2];
        for(int i = 0; i < newArr.length; i++)
        {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }
}