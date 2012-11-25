using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;


sealed class ReflectionTest
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
     * NB_REFLECTION_TESTS
     */
    private static int NB_STATIC_REFLECTION_TESTS =  1000 * 1000;
    
    /**
     * NB_REFLECTION_TESTS
     */
    private static int NB_INSTANCE_REFLECTION_TESTS = 20 * 1000 * 1000;
    
    /**
     * NB_GETTER_TESTS
     */
    private static int NB_GETTER_TESTS  = 5 * 1000 * 1000;
    
    /**
     * NB_SETTER_TESTS
     */
    private static int NB_SETTER_TESTS  = 5 * 1000 * 1000;
    
    /**
     * TESTS
     */
    private static int NB_CREATION_TESTS = 1 * 1000 * 1000;


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
     * testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface.
     *
     * @return
     */
    public static long testCreationReflectionClassWith1String()
    {
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
            ReflectionClassWith1String s = new ReflectionClassWith1String();
            count = count + s.String1.Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ReflectionTest], Creation of ReflectionClassWith1String " + NB_CREATION_TESTS + ", count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
     * testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface.
     *
     * @return
     */
    public static long testCreationReflectionClassWith1StringWithReflection()
    {
        int count = 0;
        Type type =
        Type.GetType(
            "ReflectionClassWith1String");
        DateTime start = DateTime.Now;
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
            ReflectionClassWith1String s = (ReflectionClassWith1String) Activator.CreateInstance(type);
            count = count + s.String1.Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ReflectionTest], Creation of ReflectionClassWith1String With Reflection " + NB_CREATION_TESTS + ", count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
 * testCreationOfString
 */
    public static long testGetterReflectionClassWith1String()
    {
        ReflectionClassWith1String classWith1String = new ReflectionClassWith1String();
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = NB_GETTER_TESTS; i != 0; i--)
        {
            count += classWith1String.String1.Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
	    Console.WriteLine("[ReflectionTest], Getter of ReflectionClassWith1String() test=" + NB_GETTER_TESTS + ", count=" + count
                + ", snapshot time," +	executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }

    /**
     * testCreationOfString
     */
    public static long testGetterReflectionClassWith1StringWithReflection()
    {
	    int count = 0;
        object[] arguments = new object [] {};
        ReflectionClassWith1String reflectionClassWith1String = new ReflectionClassWith1String();
        Type type = reflectionClassWith1String.GetType();
	    DateTime start = DateTime.Now;
        for (int i = NB_GETTER_TESTS; i != 0; i--)
        {
             String s = (String)type.InvokeMember("String1",
                             BindingFlags.Default | BindingFlags.GetProperty,
                             null,
                             reflectionClassWith1String,
                             arguments);

            count = count + s.Length;
        }

        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ReflectionTest], Getter of ReflectionClassWith1String() with Reflection test=" + NB_GETTER_TESTS + ", count=" + count
                + ", snapshot time," + 	executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }


    /**
 * testCreationOfString
 */
    public static long testSetterReflectionClassWith1String()
    {
        ReflectionClassWith1String classWith1String = new ReflectionClassWith1String();
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = NB_SETTER_TESTS; i != 0; i--)
        {
            classWith1String.String1 = "string1";
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ReflectionTest], Setter of ReflectionClassWith1String() test=" + NB_GETTER_TESTS + ", count=" + count
                + ", snapshot time," + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
     * testCreationOfString
     */
    public static long testSetterReflectionClassWith1StringWithReflection()
    {
        ReflectionClassWith1String reflectionClassWith1String = new ReflectionClassWith1String();
        object[] arguments = new object[] { "string1"};
        DateTime start = DateTime.Now;
        for (int i = NB_SETTER_TESTS; i != 0; i--)
        {

            reflectionClassWith1String.GetType().InvokeMember("String1",
                            BindingFlags.Public | BindingFlags.Instance | BindingFlags.SetProperty,
                            null,
                            reflectionClassWith1String,
                            arguments);
        }

        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ReflectionTest], Setter of ReflectionClassWith1String() with Reflection test=" + NB_GETTER_TESTS 
                + ", snapshot time,," + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    
    /**
     * testInvokeOfAStaticMethod.
     *
     * @return
     */
    public static long testInvokeOfAStaticMethod()
    {
	    int count = 0;
	    DateTime start = DateTime.Now;
	    for (int i = NB_STATIC_REFLECTION_TESTS; i != 0; i--)
	    {
	        count = count + ReflectionInvokeImpl.echoStatic("1").Length;
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
	    Console.WriteLine("[ReflectionTest], Invoke Of A Static Method " + NB_STATIC_REFLECTION_TESTS + " ReflectionInvokeImpl.echoStatic(\"\").length(), count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
    
    /**
     * testInvokeOfAStaticMethod.
     *
     * @return
     */
    public static long testInvokeOfAStaticMethodWithReflection()
    {
        int count = 0;
        ReflectionInvokeImpl reflectionInvokeImpl = new ReflectionInvokeImpl();
        Type type = reflectionInvokeImpl.GetType();
        object[] arguments = new object[] { "1" };
        DateTime start = DateTime.Now;
        for (int i = NB_STATIC_REFLECTION_TESTS; i != 0; i--)
        {
            String s = (String)type.InvokeMember("echoStatic",
                             BindingFlags.Public | BindingFlags.Static | BindingFlags.InvokeMethod,
                             null,
                             reflectionInvokeImpl,
                             arguments);

            count = count + s.Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;

        Console.WriteLine("[ReflectionTest], Invoke Of A Static Method With Reflection " + NB_STATIC_REFLECTION_TESTS + " ReflectionInvokeImpl.echoStatic(\"\").length(), count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }



    /**
     * testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface.
     *
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterface()
    {
        int count = 0;
        ReflectionInvokeImpl invoke = new ReflectionInvokeImpl();
        DateTime start = DateTime.Now;
        for (int i = NB_INSTANCE_REFLECTION_TESTS; i != 0; i--)
        {
            count = count + invoke.echoWithGetVariable().Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ReflectionTest], Invoke Of An Instance Method With Get Instance Variable Without Interface " + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithGetVariable().length()  , count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
     * testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface.
     *
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterfaceWithReflection()
    {
        int count = 0;
        ReflectionInvokeImpl reflectionInvokeImpl = new ReflectionInvokeImpl();
        Type type = reflectionInvokeImpl.GetType();
        object[] arguments = new object[] {};
        DateTime start = DateTime.Now;
        for (int i = NB_INSTANCE_REFLECTION_TESTS; i != 0; i--)
        {
            String s = (String)type.InvokeMember("echoWithGetVariable",
                             BindingFlags.InvokeMethod,
                             null,
                             reflectionInvokeImpl,
                             arguments);

            count = count + s.Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ReflectionTest], Invoke Of An Instance Method With Set Instance Variable Without Interface With Reflection " + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithSetVariable(\"1\").length()  , count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
     * testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface.
     *
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface()
    {
	    int count = 0;
	    InvokeImpl invoke = new InvokeImpl();
	    DateTime start = DateTime.Now;
	    for (int i = NB_INSTANCE_REFLECTION_TESTS; i != 0; i--)
	    {
	        count = count + invoke.echoWithSetVariable("1").Length;
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[ReflectionTest], Invoke Of An Instance Method With Set Instance Variable Without Interface " + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithSetVariable(\"1\").length()  , count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
    
    /**
     * testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface.
     *
     * @return
     */
    public static long testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterfaceWithReflection()
    {
        int count = 0;
        ReflectionInvokeImpl reflectionInvokeImpl = new ReflectionInvokeImpl();
        Type type = reflectionInvokeImpl.GetType();
        object[] arguments = new object[] { "1" };
        DateTime start = DateTime.Now;
        for (int i = NB_INSTANCE_REFLECTION_TESTS; i != 0; i--)
        {
            String s = (String)type.InvokeMember("echoWithSetVariable",
                             BindingFlags.InvokeMethod,
                             null,
                             reflectionInvokeImpl,
                             arguments);

            count = count + s.Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ReflectionTest], Invoke Of An Instance Method With Set Instance Variable Without Interface With Reflection " + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithSetVariable(\"1\").length()  , count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
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
            executionTimes.Add(ReflectionTest.testCreationReflectionClassWith1String());
        executionTimes.Sort();
        Console.WriteLine("[ReflectionTest], Creation of String " + NB_CREATION_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ReflectionTest.testCreationReflectionClassWith1StringWithReflection());
        executionTimes.Sort();
        Console.WriteLine("[ReflectionTest], Creation of String Wihh Reflection " + NB_CREATION_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ReflectionTest.testGetterReflectionClassWith1String());
        executionTimes.Sort();
	    Console.WriteLine("[ReflectionTest], Getter of ReflectionClassWith1String() test=" + NB_GETTER_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
        
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ReflectionTest.testGetterReflectionClassWith1StringWithReflection());
        executionTimes.Sort();
	    Console.WriteLine("[ReflectionTest], Getter of ReflectionClassWith1String() with Reflection test=" + NB_GETTER_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
    	
        
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ReflectionTest.testSetterReflectionClassWith1String());
        executionTimes.Sort();
        Console.WriteLine("[ReflectionTest], Setter of ReflectionClassWith1String() test=" + NB_SETTER_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(ReflectionTest.testSetterReflectionClassWith1StringWithReflection());
        executionTimes.Sort();
        Console.WriteLine("[ReflectionTest], Setter of ReflectionClassWith1String() with Reflection test=" + NB_SETTER_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(ReflectionTest.testInvokeOfAStaticMethod());
        executionTimes.Sort();
	    Console.WriteLine("[ReflectionTest], Invoke Of A Static Method " + NB_STATIC_REFLECTION_TESTS
		    + " ReflectionInvokeImpl.echoStatic(\"\").length(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(ReflectionTest.testInvokeOfAStaticMethodWithReflection());
        executionTimes.Sort();
	    Console.WriteLine("[ReflectionTest], Invoke Of A Static Method With Reflection " + NB_STATIC_REFLECTION_TESTS
		    + " ReflectionInvokeImpl.echoStatic(\"\").length(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
        

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ReflectionTest.testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterface());
        executionTimes.Sort();
        Console.WriteLine("[ReflectionTest], Invoke Of An Instance Method With Get Instance Variable Without Interface " + NB_INSTANCE_REFLECTION_TESTS
            + " invoke.echoWithGetVariable().length()  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ReflectionTest.testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterfaceWithReflection());
        executionTimes.Sort();
        Console.WriteLine("[ReflectionTest], Invoke Of An Instance Method With Get Instance Variable Without Interface With Reflection "
            + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithSetVariable().length()  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
        
        
	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(ReflectionTest.testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface());
        executionTimes.Sort();
	    Console.WriteLine("[ReflectionTest], Invoke Of An Instance Method With Set Instance Variable Without Interface " + NB_INSTANCE_REFLECTION_TESTS
		    + " invoke.echoWithSetVariable(\"1\").length()  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(ReflectionTest.testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterfaceWithReflection());
        executionTimes.Sort();
	    Console.WriteLine("[ReflectionTest], Invoke Of An Instance Method With Set Instance Variable Without Interface With Reflection "
		    + NB_INSTANCE_REFLECTION_TESTS + " invoke.echoWithSetVariable(\"1\").length()  ,, average time,"
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
interface ReflectionInvoke
{

    
    /**
     * echo.
     *
     * @param s
     * @return
     */
    String echo(String s);
    
    /**
     * echo.
     *
     * @param s
     * @return
     */
    String echoWithGetVariable();
    
    /**
     * echo.
     *
     * @param s
     * @return
     */
    String echoWithSetVariable(String s);

    /**
     * returnBlank.
     *
     * @return
     */
    String returnBlank();
    
    /**
     * getVariable.
     *
     * @return
     */
    String getVariable();


    /**
     * setVariable.
     *
     * @param aVariable
     */
    void setVariable(String aVariable);
    
}


/**
 * InvokeImpl.
 *
 * @author Alexandre
 *
 */
class ReflectionInvokeImpl : ReflectionInvoke
{
    private String variable = " ";
    public static String STATIC_VARIABLE = " ";
    private String echoSet;
    private String echoGet = "echo ";
    

    /**
     * @see Invoke#echo(java.lang.String)
     */
    public String echoWithSetVariable(String s)
    {
	    this.echoSet = s;
	    return s;
    }
    
    /**
     * @see Invoke#echo(java.lang.String)
     */
    public  String echoWithGetVariable()
    {
	    return this.echoGet;
    }
    
    /**
     * @see Invoke#echo(java.lang.String)
     */
    public String echo(String s)
    {
	    return s;
    }
    
    /**
     * echoStatic.
     *
     * @param s
     * @return
     */
    public static String echoStatic(String s)
    {
	    return s;
    }
    
    /**
     * @see Invoke#returnBlank()
     */
    public String returnBlank()
    {
	    return "";
    }
    
    /**
     * returnBlankStatic.
     *
     * @return
     */
    public static String returnBlankStatic()
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
    public void setVariable(String aVariable)
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
class ReflectionClassWith1String
{
    private String string1 = "string1";


    public string String1
    {
        get { return string1; }
        set { string1 = value; }
    }


    /**
     * getString1.
     * 
     * @return
     */
    /*
    public String getString1()
    {
        return this.string1;
    }
     */

    /**
     * setString1.
     * 
     * @param aString1
     */
    /*
    public void setString1(String aString1)
    {
        this.string1 = aString1;
    }
    */
    
}

