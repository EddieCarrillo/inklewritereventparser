import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class JsonReader {
	public String getJsonString(String fileName) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(fileName));
		String jsonString = "";
		while(scan.hasNext()){
			jsonString+=scan.next();
		}
		scan.close();
		
		return jsonString;
		
	}
}
