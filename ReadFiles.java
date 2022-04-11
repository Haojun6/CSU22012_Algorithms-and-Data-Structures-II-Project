import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class ReadFiles {
	
	public static File STOPS;
    public static File STOP_TIMES;
    public static File TRANSFERS;
    public static String stopTimes;
    public static String stops;
    public static String transfers;
    
	
	public ReadFiles(String fileName1, String fileName2, String fileName3)
	{
		stopTimes = fileName1;
		stops = fileName2;
		transfers = fileName3;
		STOPS = new File(stops);
        STOP_TIMES = new File(stopTimes);
        TRANSFERS = new File(transfers);
	}
	
	
	public static ArrayList<String> readFile (File fileName) throws IOException, FileNotFoundException
	{
		ArrayList<String> context = new ArrayList<>();

		BufferedReader reader = new BufferedReader(new FileReader(fileName)); 
		{
			while (reader.ready()) 
				context.add(reader.readLine());
		}
		return context;
	}
	
	public static ArrayList<String> routeDetails(String theArrivalTime) throws FileNotFoundException, IOException
	{
		ArrayList<String> rawArrivalTime = readFile(STOP_TIMES);
		ArrayList<String> arrivalTime = new ArrayList<String>();
		for (int i = 1; i < rawArrivalTime.size(); i++) 
		{
			String index = rawArrivalTime.get(i);
			SettingTimes StopTime = new SettingTimes(index);
			LocalTime temp = StopTime.getArrivalTime();
			int hour = temp.getHour();
			int minute = temp.getMinute();
			int second = temp.getSecond();
			if (hour > 24 || minute > 59 || second > 59)
				rawArrivalTime.remove(i);
		}

		for (int i = 1; i < rawArrivalTime.size(); i++) 
		{
			String index = rawArrivalTime.get(i);
			SettingTimes StopTime = new SettingTimes(index);
			String specificTime = StopTime.getArrivalTimeToString();

				if (specificTime.equalsIgnoreCase(theArrivalTime))
				{
					String departureTime = StopTime.getDepartureTimeToString();

					String tripDetails = Integer.toString(StopTime.getTripId())
							//+ "\n | Stop Name: "+ Main.returnStopName(Integer.toString(StopTime.getStopId())) 
							+ "\n | Stop id: "+ Integer.toString(StopTime.getStopId()) 
							+ "\n | Departure time: " + departureTime 
							+ "\n | Stop Sequence: "+ Integer.toString(StopTime.getStopSequence()) 
							+ "\n | Pick-up type:  "+ Integer.toString(StopTime.getPickUpType()) 
							+ "\n | Drop-off type: "+ Integer.toString(StopTime.getDropOffType()) 
							+ "\n | Distance: "+ Double.toString(StopTime.getDistance());

					arrivalTime.add(tripDetails);
				} 
		}
		Collections.sort(arrivalTime);
		return arrivalTime;
	}
	
	

}
