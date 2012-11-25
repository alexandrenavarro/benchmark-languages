using System;
using System.Collections.Generic;
using System.Text;


sealed class TrigoTest
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
     * TRIG_MAX
     */
    private static double  TRIG_MAX = 	10000000.0; // 10M

    /**
     * EXP_MAX
     */
    private static double EXP_MAX = 100.0; // 10M

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
     * testSin.
     *
     * @return
     */
    public static long testSin()
    {
        DateTime start = DateTime.Now;
	    double sine = 0.0;
        double i = 0.0;
	    while (i < TRIG_MAX)
	    {
		    sine = Math.Sin(i);
		    i++;
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[TrigoTest], Calculation of Sin " + NB_ARITHMETIC_TESTS + " while (i < " + TRIG_MAX + ") {sine = Math.Sin(i)  i++ }, sine=" + sine + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
     * testCos.
     *
     * @return
     */
    public static long testCos()
    {
        DateTime start = DateTime.Now;
        double cosine = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            cosine = Math.Cos(i);
            i++;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[TrigoTest], Calculation of Cos " + NB_ARITHMETIC_TESTS + " while (i < " + TRIG_MAX + ") {cosine = Math.Cos(i)  i++ }, cosine=" + cosine + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
     * testTan.
     *
     * @return
     */
    public static long testTan()
    {
        DateTime start = DateTime.Now;
        double tan = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            tan = Math.Tan(i);
            i++;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[TrigoTest], Calculation of Tan " + NB_ARITHMETIC_TESTS + " while (i < " + TRIG_MAX + ") {tan = Math.Tan(i)  i++ }, tan=" + tan + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
     * testLog.
     *
     * @return
     */
    public static long testLog()
    {
        DateTime start = DateTime.Now;
        double log = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            log = Math.Log(i);
            i++;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[TrigoTest], Calculation of Log " + NB_ARITHMETIC_TESTS + " while (i < " + TRIG_MAX + ") {log = Math.Log(i)  i++ }, log=" + log + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
     * testExp.
     *
     * @return
     */
    public static long testExp()
    {
        DateTime start = DateTime.Now;
        double exp = 0.0;
        for (int j = (int)(TRIG_MAX / EXP_MAX); j != 0; j--)
        {
            double i = 0.0;
            while (i < EXP_MAX)
            {
                exp = Math.Exp(i);
                i++;
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[TrigoTest], Calculation of Exp " + NB_ARITHMETIC_TESTS + " while (i < " + TRIG_MAX + ") {exp = Math.Exp(i)  i++ }, exp=" + exp + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testSqrt.
 *
 * @return
 */
    public static long testSqrt()
    {
        DateTime start = DateTime.Now;
        double sqrt = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            sqrt = Math.Sqrt(i);
            i++;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[TrigoTest], Calculation of Sqrt " + NB_ARITHMETIC_TESTS + " while (i < " + TRIG_MAX + ") {sqrt = Math.Sqrt(i)  i++ }, sqrt=" + sqrt + ", snapshot time,"
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
            executionTimes.Add(TrigoTest.testSin());
        executionTimes.Sort();
        Console.WriteLine("[TrigoTest], Calculation of Sin " + NB_ARITHMETIC_TESTS + " while (i < " + TRIG_MAX + ") {sine = Math.Sin(i)     i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(TrigoTest.testCos());
        executionTimes.Sort();
        Console.WriteLine("[TrigoTest], Calculation of Cos " + NB_ARITHMETIC_TESTS + " while (i < " + TRIG_MAX + ") {cosine = Math.Cos(i)     i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(TrigoTest.testTan());
        executionTimes.Sort();
        Console.WriteLine("[TrigoTest], Calculation of Tan " + NB_ARITHMETIC_TESTS + " while (i < " + TRIG_MAX + ") {tan = Math.Tan(i)     i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(TrigoTest.testLog());
        executionTimes.Sort();
        Console.WriteLine("[TrigoTest], Calculation of Log " + NB_ARITHMETIC_TESTS + " while (i < " + TRIG_MAX + ") {log = Math.Log(i)     i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(TrigoTest.testExp());
        executionTimes.Sort();
        Console.WriteLine("[TrigoTest], Calculation of Exp " + NB_ARITHMETIC_TESTS + " while (i < " + TRIG_MAX + ") {exp = Math.Exp(i)     i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(TrigoTest.testSqrt());
        executionTimes.Sort();
        Console.WriteLine("[TrigoTest], Calculation of Sqrt " + NB_ARITHMETIC_TESTS + " while (i < " + TRIG_MAX + ") {sqrt = Math.Sqrt(i)     i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

	
    }
}



