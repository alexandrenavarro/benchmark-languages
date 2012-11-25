import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * EnumTest.
 * 
 * @author Alexandre
 * 
 */
public final class EnumTest
{
    /**
     * NB_TESTS
     */
    private static final int NB_TESTS                   = 5;

    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    private static final int NB_OF_EXCLUSION_MIN_MAX    = 2;

    /**
     * NB_ENUM_TESTS
     */
    private static final int NB_ENUM_TESTS              = 2 * 1000 * 1000;

    /**
     * NB_ENUM_PARSE_SWITCH_TESTS
     */
    private static final int NB_ENUM_PARSE_SWITCH_TESTS = 10 * 1000 * 1000;


    
    /**
     * Added to achieve to compile with the groovy compiler
     */

    @SuppressWarnings("unused")
    private static final EnumTest.NumberEnum ONE = EnumTest.NumberEnum.ONE;
    @SuppressWarnings("unused")
    private static final EnumTest.NumberEnum TWO = EnumTest.NumberEnum.TWO;
    @SuppressWarnings("unused")
    private static final EnumTest.NumberEnum THREE = EnumTest.NumberEnum.THREE;
    @SuppressWarnings("unused")
    private static final EnumTest.NumberEnum FOUR = EnumTest.NumberEnum.FOUR;
    @SuppressWarnings("unused")
    private static final EnumTest.NumberEnum FIVE = EnumTest.NumberEnum.FIVE;
    @SuppressWarnings("unused")
    private static final EnumTest.NumberEnum SIX = EnumTest.NumberEnum.SIX;
    @SuppressWarnings("unused")
    private static final EnumTest.NumberEnum SEVEN = EnumTest.NumberEnum.SEVEN;
    @SuppressWarnings("unused")
    private static final EnumTest.NumberEnum EIGHT = EnumTest.NumberEnum.EIGHT;
    @SuppressWarnings("unused")
    private static final EnumTest.NumberEnum NINE = EnumTest.NumberEnum.NINE;
    @SuppressWarnings("unused")
    private static final EnumTest.NumberEnum TEN = EnumTest.NumberEnum.TEN;


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
     * testCurrentTime.
     * 
     * @return
     */
    public static long testEnumGetValues()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_ENUM_TESTS; i != 0; i--)
        {
            // EnumTest.NumberEnum instead of NumberEnum because of groovy
            final EnumTest.NumberEnum[] numbers = EnumTest.NumberEnum.values();
            count += numbers.length;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[EnumTest], Invoke of " + NB_ENUM_TESTS + " final Number[] numbers = Number.values(), count=" + count
                + ", snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testEnumParseAndSwitch.
     * 
     * @return
     */
    public static long testEnumParseAndSwitch()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_ENUM_PARSE_SWITCH_TESTS; i != 0; i--)
        {
            // EnumTest.NumberEnum instead of NumberEnum because of groovy
            final EnumTest.NumberEnum number = EnumTest.NumberEnum.valueOf("ONE");
            
            switch (number)
            {
            case ONE:
                count = count + 1;
                break;
            case TWO:
                count = count + 2;
                break;
            case THREE:
                count = count + 3;
                break;
            case FOUR:
                count = count + 4;
                break;
            case FIVE:
                count = count + 5;
                break;
            case SIX:
                count = count + 6;
                break;
            case SEVEN:
                count = count + 7;
                break;
            case EIGHT:
                count = count + 8;
                break;
            case NINE:
                count = count + 9;
                break;
            case TEN:
                count = count + 10;
                break;
            default:
                break;
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[EnumTest], Invoke of " + NB_ENUM_PARSE_SWITCH_TESTS + " final Number number = Number.valueOf(\"ONE\") switch (number)..., count="
                + count + ", snapshot time," + executionTime);
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
            executionTimes.add(EnumTest.testEnumGetValues());
        System.out.println("[EnumTest], Invoke of " + NB_ENUM_TESTS + " final Number[] numbers = Number.values(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(EnumTest.testEnumParseAndSwitch());
        System.out.println("[EnumTest], Invoke of " + NB_ENUM_PARSE_SWITCH_TESTS + " final Number[] numbers = Number.values(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

    }

    /**
     * NUMBER.
     * 
     * @author anavarro122404 - 25 Oct 2007
     *
     *
     *
     */
    public static enum NumberEnum
    {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN
    }
}

