using System;
using System.Collections.Generic;
using System.Text;
using System.Collections;


sealed class CollectionTest
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
     * SIZE
     */
    private static int SIZE = 1 * 1000;

    /**
     * TESTS_LIST
     */
    private static int TESTS_LIST = 1000 * 1000;

    /**
     * TESTS_MAP
     */
    private static int TESTS_MAP = 100 * 1000;

        /**
     * TESTS_READ_MAP
     */
    private static int TESTS_READ_MAP = 100 * 1000;


    /**
 * NB_COPY_DATA_TESTS
 */
    private static int NB_COPY_DATA_TESTS = 1000 * 1000;

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
     * testCreationOfStringArray.
     *
     * @return
     */
    public static long testCreationOfClassWith1intArray()
    {
	    DateTime start = DateTime.Now;
	    for (int i = TESTS_LIST; i != 0; i--)
	    {
            ClassWith1intb[] list = new ClassWith1intb[SIZE];
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " ClassWith1intArray[" + SIZE + "] ClassWith1intb[] list = new ClassWith1intb[SIZE]  ,, snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }

    /**
 * testCreationOfStringArray.
 *
 * @return
 */
    public static long testAddElementsToClassWith1intArray()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        ClassWith1intb[] list = new ClassWith1intb[SIZE];
        DateTime start = DateTime.Now;
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int j = SIZE; j-- != 0; )
                list[j] = classWith1int;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], " + TESTS_LIST + " Tests of Add " + SIZE + " Elements to ClassWith1intArray[" + SIZE + "]  for (int j = SIZE   j != 0   j--) list[j] = classWith1int  ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
