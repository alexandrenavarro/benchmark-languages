#include <time.h>
#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;



class TimingTest
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
    static const int NB_TIMING_TESTS         = 50000000;


    
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
	long averageTime = TimingTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
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
     * testCurrentTime
     */
    static long testCurrentTime()
    {
		clock_t start = clock();
		for (int i = NB_TIMING_TESTS; i != 0; i--)
        {
            clock_t currentTime = clock();
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[TimingTest], Invoke of " << NB_TIMING_TESTS << " clock_t currentTime = clock(),, snapshot time," << executionTime << "\n";
		return executionTime;
    }

	static int main(int size, char** args)
	{
		int nbTests = TimingTest::NB_TESTS;
		int nbOfExclusionMinMax = TimingTest::NB_OF_EXCLUSION_MIN_MAX;
	    
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
				(TimingTest::testCurrentTime());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[TimingTest], Invoke of " << TimingTest::NB_TIMING_TESTS << " clock_t currentTime = clock(),, average time, "<< TimingTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << TimingTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		return 0;
	}

};

/*
int main(string* args, int size)
{
	TimingTest::main(args, size);
}
*/



