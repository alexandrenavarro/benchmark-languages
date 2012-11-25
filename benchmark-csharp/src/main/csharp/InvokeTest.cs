using System;
using System.Collections.Generic;
using System.Text;


sealed class InvokeTest
{
    
    /**
     * NB_TESTS
     */
    private static int NB_TESTS = 5;

    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    private static int NB_OF_EXCLUSION_MIN_MAX = 2;

    /**
     * NB_INVOKE_TESTS
     */
    private static int NB_INVOKE_TESTS = 2 * 1000 * 1000 * 1000;
    
    /**
     * NB_INVOKE_GETTER_SETTER_TESTS
     */
    private static int NB_INVOKE_GETTER_TESTS = 2 * 1000 * 1000 * 1000;
    
    /**
     * NB_INVOKE_GETTER_SETTER_TESTS
     */
    private static int NB_INVOKE_SETTER_TESTS = 2 * 1000 * 1000 * 1000;

    /**
     * averageTimeWithoutMinMax.
     *
     * @param executionTimes
     * @param numberOfMinMaxToRemove
     * @return
     */
    public static long averageTimeWithoutMinMax(List<long> executionTimes, int numberOfMinMaxToRemove)
    {
        long average = 0;
		int minMax = (numberOfMinMaxToRemove > 0) ? numberOfMinMaxToRemove : 0;
		if (minMax != 0) 
		{
        	executionTimes.Sort();
		}
        for (int i = minMax; i < executionTimes.Count - minMax; i++)
        {
            average += executionTimes[i];
        }
        average = average / (executionTimes.Count - minMax * 2);
        return average;
    }


    /**
     * relativeDeviationTimeWithoutMinMax.
     *
     * @param executionTimes
     * @param numberOfMinMaxToRemove
     * @return
     */
    public static double relativeDeviationTimeWithoutMinMax(List<long> executionTimes, int numberOfMinMaxToRemove)
    {
        long averageTimeWithoutMinMaxVariable = averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
        long deviation = 0;
		int minMax = (numberOfMinMaxToRemove > 0) ? numberOfMinMaxToRemove : 0;
		if (minMax != 0) 
		{
        	executionTimes.Sort();
		}
        for (int i = minMax; i < executionTimes.Count - minMax; i++)
        {
            deviation += (long)Math.Pow(executionTimes[i] - averageTimeWithoutMinMaxVariable, 2);
        }
        return ((int)((Math.Sqrt(deviation / (executionTimes.Count - 2 * minMax)) / averageTimeWithoutMinMaxVariable * 100) * 100)) / 100d;
    }

