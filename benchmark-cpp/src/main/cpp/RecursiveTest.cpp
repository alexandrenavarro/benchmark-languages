#include <time.h>
#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;

class FiboInstance
{
	public :

	static int fibStatic(int n)
	{
        if (n < 2)
		//if (n == 0 || n == 1)
            return (1);
        return (fibStatic(n - 2) + fibStatic(n - 1));
    }

	int fib(int n)
	{
         if (n < 2)
	     //if (n == 0 || n == 1)
            return (1);
        return (fib(n - 2) + fib(n - 1));
    }

};


class Fibo
{
	public:
		virtual int fib(int n) = 0;
};


class FiboImpl : public Fibo
{
	public :

	int fib(int n)
	{
         if (n < 2)
	     //if (n == 0 || n == 1)
            return (1);
        return (fib(n - 2) + fib(n - 1));
    }
};



class RecursiveTest
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
     * NB_RECURSIVE_TESTS
     */
    static const int NB_RECURSIVE_TESTS      = 1;

    /**
     * FIBO_N
     */
    static const int FIBO_N                  = 41;



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
	long averageTime = RecursiveTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
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
    static long testFiboStatic()
    {
		clock_t start = clock();
		int fibo = 0;
        for (int i = NB_RECURSIVE_TESTS; i-- != 0;)
        {
			fibo = FiboInstance::fibStatic(FIBO_N);
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[RecursiveTest], Invoke of " << NB_RECURSIVE_TESTS << " fibo = FiboInstance::fibStatic(" << FIBO_N << "), fibo=" << fibo << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }


	/**
     * testFibo1
     */
    static long testFiboInstance()
    {
		FiboInstance* fiboInstance = new FiboInstance();
		clock_t start = clock();
		int fibo = 0;
        for (int i = NB_RECURSIVE_TESTS; i-- != 0;)
        {
			fibo = fiboInstance->fib(FIBO_N);
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[RecursiveTest], Invoke of " << NB_RECURSIVE_TESTS << " fibo = fiboInstance->fib(" << FIBO_N << "), fibo=" << fibo << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

		
	/**
     * testFibo1
     */
    static long testFiboInstanceImplWithInterface()
    {
		Fibo* fibo = new FiboImpl();
		clock_t start = clock();
		int fiboResult = 0;
        for (int i = NB_RECURSIVE_TESTS; i-- != 0;)
        {
			fiboResult = fibo->fib(FIBO_N);
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[RecursiveTest], Invoke of " << NB_RECURSIVE_TESTS << " fiboResult = fibo->fib(" << FIBO_N << "), fibo=" << fiboResult << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

			 /**
     * testFibo1
     */
    static long testFiboInstanceImplWithoutInterface()
    {
		FiboImpl* fiboImpl = new FiboImpl();
		clock_t start = clock();
		int fibo = 0;
        for (int i = NB_RECURSIVE_TESTS; i-- != 0;)
        {
			fibo = fiboImpl->fib(FIBO_N);
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[RecursiveTest], Invoke of " << NB_RECURSIVE_TESTS << " fibo = fiboImpl->fib(" << FIBO_N << "), fibo=" << fibo << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }



	static int main (int size, char** args)
	{
		int nbTests = RecursiveTest::NB_TESTS;
		int nbOfExclusionMinMax = RecursiveTest::NB_OF_EXCLUSION_MIN_MAX;
	    
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
				(RecursiveTest::testFiboStatic());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[RecursiveTest], Invoke of " << RecursiveTest::NB_RECURSIVE_TESTS << " fibo = FiboInstance::fibStatic(" << RecursiveTest::FIBO_N << "),, average time," << RecursiveTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << RecursiveTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(RecursiveTest::testFiboInstance());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[RecursiveTest], Invoke of " << RecursiveTest::NB_RECURSIVE_TESTS << " fibo = fiboInstance->fib(" << RecursiveTest::FIBO_N << "),, average time," << RecursiveTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << RecursiveTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(RecursiveTest::testFiboInstanceImplWithInterface());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[RecursiveTest], Invoke of " << RecursiveTest::NB_RECURSIVE_TESTS << " fiboResult = fibo->fib(" << RecursiveTest::FIBO_N << "),, average time," << RecursiveTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << RecursiveTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";


		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(RecursiveTest::testFiboInstanceImplWithoutInterface());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[RecursiveTest], Invoke of " << RecursiveTest::NB_RECURSIVE_TESTS << " fibo = fiboImpl->fib(" << RecursiveTest::FIBO_N << "),, average time," << RecursiveTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << RecursiveTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";


		return 0;
	}
};





/*
int main(string* args, int size)
{
	RecursiveTest::main(args, size);
}
*/
