import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TimingTest.
 * 
 * @author Alexandre
 * 
 */
public final class TimingTest
{
    /**
     * NB_TESTS
     */
    private static final int NB_TESTS                = 5;

    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    private static final int NB_OF_EXCLUSION_MIN_MAX = 2;

    /**
     * NB_CREATION_TESTS
     */
    private static final int NB_TIMING_TESTS         = 50 * 1000 * 1000;

    /**
     * averageTimeWithoutMinMax.
     * 
     * @param executionTimes
     * @param numberOfMinMaxToRemove
     * @return
     */
    @SuppressWarnings("boxing")
    public static long averageTimeWithoutMinMax(final List<Long> executionTimes, final int numberOfMinMaxToRemove)
    {
        long average = 0;
        final int minMax = (numberOfMinMaxToRemove > 0) ? numberOfMinMaxToRemove : 0; 
        if (minMax != 0)
        {
            Collections.sort(executionTimes);
        }
        for (int i = minMax; i < executionTimes.size() - minMax; i++)
        {
            average += executionTimes.get(i);
        }
        average = average / (executionTimes.size() - minMax * 2);
        return average;
    }
    
    
    /**
     * relativeDeviationTimeWithoutMinMax.
     *
     * @param executionTimes
     * @param numberOfMinMaxToRemove
     * @return
     */
    @SuppressWarnings("boxing")
    public static double relativeDeviationTimeWithoutMinMax(final List<Long> executionTimes, final int numberOfMinMaxToRemove)
    {
        final long averageTimeWithoutMinMax = averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
        long deviation = 0;
        final int minMax = (numberOfMinMaxToRemove > 0) ? numberOfMinMaxToRemove : 0; 
        if (minMax != 0)
        {
            Collections.sort(executionTimes);
        }
        for (int i = minMax; i < executionTimes.size() - minMax; i++)
        {
            deviation += Math.pow(executionTimes.get(i) - averageTimeWithoutMinMax, 2);
        }
        return ((int) ((Math.sqrt(deviation / (executionTimes.size() - 2 * minMax)) / averageTimeWithoutMinMax * 100) * 100)) / 100d;
    }    
    

    /**
     * testCurrentTime.
     * 
     * @return
     */
    public static long testCurrentTime()
    {
        long start = System.currentTimeMillis();
        long currentTime = 0;
        start = System.currentTimeMillis();
        for (int i = NB_TIMING_TESTS; i != 0; i--)
        {
            currentTime = System.currentTimeMillis();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[TimingTest], Invoke of " + NB_TIMING_TESTS + " long currenttime = System.currentTimeMillis()," + currentTime + ", snapshot time,"
                + executionTime);
        return executionTime;
    }

    
    /**
     * main.
     * 
     * @param args
     */
    @SuppressWarnings("boxing")
    public static void main(final String[] args)
    {
        final int nbTests = (args != null && args.length >= 1 && args[0] != null) ?  Integer.valueOf(args[0]): NB_TESTS;
        final int nbOfExclusionMinMax = (args != null && args.length >= 2 && args[1] != null) ? Integer.valueOf(args[1]) : NB_OF_EXCLUSION_MIN_MAX;

        final List<Long> executionTimes = new ArrayList<Long>(nbTests);
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(TimingTest.testCurrentTime());
        System.out.println("[TimingTest], Invoke of " + NB_TIMING_TESTS + " long currentTime = System.currentTimeMillis(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
    }
}
