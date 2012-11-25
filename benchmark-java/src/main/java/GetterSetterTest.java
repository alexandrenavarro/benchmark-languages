import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * CreationGetterSetterTest.
 *
 * @author anavarro122404 - 25 sept. 07
 *
 *
 */
public final class GetterSetterTest
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
     * NB_SETTER_GETTER_INT_TESTS
     */
    private static final int NB_SETTER_GETTER_INT_TESTS  = 500 * 1000 * 1000;
    
    /**
     * NB_SETTER_GETTER_STRING_TESTS
     */
    private static final int NB_SETTER_GETTER_STRING_TESTS  = 200 * 1000 * 1000;

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
     * testGetterClassWith1int.
     *
     * @return
     */
    public static long testGetterClassWith1int()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intGetterSetter classWith1int = new ClassWith1intGetterSetter();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_SETTER_GETTER_INT_TESTS; i != 0; i--)
        {
            // Write to force cpp not to optimize the code, never executed
            if (i % 2 == 2)
            {
                classWith1int.setInt1(i);
            }
            count += classWith1int.getInt1();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[GetterSetterTest], Getter of ClassWith1int() test=" + NB_SETTER_GETTER_INT_TESTS + ", count=" + count
                + " classWith1int.getInt1()=" + classWith1int.getInt1() + " , snapshot time," + executionTime);
        return executionTime;
    }


    /**
     * testSetterClassWith1int.
     *
     * @return
     */
    public static long testSetterClassWith1int()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intGetterSetter classWith1int = new ClassWith1intGetterSetter();
        start = System.currentTimeMillis();
        for (int i = NB_SETTER_GETTER_INT_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith1int.setInt1(i);
            }
            else
            {
                classWith1int.setInt1(i - 1);
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[GetterSetterTest], Setter of ClassWith1int() test=" + NB_SETTER_GETTER_INT_TESTS + ","
                           + " classWith1int.getInt1()=" + classWith1int.getInt1()  + ", snapshot time,"
                + executionTime);
        return executionTime;
    }

    /**
     * testSetterClassWith1int.
     *
     * @return
     */
    public static long testSetterGetterClassWith1int()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intGetterSetter classWith1int = new ClassWith1intGetterSetter();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_SETTER_GETTER_INT_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith1int.setInt1(i);
            }
            else
            {
                classWith1int.setInt1(i - 1);
            }
            count += classWith1int.getInt1();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[GetterSetterTest], Setter Getter of ClassWith1int() test=" + NB_SETTER_GETTER_INT_TESTS + ", count=" + count
                           + " classWith1int.getInt1()=" + classWith1int.getInt1() + ", snapshot time,"
                + executionTime);
        return executionTime;
    }


    /**
     * testGetterClassWith10int.
     *
     * @return
     */
    public static long testGetterClassWith10int()
    {
        long start = System.currentTimeMillis();
        final ClassWith10intGetterSetter classWith10int = new ClassWith10intGetterSetter();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_SETTER_GETTER_INT_TESTS; i != 0; i--)
        {
            // Write to force cpp and jre not to optimize the code, never executed
            final boolean testAlwaysFalse = (i % 2 == 2);
            if (testAlwaysFalse)
            {
                classWith10int.setInt1(i);
            }
            count += classWith10int.getInt1();
            
            if (testAlwaysFalse)
            {
                classWith10int.setInt2(i);
            }
            count += classWith10int.getInt2();
            
            if (testAlwaysFalse)
            {
                classWith10int.setInt3(i);
            }
            count += classWith10int.getInt3();
            
            if (testAlwaysFalse)
            {
                classWith10int.setInt4(i);
            }
            count += classWith10int.getInt4();
            
            if (testAlwaysFalse)
            {
                classWith10int.setInt5(i);
            }
            count += classWith10int.getInt5();
            
            if (testAlwaysFalse)
            {
                classWith10int.setInt6(i);
            }
            count += classWith10int.getInt6();
            
            if (testAlwaysFalse)
            {
                classWith10int.setInt7(i);
            }
            count += classWith10int.getInt7();
            
            if (testAlwaysFalse)
            {
                classWith10int.setInt8(i);
            }
            count += classWith10int.getInt8();
            
            if (testAlwaysFalse)
            {
                classWith10int.setInt9(i);
            }
            count += classWith10int.getInt9();
            
            if (testAlwaysFalse)
            {
                classWith10int.setInt10(i);
            }
            count += classWith10int.getInt10();
            
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[GetterSetterTest], Getter of ClassWith10int() test=" + NB_SETTER_GETTER_INT_TESTS + ", count=" + count
                           + " classWith10int.getInt1()=" + classWith10int.getInt1() + ", snapshot time," + executionTime);
        return executionTime;
    }


    /**
     * testSetterClassWith10int.
     *
     * @return
     */
    public static long testSetterClassWith10int()
    {
        long start = System.currentTimeMillis();
        final ClassWith10intGetterSetter classWith10int = new ClassWith10intGetterSetter();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_SETTER_GETTER_INT_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith10int.setInt1(i);
                classWith10int.setInt2(i);
                classWith10int.setInt3(i);
                classWith10int.setInt4(i);
                classWith10int.setInt5(i);
                classWith10int.setInt6(i);
                classWith10int.setInt7(i);
                classWith10int.setInt8(i);
                classWith10int.setInt9(i);
                classWith10int.setInt10(i);
            }
            else
            {
                int j = i - 1;
                classWith10int.setInt1(j);
                classWith10int.setInt2(j);
                classWith10int.setInt3(j);
                classWith10int.setInt4(j);
                classWith10int.setInt5(j);
                classWith10int.setInt6(j);
                classWith10int.setInt7(j);
                classWith10int.setInt8(j);
                classWith10int.setInt9(j);
                classWith10int.setInt10(j);
            }
            count += classWith10int.getInt1() + classWith10int.getInt2() + classWith10int.getInt3() + classWith10int.getInt4()
            + classWith10int.getInt5() + classWith10int.getInt6() + classWith10int.getInt7() + classWith10int.getInt8()
            + classWith10int.getInt9() + classWith10int.getInt10();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[GetterSetterTest], Setter of ClassWith10int() test=" + NB_SETTER_GETTER_INT_TESTS + " ,"
                           + " classWith10int.getInt1()=" + classWith10int.getInt1() + ", snapshot time,"
                + executionTime);
        return executionTime;
    }

    /**
     * testSetterClassWith10int.
     *
     * @return
     */
    public static long testSetterGetterClassWith10int()
    {
        long start = System.currentTimeMillis();
        final ClassWith10intGetterSetter classWith10int = new ClassWith10intGetterSetter();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_SETTER_GETTER_INT_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith10int.setInt1(i);
                classWith10int.setInt2(i);
                classWith10int.setInt3(i);
                classWith10int.setInt4(i);
                classWith10int.setInt5(i);
                classWith10int.setInt6(i);
                classWith10int.setInt7(i);
                classWith10int.setInt8(i);
                classWith10int.setInt9(i);
                classWith10int.setInt10(i);
            }
            else
            {
                int j = i - 1;
                classWith10int.setInt1(j);
                classWith10int.setInt2(j);
                classWith10int.setInt3(j);
                classWith10int.setInt4(j);
                classWith10int.setInt5(j);
                classWith10int.setInt6(j);
                classWith10int.setInt7(j);
                classWith10int.setInt8(j);
                classWith10int.setInt9(j);
                classWith10int.setInt10(j);
            }
            count += classWith10int.getInt1() + classWith10int.getInt2() + classWith10int.getInt3() + classWith10int.getInt4()
            + classWith10int.getInt5() + classWith10int.getInt6() + classWith10int.getInt7() + classWith10int.getInt8()
            + classWith10int.getInt9() + classWith10int.getInt10();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[GetterSetterTest], Setter Getter of ClassWith10int() test=" + NB_SETTER_GETTER_INT_TESTS + " ," +
                           " classWith10int.getInt1()=" + classWith10int.getInt1() + ", snapshot time,"
                + executionTime);
        return executionTime;
    }

    /**
     * testGetterClassWith1String.
     *
     * @return
     */
    public static long testGetterClassWith1String()
    {
        long start = System.currentTimeMillis();
        final ClassWith1StringGetterSetter classWith1String = new ClassWith1StringGetterSetter();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_SETTER_GETTER_STRING_TESTS; i != 0; i--)
        {
            // Write to force cpp not to optimize the code, never executed
            if (i % 2 == 2)
            {
                classWith1String.setString1("1");
            }
            count += classWith1String.getString1().length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[GetterSetterTest], Getter of ClassWith1String() test=" + NB_SETTER_GETTER_STRING_TESTS + ", count=" + count
                           + " classWith1String.getString1()=" + classWith1String.getString1() + ", snapshot time," + executionTime);
        return executionTime;
    }


    /**
     * testSetterClassWith1String.
     *
     * @return
     */
    public static long testSetterClassWith1String()
    {
        long start = System.currentTimeMillis();
        final ClassWith1StringGetterSetter classWith1String = new ClassWith1StringGetterSetter();
        String string1 = new String("string1");
        String string2 = new String("string2");
        start = System.currentTimeMillis();
        for (int i = NB_SETTER_GETTER_STRING_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith1String.setString1(string1);
            }
            else
            {
                classWith1String.setString1(string2);
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[GetterSetterTest], Setter of ClassWith1String() test=" + NB_SETTER_GETTER_STRING_TESTS + ","
                + " classWith1String.getString1()=" + classWith1String.getString1() + ", snapshot time,"
                + executionTime);
        return executionTime;
    }

    /**
     * testSetterGetterClassWith1String.
     *
     * @return
     */
    public static long testSetterGetterClassWith1String()
    {
        long start = System.currentTimeMillis();
        final ClassWith1StringGetterSetter classWith1String = new ClassWith1StringGetterSetter();
        String string1 = new String("string1");
        String string2 = new String("string2");
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_SETTER_GETTER_STRING_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith1String.setString1(string1);
            }
            else
            {
                classWith1String.setString1(string2);
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[GetterSetterTest], Setter Getter of ClassWith1String() test=" + NB_SETTER_GETTER_STRING_TESTS + ", " +
                "count=" + count + " classWith1String.getString1()=" + classWith1String.getString1() + " , snapshot time,"
                + executionTime);
        return executionTime;
    }


    /**
     * testGetterClassWith10String.
     *
     * @return
     */
    public static long testGetterClassWith10String()
    {
        long start = System.currentTimeMillis();
        final ClassWith10StringGetterSetter classWith10String = new ClassWith10StringGetterSetter();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_SETTER_GETTER_STRING_TESTS; i != 0; i--)
        {
            // Write to force cpp not to optimize the code, never executed
            //final boolean testAlwaysFalse = (i % 2 == 2);
            
            if (i % 2 == 2)
            {
                classWith10String.setString1("s1");
            }
            count += classWith10String.getString1().length();
            
            if (i % 2 == 2)
            {
                classWith10String.setString2("s2");
            }
            count += classWith10String.getString2().length();
            
            if (i % 2 == 2)
            {
                classWith10String.setString3("s3");
            }
            count += classWith10String.getString3().length();
            
            if (i % 2 == 2)
            {
                classWith10String.setString4("s4");
            }
            count += classWith10String.getString4().length();
            
            if (i % 2 == 2)
            {
                classWith10String.setString5("s5");
            }
            count += classWith10String.getString5().length();
            
            if (i % 2 == 2)
            {
                classWith10String.setString6("s6");
            }
            count += classWith10String.getString6().length();
            
            if (i % 2 == 2)
            {
                classWith10String.setString7("s7");
            }
            count += classWith10String.getString7().length();
            
            if (i % 2 == 2)
            {
                classWith10String.setString8("s8");
            }
            count += classWith10String.getString8().length();
            
            if (i % 2 == 2)
            {
                classWith10String.setString9("s9");
            }
            count += classWith10String.getString9().length();
            
            if (i % 2 == 2)
            {
                classWith10String.setString10("s10");
            }
            count += classWith10String.getString10().length();
            
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[GetterSetterTest], Getter of ClassWith10String() test=" + NB_SETTER_GETTER_STRING_TESTS + ", count=" + count
                           + " classWith10String.getString1()=" + classWith10String.getString1() + " , snapshot time," + executionTime);
        return executionTime;
    }


    /**
     * testSetterClassWith10String.
     *
     * @return
     */
    public static long testSetterClassWith10String()
    {
        long start = System.currentTimeMillis();
        final ClassWith10StringGetterSetter classWith10String = new ClassWith10StringGetterSetter();
        String string1 = new String("string1");
        String string2 = new String("string2");
        String string3 = new String("string3");
        String string4 = new String("string4");
        String string5 = new String("string5");
        String string6 = new String("string6");
        String string7 = new String("string7");
        String string8 = new String("string8");
        String string9 = new String("string9");
        String string10 = new String("string10");
        start = System.currentTimeMillis();
        for (int i = NB_SETTER_GETTER_STRING_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith10String.setString1(string1);
                classWith10String.setString2(string2);
                classWith10String.setString3(string3);
                classWith10String.setString4(string4);
                classWith10String.setString5(string5);
                classWith10String.setString6(string6);
                classWith10String.setString7(string7);
                classWith10String.setString8(string8);
                classWith10String.setString9(string9);
                classWith10String.setString10(string10);
            }
            else
            {
                classWith10String.setString1(string2);
                classWith10String.setString2(string3);
                classWith10String.setString3(string4);
                classWith10String.setString4(string5);
                classWith10String.setString5(string6);
                classWith10String.setString6(string7);
                classWith10String.setString7(string8);
                classWith10String.setString8(string9);
                classWith10String.setString9(string10);
                classWith10String.setString10(string1);
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[GetterSetterTest], Setter of ClassWith10String() test=" + NB_SETTER_GETTER_STRING_TESTS + "," +
                           " classWith10String.getString1()=" + classWith10String.getString1() + ", snapshot time,"
                + executionTime);
        return executionTime;
    }
    


    /**
     * testSetterClassWith10String.
     *
     * @return
     */
    public static long testSetterGetterClassWith10String()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        final ClassWith10StringGetterSetter classWith10String = new ClassWith10StringGetterSetter();
        String string1 = new String("string1");
        String string2 = new String("string2");
        String string3 = new String("string3");
        String string4 = new String("string4");
        String string5 = new String("string5");
        String string6 = new String("string6");
        String string7 = new String("string7");
        String string8 = new String("string8");
        String string9 = new String("string9");
        String string10 = new String("string10");
        start = System.currentTimeMillis();
        for (int i = NB_SETTER_GETTER_STRING_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith10String.setString1(string1);
                classWith10String.setString2(string2);
                classWith10String.setString3(string3);
                classWith10String.setString4(string4);
                classWith10String.setString5(string5);
                classWith10String.setString6(string6);
                classWith10String.setString7(string7);
                classWith10String.setString8(string8);
                classWith10String.setString9(string9);
                classWith10String.setString10(string10);
            }
            else
            {
                classWith10String.setString1(string2);
                classWith10String.setString2(string3);
                classWith10String.setString3(string4);
                classWith10String.setString4(string5);
                classWith10String.setString5(string6);
                classWith10String.setString6(string7);
                classWith10String.setString7(string8);
                classWith10String.setString8(string9);
                classWith10String.setString9(string10);
                classWith10String.setString10(string1);
            }
            count += classWith10String.getString1().length() - classWith10String.getString2().length() + classWith10String.getString3().length()
            - classWith10String.getString4().length() + classWith10String.getString5().length() - classWith10String.getString6().length()
            + classWith10String.getString7().length() - classWith10String.getString8().length() + classWith10String.getString9().length()
            - classWith10String.getString10().length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[GetterSetterTest], Setter Getter of ClassWith10String() test=" + NB_SETTER_GETTER_STRING_TESTS + ", count=" + count + "" 
                           + " classWith10String.getString1()=" + classWith10String.getString1() + ", snapshot time,"
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
            executionTimes.add(GetterSetterTest.testGetterClassWith1int());
        System.out.println("[GetterSetterTest], Getter of ClassWith1int() test=" + NB_SETTER_GETTER_INT_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(GetterSetterTest.testSetterClassWith1int());
        System.out.println("[GetterSetterTest], Setter of ClassWith1int() test=" + NB_SETTER_GETTER_INT_TESTS + ",, average time,"
                +averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(GetterSetterTest.testSetterGetterClassWith1int());
        System.out.println("[GetterSetterTest], Setter Getter of ClassWith1int() test=" + NB_SETTER_GETTER_INT_TESTS + ",, average time,"
                +averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(GetterSetterTest.testGetterClassWith10int());
        System.out.println("[GetterSetterTest], Getter of ClassWith10int() test=" + NB_SETTER_GETTER_INT_TESTS + ",, average time,"
                +averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(GetterSetterTest.testSetterClassWith10int());
        System.out.println("[GetterSetterTest], Setter of ClassWith10int() test=" + NB_SETTER_GETTER_INT_TESTS + ",, average time,"
                +averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(GetterSetterTest.testSetterGetterClassWith10int());
        System.out.println("[GetterSetterTest], Setter Getter of ClassWith10int() test=" + NB_SETTER_GETTER_INT_TESTS + ",, average time,"
                +averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(GetterSetterTest.testGetterClassWith1String());
        System.out.println("[GetterSetterTest], Getter of ClassWith1String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",, average time,"
                +averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(GetterSetterTest.testSetterClassWith1String());
        System.out.println("[GetterSetterTest], Setter of ClassWith1String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",, average time,"
                +averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(GetterSetterTest.testSetterGetterClassWith1String());
        System.out.println("[GetterSetterTest], Setter Getter of ClassWith1String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",, average time,"
                +averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(GetterSetterTest.testGetterClassWith10String());
        System.out.println("[GetterSetterTest], Getter of ClassWith10String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",, average time,"
                +averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(GetterSetterTest.testSetterClassWith10String());
        System.out.println("[GetterSetterTest], Setter of ClassWith10String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",, average time,"
                +averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(GetterSetterTest.testSetterGetterClassWith10String());
        System.out.println("[GetterSetterTest], Setter Getter of ClassWith10String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",, average time,"
                +averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
    }

}

/**
 * ClassWith1String.
 *
 * @author Alexandre
 *
 */
final class ClassWith1StringGetterSetter
{
    private String string1 = "string1";

    /**
     * getString1.
     *
     * @return
     */
    public String getString1()
    {
        return this.string1;
    }

    /**
     * setString1.
     *
     * @param aString1
     */
    public void setString1(final String aString1)
    {
        this.string1 = aString1;
    }
}

/**
 * ClassWith10String.
 *
 * @author Alexandre
 *
 */
final class ClassWith10StringGetterSetter
{
    private String string1  = "string1";

    private String string2  = "string2";

    private String string3  = "string3";

    private String string4  = "string4";

    private String string5  = "string5";

    private String string6  = "string6";

    private String string7  = "string7";

    private String string8  = "string8";

    private String string9  = "string9";

    private String string10 = "string10";

    /**
     * Get the field string1
     *
     * @return the string1
     */

    public String getString1()
    {
        return this.string1;
    }

    /**
     * Set the field string1
     *
     * @param aString1
     *            the string1 to set
     */

    public void setString1(final String aString1)
    {
        this.string1 = aString1;
    }

    /**
     * Get the field string2
     *
     * @return the string2
     */

    public String getString2()
    {
        return this.string2;
    }

    /**
     * Set the field string2
     *
     * @param aString2
     *            the string2 to set
     */

    public void setString2(final String aString2)
    {
        this.string2 = aString2;
    }

    /**
     * Get the field string3
     *
     * @return the string3
     */

    public String getString3()
    {
        return this.string3;
    }

    /**
     * Set the field string3
     *
     * @param aString3
     *            the string3 to set
     */

    public void setString3(final String aString3)
    {
        this.string3 = aString3;
    }

    /**
     * Get the field string4
     *
     * @return the string4
     */

    public String getString4()
    {
        return this.string4;
    }

    /**
     * Set the field string4
     *
     * @param aString4
     *            the string4 to set
     */

    public void setString4(final String aString4)
    {
        this.string4 = aString4;
    }

    /**
     * Get the field string5
     *
     * @return the string5
     */

    public String getString5()
    {
        return this.string5;
    }

    /**
     * Set the field string5
     *
     * @param aString5
     *            the string5 to set
     */

    public void setString5(final String aString5)
    {
        this.string5 = aString5;
    }

    /**
     * Get the field string6
     *
     * @return the string6
     */

    public String getString6()
    {
        return this.string6;
    }

    /**
     * Set the field string6
     *
     * @param aString6
     *            the string6 to set
     */

    public void setString6(final String aString6)
    {
        this.string6 = aString6;
    }

    /**
     * Get the field string7
     *
     * @return the string7
     */

    public String getString7()
    {
        return this.string7;
    }

    /**
     * Set the field string7
     *
     * @param aString7
     *            the string7 to set
     */

    public void setString7(final String aString7)
    {
        this.string7 = aString7;
    }

    /**
     * Get the field string8
     *
     * @return the string8
     */

    public String getString8()
    {
        return this.string8;
    }

    /**
     * Set the field string8
     *
     * @param aString8
     *            the string8 to set
     */

    public void setString8(final String aString8)
    {
        this.string8 = aString8;
    }

    /**
     * Get the field string9
     *
     * @return the string9
     */

    public String getString9()
    {
        return this.string9;
    }

    /**
     * Set the field string9
     *
     * @param aString9
     *            the string9 to set
     */

    public void setString9(final String aString9)
    {
        this.string9 = aString9;
    }

    /**
     * Get the field string10
     *
     * @return the string10
     */

    public String getString10()
    {
        return this.string10;
    }

    /**
     * Set the field string10
     *
     * @param aString10
     *            the string10 to set
     */

    public void setString10(final String aString10)
    {
        this.string10 = aString10;
    }

}

/**
 * ClassWith1int.
 *
 * @author Alexandre
 *
 */
final class ClassWith1intGetterSetter
{
    private int int1;

    /**
     * getInt1.
     *
     * @return
     */
    public int getInt1()
    {
        return this.int1;
    }

    /**
     * setInt1.
     *
     * @param aInt1
     */
    public void setInt1(final int aInt1)
    {
        this.int1 = aInt1;
    }
}

/**
 * ClassWith10int.
 *
 * @author Alexandre
 *
 */
final class ClassWith10intGetterSetter
{
    private int int1  = 1;

    private int int2  = -1;

    private int int3  = 3;

    private int int4  = -3;

    private int int5  = 5;

    private int int6  = -5;

    private int int7  = 7;

    private int int8  = -7;

    private int int9  = 9;

    private int int10 = -9;

    /**
     * Get the field int1
     *
     * @return the int1
     */
    public int getInt1()
    {
        return this.int1;
    }

    /**
     * Set the field int1
     *
     * @param aInt1
     *            the int1 to set
     */
    public void setInt1(final int aInt1)
    {
        this.int1 = aInt1;
    }

    /**
     * Get the field int2
     *
     * @return the int2
     */
    public int getInt2()
    {
        return this.int2;
    }

    /**
     * Set the field int2
     *
     * @param aInt2
     *            the int2 to set
     */
    public void setInt2(final int aInt2)
    {
        this.int2 = aInt2;
    }

    /**
     * Get the field int3
     *
     * @return the int3
     */
    public int getInt3()
    {
        return this.int3;
    }

    /**
     * Set the field int3
     *
     * @param aInt3
     *            the int3 to set
     */
    public void setInt3(final int aInt3)
    {
        this.int3 = aInt3;
    }

    /**
     * Get the field int4
     *
     * @return the int4
     */
    public int getInt4()
    {
        return this.int4;
    }

    /**
     * Set the field int4
     *
     * @param aInt4
     *            the int4 to set
     */
    public void setInt4(int aInt4)
    {
        this.int4 = aInt4;
    }

    /**
     * Get the field int5
     *
     * @return the int5
     */
    public int getInt5()
    {
        return this.int5;
    }

    /**
     * Set the field int5
     *
     * @param aInt5
     *            the int5 to set
     */
    public void setInt5(int aInt5)
    {
        this.int5 = aInt5;
    }

    /**
     * Get the field int6
     *
     * @return the int6
     */
    public int getInt6()
    {
        return this.int6;
    }

    /**
     * Set the field int6
     *
     * @param aInt6
     *            the int6 to set
     */
    public void setInt6(final int aInt6)
    {
        this.int6 = aInt6;
    }

    /**
     * Get the field int7
     *
     * @return the int7
     */
    public int getInt7()
    {
        return this.int7;
    }

    /**
     * Set the field int7
     *
     * @param aInt7
     *            the int7 to set
     */
    public void setInt7(final int aInt7)
    {
        this.int7 = aInt7;
    }

    /**
     * Get the field int8
     *
     * @return the int8
     */
    public int getInt8()
    {
        return this.int8;
    }

    /**
     * Set the field int8
     *
     * @param aInt8
     *            the int8 to set
     */
    public void setInt8(final int aInt8)
    {
        this.int8 = aInt8;
    }

    /**
     * Get the field int9
     *
     * @return the int9
     */
    public int getInt9()
    {
        return this.int9;
    }

    /**
     * Set the field int9
     *
     * @param aInt9
     *            the int9 to set
     */
    public void setInt9(final int aInt9)
    {
        this.int9 = aInt9;
    }

    /**
     * Get the field int10
     *
     * @return the int10
     */
    public int getInt10()
    {
        return this.int10;
    }

    /**
     * Set the field int10
     *
     * @param aInt10
     *            the int10 to set
     */
    public void setInt10(final int aInt10)
    {
        this.int10 = aInt10;
    }
}

/**
 * ClassWith1ClassWith10String.
 *
 * @author Alexandre
 *
 */
final class ClassWith1ClassWith10StringGetterSetter
{
    private ClassWith10StringGetterSetter classWith10String1 = new ClassWith10StringGetterSetter();

    /**
     * @return the classWith10String1
     */
    public ClassWith10StringGetterSetter getClassWith10String1()
    {
        return this.classWith10String1;
    }

    /**
     * @param aClassWith10String1
     *            the classWith10String1 to set
     */
    public void setClassWith10String1(final ClassWith10StringGetterSetter aClassWith10String1)
    {
        this.classWith10String1 = aClassWith10String1;
    }
}

/**
 * ClassWith10ClassWith10String.
 *
 * @author Alexandre
 *
 */
final class ClassWith10ClassWith10StringGetterSetter
{
    private ClassWith10StringGetterSetter classWith10String1  = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String2  = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String3  = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String4  = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String5  = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String6  = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String7  = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String8  = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String9  = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String10 = new ClassWith10StringGetterSetter();

    /**
     * Get the field classWith10String1
     *
     * @return the classWith10String1
     */
    public ClassWith10StringGetterSetter getClassWith10String1()
    {
        return this.classWith10String1;
    }

    /**
     * Set the field classWith10String1
     *
     * @param aClassWith10String1
     *            the classWith10String1 to set
     */
    public void setClassWith10String1(final ClassWith10StringGetterSetter aClassWith10String1)
    {
        this.classWith10String1 = aClassWith10String1;
    }

    /**
     * Get the field classWith10String2
     *
     * @return the classWith10String2
     */
    public ClassWith10StringGetterSetter getClassWith10String2()
    {
        return this.classWith10String2;
    }

    /**
     * Set the field classWith10String2
     *
     * @param aClassWith10String2
     *            the classWith10String2 to set
     */
    public void setClassWith10String2(final ClassWith10StringGetterSetter aClassWith10String2)
    {
        this.classWith10String2 = aClassWith10String2;
    }

    /**
     * Get the field classWith10String3
     *
     * @return the classWith10String3
     */
    public ClassWith10StringGetterSetter getClassWith10String3()
    {
        return this.classWith10String3;
    }

    /**
     * Set the field classWith10String3
     *
     * @param aClassWith10String3
     *            the classWith10String3 to set
     */
    public void setClassWith10String3(final ClassWith10StringGetterSetter aClassWith10String3)
    {
        this.classWith10String3 = aClassWith10String3;
    }

    /**
     * Get the field classWith10String4
     *
     * @return the classWith10String4
     */
    public ClassWith10StringGetterSetter getClassWith10String4()
    {
        return this.classWith10String4;
    }

    /**
     * Set the field classWith10String4
     *
     * @param aClassWith10String4
     *            the classWith10String4 to set
     */
    public void setClassWith10String4(final ClassWith10StringGetterSetter aClassWith10String4)
    {
        this.classWith10String4 = aClassWith10String4;
    }

    /**
     * Get the field classWith10String5
     *
     * @return the classWith10String5
     */
    public ClassWith10StringGetterSetter getClassWith10String5()
    {
        return this.classWith10String5;
    }

    /**
     * Set the field classWith10String5
     *
     * @param aClassWith10String5
     *            the classWith10String5 to set
     */
    public void setClassWith10String5(final ClassWith10StringGetterSetter aClassWith10String5)
    {
        this.classWith10String5 = aClassWith10String5;
    }

    /**
     * Get the field classWith10String6
     *
     * @return the classWith10String6
     */
    public ClassWith10StringGetterSetter getClassWith10String6()
    {
        return this.classWith10String6;
    }

    /**
     * Set the field classWith10String6
     *
     * @param aClassWith10String6
     *            the classWith10String6 to set
     */
    public void setClassWith10String6(final ClassWith10StringGetterSetter aClassWith10String6)
    {
        this.classWith10String6 = aClassWith10String6;
    }

    /**
     * Get the field classWith10String7
     *
     * @return the classWith10String7
     */
    public ClassWith10StringGetterSetter getClassWith10String7()
    {
        return this.classWith10String7;
    }

    /**
     * Set the field classWith10String7
     *
     * @param aClassWith10String7
     *            the classWith10String7 to set
     */
    public void setClassWith10String7(final ClassWith10StringGetterSetter aClassWith10String7)
    {
        this.classWith10String7 = aClassWith10String7;
    }

    /**
     * Get the field classWith10String8
     *
     * @return the classWith10String8
     */
    public ClassWith10StringGetterSetter getClassWith10String8()
    {
        return this.classWith10String8;
    }

    /**
     * Set the field classWith10String8
     *
     * @param aClassWith10String8
     *            the classWith10String8 to set
     */
    public void setClassWith10String8(final ClassWith10StringGetterSetter aClassWith10String8)
    {
        this.classWith10String8 = aClassWith10String8;
    }

    /**
     * Get the field classWith10String9
     *
     * @return the classWith10String9
     */
    public ClassWith10StringGetterSetter getClassWith10String9()
    {
        return this.classWith10String9;
    }

    /**
     * Set the field classWith10String9
     *
     * @param aClassWith10String9
     *            the classWith10String9 to set
     */
    public void setClassWith10String9(final ClassWith10StringGetterSetter aClassWith10String9)
    {
        this.classWith10String9 = aClassWith10String9;
    }

    /**
     * Get the field classWith10String10
     *
     * @return the classWith10String10
     */
    public ClassWith10StringGetterSetter getClassWith10String10()
    {
        return this.classWith10String10;
    }

    /**
     * Set the field classWith10String10
     *
     * @param aClassWith10String10
     *            the classWith10String10 to set
     */
    public void setClassWith10String10(final ClassWith10StringGetterSetter aClassWith10String10)
    {
        this.classWith10String10 = aClassWith10String10;
    }

}
