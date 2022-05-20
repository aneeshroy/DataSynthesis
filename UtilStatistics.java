import java.util.ArrayList;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class UtilStatistics {
	
	public PojoStats getStats (ArrayList<Double> _sampleDataList) {
		
		DescriptiveStatistics stats = new DescriptiveStatistics();
		
		int memberCount = _sampleDataList.size();
		
		for (int i = 0; i < _sampleDataList.size(); i ++)  {
			
			stats.addValue(_sampleDataList.get(i));
		}
		 
		double min 					= stats.getMin();
		double max 					= stats.getMax();
		double mean 				= stats.getMean();
		double standardDeviation 	= stats.getStandardDeviation();
		//double skewness			= stats.getSkewness();
		
		return new PojoStats(min, max, mean, standardDeviation, memberCount);
	}

}
