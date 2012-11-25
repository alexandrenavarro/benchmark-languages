import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * CreationGetterSetterTest.
 * 
 * @author anavarro122404 - 25 sept. 07
 *
 *
 * <!-- $Id: CreationTest.java,v 1.1 2007/10/15 17:58:12 anavarro Exp $ -->.
 *
 */
public final class CreationTest
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
     * NB_CREATION_TESTS
     */
    private static final int NB_CREATION_TESTS       = 50 * 1000 * 1000;

    /**
     * NB_CREATION_TESTS
     */
    private static final int NB_CREATION_INT_TESTS   = 2000 * 1000 * 1000;

    
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
     * testCreationOfStringi
     */
    public static long testCreationOfStringi()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
            @SuppressWarnings("unused")
            final String s = new String("string" + i);
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CreationTest], Creation of " + NB_CREATION_TESTS + " new String(\"string\" + i),, snapshot time,"
                + executionTime);
        return executionTime;
    }


    /**
     * testCreationOfClassWith1String.
     *
     * @return
     */
    public static long testCreationOfClassWith1String()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
            @SuppressWarnings("unused")
            final ClassWith1String classWith1String = new ClassWith1String();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith1String(),, snapshot time,"
                + executionTime);
        return executionTime;
    }



    /**
     * testCreationOfClassWith10String.
     *
     * @return
     */
    public static long testCreationOfClassWith10String()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
            @SuppressWarnings("unused")
            final ClassWith10String classWith10String1 = new ClassWith10String();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith10String(),, snapshot time,"
                + executionTime);
        return executionTime;
    }

    /**
     * testCreationOfint.
     * 
     * @return
     */
    public static long testCreationOfint()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_CREATION_INT_TESTS; i-- != 0;)
        {
            @SuppressWarnings("unused")
            final int k = i;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CreationTest], Creation of " + NB_CREATION_INT_TESTS + " int k = i,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCreationOfString
     */
    public static long testCreationOfClassWith1int()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
            @SuppressWarnings("unused")
            final ClassWith1int classWith1int = new ClassWith1int();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith1int(),, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCreationOfString
     */
    public static long testCreationOfClassWith10int()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
            @SuppressWarnings("unused")
            final ClassWith10int classWith10int = new ClassWith10int();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith10int(),, snapshot time," + executionTime);
        return executionTime;
    }


    /**
     * testCreationOfClassWith1ClassWith10String.
     *
     * @return
     */
    public static long testCreationOfClassWith1ClassWith10String()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
            @SuppressWarnings("unused")
            final ClassWith1ClassWith10String classWith1ClassWith10String = new ClassWith1ClassWith10String();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith1ClassWith10String(),, snapshot time,"
                + executionTime);
        return executionTime;
    }




    /**
     * testCreationOfClassWith10ClassWith10String.
     *
     * @return
     */
    public static long testCreationOfClassWith10ClassWith10String()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
            @SuppressWarnings("unused")
            final ClassWith10ClassWith10String classWith10ClassWith10Strings = new ClassWith10ClassWith10String();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CreationGetterSetterTest], Creation of " + NB_CREATION_TESTS + " new ClassWith10ClassWith10String(),, snapshot time,"
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
            executionTimes.add(CreationTest.testCreationOfClassWith1String());
        System.out.println("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith1String(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CreationTest.testCreationOfClassWith10String());
        System.out.println("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith10String(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CreationTest.testCreationOfClassWith1int());
        System.out.println("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith1int(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CreationTest.testCreationOfClassWith10int());
        System.out.println("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith10int(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CreationTest.testCreationOfClassWith1ClassWith10String());
        System.out.println("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith1ClassWith10String(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CreationTest.testCreationOfClassWith10ClassWith10String());
        System.out.println("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith10ClassWith10String((),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
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
final class ClassWith1String
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
final class ClassWith10String
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
final class ClassWith1int
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
final class ClassWith10int
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
final class ClassWith1ClassWith10String
{
    private ClassWith10String classWith10String1 = new ClassWith10String();

    /**
     * @return the classWith10String1
     */
    public ClassWith10String getClassWith10String1()
    {
        return this.classWith10String1;
    }

    /**
     * @param aClassWith10String1
     *            the classWith10String1 to set
     */
    public void setClassWith10String1(final ClassWith10String aClassWith10String1)
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
final class ClassWith10ClassWith10String
{
    private ClassWith10String classWith10String1  = new ClassWith10String();

    private ClassWith10String classWith10String2  = new ClassWith10String();

    private ClassWith10String classWith10String3  = new ClassWith10String();

    private ClassWith10String classWith10String4  = new ClassWith10String();

    private ClassWith10String classWith10String5  = new ClassWith10String();

    private ClassWith10String classWith10String6  = new ClassWith10String();

    private ClassWith10String classWith10String7  = new ClassWith10String();

    private ClassWith10String classWith10String8  = new ClassWith10String();

    private ClassWith10String classWith10String9  = new ClassWith10String();

    private ClassWith10String classWith10String10 = new ClassWith10String();

    /**
     * Get the field classWith10String1
     * 
     * @return the classWith10String1
     */
    public ClassWith10String getClassWith10String1()
    {
        return this.classWith10String1;
    }

    /**
     * Set the field classWith10String1
     * 
     * @param aClassWith10String1
     *            the classWith10String1 to set
     */
    public void setClassWith10String1(final ClassWith10String aClassWith10String1)
    {
        this.classWith10String1 = aClassWith10String1;
    }

    /**
     * Get the field classWith10String2
     * 
     * @return the classWith10String2
     */
    public ClassWith10String getClassWith10String2()
    {
        return this.classWith10String2;
    }

    /**
     * Set the field classWith10String2
     * 
     * @param aClassWith10String2
     *            the classWith10String2 to set
     */
    public void setClassWith10String2(final ClassWith10String aClassWith10String2)
    {
        this.classWith10String2 = aClassWith10String2;
    }

    /**
     * Get the field classWith10String3
     * 
     * @return the classWith10String3
     */
    public ClassWith10String getClassWith10String3()
    {
        return this.classWith10String3;
    }

    /**
     * Set the field classWith10String3
     * 
     * @param aClassWith10String3
     *            the classWith10String3 to set
     */
    public void setClassWith10String3(final ClassWith10String aClassWith10String3)
    {
        this.classWith10String3 = aClassWith10String3;
    }

    /**
     * Get the field classWith10String4
     * 
     * @return the classWith10String4
     */
    public ClassWith10String getClassWith10String4()
    {
        return this.classWith10String4;
    }

    /**
     * Set the field classWith10String4
     * 
     * @param aClassWith10String4
     *            the classWith10String4 to set
     */
    public void setClassWith10String4(final ClassWith10String aClassWith10String4)
    {
        this.classWith10String4 = aClassWith10String4;
    }

    /**
     * Get the field classWith10String5
     * 
     * @return the classWith10String5
     */
    public ClassWith10String getClassWith10String5()
    {
        return this.classWith10String5;
    }

    /**
     * Set the field classWith10String5
     * 
     * @param aClassWith10String5
     *            the classWith10String5 to set
     */
    public void setClassWith10String5(final ClassWith10String aClassWith10String5)
    {
        this.classWith10String5 = aClassWith10String5;
    }

    /**
     * Get the field classWith10String6
     * 
     * @return the classWith10String6
     */
    public ClassWith10String getClassWith10String6()
    {
        return this.classWith10String6;
    }

    /**
     * Set the field classWith10String6
     * 
     * @param aClassWith10String6
     *            the classWith10String6 to set
     */
    public void setClassWith10String6(final ClassWith10String aClassWith10String6)
    {
        this.classWith10String6 = aClassWith10String6;
    }

    /**
     * Get the field classWith10String7
     * 
     * @return the classWith10String7
     */
    public ClassWith10String getClassWith10String7()
    {
        return this.classWith10String7;
    }

    /**
     * Set the field classWith10String7
     * 
     * @param aClassWith10String7
     *            the classWith10String7 to set
     */
    public void setClassWith10String7(final ClassWith10String aClassWith10String7)
    {
        this.classWith10String7 = aClassWith10String7;
    }

    /**
     * Get the field classWith10String8
     * 
     * @return the classWith10String8
     */
    public ClassWith10String getClassWith10String8()
    {
        return this.classWith10String8;
    }

    /**
     * Set the field classWith10String8
     * 
     * @param aClassWith10String8
     *            the classWith10String8 to set
     */
    public void setClassWith10String8(final ClassWith10String aClassWith10String8)
    {
        this.classWith10String8 = aClassWith10String8;
    }

    /**
     * Get the field classWith10String9
     * 
     * @return the classWith10String9
     */
    public ClassWith10String getClassWith10String9()
    {
        return this.classWith10String9;
    }

    /**
     * Set the field classWith10String9
     * 
     * @param aClassWith10String9
     *            the classWith10String9 to set
     */
    public void setClassWith10String9(final ClassWith10String aClassWith10String9)
    {
        this.classWith10String9 = aClassWith10String9;
    }

    /**
     * Get the field classWith10String10
     * 
     * @return the classWith10String10
     */
    public ClassWith10String getClassWith10String10()
    {
        return this.classWith10String10;
    }

    /**
     * Set the field classWith10String10
     * 
     * @param aClassWith10String10
     *            the classWith10String10 to set
     */
    public void setClassWith10String10(final ClassWith10String aClassWith10String10)
    {
        this.classWith10String10 = aClassWith10String10;
    }

}