* testCreationOfStringArray.
*
* @return
*/
    public static long testCreationAndAddElementsToClassWith1intArray()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        DateTime start = DateTime.Now;
        for (int i = TESTS_LIST; i != 0; i--)
        {
            ClassWith1intb[] list = new ClassWith1intb[SIZE];
            for (int j = SIZE; j-- != 0; )
                list[j] = classWith1int;
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " and Add " + SIZE + " Elements to ClassWith1intArray[" + SIZE + "]  for (int j = SIZE   j != 0   j--)  ClassWith1intb[] list = new ClassWith1intb[SIZE]  list[j] = classWith1int  ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }
    

    
    
    /**
     * testIterationOfStringArrayWithForGeneric.
     *
     * @return
     */
    public static long testIterationOfClassWith1intArrayWithForGeneric()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        ClassWith1intb[] list = new ClassWith1intb[SIZE];
	    for (int j = SIZE; j != 0; j--)
	    {
            list[j - 1] = classWith1int;
	    }
	    int count = 0;
	    DateTime start = DateTime.Now;
	    for (int i = TESTS_LIST; i != 0; i--)
	    {
            foreach (ClassWith1intb s in list)
            {
                // Write to force cpp not to optimize the code, never executed
                if (i % 2 == 2)
                {
                    list[i].Int1 = i;
                }

                count += s.Int1;
            }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1intArray[" + SIZE + "] With For Generic   for (final ClassWith1int s : list) count += s.Int1  , count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
    
    
    /**
     * testIterationOfStringArrayWithForwardLoop.
     *
     * @return
     */
    public static long testIterationOfClassWith1intArrayWithForwardLoop()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        ClassWith1intb[] list = new ClassWith1intb[SIZE];
	    for (int j = SIZE; j != 0; j--)
	    {
            list[j - 1] = classWith1int;
	    }
	    int count = 0;
	    DateTime start = DateTime.Now;
	    for (int i = TESTS_LIST; i != 0; i--)
	    {
            for (int k = 0; k < list.Length; k++)
            {
                // Write to force cpp not to optimize the code, never executed
                if (k % 2 == 2)
                {
                    list[k].Int1 = k;
                }

                count += list[k].Int1;
            }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1intArray[" + SIZE + "] With Forward Loop   for (int k = 0   k < list.length   k++)  count += list[k].Int1, count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }

    
    /**
     * testIterationOfStringArrayWithTryCatch.
     *
     * @return
     */
    public static long testIterationOfClassWith1intArrayWithTryCatch()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        ClassWith1intb[] list = new ClassWith1intb[SIZE];
	    for (int j = SIZE; j != 0; j--)
	    {
            list[j - 1] = classWith1int;
	    }
	    int count = 0;
	    DateTime start = DateTime.Now;
	    for (int i = TESTS_LIST; i != 0; i--)
	    {
	        try
	        {
                for (int k = 0; ; k++)
                {
                    // Write to force cpp not to optimize the code, never executed
                    if (k % 2 == 2)
                    {
                        list[k].Int1 = k;
                    }
                    count += list[k].Int1;
                }
	        }
	        catch (Exception e)
	        {
	    	    //
	        }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1intArray[" + SIZE + "] With Try Catch try{for (int k = 0     k++) count += list[k].Length   } catch (ArrayIndexOutOfBoundsException x){}, count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
    
    
    /**
     * testIterationOfStringArrayWithReverseLoopDifferentZero.
     *
     * @return
     */
    public static long testIterationOfClassWith1intArrayWithReverseLoopDifferentZero()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        ClassWith1intb[] list = new ClassWith1intb[SIZE];
	    for (int j = SIZE; j != 0; j--)
	    {
            list[j - 1] = classWith1int;
	    }
	    int count = 0;
	    DateTime start = DateTime.Now;
	    for (int i = TESTS_LIST; i != 0; i--)
	    {
            for (int k = list.Length; k != 0; k--)
            {
                // Write to force cpp not to optimize the code, never executed
                if (k % 2 == 2)
                {
                    list[k].Int1 = k;
                }
                count += list[k - 1].Int1;
            }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1intArray[" + SIZE + "] With Reverse Loop Different Zero  for (int k = list.length   k != 0   k--) count = count += list[k - 1].Int1  ,, snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
    
    
    /**
     * testIterationOfStringArrayWithReverseLoopDifferentZeroDecrementInTest.
     *
     * @return
     */
    public static long testIterationOfClassWith1intArrayWithReverseLoopDifferentZeroDecrementInTest()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        ClassWith1intb[] list = new ClassWith1intb[SIZE];
	    for (int j = SIZE; j != 0; j--)
	    {
            list[j - 1] = classWith1int;
	    }
	    int count = 0;
	    DateTime start = DateTime.Now;
	    for (int i = TESTS_LIST; i != 0; i--)
	    {
            for (int k = list.Length; k-- != 0; )
            {
                // Write to force cpp not to optimize the code, never executed
                if (k % 2 == 2)
                {
                    list[k].Int1 = k;
                }
                count += list[k].Int1;
            }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1intArray[" + SIZE + "] With Reverse Loop Different Zero Decrement In Test  for (int k = list.length   k-- != 0  ) count += list[k].Int1  , count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }

/**
* testCopyData.
*
* @return
*/
    public static long testCopyData()
    {
        ClassWith1intb[] src = new ClassWith1intb[SIZE];
        ClassWith1intb[] dest = new ClassWith1intb[SIZE];
        for (int i = SIZE; i-- != 0; )
        {
            ClassWith1intb classWith1int = new ClassWith1intb();
            classWith1int.Int1 = RandomizerCollection.getRandomValue(100000);
            src[i] = classWith1int;
        }
        DateTime start = DateTime.Now;
        for (int i = NB_COPY_DATA_TESTS; i != 0; i--)
        {
            for (int j = src.Length; j-- != 0; )
            {
                dest[j] = src[j];
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Copy data from an array to an array " + SIZE + ",, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
* testCopyData.
*
* @return
*/
    public static long testCopyDataOptimized()
    {
        ClassWith1intb[] src = new ClassWith1intb[SIZE];
        ClassWith1intb[] dest = new ClassWith1intb[SIZE];
        for (int i = SIZE; i-- != 0; )
        {
            ClassWith1intb classWith1int = new ClassWith1intb();
            classWith1int.Int1 = RandomizerCollection.getRandomValue(100000);
            src[i] = classWith1int;
        }
        DateTime start = DateTime.Now;
        for (int i = NB_COPY_DATA_TESTS; i != 0; i--)
        {
            src.CopyTo(dest, 0);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Copy data Optimized from an array to an array " + SIZE + ",, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    
    /**
     * testCreationOfStringList.
     *
     * @return
     */
    public static long testCreationOfClassWith1intList()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
	    DateTime start = DateTime.Now;
	    for (int i = TESTS_LIST; i != 0; i--)
	    {
            List<ClassWith1intb> list = new List<ClassWith1intb>(SIZE);
	        for (int j = SIZE; j != 0; j--)
                list.Add(classWith1int);
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " List<ClassWith1int>(" + SIZE + ") final List<ClassWith1int> list = new ArrayList<ClassWith1int>(SIZE)     for (int j = SIZE   j != 0   j--) list.add(classWith1int)  ,, snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }



    /**
 * testCreationOfStringList.
 *
 * @return
 */
    public static long testAddElementsToClassWith1intList()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        List<ClassWith1intb> list = new List<ClassWith1intb>(SIZE);
        DateTime start = DateTime.Now;
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int j = SIZE; j != 0; j--)
                list.Add(classWith1int);
            list.Clear();
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], " + TESTS_LIST + " Tests of Add " + SIZE + " Elements to List<ClassWith1int>[" + SIZE
                + "]  for (int j = SIZE   j-- != 0  ) list.Add(classWith1int)  ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
* testCreationOfStringList.
*
* @return
*/
    public static long testCreationAndAddElementsToClassWith1intList()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        DateTime start = DateTime.Now;
        for (int i = TESTS_LIST; i != 0; i--)
        {
            List<ClassWith1intb> list = new List<ClassWith1intb>(SIZE);
            for (int j = SIZE; j != 0; j--)
                list.Add(classWith1int);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " and Add " + SIZE + " Elements to List<ClassWith1intb>[" + SIZE
                + "]  for (int j = SIZE   j-- != 0  ) List<ClassWith1intb> list = new List<ClassWith1intb>(SIZE)  list.Add(classWith1int)  ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    
    
    
    /**
     * testIterationOfStringListWithIterator.
     *
     * @return
     */
    public static long testIterationOfClassWith1intListWithIterator()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        List<ClassWith1intb> list = new List<ClassWith1intb>(SIZE);
	    for (int j = SIZE; j != 0; j--)
	    {
            list.Add(classWith1int);
	    }
	    int count = 0;
	    DateTime start = DateTime.Now;
	    for (int i = TESTS_LIST; i != 0; i--)
	    {
            for (IEnumerator enumerator = list.GetEnumerator(); enumerator.MoveNext(); )
            {
                ClassWith1intb classWith1intb = (ClassWith1intb) enumerator.Current;

                // Write to force cpp not to optimize the code, never executed
                /*if (i % 2 == 2)
                {
                    classWith1intb.Int1 = i;
                }*/

                count += classWith1intb.Int1;
            }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " List<ClassWith1intb>(" + SIZE + ") With Iterator  for (IEnumerator enumerator = list.GetEnumerator()  enumerator.MoveNext()  ) count += ((ClassWith1intb)enumerator.Current).Int1  , count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
    
    
    /**
     * testIterationOfStringListWithForGeneric.
     *
     * @return
     */
    public static long testIterationOfClassWith1intListWithForGeneric()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        List<ClassWith1intb> list = new List<ClassWith1intb>(SIZE);
	    for (int j = SIZE; j != 0; j--)
	    {
            list.Add(classWith1int);
	    }
	    int count = 0;
	    DateTime start = DateTime.Now;
	    for (int i = TESTS_LIST; i != 0; i--)
	    {
            foreach (ClassWith1intb s in list)
            {
                // Write to force cpp not to optimize the code, never executed
                /*if (i % 2 == 2)
                {
                    s.Int1 = i;
                }*/
                count += s.Int1;
            }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " List<ClassWith1int>(" + SIZE + ") With For Generic foreach (ClassWith1int s in list) count += s.Int1 , count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
    
    

    
    
    /**
     * testIterationOfStringListWithIterator.
     *
     * @return
     */
    public static long testIterationOfClassWith1intListWithForwardLoop()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        List<ClassWith1intb> list = new List<ClassWith1intb>(SIZE);
	    for (int j = SIZE; j != 0; j--)
	    {
            list.Add(classWith1int);
	    }
	    int count = 0;
	    DateTime start = DateTime.Now;
	    for (int i = TESTS_LIST; i != 0; i--)
	    {
            for (int k = 0; k < list.Count; k++)
            {
                // Write to force cpp not to optimize the code, never executed
                /*if (k % 2 == 2)
                {
                    list[k].Int1 = k;
                }*/
                count += list[k].Int1;
            }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " List<ClassWith1intb>(" + SIZE + ") With Forward Loop  for (int k = 0; k < list.Count   k++) count += list[k].Int1 , count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
    
    /**
     * testIterationOfStringListWithTryCatch.
     *
     * @return
     */
    public static long testIterationOfClassWith1intListWithTryCatch()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        List<ClassWith1intb> list = new List<ClassWith1intb>(SIZE);
	    for (int j = SIZE; j-- != 0; )
	    {
            list.Add(classWith1int);
	    }
	    int count = 0;
        DateTime start = DateTime.Now;
	    for (int i = TESTS_LIST; i != 0; i--)
	    {
	        try
	        {
                for (int k = 0; ; k++)
                {
                    // Write to force cpp not to optimize the code, never executed
                    if (k % 2 == 2)
                    {
                        list[k].Int1 = k;
                    }
                    count += list[k].Int1;
                }
	        }
	        catch (Exception e)
	        {
		    //
	        }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " List<ClassWith1intb>(" + SIZE + ") With Try Catch  try{ for (int k = 0     k++) count += list.get(k).Int1  } catch (ExceptionException x){}, count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
    
    /**
     * testIterationOfStringListWithReverseLoopDifferentZero.
     *
     * @return
     */
    public static long testIterationOfClassWith1intListWithReverseLoopDifferentZero()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        List<ClassWith1intb> list = new List<ClassWith1intb>(SIZE);
	    for (int j = SIZE; j != 0; j--)
	    {
            list.Add(classWith1int);
	    }
	    int count = 0;
        DateTime start = DateTime.Now;
	    for (int i = TESTS_LIST; i != 0; i--)
	    {
            for (int k = list.Count; k != 0; k--)
            {
                // Write to force cpp not to optimize the code, never executed
                if (k % 2 == 2)
                {
                    list[k].Int1 = k;
                }
                count += list[k - 1].Int1;
            }
	    }

	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " List<ClassWith1int>(" + SIZE + ") With Reverse Loop Different Zero   for (int k = list.Count   k != 0   k--) count += list[k - 1].Int1  , count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }
    
    /**
     * testIterationOfStringListWithReverseLoopDifferentZeroDecrementInTest.
     *
     * @return
     */
    public static long testIterationOfClassWith1intListWithReverseLoopDifferentZeroDecrementInTest()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        List<ClassWith1intb> list = new List<ClassWith1intb>(SIZE);
	    for (int j = SIZE; j != 0; j--)
	    {
            list.Add(classWith1int);
	    }
	    int count = 0;
        DateTime start = DateTime.Now;
	    for (int i = TESTS_LIST; i != 0; i--)
	    {
            for (int k = list.Count; k-- != 0; )
            {
                if (k % 2 == 2)
                {
                    list[k].Int1 = k;
                }
                count += list[k].Int1;
            }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " List<ClassWith1intb>(" + SIZE + ") With Reverse Loop Different Zero Decrement in Test  for (int k = list.Count   k-- != 0  ) count += list[k].Int1  , count=" + count + ", snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }




/**
* testCreationOfStringList.
*
* @return
*/
    public static long testCreationOfClassWith1intArrayList()
    {
        DateTime start = DateTime.Now;
        for (int i = TESTS_LIST; i != 0; i--)
        {
            IList list = new ArrayList(SIZE);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " ArrayList(" + SIZE + ") IList list = new ArrayList(SIZE) ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

/**
* testCreationOfStringList.
*
* @return
*/
    public static long testAddElementsToClassWith1intArrayList()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        IList list = new ArrayList(SIZE);
        DateTime start = DateTime.Now;
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int j = SIZE; j != 0; j--)
                list.Add(classWith1int);
            list.Clear();
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;

        Console.WriteLine("[CollectionTest], " + TESTS_LIST + " Tests of Add " + SIZE + " Elements to ArrayList[" + SIZE + "    for (int j = SIZE   j != 0   j--) list.Add(classWith1int) list.Clear() ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

/**
* testCreationOfStringList.
*
* @return
*/
    public static long testCreationAndAddElementsToClassWith1intArrayList()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        DateTime start = DateTime.Now;
        for (int i = TESTS_LIST; i != 0; i--)
        {
            IList list = new ArrayList(SIZE);
            for (int j = SIZE; j != 0; j--)
                list.Add(classWith1int);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " and Add " + SIZE + " Elements to ArrayList[" + SIZE + ") IList list = new ArrayList(SIZE)     for (int j = SIZE   j != 0   j--) list.Add(classWith1int)  ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }




    /**
     * testIterationOfStringListWithIterator.
     *
     * @return
     */
    public static long testIterationOfClassWith1intArrayListWithIterator()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        IList list = new ArrayList(SIZE);
        for (int j = SIZE; j != 0; j--)
        {
            list.Add(classWith1int);
        }
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (IEnumerator enumerator = list.GetEnumerator(); enumerator.MoveNext(); )
            {
                ClassWith1intb classWith1intb = (ClassWith1intb)enumerator.Current;

                // Write to force cpp not to optimize the code, never executed
                if (i % 2 == 2)
                {
                    classWith1intb.Int1 = i;
                }
                count += classWith1intb.Int1;
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList(" + SIZE + ") With Iterator  for (IEnumerator enumerator = list.GetEnumerator()  enumerator.MoveNext()   )   iterator.hasNext()  ) count += ((ClassWith1intb)enumerator.Current).Int1  , count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
     * testIterationOfStringListWithForGeneric.
     *
     * @return
     */
    public static long testIterationOfClassWith1intArrayListWithForGeneric()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        IList list = new ArrayList(SIZE);
        for (int j = SIZE; j != 0; j--)
        {
            list.Add(classWith1int);
        }
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = TESTS_LIST; i != 0; i--)
        {
            foreach (ClassWith1intb s in list)
            {
                // Write to force cpp not to optimize the code, never executed
                if (i % 2 == 2)
                {
                    s.Int1 = i;
                }
                count += s.Int1;
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList(" + SIZE + ") With For Generic foreach (ClassWith1intb s in list) count += s.Int1  , count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }





    /**
     * testIterationOfStringListWithIterator.
     *
     * @return
     */
    public static long testIterationOfClassWith1intArrayListWithForwardLoop()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        IList list = new ArrayList(SIZE);
        for (int j = SIZE; j != 0; j--)
        {
            list.Add(classWith1int);
        }
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = 0; k < list.Count; k++)
            {
                // Write to force cpp not to optimize the code, never executed
                if (k % 2 == 2)
                {
                    ((ClassWith1intb)list[k]).Int1 = k;
                }
                count += ((ClassWith1intb)list[k]).Int1;
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList(" + SIZE + ") With Forward Loop  for (int k = 0   k < list.Count   k++) count += list.get(k).Length  , count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
     * testIterationOfStringListWithTryCatch.
     *
     * @return
     */
    public static long testIterationOfClassWith1intArrayListWithTryCatch()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        IList list = new ArrayList(SIZE);
        for (int j = SIZE; j-- != 0; )
        {
            list.Add(classWith1int);
        }
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = TESTS_LIST; i != 0; i--)
        {
            try
            {
                for (int k = 0; ; k++)
                {
                    // Write to force cpp not to optimize the code, never executed
                    if (k % 2 == 2)
                    {
                        ((ClassWith1intb) list[k]).Int1 = k;
                    }
                    count += ((ClassWith1intb)list[k]).Int1;
                }
            }
            catch (Exception e)
            {
                //
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList(" + SIZE + ") With Try Catch  try{ for (int k = 0     k++) count += ((ClassWith1intb)list[k]).Length } catch (Exception x){}, count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
     * testIterationOfStringListWithReverseLoopDifferentZero.
     *
     * @return
     */
    public static long testIterationOfClassWith1intArrayListWithReverseLoopDifferentZero()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        IList list = new ArrayList(SIZE);
        for (int j = SIZE; j != 0; j--)
        {
            list.Add(classWith1int);
        }
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = list.Count; k != 0; k--)
            {
                // Write to force cpp not to optimize the code, never executed
                if (k % 2 == 2)
                {
                    ((ClassWith1intb)list[k]).Int1 = k;
                }
                count += ((ClassWith1intb)list[k - 1]).Int1;
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList(" + SIZE + ") With Reverse Loop Different Zero   for (int k = list.size()   k != 0   k--) count += ((ClassWith1intb)list[k - 1]).Int1  , count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
     * testIterationOfStringListWithReverseLoopDifferentZeroDecrementInTest.
     *
     * @return
     */
    public static long testIterationOfClassWith1intArrayListWithReverseLoopDifferentZeroDecrementInTest()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        IList list = new ArrayList(SIZE);
        for (int j = SIZE; j != 0; j--)
        {
            list.Add(classWith1int);
        }
        int count = 0;
        DateTime start = DateTime.Now;
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = list.Count; k-- != 0; )
            {
                // Write to force cpp not to optimize the code, never executed
                if (k % 2 == 2)
                {
                    ((ClassWith1intb)list[k]).Int1 = k;
                }
                count += ((ClassWith1intb)list[k]).Int1;
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList(" + SIZE + ") With Reverse Loop Different Zero Decrement in Test  for (int k = list.Count   k-- != 0  ) count += ((ClassWith1intb)list[k]).Int1  ,count=" + count + ", snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }
    
    


    /**
     * testCreationOfStringMap.
     *
     * @return
     */
    public static long testCreationOfClassWith1intMap()
    {
	    DateTime start = DateTime.Now;
	    for (int i = TESTS_MAP; i-- != 0;)
	    {
            Hashtable map = new Hashtable(SIZE);
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], " + TESTS_MAP + " Tests of Put " + SIZE + " Elements  Hashtable(" + SIZE + ")  Hashtable map = new Hashtable(SIZE)  ,, snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }

/**
 * testAddElementsToStringArray.
 *
 * @return
 */
    public static long testPutElementsInClassWith1intMap()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        Hashtable map = new Hashtable(SIZE);
        DateTime start = DateTime.Now;
        for (int i = TESTS_MAP; i-- != 0; )
        {
            for (int j = SIZE; j != 0; j--)
                map.Add(j, classWith1int);
            map.Clear();
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " Hashtable(" + SIZE + ")  for (int j = SIZE   j != 0   j--) map.Add(j  classWith1int)  map.Clear()  ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

/**
 * testCreationAndAddElementsToStringArray.
 *
 * @return 
 */
    public static long testCreationAndPutElementsInClassWith1intMap()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        DateTime start = DateTime.Now;
        for (int i = TESTS_MAP; i-- != 0; )
        {
            Hashtable map = new Hashtable(SIZE);
            for (int j = SIZE; j != 0; j--)
                map.Add(j, classWith1int);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_MAP + " And Put " + SIZE + " Elements   for (int j = SIZE   j != 0   j--) Hashtable map = new Hashtable(SIZE) map.Add(j   classWith1int)  ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    
    /**
     * testCreationOfStringMap.
     *
     * @return
     */
    public static long testIterationOfClassWith1intMap()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        Hashtable map = new Hashtable(SIZE);
	    for (int j = SIZE; j != 0; j--)
	    {
            map.Add(j, classWith1int);
	    }
        DateTime start = DateTime.Now;
	    for (int i = TESTS_READ_MAP; i-- != 0;)
	    {
	        for (int j = SIZE; j != 0; j--)
	        {
                ClassWith1intb s = (ClassWith1intb) map[j];
	        }
	    }
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_MAP + " Hashtable(" + SIZE + ")  for (int j = SIZE   j != 0   j--) ClassWith1intb s = (ClassWith1intb) map[j]  ,, snapshot time,"
		    + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }


/**
 * testCreationOfStringMap.
 *
 * @return
 */
    public static long testCreationOfClassWith1intDictionary()
    {
        DateTime start = DateTime.Now;
        for (int i = TESTS_MAP; i-- != 0; )
        {
            Dictionary<int, ClassWith1intb> map = new Dictionary<int, ClassWith1intb>(SIZE);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_MAP + " Dictionary<int  ClassWith1int>(" + SIZE + ")   for (int j = SIZE   j != 0   j--) Dictionary map = new Dictionary<int ClassWith1int>(SIZE) ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

/**
 * testPutElementsInStringDictionary.
 *
 * @return
 */
    public static long testPutElementsInClassWith1intDictionary()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        Dictionary<int, ClassWith1intb> map = new Dictionary<int, ClassWith1intb>(SIZE);
        DateTime start = DateTime.Now;
        for (int i = TESTS_MAP; i-- != 0; )
        {
            for (int j = SIZE; j != 0; j--)
                map.Add(j, classWith1int);
            map.Clear();
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], " + TESTS_MAP + " Tests of Put " + SIZE + " Elements Dictionary<int  ClassWith1int>(" + SIZE + ")  for (int j = SIZE   j != 0   j--) map.Add(j  classWith1int)  ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

/**
 * testCreationOfStringMap.
 *
 * @return
 */
    public static long testCreationAndPutElementsInClassWith1intDictionary()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        DateTime start = DateTime.Now;
        for (int i = TESTS_MAP; i-- != 0; )
        {
            Dictionary<int, ClassWith1intb> map = new Dictionary<int, ClassWith1intb>(SIZE);
            for (int j = SIZE; j != 0; j--)
                map.Add(j, classWith1int);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_MAP + " And Put " + SIZE + " Elements  Dictionary<int  ClassWith1int>(" + SIZE + ") Dictionary map = new Dictionary<int ClassWith1int>(SIZE)  for (int j = SIZE   j != 0   j--)  map.Add(j  classWith1int)  ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
     * testCreationOfStringMap.
     *
     * @return
     */
    public static long testIterationOfClassWith1intDictionary()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        Dictionary<int, ClassWith1intb> map = new Dictionary<int, ClassWith1intb>(SIZE);
        for (int j = SIZE; j != 0; j--)
        {
            map.Add(j, classWith1int);
        }
        DateTime start = DateTime.Now;
        for (int i = TESTS_READ_MAP; i-- != 0; )
        {
            for (int j = SIZE; j != 0; j--)
            {
                try
                {
                    ClassWith1intb s = (ClassWith1intb) map[j];
                }
                catch (KeyNotFoundException e)
                {
                }
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_MAP + " Dictionary<int  ClassWith1int>(" + SIZE + ")  for (int j = SIZE  j != 0   j--) try {ClassWith1intb s = (ClassWith1intb) map[j]  } catch (KeyNotFoundException e),, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }



    /**
     * testCreationOfStringMap.
     *
     * @return
     */
    public static long testIterationOfClassWith1intDictionaryWithoutTryCatch()
    {
        ClassWith1intb classWith1int = new ClassWith1intb();
        Dictionary<int, ClassWith1intb> map = new Dictionary<int, ClassWith1intb>(SIZE);
        for (int j = SIZE; j != 0; j--)
        {
            map.Add(j, classWith1int);
        }
        DateTime start = DateTime.Now;
        for (int i = TESTS_READ_MAP; i-- != 0; )
        {
            for (int j = SIZE; j != 0; j--)
            {
                if (map.ContainsKey(j))
                {
                    ClassWith1intb s = (ClassWith1intb) map[j];
                }
            }
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_MAP + " Dictionary<int  ClassWith1intb>(" + SIZE + ")  for (int j = SIZE  j != 0   j--) if (map.ContainsKey(j)) ClassWith1intb s = (ClassWith1intb) map[j] ,, snapshot time,"
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
	        executionTimes.Add(CollectionTest.testCreationOfClassWith1intArray());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " ClassWith1intArray[" + SIZE + "] ClassWith1intb[] list = new ClassWith1intb[SIZE]  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testAddElementsToClassWith1intArray());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], " + TESTS_LIST + " Tests of Add " + SIZE + " Elements to ClassWith1intArray[" + SIZE + "]  for (int j = SIZE   j != 0   j--) list[j] = classWith1int  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        
        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testCreationAndAddElementsToClassWith1intArray());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " and Add " + SIZE + " Elements to ClassWith1intArray[" + SIZE + "]  for (int j = SIZE   j != 0   j--)  ClassWith1intb[] list = new ClassWith1intb[SIZE]  list[j] = classWith1int  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
        
    	
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intArrayWithForGeneric());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1intArray[" + SIZE + "] With For Generic   for (final ClassWith1int s : list) count += s.Int1  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	

	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intArrayWithForwardLoop());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1intArray[" + SIZE + "] With Forward Loop   for (int k = 0   k < list.length   k++)  count += list[k].Int1,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intArrayWithReverseLoopDifferentZero());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1intArray[" + SIZE + "] With Reverse Loop Different Zero  for (int k = list.length; k != 0  k--) count = count + list[k - 1].Int1  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intArrayWithReverseLoopDifferentZeroDecrementInTest());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1intArray[" + SIZE + "] With Reverse Loop Different Zero Decrement In Test  for (int k = list.length   k-- != 0  ) count += list[k].Int1  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
        
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testCopyData());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Copy data from an array to an array " + SIZE + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testCopyDataOptimized());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Copy data Optimized from an array to an array " + SIZE + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testCreationOfClassWith1intList());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " List<ClassWith1int>(" + SIZE + ") final List<ClassWith1int> list = new ArrayList<ClassWith1int>(SIZE)     for (int j = SIZE   j != 0   j--) list.add(classWith1int)  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testAddElementsToClassWith1intList());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], " + TESTS_LIST + " Tests of Add " + SIZE + " Elements to List<ClassWith1int>[" + SIZE
               + "]  for (int j = SIZE   j-- != 0  ) list.Add(classWith1int)  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testCreationAndAddElementsToClassWith1intList());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " and Add " + SIZE + " Elements to List<ClassWith1intb>[" + SIZE
                + "]  for (int j = SIZE   j-- != 0  ) List<ClassWith1intb> list = new List<ClassWith1intb>(SIZE)  list.Add(classWith1int)  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intListWithForGeneric());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " List<ClassWith1int>(" + SIZE + ") With For Generic foreach (ClassWith1int s in list) count += s.Int1 ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intListWithForwardLoop());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " List<ClassWith1intb>(" + SIZE + ") With Forward Loop  for (int k = 0; k < list.Count   k++) count += list[k].Int1 ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intListWithReverseLoopDifferentZero());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " List<ClassWith1int>(" + SIZE + ") With Reverse Loop Different Zero   for (int k = list.Count   k != 0   k--) count += list[k - 1].Int1  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intListWithReverseLoopDifferentZeroDecrementInTest());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " List<ClassWith1intb>(" + SIZE + ") With Reverse Loop Different Zero Decrement in Test  for (int k = list.Count   k-- != 0  ) count += list[k].Int1  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();


        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testCreationOfClassWith1intArrayList());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " ArrayList(" + SIZE + ") IList list = new ArrayList(SIZE) ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testAddElementsToClassWith1intArrayList());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], " + TESTS_LIST + " Tests of Add " + SIZE + " Elements to ArrayList[" + SIZE + "    for (int j = SIZE   j != 0   j--) list.Add(classWith1int) list.Clear() ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testCreationAndAddElementsToClassWith1intArrayList());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " and Add " + SIZE + " Elements to ArrayList[" + SIZE + ") IList list = new ArrayList(SIZE)     for (int j = SIZE   j != 0   j--) list.Add(classWith1int)  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intArrayListWithIterator());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList(" + SIZE + ") With Iterator  for (IEnumerator enumerator = list.GetEnumerator()  enumerator.MoveNext()   )   iterator.hasNext()  ) count += ((ClassWith1intb)enumerator.Current).Int1  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intArrayListWithForGeneric());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList(" + SIZE + ") With For Generic foreach (ClassWith1intb s in list) count += s.Int1  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intArrayListWithForwardLoop());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList(" + SIZE + ") With Forward Loop  for (int k = 0   k < list.Count   k++) count += list.get(k).Length  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intArrayListWithReverseLoopDifferentZero());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList(" + SIZE + ") With Reverse Loop Different Zero   for (int k = list.size()    k != 0   k--) count = count + list.get(k - 1).Length  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intArrayListWithReverseLoopDifferentZeroDecrementInTest());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList(" + SIZE + ") With Reverse Loop Different Zero Decrement in Test  for (int k = list.Count   k-- != 0  ) count += ((ClassWith1intb)list[k]).Int1  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testCreationOfClassWith1intMap());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], " + TESTS_MAP + " Tests of Put " + SIZE + " Elements  Hashtable(" + SIZE + ")  Hashtable map = new Hashtable(SIZE)  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testPutElementsInClassWith1intMap());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_LIST + " Hashtable(" + SIZE + ")  for (int j = SIZE   j != 0   j--) map.Add(j  classWith1int)  map.Clear()  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testCreationAndPutElementsInClassWith1intMap());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_MAP + " And Put " + SIZE + " Elements   for (int j = SIZE   j != 0   j--) Hashtable map = new Hashtable(SIZE) map.Add(j   classWith1int)  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intMap());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_MAP + " Hashtable(" + SIZE + ")  for (int j = SIZE   j != 0   j--) ClassWith1intb s = (ClassWith1intb) map[j]  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testCreationOfClassWith1intDictionary());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_MAP + " Dictionary<int  ClassWith1int>(" + SIZE + ")   for (int j = SIZE   j != 0   j--) Dictionary map = new Dictionary<int ClassWith1int>(SIZE) ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testPutElementsInClassWith1intDictionary());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], " + TESTS_MAP + " Tests of Put " + SIZE + " Elements Dictionary<int  ClassWith1int>(" + SIZE + ")  for (int j = SIZE   j != 0   j--) map.Add(j   classWith1int)  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testCreationAndPutElementsInClassWith1intDictionary());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Creation of " + TESTS_MAP + " And Put " + SIZE + " Elements  Dictionary<int  ClassWith1int>(" + SIZE + ") Dictionary map = new Dictionary<int ClassWith1int>(SIZE)  for (int j = SIZE   j != 0   j--)  map.Add(j   classWith1int)  BLANK)  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intDictionary());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_MAP + " Dictionary<int  ClassWith1int>(" + SIZE + ")  for (int j = SIZE  j != 0   j--) try {ClassWith1intb s = (ClassWith1intb) map[j]  } catch (KeyNotFoundException e),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(CollectionTest.testIterationOfClassWith1intDictionaryWithoutTryCatch());
        executionTimes.Sort();
        Console.WriteLine("[CollectionTest], Iteration of " + TESTS_MAP + " Dictionary<int  ClassWith1intb>(" + SIZE + ")  for (int j = SIZE  j != 0   j--) if (map.ContainsKey(j)) ClassWith1intb s = (ClassWith1intb) map[j] ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
        
    }


}


/**
 * ClassWith1int.
 *
 * @author Alexandre
 *
 */
sealed class ClassWith1intb
{
    private int int1;

    
    /**
     * Constructor.
     *
     */
    public ClassWith1intb()
    {
        this.int1 = 1;
    }

    public int Int1
    {
        get { return int1; }
        set { int1 = value; }
    }
}


/**
 * Randomizer.
 *
 * @author Alexandre
 *
 */
class RandomizerCollection
{
    static long lastRandom = 0;

    /**
     * resetRandomizer.
     *
     */
    public static void resetRandomizer()
    {
        lastRandom = 42;
    }

    /**
     * getRandomValue.
     *
     * @param max
     * @return
     */
    public static int getRandomValue(int max)
    {
        return (int) (max * (lastRandom = (lastRandom * 3877 + 29573) % 139968) / 139968);
    }
}




