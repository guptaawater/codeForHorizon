/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package main.java.codeForHorizon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class App {
    public void fileProcessing() throws FileNotFoundException{
    	try (InputStream input = new FileInputStream("replacement.properties")) {

			Properties prop = new Properties();

			// load a properties file
			prop.load(input);
			File file = new File("textfile.txt");
			Scanner sc;
			String fileTxt = new String();
			try {
				sc = new Scanner(file);
				sc.useDelimiter("\\Z");

				fileTxt = sc.next();
			} catch (FileNotFoundException e) {
				System.out.println("Error to load textfile file. "+ e.getMessage());

			}
			List<String> fileWords = Arrays.asList(fileTxt.split(" "));
			Set<Map.Entry<Object, Object>> set = prop.entrySet();
			
			for (Map.Entry<Object, Object> entry: set)
	        {
				String key = (String)entry.getKey();
				String value =(String) entry.getValue();
				Long wordCount = fileWords.stream().filter(word -> word.equals(key)).count();
				Long withCommaCount = fileWords.stream().filter(word -> word.equals(key + ",")).count();
				Long totalCount = wordCount + withCommaCount;

	            String timeStr = "time";
				if (totalCount > 1)
					timeStr += "s";
				System.out.println(
						key.toString().toUpperCase() + " is replaced " + totalCount + " " + timeStr);
				fileTxt = fileTxt.replaceAll((String) key, (String) value);
				fileTxt = fileTxt.replaceAll((key + ","), (value + ","));
	        }
			
			try {
			      FileWriter myWriter = new FileWriter("replacedFileTxt.txt");
			      myWriter.write(fileTxt);
			      myWriter.close();
			    } catch (IOException e) {
			      System.out.println("An error occurred in writing replaced file. "+ e.getMessage());
			    }
			//System.out.println(fileTxt);
		} catch (IOException ex) {
			System.out.println("Error to load replacement.properties file "+ ex.getMessage());
		}
    }

    public static void main(String[] args) throws FileNotFoundException  {
    	new App().fileProcessing();
		
	}
}