    /**
     * testInvokeOfAStaticMethod.
     *
     * @return
     */
    public static long testInvokeOfAStaticMethodString()
    {
	    int count = 0;
	    DateTime start = DateTime.Now;
	    for (int i = NB_INVOKE_TESTS; i != 0; i--)
	    {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                count = count + InvokeImpl.echoStatic("1").Length;
            }
            else
            {
                count = count + InvokeImpl.echoStatic("0").Length;
            }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[InvokeTest], Invoke Of A Static Method String " + NB_INVOKE_TESTS + " InvokeImpl.echoStatic(i), count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testInvokeOfAStaticMethod.
 *
 * @return
 */
    public static long testInvokeOfAStaticMethodInt()
    {
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = NB_INVOKE_TESTS; i != 0; i--)
        {
           count = count + InvokeImpl.echoStatic(i);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[InvokeTest], Invoke Of A Static Method int " + NB_INVOKE_TESTS + " InvokeImpl.echoStatic(\"\").Length, count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }
    
    /**
     * testInvokeOfAInstanceMethodWithInterface.
     *
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithInterface()
    {
	    int count = 0;
	    Invoke invoke = new InvokeImpl();
	    DateTime start = DateTime.Now;
        for (int i = NB_INVOKE_TESTS; i != 0; i--)
	    {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                count = count + invoke.echo("1").Length;
            }
            else
            {
                count = count + invoke.echo("0").Length;
            }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
	    Console.WriteLine("[InvokeTest], Invoke Of An Instance Method With Interface " + NB_INVOKE_TESTS + " invoke.echo(\"\").Length, count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testInvokeOfAInstanceMethodWithInterface.
 *
 * @return
 */
    public static long testInvokeOfAnInstanceMethodWithGetInstanceVariableWithInterface()
    {
        int count = 0;
        Invoke invoke = new InvokeImpl();
        DateTime start = DateTime.Now;
        for (int i = NB_INVOKE_GETTER_TESTS; i != 0; i--)
        {
            // Write to force cpp not to optimize the code, never executed
            if (i % 2 == 2)
            {
                invoke.setEchoGet("1");
            }
            count = count + invoke.echoWithGetVariable().Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method With Get Instance Variable With Interface " + NB_INVOKE_TESTS + " invoke.echoWithGetVariable().Length, count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
* testInvokeOfAInstanceMethodWithInterface.
*
* @return
*/
    public static long testInvokeOfAnInstanceMethodWithSetInstanceVariableWithInterface()
    {
        int count = 0;
        Invoke invoke = new InvokeImpl();
        DateTime start = DateTime.Now;
        for (int i = NB_INVOKE_SETTER_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                count = count + invoke.echoWithSetVariable("1").Length;
            }
            else
            {
                count = count + invoke.echoWithSetVariable("0").Length;
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method With Set Instance Variable With Interface " + NB_INVOKE_TESTS + " invoke.echoWithSetVariable(\"1\").Length, count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    
    /**
     * testInvokeOfAnInstanceMethodWithoutInterface.
     *
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithoutInterface()
    {
	    int count = 0;
        InvokeImpl invoke = new InvokeImpl();
	    DateTime start = DateTime.Now;
        for (int i = NB_INVOKE_TESTS; i != 0; i--)
	    {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                count = count + invoke.echo("1").Length;
            }
            else
            {
                count = count + invoke.echo("0").Length;
            }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
	    Console.WriteLine("[InvokeTest], Invoke Of An Instance Method Without Interface " + NB_INVOKE_TESTS + " invoke.echo(\"\").Length, count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testInvokeOfAnInstanceMethodWithoutInterface.
 *
 * @return
 */
    public static long testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterface()
    {
        int count = 0;
        InvokeImpl invoke = new InvokeImpl();
        DateTime start = DateTime.Now;
        for (int i = NB_INVOKE_GETTER_TESTS; i != 0; i--)
        {
            // Write to force cpp not to optimize the code, never executed
            if (i % 2 == 2)
            {
                invoke.setEchoGet("1");
            }
            count = count + invoke.echoWithGetVariable().Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method With Get Instance Variable Without Interface " + NB_INVOKE_TESTS + " invoke.echoWithGetVariable(\"1\").Length, count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testInvokeOfAnInstanceMethodWithoutInterface.
 *
 * @return
 */
    public static long testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface()
    {
        int count = 0;
        InvokeImpl invoke = new InvokeImpl();
        DateTime start = DateTime.Now;
        for (int i = NB_INVOKE_SETTER_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                count = count + invoke.echoWithSetVariable("1").Length;
            }
            else
            {
                count = count + invoke.echoWithSetVariable("0").Length;
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method With Set Instance Variable Without Interface " + NB_INVOKE_TESTS + " invoke.echoWithSetVariable(\"1\").Length, count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testInvokeOfAnInstanceMethodWithoutInterface.
 *
 * @return
 */
    public static long testInvokeOfAnInstanceMethod()
    {
        int count = 0;
        InvokeInstance invoke = new InvokeInstance();
        DateTime start = DateTime.Now;
        for (int i = NB_INVOKE_TESTS; i != 0; i--)
        {
             // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                count = count + invoke.echo("1").Length;
            }
            else
            {
                count = count + invoke.echo("0").Length;
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method Without Interface " + NB_INVOKE_TESTS + " invoke.echo(\"\").Length, count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testInvokeOfAnInstanceMethodWithoutInterface.
 *
 * @return
 */
    public static long testInvokeOfAnInstanceMethodWithGetInstanceVariable()
    {
        int count = 0;
        InvokeInstance invoke = new InvokeInstance();
        DateTime start = DateTime.Now;
        for (int i = NB_INVOKE_GETTER_TESTS; i != 0; i--)
        {
            // Write to force cpp not to optimize the code, never executed
            if (i % 2 == 2)
            {
                invoke.setEchoGet("1");
            }
            count = count + invoke.echoWithGetVariable().Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method With Get Instance Variable " + NB_INVOKE_TESTS + " invoke.echoWithGetVariable(\"1\").Length, count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testInvokeOfAnInstanceMethodWithoutInterface.
 *
 * @return
 */
    public static long testInvokeOfAnInstanceMethodWithSetInstanceVariable()
    {
        int count = 0;
        InvokeInstance invoke = new InvokeInstance();
        DateTime start = DateTime.Now;
        for (int i = NB_INVOKE_SETTER_TESTS; i != 0; i--)
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                count = count + invoke.echoWithSetVariable("0").Length;
            }
            else
            {
                count = count + invoke.echoWithSetVariable("1").Length;
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method With Set Instance Variable " + NB_INVOKE_TESTS + " invoke.echoWithSetVariable(\"1\").Length, count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
     * main.
     *
     * @param args
     */
    public static void main(string[] args)
    {
	    int nbTests = (args != null && args.Length >= 1) ? int.Parse(args[0]) : NB_TESTS;
	    int nbOfExclusionMinMax = (args != null && args.Length >= 2) ? int.Parse(args[1]) : NB_OF_EXCLUSION_MIN_MAX;

	    List<long> executionTimes = new List<long>(nbTests);
        
	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(InvokeTest.testInvokeOfAStaticMethodString());
        executionTimes.Sort();
	    Console.WriteLine("[InvokeTest], Invoke Of A Static Method String" + NB_INVOKE_TESTS + " InvokeImpl.echoStatic(\"1\").Length,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(InvokeTest.testInvokeOfAStaticMethodInt());
        executionTimes.Sort();
        Console.WriteLine("[InvokeTest], Invoke Of A Static Method int" + NB_INVOKE_TESTS + " InvokeImpl.echoStatic(\"1\").Length,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
         
	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(InvokeTest.testInvokeOfAnInstanceMethodWithInterface());
        executionTimes.Sort();
	    Console.WriteLine("[InvokeTest], Invoke Of An Instance Method With Interface " + NB_INVOKE_TESTS + " invoke.echo(\"1\").Length,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(InvokeTest.testInvokeOfAnInstanceMethodWithGetInstanceVariableWithInterface());
        executionTimes.Sort();
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method With Get Instance Variable With Interface " + NB_INVOKE_TESTS + " invoke.echoWithGetVariable().Length,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(InvokeTest.testInvokeOfAnInstanceMethodWithSetInstanceVariableWithInterface());
        executionTimes.Sort();
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method With Set Instance Variable With Interface " + NB_INVOKE_TESTS + " invoke.echoWithSetVariable(\"1\").Length,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

    	
	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(InvokeTest.testInvokeOfAnInstanceMethodWithoutInterface());
        executionTimes.Sort();
	    Console.WriteLine("[InvokeTest], Invoke Of An Instance Method Without Interface " + NB_INVOKE_TESTS + " invoke.echo(\"1\").Length,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(InvokeTest.testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterface());
        executionTimes.Sort();
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method Get Instance Variable Without Interface " + NB_INVOKE_TESTS + " iinvoke.echoWithGetVariable().Length,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(InvokeTest.testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface());
        executionTimes.Sort();
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method With Set Instance Variable Without Interface " + NB_INVOKE_TESTS + " invoke.echoWithSetVariable(\"1\").Length,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(InvokeTest.testInvokeOfAnInstanceMethod());
        executionTimes.Sort();
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method " + NB_INVOKE_TESTS + " invoke.echo(\"1\").Length,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(InvokeTest.testInvokeOfAnInstanceMethodWithGetInstanceVariable());
        executionTimes.Sort();
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method Get Instance Variable " + NB_INVOKE_TESTS + " iinvoke.echoWithGetVariable().Length,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(InvokeTest.testInvokeOfAnInstanceMethodWithSetInstanceVariable());
        executionTimes.Sort();
        Console.WriteLine("[InvokeTest], Invoke Of An Instance Method With Set Instance Variable " + NB_INVOKE_TESTS + " invoke.echoWithSetVariable(\"1\").Length,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
    	
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
    string echo(string s);

    /**
     * echo.
     *
     * @param s
     * @return
     */
    string echoWithGetVariable();
    
    /**
     * echo.
     *
     * @param s
     * @return
     */
    string echoWithSetVariable(string s);


    /**
     * returnBlank.
     *
     * @return
     */
    string returnBlank();
    
    /**
     * getVariable.
     *
     * @return
     */
    string getVariable();


    /**
     * setVariable.
     *
     * @param aVariable
     */
    void setVariable(string aVariable);


    void setEchoGet(string aVariable);
    
}


/**
 * InvokeImpl.
 *
 * @author Alexandre
 *
 */
sealed class InvokeImpl : Invoke
{
    private string variable = " ";
    public static string STATIC_VARIABLE = " ";
    private string echoSet;
    private string echoGet = "echo";

    


    /**
     * @see Invoke#echo(java.lang.String)
     */
    public string echoWithSetVariable(string s)
    {
	    this.echoSet = s;
	    return s;
    }
    
    /**
     * @see Invoke#echo(java.lang.String)
     */
    public string echoWithGetVariable()
    {
	    return this.echoGet;
    }


    /**
     * @see Invoke#echo(java.lang.string)
     */
    public string echo(string s)
    {
	    return s;
    }
    
    /**
     * echoStatic.
     *
     * @param s
     * @return
     */
    public static string echoStatic(string s)
    {
	    return s;
    }

    /**
     * echoStatic.
     *
     * @param s
     * @return
     */
    public static int echoStatic(int s)
    {
        return s;
    }


    
    /**
     * @see Invoke#returnBlank()
     */
    public string returnBlank()
    {
	    return "";
    }
    
    /**
     * returnBlankStatic.
     *
     * @return
     */
    public static string returnBlankStatic()
    {
	    return "";
    }
    
    /**
     * @see Invoke#getVariable()
     */
    public string getVariable()
    {
        return this.variable;
    }


    /**
     * @see Invoke#setVariable(java.lang.string)
     */
    public void setVariable(string aVariable)
    {
        this.variable = aVariable;
    }

    /**
     * @see Invoke#setVariable(java.lang.string)
     */
    public void setEchoGet(string aVariable)
    {
        this.echoGet = aVariable;
    }

}



/**
 * InvokeImpl.
 *
 * @author Alexandre
 *
 */
sealed class InvokeInstance
{
    private string variable = " ";
    public static string STATIC_VARIABLE = " ";
    private string echoSet;
    private string echoGet = "echo";




    /**
     * @see Invoke#echo(java.lang.String)
     */
    public string echoWithSetVariable(string s)
    {
        this.echoSet = s;
        return s;
    }

    /**
     * @see Invoke#echo(java.lang.String)
     */
    public string echoWithGetVariable()
    {
        return this.echoGet;
    }


    /**
     * @see Invoke#echo(java.lang.string)
     */
    public string echo(string s)
    {
        return s;
    }

    /**
     * echoStatic.
     *
     * @param s
     * @return
     */
    public static string echoStatic(string s)
    {
        return s;
    }

    /**
     * echoStatic.
     *
     * @param s
     * @return
     */
    public static int echoStatic(int s)
    {
        return s;
    }



    /**
     * @see Invoke#returnBlank()
     */
    public string returnBlank()
    {
        return "";
    }

    /**
     * returnBlankStatic.
     *
     * @return
     */
    public static string returnBlankStatic()
    {
        return "";
    }

    /**
     * @see Invoke#getVariable()
     */
    public string getVariable()
    {
        return this.variable;
    }


    /**
     * @see Invoke#setVariable(java.lang.string)
     */
    public void setVariable(string aVariable)
    {
        this.variable = aVariable;
    }

    /**
     * @see Invoke#setVariable(java.lang.string)
     */
    public void setEchoGet(string aVariable)
    {
        this.echoGet = aVariable;
    }

}


