using System;
using System.Collections.Generic;
using System.Text;
using System.Diagnostics;

sealed class ExceptionTest
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
     * NB_EXCEPTION_TESTS
     */
    private static int NB_EXCEPTION_TESTS = 100 * 1000;//1000 * 1000;

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
     * testUncheckedException.
     *
     * @return
     */
    public static long testUncheckedException()
    {
        DateTime start = DateTime.Now;
	    for (int i = NB_EXCEPTION_TESTS; i != 0; i--)
	    {
	        try
	        {
		        throw new Exception();
	        }
            catch (Exception e) 
	        {
                string s = e.StackTrace;
                //Console.Write("", e.StackTrace);
	        }
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ExceptionTest], Throw and catch RuntimeException " + NB_EXCEPTION_TESTS + " try {throw new Exception()  } catch (Exception e) {},, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }
    
    
    /**
     * testUncheckedExceptionWithDeepTry.
     *
     * @return
     */
    public static long testUncheckedExceptionWithDeepTry()
    {
        DateTime start = DateTime.Now;
	    for (int i = NB_EXCEPTION_TESTS; i != 0; i--)
	    {
	        try
	        {
		        try
		        {
			        try
			        {
				        try
				        {
					        try
					        {
                                throw new Exception();
					        }
                            catch (Exception e) 
					        {
						        throw e;
					        }
				        }
                        catch (Exception e) 
				        {
					        throw e;
				        }
			        }
                    catch (Exception e) 
			        {
				        throw e;
			        }
		        }
                catch (Exception e) 
		        {
			        throw e;
		        }
	        }
            catch (Exception e) 
	        {
	        }
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ExceptionTest], Throw and catch Exception with deep try (5) " + NB_EXCEPTION_TESTS + " try { try { try { try { try {throw new Exception()  } catch (RuntimeException e) {},, snapshot time,"
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
	        executionTimes.Add(ExceptionTest.testUncheckedException());
        executionTimes.Sort();
	    Console.WriteLine("[ExceptionTest], Throw and catch RuntimeException " + NB_EXCEPTION_TESTS + " try {throw new RuntimeException()} catch (RuntimeException e) {  },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(ExceptionTest.testUncheckedExceptionWithDeepTry());
        executionTimes.Sort();
	    Console.WriteLine("[ExceptionTest], Throw and catch RuntimeException with deep try (5) " + NB_EXCEPTION_TESTS + " try { try { try { try { try {throw new RuntimeException()  } catch (RuntimeException e) {},, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	
    }
    


}

