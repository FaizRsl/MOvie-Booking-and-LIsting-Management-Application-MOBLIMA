import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class ExcelEditor {

	private String fileLocation;
	public ExcelEditor(String fileLocation) throws IOException {
		this.fileLocation = fileLocation;
	}
	
	public void createRow(String fileLocation, ArrayList<String> input, int sheetNumber) throws IOException {
		
		ArrayList<ArrayList<String>> csvData = getData();
		FileWriter csvWriter = new FileWriter(fileLocation);
		
		for(ArrayList<String> rowData : csvData) {
			csvWriter.append(String.join(",", rowData));
			csvWriter.append("\n");
		}
		
		for(int i = 0; i < input.size(); i++) {
			csvWriter.append(input.get(i));
			if(i != input.size()+1) csvWriter.append(",");
			else csvWriter.append("\n");
		}
		csvWriter.flush();
		csvWriter.close();
	}
	
	public ArrayList<ArrayList<String>> getData() throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(fileLocation));

		String row;
		ArrayList<ArrayList<String>> csvData = new ArrayList<ArrayList<String>>();
		while((row = csvReader.readLine()) != null) {	
			String []data = row.split(",");
			ArrayList<String> temp = new ArrayList<String>();
			for(int i = 0; i < data.length; i++) {
				temp.add(data[i]);
			}
			csvData.add(temp);

		}
		

		csvReader.close();
		return csvData;
	}
}
