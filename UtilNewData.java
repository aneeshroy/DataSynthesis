import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.IntStream;

public final class UtilNewData {
	
	static Random r 						= new Random();
	static int loopCount 					= 0;
	static int loopCountIncreaseFactor 		= 10;
	static int loopCountMaxCurrent 			= 0;
	static int histPercentileDelta 			= 5;
	static int histPercentileDeltaCurrent 	= 0;
	
	//-------Generate New Data -----------------------------------
	
	public static void generateNewData (ObjData _myData) {
		
		int origDataSize 	= _myData.orig_data_List.size();
		int arraySize 		= origDataSize;
		
		double desiredMean 				= _myData.orig_data_Stats.mean;
		double desiredStandardDeviation = _myData.orig_data_Stats.standardDeviation;
		double desiredMin				= _myData.orig_data_Stats.min;
		double desiredMax				= _myData.orig_data_Stats.max;
		
		double newData;
		
		while (arraySize > 0) {
			
			loopCount++;
			
			if (loopCount >= loopCountMaxCurrent)  {
				
				loopCountMaxCurrent 		+= origDataSize * loopCountIncreaseFactor;
				
				if (histPercentileDeltaCurrent + histPercentileDelta  < 50) {
					histPercentileDeltaCurrent 	+= histPercentileDelta;
					resetHistogram (_myData, histPercentileDeltaCurrent);
					arraySize = origDataSize - _myData.temp_new_data_List.size();
				}
				continue;
			}
			
			newData = r.nextGaussian()*desiredStandardDeviation + desiredMean;
			
			if (newData < desiredMin) continue;
			if (newData > desiredMax) continue;
			if (!UtilNewDataQualityCheck.newDataHistogramQtyCheck(newData, _myData)) continue;
			
			
			_myData.temp_new_data_List.add(newData);
			arraySize--;
		}
		
		return;
	}
	
	//-------Align New Data -----------------------------------
	
	public static void alignNewData (ObjData _myData) {
		
		ArrayList<Double> outputList = new ArrayList<Double>(_myData.temp_new_data_List);
		
		Collections.sort(_myData.temp_new_data_List);
		
		int[] sortedIndices = IntStream.range(0, _myData.orig_data_List.size())
		                .boxed().sorted((i, j) -> Double.compare(_myData.orig_data_List.get(i), _myData.orig_data_List.get(j)))
		                .mapToInt(ele -> ele).toArray();	
		
		for (int i = 0; i < sortedIndices.length; i++) 
			outputList.set(sortedIndices[i], _myData.temp_new_data_List.get(i));

		_myData.new_data_List = outputList;
		
		return;
	}
	
	//-------Reset  Histogram  ----------------------------------------
	public static void resetHistogram (ObjData _myData, int _percentileDelta) {
		
		_myData.histogram_List = new ArrayList<PojoBins>();
	
		UtilHistogram.createHistogram(_myData, _percentileDelta);
		
		if (_myData.temp_new_data_List.size() > 0) {
			
			int index = 0;
			
			while (index < _myData.temp_new_data_List.size()) {
				//UtilNewDataQualityCheck.repopulateNewHistogramWithExisting(newMember, _myData);
				boolean flag = UtilNewDataQualityCheck.repopulateNewHistogramWithExisting(_myData.temp_new_data_List.get(index), _myData);
				
				if (!flag) {
					_myData.temp_new_data_List.remove(index);
					continue;
				}
				index++;
			}

		}
	}
	

}
