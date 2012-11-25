#include <time.h>
#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;


class MatrixMultiplyTest
{
	public:

	/**
     * NB_TESTS
     */
	static const int NB_TESTS                = 10;

    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    static const int NB_OF_EXCLUSION_MIN_MAX = 0;

    /**
     * NB_CREATION_TESTS
     */
    static const int NB_MATRIX_MULTIPLY_TESTS = 20000;

    /**
     * MATRIX_SIZE
     */
    static const int MATRIX_SIZE              = 30;

    /**
     * averageTimeWithoutMinMax.
     * 
     * @param executionTimes
     * @param numberOfMinMaxToRemove
     * @return
     */
    static long averageTimeWithoutMinMax(vector<long> executionTimes, int numberOfMinMaxToRemove)
    {
        long average = 0;
	int minMax = (numberOfMinMaxToRemove > 0) ? numberOfMinMaxToRemove : 0;
	if (minMax != 0)	
		sort(executionTimes.begin(), executionTimes.end());
        for (int i = minMax; i < executionTimes.size() - minMax; i++)
        {
			average += executionTimes[i];
        }
        average = average / (executionTimes.size() - minMax * 2);
        return average;
    }
	
    
	/**
     * relativeDeviationTime.
     *
     * @param executionTimes
     * @return
     */
    static double relativeDeviationTimeWithoutMinMax(vector<long> executionTimes, int numberOfMinMaxToRemove)
    {
	long averageTime = MatrixMultiplyTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
        double deviation = 0;
	int minMax = (numberOfMinMaxToRemove > 0) ? numberOfMinMaxToRemove : 0;
	if (minMax != 0)	
		sort(executionTimes.begin(), executionTimes.end());
        for (int i = minMax; i < executionTimes.size() - minMax; i++)
        {
            deviation += pow(executionTimes[i] - averageTime, 2.0);
        }
        return (deviation == 0) ? 0.0 : ((int) ((sqrt((double)(deviation / executionTimes.size())) / averageTime * 100) * 100)) / 100.0;
    }


	static int **mkmatrix(int rows, int cols) 
	{
		int i, j, count = 1;
		int **m = (int **) malloc(rows * sizeof(int *));
		for (i=0; i<rows; i++) 
		{
			m[i] = (int *) malloc(cols * sizeof(int));
			for (j=0; j<cols; j++) 
			{
				m[i][j] = count++;
			}
		}
		return(m);
	}

	static void zeromatrix(int rows, int cols, int **m) 
	{
		int i, j;
		for (i=0; i<rows; i++)
		for (j=0; j<cols; j++)
			m[i][j] = 0;
	}

	static void freematrix(int rows, int **m) 
	{
		while (--rows > -1) { free(m[rows]); }
		free(m);
	}

	static int **mmult(int rows, int cols, int **m1, int **m2, int **m3) 
	{
		int i, j, k, val;
		for (i=0; i<rows; i++) 
		{
			for (j=0; j<cols; j++) 
			{
				val = 0;
				for (k=0; k<cols; k++) 
				{
					val += m1[i][k] * m2[k][j];
				}
				m3[i][j] = val;
			}
		}
		return(m3);
	}



	 /**
     * testFibo1
     */
    static long testMultiplyMatrix()
    {
        int count = 0;
		clock_t start = clock();
		int i;
	    
		int **m1 = MatrixMultiplyTest::mkmatrix(MATRIX_SIZE, MATRIX_SIZE);
		int **m2 = MatrixMultiplyTest::mkmatrix(MATRIX_SIZE, MATRIX_SIZE);
		int **mm = MatrixMultiplyTest::mkmatrix(MATRIX_SIZE, MATRIX_SIZE);

		for (i=0; i<NB_MATRIX_MULTIPLY_TESTS; i++) {
		mm = mmult(MATRIX_SIZE, MATRIX_SIZE, m1, m2, mm);
		}
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[MatrixMultiply], Multiply of " << NB_MATRIX_MULTIPLY_TESTS << " Matrix with a size of "  << MATRIX_SIZE << ",, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	static int main (int size, char** args)
	{
		int nbTests = MatrixMultiplyTest::NB_TESTS;
		int nbOfExclusionMinMax = MatrixMultiplyTest::NB_OF_EXCLUSION_MIN_MAX;
	    
		// TODO does not work
		//if (args && size > 0)
		//{
		//    nbTests = atoi(args[0].c_str());
		//}
		//if (args && size > 1)
		//{
		//    nbOfExclusionMinMax = atoi(args[1].c_str());
		//}
		
		vector<long> executionTimes;
		
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(MatrixMultiplyTest::testMultiplyMatrix());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[MatrixMultiply], Multiply of " << MatrixMultiplyTest::NB_MATRIX_MULTIPLY_TESTS << " Matrix with a size of " << MatrixMultiplyTest::MATRIX_SIZE << " ,, average time," << MatrixMultiplyTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << MatrixMultiplyTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
	
		return 0;
	}

};

/*

*/

/*
int main(string* args, int size)
{
	MatrixMultiplyTest::main(args, size);
}
*/
