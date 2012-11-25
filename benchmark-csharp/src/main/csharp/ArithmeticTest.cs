using System;
using System.Collections.Generic;
using System.Text;


sealed class ArithmeticTest
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
    private static int NB_ARITHMETIC_TESTS = 1;
    
    
    /**
     * INT_MAX
     */
    private static int INT_MAX = 		1 * 1000 * 1000 * 1000;
    
    /**
     * DOUBLE_MIN
     */
    private static double DOUBLE_MIN = 	10 * 1000 * 1000 * 1000.0;
    
    /**
     * DOUBLE_MAX
     */
    private static double DOUBLE_MAX = 	11 * 1000 * 1000 * 1000.0;   

    /**
     * LONG_MIN
     */
    private static long LONG_MIN = 	10 * 1000 * 1000 * 1000L;
    
    /**
     * LONG_MAX
     */
    private static long LONG_MAX = 	11 * 1000 * 1000 * 1000L;   
    
    
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
     * testIntArithmetic.
     *
     * @return
     */
    public static long testIntArithmetic()
    {
        DateTime start = DateTime.Now;
    	int intResult = 1;
	    int i = 1;
	    while (i < INT_MAX)
	    {
	        intResult -= i++;
	        intResult += i++;
	        intResult *= i++;
	        intResult /= i++;
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ArithmeticTest], Calculation of " + NB_ARITHMETIC_TESTS + " int intResult = 1  int i = 1  while (i < " + INT_MAX + ") {intResult -= i++  intResult += i++  intResult *= i++  intResult /= i++ },intResult=" + intResult + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    
    /**
     * testDoubleArithmetic.
     *
     * @return
     */
    public static long testDoubleArithmetic()
    {
        DateTime start = DateTime.Now;
        double doubleResult = DOUBLE_MIN;
        double i = DOUBLE_MIN;
        while (i < DOUBLE_MAX)
        {
            doubleResult -= i++;
            doubleResult += i++;
            doubleResult *= i++;
            doubleResult /= i++;
        }

        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ArithmeticTest], Calculation of " + NB_ARITHMETIC_TESTS + " double doubleResult = " + DOUBLE_MIN + " double i = " + DOUBLE_MIN + "  while (i < " + DOUBLE_MAX + ") {doubleResult -= i++  doubleResult += i++  doubleResult *= i++  doubleResult /= i++ },doubleResult=" + doubleResult + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }
    
    
    /**
     * testLongArithmetic.
     *
     * @return
     */
    public static long testLongArithmetic()
    {
        DateTime start = DateTime.Now;
	    long longResult = LONG_MIN;
	    long i = LONG_MIN;
	    while (i < LONG_MAX)
	    {
		    longResult -= i++;
		    longResult += i++;
		    longResult *= i++;
		    longResult /= i++;
	    }

        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ArithmeticTest], Calculation of " + NB_ARITHMETIC_TESTS + " long longResult = " + LONG_MIN + "  long i = " + LONG_MIN + "  while (i < " + LONG_MAX + ") { longResult -= i++  longResult += i++  longResult *= i++  longResult /= i++ },longResult=" + longResult + " , snapshot time,"
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
	        executionTimes.Add(ArithmeticTest.testIntArithmetic());
        executionTimes.Sort();
	    Console.WriteLine("[ArithmeticTest], Calculation of " + NB_ARITHMETIC_TESTS + " int intResult = 1  int i = 1  while (i < " + INT_MAX + ") {intResult -= i++  intResult += i++  intResult *= i++  intResult /= i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	

	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ArithmeticTest.testDoubleArithmetic());
        executionTimes.Sort();
	    Console.WriteLine("[ArithmeticTest], Calculation of " + NB_ARITHMETIC_TESTS + " double doubleResult = " + DOUBLE_MIN + " double i = " + DOUBLE_MIN + "  while (i < " + DOUBLE_MAX + ") {doubleResult -= i++  doubleResult += i++  doubleResult *= i++  doubleResult /= i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ArithmeticTest.testLongArithmetic());
        executionTimes.Sort();
	    Console.WriteLine("[ArithmeticTest], Calculation of " + NB_ARITHMETIC_TESTS + " long longResult = " + LONG_MIN + "  long i = " + LONG_MIN + "  while (i < " + LONG_MAX + ") { longResult -= i++  longResult += i++  longResult *= i++  longResult /= i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
    	

	
    }
    
}



