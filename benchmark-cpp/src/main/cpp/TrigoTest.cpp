#include <time.h>
#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;



class TrigoTest
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
     * NB_TRIGO_TESTS
     */
    static const int    NB_TRIGO_TESTS     = 1;
    
    /**
     * TRIG_MAX
     */
    static double TRIG_MAX                ;               // 10M

	    /**
     * EXP_MAX
     */
    static double EXP_MAX                ;          


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
	long averageTime = TrigoTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
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
     * testSin
     */
    static long testSin()
    {
		clock_t start = clock();
        double sine = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            sine = sin(i);
            i++;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[TrigoTest], Calculation of Sine "  << NB_TRIGO_TESTS << " while (i < " << TRIG_MAX << " ) {sine = sin(i)  i++ } , sine=" << sine << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCos
     */
    static long testCos()
    {
		clock_t start = clock();
        double cosine = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            cosine = cos(i);
            i++;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[TrigoTest], Calculation of Cosine "  << NB_TRIGO_TESTS << " while (i < " << TRIG_MAX << " ) {cosine = cos(i)  i++ } , cosine=" << cosine << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testTan
     */
    static long testTan()
    {
		clock_t start = clock();
        double tangente = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            tangente = tan(i);
            i++;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[TrigoTest], Calculation of Tangente "  << NB_TRIGO_TESTS << " while (i < " << TRIG_MAX << " ) {tangente = tan(i)  i++ } , tangente=" << tangente << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

    /**
     * testLog
     */
    static long testLog()
    {
		clock_t start = clock();
        double log = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            log = log10(i);
            i++;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[TrigoTest], Calculation of Log "  << NB_TRIGO_TESTS << " while (i < " << TRIG_MAX << " ) {log = log(i)  i++ } , log=" << log << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }


	/**
     * testExp
     */
    static long testExp()
    {
		clock_t start = clock();
        double exp1 = 0.0;
		for (int j = (int) (TRIG_MAX / EXP_MAX); j != 0 ; j-- )
		{
			double i = 0.0;
			while (i < EXP_MAX)
			{
				exp1 = exp(i);
				i++;
			}
		}
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[TrigoTest], Calculation of Exp "  << NB_TRIGO_TESTS << " while (i < " << TRIG_MAX << " ) {exp1 = exp(i)  i++ } , exp=" << exp1 << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testSqrt
     */
    static long testSqrt()
    {
		clock_t start = clock();
        double sqrt1 = 0.0;
        double i = 0.0;
        while (i < TRIG_MAX)
        {
            sqrt1 = sqrt(i);
            i++;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[TrigoTest], Calculation of Sqrt "  << NB_TRIGO_TESTS << " while (i < " << TRIG_MAX << " ) {sqrt1 = sqrt(i)  i++ } , sqrt=" << sqrt1 << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	static int main (int size, char** args)
	{
		int nbTests = TrigoTest::NB_TESTS;
		int nbOfExclusionMinMax = TrigoTest::NB_OF_EXCLUSION_MIN_MAX;
	    
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
				(TrigoTest::testSin());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[TrigoTest], Calculation of Sine "  << TrigoTest::NB_TRIGO_TESTS << " while (i < " << TrigoTest::TRIG_MAX << " ) {sine = sin(i)  i++ } ,, average time," << TrigoTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << TrigoTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(TrigoTest::testCos());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[TrigoTest], Calculation of Cosine "  << TrigoTest::NB_TRIGO_TESTS << " while (i < " << TrigoTest::TRIG_MAX << " ) {cosine = cos(i)  i++ } ,, average time," << TrigoTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << TrigoTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(TrigoTest::testTan());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[TrigoTest], Calculation of Tangente "  << TrigoTest::NB_TRIGO_TESTS << " while (i < " << TrigoTest::TRIG_MAX << " ) {tangente = tan(i)  i++ } ,, average time," << TrigoTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << TrigoTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(TrigoTest::testLog());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[TrigoTest], Calculation of Log "  << TrigoTest::NB_TRIGO_TESTS << " while (i < " << TrigoTest::TRIG_MAX << " ) {log = log(i)  i++ } ,, average time," << TrigoTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << TrigoTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(TrigoTest::testExp());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[TrigoTest], Calculation of Exp "  << TrigoTest::NB_TRIGO_TESTS << " while (i < " << TrigoTest::TRIG_MAX << " ) {exp1 = exp(i)  i++ } ,, average time," << TrigoTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << TrigoTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(TrigoTest::testSqrt());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[TrigoTest], Calculation of Sqrt "  << TrigoTest::NB_TRIGO_TESTS << " while (i < " << TrigoTest::TRIG_MAX << " ) {sqrt1 = sqrt(i)  i++ } ,, average time," << TrigoTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << TrigoTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		return 0;
	}


};

#ifndef Centralized
double TrigoTest::TRIG_MAX = 10000000.0;
double TrigoTest::EXP_MAX = 100.0;
#endif


/*
int main(string* args, int size)
{
	TrigoTest::main(args, size);
}
*/
