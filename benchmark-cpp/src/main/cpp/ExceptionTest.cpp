#include <time.h>
#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;



class ExceptionTest
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
     * NB_EXCEPTION_TESTS
     */
    static const int NB_EXCEPTION_TESTS      = 1000 * 1000;


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
	long averageTime = ExceptionTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
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
     * testCreationOfClassWith1String
     */
    static long testUncheckedException()
    {
		clock_t start = clock();
	    for (int i = NB_EXCEPTION_TESTS; i != 0; i--)
	    {
	        try
	        {
				throw new exception();
	        }
            catch (...) 
	        {
	        }
	    }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[ExceptionTest], Throw and catch UncheckedException  " << NB_EXCEPTION_TESTS << " try {throw new exception()  } catch (...) {},, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCreationOfClassWith1String
     */
    static long testUncheckedExceptionWithDeepTry()
    {
		clock_t start = clock();
	    for (int i = NB_EXCEPTION_TESTS; i != 0; i--)
	    {
	        try
	        {
		        try
		        {
			        try
			        {
				        try
				        {
					        try
					        {
                                throw new exception();
					        }
                            catch (...) 
					        {
						        throw;
					        }
				        }
                        catch (...) 
				        {
					        throw;
				        }
			        }
                    catch (...) 
			        {
				        throw;
			        }
		        }
                catch (...) 
		        {
			        throw;
		        }
	        }
            catch (...) 
	        {
	        }
	    }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[ExceptionTest], Throw and catch RuntimeException with deep try (5)  " << NB_EXCEPTION_TESTS << " try { try { try { try { try {throw new RuntimeException()  } catch (RuntimeException e) {},, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	
static int main(int size, char** args)
	{
		int nbTests = ExceptionTest::NB_TESTS;
		int nbOfExclusionMinMax = ExceptionTest::NB_OF_EXCLUSION_MIN_MAX;
	    
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
				(ExceptionTest::testUncheckedException());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[ExceptionTest], Throw and catch UncheckedException  " << ExceptionTest::NB_EXCEPTION_TESTS << " try {throw new exception()  } catch (...) {},, average time,"<< ExceptionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << ExceptionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(ExceptionTest::testUncheckedExceptionWithDeepTry());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[ExceptionTest], Throw and catch RuntimeException with deep try (5)  " << ExceptionTest::NB_EXCEPTION_TESTS << " try { try { try { try { try {throw new RuntimeException()  } catch (RuntimeException e) {},, average time," << ExceptionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << ExceptionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		return 0;
	}
};



/*
int main(string* args, int size)
{
	ExceptionTest::main(args, size);
}
*/
