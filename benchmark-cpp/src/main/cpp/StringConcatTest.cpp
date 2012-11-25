#include <time.h>
#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;



class StringConcatTest
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
     * NB_STRING_CONCAT_TESTS
     */
    static const int    NB_STRING_CONCAT_WITH_ADD_TESTS   = 10000;
    
    /**
     * NB_STRING_CONCAT_TESTS
     */
    static const int    NB_STRING_CONCAT_TESTS            = 100000;
    
    /**
     * NB_STRING_CONCAT_TESTS
     */
    static const int    NB_STRING_CONCAT_WITH_ADD_TESTS_2 = 10000000;
    
    /**
     * NB_STRING_CONCAT_TESTS
     */
    static const int    NB_STRING_CONCAT_TESTS_2          = 10 * 1000 * 1000;
    
    /**
     * SIZE
     */
    static const int    SIZE                              = 1000;
    
    /**
     * SIZE
     */
    static const int    SIZE_2                            = 3;


	/**
     * A
     */
    static string A;

		/**
     * averageTime.
     * 
     * @param executionTimes
     * @return
     */
    static long averageTime(vector<long> executionTimes)
    {
        long average = 0;
        for (int i = executionTimes.size(); i-- != 0;)
        {
            average += executionTimes[i];
        }
        average = average / executionTimes.size();
        return average;
    }
    
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
	long averageTime = StringConcatTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
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
    static long testStringConcatWithAdd()
    {
		int count = 0;
		clock_t start = clock();
        for (int i = NB_STRING_CONCAT_TESTS; i != 0; i--)
        {
            string s = "";
            for (int j = SIZE; j != 0; j--)
            {
                s += StringConcatTest::A;
            }
            count += s.length();
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[StringConcatTest], Concatenation of " << NB_STRING_CONCAT_TESTS << " String With " << SIZE << " Add  for (int j = SIZE   j != 0   j--) {s += StringConcatTest::A  } count += s.length() ,count="  << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

		/**
     * testCreationOfClassWith1String
     */
    static long testStringConcatWithChar()
    {
		int count = 0;
		wchar_t *test =(wchar_t*) StringConcatTest::A.c_str();
		clock_t start = clock();
        for (int j = NB_STRING_CONCAT_TESTS; j != 0; j--)
        {
			int i;
			wchar_t *strbuf = new wchar_t[SIZE * wcslen(test)];
			strbuf[0]='\0';
			int i3=0;
			for ( i = SIZE; i != 0; i--)
			{
				for (int i2 = 0; i2 < wcslen(test); i2++)
				{
					strbuf[i3]=test[i2];
					i3++;
				}
			}
			count += SIZE * wcslen(test);
			delete strbuf;
		}
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[StringConcatTest], Concatenation of " << NB_STRING_CONCAT_TESTS << " String With " << SIZE << " Add  for (int j = SIZE   j != 0   j--) {s += StringConcatTest::A  } count += s.length() ,count="  << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }


	/**
     * testCreationOfClassWith1String
     */
    static long testStringConcatWithAdd2()
    {
		int count = 0;
		clock_t start = clock();
        for (int i = NB_STRING_CONCAT_TESTS_2; i != 0; i--)
        {
            string s = "";
            for (int j = SIZE_2; j != 0; j--)
            {
               s += StringConcatTest::A;
            }
            count += s.length();
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[StringConcatTest], Concatenation of " << NB_STRING_CONCAT_TESTS << " String With " << SIZE_2 << " Add  for (int j = SIZE   j != 0   j--) {s += StringConcatTest::A  } count += s.length() ,count="  << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

		/**
     * testCreationOfClassWith1String
     */
    static long testStringConcatWithChar2()
    {
		int count = 0;
		wchar_t *test = (wchar_t*) StringConcatTest::A.c_str();
		clock_t start = clock();
		for (int j = NB_STRING_CONCAT_TESTS_2; j != 0; j--)
        {
			wchar_t *strbuf = new wchar_t[SIZE * wcslen(test)];
			strbuf[0]='\0';
			int i3=0;
			for (int i = SIZE_2; i != 0; i--)
			{
				for (int i2 = 0; i2 < wcslen(test); i2++)
				{
					strbuf[i3]=test[i2];
					i3++;
				}
			}
			count += SIZE * wcslen(test);
			delete strbuf;
		}
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[StringConcatTest], Concatenation of " << NB_STRING_CONCAT_TESTS << " String With " << SIZE_2 << " wchar_t *strbuf, count="  << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	static int main(int size, char** args)
	{
		int nbTests = StringConcatTest::NB_TESTS;
		int nbOfExclusionMinMax = StringConcatTest::NB_OF_EXCLUSION_MIN_MAX;
	    
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
				(StringConcatTest::testStringConcatWithAdd());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[StringConcatTest], Concatenation of " << StringConcatTest::NB_STRING_CONCAT_TESTS << " String With " << StringConcatTest::SIZE << " Add  for (int j = SIZE   j != 0   j--) {s += StringConcatTest::A } ,,average time," << StringConcatTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << StringConcatTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(StringConcatTest::testStringConcatWithChar());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[StringConcatTest], Concatenation of " << StringConcatTest::NB_STRING_CONCAT_TESTS << " String With " << StringConcatTest::SIZE << " Add  for (int j = SIZE   j != 0   j--) {s += StringConcatTest::A } ,,average time," << StringConcatTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << StringConcatTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(StringConcatTest::testStringConcatWithAdd2());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[StringConcatTest], Concatenation of " << StringConcatTest::NB_STRING_CONCAT_TESTS_2 << " String With " << StringConcatTest::SIZE_2 << " Add  for (int j = SIZE   j != 0   j--) {s += StringConcatTest::A } ,,average time," << StringConcatTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << StringConcatTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
		executionTimes.push_back
				(StringConcatTest::testStringConcatWithChar2());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[StringConcatTest], Concatenation of " << StringConcatTest::NB_STRING_CONCAT_TESTS_2 << " String With " << StringConcatTest::SIZE_2 << " Add  for (int j = SIZE   j != 0   j--) {s += StringConcatTest::A } ,,average time," << StringConcatTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << StringConcatTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		return 0;
	}
	

};

#ifndef Centralized
string StringConcatTest::A = "aa";
#endif




/*
int main(string* args, int size)
{
	StringConcatTest::main(args, size);
}
*/

