using System;
using System.Collections.Generic;
using System.Text;


sealed class StringConcatTest
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
     * NB_STRING_CONCAT_TESTS
     */
    private static int NB_STRING_CONCAT_TESTS = 100 * 1000;
    
    /**
     * NB_STRING_CONCAT_RATIO_ADD_APPEND
     * 
     * This variable is used to reduce the number of test with + by multiplying the time execution in order to reduce time to calculate it
     */
    private static int NB_STRING_CONCAT_RATIO_ADD_APPEND = 10;
    
    
    /**
     * NB_STRING_CONCAT_TESTS
     */
    private static int NB_STRING_CONCAT_TESTS_2 = 10 * 1000 * 1000;
    
    
    
    /**
     * SIZE
     */
    private static int SIZE = 1000;
    
    /**
     * SIZE
     */
    private static int SIZE_2 = 3;
    /**
     * A
     */
    private static String A = "aa";
    
    

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
     * testStringConcatWithAdd.
     *
     * @return
     */
    public static long testStringConcatWithAdd()
    {
	    int count = 0;
        DateTime start = DateTime.Now;
	    for (int i = NB_STRING_CONCAT_TESTS / NB_STRING_CONCAT_RATIO_ADD_APPEND; i != 0; i--)
	    {
	        String s = "";
	        for (int j = SIZE; j != 0; j--)
	        {
		        s = s + A;
	        }
	        count += s.Length;
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = (end - start);
        executionTime = TimeSpan.FromTicks(executionTime.Ticks * NB_STRING_CONCAT_RATIO_ADD_APPEND);
        executionTime = executionTime.Add(executionTime);
        Console.WriteLine("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS + " String With " + SIZE + " Add  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.Length   ,count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }
    

    /**
     * testStringConcatWithStringBuilder.
     *
     * @return
     */
    public static long testStringConcatWithStringBuilder()
    {
	    int count = 0;
	    DateTime start = DateTime.Now;
	    for (int i = NB_STRING_CONCAT_TESTS; i != 0; i--)
	    {
            StringBuilder s = new StringBuilder(SIZE * A.Length);
	        for (int j = SIZE; j != 0; j--)
	        {
		        s.Append(A);
	        }
	        count += s.ToString().Length;
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS + " String With " + SIZE + " StringBuilder for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.Length   ,count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
 * testStringConcatWithAdd.
 *
 * @return
 */
    public static long testStringConcatWithAdd2()
    {
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = NB_STRING_CONCAT_TESTS_2; i != 0; i--)
        {
            String s = "";
            for (int j = SIZE_2; j != 0; j--)
            {
                s = s + A;
            }
            count += s.Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS_2 + " String With " + SIZE_2 + " Add  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.Length   ,count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
     * testStringConcatWithStringBuilder.
     *
     * @return
     */
    public static long testStringConcatWithStringBuilder2()
    {
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = NB_STRING_CONCAT_TESTS_2; i != 0; i--)
        {
            StringBuilder s = new StringBuilder(SIZE * A.Length);
            for (int j = SIZE_2; j != 0; j--)
            {
                s.Append(A);
            }
            count += s.ToString().Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS_2 + " String With " + SIZE_2 + " StringBuilder for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.Length   ,count=" + count + ", snapshot time,"
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
	        executionTimes.Add(StringConcatTest.testStringConcatWithAdd());
        executionTimes.Sort();
	    Console.WriteLine("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS + " String With " + SIZE + " Add  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.Length   ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(StringConcatTest.testStringConcatWithStringBuilder());
        executionTimes.Sort();
        Console.WriteLine("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS + " String With " + SIZE + " StringBuilder  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.Length   ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(StringConcatTest.testStringConcatWithAdd2());
        executionTimes.Sort();
        Console.WriteLine("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS_2 + " String With " + SIZE_2 + " Add  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.Length   ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(StringConcatTest.testStringConcatWithStringBuilder2());
        executionTimes.Sort();
        Console.WriteLine("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS_2 + " String With " + SIZE_2 + " StringBuilder  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.Length   ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
    	

    }
  

}

