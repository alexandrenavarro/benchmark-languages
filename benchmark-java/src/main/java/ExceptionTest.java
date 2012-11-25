import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ExceptionTest.
 * 
 * @author anavarro122404 - 25 sept. 07
 * 
 * 
 * <!-- $Id: ExceptionTest.java,v 1.3 2008/01/10 10:55:58 anavarro Exp $ -->.
 * 
 */
public final class ExceptionTest
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
     * NB_EXCEPTION_TESTS
     */
    private static final int NB_EXCEPTION_TESTS      = 1000 * 1000;
    
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
     * testUncheckedException.
     * 
     * @return
     */
    public static long testUncheckedException()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_EXCEPTION_TESTS; i != 0; i--)
        {
            try
            {
                throw new RuntimeException();
            }
            catch (RuntimeException e)
            {
                //
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ExceptionTest], Throw and catch RuntimeException " + NB_EXCEPTION_TESTS
                + " try {throw new RuntimeException()  } catch (RuntimeException e) {},, snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testUncheckedException.
     * 
     * @return
     */
    public static long testCheckedException()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_EXCEPTION_TESTS; i != 0; i--)
        {
            try
            {
                throw new Exception();
            }
            catch (Exception e)
            {
                //
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ExceptionTest], Throw and catch CheckedException " + NB_EXCEPTION_TESTS
                + " try {throw new Exception()  } catch (Exception e) {},, snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testUncheckedExceptionWithDeepTry.
     * 
     * @return
     */
    public static long testUncheckedExceptionWithDeepTry()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
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
                                throw new RuntimeException();
                            }
                            catch (RuntimeException e)
                            {
                                throw e;
                            }
                        }
                        catch (RuntimeException e)
                        {
                            throw e;
                        }
                    }
                    catch (RuntimeException e)
                    {
                        throw e;
                    }
                }
                catch (RuntimeException e)
                {
                    throw e;
                }
            }
            catch (RuntimeException e)
            {
                //
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ExceptionTest], Throw and catch RuntimeException with deep try (5) " + NB_EXCEPTION_TESTS
                + " try { try { try { try { try {throw new RuntimeException()  } catch (RuntimeException e) {},, snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testUncheckedExceptionWithDeepTry.
     * 
     * @return
     */
    public static long testCheckedExceptionWithDeepTry()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
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
                //
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ExceptionTest], Throw and catch CheckedException with deep try (5) " + NB_EXCEPTION_TESTS
                + " try { try { try { try { try {throw new Exception()  } catch (Exception e) {},, snapshot time," + executionTime);
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
            executionTimes.add(ExceptionTest.testUncheckedException());
        System.out.println("[ExceptionTest], Throw and catch UncheckedException " + NB_EXCEPTION_TESTS
                + " try {throw new RuntimeException()} catch (RuntimeException e) {},, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ExceptionTest.testCheckedException());
        System.out.println("[ExceptionTest], Throw and catch CheckedException " + NB_EXCEPTION_TESTS
                + " try {throw new Exception()} catch (Exception e) {},, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ExceptionTest.testUncheckedExceptionWithDeepTry());
        System.out.println("[ExceptionTest], Throw and catch UncheckedException with deep try (5) " + NB_EXCEPTION_TESTS
                + " try { try { try { try { try {throw new RuntimeException()} catch (RuntimeException e) {},, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ExceptionTest.testCheckedExceptionWithDeepTry());
        System.out.println("[ExceptionTest], Throw and catch CheckedException with deep try (5) " + NB_EXCEPTION_TESTS
                + " try { try { try { try { try {throw new Exception()} catch (Exception e) {},, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
    }
}
