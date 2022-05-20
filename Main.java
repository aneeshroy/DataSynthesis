

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String 		fh_data 			= "EXP_12_cell4";
		ObjData		myData				= new ObjData();
		UtilStatistics	myStatistics	= new UtilStatistics();
		
		
		//-------Read Data From File --------------------------------------
		UtilRead myReadUtil = new UtilRead();
		myReadUtil.readDataFromFile(fh_data, myData);
		
		//-------Get Orig Data Stats   ------------------------------------
		myData.orig_data_Stats = myStatistics.getStats(myData.orig_data_List);
		
		//-------Generate New Data for each Segment ------------------------
		UtilNewData.generateNewData(myData);
		
		//-------Align Data Sets for each Segment -------------------------
		UtilNewData.alignNewData(myData);
		
		//-------Get New Data Stats   --------------------------------------
		myData.new_data_Stats = myStatistics.getStats(myData.new_data_List);
		
		//-------Print New Data Segments ----------------------------------
		UtilWrite.writeFile(myData);
		
		//-------End Program ----------------------------------------------
		System.out.println("SUCCESSFUL END");
	}

}
