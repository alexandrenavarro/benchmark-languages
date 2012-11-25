#include <time.h>
#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;

class FileTest
{
public : 
	 /**
     * NB_TESTS
     */
    static const int    NB_TESTS                = 10;
    
    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    static const int    NB_OF_EXCLUSION_MIN_MAX = 0;
    
    /**
     * NB_FILE_TESTS
     */
    static const int    NB_FILE_TESTS           = 1;
    
    /**
     * IO_MAX
     */
    static const int    IO_MAX                  = 2000000;
    
	#define IM 139968
	#define IA   3877
	#define IC  29573

	static int gen_random(int max) 
	{
		static long last = 42;
		return( max * (last = (last * IA + IC) % IM) / IM );
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
	long averageTime = FileTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
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
    static long testWriteFile()
    {
		FILE *stream = NULL;
		string beginFile = "" ;
		char temp [32];
		sprintf(temp, "%d", FileTest::gen_random(100));
		string random(temp);
		string endFile = ".txt";
		string file ("");
		file = beginFile;
		file += random;
		file += endFile;
		stream = fopen(file.c_str(), "w");
		char textLine[] = "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz1234567890abcdefgh\n";
		char textLine2[] = "1234567890\n";
		clock_t start = clock();
		int i = 0;
		while (i++ < IO_MAX)
		{
			//fwrite seems to be a little bit quicker than fputs
			if (i % 2 == 0)
			{
				fwrite(textLine, sizeof(textLine) , 1, stream);
			}
			else
			{
				fwrite(textLine2, sizeof(textLine2) , 1, stream);
			}
		}
		fclose(stream);
        clock_t end = clock();
		if( remove( file.c_str()) != 0 )
			cout << "Error deleting file";
		long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[FileTest], Write " << NB_FILE_TESTS << " file(s) with " << IO_MAX << " lines,, snapshot time," << executionTime << "\n";
		return executionTime;
    }

    /**
     * testCreationOfClassWith1String
     */
    static long testWriteFileWithFlush()
    {
		FILE *stream;
		string beginFile = "" ;
		char temp [32];
		sprintf(temp, "%d", FileTest::gen_random(100));
		string random(temp);
		string endFile = ".txt";
		string file ("");
		file = beginFile;
		file += random;
		file += endFile;
		stream = fopen(file.c_str(), "w");
		char textLine[] = "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz1234567890abcdefgh\n";
		char textLine2[] = "1234567890\n";
		clock_t start = clock();
		int i = 0;
		while (i++ < IO_MAX)
		{
			//fwrite seems to be a little bit quicker than fputs
			if (i % 2 == 0)
			{
				fwrite(textLine,  sizeof(textLine) , 1, stream);
			}
			else
			{
				fwrite(textLine2, sizeof(textLine2) , 1, stream);
			}
			fflush(stream);
		}
		fclose(stream);
        clock_t end = clock();
		if( remove( file.c_str()) != 0 )
			cout << "Error deleting file";
		long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[FileTest], Write with flush " << NB_FILE_TESTS << " file(s) with " << IO_MAX << " lines,, snapshot time," << executionTime << "\n";
		return executionTime;
    }

		    /**
     * testCreationOfClassWith1String
     */
    static long testReadFile()
    {
		FILE *stream;
		char readLine[100];
		stream = fopen("test.txt", "r");
		int i = 0;
		int count = 0;
		clock_t start = clock();
		while (!feof(stream))
		{
			fgets(readLine, 100, stream);
			// Just for doing the same thing of readLine 
			for (int i = 0; i < 100; i++)
			{
				if (readLine[i] != '\n')
				{
					count++;
				}
			}
		}
		fclose(stream);
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[FileTest], Read " << NB_FILE_TESTS << " file(s) with " << IO_MAX << " lines,, snapshot time," << executionTime << "\n";
        return executionTime;
    }

static int main(int size, char** args)
	{
		int nbTests = FileTest::NB_TESTS;
		int nbOfExclusionMinMax = FileTest::NB_OF_EXCLUSION_MIN_MAX;
	    
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
				(FileTest::testWriteFile());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[FileTest], Write " << FileTest::NB_FILE_TESTS << " file(s) with " << FileTest::IO_MAX << " lines,, average time, " << FileTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << FileTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
		executionTimes.clear();

		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(FileTest::testWriteFileWithFlush());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[FileTest], Write with flush " << FileTest::NB_FILE_TESTS << " file(s) with " << FileTest::IO_MAX << " lines,, average time, " << FileTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << FileTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
		
		// Write file for read Test
		FILE *stream;
		stream = fopen("test.txt", "w");
		char textLine[] = "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz1234567890abcdefgh\n";
		char textLine2[] = "1234567890\n";
		clock_t start = clock();
		int i = 0;
		while (i++ < FileTest::IO_MAX)
		{
			if (i % 2 == 0)
			{
				fputs(textLine, stream);
			}
			else
			{
				fputs(textLine2, stream);
			}
		}
		fclose(stream);

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(FileTest::testReadFile());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[FileTest], Read " << FileTest::NB_FILE_TESTS << " file(s) with " << FileTest::IO_MAX << " lines,, average time, " << FileTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << FileTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
		
		// Delete File
		if( remove("test.txt") != 0 )
			cout << "Error deleting file";

		return 0;

	}


};





/*
int main(string* args, int size)
{
	FileTest::main(args, size);
}
*/
