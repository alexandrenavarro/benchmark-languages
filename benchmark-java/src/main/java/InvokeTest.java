import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * InvokeTest.
 * 
 * @author anavarro122404 - 25 sept. 07
 * 
 * 
 * 
 */
public final class InvokeTest
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
     * NB_INVOKE_TESTS
     */
    private static final int NB_INVOKE_TESTS         = 2 * 1000 * 1000 * 1000;
    
    /**
     * NB_INVOKE_GETTER_SETTER_TESTS
     */
    private static final int NB_INVOKE_GETTER_TESTS  = 2 * 1000 * 1000 * 1000;
    
    /**
     * NB_INVOKE_GETTER_SETTER_TESTS
     */
    private static final int NB_INVOKE_SETTER_TESTS  = 2 * 1000 * 1000 * 1000;
   
    
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
     * testInvokeOfAStaticMethod.
     * 
     * @return
     */
    public static long testInvokeOfAStaticMethodString()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_INVOKE_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                count = count + InvokeImpl.echoStatic("0").length();
            }
            else
            {
                count = count + InvokeImpl.echoStatic("1").length();
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[InvokeTest], Invoke Of A Static Method String " + NB_INVOKE_TESTS + " InvokeImpl.echoStatic(\"\").length(), count=" + count
                + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAStaticMethod.
     * 
     * @return
     */
    public static long testInvokeOfAStaticMethodInt()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = NB_INVOKE_TESTS; i != 0; i--)
        {
            count = count + InvokeImpl.echoStatic(i);
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[InvokeTest], Invoke Of A Static Method Int " + NB_INVOKE_TESTS + " InvokeImpl.echoStatic(i), count=" + count
                + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAInstanceMethodWithInterface.
     * 
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithInterface()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        final Invoke invoke = new InvokeImpl();
        start = System.currentTimeMillis();
        for (int i = NB_INVOKE_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                count = count + invoke.echo("1").length();
            }
            else
            {
                count = count + invoke.echo("0").length();
            }
                
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Interface " + NB_INVOKE_TESTS + " invoke.echo(\"1\").length(), count="
                + count + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAnInstanceMethodWithGetInstanceVariableWithInterface.
     * 
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithGetInstanceVariableWithInterface()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        final Invoke invoke = new InvokeImpl();
        start = System.currentTimeMillis();
        for (int i = NB_INVOKE_GETTER_TESTS; i != 0; i--)
        {
            // Write to force cpp not to optimize the code, never executed
            if (i % 2 == 2)
            {
                invoke.setEchoGet("1");
            }
            count = count + invoke.echoWithGetVariable().length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Get Instance Variable With Interface " + NB_INVOKE_TESTS
                + " invoke.echoWithGetVariable().length(), count=" + count + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAnInstanceMethodWithGetInstanceVariableWithInterface.
     * 
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithSetInstanceVariableWithInterface()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        final Invoke invoke = new InvokeImpl();
        start = System.currentTimeMillis();
        for (int i = NB_INVOKE_SETTER_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                count = count + invoke.echoWithSetVariable("1").length();
            }
            else
            {
                count = count + invoke.echoWithSetVariable("0").length();
            }
            
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Set Instance Variable With Interface " + NB_INVOKE_TESTS
                + " invoke.echoWithSetVariable(\"1\").length(), count=" + count + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAnInstanceMethodWithoutInterface.
     * 
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithoutInterface()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        final InvokeImpl invoke = new InvokeImpl();
        start = System.currentTimeMillis();
        for (int i = NB_INVOKE_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                count = count + invoke.echo("1").length();
            }
            else
            {
                count = count + invoke.echo("0").length();
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[InvokeTest], Invoke Of An Instance Method Without Interface " + NB_INVOKE_TESTS + " invoke.echo(\"\").length(), count="
                + count + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterface.
     * 
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterface()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        final InvokeImpl invoke = new InvokeImpl();
        start = System.currentTimeMillis();
        for (int i = NB_INVOKE_GETTER_TESTS; i != 0; i--)
        {
            // Write to force cpp not to optimize the code, never executed
            if (i % 2 == 2)
            {
                invoke.setEchoGet("1");
            }
            count = count + invoke.echoWithGetVariable().length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Get Instance Variable Without Interface " + NB_INVOKE_TESTS
                + " invoke.echoWithGetVariable().length(), count=" + count + ", snapshot time," + executionTime);
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
        final InvokeImpl invoke = new InvokeImpl();
        start = System.currentTimeMillis();
        for (int i = NB_INVOKE_SETTER_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                count = count + invoke.echoWithSetVariable("1").length();
            }
            else
            {
                count = count + invoke.echoWithSetVariable("0").length();
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Get Instance Variable Without Interface " + NB_INVOKE_TESTS
                + " invoke.echoWithSetVariable(\"1\").length(), count=" + count + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAnInstanceMethodWithoutInterface.
     * 
     * @return
     */
    public static long testInvokeOfAnInstanceMethod()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        final InvokeInstance invoke = new InvokeInstance();
        start = System.currentTimeMillis();
        for (int i = NB_INVOKE_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                count = count + invoke.echo("1").length();
            }
            else
            {
                count = count + invoke.echo("0").length();
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[InvokeTest], Invoke Of An Instance Method " + NB_INVOKE_TESTS + " invoke.echo(\"\").length(), count="
                + count + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterface.
     * 
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithGetInstanceVariable()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        final InvokeInstance invoke = new InvokeInstance();
        
        start = System.currentTimeMillis();
        for (int i = NB_INVOKE_GETTER_TESTS; i != 0; i--)
        {
            // Write to force cpp not to optimize the code, never executed
            if (i % 2 == 2)
            {
                invoke.setEchoGet("1");
            }
            count = count + invoke.echoWithGetVariable().length();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Get Instance Variable " + NB_INVOKE_TESTS
                + " invoke.echoWithGetVariable().length(), count=" + count + ", snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface.
     * 
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithSetInstanceVariable()
    {
        long start = System.currentTimeMillis();
        int count = 0;
        final InvokeInstance invoke = new InvokeInstance();
        start = System.currentTimeMillis();
        for (int i = NB_INVOKE_SETTER_TESTS; i != 0; i--)
        {
            if (i % 2 == 0)
            {
                count = count + invoke.echoWithSetVariable("0").length();
            }
            else
            {
                count = count + invoke.echoWithSetVariable("1").length();
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Set Instance Variable " + NB_INVOKE_TESTS
                + " invoke.echoWithSetVariable(\"1\").length(), count=" + count + ", snapshot time," + executionTime);
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
            executionTimes.add(InvokeTest.testInvokeOfAStaticMethodString());
        System.out.println("[InvokeTest], Invoke Of A Static Method String " + NB_INVOKE_TESTS + " InvokeImpl.echoStatic(\"\").length(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(InvokeTest.testInvokeOfAStaticMethodInt());
        System.out.println("[InvokeTest], Invoke Of A Static Method Int " + NB_INVOKE_TESTS + " InvokeImpl.echoStatic(\"\").length(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(InvokeTest.testInvokeOfAnInstanceMethodWithInterface());
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Interface " + NB_INVOKE_TESTS
                + " invoke.echo(\"1\").length(),, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes)
                + ", max time," + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(InvokeTest.testInvokeOfAnInstanceMethodWithGetInstanceVariableWithInterface());
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Get Instance Variable With Interface " + NB_INVOKE_TESTS
                + " invoke.echoWithGetVariable(\"1\").length(),, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(InvokeTest.testInvokeOfAnInstanceMethodWithSetInstanceVariableWithInterface());
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Set Instance Variable With Interface " + NB_INVOKE_TESTS
                + " invoke.echoWithSetVariable(\"1\").length(),, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(InvokeTest.testInvokeOfAnInstanceMethodWithoutInterface());
        System.out.println("[InvokeTest], Invoke Of An Instance Method Without Interface " + NB_INVOKE_TESTS
                + " invoke.echo(\"1\").length(),, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes)
                + ", max time," + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(InvokeTest.testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterface());
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Get Instance Variable Without Interface " + NB_INVOKE_TESTS
                + " invoke.echoWithGetVariable().length(),, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(InvokeTest.testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface());
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Set Instance Variable Without Interface " + NB_INVOKE_TESTS
                + " invoke.echoWithSetVariable(\"1\").length(),, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(InvokeTest.testInvokeOfAnInstanceMethod());
        System.out.println("[InvokeTest], Invoke Of An Instance Method " + NB_INVOKE_TESTS
                + " invoke.echo(\"1\").length(),, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes)
                + ", max time," + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(InvokeTest.testInvokeOfAnInstanceMethodWithGetInstanceVariable());
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Get Instance Variable " + NB_INVOKE_TESTS
                + " invoke.echoWithGetVariable().length(),, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(InvokeTest.testInvokeOfAnInstanceMethodWithSetInstanceVariable());
        System.out.println("[InvokeTest], Invoke Of An Instance Method With Set Instance Variable " + NB_INVOKE_TESTS
                + " invoke.echoWithSetVariable(\"1\").length(),, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
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
interface Invoke
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
    
    /**
     * setEchoGet.
     * 
     * @param aEchoGet
     */
    public abstract void setEchoGet(final String aEchoGet);
}

/**
 * InvokeImpl.
 * 
 * @author Alexandre
 * 
 */
final class InvokeImpl implements Invoke
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
     * @see Invoke#echo(java.lang.String)
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
     * echoStatic.
     * 
     * @param s
     * @return
     */
    public static final int echoStatic(final int s)
    {
        return s;
    }
    
    /**
     * @see Invoke#returnBlank()
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
     * @see Invoke#getVariable()
     */
    public String getVariable()
    {
        return this.variable;
    }
    
    /**
     * @see Invoke#setVariable(java.lang.String)
     */
    public void setVariable(final String aVariable)
    {
        this.variable = aVariable;
    }
    
    /**
     * Sets the echoGet.
     * 
     * @param aEchoGet The echoGet to set.
     */
    public final void setEchoGet(String aEchoGet)
    {
        this.echoGet = aEchoGet;
    }
}

/**
 * InvokeInstance.
 * 
 * @author Alexandre
 * 
 */
final class InvokeInstance
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
     * @see Invoke#echo(java.lang.String)
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
     * echoStatic.
     * 
     * @param s
     * @return
     */
    public static final int echoStatic(final int s)
    {
        return s;
    }
    
    /**
     * @see Invoke#returnBlank()
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
     * @see Invoke#getVariable()
     */
    public String getVariable()
    {
        return this.variable;
    }
    
    /**
     * @see Invoke#setVariable(java.lang.String)
     */
    public void setVariable(final String aVariable)
    {
        this.variable = aVariable;
    }

    
    /**
     * Sets the echoGet.
     * 
     * @param aEchoGet The echoGet to set.
     */
    public final void setEchoGet(String aEchoGet)
    {
        this.echoGet = aEchoGet;
    }
    
    
    
}
