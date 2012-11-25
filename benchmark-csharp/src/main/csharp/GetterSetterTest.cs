using System;
using System.Collections.Generic;
using System.Text;


sealed class GetterSetterTest
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
     * NB_SETTER_GETTER_INT_TESTS
     */
    private static int NB_SETTER_GETTER_INT_TESTS = 500 * 1000 * 1000;

    /**
     * NB_SETTER_GETTER_STRING_TESTS
     */
    private static int NB_SETTER_GETTER_STRING_TESTS = 200 * 1000 * 1000;


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
 * testCreationOfString
 */
    public static long testGetterClassWith1int()
    {
        ClassWith1intGetterSetter classWith1int = new ClassWith1intGetterSetter();
	    int count = 0;
        DateTime start = DateTime.Now;
	    for (int i = NB_SETTER_GETTER_INT_TESTS; i-- != 0;)
	    {
            // Write to force cpp not to optimize the code, never executed
            if (i % 2 == 2)
            {
                classWith1int.Int1 = i;
            }

	        count += classWith1int.Int1;
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[GetterSetterTest], Getter of ClassWith1int() test=" + NB_SETTER_GETTER_INT_TESTS + ", count=" + count
            + " classWith1int.Int1=" + classWith1int.Int1 + " , snapshot time," + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }

    /**
     * testCreationOfString
     */
    public static long testSetterClassWith1int()
    {
        ClassWith1intGetterSetter classWith1int = new ClassWith1intGetterSetter();
	    DateTime start = DateTime.Now;
        for (int i = NB_SETTER_GETTER_INT_TESTS; i-- != 0; )
	    {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith1int.Int1 = i;
            }
            else
            {
                classWith1int.Int1 = i - 1;
            }
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[GetterSetterTest], Setter of ClassWith1int() test=" + NB_SETTER_GETTER_INT_TESTS + ","
            + " classWith1int.Int1=" + classWith1int.Int1 + " , snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long) executionTime.TotalMilliseconds;
    }

    /**
 * testCreationOfString
 */
    public static long testSetterGetterClassWith1int()
    {
        ClassWith1intGetterSetter classWith1int = new ClassWith1intGetterSetter();
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = NB_SETTER_GETTER_INT_TESTS; i-- != 0; )
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith1int.Int1 = i;
            }
            else
            {
                classWith1int.Int1 = i - 1;
            }
            count += classWith1int.Int1;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[GetterSetterTest], Setter Getter of ClassWith1int() test=" + NB_SETTER_GETTER_INT_TESTS + ","
            + " classWith1int.Int1=" + classWith1int.Int1 + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
     * testCreationOfString
     */
    public static long testGetterClassWith10int()
    {
        ClassWith10intGetterSetter classWith10int = new ClassWith10intGetterSetter();
	    int count = 0;
	    DateTime start = DateTime.Now;
        for (int i = NB_SETTER_GETTER_INT_TESTS; i-- != 0; )
	    {
            // Write to force cpp not to optimize the code, never executed
            bool testAlwaysFalse = (i % 2 == 2);
            if (testAlwaysFalse)
            {
                classWith10int.Int1 = i;
            }
            count += classWith10int.Int1;

            if (testAlwaysFalse)
            {
                classWith10int.Int2 = i;
            }
            count += classWith10int.Int2;

            if (testAlwaysFalse)
            {
                classWith10int.Int3 = i;
            }
            count += classWith10int.Int3;

            if (testAlwaysFalse)
            {
                classWith10int.Int4 = i;
            }
            count += classWith10int.Int4;

            if (testAlwaysFalse)
            {
                classWith10int.Int5 = i;
            }
            count += classWith10int.Int5;

            if (testAlwaysFalse)
            {
                classWith10int.Int6 = i;
            }
            count += classWith10int.Int6;

            if (testAlwaysFalse)
            {
                classWith10int.Int7 = i;
            }
            count += classWith10int.Int7;

            if (testAlwaysFalse)
            {
                classWith10int.Int8 = i;
            }
            count += classWith10int.Int8;

            if (testAlwaysFalse)
            {
                classWith10int.Int9 = i;
            }
            count += classWith10int.Int9;

            if (testAlwaysFalse)
            {
                classWith10int.Int10 = i;
            }
            count += classWith10int.Int10;
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[GetterSetterTest], Getter of ClassWith10int() test=" + NB_SETTER_GETTER_INT_TESTS + ", count=" + count
            + " classWith10int.Int1=" + classWith10int.Int1 + ", snapshot time," + executionTime.TotalMilliseconds);
        return (long) executionTime.TotalMilliseconds;
    }

    /**
     * testCreationOfString
     */
    public static long testSetterClassWith10int()
    {
        ClassWith10intGetterSetter classWith10int = new ClassWith10intGetterSetter();
	    DateTime start = DateTime.Now;
        for (int i = NB_SETTER_GETTER_INT_TESTS; i-- != 0; )
	    {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith10int.Int1 = i;
                classWith10int.Int2 = i;
                classWith10int.Int3 = i;
                classWith10int.Int4 = i;
                classWith10int.Int5 = i;
                classWith10int.Int6 = i;
                classWith10int.Int7 = i;
                classWith10int.Int8 = i;
                classWith10int.Int9 = i;
                classWith10int.Int10 = i;
            }
            else
            {
                int j = i - 1;
                classWith10int.Int1 = j;
                classWith10int.Int2 = j;
                classWith10int.Int3 = j;
                classWith10int.Int4 = j;
                classWith10int.Int5 = j;
                classWith10int.Int6 = j;
                classWith10int.Int7 = j;
                classWith10int.Int8 = j;
                classWith10int.Int9 = j;
                classWith10int.Int10 = j;
            }
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[GetterSetterTest], Setter of ClassWith10int() test=" + NB_SETTER_GETTER_INT_TESTS 
            + ", classWith10int.Int1=" + classWith10int.Int1 + " , snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testCreationOfString
 */
    public static long testSetterGetterClassWith10int()
    {
        ClassWith10intGetterSetter classWith10int = new ClassWith10intGetterSetter();
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = NB_SETTER_GETTER_INT_TESTS; i-- != 0; )
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith10int.Int1 = i;
                classWith10int.Int2 = i;
                classWith10int.Int3 = i;
                classWith10int.Int4 = i;
                classWith10int.Int5 = i;
                classWith10int.Int6 = i;
                classWith10int.Int7 = i;
                classWith10int.Int8 = i;
                classWith10int.Int9 = i;
                classWith10int.Int10 = i;
            }
            else
            {
                int j = i - 1;
                classWith10int.Int1 = j;
                classWith10int.Int2 = j;
                classWith10int.Int3 = j;
                classWith10int.Int4 = j;
                classWith10int.Int5 = j;
                classWith10int.Int6 = j;
                classWith10int.Int7 = j;
                classWith10int.Int8 = j;
                classWith10int.Int9 = j;
                classWith10int.Int10 = j;
            }
            count += classWith10int.Int1 + classWith10int.Int2 + classWith10int.Int3 + classWith10int.Int4
                    + classWith10int.Int5 + classWith10int.Int6 + classWith10int.Int7 + classWith10int.Int8
                    + classWith10int.Int9 + classWith10int.Int10;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[GetterSetterTest], Setter Getter of ClassWith10int() test=" + NB_SETTER_GETTER_INT_TESTS 
            + ", classWith10int.Int1=" + classWith10int.Int1 + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
 * testCreationOfString
 */
    public static long testGetterClassWith1String()
    {
        ClassWith1StringGetterSetter classWith1String = new ClassWith1StringGetterSetter();
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = NB_SETTER_GETTER_STRING_TESTS; i-- != 0; )
        {
            // Write to force cpp not to optimize the code, never executed
            if (i % 2 == 2)
            {
                classWith1String.String1 = "s1";
            }
            count += classWith1String.String1.Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[GetterSetterTest], Getter of ClassWith1String() test=" + NB_SETTER_GETTER_STRING_TESTS + ", count=" + count
            + " classWith1String.String1=" + classWith1String.String1 + ", snapshot time," + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
     * testCreationOfString
     */
    public static long testSetterClassWith1String()
    {
        ClassWith1StringGetterSetter classWith1String = new ClassWith1StringGetterSetter();
        string string1 = "string1";
        string string2 = "string2";
        DateTime start = DateTime.Now;
        for (int i = NB_SETTER_GETTER_STRING_TESTS; i-- != 0; )
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith1String.String1 = string1;
            }
            else
            {
                classWith1String.String1 = string2;
            }
            
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[GetterSetterTest], Setter of ClassWith1String() test=" + NB_SETTER_GETTER_STRING_TESTS
            + ", classWith1String.String1=" + classWith1String.String1 + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testCreationOfString
 */
    public static long testSetterGetterClassWith1String()
    {
        ClassWith1StringGetterSetter classWith1String = new ClassWith1StringGetterSetter();
        int count = 0;
        string string1 = "string1";
        string string2 = "string2";
        DateTime start = DateTime.Now;
        for (int i = NB_SETTER_GETTER_STRING_TESTS; i-- != 0; )
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith1String.String1 = string1;
            }
            else
            {
                classWith1String.String1 = string2;
            }
            count += classWith1String.String1.Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[GetterSetterTest], Setter Getter of ClassWith1String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",count=" + count
            + " classWith1String.String1=" + classWith1String.String1 + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
     * testCreationOfString
     */
    public static long testGetterClassWith10String()
    {
        ClassWith10StringGetterSetter classWith10String = new ClassWith10StringGetterSetter();
	    int count = 0;
	    DateTime start = DateTime.Now;
        for (int i = NB_SETTER_GETTER_STRING_TESTS; i-- != 0; )
	    {
            // Write to force cpp not to optimize the code, never executed
            bool testAlwaysFalse = (i % 2 == 2);
            if (testAlwaysFalse)
            {
                classWith10String.String1 = "s1";
            }
            count += classWith10String.String1.Length;

            if (testAlwaysFalse)
            {
                classWith10String.String2 = "s2";
            }
            count += classWith10String.String2.Length;

            if (testAlwaysFalse)
            {
                classWith10String.String3 = "s3";
            }
            count += classWith10String.String3.Length;

            if (testAlwaysFalse)
            {
                classWith10String.String4 = "s4";
            }
            count += classWith10String.String4.Length;

            if (testAlwaysFalse)
            {
                classWith10String.String5 = "s5";
            }
            count += classWith10String.String5.Length;

            if (testAlwaysFalse)
            {
                classWith10String.String6 = "s6";
            }
            count += classWith10String.String6.Length;

            if (testAlwaysFalse)
            {
                classWith10String.String7 = "s7";
            }
            count += classWith10String.String7.Length;

            if (testAlwaysFalse)
            {
                classWith10String.String8 = "s8";
            }
            count += classWith10String.String8.Length;

            if (testAlwaysFalse)
            {
                classWith10String.String9 = "s9";
            }
            count += classWith10String.String9.Length;

            if (testAlwaysFalse)
            {
                classWith10String.String10 = "s10";
            }
            count += classWith10String.String10.Length;

	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[GetterSetterTest], Getter of ClassWith10String() test=" + NB_SETTER_GETTER_STRING_TESTS + ", count=" + count
            + " classWith10String.String1=" + classWith10String.String1 + " , snapshot time," + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
     * testCreationOfString
     */
    public static long testSetterClassWith10String()
    {
        ClassWith10StringGetterSetter classWith10String = new ClassWith10StringGetterSetter();
        string string1 = "string1";
        string string2 = "string2";
        string string3 = "string3";
        string string4 = "string4";
        string string5 = "string5";
        string string6 = "string6";
        string string7 = "string7";
        string string8 = "string8";
        string string9 = "string9";
        string string10 = "string10";
	   	DateTime start = DateTime.Now;
        for (int i = NB_SETTER_GETTER_STRING_TESTS; i-- != 0; )
	    {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith10String.String1 = string1;
                classWith10String.String2 = string2;
                classWith10String.String3 = string3;
                classWith10String.String4 = string4;
                classWith10String.String5 = string5;
                classWith10String.String6 = string6;
                classWith10String.String7 = string7;
                classWith10String.String8 = string8;
                classWith10String.String9 = string9;
                classWith10String.String10 = string10;
            }
            else
            {
                classWith10String.String1 = string2;
                classWith10String.String2 = string3;
                classWith10String.String3 = string4;
                classWith10String.String4 = string5;
                classWith10String.String5 = string6;
                classWith10String.String6 = string7;
                classWith10String.String7 = string8;
                classWith10String.String8 = string9;
                classWith10String.String9 = string10;
                classWith10String.String10 = string1;
            }
	    }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[GetterSetterTest], Setter of ClassWith10String() test=" + NB_SETTER_GETTER_STRING_TESTS
            + ", classWith10String.String1=" + classWith10String.String1 + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testCreationOfString
 */
    public static long testSetterGetterClassWith10String()
    {
        ClassWith10StringGetterSetter classWith10String = new ClassWith10StringGetterSetter();
        int count = 0;
        string string1 = "string1";
        string string2 = "string2";
        string string3 = "string3";
        string string4 = "string4";
        string string5 = "string5";
        string string6 = "string6";
        string string7 = "string7";
        string string8 = "string8";
        string string9 = "string9";
        string string10 = "string10";
        DateTime start = DateTime.Now;
        for (int i = NB_SETTER_GETTER_STRING_TESTS; i-- != 0; )
        {
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
                classWith10String.String1 = string1;
                classWith10String.String2 = string2;
                classWith10String.String3 = string3;
                classWith10String.String4 = string4;
                classWith10String.String5 = string5;
                classWith10String.String6 = string6;
                classWith10String.String7 = string7;
                classWith10String.String8 = string8;
                classWith10String.String9 = string9;
                classWith10String.String10 = string10;
            }
            else
            {
                
                classWith10String.String1 = string2;
                classWith10String.String2 = string3;
                classWith10String.String3 = string4;
                classWith10String.String4 = string5;
                classWith10String.String5 = string6;
                classWith10String.String6 = string7;
                classWith10String.String7 = string8;
                classWith10String.String8 = string9;
                classWith10String.String9 = string10;
                classWith10String.String10 = string1;

            }
            count += classWith10String.String1.Length - classWith10String.String2.Length + classWith10String.String3.Length
    - classWith10String.String4.Length + classWith10String.String5.Length - classWith10String.String6.Length
    + classWith10String.String7.Length - classWith10String.String8.Length + classWith10String.String9.Length
    - classWith10String.String10.Length;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[GetterSetterTest], Setter Getter of ClassWith10String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",count=" + count
            + "  classWith10String.String1=" + classWith10String.String1 + ", snapshot time," + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

	
	    public static void main(string[] args)
    {
	    int nbTests = (args != null && args.Length >= 1) ? int.Parse(args[0]) : NB_TESTS;
	    int nbOfExclusionMinMax = (args != null && args.Length >= 2) ? int.Parse(args[1]) : NB_OF_EXCLUSION_MIN_MAX;

	    List<long> executionTimes = new List<long>(nbTests);

       
      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(GetterSetterTest.testGetterClassWith1int());
      executionTimes.Sort();
      Console.WriteLine("[GetterSetterTest], Getter of ClassWith1int() test=" + NB_SETTER_GETTER_INT_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
      executionTimes.Clear();

      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(GetterSetterTest.testSetterClassWith1int());
      executionTimes.Sort();
      Console.WriteLine("[GetterSetterTest], Setter of ClassWith1int() test=" + NB_SETTER_GETTER_INT_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
      executionTimes.Clear();

      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(GetterSetterTest.testSetterGetterClassWith1int());
      executionTimes.Sort();
      Console.WriteLine("[GetterSetterTest], Setter Getter of ClassWith1int() test=" + NB_SETTER_GETTER_INT_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
      executionTimes.Clear();
      
      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(GetterSetterTest.testGetterClassWith10int());
      executionTimes.Sort();
      Console.WriteLine("[GetterSetterTest], Getter of ClassWith10int() test=" + NB_SETTER_GETTER_INT_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
      executionTimes.Clear();
      
      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(GetterSetterTest.testSetterClassWith10int());
      executionTimes.Sort();
      Console.WriteLine("[GetterSetterTest], Setter of ClassWith10int() test=" + NB_SETTER_GETTER_INT_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
      executionTimes.Clear();
      
      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(GetterSetterTest.testSetterGetterClassWith10int());
      executionTimes.Sort();
      Console.WriteLine("[GetterSetterTest], Setter Getter of ClassWith10int() test=" + NB_SETTER_GETTER_INT_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
      executionTimes.Clear();

      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(GetterSetterTest.testGetterClassWith1String());
      executionTimes.Sort();
      Console.WriteLine("[GetterSetterTest], Getter of ClassWith1String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
      executionTimes.Clear();

      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(GetterSetterTest.testSetterClassWith1String());
      executionTimes.Sort();
      Console.WriteLine("[GetterSetterTest], Setter of ClassWith1String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
      executionTimes.Clear();

      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(GetterSetterTest.testSetterGetterClassWith1String());
      executionTimes.Sort();
      Console.WriteLine("[GetterSetterTest], Setter Getter of ClassWith1String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
      executionTimes.Clear();
      

      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(GetterSetterTest.testGetterClassWith10String());
      executionTimes.Sort();
      Console.WriteLine("[GetterSetterTest], Getter of ClassWith10String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
      executionTimes.Clear();
      
      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(GetterSetterTest.testSetterClassWith10String());
      executionTimes.Sort();
      Console.WriteLine("[GetterSetterTest], Setter of ClassWith10String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
      executionTimes.Clear();
      
      for (int i = nbTests; i != 0; i--)
          executionTimes.Add(GetterSetterTest.testSetterGetterClassWith10String());
      executionTimes.Sort();
      Console.WriteLine("[GetterSetterTest], Setter Getter of ClassWith10String() test=" + NB_SETTER_GETTER_STRING_TESTS + ",, average time,"
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
sealed class ClassWith1StringGetterSetter
{
    private string string1 = "string1";

    public string String1
    {
        get { return string1; }
        set { string1 = value; }
    }
}




sealed class ClassWith10StringGetterSetter
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

    public string getString1()
    {
        return this.string1;
    }

    public void setString1(string aString1)
    {
        this.string1 = aString1;
    }

    public string getString2()
    {
        return this.string2;
    }

    public void setString2(string aString2)
    {
        this.string2 = aString2;
    }

    public string getString3()
    {
        return this.string3;
    }

    public void setString3(string aString3)
    {
        this.string3 = aString3;
    }

    public string getString4()
    {
        return this.string4;
    }

    public void setString4(string aString4)
    {
        this.string4 = aString4;
    }

    public string getString5()
    {
        return this.string5;
    }

    public void setString5(string aString5)
    {
        this.string5 = aString5;
    }

    public string getString6()
    {
        return this.string6;
    }

    public void setString6(string aString6)
    {
        this.string6 = aString6;
    }

    public string getString7()
    {
        return this.string7;
    }

    public void setString7(string aString7)
    {
        this.string7 = aString7;
    }

    public string getString8()
    {
        return this.string8;
    }

    public void setString8(string aString8)
    {
        this.string6 = aString8;
    }

    public string getString9()
    {
        return this.string9;
    }

    public void setString9(string aString9)
    {
        this.string9 = aString9;
    }

    public string getString10()
    {
        return this.string10;
    }

    public void setString10(string aString10)
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
sealed class ClassWith1intGetterSetter
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
sealed class ClassWith10intGetterSetter
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
sealed class ClassWith1ClassWith10StringGetterSetter
{
    private ClassWith10StringGetterSetter classWith10String1 = new ClassWith10StringGetterSetter();

    public ClassWith10StringGetterSetter ClassWith10String1
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
sealed class ClassWith10ClassWith10StringGetterSetter
{
    private ClassWith10StringGetterSetter classWith10String1 = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String2 = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String3 = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String4 = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String5 = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String6 = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String7 = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String8 = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String9 = new ClassWith10StringGetterSetter();

    private ClassWith10StringGetterSetter classWith10String10 = new ClassWith10StringGetterSetter();

    public ClassWith10StringGetterSetter ClassWith10String1
    {
        get { return classWith10String1; }
        set { classWith10String1 = value; }
    }

    public ClassWith10StringGetterSetter ClassWith10String2
    {
        get { return classWith10String2; }
        set { classWith10String2 = value; }
    }

    public ClassWith10StringGetterSetter ClassWith10String3
    {
        get { return classWith10String3; }
        set { classWith10String3 = value; }
    }

    public ClassWith10StringGetterSetter ClassWith10String4
    {
        get { return classWith10String4; }
        set { classWith10String4 = value; }
    }

    public ClassWith10StringGetterSetter ClassWith10String5
    {
        get { return classWith10String5; }
        set { classWith10String5 = value; }
    }

    public ClassWith10StringGetterSetter ClassWith10String6
    {
        get { return classWith10String6; }
        set { classWith10String6 = value; }
    }

    public ClassWith10StringGetterSetter ClassWith10String7
    {
        get { return classWith10String7; }
        set { classWith10String7 = value; }
    }

    public ClassWith10StringGetterSetter ClassWith10String8
    {
        get { return classWith10String8; }
        set { classWith10String8 = value; }
    }

    public ClassWith10StringGetterSetter ClassWith10String9
    {
        get { return classWith10String9; }
        set { classWith10String9 = value; }
    }

    public ClassWith10StringGetterSetter ClassWith10String10
    {
        get { return classWith10String10; }
        set { classWith10String10 = value; }
    }

}