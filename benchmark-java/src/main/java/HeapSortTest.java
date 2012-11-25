import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * HeapSortTest.
 * 
 * @author Alexandre
 * 
 */
public final class HeapSortTest
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
     * SIZE
     */
    private static final int SIZE                    = 5 * 1000 * 1000;
    
    /**
     * NB_HEAPSORT_TESTS
     */
    private static final int NB_HEAPSORT_TESTS       = 1;

    
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
     * testHeapSort.
     * 
     * @return
     */
    public static long testHeapSort()
    {
        double[] array = new double[SIZE];
        for (int i = SIZE; i-- != 0;)
            array[i] = RandomizerHeapSort.getRandomValue(1);
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_HEAPSORT_TESTS; i != 0; i--)
        {
            heapSort(array);
            
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[HeapSortTest], Sort an array of double " + SIZE + ",, snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * heapsort.
     * 
     * @param n
     * @param ra
     */
    public static void heapSort(double[] ra)
    {
        int l, ir;
        double rra;
        
        l = ((ra.length - 1) >> 1) + 1;
        ir = ra.length - 1;
        for (;;)
        {
            if (l > 1)
                rra = ra[--l];
            else
            {
                rra = ra[ir];
                ra[ir] = ra[1];
                if (--ir == 1)
                {
                    ra[1] = rra;
                    return;
                }
            }
            
            int i = l;
            int j = l << 1;
            
            while (j <= ir)
            {
                
                if (j < ir && ra[j] < ra[j + 1])
                    ++j;
                
                if (rra < ra[j])
                {
                    ra[i] = ra[j];
                    j += (i = j);
                }
                else
                    j = ir + 1;
            }
            
            ra[i] = rra;
        }
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
            executionTimes.add(HeapSortTest.testHeapSort());
        System.out.println("[HeapSortTest], Sort an array of double " + SIZE + ",, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
    }
    
}

/**
 * Randomizer.
 * 
 * @author Alexandre
 * 
 */
final class RandomizerHeapSort
{
    private static long lastRandom = 42;
    
    /**
     * Constructor.
     * 
     */
    private RandomizerHeapSort()
    {
        //
    }
    
    /**
     * resetRandomizer.
     * 
     */
    public static void resetRandomizer()
    {
        lastRandom = 42;
    }
    
    /**
     * getRandomValue.
     * 
     * @param max
     * @return
     */
    public static double getRandomValue(final double max)
    {
        return (max * (lastRandom = (lastRandom * 3877 + 29573) % 139968) / 139968);
    }
}
