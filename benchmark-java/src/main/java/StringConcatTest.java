import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * StringConcatTest.
 * 
 * @author anavarro122404 - 25 sept. 07
 * 
 * 
 * <!-- $Id: StringConcatTest.java,v 1.1 2007/10/15 17:58:12 anavarro Exp $ -->.
 * 
 */
public final class StringConcatTest
{
    /**
     * NB_TESTS
     */
    private static final int    NB_TESTS                          = 5;
    
    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    private static final int    NB_OF_EXCLUSION_MIN_MAX           = 2;
    
    /**
     * NB_STRING_CONCAT_TESTS
     */
    private static final int    NB_STRING_CONCAT_WITH_ADD_TESTS   = 10 * 1000;
    
    /**
     * NB_STRING_CONCAT_TESTS
     */
    private static final int    NB_STRING_CONCAT_TESTS            = 100 * 1000;

    /**
     * NB_STRING_CONCAT_RATIO_ADD_APPEND
     * 
     * This variable is used to reduce the number of test with + by multiplying the time execution in order to reduce time to calculate it
     */
    private static final int    NB_STRING_CONCAT_RATIO_ADD_APPEND            = 10;
        
    /**
     * NB_STRING_CONCAT_TESTS
     */
    private static final int    NB_STRING_CONCAT_TESTS_2          = 10 * 1000 * 1000;
    
    /**
     * SIZE
     */
    private static final int    SIZE                              = 1000;
    
    /**
     * SIZE
     */
    private static final int    SIZE_2                            = 3;
    
    /**
     * A
     */
    private static final String A                                 = "a";
    
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
     * testStringConcatWithAdd.
     * 
     * @return
     */
    public static long testStringConcatWithAdd()
    {
        int count = 0;
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_STRING_CONCAT_TESTS / NB_STRING_CONCAT_RATIO_ADD_APPEND; i != 0; i--)
        {
            String s = "";
            for (int j = SIZE; j != 0; j--)
            {
                s = s + A;
            }
            count += s.length();
        }
        long end = System.currentTimeMillis();
        long executionTime = (end - start) * NB_STRING_CONCAT_RATIO_ADD_APPEND;
        System.out.println("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS + " String With " + SIZE
                + " Add  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.length()   ,count=" + count + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testStringConcatWithStringBuffer.
     * 
     * @return
     */
    public static long testStringConcatWithStringBuffer()
    {
        int count = 0;
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_STRING_CONCAT_TESTS; i != 0; i--)
        {
            final StringBuffer s = new StringBuffer(SIZE * A.length());
            for (int j = SIZE; j != 0; j--)
            {
                s.append(A);
            }
            count += s.toString().length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS + " String With " + SIZE
                + "StringBuffer  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.length()   ,count=" + count + ", snapshot time,"
                + executionTime);
        return executionTime;
    }
    
    /**
     * testStringConcatWithStringBuilder.
     * 
     * @return
     */
    public static long testStringConcatWithStringBuilder()
    {
        int count = 0;
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_STRING_CONCAT_TESTS; i != 0; i--)
        {
            final StringBuilder s = new StringBuilder(SIZE * A.length());
            for (int j = SIZE; j != 0; j--)
            {
                s.append(A);
            }
            count += s.toString().length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS + " String With " + NB_STRING_CONCAT_WITH_ADD_TESTS
                + "StringBuilder for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.length()   ,count=" + count + ", snapshot time,"
                + executionTime);
        return executionTime;
    }
    
    /**
     * testStringConcatWithAdd.
     * 
     * @return
     */
    public static long testStringConcatWithAdd2()
    {
        int count = 0;
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_STRING_CONCAT_TESTS_2; i != 0; i--)
        {
            String s = "";
            for (int j = SIZE_2; j != 0; j--)
            {
                s = s + A;
            }
            count += s.length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS_2 + " String With " + SIZE_2
                + " Add  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.length()   ,count=" + count + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testStringConcatWithStringBuffer.
     * 
     * @return
     */
    public static long testStringConcatWithStringBuffer2()
    {
        int count = 0;
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_STRING_CONCAT_TESTS_2; i != 0; i--)
        {
            final StringBuffer s = new StringBuffer(SIZE_2 * A.length());
            for (int j = SIZE_2; j != 0; j--)
            {
                s.append(A);
            }
            count += s.toString().length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS_2 + " String With " + SIZE_2
                + " StringBuffer  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.length()   ,count=" + count + ", snapshot time,"
                + executionTime);
        return executionTime;
    }
    
    /**
     * testStringConcatWithStringBuilder.
     * 
     * @return
     */
    public static long testStringConcatWithStringBuilder2()
    {
        int count = 0;
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_STRING_CONCAT_TESTS_2; i != 0; i--)
        {
            final StringBuilder s = new StringBuilder(SIZE_2 * A.length());
            for (int j = SIZE_2; j != 0; j--)
            {
                s.append(A);
            }
            count += s.toString().length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS_2 + " String With " + SIZE_2
                + " StringBuilder for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.length()   ,count=" + count + ", snapshot time,"
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
            executionTimes.add(StringConcatTest.testStringConcatWithAdd());
        System.out.println("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS + " String With " + SIZE
                + " Add  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.length()   ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(StringConcatTest.testStringConcatWithStringBuffer());
        System.out.println("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS + " String With " + SIZE
                + " StringBuffer  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.length()   ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(StringConcatTest.testStringConcatWithStringBuilder());
        System.out.println("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS + " String With " + SIZE
                + " StringBuilder for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.length()   ,, snapshot time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(StringConcatTest.testStringConcatWithAdd2());
        System.out.println("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS_2 + " String With " + SIZE_2
                + " Add  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.length()   ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(StringConcatTest.testStringConcatWithStringBuffer2());
        System.out.println("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS_2 + " String With " + SIZE_2
                + " StringBuffer  for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.length()   ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(StringConcatTest.testStringConcatWithStringBuilder2());
        System.out.println("[StringConcatTest], Concatenation of " + NB_STRING_CONCAT_TESTS_2 + " String With " + SIZE_2
                + " StringBuilder for (int j = SIZE   j != 0   j--) {s = s + A  } count += s.length()   ,, snapshot time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
    }

}
