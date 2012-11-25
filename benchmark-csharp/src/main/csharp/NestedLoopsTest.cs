using System;
using System.Collections.Generic;
using System.Text;


sealed class NestedLoopsTest
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
     * NB_CREATION_TESTS
     */
    private static int NB_NESTED_LOOPS_TESTS = 40;

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
     * testNestedLoops.
     *
     * @return
     */
    public static long testNestedLoops()
    {
	    int a, b, c, d, e, f;
	    int count = 0;
        DateTime start = DateTime.Now;
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
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
	    Console.WriteLine("[NestedLoopsTest], 6 Nested Loops and add all counters" + NB_NESTED_LOOPS_TESTS + "  ,count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
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
	        executionTimes.Add(NestedLoopsTest.testNestedLoops());
        executionTimes.Sort();
	    Console.WriteLine("[NestedLoopsTest], 6 Nested Loops and add all counters" + NB_NESTED_LOOPS_TESTS + "  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    }

}

