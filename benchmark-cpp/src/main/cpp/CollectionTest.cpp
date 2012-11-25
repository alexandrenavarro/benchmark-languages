#include <time.h>
#include <iostream>
#include <vector>
#include <map>
#include <math.h>
#include <algorithm>
#include <iostream>
#include <sstream>

using namespace std;

class ClassWith1intb;

#ifdef _WIN32
#include <hash_map>
typedef stdext::hash_map<int, ClassWith1intb> HASH_MAP;
#else
#include <tr1/unordered_map>
using namespace std::tr1;
typedef unordered_map<int, ClassWith1intb> HASH_MAP;
#endif 

typedef map<int, ClassWith1intb> TREE_MAP;

class ClassWith1intb
{
	private :
	int int1;

	public:
	
	ClassWith1intb() 
	{
		int1 = 1;
	}

    // Write because I want to test add element in a array on the heap, not on the stack
	~ClassWith1intb() 
	{
	}
	
	
	

    /**
     * getInt1.
     * 
     * @return
     */
    int getInt1()
    {
		return this->int1;
    }

    /**
     * setInt1.
     * 
     * @param aString1
     */
		void setInt1(int aInt1)
    {
        this->int1 = aInt1;
    }
};

class CollectionTest
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
    static const int    SIZE                    = 1000;
    
    /**
     * TESTS_LIST
     */
    static const int    TESTS_LIST              = 1000000;
    
    /**
     * TESTS_MAP
     */
    static const int    TESTS_MAP               = 100000;
    
    /**
     * TESTS_READ_MAP
     */
    static const int    TESTS_READ_MAP          = 100000;
    
    /**
     * TESTS_HASH
     */
    static const int    TESTS_HASH               = 1000000;

    /**
     * TESTS_HASHES_10_000
     */
    static const int    TESTS_HASHES_1_000_000     = 1000000;
    
    /**
     * TESTS_HASHES_10_000
     */
    static const int    TESTS_HASHES_100_000     = 100000;
    
    /**
     * TESTS_HASHES_100
     */
    static const int    TESTS_HASHES_100         = 100;
    
    /**
     * NB_COPY_DATA_TESTS
     */
    static const int    NB_COPY_DATA_TESTS      = 1000000;;
    
    /**
     * BLANK
     */
    static string BLANK;
    
    /**
     * SPACE
     */
    static string SPACE;

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
	long averageTime = CollectionTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
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
    static long testCreationOfClassWith1intArray()
    {
		int count = 0 ;
		clock_t start = clock();
		for (int i = TESTS_LIST; i != 0; i--)
        {
            ClassWith1intb list[SIZE];
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Creation of " << TESTS_LIST << " ClassWith1intArray[" << SIZE <<"] final ClassWith1int[] list = new ClassWith1int[SIZE] ,, snapshot time," << executionTime << "\n";
        return executionTime;
    }

    /**
     * testAddElementsInStringArray
     */
    static long testAddElementsInClassWith1intArray()
    {
		ClassWith1intb classWith1int;
		ClassWith1intb list[SIZE];
		clock_t start = clock();
		for (int i = TESTS_LIST; i != 0; i--)
        {
			for (int j = SIZE; j-- != 0;)
			{
				list[j] = classWith1int;
			}
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], " << TESTS_LIST << " Tests of Add " << SIZE << " Elements to ClassWith1intArray[" << SIZE << "]  for (int j = SIZE   j-- != 0  ) list[j] = classWith1int,, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCreationAndAddElementsToStringArray
     */
    static long testCreationAndAddElementsToClassWith1intArray()
    {
		ClassWith1intb classWith1int;
		clock_t start = clock();
		for (int i = TESTS_LIST; i != 0; i--)
        {
			ClassWith1intb list[SIZE];
			for (int j = SIZE; j-- != 0;)
				list[j] = classWith1int;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Creation of " << TESTS_LIST << " and Add " << SIZE << " Elements to ClassWith1intArray[" << SIZE << "]  for (int j = SIZE   j-- != 0  ) ClassWith1intb list[SIZE]  list[j] = classWith1int,, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCreationAndAddElementsToStringArray
     */
    static long testIterationOfClassWith1intArrayWithForwardLoop()
    {
		int count = 0;
		ClassWith1intb classWith1int;
		ClassWith1intb list[SIZE];
		for (int j = SIZE; j-- != 0;)
			list[j] = classWith1int;
		clock_t start = clock();
		//for (int i = TESTS_LIST; i != 0; i--)
		for (int i = 0; i < TESTS_LIST; i++)
        {
            for (int k = 0; k < SIZE; k++)
			{
				// Write to force cpp not to optimize the code, never executed
				if (k % 2 == 2)
				{
					list[k].setInt1(k);
				}
				count += list[k].getInt1();
			}

        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Iteration of " << TESTS_LIST << " ClassWith1intArray[" << SIZE << "] With Forward Loop   for (int k = 0   k < list.length   k++) count += list[k].getInt1()  , count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

		/**
     * testCreationAndAddElementsToStringArray
     */
    static long testIterationOfClassWith1intArrayWithTryCatch()
    {
		int count = 0;
		ClassWith1intb classWith1int;
		ClassWith1intb list[SIZE];
		for (int j = SIZE; j-- != 0;)
			list[j] = classWith1int;
		clock_t start = clock();
		for (int i = TESTS_LIST; i != 0; i--)
        {
			try
            {
				for (int k = 0;; k++)
				{
					// Write to force cpp not to optimize the code, never executed
					if (k % 2 == 2)
					{
						list[k].setInt1(k);
					}
					count += list[k].getInt1();
				}
			}
            catch (...)
            {
                //
            }
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Iteration of " << TESTS_LIST << " ClassWith1intArray[" << SIZE << "]With Try Catch try{for (int k = 0     k++) count += list[k].getInt1()   } catch (ArrayIndexOutOfBoundsException x){}  count += list[k].getInt1()  , count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }


		/**
     * testCreationAndAddElementsToStringArray
     */
    static long testIterationOfClassWith1intArrayWithReverseLoopDifferentZero()
    {
		int count = 0;
		ClassWith1intb classWith1int;
		ClassWith1intb list[SIZE];
		for (int j = SIZE; j-- != 0;)
			list[j] = classWith1int;
		clock_t start = clock();
		for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = SIZE; k != 0; k--)
			{
				// Write to force cpp not to optimize the code, never executed
				if (k % 2 == 2)
				{
					list[k].setInt1(k);
				}
				count += list[k - 1].getInt1();
			}
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Iteration of " << TESTS_LIST << " ClassWith1intArray[" << SIZE << "] With Reverse Loop Different Zero  for (int k = list.length   k != 0   k--) count += list[k - 1].getInt1()  , count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testIterationOfStringArrayWithReverseLoopDifferentZeroDecrementInTest
     */
    static long testIterationOfClassWith1intArrayWithReverseLoopDifferentZeroDecrementInTest()
    {
		int count = 0;
		ClassWith1intb classWith1int;
		ClassWith1intb list[SIZE];
		for (int j = SIZE; j-- != 0;)
			list[j] = classWith1int;
		clock_t start = clock();
		for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = SIZE; k-- != 0;)
            {
				// Write to force cpp not to optimize the code, never executed
				if (k % 2 == 2)
				{
					list[k].setInt1(k);
				}
				count += list[k].getInt1();
            }
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Iteration of " << TESTS_LIST << " ClassWith1intArray[" << SIZE << "] With Reverse Loop Different Zero Decrement In Test  for (int k = list.length   k-- != 0  ) count += list[k].getInt1()  , count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCopyData
     */
    static long testCopyData()
    {
        ClassWith1intb src [SIZE];
        ClassWith1intb dest [SIZE];
        for (int i = SIZE; i-- != 0;)
			src[i].setInt1(gen_random(100000));
		clock_t start = clock();
        for (int i = NB_COPY_DATA_TESTS; i != 0; i--)
        {
            for (int j = SIZE; j-- != 0;)
                dest[j] = src[j];
        }
        clock_t end = clock();
		double count = 0;
		for (int j = SIZE; j-- != 0;)
		{
			count += dest[j].getInt1();
		}

        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Copy data from an array to an array " << SIZE << ", count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

		/**
     * testCopyData
     */
    static long testCopyDataOptimized()
    {
        ClassWith1intb src [SIZE];
        ClassWith1intb dest [SIZE];
        for (int i = SIZE; i-- != 0;)
            src[i].setInt1(gen_random(100000));
		clock_t start = clock();
        for (int i = NB_COPY_DATA_TESTS; i != 0; i--)
        {
			  //memcpy (dest,src,SIZE);
        }
        clock_t end = clock();
		double count = 0;
		for (int j = SIZE; j-- != 0;)
		{
			count += dest[j].getInt1();
		}

        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Copy data Optimized from an array to an array " << SIZE  << ", count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }


	/**
     * testCreationOfStringList
     */
    static long testCreationOfClassWith1intList()
    {
		clock_t start = clock();
		for (int i = TESTS_LIST; i != 0; i--)
        {
			vector<ClassWith1intb> list(SIZE);
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Creation of " << TESTS_LIST << " vector<ClassWith1int>[" << SIZE <<"] vector<ClassWith1int> list(SIZE) ,, snapshot time," << executionTime << "\n";
        return executionTime;
    }

    /**
     * testAddElementsInStringArray
     */
    static long testAddElementsInClassWith1intList()
    {
		ClassWith1intb classWith1int;
		vector<ClassWith1intb> list(SIZE);
		clock_t start = clock();
		for (int i = TESTS_LIST; i != 0; i--)
        {
			for (int j = SIZE; j-- != 0;)
				list.push_back(classWith1int);
			list.clear();
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], " << TESTS_LIST << " Tests of Add " << SIZE << " Elements to vector<ClassWith1int>[" << SIZE << "]  for (int j = SIZE   j-- != 0  ) list.push_back(classWith1int)  ,, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCreationAndAddElementsToStringArray
     */
    static long testCreationAndAddElementsToClassWith1intList()
    {
		ClassWith1intb classWith1int;
		clock_t start = clock();
		for (int i = TESTS_LIST; i != 0; i--)
        {
			vector<ClassWith1intb> list(SIZE);
			for (int j = SIZE; j-- != 0;)
				list.push_back(classWith1int);
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Creation of " << TESTS_LIST << " and Add " << SIZE << " Elements to vector<ClassWith1int>[" << SIZE << "]  for (int j = SIZE   j-- != 0  )  vector<ClassWith1int>  list(SIZE)  list.push_back(classWith1int),, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCreationAndAddElementsToStringArray
     */
    static long testIterationOfClassWith1intListWithForwardLoop()
    {
		int count = 0;
		ClassWith1intb classWith1int;
		vector<ClassWith1intb> list(SIZE);
		for (int j = SIZE; j-- != 0;)
			list[j] = classWith1int;
		clock_t start = clock();
		for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = 0; k < SIZE; k++)
			{
				// Write to force cpp not to optimize the code, never executed
				if (k % 2 == 2)
				{
					list[k].setInt1(k);
				}
				count += list[k].getInt1();
			}
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Iteration of " << TESTS_LIST << " vector<ClassWith1int>[" << SIZE << "] With Forward Loop   for (int k = 0   k < list.length   k++) count += (list)[k].getInt1()  , count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }


    /**
     * testCreationAndAddElementsToStringArray
     */
    static long testIterationOfClassWith1intListWithReverseLoopDifferentZero()
    {
		int count = 0;
		ClassWith1intb ClassWith1int;
		vector<ClassWith1intb> list(SIZE);
		for (int j = SIZE; j-- != 0;)
			list[j] = ClassWith1int;
		clock_t start = clock();
		for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = SIZE; k != 0; k--)
			{
				// Write to force cpp not to optimize the code, never executed
				if (k % 2 == 2)
				{
					list[k - 1].setInt1(k);
				}
				count += (list)[k - 1].getInt1();
			}
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Iteration of " << TESTS_LIST << " vector<ClassWith1intb>[" << SIZE << "] With Reverse Loop Different Zero  for (int k = list.length   k != 0   k--)  count += (list)[k - 1].getInt1()  , count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testIterationOfStringArrayWithReverseLoopDifferentZeroDecrementInTest
     */
    static long testIterationOfClassWith1intListWithReverseLoopDifferentZeroDecrementInTest()
    {
		int count = 0;
		ClassWith1intb classWith1int;
		vector<ClassWith1intb> list(SIZE);
		for (int j = SIZE; j-- != 0;)
			list[j] = classWith1int;
		clock_t start = clock();
		for (int i = TESTS_LIST; i != 0; i--)
        {
            for (int k = SIZE; k-- != 0;)
			{
				// Write to force cpp not to optimize the code, never executed
				if (k % 2 == 2)
				{
					list[k].setInt1(k);
				}
				count += list[k].getInt1();
			}
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Iteration of " << TESTS_LIST << " vector<ClassWith1int>[" << SIZE << "] With Reverse Loop Different Zero Decrement In Test  for (int k = list.length   k-- != 0  ) count += (list)[k].getInt1()  ,count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

			/**
     * testCreationOfStringList
     */
    static long testCreationOfClassWith1intHashMap()
    {
		clock_t start = clock();
		for (int i = TESTS_MAP; i != 0; i--)
        {
			HASH_MAP map;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Creation of " << TESTS_MAP << " map[" << SIZE <<"] HASH_MAP map ,, snapshot time," << executionTime << "\n";
        return executionTime;
    }

    /**
     * testAddElementsInStringArray
     */
    static long testPutElementsInClassWith1intHashMap()
    {
		ClassWith1intb classWith1int;
		HASH_MAP map1;
		clock_t start = clock();
		for (int i = TESTS_MAP; i != 0; i--)
        {
			for (int j = SIZE; j-- != 0;)
			    map1[j] = classWith1int;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], " << TESTS_MAP << " Tests of Put " << SIZE << " Elements to map[" << SIZE << "]  for (int j = SIZE   j-- != 0  ) map1[j] = classWith1int ,, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCreationAndAddElementsToStringArray
     */
    static long testCreationAndPutElementsToClassWith1intHashMap()
    {
		ClassWith1intb classWith1int;
		clock_t start = clock();
		for (int i = TESTS_MAP; i != 0; i--)
        {
			HASH_MAP map1;
			for (int j = SIZE; j-- != 0;)
				map1[j] = classWith1int;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Creation of " << TESTS_MAP << " And Put " << SIZE << " Elements to map[" << SIZE << "] HASH_MAP map  for (int j = SIZE   j-- != 0  ) map1[j] = classWith1int) ,, snapshot time," << executionTime << "\n";
        return executionTime;
    }


		/**
     * testIterationOfStringMap
     */
    static long testIterationOfClassWith1intHashMap()
    {
		ClassWith1intb classWith1int;
		HASH_MAP map1;
		for (int j = SIZE; j-- != 0;)
		    map1[j] = classWith1int;
		clock_t start = clock();
		for (int i = TESTS_MAP; i != 0; i--)
        {
			for (int j = SIZE; j-- != 0;)
            {
				ClassWith1intb s = map1.find(j)->second;
            }
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Iteration of " << TESTS_MAP << " HASH_MAP map1"  << SIZE << ")  for (int j = SIZE   j-- != 0  ) ClassWith1intb s = map1.find(j)->second  ,, snapshot time," << executionTime << "\n";
        return executionTime;
    }



				/**
     * testCreationOfStringList
     */
    static long testCreationOfClassWith1intTreeMap()
    {
		clock_t start = clock();
		for (int i = TESTS_MAP; i != 0; i--)
        {
			TREE_MAP map;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Creation of " << TESTS_MAP << " map[" << SIZE <<"] TREE_MAP map,, snapshot time," << executionTime << "\n";
        return executionTime;
    }

    /**
     * testAddElementsInStringArray
     */
    static long testPutElementsInClassWith1intTreeMap()
    {
		ClassWith1intb classWith1int;
		TREE_MAP map1;
		clock_t start = clock();
		for (int i = TESTS_MAP; i != 0; i--)
        {
			for (int j = SIZE; j-- != 0;)
			    map1[j] = classWith1int;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], " << TESTS_MAP << " Tests of Put " << SIZE << " Elements to map[" << SIZE << "]  for (int j = SIZE   j-- != 0  ) map1[j] = classWith1int ,, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCreationAndAddElementsToStringArray
     */
    static long testCreationAndPutElementsToClassWith1intTreeMap()
    {
		ClassWith1intb classWith1int;
		clock_t start = clock();
		for (int i = TESTS_MAP; i != 0; i--)
        {
			TREE_MAP map1;
			for (int j = SIZE; j-- != 0;)
				map1[j] = classWith1int;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Creation of " << TESTS_MAP << " And Put " << SIZE << " Elements to map[" << SIZE << "]  TREE_MAP map1 for (int j = SIZE   j-- != 0  ) map1[j] = classWith1int ,, snapshot time," << executionTime << "\n";
        return executionTime;
    }


		/**
     * testIterationOfStringMap
     */
    static long testIterationOfClassWith1intTreeMap()
    {
		ClassWith1intb classWith1int;
		TREE_MAP map1;
		for (int j = SIZE; j-- != 0;)
		    map1[j] = classWith1int;
		clock_t start = clock();
		for (int i = TESTS_MAP; i != 0; i--)
        {
			for (int j = SIZE; j-- != 0;)
            {
				ClassWith1intb s = map1.find(j)->second;
            }
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CollectionTest], Iteration of " << TESTS_MAP << " TREE_MAP("  << SIZE << ")  for (int j = SIZE   j-- != 0  ) ClassWith1intb s = map1.find(j)->second  ,, snapshot time," << executionTime << "\n";
        return executionTime;
    }



static int main(int size, char** args)
	{
		int nbTests = CollectionTest::NB_TESTS;
		int nbOfExclusionMinMax = CollectionTest::NB_OF_EXCLUSION_MIN_MAX;


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
				(CollectionTest::testCreationOfClassWith1intArray());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Creation of " << CollectionTest::TESTS_LIST << " ClassWith1intArray[" << CollectionTest::SIZE <<"] final ClassWith1int[] list = new ClassWith1int[SIZE] ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
		
		

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testAddElementsInClassWith1intArray());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], " << CollectionTest::TESTS_LIST << " Tests of Add " << CollectionTest::SIZE << " Elements to ClassWith1intArray[" << CollectionTest::SIZE << "]  for (int j = SIZE   j-- != 0  ) list[j] = classWith1int,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
		executionTimes.clear();

		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testCreationAndAddElementsToClassWith1intArray());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Creation of " << TESTS_LIST << " and Add " << SIZE << " Elements to ClassWith1intArray[" << SIZE << "]  for (int j = SIZE   j-- != 0  ) ClassWith1intb list[SIZE]  list[j] = classWith1int,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testIterationOfClassWith1intArrayWithForwardLoop());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Iteration of " << TESTS_LIST << " ClassWith1intArray[" << SIZE << "] With Forward Loop   for (int k = 0   k < list.length   k++) count += list[k].getInt1()  ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
		executionTimes.push_back
				(CollectionTest::testIterationOfClassWith1intArrayWithReverseLoopDifferentZero());
		std::sort(executionTimes.begin(), executionTimes.end());
				cout << "[CollectionTest], Iteration of " << TESTS_LIST << " ClassWith1intArray[" << SIZE << "] With Reverse Loop Different Zero  for (int k = list.length   k != 0   k--) count += list[k - 1].getInt1()  ,, average time,"<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
		executionTimes.push_back
				(CollectionTest::testIterationOfClassWith1intArrayWithReverseLoopDifferentZeroDecrementInTest());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Iteration of " << TESTS_LIST << " ClassWith1intArray[" << SIZE << "] With Reverse Loop Different Zero Decrement In Test  for (int k = list.length   k-- != 0  ) count += list[k].getInt1()  ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
		executionTimes.push_back
				(CollectionTest::testCopyData());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout <<  "[CollectionTest], Copy data from an array to an array " << SIZE << ",, average time, " << CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

	    executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
		executionTimes.push_back
				(CollectionTest::testCopyDataOptimized());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout <<  "[CollectionTest], Copy data Optimized from an array to an array " << SIZE << ",, average time, " << CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		
		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testCreationOfClassWith1intList());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Creation of " << TESTS_LIST << " vector<ClassWith1int>[" << SIZE <<"] vector<ClassWith1int> list(SIZE) ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testAddElementsInClassWith1intList());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], " << TESTS_LIST << " Tests of Add " << SIZE << " Elements to vector<ClassWith1int>[" << SIZE << "]  for (int j = SIZE   j-- != 0  ) list.push_back(classWith1int)  ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testCreationAndAddElementsToClassWith1intList());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Creation of " << TESTS_LIST << " and Add " << SIZE << " Elements to vector<ClassWith1int>[" << SIZE << "]  for (int j = SIZE   j-- != 0  )  vector<ClassWith1int>  list(SIZE)  list.push_back(classWith1int),, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testIterationOfClassWith1intListWithForwardLoop());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Iteration of " << TESTS_LIST << " vector<ClassWith1int>[" << SIZE << "] With Forward Loop   for (int k = 0   k < list.length   k++) count += (list)[k].getInt1()  ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
		executionTimes.push_back
				(CollectionTest::testIterationOfClassWith1intListWithReverseLoopDifferentZero());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Iteration of " << TESTS_LIST << " vector<ClassWith1intb>[" << SIZE << "] With Reverse Loop Different Zero  for (int k = list.length   k != 0   k--)  count += (list)[k - 1].getInt1()  ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
		executionTimes.push_back
				(CollectionTest::testIterationOfClassWith1intListWithReverseLoopDifferentZeroDecrementInTest());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Iteration of " << TESTS_LIST << " vector<ClassWith1int>[" << SIZE << "] With Reverse Loop Different Zero Decrement In Test  for (int k = list.length   k-- != 0  ) count += (list)[k].getInt1()  ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testCreationOfClassWith1intHashMap());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Creation of " << CollectionTest::TESTS_MAP << " map[" << SIZE <<"] HASH_MAP map ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testPutElementsInClassWith1intHashMap());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], " << TESTS_MAP << " Tests of Put " << SIZE << " Elements to map[" << SIZE << "]  for (int j = SIZE   j-- != 0  ) map1[j] = classWith1int ,, average time, " << CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testCreationAndPutElementsToClassWith1intHashMap());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Creation of " << TESTS_MAP << " And Put " << SIZE << " Elements to map[" << SIZE << "] HASH_MAP map  for (int j = SIZE   j-- != 0  ) map1[j] = classWith1int) ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testIterationOfClassWith1intHashMap());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Iteration of " << TESTS_MAP << " HASH_MAP map1"  << SIZE << ")  for (int j = SIZE   j-- != 0  ) ClassWith1intb s = map1.find(j)->second ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";


		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testCreationOfClassWith1intTreeMap());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Creation of " << CollectionTest::TESTS_MAP << " map[" << CollectionTest::SIZE <<"] TREE_MAP map ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testPutElementsInClassWith1intTreeMap());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], " << TESTS_MAP << " Tests of Put " << SIZE << " Elements to map[" << SIZE << "]  for (int j = SIZE   j-- != 0  ) map1[j] = classWith1int  ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testCreationAndPutElementsToClassWith1intTreeMap());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Creation of " << TESTS_MAP << " And Put " << SIZE << " Elements to map[" << SIZE << "]  TREE_MAP map1 for (int j = SIZE   j-- != 0  ) map1[j] = classWith1int ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CollectionTest::testIterationOfClassWith1intTreeMap());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CollectionTest], Iteration of " << TESTS_MAP << " TREE_MAP("  << SIZE << ")  for (int j = SIZE   j-- != 0  ) ClassWith1intb s = map1.find(j)->second  ,, average time, "<< CollectionTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CollectionTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		return 0;
		
	}

};

#ifndef Centralized
string CollectionTest::BLANK = "";
string CollectionTest::SPACE = " ";
#endif



/*
int main(string* args, int size)
{
	CollectionTest::main(args, size);
}
*/
