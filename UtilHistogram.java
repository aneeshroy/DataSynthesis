import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public final class UtilHistogram {
	
	public static DescriptiveStatistics stats = new DescriptiveStatistics();
	
	public static void createHistogram (ObjData _myData, int _percentileDelta) {
		
		ArrayList<Double> dataList = _myData.orig_data_List;
		
		for (int i = 0; i < dataList.size(); i ++)  {
			
			stats.addValue(dataList.get(i));
		}
		
		double iqr 		= stats.getPercentile(50 + _percentileDelta) - stats.getPercentile(50 - _percentileDelta);
		double binSize	= 2.0 * iqr * Math.pow(dataList.size(), -1.0/3.0);
		
		double binMin = dataList
			      .stream()
			      .mapToDouble(v -> v)
			      .min().orElseThrow(NoSuchElementException::new);
		
		double binMax = dataList
			      .stream()
			      .mapToDouble(v -> v)
			      .max().orElseThrow(NoSuchElementException::new);
		
		Integer numBins		= (int) ((binMax - binMin)/binSize);
		
		Double currentBinMin	= binMin;
		Double locMin;
		Double locMax;
		
		// Creating Bins List:  Bins are defined by binMax and binMin
		
		for (int i = 0; i < numBins; i ++) {
			
			locMin = currentBinMin;
			locMax = currentBinMin + binSize;
			currentBinMin += binSize;
			
			if (i == numBins - 1) locMax = binMax;
			
			_myData.histogram_List.add(new PojoBins(locMin, locMax));

		}
		
		// Add Members to Bins
		for (double member: dataList)  {
			
			for(PojoBins pojoBin : _myData.histogram_List) {
		        if ((pojoBin.binMin <= member) && (pojoBin.binMax >= member)) {
		        	pojoBin.addMember(member);
		        	break;
		        }
		    }
		}
		
		// Calculate Stats for Bins
		for(PojoBins pojoBin : _myData.histogram_List) {
			pojoBin.calcStats();
		}

	}

}
