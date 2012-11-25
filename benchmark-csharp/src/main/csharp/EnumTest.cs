using System;
using System.Collections.Generic;
using System.Text;

sealed class EnumTest
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
    private static int NB_ENUM_TESTS = 2 * 1000 * 1000;

    /**
     * NB_CREATION_TESTS
     */
    private static int NB_ENUM_PARSE_SWITCH_TESTS = 10 * 1000 * 1000;

    public enum Number
    {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN
    }

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
     * testCurrentTime.
     *
     * @return
     */
    public static long testEnumGetValues()
    {
	    DateTime start = DateTime.Now;
        for (int i = NB_ENUM_TESTS; i != 0; i--)
	    {
            Number[] numbers = (Number[]) Enum.GetValues(typeof(Number));
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[EnumTest], Invoke of " + NB_ENUM_TESTS + " Number[] numbers = (Number[]) Enum.GetValues(typeof(Number)),, snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }

    /**
 * testCurrentTime.
 *
 * @return
 */
    public static long testEnumParseAndSwitch()
    {
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = NB_ENUM_PARSE_SWITCH_TESTS; i != 0; i--)
        {
            Number number = (Number) Enum.Parse(typeof(Number), "ONE");
            switch (number)
            {
                case Number.ONE:
                    count = count + 1;
                    break;
                case Number.TWO:
                    count = count + 2;
                    break;
                case Number.THREE:
                    count = count + 3;
                    break;
                case Number.FOUR:
                    count = count + 4;
                    break;
                case Number.FIVE:
                    count = count + 5;
                    break;
                case Number.SIX:
                    count = count + 6;
                    break;
                case Number.SEVEN:
                    count = count + 7;
                    break;
                case Number.EIGHT:
                    count = count + 8;
                    break;
                case Number.NINE:
                    count = count + 9;
                    break;
                case Number.TEN:
                    count = count + 10;
                    break;
                default:
                    break;
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[EnumTest], Invoke of " + NB_ENUM_PARSE_SWITCH_TESTS + "  Number number = (Number) Enum.Parse(typeof(Number)  \"ONE\") switch (number)..., count=" + count + ", snapshot time,"
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
            executionTimes.Add(EnumTest.testEnumGetValues());
        executionTimes.Sort();
        Console.WriteLine("[EnumTest], Invoke of " + NB_ENUM_TESTS + " Number[] numbers = (Number[]) Enum.GetValues(typeof(Number)),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
        

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(EnumTest.testEnumParseAndSwitch());
        executionTimes.Sort();
        Console.WriteLine("[EnumTest], Invoke of " + NB_ENUM_PARSE_SWITCH_TESTS + " Number number = (Number) Enum.Parse(typeof(Number)  \"ONE\") switch (number)...,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
    }

}

