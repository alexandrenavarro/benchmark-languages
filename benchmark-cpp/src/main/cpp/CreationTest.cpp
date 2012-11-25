#include <time.h>
#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;


/**
 * ClassWith1String.
 * 
 * @author Alexandre
 * 
 */
class ClassWith1String
{
	private :
    string* string1;

	public:
	ClassWith1String () 
	{
		this->string1 = new string("string1");
	}

	~ClassWith1String () 
	{
		delete string1;
	}

    /**
     * getString1.
     * 
     * @return
     */
    string* getString1()
    {
        return this->string1;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setString1(string* aString1)
    {
        this->string1 = aString1;
    }
};


/**
 * ClassWith1String.
 * 
 * @author Alexandre
 * 
 */
class ClassWith10String
{
	private :
    string* string1;
	string* string2;
	string* string3;
	string* string4;
	string* string5;
	string* string6;
	string* string7;
	string* string8;
	string* string9;
	string* string10;

	public:
	ClassWith10String () 
	{
		this->string1 = new string("string1");
		this->string2 = new string("string2");
		this->string3 = new string("string3");
		this->string4 = new string("string4");
		this->string5 = new string("string5");
		this->string6 = new string("string6");
		this->string7 = new string("string7");
		this->string8 = new string("string8");
		this->string9 = new string("string9");
		this->string10 = new string("string10");
	}

	~ClassWith10String () 
	{
		delete string1;
		delete string2;
		delete string3;
		delete string4;
		delete string5;
		delete string6;
		delete string7;
		delete string8;
		delete string9;
		delete string10;
	}

