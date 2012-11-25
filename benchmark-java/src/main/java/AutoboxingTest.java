import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AutoboxingTest.
 * 
 * @author anavarro122404 - 25 sept. 07
 *
 *
 * <!-- $Id: AutoboxingTest.java,v 1.1 2007/10/15 17:58:12 anavarro Exp $ -->.
 *
 */
public final class AutoboxingTest
{
    /**
     * NB_TESTS
     */
    private static final int NB_TESTS                 = 5;
    
    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    private static final int NB_OF_EXCLUSION_MIN_MAX  = 2;
    
    /**
     * NB_AUTOBOXING_TESTS
     */
    private static final int NB_AUTOBOXING_TESTS      = 200 * 1000 * 1000;
    
    
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
     * testBoxingIntegerAndToString.
     * 
     * @return
     */
    public static long testBoxingIntegerAndToString()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_AUTOBOXING_TESTS; i != 0; i--)
        {
            final Integer integer = new Integer(i);
            final String s = integer.toString();
            count += s.length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[AutoBoxingTest], testBoxingIntegerAndToString " + NB_AUTOBOXING_TESTS
                + " final Integer integer = new Integer(i)   final String s = integer.toString()  ,count=" + count + ", snapshot time,"
                + executionTime);
        return executionTime;
    }
    
    /**
     * testBoxingIntegerWithNew.
     * 
     * @return
     */
    public static long testBoxingInteger()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_AUTOBOXING_TESTS; i != 0; i--)
        {
            @SuppressWarnings("unused")
            final Integer integer = new Integer(i);
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[AutoBoxingTest], testBoxingIntegerWithNew " + NB_AUTOBOXING_TESTS
                + " final Integer integer = new Integer(i)  ,, snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testUnboxingInteger.
     *
     * @return
     */
    public static long testUnboxingInteger()
    {
        Object integer = new Integer(123456);
        long start = System.currentTimeMillis();
        for (int i = NB_AUTOBOXING_TESTS; i != 0; i--)
        {
            @SuppressWarnings("unused")
            final int k = ((Integer) integer).intValue();
        }
        
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[AutoBoxingTest], testUnboxingInteger " + NB_AUTOBOXING_TESTS + " count += integers[i]; ,, snapshot time,"
                + executionTime);
        return executionTime;
    }
    
    /**
     * testBoxingIntegerWithValueOf.
     * 
     * @return
     */
    public static long testBoxingUnboxingInteger()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_AUTOBOXING_TESTS; i != 0; i--)
        {
            final Integer integer = Integer.valueOf(i);
            @SuppressWarnings("unused")
            int k = integer.intValue();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[AutoBoxingTest], testBoxingUnboxingInteger " + NB_AUTOBOXING_TESTS
                + " final Integer integer = Integer.valueOf(i)   int k = integer.intValue()  ,, snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testBoxingIntegerAndToString.
     *
     * @return
     */
    public static long testBoxingUnboxingDouble()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_AUTOBOXING_TESTS; i != 0; i--)
        {
            final Double d = Double.valueOf(i);
            @SuppressWarnings("unused")
            double k = d.doubleValue();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[AutoBoxingTest], testBoxingUnboxingDouble " + NB_AUTOBOXING_TESTS
                + " final Double d = Double.valueOf(i)   double k = d.doubleValue()  ,, snapshot time," + executionTime);
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
            executionTimes.add(AutoboxingTest.testUnboxingInteger());
        System.out.println("[AutoBoxingTest], testBoxingIntegerWithNew " + NB_AUTOBOXING_TESTS + " count += integers[i]; ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(AutoboxingTest.testBoxingInteger());
        System.out.println("[AutoBoxingTest], testBoxingIntegerWithNew " + NB_AUTOBOXING_TESTS
                + " final Integer integer = new Integer(i)  ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(AutoboxingTest.testBoxingUnboxingInteger());
        System.out.println("[AutoBoxingTest], testBoxingUnboxingInteger " + NB_AUTOBOXING_TESTS
                + " final Integer integer = Integer.valueOf(i)   int k = integer.intValue()  ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(AutoboxingTest.testBoxingUnboxingDouble());
        System.out.println("[AutoBoxingTest], testBoxingUnboxingDouble " + NB_AUTOBOXING_TESTS
                + " final Double d = Double.valueOf(i)   double k = d.doubleValue()  ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
    }
    
}
