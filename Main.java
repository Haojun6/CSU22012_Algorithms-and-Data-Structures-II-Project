import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	ReadFiles rf = new ReadFiles("stop_times.txt", "stops.txt", "transfers.txt");
	public static ArrayList<SettingStops> stops = new ArrayList<SettingStops>();
    
    public static void main(String[] args) throws IOException 
    {
    	
    	System.out.println("****************************************************************************");
    	System.out.println(" Welcome\n "
    						+"  	    To\n"
    						+"		 Vancouver\n"
    						+"				Bus\n"
    						+"					Management\n"
    						+"							System\n");
    	System.out.println("****************************************************************************"+ "\n\n");
        Scanner scanner = new Scanner(System.in);
        Scanner input = new Scanner (System.in);
        StopMap stopMap = null;
        boolean part1Inital = true;   
    	boolean quit = false;
    	while(!quit)
    	{
	    	System.out.println("---------------------------------------------------------------------------");
	    	System.out.println(" Please input part1, paart2 or part3 that you want to use. ");
	    	System.out.println("---------------------------------------------------------------------------");
	    	if (input.hasNext("part1") || input.hasNext("PART1") || input.hasNext("Part1") || input.hasNext("1"))
	    	{
	    		System.out.println("---------------------------------------------------------------------------");
		    	System.out.println(" Loading... The program is almost there... ");
		    	System.out.println("---------------------------------------------------------------------------");
	    		boolean part1 = true;
                if(part1Inital)
                {
                    stopMap = new StopMap("stops.txt", "stop_times.txt", "transfers.txt");
                    part1Inital = false;
                }
                SettingStops searchStops = new SettingStops("stops.txt");
                while(part1)
                {   
                	System.out.println("\n---------------------------------------------------------------------------");
                    System.out.println(" Please type in the first stop you wanna search: ");
                    System.out.println("---------------------------------------------------------------------------");
                    String firstStop = scanner.next();
                    firstStop += scanner.nextLine();
                    ArrayList<String> results = searchStops.isValid(firstStop);
                    if(results != null)
                    {
                        String stopOne = searchStops.getStop(scanner, results);
                        System.out.println("\n---------------------------------------------------------------------------");
                        System.out.println(" Please type in the second stop you wanna search: ");
                        System.out.println("---------------------------------------------------------------------------");
                        String secondStop = scanner.next();
                        secondStop += scanner.nextLine();
                        results = searchStops.isValid(secondStop);
                        if(results != null)
                        {
                            String stopTwo = searchStops.getStop(scanner, results);
                            try
                            {
                                stopMap.makePaths(stopOne);
                                Double cost = stopMap.getCost(stopTwo);
                                if(cost != null)
                                    stopMap.getStops(stopTwo, cost);
                                else
                                {   
                                	System.out.println("\n---------------------------------------------------------------------------");
                                    System.out.println(" Opps...Seems like no routes between these two stops");
                                    System.out.println("---------------------------------------------------------------------------");
                                }
                            }
                            catch(IllegalArgumentException e)
                            {   
                            	System.out.println("\n---------------------------------------------------------------------------");
                                System.out.println(" Opps...There're no stops searched");
                                System.out.println("---------------------------------------------------------------------------");
                            }
                        }
                        else
                        {   
                        	System.out.println("\n---------------------------------------------------------------------------");
                            System.out.println(" Opps...There're no stops searched");
                            System.out.println("---------------------------------------------------------------------------");
                        }
                    }
                    else
                    {   System.out.println("\n---------------------------------------------------------------------------");
                        System.out.println(" Opps...There're no stops searched");
                        System.out.println("---------------------------------------------------------------------------");
                    }
                    part1 = continueOrNot(scanner);
                }
            break;
	    	}
	    	
	    	else if (input.hasNext("part2") || input.hasNext("PART2") || input.hasNext("Part2") || input.hasNext("2"))
	    	{
	    		boolean part2 = true;
                while (part2)
                {  
                	System.out.println("\n---------------------------------------------------------------------------");
                    System.out.println(" Please type in the bus stop that you wanna search");
                    System.out.println("---------------------------------------------------------------------------");
                    String part2Input = scanner.next();
                    part2Input += scanner.nextLine();
                    SettingStops tst2 = new SettingStops("stops.txt");
                    int returnValue = tst2.tst.get(part2Input);
                    if (returnValue < 0)
                    {
                    	System.out.println("\n---------------------------------------------------------------------------");
                        System.out.println(" Opps...There're no results searched");
                        System.out.println("---------------------------------------------------------------------------");
                    }
                    else
                    {
                    	SettingStops.printStopNamesMatchingCriteria(tst2);
                    }
                    part2 = continueOrNot(scanner);
                }
            break;
	    	}
	    	
	    	/**else if (input.hasNext("part3") || input.hasNext("PART3") || input.hasNext("Part3") || input.hasNext("3"))
	    	{
	    		boolean part3 = true;
               
                if (!part3Inital)
                {
                    
                }
                while (part3)
                {
                    
                }
            break;
            
	    	}*/
	    	else if (input.hasNext("quit") || input.hasNext("Quit") || input.hasNext("QUIT") || input.hasNext("q"))
	    	{
	    		quit = true;
	    	}
	    	else
	    	{
	    		System.out.println("---------------------------------------------------------------------------");
	    		System.out.println(" Invalid Input: Please type in a valid content: eg part1, PART2, 3, quit");
	    		System.out.println("---------------------------------------------------------------------------\n");
	    	}
    	}
    }
    
    
    
    
    
    public static boolean continueOrNot(Scanner scanner)
    {
        boolean goOn = true;
        boolean endThisFunc = true;
        while (endThisFunc)
        {
        	System.out.println("\n---------------------------------------------------------------------------");
            System.out.println(" Do you expect to have another search ? [Y/N]: ");
            System.out.println("---------------------------------------------------------------------------");
            String theReply = scanner.next();
            theReply += scanner.nextLine();
            if (theReply.equalsIgnoreCase("N") || theReply.equalsIgnoreCase("no"))
            {
            	endThisFunc = false;
            	goOn = false;
            }
            else if (theReply.equalsIgnoreCase("Y") || theReply.equalsIgnoreCase("yes"))
            	endThisFunc = false;
            else
            {   System.out.println("\n---------------------------------------------------------------------------");
                System.out.println(" Invalid Input: Please only input Yes (Y) or No (N)");
                System.out.println("---------------------------------------------------------------------------");
            }
        }
        return goOn;
    }
    
}
