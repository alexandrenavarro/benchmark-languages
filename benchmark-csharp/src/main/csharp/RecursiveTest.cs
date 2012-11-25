using System;
using System.Collections.Generic;
using System.Text;

/**
 * RecursiveTest.
 *
 * @author Alexandre
 *
 */
sealed class RecursiveTest
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
     * NB_RECURSIVE_TESTS
     */
    private static int NB_RECURSIVE_TESTS = 1;
    
    /**
     * FIBO_N
     */
    private static int FIBO_N = 41;
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
     * testFibo1.
     *
     * @return
     */
    public static long testFiboStatic()
    {
        int fibo = 0;
	    DateTime start = DateTime.Now;
	    for (int i = NB_RECURSIVE_TESTS; i!= 0; i--)
	    {
	        fibo = FiboInstance.fibStatic(FIBO_N);
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " fibo = FiboInstance.fibStatic(" + FIBO_N + ")  , fibo=" + fibo + " , snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
    

    /**
 * testFibo2.
 *
 * @return
 */
    public static long testFiboInstance()
    {
        FiboInstance fiboInstance = new FiboInstance();
        int fibo = 0;
        DateTime start = DateTime.Now;
        for (int i = NB_RECURSIVE_TESTS; i != 0; i--)
        {
            fibo = fiboInstance.fib(FIBO_N);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " fibo = fiboInstance.fib(" + FIBO_N + ")  , fibo=" + fibo + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }



    /**
    * testFibo2.
    *
    * @return
    */
    public static long testFiboInstanceImplWithInterface()
    {
        Fibo fibo = new FiboImpl();
        int fiboResult = 0;
        DateTime start = DateTime.Now;
        for (int i = NB_RECURSIVE_TESTS; i != 0; i--)
        {
            fiboResult = fibo.fib(FIBO_N);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " fiboResult = fibo.fib(" + FIBO_N + ")  , fibo=" + fiboResult + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }



    /**
* testFibo2.
*
* @return
*/
    public static long testFiboInstanceImplWithoutInterface()
    {
        FiboImpl fiboImpl = new FiboImpl();
        int fibo = 0;
        DateTime start = DateTime.Now;
        for (int i = NB_RECURSIVE_TESTS; i != 0; i--)
        {
            fibo = fiboImpl.fib(FIBO_N);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " fibo = fiboImpl.fib(" + FIBO_N + ")  , fibo=" + fibo + ", snapshot time,"
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
            executionTimes.Add(RecursiveTest.testFiboStatic());
        executionTimes.Sort();
        Console.WriteLine("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " fibo = FiboInstance.fib(" + FIBO_N + ")  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(RecursiveTest.testFiboInstance());
        executionTimes.Sort();
        Console.WriteLine("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " fibo = fiboInstance.fib(" + FIBO_N + ")  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(RecursiveTest.testFiboInstanceImplWithInterface());
        executionTimes.Sort();
        Console.WriteLine("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " fiboResult = fibo.fib(" + FIBO_N + ")  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(RecursiveTest.testFiboInstanceImplWithoutInterface());
        executionTimes.Sort();
        Console.WriteLine("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " fibo = fiboImpl.fib(" + FIBO_N + ")  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
	
    }
    
    
    
}

/**
 * Fibo.
 *
 * @author Alexandre
 *
 */
sealed class FiboInstance
{

    /**
     * fib.
     *
     * @param n
     * @return
     */
    public static int fibStatic(int n)
    {
	    if (n < 2)
        //if (n == 0 || n == 1)
	        return (1);
        return (fibStatic(n - 2) + fibStatic(n - 1));
    }

    /**
 * fib.
 *
 * @param n
 * @return
 */
    public int fib(int n)
    {
        if (n < 2)
        //if (n == 0 || n == 1)
            return (1);
        return (fib(n - 2) + fib(n - 1));
    }
}

interface Fibo
{
    int fib(int n);
}

/**
 * Fibo.
 *
 * @author Alexandre
 *
 */
sealed class FiboImpl : Fibo
{

/**
 * fib.
 *
 * @param n
 * @return
 */
    public int fib(int n)
    {
        if (n < 2)
        //if (n == 0 || n == 1)
            return (1);
        return (fib(n - 2) + fib(n - 1));
    }
}