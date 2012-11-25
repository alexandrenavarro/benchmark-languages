using System;
using System.Collections.Generic;
using System.Text;


sealed class CreationTest
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
     * NB_CREATION_TESTS
     */
    private static int NB_CREATION_TESTS = 50 * 1000 * 1000;
    
    /**
     * NB_CREATION_TESTS
     */
    private static int NB_CREATION_INT_TESTS = 2000 * 1000 * 1000;
    
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
    * testCreation
    */
    public static long testCreationOfStringi()
    {
	    DateTime start = DateTime.Now;
	    for (int i = NB_CREATION_TESTS; i != 0; i--)
	    {
	        String s = "string" + i;
	    }
	    DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_TESTS + " new String(\"string\" + i),, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long) executionTime.TotalMilliseconds;
    }

    /**
     * testCreationOfString
     */
    public static long testCreationOfClassWith1String()
    {
	    DateTime start = DateTime.Now;
	    for (int i = NB_CREATION_TESTS; i != 0; i--)
	    {
	        ClassWith1String classWith1String = new ClassWith1String();
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith1String(),, snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }


    /**
 * testCreationOfString
 */
    public static long testCreationOfClassWith10String()
    {
	    DateTime start = DateTime.Now;
	    for (int i = NB_CREATION_TESTS; i != 0; i--)
	    {
	        ClassWith10String classWith10String1 = new ClassWith10String();
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith10String(),, snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }

/**
 * testCreationOfint.
 *
 * @return
 */
    public static long testCreationOfint()
    {
	    DateTime start = DateTime.Now;
        for (int i = NB_CREATION_INT_TESTS; i != 0; i--)
	    {
	        int k = i;
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_INT_TESTS + " int k = i,, snapshot time," + executionTime.TotalMilliseconds);
        return (long) executionTime.TotalMilliseconds;
    }

    /**
 * testCreationOfString
 */
    public static long testCreationOfClassWith1int()
    {
	    DateTime start = DateTime.Now;
	    for (int i = NB_CREATION_TESTS; i != 0; i--)
	    {
	        ClassWith1int classWith1int = new ClassWith1int();
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith1int(),, snapshot time," + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
 * testCreationOfString
 */
    public static long testCreationOfClassWith10int()
    {
	    DateTime start = DateTime.Now;
	    for (int i = NB_CREATION_TESTS; i != 0; i--)
	    {
	        ClassWith10int classWith10int = new ClassWith10int();
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith10int(),, snapshot time," + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testCreationOfString
 */
    public static long testCreationOfClassWith1ClassWith10String()
    {
	    DateTime start = DateTime.Now;
	    for (int i = NB_CREATION_TESTS; i != 0; i--)
	    {
	        ClassWith1ClassWith10String classWith1ClassWith10String = new ClassWith1ClassWith10String();
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith1ClassWith10String(),, snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }


    /**
     * testCreationOfString
     */
    public static long testCreationOfClassWith10ClassWith10String()
    {
	    DateTime start = DateTime.Now;
	    for (int i = NB_CREATION_TESTS; i != 0; i--)
	    {
	        ClassWith10ClassWith10String classWith10ClassWith10Strings = new ClassWith10ClassWith10String();
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith10ClassWith10String(),, snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
	
	
	
    public static void main(string[] args)
    {
	    int nbTests = (args != null && args.Length >= 1) ? int.Parse(args[0]) : NB_TESTS;
	    int nbOfExclusionMinMax = (args != null && args.Length >= 2) ? int.Parse(args[1]) : NB_OF_EXCLUSION_MIN_MAX;

	    List<long> executionTimes = new List<long>(nbTests);


	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CreationTest.testCreationOfClassWith1String());
        executionTimes.Sort();
        Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith1String(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
        
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CreationTest.testCreationOfClassWith10String());
        executionTimes.Sort();
        Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith10String(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

        
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CreationTest.testCreationOfClassWith1int());
        executionTimes.Sort();
        Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith1int(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
        

       for (int i = nbTests; i != 0; i--)
           executionTimes.Add(CreationTest.testCreationOfClassWith10int());
       executionTimes.Sort();
       Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith10int(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
       executionTimes.Clear();

      
      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(CreationTest.testCreationOfClassWith1ClassWith10String());
      executionTimes.Sort();
      Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith1ClassWith10String(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)); 
        executionTimes.Clear();


      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(CreationTest.testCreationOfClassWith10ClassWith10String());
      executionTimes.Sort();
      Console.WriteLine("[CreationTest], Creation of " + NB_CREATION_TESTS + " new ClassWith10ClassWith10String((),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
      executionTimes.Clear();

   }

 
}


/**
 * ClassWith1String.
 * 
 * @author Alexandre
 * 
 */
sealed class ClassWith1String
{
    private string string1 = "string1";

    public string String1
    {
        get { return string1; }
        set { string1 = value; }
    }
}




sealed class ClassWith10String
{
    private string string1 = "string1";

    private string string2 = "string2";

    private string string3 = "string3";

    private string string4 = "string4";

    private string string5 = "string5";

    private string string6 = "string6";

    private string string7 = "string7";

    private string string8 = "string8";

    private string string9 = "string9";

    private string string10 = "string10";



    public string String1
    {
        get { return string1; }
        set { string1 = value; }
    }

    public string String2
    {
        get { return string2; }
        set { string2 = value; }
    }

    public string String3
    {
        get { return string3; }
        set { string3 = value; }
    }

    public string String4
    {
        get { return string4; }
        set { string4 = value; }
    }

    public string String5
    {
        get { return string5; }
        set { string5 = value; }
    }

    public string String6
    {
        get { return string6; }
        set { string6 = value; }
    }

    public string String7
    {
        get { return string7; }
        set { string7 = value; }
    }

    public string String8
    {
        get { return string8; }
        set { string8 = value; }
    }

    public string String9
    {
        get { return string9; }
        set { string9 = value; }
    }

    public string String10
    {
        get { return string10; }
        set { string10 = value; }
    }
}


/**
 * ClassWith1int.
 *
 * @author Alexandre
 *
 */
 sealed class ClassWith1int
{
    private int int1;

     public int Int1
     {
         get { return int1; }
         set { int1 = value; }
     }
}

/**
 * ClassWith10int.
 *
 * @author Alexandre
 *
 */
 sealed class ClassWith10int
{
    private int int1 = 1;

    private int int2 = -1;

    private int int3 = 3;

    private int int4 = -3;

    private int int5 = 5;

    private int int6 = -5;

    private int int7 = 7;

    private int int8 = -7;

    private int int9 = 9;

    private int int10 = -9;

    public int Int1
    {
        get { return int1; }
        set { int1 = value; }
    }

    public int Int2
    {
        get { return int2; }
        set { int2 = value; }
    }

    public int Int3
    {
        get { return int3; }
        set { int3 = value; }
    }

    public int Int4
    {
        get { return int4; }
        set { int4 = value; }
    }

    public int Int5
    {
        get { return int5; }
        set { int5 = value; }
    }

    public int Int6
    {
        get { return int6; }
        set { int6 = value; }
    }

    public int Int7
    {
        get { return int7; }
        set { int7 = value; }
    }

    public int Int8
    {
        get { return int8; }
        set { int8 = value; }
    }

    public int Int9
    {
        get { return int9; }
        set { int9 = value; }
    }

    public int Int10
    {
        get { return int10; }
        set { int10 = value; }
    }
}

/**
 * ClassWith1ClassWith10String.
 *
 * @author Alexandre
 *
 */
sealed class ClassWith1ClassWith10String
{
    private ClassWith10String classWith10String1 = new ClassWith10String();

    public ClassWith10String ClassWith10String1
    {
        get { return classWith10String1; }
        set { classWith10String1 = value; }
    }
}

/**
 * ClassWith10ClassWith10String.
 *
 * @author Alexandre
 *
 */
sealed class ClassWith10ClassWith10String
{
    private ClassWith10String classWith10String1 = new ClassWith10String();

    private ClassWith10String classWith10String2 = new ClassWith10String();

    private ClassWith10String classWith10String3 = new ClassWith10String();

    private ClassWith10String classWith10String4 = new ClassWith10String();

    private ClassWith10String classWith10String5 = new ClassWith10String();

    private ClassWith10String classWith10String6 = new ClassWith10String();

    private ClassWith10String classWith10String7 = new ClassWith10String();

    private ClassWith10String classWith10String8 = new ClassWith10String();

    private ClassWith10String classWith10String9 = new ClassWith10String();

    private ClassWith10String classWith10String10 = new ClassWith10String();

    public ClassWith10String ClassWith10String1
    {
        get { return classWith10String1; }
        set { classWith10String1 = value; }
    }

    public ClassWith10String ClassWith10String2
    {
        get { return classWith10String2; }
        set { classWith10String2 = value; }
    }

    public ClassWith10String ClassWith10String3
    {
        get { return classWith10String3; }
        set { classWith10String3 = value; }
    }

    public ClassWith10String ClassWith10String4
    {
        get { return classWith10String4; }
        set { classWith10String4 = value; }
    }

    public ClassWith10String ClassWith10String5
    {
        get { return classWith10String5; }
        set { classWith10String5 = value; }
    }

    public ClassWith10String ClassWith10String6
    {
        get { return classWith10String6; }
        set { classWith10String6 = value; }
    }

    public ClassWith10String ClassWith10String7
    {
        get { return classWith10String7; }
        set { classWith10String7 = value; }
    }

    public ClassWith10String ClassWith10String8
    {
        get { return classWith10String8; }
        set { classWith10String8 = value; }
    }

    public ClassWith10String ClassWith10String9
    {
        get { return classWith10String9; }
        set { classWith10String9 = value; }
    }

    public ClassWith10String ClassWith10String10
    {
        get { return classWith10String10; }
        set { classWith10String10 = value; }
    }
}