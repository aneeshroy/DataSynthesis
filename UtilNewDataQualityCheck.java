
public final class UtilNewDataQualityCheck {
	
	public static boolean newDataHistogramQtyCheck (Double newMember, ObjData _myData) {
		
		for(PojoBins pojoBin : _myData.histogram_List) {
	        if ((pojoBin.binMin <= newMember) && (pojoBin.binMax >= newMember)) {
	        	if (pojoBin.updateNewBinMemberCount ()) {
	        		return (true);
	        	}
	        }
	    }
		return false;
	}
	
	public static boolean repopulateNewHistogramWithExisting (Double newMember, ObjData _myData) {
		
		for(PojoBins pojoBin : _myData.histogram_List) {
	        if ((pojoBin.binMin <= newMember) && (pojoBin.binMax >= newMember)) {
	        	if (pojoBin.updateNewBinMemberCount ()) {
	        		return (true);
	        	}
	        }
	    }
		return false;
	}
}
