import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * RecursiveTest.
 * 
 * @author anavarro122404 - 25 sept. 07
 *
 *
 * <!-- $Id: RecursiveTest.java,v 1.2 2007/11/20 13:17:31 anavarro Exp $ -->.
 *
 */
public final class RecursiveTest
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
     * NB_RECURSIVE_TESTS
     */
    private static final int NB_RECURSIVE_TESTS      = 1;

    /**
     * FIBO_N
     */
    private static final int FIBO_N                  = 41;

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
     * testFiboStatic.
     * 
     * @return
     */
    public static long testFiboStatic()
    {
        long start = System.currentTimeMillis();
        int fibo = 0;
        start = System.currentTimeMillis();
        for (int i = NB_RECURSIVE_TESTS; i-- != 0;)
        {
            fibo = FiboInstance.fibStatic(FIBO_N);
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " int fibo = FiboInstance.fibStatic(" + FIBO_N + "), fibo=" + fibo + ", snapshot time,"
                + executionTime);
        return executionTime;
    }
    
    /**
     * testFibo2.
     * 
     * @return
     */
    public static long testFiboInstance()
    {
        long start = System.currentTimeMillis();
        int fibo = 0;
        final FiboInstance fiboInstance = new FiboInstance();
        start = System.currentTimeMillis();
        for (int i = NB_RECURSIVE_TESTS; i-- != 0;)
        {
            fibo = fiboInstance.fib(FIBO_N);
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[RecursiveTest], Invoke of  " + NB_RECURSIVE_TESTS + " int fibo = fiboInstance.fib(" + FIBO_N + "), fibo=" + fibo + ", snapshot time,"
                + executionTime);
        return executionTime;
    }

    
    
    /**
     * testFiboInstanceImplWithInterface.
     * 
     * @return
     */
    public static long testFiboInstanceImplWithInterface()
    {
        long start = System.currentTimeMillis();
        int fiboResult = 0;
        final Fibo fibo = new FiboImpl();
        start = System.currentTimeMillis();
        for (int i = NB_RECURSIVE_TESTS; i-- != 0;)
        {
            fiboResult = fibo.fib(FIBO_N);
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " fiboResult = fibo.fib(" + FIBO_N + "), fibo=" + fiboResult + ", snapshot time,"
                + executionTime);
        return executionTime;
    }
    

    
    /**
     * testFiboInstanceImplWithoutInterface.
     * 
     * @return
     */
    public static long testFiboInstanceImplWithoutInterface()
    {
        long start = System.currentTimeMillis();
        int fibo = 0;
        final FiboImpl fiboImpl = new FiboImpl();
        start = System.currentTimeMillis();
        for (int i = NB_RECURSIVE_TESTS; i-- != 0;)
        {
            fibo = fiboImpl.fib(FIBO_N);
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " fibo = fiboImpl.fib(" + FIBO_N + "), fibo=" + fibo + ", snapshot time,"
                + executionTime);
        return executionTime;
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
            executionTimes.add(RecursiveTest.testFiboStatic());
        System.out.println("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " int fibo = FiboInstance.fibStatic(" + FIBO_N + "),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(RecursiveTest.testFiboInstance());
        System.out.println("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " int fibo = fiboInstance.fib(" + FIBO_N + "),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(RecursiveTest.testFiboInstanceImplWithInterface());
        System.out.println("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " fiboResult = fibo.fib(" + FIBO_N + "),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(RecursiveTest.testFiboInstanceImplWithoutInterface());
        System.out.println("[RecursiveTest], Invoke of " + NB_RECURSIVE_TESTS + " fibo = fiboImpl.fib(" + FIBO_N + "),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
    }
    


}

/**
 * Fibo.
 * 
 * @author anavarro122404 - 25 sept. 07
 *
 *
 * <!-- $Id: RecursiveTest.java,v 1.2 2007/11/20 13:17:31 anavarro Exp $ -->.
 *
 */
final class FiboInstance
{

    /**
     * fib.
     *
     * @param n
     * @return
     */
    public final static int fibStatic(final int n)
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
    public int fib(final int n)
    {
        if (n < 2)
        //if (n == 0 || n == 1)
            return (1);
        return (fib(n - 2) + fib(n - 1));
    }

}

/**
 * Fibonacci.
 * 
 * @author anavarro122404 - 7 Nov 2007
 *
 *
 * <!-- $Id: RecursiveTest.java,v 1.2 2007/11/20 13:17:31 anavarro Exp $ -->.
 *
 */
interface Fibo
{
    /**
     * fib.
     *
     * @param n
     * @return
     */
    public abstract int fib(final int n);
    
}

/**
 * FibonacciImpl.
 * 
 * @author anavarro122404 - 8 Nov 2007
 *
 *
 * <!-- $Id: RecursiveTest.java,v 1.2 2007/11/20 13:17:31 anavarro Exp $ -->.
 *
 */
final class FiboImpl implements Fibo
{
    /**
     * fib.
     *
     * @param n
     * @return
     */
    public int fib(final int n)
    {
        if (n < 2)
            return (1);
        return (fib(n - 2) + fib(n - 1));
    }

}

