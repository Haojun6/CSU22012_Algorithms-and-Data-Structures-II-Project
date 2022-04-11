import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.*;

public class SettingStops
{
    TST tst;
    String namesFile;
    public SettingStops(String filename)
    {
    	tst = new TST();
        namesFile = filename;
        int lineID = 2;
        TST.allNames.clear();
        try
        {
        	BufferedReader reader = new BufferedReader(new FileReader(filename));
	        reader.readLine();
	        String thisLine;
	        while ((thisLine = reader.readLine()) != null)
	        {
	            String[] tokens = thisLine.split(",");
	            String stopNameUnformatted = tokens[2];
	            String[] test = stopNameUnformatted.split(" ");
	            List<String> t = Arrays.asList(test);
	            LinkedList<String> temp = new LinkedList<>(t);
	            while (temp.get(0).equals("NB") || temp.get(0).equals("SB") ||
	                    temp.get(0).equals("WB") || temp.get(0).equals("EB") ||
	                    temp.get(0).equals("FLAGSTOP"))
	            {
	                String s = temp.remove(0);
	                temp.add(s);
	            }
	            String stopNameFormatted = temp.toString();
	            stopNameFormatted = stopNameFormatted.replaceAll("\\p{P}", "");
	            tst.put(stopNameFormatted, lineID);
	            lineID++;
	        }
        }
        catch(Exception e) 
        {
        	System.out.println("\n---------------------------------------------------------------------------");
            System.out.println(" Opps...There're no files searched");
            System.out.println("---------------------------------------------------------------------------");
        };
    }

    
    public ArrayList<String> isValid(String query)
    {
        int returnValue = tst.get(query);
        if(returnValue < 0)
        	return null;
        else
        {
            ArrayList<String> results = new ArrayList<>();
            for (int i = 0; i < TST.allNames.size(); i++)
            {
                String output;
                int lineNumber = TST.allNames.get(i);
                try
                {
                	Stream<String> lines = Files.lines(Paths.get(namesFile));
                
                    output = lines.skip(lineNumber - 1).findFirst().get(); 
                    String[] outTokens = output.split(",");
                    results.add(outTokens[2]);
                }
                catch(Exception IOException) 
                {
                	System.out.println("\n---------------------------------------------------------------------------");
                    System.out.println(" Opps...There're no files searched");
                    System.out.println("---------------------------------------------------------------------------");
                };
            }
            TST.allNames.clear();
            return results;
        }
    }
    
   
    public static void printStopNamesMatchingCriteria(SettingStops tst) 
    {
        System.out.println(String.join("", Collections.nCopies(30,"*")) + " SEARCH-RESULTS " + String.join("", Collections.nCopies(30,"*")));
        int i = 0;
        while(i <= TST.allNames.size() - 1)
        {
            String output;
            int lineNumber = TST.allNames.get(i);
            if(i != 0)
                System.out.println(String.join("", Collections.nCopies(80,"*")));
            System.out.println(String.join("", Collections.nCopies(30," ")) + "Matching stop #" + (i+1));
            try 
            {
            	Stream<String> lines = Files.lines(Paths.get(tst.namesFile));
                output = lines.skip(lineNumber - 1).findFirst().get(); 
                String[] outTokens = output.split(",");
                for(int j = 0; j < outTokens.length; j++)
                {
                    if(outTokens[j].equals(" "))
                        outTokens[j] = "null";
                }
                System.out.println("[+] ID: " + outTokens[0]);
                System.out.println("[+] Code: " + outTokens[1]);
                System.out.println("[+] Name: " + outTokens[2]);
                System.out.println("[+] Description: " + outTokens[3]);
                System.out.println("[+] Latitude: " + outTokens[4]);
                System.out.println("[+] Longitude: " + outTokens[5]);
                System.out.println("[+] Zone ID: " + outTokens[6]);
                System.out.println("[+] URL: " + outTokens[7]);
                System.out.println("[+] Location Type: " + outTokens[8]);
                if (outTokens.length > 9)
                    System.out.println("[+] Parent Station: " + outTokens[9]);
                else
                    System.out.println("[+] Parent Station: null");
            }
            catch(Exception IOException)
            {
            	System.out.println("\n---------------------------------------------------------------------------");
                System.out.println(" Opps...There're no files searched");
                System.out.println("---------------------------------------------------------------------------");
            }
            i++;
        }
        System.out.println(String.join("", Collections.nCopies(80,"*")));
    }
    
    public static String getStop(Scanner scanner, ArrayList<String> results)
    {
        String stop = null;
        if(results.size() > 1)
        {
        	System.out.println("\n---------------------------------------------------------------------------");
            System.out.println(" Please Choose 1 of the following: ");
            System.out.println("---------------------------------------------------------------------------");
            for(int i = 0; i < results.size(); i++)
            {
                System.out.println("" + (i + 1) + ". " + results.get(i));
            }
            boolean firstStopGiven = false;
            while(!firstStopGiven)
            {
            	System.out.println("\n---------------------------------------------------------------------------");
                System.out.println(" Type in the number of the stop you want to choose: ");
                System.out.println("---------------------------------------------------------------------------");
                String s = scanner.next();
                s += scanner.nextLine();
                if(s.matches("[0-9]*")) 
                {
                    int reply = Integer.parseInt(s);
                    if(reply - 1 >= 0 && reply - 1 < results.size())
                    {
                        stop = results.get(reply - 1);
                        firstStopGiven = true;
                    }
                    else
                    {
                    	System.out.println("\n---------------------------------------------------------------------------");
                        System.out.println(" Invalid Input: Please choose use one of the numbers found beside the stops listed above");
                        System.out.println("---------------------------------------------------------------------------");
                    }
                }
                else
                {
                	System.out.println("\n---------------------------------------------------------------------------");
                    System.out.println(" Invalid Input: Please use numbers only");
                    System.out.println("---------------------------------------------------------------------------");
                }
            }
        }
        return stop;
    }
}