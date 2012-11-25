using System;
using System.Collections.Generic;
using System.Text;


sealed class MatrixMultiplyTest
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
    private static int NB_MATRIX_MULTIPLY_TESTS = 20 * 1000;
    
    /**
     * MATRIX_SIZE
     */
    private static int MATRIX_SIZE = 30;
    

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
 * testMultiplicationMatrix.
 *
 * @return
 */
    public static long testMultiplicationMatrix()
    {
        int[][] m1 = makeMatrix(MATRIX_SIZE, MATRIX_SIZE);
        int[][] m2 = makeMatrix(MATRIX_SIZE, MATRIX_SIZE);
        int[][] mm = new int[MATRIX_SIZE][];
        for (int i = 0; i < MATRIX_SIZE; i++)
        {
            mm[i] = new int[MATRIX_SIZE];
        }
        DateTime start = DateTime.Now;
        for (int i = NB_MATRIX_MULTIPLY_TESTS; i != 0; i--)
            multiplyMatrix(MATRIX_SIZE, MATRIX_SIZE, m1, m2, mm);
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[MatrixMultiplyTest], Multiply of " + NB_MATRIX_MULTIPLY_TESTS + " Matrix 2 with a size of " + MATRIX_SIZE + ",, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }
    
    
    

    /**
 * makeMatrix.
 *
 * @param rows
 * @param cols
 * @return
 */
    public static int[][] makeMatrix(int rows, int cols)
    {
        int count = 1;
        int[][] m = new int[rows][];
        for (int i = 0; i < rows; i++)
        {
            m[i] = new int[cols];
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
    public static void multiplyMatrix(int rows, int cols, int[][] m1, int[][] m2, int[][] m3)
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
            executionTimes.Add(MatrixMultiplyTest.testMultiplicationMatrix());
        executionTimes.Sort();
        Console.WriteLine("[MatrixMultiplyTest], Multiply of " + NB_MATRIX_MULTIPLY_TESTS + " Matrix  with a size of " + MATRIX_SIZE + ",, average time,"
                    + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                    + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
	
    }
    
    

}

