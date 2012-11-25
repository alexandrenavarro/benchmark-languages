using System;
using System.Collections.Generic;
using System.Text;


sealed class AutoboxingTest
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
    private static int NB_AUTOBOXING_TESTS =  200 * 1000 * 1000 ;


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
     * testBoxingIntegerAndToString.
     *
     * @return
     */
    public static long testBoxingIntegerAndToString()
    {
	    int count = 0;
	    DateTime start = DateTime.Now; 
	    for (int i = NB_AUTOBOXING_TESTS; i != 0; i--)
	    {
            Object o = i;
            String toString = o.ToString();
            count += toString.Length;
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
	    Console.WriteLine("[AutoBoxingTest], testBoxingIntegerAndToString " + NB_AUTOBOXING_TESTS + " final Integer integer = new Integer(i)   final String s = integer.toString()   ,count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
    
    /**
     * testBoxingIntegerWithNew.
     *
     * @return
     */
    public static long testUnboxingInteger()
    {
        Object o = 123456;
        DateTime start = DateTime.Now;
        for (int i = NB_AUTOBOXING_TESTS; i != 0; i--)
        {
            int k = (int)o;
        }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[AutoBoxingTest], testUnboxingInteger " + NB_AUTOBOXING_TESTS + " final Integer integer = new Integer(i)  ,, snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }

    /**
 * testBoxingIntegerWithValueOf.
 *
 * @return
 */
    public static long testBoxingInteger()
    {
        DateTime start = DateTime.Now;
        for (int i = NB_AUTOBOXING_TESTS; i != 0; i--)
        {
            Object o = i;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[AutoBoxingTest], testBoxingInteger " + NB_AUTOBOXING_TESTS + " final Integer integer = new Integer(i)   int k = integer.intValue()  ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }
    
    /**
     * testBoxingIntegerWithValueOf.
     *
     * @return
     */
    public static long testBoxingUnboxingInteger()
    {
	    DateTime start = DateTime.Now;
	    for (int i = NB_AUTOBOXING_TESTS; i != 0; i--)
	    {
            Object o = i;
            int k = (int)o;
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
	    Console.WriteLine("[AutoBoxingTest], testBoxingUnboxingInteger " + NB_AUTOBOXING_TESTS + " final Integer integer = Integer.valueOf(i)   int k = integer.intValue()  ,, snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
    
    /**
     * testBoxingIntegerAndToString.
     *
     * @return
     */
    public static long testBoxingUnboxingDouble()
    {
	    DateTime start = DateTime.Now;
	    for (double i = NB_AUTOBOXING_TESTS; i != 0; i--)
	    {
            Object o = i;
            double k = (double)o;
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
	    Console.WriteLine("[AutoBoxingTest], testBoxingUnboxingDouble " + NB_AUTOBOXING_TESTS + " final Double d = Double.valueOf(i)   double k = d.longValue()  ,, snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
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
            executionTimes.Add(AutoboxingTest.testUnboxingInteger());
        executionTimes.Sort();
        Console.WriteLine("[AutoBoxingTest], testUnboxingInteger " + NB_AUTOBOXING_TESTS + " final Integer integer = new Integer(i)  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(AutoboxingTest.testBoxingInteger());
        executionTimes.Sort();
	    Console.WriteLine("[AutoBoxingTest], testBoxingInteger " + NB_AUTOBOXING_TESTS + " final Integer integer = new Integer(i)  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(AutoboxingTest.testBoxingUnboxingInteger());
        executionTimes.Sort();
	    Console.WriteLine("[AutoBoxingTest], testBoxingUnboxingInteger " + NB_AUTOBOXING_TESTS + " final Integer integer = Integer.valueOf(i)   int k = integer.intValue()  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(AutoboxingTest.testBoxingUnboxingDouble());
        executionTimes.Sort();
	    Console.WriteLine("[AutoBoxingTest], testBoxingUnboxingDouble " + NB_AUTOBOXING_TESTS + " final Double d = Double.valueOf(i)   double k = d.longValue()  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
	
	
    }
    

}
