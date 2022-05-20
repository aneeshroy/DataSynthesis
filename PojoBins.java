import java.util.ArrayList;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class PojoBins {
	
	public double 	binMin;
	public double 	binMax;
	public double 	binMean;
	public double 	binStandardDeviation;
	public int 		binFrequency;
	public ArrayList<Double> binMemberList = new ArrayList<Double>();
	public int 		newBinMemberCount;
	
	public PojoBins(double _binMin, double _binMax) {
		super();
		this.binMin = _binMin;
		this.binMax = _binMax;
		this.newBinMemberCount 		= 0;
		this.binMean 				= 0.0;
		this.binStandardDeviation 	= 0.0;
		this.binFrequency			= 0;
	}
	
	public void addMember (Double _member) {
		
		binMemberList.add(_member);
	}
	
	public void calcStats () {
		
		DescriptiveStatistics stats = new DescriptiveStatistics();
		
		for (int i = 0; i < binMemberList.size(); i ++)  {
			
			stats.addValue(binMemberList.get(i));
		}
		 
		binMean 				= stats.getMean();
		binStandardDeviation 	= stats.getStandardDeviation();
		binFrequency			= binMemberList.size();
		
	}
	
	public boolean updateNewBinMemberCount () {
		
		if (newBinMemberCount >= binFrequency) return false;
		
		newBinMemberCount++;
		return true;
	}


	@Override
	public String toString() {
		return "PojoBins [binMin=" + binMin + ", binMax=" + binMax + ", binMean=" + binMean + ", binStandardDeviation="
				+ binStandardDeviation + ", binFrequency=" + binFrequency + ", newBinMemberCount=" + newBinMemberCount
				+ "]";
	}
	
	
	
}
