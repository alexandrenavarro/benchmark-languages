import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * MatrixMultiplyTest.
 * 
 * @author anavarro122404 - 25 sept. 07
 *
 *
 * <!-- $Id: MatrixMultiplyTest.java,v 1.1 2007/10/15 17:58:12 anavarro Exp $ -->.
 *
 */
public final class MatrixMultiplyTest
{
    /**
     * NB_TESTS
     */
    private static final int NB_TESTS                 = 5;

    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    private static final int NB_OF_EXCLUSION_MIN_MAX  = 2;

    /**
     * NB_CREATION_TESTS
     */
    private static final int NB_MATRIX_MULTIPLY_TESTS = 20 * 1000;

    /**
     * MATRIX_SIZE
     */
    private static final int MATRIX_SIZE              = 30;

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
     * testMultiplicationMatrix.
     * 
     * @return
     */
    public static long testMultiplyMatrix()
    {
        final int[][] m1 = makeMatrix(MATRIX_SIZE, MATRIX_SIZE);
        final int[][] m2 = makeMatrix(MATRIX_SIZE, MATRIX_SIZE);
        final int[][] mm = new int[MATRIX_SIZE][MATRIX_SIZE];
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_MATRIX_MULTIPLY_TESTS; i != 0; i--)
            multiplyMatrix(MATRIX_SIZE, MATRIX_SIZE, m1, m2, mm);
        final long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[MatrixMultiplyTest], Multiply of " + NB_MATRIX_MULTIPLY_TESTS + " Matrix with a size of " + MATRIX_SIZE
                + ",, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testMultiplicationMatrixOpimized.
     * 
     * @return
     */
    public static long testMultiplyMatrixOptimized()
    {
        final int[][] m1 = makeMatrix(MATRIX_SIZE, MATRIX_SIZE);
        final int[][] m2 = makeMatrix(MATRIX_SIZE, MATRIX_SIZE);
        final int[][] mm = new int[MATRIX_SIZE][MATRIX_SIZE];
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_MATRIX_MULTIPLY_TESTS; i != 0; i--)
            multiplyMatrixOptimized(MATRIX_SIZE, MATRIX_SIZE, m1, m2, mm);
        final long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[MatrixMultiplyTest], Multiply Optimized of " + NB_MATRIX_MULTIPLY_TESTS + " Matrix with a size of " + MATRIX_SIZE
                + ",, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * makeMatrix.
     * 
     * @param rows
     * @param cols
     * @return
     */
    public static int[][] makeMatrix(final int rows, final int cols)
    {
        int count = 1;
        final int[][] m = new int[rows][cols];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                m[i][j] = count++;
            }
        }
        return (m);
    }

    /**
     * multiplyMatrix.
     * 
     * @param rows
     * @param cols
     * @param m1
     * @param m2
     * @param m3
     */
    public static void multiplyMatrix(final int rows, final int cols, final int[][] m1, final int[][] m2, final int[][] m3)
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                int val = 0;
                for (int k = 0; k < cols; k++)
                {
                    val += m1[i][k] * m2[k][j];
                }
                m3[i][j] = val;
            }
        }
    }

    /**
     * multiplyMatrixOptimized.
     * 
     * @param rows
     * @param cols
     * @param m1
     * @param m2
     * @param m3
     */
    public static void multiplyMatrixOptimized(final int rows, final int cols, final int[][] m1, final int[][] m2, final int[][] m3)
    {
        try
        {
            for (int i = 0;; i++)
            {
                for (int j = cols; j-- != 0;)
                {
                    int val = 0;
                    for (int k = cols; k-- != 0;)
                    {
                        val += m1[i][k] * m2[k][j];
                    }
                    m3[i][j] = val;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            //
        }
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
            executionTimes.add(MatrixMultiplyTest.testMultiplyMatrix());
        System.out.println("[MatrixMultiplyTest], Multiply of " + NB_MATRIX_MULTIPLY_TESTS + " Matrix with a size of " + MATRIX_SIZE
                + ",, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time,"
                + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(MatrixMultiplyTest.testMultiplyMatrixOptimized());
        System.out.println("[MatrixMultiplyTest], Multiply Optimized of " + NB_MATRIX_MULTIPLY_TESTS + " Matrix with a size of " + MATRIX_SIZE
                + ",, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time,"
                + Collections.max(executionTimes) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
    }
    
}
