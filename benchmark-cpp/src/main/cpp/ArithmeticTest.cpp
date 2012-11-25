#include <time.h>
#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;



class ArithmeticTest
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
    static const int NB_ARITHMETIC_TESTS	= 0;
    
    /**
     * INT_MAX
     */
	static const int INT_MAX1 = 1000000000;
    
    /**
     * DOUBLE_MIN
     */
    static double DOUBLE_MIN1;
    
    /**
     * DOUBLE_MAX
     */
    static double DOUBLE_MAX1;
    
    /**
     * LONG_MIN
     */
    static long long LONG_MIN1;
    
    /**
     * LONG_MAX
     */
    static long long LONG_MAX1;


   
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
	long averageTime = ArithmeticTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
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
     * testIntArithmetic
     */
	
    static long testIntArithmetic()
    {
        int count = 0;
        clock_t start = clock();
        int intResult = 1;
        int i = 1;
        while (i < INT_MAX1)
        {
            intResult -= i++;
            intResult += i++;
            intResult *= i++;
            intResult /= i++;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[ArithmeticTest], Calculation of  " << NB_ARITHMETIC_TESTS << " int intResult = 1  int i = 1  while (i <" << INT_MAX1 << ") {intResult -= i++  intResult += i++  intResult *= i++  intResult /= i++},intResult=" << intResult << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}

	/**
     * testDoubleArithmetic
     */
	
    static long testDoubleArithmetic()
    {
        int count = 0;
        clock_t start = clock();
        double doubleResult = DOUBLE_MIN1;
        double i = DOUBLE_MIN1;
        while (i < DOUBLE_MAX1)
        {
            doubleResult -= i++;
            doubleResult += i++;
            doubleResult *= i++;
            doubleResult /= i++;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[ArithmeticTest], Calculation of  " << NB_ARITHMETIC_TESTS << " double doubleResult = " << DOUBLE_MIN1 << " double i = " << DOUBLE_MIN1 << " while (i <" << DOUBLE_MAX1 << ") {doubleResult -= i++  doubleResult += i++  doubleResult *= i++  doubleResult /= i++ },doubleResult=" << doubleResult << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}

	/**
     * testLongArithmetic
     */
	
    static long testLongArithmetic()
    {
        int count = 0;
        clock_t start = clock();
		long long longResult = LONG_MIN1;
		long long i = LONG_MIN1;
		while (i < LONG_MAX1)
		{
			longResult -= i++;
			longResult += i++;
			longResult *= i++;
			longResult /= i++;
		}
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[ArithmeticTest], Calculation of  " << NB_ARITHMETIC_TESTS << " long long longResult = " << LONG_MIN1 << " long long i = " << LONG_MIN1 << " while (i < " << LONG_MAX1 << ") {longResult -= i++  longResult += i++  longResult *= i++  longResult /= i++ },longResult=" << longResult << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}

static 	int main(int size, char** args)
	{
		int nbTests = ArithmeticTest::NB_TESTS;
		int nbOfExclusionMinMax = ArithmeticTest::NB_OF_EXCLUSION_MIN_MAX;
	    
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
				(ArithmeticTest::testIntArithmetic());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[ArithmeticTest], Calculation of  " << ArithmeticTest::NB_ARITHMETIC_TESTS << " int intResult = 1  int i = 1  while (i <" << ArithmeticTest::INT_MAX1 << ") {intResult -= i++  intResult += i++  intResult *= i++  intResult /= i++},, average time," << ArithmeticTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << ArithmeticTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(ArithmeticTest::testDoubleArithmetic());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[ArithmeticTest], Calculation of  " << ArithmeticTest::NB_ARITHMETIC_TESTS << " double doubleResult = " << ArithmeticTest::DOUBLE_MIN1 << " double i = " << ArithmeticTest::DOUBLE_MIN1 << " while (i <" << ArithmeticTest::DOUBLE_MAX1 << ") {doubleResult -= i++  doubleResult += i++  doubleResult *= i++  doubleResult /= i++ },, average time," << ArithmeticTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << ArithmeticTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
		
		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(ArithmeticTest::testLongArithmetic());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[ArithmeticTest], Calculation of  " << ArithmeticTest::NB_ARITHMETIC_TESTS << " long long longResult = " << ArithmeticTest::LONG_MIN1 << " long long i = " << ArithmeticTest::LONG_MIN1 << " while (i < " << ArithmeticTest::LONG_MAX1 << ") {longResult -= i++  longResult += i++  longResult *= i++  longResult /= i++ },, average time," << ArithmeticTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << ArithmeticTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		return 0;
	}


};

#ifndef Centralized
double ArithmeticTest::DOUBLE_MIN1 = 10000000000;
double ArithmeticTest::DOUBLE_MAX1 = 11000000000;
long long ArithmeticTest::LONG_MIN1 = 10000000000;
long long ArithmeticTest::LONG_MAX1 = 11000000000;
#endif

/*
int int main(char** args, int size)
{
	ArithmeticTest::main(args, size);
}
*/

