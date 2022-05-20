import java.util.ArrayList;
import java.util.HashMap;

public class PojoHistogram {
	
	public double 	min;
	public double 	max;
	public double 	mean;
	public double 	standardDeviation;
	public int 		memberCount;
	public int 		newMemberCount;
	
	HashMap<Integer, ArrayList<Double>>  	data_L2_seg_data_Map 		= new HashMap<Integer, ArrayList<Double>>();
	HashMap<Integer, PojoHistogram>  			data_L2_seg_data_stats_Map  = new HashMap<Integer, PojoHistogram>();
	
	//public double skewness;
	
	public PojoHistogram(double _min, double _max, double _mean, double _standardDeviation, int _memberCount) { //, double _skewness) {
		
		super();
		
		this.min 				= _min;
		this.max 				= _max;
		this.mean 				= _mean;
		this.standardDeviation 	= _standardDeviation;
		this.memberCount 		= _memberCount;
		this.newMemberCount 	= 0;
			
	}
	
	public boolean updateNewBinMemberCount () {
		
		if (newMemberCount >= memberCount) return false;
		
		newMemberCount++;
		return true;
	}

	@Override
	public String toString() {
		return "PojoStats [min=" + min + ", max=" + max + ", mean=" + mean + ", standardDeviation=" + standardDeviation
				+ ", memberCount=" + memberCount + ", newMemberCount=" + newMemberCount + "]";
	}
	
	
	
	

}
