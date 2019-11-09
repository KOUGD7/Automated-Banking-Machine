
package src.project2.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class SimpleData{

    public static ArrayList<String[]> ReadCSV(String filepath, String delimiter)  throws IOException  {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line = reader.readLine();
        String[] lineData;
        ArrayList<String[]> outData = new ArrayList<String[]>();
        
        while(line != null) {
            lineData = line.split(delimiter);
            outData.add(lineData);
            line = reader.readLine();
        }
        reader.close();
        return outData;
    }

    public static ArrayList<String[]> ReadCSV(String filepath)  throws IOException {
    	return ReadCSV(filepath, "\t");
    }
    
    public static boolean writeCSV(String filepath, ArrayList<String[]> data, String delimiter) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        String[] line;
        final int size = data.size();
        for (int i=0; i < size; i++) {
            line = data.get(i);
            writer.write(String.join(delimiter, line));
            writer.newLine();
        }
        writer.close();
        return true;
    }
    
    public static boolean writeCSV(String filepath, ArrayList<String[]> data) throws IOException {
	return writeCSV(filepath, data, "\t");
    }

}