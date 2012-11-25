#include <time.h>
#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;



class HeapSortTest
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
     * SIZE
     */
    static const int SIZE                    = 5000000;
    
    /**
     * NB_HEAPSORT_TESTS
     */
    static const int NB_HEAPSORT_TESTS       = 1;


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
	long averageTime = HeapSortTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
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
	#define IM 139968
	#define IA   3877
	#define IC  29573


	static double gen_random(double max) {
		static long last = 42;
		return( max * (last = (last * IA + IC) % IM) / IM );
	}

	
	/**
     * heapsort.
     *
     */
	static void heapsort(int n, double *ra) 
	{
		int i, j;
		int ir = n;
		int l = (n >> 1) + 1;
		double rra;

		for (;;) 
		{
			if (l > 1) 
			{
				rra = ra[--l];
			} 
			else 
			{
				rra = ra[ir];
				ra[ir] = ra[1];
				if (--ir == 1) 
				{
				ra[1] = rra;
				return;
				}
			}
			i = l;
			j = l << 1;
			while (j <= ir) 
			{
				if (j < ir && ra[j] < ra[j+1]) 
				{ 
					++j; 
				}
				if (rra < ra[j]) 
				{
					ra[i] = ra[j];
					j += (i = j);
				} 
				else 
				{
					j = ir + 1;
				}
			}
			ra[i] = rra;
		}
	}
	
	/**
     * testCreationOfClassWith1String
     */
    static long testHeapSort()
    {
		
		double * ary;
		ary = new double[SIZE+1];
		for (int i = SIZE; i != 0; i--) 
		{
			ary[i] = gen_random(1.0);
		}
		clock_t start = clock();
		for (int i = NB_HEAPSORT_TESTS; i != 0; i--)
        {
			HeapSortTest::heapsort(SIZE, ary);
		}
        clock_t end = clock();
		
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[HeapSortTest], Sort an array of double " << SIZE  << ",, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	static int main (int size, char** args)
	{
		int nbTests = HeapSortTest::NB_TESTS;
		int nbOfExclusionMinMax = HeapSortTest::NB_OF_EXCLUSION_MIN_MAX;
	    
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
		
		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(HeapSortTest::testHeapSort());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[HeapSortTest], Sort an array of double " << HeapSortTest::SIZE << ",, average time,"<< HeapSortTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << HeapSortTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		return 0;
	}
};





/*
int main(string* args, int size)
{
	HeapSortTest::main(args, size);
}
*/
