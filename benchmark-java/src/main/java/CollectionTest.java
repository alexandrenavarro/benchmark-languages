import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * CollectionTest.
 *
 * @author anavarro122404 - 25 sept. 07
 *
 *
 * <!-- $Id: CollectionTest.java,v 1.8 2007/12/03 17:41:03 anavarro Exp $ -->.
 *
 */
public final class CollectionTest
{
    /**
     * NB_TESTS
     */
    private static final int    NB_TESTS                = 5;

    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    private static final int    NB_OF_EXCLUSION_MIN_MAX = 2;

    /**
     * SIZE
     */
    private static final int    SIZE                    = 1 * 1000;

    /**
     * TESTS_LIST
     */
    private static final int    TESTS_LIST              = 1000 * 1000;

    /**
     * TESTS_MAP
     */
    private static final int    TESTS_MAP               = 100 * 1000;

    /**
     * TESTS_READ_MAP
     */
    private static final int    TESTS_READ_MAP          = 100 * 1000;


    /**
     * NB_COPY_DATA_TESTS
     */
    private static final int    NB_COPY_DATA_TESTS      = 1000 * 1000;


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
     * testCreationOfClassWith1IntArray.
     *
     * @return
     */
    public static long testCreationOfClassWith1IntArray()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            @SuppressWarnings("unused")
            final ClassWith1intb[] list = new ClassWith1intb[SIZE];
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Creation of " + TESTS_LIST + " ClassWith1Int[" + SIZE
                + "] final ClassWith1Int[] list = new ClassWith1Int[SIZE] ,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testAddElementsToClassWith1IntArray.
     *
     * @return
     */
    public static long testAddElementsToClassWith1IntArray()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        final ClassWith1intb[] list = new ClassWith1intb[SIZE];
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int j = SIZE; j-- != 0;)
                list[j] = classWith1Int;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], " + TESTS_LIST + " Tests of Add " + SIZE + " Elements to ClassWith1IntArray[" + SIZE
                + "]  for (int j = SIZE   j-- != 0  ) list[j] = classWith1Int  ,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testAddElementsToClassWith1IntArray.
     *
     * @return
     */
    public static long testCreationAndAddElementsToClassWith1IntArray()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWithInt = new ClassWith1intb();
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            final ClassWith1intb[] list = new ClassWith1intb[SIZE];
            for (int j = SIZE; j-- != 0;)
                list[j] = classWithInt;
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out
                .println("[CollectionTest], Creation of "
                        + TESTS_LIST
                        + " and Add "
                        + SIZE
                        + " Elements to ClassWith1Int["
                        + SIZE
                        + "]  for (int j = SIZE   j-- != 0  ) final ClassWith1Int[] list = new ClassWith1Int[SIZE]  list[j] = classWithInt  ,, snapshot time,"
                        + executionTime);
        return executionTime;
    }

    /**
     * testIterationOfClassWith1IntArrayWithForGeneric.
     *
     * @return
     */
    public static long testIterationOfClassWith1IntArrayWithForGeneric()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        start = System.currentTimeMillis();
        final ClassWith1intb[] list = new ClassWith1intb[SIZE];
        for (int j = SIZE; j-- != 0;)
        {
            list[j] = classWith1Int;
        }
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (final ClassWith1intb s : list)
            {
                // Write to force cpp not to optimize the code, never executed
                if (i % 2 == 2)
                {
                    s.setInt1(i);
                }
                count += s.getInt1();
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1IntArray[" + SIZE
                + "] With For Generic   for (final ClassWith1Int s : list)  count += s.getInt1()  , count=" + count + ", snapshot time,"
                + executionTime);
        return executionTime;
    }

    /**
     * testIterationOfClassWith1IntArrayWithForwardLoop.
     *
     * @return
     */
    public static long testIterationOfClassWith1IntArrayWithForwardLoop()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        start = System.currentTimeMillis();
        final ClassWith1intb[] list = new ClassWith1intb[SIZE];
        for (int j = SIZE; j-- != 0;)
        {
            list[j] = classWith1Int;
        }
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = 0; k < list.length; k++)
            {
                // Write to force cpp not to optimize the code, never executed
                if (k % 2 == 2)
                {
                    list[k].setInt1(k);
                }
                count += list[k].getInt1();
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1intArray[" + SIZE
                + "] With Forward Loop   for (int k = 0   k < list.length   k++) count += list[k].getInt1()  ,count=" + count + ", snapshot time,"
                + executionTime);
        return executionTime;
    }

    /**
     * testIterationOfClassWith1IntArrayWithTryCatch.
     *
     * @return
     */
    public static long testIterationOfClassWith1IntArrayWithTryCatch()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        final ClassWith1intb[] list = new ClassWith1intb[SIZE];
        for (int j = SIZE; j-- != 0;)
        {
            list[j] = classWith1Int;
        }
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            try
            {
                for (int k = 0;; k++)
                {
                    // Write to force cpp not to optimize the code, never executed
                    if (k % 2 == 2)
                    {
                        list[k].setInt1(k);
                    }
                    count += list[k].getInt1();
                }
            }
            catch (ArrayIndexOutOfBoundsException x)
            {
                //
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1IntArray[" + SIZE
                + "] With Try Catch try{for (int k = 0     k++) count += list[k].getInt1()   } catch (ArrayIndexOutOfBoundsException x){},count="
                + count + ", snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testIterationOfClassWith1IntArrayWithReverseLoopDifferentZero.
     *
     * @return
     */
    public static long testIterationOfClassWith1IntArrayWithReverseLoopDifferentZero()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        final ClassWith1intb[] list = new ClassWith1intb[SIZE];
        for (int j = SIZE; j-- != 0;)
        {
            list[j] = classWith1Int;
        }
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = list.length; k != 0; k--)
            {
                // Write to force cpp not to optimize the code, never executed
                if (k % 2 == 2)
                {
                    list[k].setInt1(k);
                }
                count += list[k - 1].getInt1();
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1intArray[" + SIZE
                + "] With Reverse Loop Different Zero  for (int k = list.length   k != 0   k--) count = list[k - 1].getInt1()  ,count=" + count
                + ", snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testIterationOfClassWith1IntArrayWithReverseLoopDifferentZeroDecrementInTest.
     *
     * @return
     */
    public static long testIterationOfClassWith1IntArrayWithReverseLoopDifferentZeroDecrementInTest()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        final ClassWith1intb[] list = new ClassWith1intb[SIZE];
        for (int j = SIZE; j-- != 0;)
        {
            list[j] = classWith1Int;
        }
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = list.length; k-- != 0;)
            {
                // Write to force cpp not to optimize the code, never executed
                if (k % 2 == 2)
                {
                    list[k].setInt1(k);
                }
                count += list[k].getInt1();
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1IntArray[" + SIZE
                + "] With Reverse Loop Different Zero Decrement In Test  for (int k = list.length   k-- != 0  ) count += list[k].getInt1()  ,count="
                + count + ", snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCopyData.
     *
     * @return
     */
    public static long testCopyData()
    {
        final ClassWith1intb[] src = new ClassWith1intb[SIZE];
        final ClassWith1intb[] dest = new ClassWith1intb[SIZE];
        for (int i = SIZE; i-- != 0;)
        {
            final ClassWith1intb classWith1Int = new ClassWith1intb();
            classWith1Int.setInt1(Randomizer.getRandomValue(100000));
            src[i] = classWith1Int;
        }
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_COPY_DATA_TESTS; i != 0; i--)
        {
            for (int j = src.length; j-- != 0;)
            {
                dest[j] = src[j];
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Copy data from an array to an array " + SIZE + ",, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCopyDataOptimized.
     *
     * @return
     */
    public static long testCopyDataOptimized()
    {
        final ClassWith1intb[] src = new ClassWith1intb[SIZE];
        final ClassWith1intb[] dest = new ClassWith1intb[SIZE];
        for (int i = SIZE; i-- != 0;)
        {
            final ClassWith1intb classWith1Int = new ClassWith1intb();
            classWith1Int.setInt1(Randomizer.getRandomValue(100000));
            src[i] = classWith1Int;
        }
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_COPY_DATA_TESTS; i != 0; i--)
        {
            System.arraycopy(src, 0, dest, 0, src.length);
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Copy Optimized data from an array to an array " + SIZE + ",, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCreationOfClassWith1IntList.
     *
     * @return
     */
    public static long testCreationOfClassWith1IntList()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            @SuppressWarnings("unused")
            final List<ClassWith1intb> list = new ArrayList<ClassWith1intb>(SIZE);
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Creation of " + TESTS_LIST + " ArrayList<ClassWith1Int>(" + SIZE
                + ") final List<ClassWith1Int> list = new ArrayList<ClassWith1Int>(SIZE)   ,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testAddElementsToClassWith1IntList.
     *
     * @return
     */
    public static long testAddElementsToClassWith1IntList()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        final List<ClassWith1intb> list = new ArrayList<ClassWith1intb>(SIZE);
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int j = SIZE; j != 0; j--)
            {
                list.add(classWith1Int);
            }
            list.clear();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], " + TESTS_LIST + " Tests Add " + SIZE + " Elements to ArrayList<ClassWith1Int>(" + SIZE
                + ")     for (int j = SIZE   j != 0   j--) list.add(classWith1Int)  ,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCreationAndAddElementsToClassWith1IntList.
     *
     * @return
     */
    public static long testCreationAndAddElementsToClassWith1IntList()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            final List<ClassWith1intb> list = new ArrayList<ClassWith1intb>(SIZE);
            for (int j = SIZE; j != 0; j--)
            {
                list.add(classWith1Int);
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out
                .println("[CollectionTest], Creation of "
                        + TESTS_LIST
                        + " and Add "
                        + SIZE
                        + " Elements to ArrayList<ClassWith1Int>("
                        + SIZE
                        + ")  final List<ClassWith1Int> list = new ArrayList<ClassWith1Int>(SIZE)   for (int j = SIZE   j != 0   j--) list.add(classWith1Int)  ,, snapshot time,"
                        + executionTime);
        return executionTime;
    }

    /**
     * testIterationOfClassWith1IntListWithIterator.
     *
     * @return
     */
    public static long testIterationOfClassWith1IntListWithIterator()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        final List<ClassWith1intb> list = new ArrayList<ClassWith1intb>(SIZE);
        for (int j = SIZE; j != 0; j--)
        {
            list.add(classWith1Int);
        }
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (final Iterator<ClassWith1intb> iterator = list.iterator(); iterator.hasNext();)
            {
                final ClassWith1intb classWith1intb = iterator.next();
                // Write to force cpp not to optimize the code, never executed
                if (i % 2 == 2)
                {
                    classWith1intb.setInt1(i);
                }
                count += classWith1intb.getInt1();
            }
        }

        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out
                .println("[CollectionTest], Iteration of "
                        + TESTS_LIST
                        + " ArrayList<ClassWith1Int>("
                        + SIZE
                        + ") With Iterator  for (final Iterator<ClassWith1Int> iterator = list.iterator()   iterator.hasNext()  ) count += iterator.next().getInt1()  , count="
                        + count + ", snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testIterationOfClassWith1IntListWithForGeneric.
     *
     * @return
     */
    public static long testIterationOfClassWith1IntListWithForGeneric()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        final List<ClassWith1intb> list = new ArrayList<ClassWith1intb>(SIZE);
        for (int j = SIZE; j != 0; j--)
        {
            list.add(classWith1Int);
        }
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (final ClassWith1intb s : list)
            {
                // Write to force cpp not to optimize the code, never executed
                if (i % 2 == 2)
                {
                    s.setInt1(i);
                }
                count += s.getInt1();
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList<ClassWith1Int>(" + SIZE
                + ") With For Generic for (final ClassWith1Int s : list) count +=  s.getInt1()  , count=" + count + ", snapshot time,"
                + executionTime);
        return executionTime;
    }

    /**
     * testIterationOfClassWith1IntListWithForwardLoop.
     *
     * @return
     */
    public static long testIterationOfClassWith1IntListWithForwardLoop()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1intCollection = new ClassWith1intb();
        final List<ClassWith1intb> list = new ArrayList<ClassWith1intb>(SIZE);
        for (int j = SIZE; j != 0; j--)
        {
            list.add(classWith1intCollection);
        }
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = 0; k < list.size(); k++)
            {
                // Write to force cpp not to optimize the code, never executed
                if (k % 2 == 2)
                {
                    list.get(k).setInt1(k);
                }
                count += list.get(k).getInt1();
            }
        }

        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList<ClassWith1Int>(" + SIZE
                + ") With Forward Loop  for (int k = 0   k < list.size()   k++) count += list.get(k).length()  , count=" + count + ", snapshot time,"
                + executionTime);
        return executionTime;
    }

    /**
     * testIterationOfClassWith1IntListWithTryCatch.
     *
     * @return
     */
    public static long testIterationOfClassWith1IntListWithTryCatch()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1intCollection = new ClassWith1intb();
        final List<ClassWith1intb> list = new ArrayList<ClassWith1intb>(SIZE);
        for (int j = SIZE; j-- != 0;)
        {
            list.add(classWith1intCollection);
        }
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            try
            {
                for (int k = 0;; k++)
                {
                    // Write to force cpp not to optimize the code, never executed
                    if (k % 2 == 2)
                    {
                        list.get(k).setInt1(k);
                    }
                    count += list.get(k).getInt1();
                }
            }
            catch (IndexOutOfBoundsException x)
            {
                //
            }
        }

        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList<ClassWith1Int>(" + SIZE
                + ") With Try Catch  try{ for (int k = 0     k++) count += list.get(k).getInt1()  } catch (IndexOutOfBoundsException x){},count="
                + count + ", snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testIterationOfClassWith1IntListWithReverseLoopDifferentZero.
     *
     * @return
     */
    public static long testIterationOfClassWith1IntListWithReverseLoopDifferentZero()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        final List<ClassWith1intb> list = new ArrayList<ClassWith1intb>(SIZE);
        for (int j = SIZE; j != 0; j--)
        {
            list.add(classWith1Int);
        }
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = list.size(); k != 0; k--)
            {
                // Write to force cpp not to optimize the code, never executed
                if (k % 2 == 2)
                {
                    list.get(k).setInt1(k);
                }
                count += list.get(k - 1).getInt1();
            }
        }

        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList<ClassWith1Int>(" + SIZE
                + ") With Reverse Loop Different Zero   for (int k = list.size()   k != 0   k--) count += list.get(k - 1).getInt1()  ,count=" + count
                + ", snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testIterationOfClassWith1IntListWithReverseLoopDifferentZeroDecrementInTest.
     *
     * @return
     */
    public static long testIterationOfClassWith1IntListWithReverseLoopDifferentZeroDecrementInTest()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        final List<ClassWith1intb> list = new ArrayList<ClassWith1intb>(SIZE);
        for (int j = SIZE; j != 0; j--)
        {
            list.add(classWith1Int);
        }
        int count = 0;
        start = System.currentTimeMillis();
        for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = list.size(); k-- != 0;)
            {
                // Write to force cpp not to optimize the code, never executed
                if (k % 2 == 2)
                {
                    list.get(k).setInt1(k);
                }
                count += list.get(k).getInt1();
            }
        }

        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out
                .println("[CollectionTest], Iteration of "
                        + TESTS_LIST
                        + " ArrayList<ClassWith1Int>("
                        + SIZE
                        + ") With Reverse Loop Different Zero Decrement in Test  for (int k = list.size()   k-- != 0  )  count += list.get(k).getInt1()  , count="
                        + count + ", snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCreationOfClassWith1IntHashMap.
     *
     * @return
     */
    public static long testCreationOfClassWith1IntHashMap()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = TESTS_MAP; i-- != 0;)
        {
            @SuppressWarnings("unused")
            final Map<Integer, ClassWith1intb> map = new HashMap<Integer, ClassWith1intb>(SIZE);
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Creation of " + TESTS_MAP + " HashMap<Integer  ClassWith1Int>(" + SIZE
                + ") final Map<Integer  ClassWith1Int> map = new HashMap<Integer  ClassWith1Int>(SIZE)  ,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testPutElementsInClassWith1IntHashMap.
     *
     * @return
     */
    public static long testPutElementsInClassWith1IntHashMap()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        final Map<Integer, ClassWith1intb> map = new HashMap<Integer, ClassWith1intb>(SIZE);
        start = System.currentTimeMillis();
        for (int i = TESTS_MAP; i-- != 0;)
        {
            for (int j = SIZE; j != 0; j--)
                map.put(i, classWith1Int);
            map.clear();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], " + TESTS_MAP + " Tests of Put " + SIZE + " Elements " + " HashMap<Integer  ClassWith1Int>(" + SIZE
                + ")  for (int j = SIZE   j != 0  j--) map.put(j   classWith1Int)  ,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCreationAndPutElementsInClassWith1IntHashMap.
     *
     * @return
     */
    public static long testCreationAndPutElementsInClassWith1IntHashMap()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        start = System.currentTimeMillis();
        for (int i = TESTS_MAP; i-- != 0;)
        {
            final Map<Integer, ClassWith1intb> map = new HashMap<Integer, ClassWith1intb>(SIZE);
            for (int j = SIZE; j != 0; j--)
            {
                map.put(i, classWith1Int);
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out
                .println("[CollectionTest], Creation of "
                        + TESTS_MAP
                        + " And Put "
                        + SIZE
                        + " Elements "
                        + " HashMap<Integer  ClassWith1Int>("
                        + SIZE
                        + ") final Map<Integer  ClassWith1Int> map = new HashMap<Integer  ClassWith1Int>(SIZE)  for (int j = SIZE   j != 0  j--) map.put(j   classWith1Int)  ,, snapshot time,"
                        + executionTime);
        return executionTime;
    }

    /**
     * testIterationOfClassWith1IntHashMap.
     *
     * @return
     */
    public static long testIterationOfClassWith1IntHashMap()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        final Map<Integer, ClassWith1intb> map = new HashMap<Integer, ClassWith1intb>();
        for (int j = SIZE; j != 0; j--)
        {
            map.put(j, classWith1Int);
        }
        start = System.currentTimeMillis();
        for (int i = TESTS_READ_MAP; i-- != 0;)
        {
            for (int j = SIZE; j != 0; j--)
            {
                @SuppressWarnings("unused")
                final ClassWith1intb s = map.get(j);
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Iteration of " + TESTS_MAP + " HashMap<Integer  ClassWith1Int>(" + SIZE
                + ")  for (int j = SIZE  j != 0  j--) final ClassWith1Int s = map.get(j)  ,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCreationOfClassWith1IntTreeMap.
     *
     * @return
     */
    public static long testCreationOfClassWith1IntTreeMap()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = TESTS_MAP; i-- != 0;)
        {
            @SuppressWarnings("unused")
            final Map<Integer, ClassWith1intb> map = new TreeMap<Integer, ClassWith1intb>();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Creation of " + TESTS_MAP + " TreeMap<Integer  ClassWith1Int>(" + SIZE
                + ") final Map<Integer  ClassWith1Int> map = new TreeMap<Integer  ClassWith1Int>(SIZE)  ,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testPutElementsInClassWith1IntTreeMap.
     *
     * @return
     */
    public static long testPutElementsInClassWith1IntTreeMap()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        final Map<Integer, ClassWith1intb> map = new TreeMap<Integer, ClassWith1intb>();
        start = System.currentTimeMillis();
        for (int i = TESTS_MAP; i-- != 0;)
        {
            for (int j = SIZE; j != 0; j--)
                map.put(i, classWith1Int);
            map.clear();
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], " + TESTS_MAP + " Tests of Put " + SIZE + " Elements " + " TreeMap<Integer  ClassWith1Int>(" + SIZE
                           + ")  for (int j = SIZE   j != 0  j--) map.put(j   classWith1Int)  ,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCreationAndPutElementsInClassWith1IntTreeMap.
     *
     * @return
     */
    public static long testCreationAndPutElementsInClassWith1IntTreeMap()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        start = System.currentTimeMillis();
        for (int i = TESTS_MAP; i-- != 0;)
        {
            final Map<Integer, ClassWith1intb> map = new TreeMap<Integer, ClassWith1intb>();
            for (int j = SIZE; j != 0; j--)
                map.put(i, classWith1Int);
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out
                .println("[CollectionTest], Creation of "
                        + TESTS_MAP
                        + " And Put "
                        + SIZE
                        + " Elements "
                        + " TreeMap<Integer  ClassWith1Int>("
                        + SIZE
                        + ") final Map<Integer  ClassWith1Int> map = new TreeMap<Integer  ClassWith1Int>(SIZE)  for (int j = SIZE   j != 0  j--) map.put(j   classWith1Int)  ,, snapshot time,"
                        + executionTime);
        return executionTime;
    }

    /**
     * testIterationOfClassWith1IntTreeMap.
     *
     * @return
     */
    public static long testIterationOfClassWith1IntTreeMap()
    {
        long start = System.currentTimeMillis();
        final ClassWith1intb classWith1Int = new ClassWith1intb();
        final Map<Integer, ClassWith1intb> map = new TreeMap<Integer, ClassWith1intb>();
        for (int j = SIZE; j != 0; j--)
        {
            map.put(j, classWith1Int);
        }
        start = System.currentTimeMillis();
        for (int i = TESTS_READ_MAP; i-- != 0;)
        {
            for (int j = SIZE; j != 0; j--)
            {
                @SuppressWarnings("unused")
                final ClassWith1intb s = map.get(j);
            }
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[CollectionTest], Iteration of " + TESTS_MAP + " TreeMap<Integer  ClassWith1Int>(" + SIZE
                + ")  for (int j = SIZE  j != 0  j--) final ClassWith1Int s = map.get(j)  ,, snapshot time," + executionTime);
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
            executionTimes.add(CollectionTest.testCreationOfClassWith1IntArray());
        System.out.println("[CollectionTest], Creation of " + TESTS_LIST + " ClassWith1Int[" + SIZE
                + "] final ClassWith1Int[] list = new ClassWith1Int[SIZE] ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testAddElementsToClassWith1IntArray());
        System.out.println("[CollectionTest], " + TESTS_LIST + " Tests of Add " + SIZE + " Elements to ClassWith1IntArray[" + SIZE
                + "]  for (int j = SIZE   j-- != 0  ) list[j] = classWith1Int  ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testCreationAndAddElementsToClassWith1IntArray());
        System.out.println("[CollectionTest], Creation of " + TESTS_LIST + " and Add " + SIZE + " Elements to ClassWith1Int[" + SIZE
                + "]  for (int j = SIZE   j-- != 0  ) final ClassWith1Int[] list = new ClassWith1Int[SIZE]  list[j] = classWithInt  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntArrayWithForGeneric());
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1IntArray[" + SIZE
                           + "] With For Generic   for (final ClassWith1Int s : list)  count += s.getInt1()  ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntArrayWithForwardLoop());
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1intArray[" + SIZE
                           + "] With Forward Loop   for (int k = 0   k < list.length   k++) count += list[k].getInt1()  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntArrayWithTryCatch());
        System.out
                .println("[CollectionTest], Iteration of "
                        + TESTS_LIST
                        + " ClassWith1IntArray["
                        + SIZE
                        + "] With Try Catch try{for (int k = 0     k++) count += list[k].getInt1()   } catch (ArrayIndexOutOfBoundsException x){},, average time,"
                        + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time,"
                        + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntArrayWithReverseLoopDifferentZero());
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ClassWith1intArray[" + SIZE
                + "] With Reverse Loop Different Zero  for (int k = list.length   k != 0   k--) count += list[k - 1].getInt1(),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntArrayWithReverseLoopDifferentZeroDecrementInTest());
        System.out
                .println("[CollectionTest], Iteration of "
                        + TESTS_LIST
                        + " ClassWith1IntArray["
                        + SIZE
                        + "] With Reverse Loop Different Zero Decrement In Test  for (int k = list.length   k-- != 0  ) count += list[k].getInt1() ,, average time,"
                        + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time,"
                        + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntArrayWithReverseLoopDifferentZeroDecrementInTest());
        System.out
                .println("[CollectionTest], Iteration of "
                        + TESTS_LIST
                        + " ClassWith1IntArray["
                        + SIZE
                        + "] With Reverse Loop Different Zero Decrement In Test  for (int k = list.length   k-- != 0  ) count += list[k].getInt1() ,, average time,"
                        + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time,"
                        + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testCopyData());
        System.out.println("[CollectionTest], Copy data from an array to an array " + SIZE + ",, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testCopyDataOptimized());
        System.out.println("[CollectionTest], Copy Optimized data from an array to an array " + SIZE + ",, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testCreationOfClassWith1IntList());
        System.out.println("[CollectionTest], Creation of " + TESTS_LIST + " ArrayList<ClassWith1Int>(" + SIZE
                           + ") final List<ClassWith1Int> list = new ArrayList<ClassWith1Int>(SIZE)   ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testAddElementsToClassWith1IntList());
        System.out.println("[CollectionTest], " + TESTS_LIST + " Tests Add " + SIZE + " Elements to ArrayList<ClassWith1Int>(" + SIZE
                           + ")     for (int j = SIZE   j != 0   j--) list.add(classWith1Int)  ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testCreationAndAddElementsToClassWith1IntList());
        System.out.println("[CollectionTest], Creation of "
                           + TESTS_LIST
                           + " and Add "
                           + SIZE
                           + " Elements to ArrayList<ClassWith1Int>("
                           + SIZE
                           + ")  final List<ClassWith1Int> list = new ArrayList<ClassWith1Int>(SIZE)   for (int j = SIZE   j != 0   j--) list.add(classWith1Int)  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntListWithIterator());
        System.out
                .println("[CollectionTest], Iteration of "
                         + TESTS_LIST
                         + " ArrayList<ClassWith1Int>("
                         + SIZE
                         + ") With Iterator  for (final Iterator<ClassWith1Int> iterator = list.iterator()   iterator.hasNext()  ) count += iterator.next().getInt1()  ,, average time,"
                        + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time,"
                        + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntListWithForGeneric());
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList<ClassWith1Int>(" + SIZE
                           + ") With For Generic for (final ClassWith1Int s : list) count +=  s.getInt1()  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntListWithForwardLoop());
        System.out.println("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList<ClassWith1Int>(" + SIZE
                           + ") With Forward Loop  for (int k = 0   k < list.size()   k++) count += list.get(k).length()  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntListWithTryCatch());
        System.out
                .println("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList<ClassWith1Int>(" + SIZE
                         + ") With Try Catch  try{ for (int k = 0     k++) count += list.get(k).getInt1()  } catch (IndexOutOfBoundsException x){},, average time,"
                        + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time,"
                        + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntListWithReverseLoopDifferentZero());
        System.out
                .println("[CollectionTest], Iteration of " + TESTS_LIST + " ArrayList<ClassWith1Int>(" + SIZE
                         + ") With Reverse Loop Different Zero   for (int k = list.size()   k != 0   k--) count += list.get(k - 1).getInt1()  ,, average time,"
                        + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time,"
                        + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntListWithReverseLoopDifferentZeroDecrementInTest());
        System.out
                .println("[CollectionTest], Iteration of "
                         + TESTS_LIST
                         + " ArrayList<ClassWith1Int>("
                         + SIZE
                         + ") With Reverse Loop Different Zero Decrement in Test  for (int k = list.size()   k-- != 0  )  count += list.get(k).getInt1()  ,, average time,"
                        + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time,"
                        + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testCreationOfClassWith1IntHashMap());
        System.out
                .println("[CollectionTest], Creation of " + TESTS_MAP + " HashMap<Integer  ClassWith1Int>(" + SIZE
                         + ") final Map<Integer  ClassWith1Int> map = new HashMap<Integer  ClassWith1Int>(SIZE)  ,, average time,"
                        + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time,"
                        + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testPutElementsInClassWith1IntHashMap());
        System.out.println("[CollectionTest], " + TESTS_MAP + " Tests of Put " + SIZE + " Elements " + " HashMap<Integer  ClassWith1Int>(" + SIZE
                           + ")  for (int j = SIZE   j != 0  j--) map.put(j   classWith1Int)  ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testCreationAndPutElementsInClassWith1IntHashMap());
        System.out
                .println("[CollectionTest], Creation of "
                         + TESTS_MAP
                         + " And Put "
                         + SIZE
                         + " Elements "
                         + " HashMap<Integer  ClassWith1Int>("
                         + SIZE
                         + ") final Map<Integer  ClassWith1Int> map = new HashMap<Integer  ClassWith1Int>(SIZE)  for (int j = SIZE   j != 0  j--) map.put(j   classWith1Int)  ,, average time,"
                        + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time,"
                        + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntHashMap());
        System.out.println("[CollectionTest], Iteration of " + TESTS_MAP + " HashMap<Integer  ClassWith1Int>(" + SIZE
                + ")  for (int j = SIZE   j-- != 0  ) final ClassWith1Int s = map.get(j)  ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testCreationOfClassWith1IntTreeMap());
        System.out
                .println("[CollectionTest], Creation of " + TESTS_MAP + " TreeMap<Integer  ClassWith1Int>(" + SIZE
                         + ") final Map<Integer  ClassWith1Int> map = new TreeMap<Integer  ClassWith1Int>(SIZE)  ,, average time,"
                        + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time,"
                        + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testPutElementsInClassWith1IntTreeMap());
        System.out.println("[CollectionTest], " + TESTS_MAP + " Tests of Put " + SIZE + " Elements " + " TreeMap<Integer  ClassWith1Int>(" + SIZE
                           + ")  for (int j = SIZE   j != 0  j--) map.put(j   classWith1Int)  ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testCreationAndPutElementsInClassWith1IntTreeMap());
        System.out
                .println("[CollectionTest], Creation of "
                         + TESTS_MAP
                         + " And Put "
                         + SIZE
                         + " Elements "
                         + " TreeMap<Integer  ClassWith1Int>("
                         + SIZE
                         + ") final Map<Integer  ClassWith1Int> map = new TreeMap<Integer  ClassWith1Int>(SIZE)  for (int j = SIZE   j != 0  j--) map.put(j   classWith1Int)  ,, average time,"
                        + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time,"
                        + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.add(CollectionTest.testIterationOfClassWith1IntTreeMap());
        System.out.println("[CollectionTest], Iteration of " + TESTS_MAP + " TreeMap<Integer  ClassWith1Int>(" + SIZE
                           + ")  for (int j = SIZE  j != 0  j--) final ClassWith1Int s = map.get(j)  ,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();

    }

}

/**
 * Randomizer.
 *
 * @author Alexandre
 *
 */
final class Randomizer
{
    private static long lastRandom = 42;

    /**
     * Constructor.
     *
     */
    private Randomizer()
    {
        //
    }

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
    public static int getRandomValue(final int max)
    {
        return (int) ((max * (lastRandom = (lastRandom * 3877 + 29573) % 139968)) / 139968);
    }
}

/**
 * ClassWith1int.
 *
 * @author Alexandre
 *
 */
final class ClassWith1intb
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
