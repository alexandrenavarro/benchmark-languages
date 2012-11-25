import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ReflectionTest.
 * 
 * TODO Proxy
 * 
 * @author Alexandre
 * 
 */
public final class ReflectionTest
{
    /**
     * NB_TESTS
     */
    private static final int NB_TESTS                     = 5;
    
    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    private static final int NB_OF_EXCLUSION_MIN_MAX      = 2;
    
    /**
     * NB_REFLECTION_TESTS
     */
    private static final int NB_STATIC_REFLECTION_TESTS   = 1000 * 1000;
    
    /**
     * NB_REFLECTION_TESTS
     */
    private static final int NB_INSTANCE_REFLECTION_TESTS = 20 * 1000 * 1000;
    
    /**
     * NB_GETTER_TESTS
     */
    private static final int NB_GETTER_TESTS              = 5 * 1000 * 1000;
    
    /**
     * NB_SETTER_TESTS
     */
    private static final int NB_SETTER_TESTS              = 5 * 1000 * 1000;
    
    /**
     * NB_CREATION_TESTS
     */
    private static final int NB_CREATION_TESTS            = 10 * 1000 * 1000;
    
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
     * testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface.
     * 
     * @return
     */
    public static long testCreationReflectionClassWith1String()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
            final ReflectionClassWith1String s = new ReflectionClassWith1String();
            count = count + s.getString1().length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ReflectionTest], Creation of ReflectionClassWith1String " + NB_CREATION_TESTS + ", count=" + count + ", snapshot time,"
                + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface.
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public static long testCreationReflectionClassWith1StringWithReflection()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        start = System.currentTimeMillis();
        Class c;
        try
        {
            c = Class.forName("ReflectionClassWith1String");
            start = System.currentTimeMillis();
            for (int i = NB_CREATION_TESTS; i != 0; i--)
            {
                final ReflectionClassWith1String s = (ReflectionClassWith1String) c.newInstance();
                count = count + s.getString1().length();
            }
        }
        catch (ClassNotFoundException e)
        {
            //
        }
        catch (IllegalArgumentException e)
        {
            //
        }
        catch (IllegalAccessException e)
        {
            //
        }
        catch (SecurityException e)
        {
            //
        }
        catch (InstantiationException e)
        {
            //
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ReflectionTest], Creation of ReflectionClassWith1String With Reflection " + NB_CREATION_TESTS + ", count=" + count
                + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testGetterReflectionClassWith1String.
     * 
     * @return
     */
    public static long testGetterReflectionClassWith1String()
    {
        long start = System.currentTimeMillis();
        final ReflectionClassWith1String classWith1String = new ReflectionClassWith1String();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_GETTER_TESTS; i != 0; i--)
        {
            count += classWith1String.getString1().length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ReflectionTest], Getter of ReflectionClassWith1String() test=" + NB_GETTER_TESTS + ", count=" + count
                + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testGetterReflectionClassWith1StringWithReflection.
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public static long testGetterReflectionClassWith1StringWithReflection()
    {
        
        long start = System.currentTimeMillis();
        int count = 0;
        start = System.currentTimeMillis();
        Class c;
        try
        {
            c = Class.forName("ReflectionClassWith1String");
            Object object = c.newInstance();
            final Method m1 = c.getMethod("getString1");
            start = System.currentTimeMillis();
            for (int i = NB_GETTER_TESTS; i != 0; i--)
            {
                final String s = (String) m1.invoke(object);
                count = count + s.length();
            }
        }
        catch (ClassNotFoundException e)
        {
            //
        }
        catch (IllegalArgumentException e)
        {
            //
        }
        catch (IllegalAccessException e)
        {
            //
        }
        catch (InvocationTargetException e)
        {
            //
        }
        catch (SecurityException e)
        {
            //
        }
        catch (NoSuchMethodException e)
        {
            //
        }
        catch (InstantiationException e)
        {
            //
        }
        
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ReflectionTest], Getter of ClassWith1String() with Reflection test=" + NB_GETTER_TESTS + ", count=" + count
                + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testSetterReflectionClassWith1String.
     * 
     * @return
     */
    public static long testSetterReflectionClassWith1String()
    {
        long start = System.currentTimeMillis();
        final ReflectionClassWith1String classWith1String = new ReflectionClassWith1String();
        start = System.currentTimeMillis();
        for (int i = NB_SETTER_TESTS; i != 0; i--)
        {
            classWith1String.setString1("string1");
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ReflectionTest], Setter of ReflectionClassWith1String() test=" + NB_SETTER_TESTS + ",, snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testSetterReflectionClassWith1StringWithReflection.
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public static long testSetterReflectionClassWith1StringWithReflection()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        Class c;
        try
        {
            c = Class.forName("ReflectionClassWith1String");
            Object object = c.newInstance();
            final Method m1 = c.getMethod("setString1", String.class);
            start = System.currentTimeMillis();
            for (int i = NB_SETTER_TESTS; i != 0; i--)
            {
                m1.invoke(object, "string1");
            }
        }
        catch (ClassNotFoundException e)
        {
            //
        }
        catch (IllegalArgumentException e)
        {
            //
        }
        catch (IllegalAccessException e)
        {
            //
        }
        catch (InvocationTargetException e)
        {
            //
        }
        catch (SecurityException e)
        {
            //
        }
        catch (NoSuchMethodException e)
        {
            //
        }
        catch (InstantiationException e)
        {
            //
        }
        
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ReflectionTest], Setter of ReflectionClassWith1String() with Reflection test=" + NB_GETTER_TESTS + ",, snapshot time,"
                + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAStaticMethod.
     * 
     * @return
     */
    public static long testInvokeOfAStaticMethod()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_STATIC_REFLECTION_TESTS; i != 0; i--)
        {
            count = count + ReflectionInvokeImpl.echoStatic("1").length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ReflectionTest], Invoke Of A Static Method " + NB_STATIC_REFLECTION_TESTS
                + " ReflectionInvokeImpl.echoStatic(\"\").length(), count=" + count + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAStaticMethod.
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public static long testInvokeOfAStaticMethodWithReflection()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        start = System.currentTimeMillis();
        Class c;
        try
        {
            c = Class.forName("ReflectionInvokeImpl");
            final Method m1 = c.getMethod("echoStatic", String.class);
            start = System.currentTimeMillis();
            for (int i = NB_STATIC_REFLECTION_TESTS; i != 0; i--)
            {
                final String s = (String) m1.invoke(null, "1");
                count = count + s.length();
            }
        }
        catch (ClassNotFoundException e)
        {
            //
        }
        catch (IllegalArgumentException e)
        {
            //
        }
        catch (IllegalAccessException e)
        {
            //
        }
        catch (InvocationTargetException e)
        {
            //
        }
        catch (SecurityException e)
        {
            //
        }
        catch (NoSuchMethodException e)
        {
            //
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ReflectionTest], Invoke Of A Static Method With Reflection " + NB_STATIC_REFLECTION_TESTS
                + " ReflectionInvokeImpl.echoStatic(\"\").length(), count=" + count + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface.
     * 
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterface()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        final ReflectionInvokeImpl invoke = new ReflectionInvokeImpl();
        start = System.currentTimeMillis();
        for (int i = NB_INSTANCE_REFLECTION_TESTS; i != 0; i--)
        {
            count = count + invoke.echoWithGetVariable().length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ReflectionTest], Invoke Of An Instance Method With Get Instance Variable Without Interface "
                + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithGetVariable().length()  , count=" + count + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface.
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public static long testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterfaceWithReflection()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        start = System.currentTimeMillis();
        Class c;
        try
        {
            c = Class.forName("ReflectionInvokeImpl");
            Object object = c.newInstance();
            final Method m1 = c.getMethod("echoWithGetVariable");
            start = System.currentTimeMillis();
            for (int i = NB_INSTANCE_REFLECTION_TESTS; i != 0; i--)
            {
                final String s = (String) m1.invoke(object);
                count = count + s.length();
            }
        }
        catch (ClassNotFoundException e)
        {
            //
        }
        catch (IllegalArgumentException e)
        {
            //
        }
        catch (IllegalAccessException e)
        {
            //
        }
        catch (InvocationTargetException e)
        {
            //
        }
        catch (SecurityException e)
        {
            //
        }
        catch (NoSuchMethodException e)
        {
            //
        }
        catch (InstantiationException e)
        {
            //
        }
        
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ReflectionTest], Invoke Of An Instance Method With Get Instance Variable Without Interface With Reflection "
                + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithGetVariable().length()  , count=" + count + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface.
     * 
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        final ReflectionInvokeImpl invoke = new ReflectionInvokeImpl();
        start = System.currentTimeMillis();
        for (int i = NB_INSTANCE_REFLECTION_TESTS; i != 0; i--)
        {
            count = count + invoke.echoWithSetVariable("1").length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out
                .println("[ReflectionTest], Invoke Of An Instance Method With Set Instance Variable Without Interface "
                        + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithSetVariable(\"1\").length()  , count=" + count + ", snapshot time,"
                        + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface.
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public static long testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterfaceWithReflection()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        start = System.currentTimeMillis();
        Class c;
        try
        {
            c = Class.forName("ReflectionInvokeImpl");
            Object object = c.newInstance();
            final Method m1 = c.getMethod("echoWithSetVariable", String.class);
            start = System.currentTimeMillis();
            for (int i = NB_INSTANCE_REFLECTION_TESTS; i != 0; i--)
            {
                final String s = (String) m1.invoke(object, "1");
                count = count + s.length();
            }
        }
        catch (ClassNotFoundException e)
        {
            //
        }
        catch (IllegalArgumentException e)
        {
            //
        }
        catch (IllegalAccessException e)
        {
            //
        }
        catch (InvocationTargetException e)
        {
            //
        }
        catch (SecurityException e)
        {
            //
        }
        catch (NoSuchMethodException e)
        {
            //
        }
        catch (InstantiationException e)
        {
            //
        }
        
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out
                .println("[ReflectionTest], Invoke Of An Instance Method With Set Instance Variable Without Interface With Reflection "
                        + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithSetVariable(\"1\").length()  , count=" + count + ", snapshot time,"
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
            executionTimes.add(ReflectionTest.testCreationReflectionClassWith1String());
        System.out.println("[ReflectionTest], Creation of ReflectionClassWith1String " + NB_CREATION_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ReflectionTest.testCreationReflectionClassWith1StringWithReflection());
        System.out.println("[ReflectionTest], Creation of ReflectionClassWith1String With Reflection " + NB_CREATION_TESTS + ",, average time,"
                +  averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ReflectionTest.testGetterReflectionClassWith1String());
        System.out.println("[ReflectionTest], Getter of ReflectionClassWith1String() test=" + NB_GETTER_TESTS + ",, average time,"
                +  averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ReflectionTest.testGetterReflectionClassWith1StringWithReflection());
        System.out.println("[ReflectionTest], Getter of ReflectionClassWith1String() with Reflection test=" + NB_GETTER_TESTS + ",, average time,"
                +  averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ReflectionTest.testSetterReflectionClassWith1String());
        System.out.println("[ReflectionTest], Setter of ReflectionClassWith1String() test=" + NB_SETTER_TESTS + ",, average time,"
                +  averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ReflectionTest.testSetterReflectionClassWith1StringWithReflection());
        System.out.println("[ReflectionTest], Setter of ReflectionClassWith1String() with Reflection test=" + NB_SETTER_TESTS + ",, average time,"
                +  averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ReflectionTest.testInvokeOfAStaticMethod());
        System.out.println("[ReflectionTest], Invoke Of A Static Method " + NB_STATIC_REFLECTION_TESTS
                + " ReflectionInvokeImpl.echoStatic(\"\").length(),, average time," +  averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ReflectionTest.testInvokeOfAStaticMethodWithReflection());
        System.out.println("[ReflectionTest], Invoke Of A Static Method With Reflection " + NB_STATIC_REFLECTION_TESTS
                + " ReflectionInvokeImpl.echoStatic(\"\").length()  ,, average time," +  averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ReflectionTest.testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterface());
        System.out.println("[ReflectionTest], Invoke Of An Instance Method With Get Instance Variable Without Interface "
                + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithSetVariable(\"1\").length()  ,, average time," +  averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ReflectionTest.testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterfaceWithReflection());
        System.out.println("[ReflectionTest], Invoke Of An Instance Method With Get Instance Variable Without Interface With Reflection "
                + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithSetVariable(\"1\").length()  ,, average time," +  averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ReflectionTest.testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface());
        System.out.println("[ReflectionTest], Invoke Of An Instance Method With Set Instance Variable Without Interface "
                + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithSetVariable(\"1\").length() ,, average time," +  averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ReflectionTest.testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterfaceWithReflection());
        System.out.println("[ReflectionTest], Invoke Of An Instance Method With Set Instance Variable Without Interface With Reflection "
                + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithSetVariable(\"1\").length()  ,, average time," +  averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
    }

}

/**
 * Invoke.
 * 
 * @author Alexandre
 * 
 */
interface ReflectionInvoke
{
    
    /**
     * echo.
     * 
     * @param s
     * @return
     */
    public abstract String echo(final String s);
    
    /**
     * echo.
     * 
     * @param s
     * @return
     */
    public abstract String echoWithGetVariable();
    
    /**
     * echo.
     * 
     * @param s
     * @return
     */
    public abstract String echoWithSetVariable(final String s);
    
    /**
     * returnBlank.
     * 
     * @return
     */
    public abstract String returnBlank();
    
    /**
     * getVariable.
     * 
     * @return
     */
    public abstract String getVariable();
    
    /**
     * setVariable.
     * 
     * @param aVariable
     */
    public abstract void setVariable(final String aVariable);
    
}

/**
 * InvokeImpl.
 * 
 * @author Alexandre
 * 
 */
final class ReflectionInvokeImpl implements ReflectionInvoke
{
    
    /**
     * STATIC_VARIABLE
     */
    public static String STATIC_VARIABLE = " ";
    
    private String       variable        = " ";
    @SuppressWarnings("unused")
    private String       echoSet;
    private String       echoGet         = "echo ";
    
    /**
     * echoWithSetVariable.
     * 
     * @param s
     * @return
     */
    public final String echoWithSetVariable(final String s)
    {
        this.echoSet = s;
        return s;
    }
    
    /**
     * echoWithGetVariable.
     * 
     * @return
     */
    public final String echoWithGetVariable()
    {
        return this.echoGet;
    }
    
    /**
     * echo.
     * 
     * @param s
     * @return
     */
    public final String echo(final String s)
    {
        return s;
    }
    
    /**
     * echoStatic.
     * 
     * @param s
     * @return
     */
    public static final String echoStatic(final String s)
    {
        return s;
    }
    
    /**
     * returnBlank.
     * 
     * @return
     */
    public final String returnBlank()
    {
        return "";
    }
    
    /**
     * returnBlankStatic.
     * 
     * @return
     */
    public static final String returnBlankStatic()
    {
        return "";
    }
    
    /**
     * getVariable.
     * 
     * @return
     */
    public String getVariable()
    {
        return this.variable;
    }
    
    /**
     * setVariable.
     * 
     * @param aVariable
     */
    public void setVariable(final String aVariable)
    {
        this.variable = aVariable;
    }
}

/**
 * ClassWith1String.
 * 
 * @author Alexandre
 * 
 */
final class ReflectionClassWith1String
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
