import java.util.Random;

public final class UtilNumberGenerator {
	
	final static public Random RANDOM = new Random(System.currentTimeMillis());

    static public double nextSkewedBoundedDouble(double min, double max, double skew, double bias) {
        
    	double range = max - min;
        double mid = min + range / 2.0;
        double unitGaussian = RANDOM.nextGaussian();
        double biasFactor = Math.exp(bias);
        double retval = mid+(range*(biasFactor/(biasFactor+Math.exp(-unitGaussian/skew))-0.5));
        double test = biasFactor/(biasFactor+Math.exp(-unitGaussian/skew));
        
        
        System.out.println (String.valueOf(test) + "  " + String.valueOf(biasFactor/(biasFactor+Math.exp(-unitGaussian/skew))-0.5)  + "\n");
        return retval;
    }

}////newData = r.nextGaussian()*desiredStandardDeviation+desiredMean;
