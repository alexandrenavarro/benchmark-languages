import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * NestedLoopsTest.
 * 
 * @author anavarro122404 - 25 sept. 07
 * 
 * 
 * 
 */
public final class NestedLoopsTest
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
    private static final int NB_NESTED_LOOPS_TESTS   = 40;
    
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
     * testNestedLoops.
     * 
     * @return
     */
    public static long testNestedLoops()
    {
        int a, b, c, d, e, f;
        int count = 0;
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (a = 0; a < NB_NESTED_LOOPS_TESTS; a++)
        {
            for (b = 0; b < NB_NESTED_LOOPS_TESTS; b++)
            {
                for (c = 0; c < NB_NESTED_LOOPS_TESTS; c++)
                {
                    for (d = 0; d < NB_NESTED_LOOPS_TESTS; d++)
                    {
                        for (e = 0; e < NB_NESTED_LOOPS_TESTS; e++)
                        {
                            for (f = 0; f < NB_NESTED_LOOPS_TESTS; f++)
                            {
                                // Never true, just to force gcc not to optimize
                                if (count % 2 == 2)
                                {
                                    count = count * 2;
                                }
                                count += a + b + c + d + e + f;
                            }
                        }
                    }
                }
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[NestedLoopsTest], 6 Nested Loops and add all counters " + NB_NESTED_LOOPS_TESTS + "  ,count=" + count
                + ", snapshot time," + executionTime);
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
            executionTimes.add(NestedLoopsTest.testNestedLoops());
        System.out.println("[NestedLoopsTest], 6 Nested Loops and add all counters " + NB_NESTED_LOOPS_TESTS + "  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
    }

}
