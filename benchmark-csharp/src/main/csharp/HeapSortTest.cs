using System;
using System.Collections.Generic;
using System.Text;


sealed class HeapSortTest
{

    /**
     * NB_TESTS
     */
    private static int NB_TESTS = 5;

    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    private static int NB_OF_EXCLUSION_MIN_MAX = 2;

    /**
     * SIZE
     */
    private static int SIZE = 5 * 1000 * 1000;

    /**
     * NB_HEAPSORT_TESTS
     */
    private static int NB_HEAPSORT_TESTS = 1;

       
    /**
     * averageTimeWithoutMinMax.
     *
     * @param executionTimes
     * @param numberOfMinMaxToRemove
     * @return
     */
    public static long averageTimeWithoutMinMax(List<long> executionTimes, int numberOfMinMaxToRemove)
    {
        long average = 0;
		int minMax = (numberOfMinMaxToRemove > 0) ? numberOfMinMaxToRemove : 0;
		if (minMax != 0) 
		{
        	executionTimes.Sort();
		}
        for (int i = minMax; i < executionTimes.Count - minMax; i++)
        {
            average += executionTimes[i];
        }
        average = average / (executionTimes.Count - minMax * 2);
        return average;
    }


    /**
     * relativeDeviationTimeWithoutMinMax.
     *
     * @param executionTimes
     * @param numberOfMinMaxToRemove
     * @return
     */
    public static double relativeDeviationTimeWithoutMinMax(List<long> executionTimes, int numberOfMinMaxToRemove)
    {
        long averageTimeWithoutMinMaxVariable = averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
        long deviation = 0;
		int minMax = (numberOfMinMaxToRemove > 0) ? numberOfMinMaxToRemove : 0;
		if (minMax != 0) 
		{
        	executionTimes.Sort();
		}
        for (int i = minMax; i < executionTimes.Count - minMax; i++)
        {
            deviation += (long)Math.Pow(executionTimes[i] - averageTimeWithoutMinMaxVariable, 2);
        }
        return ((int)((Math.Sqrt(deviation / (executionTimes.Count - 2 * minMax)) / averageTimeWithoutMinMaxVariable * 100) * 100)) / 100d;
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
	        array[i] = Randomizer.getRandomValue(1);
        DateTime start = DateTime.Now;
        for (int i = NB_HEAPSORT_TESTS; i != 0; i--)
        {
            heapSort(array);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[HeapSortTest], Sort an array of double " + SIZE + ",, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
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

	    l = ((ra.Length - 1) >> 1) + 1;
	    ir = ra.Length - 1;
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
    public static void main(string[] args)
    {
	    int nbTests = (args != null && args.Length >= 1) ? int.Parse(args[0]) : NB_TESTS;
	    int nbOfExclusionMinMax = (args != null && args.Length >= 2) ? int.Parse(args[1]) : NB_OF_EXCLUSION_MIN_MAX;

        List<long> executionTimes = new List<long>(nbTests);
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(HeapSortTest.testHeapSort());
        executionTimes.Sort();
        Console.WriteLine("[HeapSortTest], Sort an array of double " + SIZE + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
        
    }
}

/**
 * Randomizer.
 *
 * @author Alexandre
 *
 */
class Randomizer
{
    static long lastRandom = 0;

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
    public static double getRandomValue(double max)
    {
	    return (max * (lastRandom = (lastRandom * 3877 + 29573) % 139968) / 139968);
    }
}

