#include <time.h>
#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;


class NestedLoopsTest
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
    static const int NB_NESTED_LOOPS_TESTS   = 40;


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
	long averageTime = NestedLoopsTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
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
	 /**
     * testFibo1
     */
    static long testNestedLoops()
    {
		int a, b, c, d, e, f;
        int count = 0;
		clock_t start = clock();
        for (a = 0; a < NB_NESTED_LOOPS_TESTS; a++)
		{
            for (b = 0; b < NB_NESTED_LOOPS_TESTS; b++)
			{
                for (c = 0; c < NB_NESTED_LOOPS_TESTS; c++)
				{
                    for (d = 0; d < NB_NESTED_LOOPS_TESTS; d++)
					{
                        for (e = 0; e < NB_NESTED_LOOPS_TESTS; e++)
						{
                            for (f = 0; f < NB_NESTED_LOOPS_TESTS; f++)
							{
								// Never true, just to force gcc not to optimize
                                if (count % 2 == 2)
                                {
                                    count = count * 2;
								}
                                count += a + b + c + d + e + f;
							}
						}
					}
				}
			}
		}
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[NestedLoopsTest], 6 Nested Loops and add all counters " << NB_NESTED_LOOPS_TESTS << " ,count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	static int main (int size, char** args)
	{
		int nbTests = NestedLoopsTest::NB_TESTS;
		int nbOfExclusionMinMax = NestedLoopsTest::NB_OF_EXCLUSION_MIN_MAX;
	    
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
				(NestedLoopsTest::testNestedLoops());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[NestedLoopsTest], 6 Nested Loops and add all counters " << NestedLoopsTest::NB_NESTED_LOOPS_TESTS << " ,, average time," << NestedLoopsTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << NestedLoopsTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
		
		return 0;
	}
};

/*
int main(string* args, int size)
{
	NestedLoopsTest::main(args, size);
}
*/
