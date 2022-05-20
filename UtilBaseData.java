

public final class UtilBaseData {
	
	static UtilStatistics	myStatistics	= new UtilStatistics();
	
	public static void setup (ObjData _myData) {
		
		_myData.orig_data_Stats = myStatistics.getStats(_myData.orig_data_List);
		
	}				
	
}
