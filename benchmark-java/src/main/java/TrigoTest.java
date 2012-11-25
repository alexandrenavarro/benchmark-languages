import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ArithmeticTest.
 * 
 * @author anavarro122404 - 25 sept. 07
 * 
 * 
 * <!-- $Id: TrigoTest.java,v 1.1 2007/10/15 17:58:12 anavarro Exp $ -->.
 * 
 */
public final class TrigoTest
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
     * NB_TRIGO_TESTS
     */
    private static final int    NB_TRIGO_TESTS     = 1;
    
    /**
     * TRIG_MAX
     */
    private static final double TRIG_MAX                = 10000000.0;               // 10M
    
    /**
     * EXP_MAX
     */
    private static final double EXP_MAX                = 100.0;               
    
    
    /**
     * TWO_PI
     */
    private static final double TWO_PI = 2 * Math.PI;
    
    /**
     * HALF_PI
     */
    private static final double HALF_PI = Math.PI / 2;
    
    /**
     * QUARTER_PI
     */
    private static final double QUARTER_PI = Math.PI / 4;
                                                                                     
    /**
     * Constructor.
     * 
     */
    private TrigoTest()
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
     * testSin.
     *
     * @return
     */
    public static long testSin()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        double sine = 0.0;
        double i = 0.0f;
        while (i < TRIG_MAX)
        {
            sine = Math.sin(i);
            i++;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[TrigoTest], Calculation of Sine " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {sine = Math.sin(i)  i++ },  sine=" + sine + ", snapshot time," + executionTime);
        return executionTime;
    }
    

    /**
     * testFastSin.
     *
     * @return
     */
    public static long testFastSin()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        double sine = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            sine = sin(i);
            i++;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[TrigoTest], Calculation of Fast Sine " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {sine = sin(i)  i++ }, sine=" + sine
                + ", snapshot time," + executionTime);
        return executionTime;
    }
    

    /**
     * testCos.
     *
     * @return
     */
    public static long testCos()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        double cosine = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            cosine = Math.cos(i);
            i++;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[TrigoTest], Calculation of Cosine " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {cosine = Math.cos(i)  i++ },  cosine=" + cosine + ", snapshot time," + executionTime);
        return executionTime;
    }
    


    /**
     * testFastCos.
     *
     * @return
     */
    public static long testFastCos()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        double cosine = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            cosine = cos(i);
            i++;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[TrigoTest], Calculation of Fast Cosine " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {cosine = cos(i)  i++ }, cosine=" + cosine
                + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    

    /**
     * testTan.
     *
     * @return
     */
    public static long testTan()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        double tangente = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            tangente = Math.tan(i);
            i++;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[TrigoTest], Calculation of Tangente " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {tangente = Math.tan(i)  i++ },  tangente=" + tangente + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testFastTan.
     *
     * @return
     */
    public static long testFastTan()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        double tangente = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            tangente = tan(i);
            i++;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[TrigoTest], Calculation of Tangente " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {tangente = tan(i)  i++ },  tangente=" + tangente + ", snapshot time," + executionTime);
        return executionTime;
    }
    

    /**
     * testLog.
     *
     * @return
     */
    public static long testLog()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        double log = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            log = Math.log(i);
            i++;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[TrigoTest], Calculation of Log " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {log = Math.log(i)  i++ },  log=" + log + ", snapshot time," + executionTime);
        return executionTime;
    }
    

    /**
     * testExp.
     *
     * @return
     */
    public static long testExp()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        double exp = 0.0;
        for (int j = (int) (TRIG_MAX / EXP_MAX); j != 0 ; j-- )
        {
            double i = 0.0;
            while (i < EXP_MAX)
            {
                exp = Math.exp(i);
                i++;
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[TrigoTest], Calculation of Exp " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {exp = Math.exp(i)  i++ },  exp=" + exp + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testSqrt.
     *
     * @return
     */
    public static long testSqrt()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        double sqrt = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            sqrt = Math.sqrt(i);
            i++;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[TrigoTest], Calculation of Sqrt " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {sqrt = Math.sqrt(i)  i++ },  sqrt=" + sqrt + ", snapshot time," + executionTime);
        return executionTime;
    }

    
    
    /**
     * reduceSinAngle.
     *
     * @param d
     * @return
     */
    public static double reduceSinAngle(double d)
    {
        d %= TWO_PI;
        if (Math.abs(d) > Math.PI)
            d -= TWO_PI;
        if (Math.abs(d) > HALF_PI)
            d = Math.PI - d;
        return d;
    }
    
    /**
     * sin.
     *
     * @param d
     * @return
     */
    public static double sin(double d)
    {
        d = reduceSinAngle(d);
        if (Math.abs(d) <= QUARTER_PI)
            return Math.sin(d);
        return Math.cos(HALF_PI - d);
    }

    /**
     * cos.
     *
     * @param d
     * @return
     */
    public static double cos(double d)
    {
        return sin(d + HALF_PI);
    }
    
    /**
     * tangent.
     *
     * @param d
     * @return
     */
    public static double tan(double d)
    {
        return sin(d) / cos(d);
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
            executionTimes.add(TrigoTest.testSin());
        System.out.println("[TrigoTest], Calculation of Sine " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {sine = Math.sin(i)  i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time,"
                + Collections.min(executionTimes)
                + ", max time,"
                + Collections.max(executionTimes)                
                + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
        );
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(TrigoTest.testFastSin());
        System.out.println("[TrigoTest], Calculation of Fast Sine " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {sine = sin(i)  i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time,"
                + Collections.min(executionTimes)
                + ", max time,"
                + Collections.max(executionTimes)                
                + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                );
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(TrigoTest.testCos());
        System.out.println("[TrigoTest], Calculation of Cosine " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {cosine = Math.cos(i)  i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time,"
                + Collections.min(executionTimes)
                + ", max time,"
                + Collections.max(executionTimes)                
                + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                );
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(TrigoTest.testFastCos());
        System.out.println("[TrigoTest], Calculation of Fast Cosine " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {cosine = cos(i)  i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time,"
                + Collections.min(executionTimes)
                + ", max time,"
                + Collections.max(executionTimes)                
                + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                );
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(TrigoTest.testTan());
        System.out.println("[TrigoTest], Calculation of Tangente " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {tangente = Math.tan(i)  i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time,"
                + Collections.min(executionTimes)
                + ", max time,"
                + Collections.max(executionTimes)                
                + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                );
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(TrigoTest.testFastTan());
        System.out.println("[TrigoTest], Calculation of Fast Tangente " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {tangente = tan(i)  i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time,"
                + Collections.min(executionTimes)
                + ", max time,"
                + Collections.max(executionTimes)                
                + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                );
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(TrigoTest.testLog());
        System.out.println("[TrigoTest], Calculation of Log " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {log = Math.log(i)  i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time,"
                + Collections.min(executionTimes)
                + ", max time,"
                + Collections.max(executionTimes)                
                + ", deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                );
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(TrigoTest.testExp());
        System.out.println("[TrigoTest], Calculation of Exp " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {exp = Math.exp(i)  i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time,"
                + Collections.min(executionTimes)
                + ", max time,"
                + Collections.max(executionTimes)                
                + ", deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                );
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(TrigoTest.testSqrt());
        System.out.println("[TrigoTest], Calculation of Sqrt " + NB_TRIGO_TESTS + " while (i < " + TRIG_MAX
                + ") {sqrt = Math.sqrt(i)  i++ },, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time,"
                + Collections.min(executionTimes)
                + ", max time,"
                + Collections.max(executionTimes)                
                + ", relative deviation,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                );
        executionTimes.clear();
        
    }

}