    /**
     * getString1.
     * 
     * @return
     */
    string* getString1()
    {
        return this->string1;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setString1(string* aString1)
    {
        this->string1 = aString1;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    string* getString2()
    {
        return this->string2;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setString2(string* aString2)
    {
        this->string2 = aString2;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    string* getString3()
    {
        return this->string3;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setString3(string* aString3)
    {
        this->string3 = aString3;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    string* getString4()
    {
        return this->string4;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setString4(string* aString4)
    {
        this->string4 = aString4;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    string* getString5()
    {
        return this->string5;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setString5(string* aString5)
    {
        this->string5 = aString5;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    string* getString6()
    {
        return this->string6;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setString6(string* aString6)
    {
        this->string6 = aString6;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    string* getString7()
    {
        return this->string7;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setString7(string* aString7)
    {
        this->string7 = aString7;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    string* getString8()
    {
        return this->string8;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setString8(string* aString8)
    {
        this->string8 = aString8;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    string* getString9()
    {
        return this->string9;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setString9(string* aString9)
    {
        this->string9 = aString9;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    string* getString10()
    {
        return this->string10;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setString10(string* aString10)
    {
        this->string10 = aString10;
    }
};



/**
 * ClassWith1Int.
 * 
 * @author Alexandre
 * 
 */
class ClassWith1Int
{
	private :
    int int1;

	public:
	ClassWith1Int() 
	{
		this->int1 = 1;
	}

	~ClassWith1Int() 
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



/**
 * ClassWith1SClassWith10Inttring.
 * 
 * @author Alexandre
 * 
 */
class ClassWith10Int
{
	private :
    int int1;
	int int2;
	int int3;
	int int4;
	int int5;
	int int6;
	int int7;
	int int8;
	int int9;
	int int10;

	public:
	ClassWith10Int () 
	{
		this->int1 = 1;
		this->int2 = -1;
		this->int3 = 3;
		this->int4 = -3;
		this->int5 = 5;
		this->int6 = -5;
		this->int7 = 7;
		this->int8 = -7;
		this->int9 = 9;
		this->int10 = 9;
	}

	~ClassWith10Int () 
	{
	}

    /**
     * getString1.
     * 
     * @return
     */
    int getInt1()
    {
        return this->int1;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setInt1(int aInt1)
    {
        this->int1 = aInt1;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    int getInt2()
    {
        return this->int2;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setInt2(int aInt2)
    {
        this->int2 = aInt2;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    int getInt3()
    {
        return this->int3;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setInt3(int aInt3)
    {
        this->int3 = aInt3;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    int getInt4()
    {
        return this->int4;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setInt4(int aInt4)
    {
        this->int4 = aInt4;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    int getInt5()
    {
        return this->int5;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setInt5(int aInt5)
    {
        this->int5 = aInt5;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    int getInt6()
    {
        return this->int6;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setInt6(int aInt6)
    {
        this->int6 = aInt6;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    int getInt7()
    {
        return this->int7;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setInt7(int aInt7)
    {
        this->int7 = aInt7;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    int getInt8()
    {
        return this->int8;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setInt8(int aInt8)
    {
        this->int8 = aInt8;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    int getInt9()
    {
        return this->int9;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setInt9(int aInt9)
    {
        this->int9 = aInt9;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    int getInt10()
    {
        return this->int10;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setInt10(int aInt10)
    {
        this->int10 = aInt10;
    }
};


/**
 * ClassWith1ClassWith10String.
 * 
 * @author Alexandre
 * 
 */
class ClassWith1ClassWith10String
{
	private :
    ClassWith10String* classWith10String1;

	public:
	ClassWith1ClassWith10String() 
	{
		this->classWith10String1 = new ClassWith10String();
	}

	~ClassWith1ClassWith10String() 
	{
		delete classWith10String1;
	}

    /**
     * getClassWith10String1.
     * 
     * @return
     */
    ClassWith10String* getClassWith10String1()
    {
        return this->classWith10String1;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String1(ClassWith10String* aClassWith10String1)
    {
        this->classWith10String1 = aClassWith10String1;
    }
};


/**
 * ClassWith1String.
 * 
 * @author Alexandre
 * 
 */
class ClassWith10ClassWith10String
{
	private :
    ClassWith10String* classWith10String1;
	ClassWith10String* classWith10String2;
	ClassWith10String* classWith10String3;
	ClassWith10String* classWith10String4;
	ClassWith10String* classWith10String5;
	ClassWith10String* classWith10String6;
	ClassWith10String* classWith10String7;
	ClassWith10String* classWith10String8;
	ClassWith10String* classWith10String9;
	ClassWith10String* classWith10String10;

	public:
	ClassWith10ClassWith10String () 
	{
		this->classWith10String1 = new ClassWith10String();
		this->classWith10String2 = new ClassWith10String();
		this->classWith10String3 = new ClassWith10String();
		this->classWith10String4 = new ClassWith10String();
		this->classWith10String5 = new ClassWith10String();
		this->classWith10String6 = new ClassWith10String();
		this->classWith10String7 = new ClassWith10String();
		this->classWith10String8 = new ClassWith10String();
		this->classWith10String9 = new ClassWith10String();
		this->classWith10String10 = new ClassWith10String();
	}

	~ClassWith10ClassWith10String () 
	{
		delete classWith10String1;
		delete classWith10String2;
		delete classWith10String3;
		delete classWith10String4;
		delete classWith10String5;
		delete classWith10String6;
		delete classWith10String7;
		delete classWith10String8;
		delete classWith10String9;
		delete classWith10String10;
	}

    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10String* getClassWith10String1()
    {
        return this->classWith10String1;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String1(ClassWith10String* aClassWith10String1)
    {
        this->classWith10String1 = aClassWith10String1;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10String* getClassWith10String2()
    {
        return this->classWith10String2;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String2(ClassWith10String* aClassWith10String2)
    {
        this->classWith10String2 = aClassWith10String2;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10String* getClassWith10String3()
    {
        return this->classWith10String3;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String3(ClassWith10String* aClassWith10String3)
    {
        this->classWith10String3 = aClassWith10String3;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10String* getClassWith10String4()
    {
        return this->classWith10String4;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String4(ClassWith10String* aClassWith10String4)
    {
        this->classWith10String4 = aClassWith10String4;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10String* getClassWith10String5()
    {
        return this->classWith10String5;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String5(ClassWith10String* aClassWith10String5)
    {
        this->classWith10String5 = aClassWith10String5;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10String* getClassWith10String6()
    {
        return this->classWith10String6;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String6(ClassWith10String* aClassWith10String6)
    {
        this->classWith10String6 = aClassWith10String6;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10String* getClassWith10String7()
    {
        return this->classWith10String7;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String7(ClassWith10String* aClassWith10String7)
    {
        this->classWith10String7 = aClassWith10String7;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10String* getClassWith10String8()
    {
        return this->classWith10String8;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String8(ClassWith10String* aClassWith10String8)
    {
        this->classWith10String8 = aClassWith10String8;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10String* getClassWith10String9()
    {
        return this->classWith10String9;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String9(ClassWith10String* aClassWith10String9)
    {
        this->classWith10String9 = aClassWith10String9;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10String* getClassWith10String10()
    {
        return this->classWith10String10;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String10(ClassWith10String* aClassWith10String10)
    {
        this->classWith10String10 = aClassWith10String10;
    }
};



class CreationTest 
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
    static const int NB_CREATION_TESTS       = 50 * 1000 * 1000;

    /**
     * NB_CREATION_TESTS
     */
    static const int NB_CREATION_INT_TESTS   = 2000 * 1000 * 1000;

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
	long averageTime = CreationTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
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
    static long testCreationOfClassWith1String()
    {
        clock_t start = clock();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
			ClassWith1String* classWith1String = new ClassWith1String();
			delete classWith1String;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CreationTest], Creation of " << NB_CREATION_TESTS << " new ClassWith1String(),, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCreationOfClassWith10String
     */
    static long testCreationOfClassWith10String()
    {
        clock_t start = clock();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
			ClassWith10String* classWith10String = new ClassWith10String();
			delete classWith10String;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CreationTest], Creation of " << NB_CREATION_TESTS << " new ClassWith10String(),, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCreationOfClassWith1Int
     */
    static long testCreationOfClassWith1Int()
    {
        clock_t start = clock();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
			ClassWith1Int* classWith1Int = new ClassWith1Int();
			delete classWith1Int;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CreationTest], Creation of " << NB_CREATION_TESTS << " new ClassWith1Int(),, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCreationOfClassWith10Int
     */
    static long testCreationOfClassWith10Int()
    {
        clock_t start = clock();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
			ClassWith10Int* classWith10Int = new ClassWith10Int();
			delete classWith10Int;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CreationTest], Creation of " << NB_CREATION_TESTS << " new ClassWith10Int(),, snapshot time," << executionTime << "\n";
        return executionTime;
    }

		/**
     * testCreationOfClassWith1ClassWith10String
     */
    static long testCreationOfClassWith1ClassWith10String()
    {
        clock_t start = clock();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
			ClassWith1ClassWith10String* classWith1ClassWith10String = new ClassWith1ClassWith10String();
			delete classWith1ClassWith10String;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CreationTest], Creation of " << NB_CREATION_TESTS << " new ClassWith1ClassWith10String(),, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCreationOfClassWith10Int
     */
    static long testCreationOfClassWith10ClassWith10String()
    {
        clock_t start = clock();
        for (int i = NB_CREATION_TESTS; i != 0; i--)
        {
			ClassWith10ClassWith10String* classWith10ClassWith10String = new ClassWith10ClassWith10String();
			delete classWith10ClassWith10String;
        }
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[CreationTest], Creation of " << NB_CREATION_TESTS << " new ClassWith10ClassWith10String(),, snapshot time," << executionTime << "\n";
        return executionTime;
    }

	
static int main(int size, char** args)
	{
		int nbTests = CreationTest::NB_TESTS;
		int nbOfExclusionMinMax = CreationTest::NB_OF_EXCLUSION_MIN_MAX;
	    
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
				(CreationTest::testCreationOfClassWith1String());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CreationTest], Creation of " << CreationTest::NB_CREATION_TESTS << " new ClassWith1String(),, average time," << CreationTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CreationTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
	       
		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CreationTest::testCreationOfClassWith10String());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CreationTest], Creation of " << CreationTest::NB_CREATION_TESTS << " new ClassWith10String(),, average time," << CreationTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CreationTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CreationTest::testCreationOfClassWith1Int());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CreationTest], Creation of " << CreationTest::NB_CREATION_TESTS << " new ClassWith1Int(),, average time," << CreationTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CreationTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
	       
		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CreationTest::testCreationOfClassWith10Int());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CreationTest], Creation of " << CreationTest::NB_CREATION_TESTS << " new ClassWith10Int(),, average time," << CreationTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CreationTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CreationTest::testCreationOfClassWith1ClassWith10String());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CreationTest], Creation of " << CreationTest::NB_CREATION_TESTS << " new ClassWith1ClassWith10String(),, average time," << CreationTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CreationTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
	       
		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(CreationTest::testCreationOfClassWith10ClassWith10String());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[CreationTest], Creation of " << CreationTest::NB_CREATION_TESTS << " new ClassWith10ClassWith10String(),, average time," << CreationTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << CreationTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		return 0;
	}


};



	


/*
int main(string* args, int size)
{
	CreationTest::main(args, size);
}
*/
