import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ArithmeticTest.
 * 
 * @author anavarro122404 - 25 sept. 07
 * 
 * 
 * <!-- $Id: ArithmeticTest.java,v 1.1 2007/10/15 17:58:12 anavarro Exp $ -->.
 * 
 */
public final class ArithmeticTest
{
    /**
     * NB_TESTS
     */
    private static final int    NB_TESTS                = 5;
    
    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    private static final int    NB_OF_EXCLUSION_MIN_MAX = 2;
    
    /**
     * NB_CREATION_TESTS
     */
    private static final int    NB_ARITHMETIC_TESTS     = 1;
    
    /**
     * INT_MAX
     */
    private static final int    INT_MAX                 = 1 * 1000 * 1000 * 1000;
    
    /**
     * DOUBLE_MIN
     */
    private static final double DOUBLE_MIN              = 10 * 1000 * 1000 * 1000.0;
    
    /**
     * DOUBLE_MAX
     */
    private static final double DOUBLE_MAX              = 11 * 1000 * 1000 * 1000.0;
    
    /**
     * LONG_MIN
     */
    private static final long   LONG_MIN                = 10 * 1000 * 1000 * 1000L;
    
    /**
     * LONG_MAX
     */
    private static final long   LONG_MAX                = 11 * 1000 * 1000 * 1000L;
    
    /**
     * Constructor.
     * 
     */
    private ArithmeticTest()
    {
        //
    }
    
    
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
     * testIntArithmetic.
     * 
     * @return
     */
    public static long testIntArithmetic()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        int intResult = 1;
        int i = 1;
        while (i < INT_MAX)
        {
            intResult -= i++;
            intResult += i++;
            intResult *= i++;
            intResult /= i++;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ArithmeticTest], Calculation of " + NB_ARITHMETIC_TESTS + " int intResult = 1  int i = 1  while (i < " + INT_MAX
                + ") {intResult -= i++  intResult += i++  intResult *= i++  intResult /= i++},intResult=" + intResult + ", snapshot time,"
                + executionTime);
        return executionTime;
    }
    
    /**
     * testDoubleArithmetic.
     * 
     * @return
     */
    public static long testDoubleArithmetic()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        double doubleResult = DOUBLE_MIN;
        double i = DOUBLE_MIN;
        while (i < DOUBLE_MAX)
        {
            doubleResult -= i++;
            doubleResult += i++;
            doubleResult *= i++;
            doubleResult /= i++;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ArithmeticTest], Calculation of " + NB_ARITHMETIC_TESTS + " double doubleResult = " + DOUBLE_MIN + " double i = "
                + DOUBLE_MIN + "  while (i < " + DOUBLE_MAX
                + ") {doubleResult -= i++  doubleResult += i++  doubleResult *= i++  doubleResult /= i++ },doubleResult=" + doubleResult
                + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testLongArithmetic.
     * 
     * @return
     */
    public static long testLongArithmetic()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        long longResult = LONG_MIN;
        long i = LONG_MIN;
        while (i < LONG_MAX)
        {
            longResult -= i++;
            longResult += i++;
            longResult *= i++;
            longResult /= i++;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ArithmeticTest], Calculation of " + NB_ARITHMETIC_TESTS + " long longResult = " + LONG_MIN + "  long i = " + LONG_MIN
                + "  while (i < " + LONG_MAX + ") { longResult -= i++  longResult += i++  longResult *= i++  longResult /= i++ },longResult="
                + longResult + ", snapshot time," + executionTime);
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
            executionTimes.add(ArithmeticTest.testIntArithmetic());
        System.out.println("[ArithmeticTest], Calculation of " + NB_ARITHMETIC_TESTS + " int intResult = 1  int i = 1  while (i < " + INT_MAX
                + ") {intResult -= i++  intResult += i++  intResult *= i++  intResult /= i++ },, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ArithmeticTest.testDoubleArithmetic());
        System.out.println("[ArithmeticTest], Calculation of " + NB_ARITHMETIC_TESTS + " double doubleResult = " + DOUBLE_MIN + "  double i = "
                + DOUBLE_MIN + "  while (i < " + DOUBLE_MAX
                + ") {doubleResult -= i++  doubleResult += i++  doubleResult *= i++  doubleResult /= i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ArithmeticTest.testLongArithmetic());
        System.out.println("[ArithmeticTest], Calculation of " + NB_ARITHMETIC_TESTS + " long longResult = " + LONG_MIN + "  long i = " + LONG_MIN
                + "  while (i < " + LONG_MAX + ") { longResult -= i++  longResult += i++  longResult *= i++  longResult /= i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
    }
}
