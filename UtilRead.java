import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UtilRead {
	

	//-------Read Data From File --------------------------------------
	
	public void readDataFromFile (String _fh_data, ObjData _myData) throws IOException {

		File file = new File(_fh_data);
		BufferedReader br = new BufferedReader(new FileReader(file));
		double var;
        
        String st;
        while((st=br.readLine()) != null){
        	var	= Double.parseDouble(st.split(",")[0]);
        	_myData.orig_data_List.add(var);
        }
        br.close();
        
        return;
	}
	
	

}
