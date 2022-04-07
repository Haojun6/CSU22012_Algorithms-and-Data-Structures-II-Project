import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ShortestPath {
	public static void readFile (File fileName) throws IOException, FileNotFoundException
	{
		ArrayList<String> context = new ArrayList<>();

		BufferedReader reader = new BufferedReader(new FileReader(fileName)); 
		{
			while (reader.ready()) 
				context.add(reader.readLine());
		}
	}

}
