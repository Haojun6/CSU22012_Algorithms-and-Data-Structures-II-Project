import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class SettingTimes 
{
	int tripId;
	LocalTime arrivalTime;
	LocalTime departureTime;
	String arrivalTimeString;
	String departureTimeString;
	int stopId;
	int stopSequence;
	int pickUpType;
	int dropOffType;
	double distance;

	
	public SettingTimes(String lineOfTimes) 
	{
		String[] inToArray = lineOfTimes.split(",");

		try 
		{
			tripId = Integer.parseInt(inToArray[0]);
		} 
		catch (Exception e) 
		{
			tripId = -9;
		}

		try 
		{
			arrivalTime = LocalTime.parse(inToArray[1]);
		} 
		catch (Exception e) 
		{
			arrivalTime = null;
		}

		try 
		{
			arrivalTimeString = inToArray[1];
		} 
			catch (Exception e) 
		{
			arrivalTimeString = null;
		}
		
		try 
		{
			departureTime = LocalTime.parse(inToArray[2]);
		} 
		catch (Exception e) 
		{
			departureTime = null;
		}

		try 
		{
			departureTimeString = inToArray[2];
		} 
		catch (Exception e)
		{
			departureTimeString = null;
		}

		try 
		{
			stopId = Integer.parseInt(inToArray[3]);
		} 
		catch (Exception e) 
		{
			stopId = -9;
		}

		try 
		{
			stopSequence = Integer.parseInt(inToArray[4]);
		} 
		catch (Exception e) 
		{
			stopSequence = -9;
		}

		try
		{
			pickUpType = Integer.parseInt(inToArray[6]);
		} 
		catch (Exception e) 
		{
			pickUpType = -9;
		}

		try 
		{
			dropOffType = Integer.parseInt(inToArray[7]);
		} 
		catch (Exception e) 
		{
			dropOffType = -9;
		}

		try
		{
			distance = Double.parseDouble(inToArray[8]);
		} 
		catch (Exception e) 
		{
			distance = -9;
		}
	}

	public int getTripId() 
	{
		if (tripId == -9)
			return 0;
		return tripId;
	}

	public LocalTime getArrivalTime() 
	{
		if (arrivalTime == null)
			return null;
		return arrivalTime;
	}
	
	public String getArrivalTimeToString()
	{
		if (arrivalTimeString == null)
			return "None";
		return arrivalTimeString;
	}

	public LocalTime getDepartureTime()
	{
		if (departureTime == null)
			return null;
		return departureTime;
	}
	
	public String getDepartureTimeToString() 
	{
		if (departureTimeString == null)
			return "None";
		return departureTimeString;
	}

	public int getStopId()
	{
		if (stopId == -9)
			return 0;
		return stopId;
	}

	public int getStopSequence()
	{
		if (stopSequence == -9)
			return 0;
		return stopSequence;
	}

	public int getPickUpType() 
	{
		if (pickUpType == -9)
			return 0;
		return pickUpType;
	}

	public int getDropOffType() 
	{
		if (dropOffType == -9)
			return 0;
		return dropOffType;
	}

	public double getDistance()
	{
		if (distance == -9)
			return 0;
		return distance;
	}
}