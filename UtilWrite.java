import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class UtilWrite {
	
	public static void writeFile (ObjData _myData) throws IOException {

		Path path = Paths.get("Output");
		
		try (BufferedWriter writer = Files.newBufferedWriter(path))  {
			
			for (int i = 0; i < _myData.new_data_List.size(); i++) {
				
				writer.write(_myData.new_data_List.get(i).toString() + "\n");
				
			}	
		} 		
	}
}
