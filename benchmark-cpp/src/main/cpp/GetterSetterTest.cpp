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
class ClassWith1StringGetterSetter
{
	private :
    string* string1;

	public:
	ClassWith1StringGetterSetter () 
	{
		this->string1 = new string("string1");
	}

	~ClassWith1StringGetterSetter () 
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
class ClassWith10StringGetterSetter
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
	ClassWith10StringGetterSetter () 
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

	~ClassWith10StringGetterSetter () 
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
class ClassWith1intGetterSetter
{
	private :
	int int1;

	public:
	ClassWith1intGetterSetter() 
	{
	}

	~ClassWith1intGetterSetter() 
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
class ClassWith10intGetterSetter
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
	ClassWith10intGetterSetter () 
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

	~ClassWith10intGetterSetter () 
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
class ClassWith1ClassWith10StringGetterSetter
{
	private :
    ClassWith10StringGetterSetter* classWith10String1;

	public:
	ClassWith1ClassWith10StringGetterSetter() 
	{
		this->classWith10String1 = new ClassWith10StringGetterSetter();
	}

	~ClassWith1ClassWith10StringGetterSetter() 
	{
		delete classWith10String1;
	}

    /**
     * getClassWith10String1.
     * 
     * @return
     */
    ClassWith10StringGetterSetter* getClassWith10String1()
    {
        return this->classWith10String1;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String1(ClassWith10StringGetterSetter* aClassWith10String1)
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
class ClassWith10ClassWith10StringGetterSetter
{
	private :
    ClassWith10StringGetterSetter* classWith10String1;
	ClassWith10StringGetterSetter* classWith10String2;
	ClassWith10StringGetterSetter* classWith10String3;
	ClassWith10StringGetterSetter* classWith10String4;
	ClassWith10StringGetterSetter* classWith10String5;
	ClassWith10StringGetterSetter* classWith10String6;
	ClassWith10StringGetterSetter* classWith10String7;
	ClassWith10StringGetterSetter* classWith10String8;
	ClassWith10StringGetterSetter* classWith10String9;
	ClassWith10StringGetterSetter* classWith10String10;

	public:
	ClassWith10ClassWith10StringGetterSetter () 
	{
		this->classWith10String1 = new ClassWith10StringGetterSetter();
		this->classWith10String2 = new ClassWith10StringGetterSetter();
		this->classWith10String3 = new ClassWith10StringGetterSetter();
		this->classWith10String4 = new ClassWith10StringGetterSetter();
		this->classWith10String5 = new ClassWith10StringGetterSetter();
		this->classWith10String6 = new ClassWith10StringGetterSetter();
		this->classWith10String7 = new ClassWith10StringGetterSetter();
		this->classWith10String8 = new ClassWith10StringGetterSetter();
		this->classWith10String9 = new ClassWith10StringGetterSetter();
		this->classWith10String10 = new ClassWith10StringGetterSetter();
	}

	~ClassWith10ClassWith10StringGetterSetter () 
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
    ClassWith10StringGetterSetter* getClassWith10String1()
    {
        return this->classWith10String1;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String1(ClassWith10StringGetterSetter* aClassWith10String1)
    {
        this->classWith10String1 = aClassWith10String1;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10StringGetterSetter* getClassWith10String2()
    {
        return this->classWith10String2;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String2(ClassWith10StringGetterSetter* aClassWith10String2)
    {
        this->classWith10String2 = aClassWith10String2;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10StringGetterSetter* getClassWith10String3()
    {
        return this->classWith10String3;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String3(ClassWith10StringGetterSetter* aClassWith10String3)
    {
        this->classWith10String3 = aClassWith10String3;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10StringGetterSetter* getClassWith10String4()
    {
        return this->classWith10String4;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String4(ClassWith10StringGetterSetter* aClassWith10String4)
    {
        this->classWith10String4 = aClassWith10String4;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10StringGetterSetter* getClassWith10String5()
    {
        return this->classWith10String5;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String5(ClassWith10StringGetterSetter* aClassWith10String5)
    {
        this->classWith10String5 = aClassWith10String5;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10StringGetterSetter* getClassWith10String6()
    {
        return this->classWith10String6;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String6(ClassWith10StringGetterSetter* aClassWith10String6)
    {
        this->classWith10String6 = aClassWith10String6;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10StringGetterSetter* getClassWith10String7()
    {
        return this->classWith10String7;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String7(ClassWith10StringGetterSetter* aClassWith10String7)
    {
        this->classWith10String7 = aClassWith10String7;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10StringGetterSetter* getClassWith10String8()
    {
        return this->classWith10String8;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String8(ClassWith10StringGetterSetter* aClassWith10String8)
    {
        this->classWith10String8 = aClassWith10String8;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10StringGetterSetter* getClassWith10String9()
    {
        return this->classWith10String9;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String9(ClassWith10StringGetterSetter* aClassWith10String9)
    {
        this->classWith10String9 = aClassWith10String9;
    }

	    /**
     * getString1.
     * 
     * @return
     */
    ClassWith10StringGetterSetter* getClassWith10String10()
    {
        return this->classWith10String10;
    }

    /**
     * setString1.
     * 
     * @param aString1
     */
    void setClassWith10String10(ClassWith10StringGetterSetter* aClassWith10String10)
    {
        this->classWith10String10 = aClassWith10String10;
    }
};



class GetterSetterTest 
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
     * TESTS
     */
    static const int NB_SETTER_GETTER_INT_TESTS  = 500 * 1000 * 1000;

    /**
     * TESTS
     */
    static const int NB_SETTER_GETTER_STRING_TESTS  = 200 * 1000 * 1000;


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
	long averageTime = GetterSetterTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
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
    static long testGetterClassWith1int()
    {
		// The use of an object on stack or with new is the same
		ClassWith1intGetterSetter* classWith1int = new ClassWith1intGetterSetter();
		classWith1int->setInt1(1);
        int count = 0;
        clock_t start = clock();
		for (int i = NB_SETTER_GETTER_INT_TESTS; i != 0; i--)
		{
			// Write to force cpp not to optimize the code, never executed
            if (i % 2 == 2)
			{
				classWith1int->setInt1(i);
			}
			count += classWith1int->getInt1();
		}
        clock_t end = clock();
		delete classWith1int;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[GetterSetterTest], Getter of ClassWith1int() test=" << NB_SETTER_GETTER_INT_TESTS << ", count=" << count << " classWith1int->getInt1()=" << classWith1int->getInt1() << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	    /**
     * testCreationOfClassWith1String
     */
    static long testSetterClassWith1int()
    {
		ClassWith1intGetterSetter* classWith1int = new ClassWith1intGetterSetter();
        int count = 0;
        clock_t start = clock();
		for (int i = NB_SETTER_GETTER_INT_TESTS; i != 0; i--)
		{
			// Write to force jre not to optimize the code
            if (i % 2 == 0)
			{
				classWith1int->setInt1(i);
			}
			else
			{
				classWith1int->setInt1(i - 1);
			}
		}
        clock_t end = clock();
		delete classWith1int;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[GetterSetterTest], Setter of ClassWith1int() test=" << NB_SETTER_GETTER_INT_TESTS << ", count=" << count << " classWith1int->getInt1()=" << classWith1int->getInt1() << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCreationOfClassWith1String
     */
    static long testSetterGetterClassWith1int()
    {
		ClassWith1intGetterSetter* classWith1int = new ClassWith1intGetterSetter();
        int count = 0;
        clock_t start = clock();
		for (int i = NB_SETTER_GETTER_INT_TESTS; i != 0; i--)
		{
			// Write to force jre not to optimize the code
            if (i % 2 == 0)
			{
				classWith1int->setInt1(i);
			}
			else
			{
				classWith1int->setInt1(i - 1);
			}
			count += classWith1int->getInt1();
		}

        clock_t end = clock();
		delete classWith1int;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[GetterSetterTest], Setter Getter of ClassWith1int() test=" << NB_SETTER_GETTER_INT_TESTS << ", count=" << count << " classWith1int->getInt1()=" << classWith1int->getInt1() << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	    /**
     * testCreationOfClassWith1String
     */
    static long testGetterClassWith10int()
    {
		ClassWith10intGetterSetter* classWith10int = new ClassWith10intGetterSetter();
        int count = 0;
        clock_t start = clock();
		for (int i = NB_SETTER_GETTER_INT_TESTS; i != 0; i--)
		{
			// Write to force cpp et jre not to optimize the code, never executed
            bool testAlwaysFalse = (i % 2 == 2);
            if (testAlwaysFalse)
            {
				classWith10int->setInt1(i);
			}
			count += classWith10int->getInt1();

			if (testAlwaysFalse)
            {
				classWith10int->setInt2(i);
			}
			count += classWith10int->getInt2();

			if (testAlwaysFalse)
            {
				classWith10int->setInt3(i);
			}
			count += classWith10int->getInt3();

			if (testAlwaysFalse)
            {
				classWith10int->setInt4(i);
			}
			count += classWith10int->getInt4();

			if (testAlwaysFalse)
            {
				classWith10int->setInt5(i);
			}
			count += classWith10int->getInt5();

			if (testAlwaysFalse)
            {
				classWith10int->setInt6(i);
			}
			count += classWith10int->getInt6();

			if (testAlwaysFalse)
            {
				classWith10int->setInt7(i);
			}
			count += classWith10int->getInt7();

			if (testAlwaysFalse)
            {
				classWith10int->setInt8(i);
			}
			count += classWith10int->getInt8();

			if (testAlwaysFalse)
            {
				classWith10int->setInt9(i);
			}
			count += classWith10int->getInt9();

			if (testAlwaysFalse)
            {
				classWith10int->setInt10(i);
			}
			count += classWith10int->getInt10();
		}

        clock_t end = clock();
		delete classWith10int;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[GetterSetterTest], Getter of ClassWith10int() test=" << NB_SETTER_GETTER_INT_TESTS << ", count=" << count << " classWith10int->getInt1()=" << classWith10int->getInt1() << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	    /**
     * testCreationOfClassWith1String
     */
    
	static long testSetterClassWith10int()
    {
		ClassWith10intGetterSetter* classWith10int = new ClassWith10intGetterSetter();
        int count = 0;
        clock_t start = clock();
		for (int i = NB_SETTER_GETTER_INT_TESTS; i != 0; i--)
		{
			// Write to force jre not to optimize the code
            if (i % 2 == 0)
			{
				classWith10int->setInt1(i);
				classWith10int->setInt2(i);
				classWith10int->setInt3(i);
				classWith10int->setInt4(i);
				classWith10int->setInt5(i);
				classWith10int->setInt6(i);
				classWith10int->setInt7(i);
				classWith10int->setInt8(i);
				classWith10int->setInt9(i);
				classWith10int->setInt10(i);
			}
			else
			{
				int j = i - 1;
				classWith10int->setInt1(j);
				classWith10int->setInt2(j);
				classWith10int->setInt3(j);
				classWith10int->setInt4(j);
				classWith10int->setInt5(j);
				classWith10int->setInt6(j);
				classWith10int->setInt7(j);
				classWith10int->setInt8(j);
				classWith10int->setInt9(j);
				classWith10int->setInt10(j);
			}
		}
        clock_t end = clock();
		delete classWith10int;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[GetterSetterTest], Setter of ClassWith10int() test=" << NB_SETTER_GETTER_INT_TESTS << ", count=" << count << " classWith10int->getInt1()=" << classWith10int->getInt1() << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	    /**
     * testCreationOfClassWith1String
     */
    static long testSetterGetterClassWith10int()
    {
		ClassWith10intGetterSetter* classWith10int = new ClassWith10intGetterSetter();
        int count = 0;
        clock_t start = clock();
		for (int i = NB_SETTER_GETTER_INT_TESTS; i != 0; i--)
		{
            // Write to force jre not to optimize the code
            if (i % 2 == 0)
			{
				classWith10int->setInt1(i);
				classWith10int->setInt2(i);
				classWith10int->setInt3(i);
				classWith10int->setInt4(i);
				classWith10int->setInt5(i);
				classWith10int->setInt6(i);
				classWith10int->setInt7(i);
				classWith10int->setInt8(i);
				classWith10int->setInt9(i);
				classWith10int->setInt10(i);
			}
			else
			{
				int j = i - 1;
				classWith10int->setInt1(j);
				classWith10int->setInt2(j);
				classWith10int->setInt3(j);
				classWith10int->setInt4(j);
				classWith10int->setInt5(j);
				classWith10int->setInt6(j);
				classWith10int->setInt7(j);
				classWith10int->setInt8(j);
				classWith10int->setInt9(j);
				classWith10int->setInt10(j);
			}
			count += (classWith10int->getInt1()
				+ classWith10int->getInt2()
				+ classWith10int->getInt3()
				+ classWith10int->getInt4()
				+ classWith10int->getInt5()
				+ classWith10int->getInt6()
				+ classWith10int->getInt7()
				+ classWith10int->getInt8()
				+ classWith10int->getInt9()
				+ classWith10int->getInt10());
				
		}

        clock_t end = clock();
		delete classWith10int;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[GetterSetterTest], Setter Getter of ClassWith10int() test=" << NB_SETTER_GETTER_INT_TESTS << ", count=" << count << " classWith10int->getInt1()=" << classWith10int->getInt1() << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }


	    /**
     * testCreationOfClassWith1String
     */
    static long testGetterClassWith1String()
    {
		ClassWith1StringGetterSetter* classWith1String = new ClassWith1StringGetterSetter();
        int count = 0;
		string* s1 = new string("1");
        clock_t start = clock();
		for (int i = NB_SETTER_GETTER_STRING_TESTS; i != 0; i--)
		{
			// Write to force cpp not to optimize the code, never executed
            if (i % 2 == 2)
			{
				classWith1String->setString1(s1);
			}

			count += classWith1String->getString1()->length();
		}
        clock_t end = clock();
		delete classWith1String;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[GetterSetterTest], Getter of ClassWith1String() test=" << NB_SETTER_GETTER_STRING_TESTS << ", count=" << count << " classWith1String->getString1()=" << classWith1String->getString1() << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	 /**
     * testCreationOfClassWith1String
     */
    static long testSetterClassWith1String()
    {
		ClassWith1StringGetterSetter* classWith1String = new ClassWith1StringGetterSetter();
        int count = 0;
		string* string1 = new string("string1");
		string* string2 = new string("string2");
        clock_t start = clock();
		for (int i = NB_SETTER_GETTER_STRING_TESTS; i != 0; i--)
		{
			// Write to force jre not to optimize the code
            if (i % 2 == 0)
			{
				classWith1String->setString1(string1);
			}
			else
			{
				classWith1String->setString1(string2);
			}
		}
        clock_t end = clock();
		delete classWith1String;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[GetterSetterTest], Setter of ClassWith1String() test=" << NB_SETTER_GETTER_STRING_TESTS << ", count=" << count << " classWith1String->getString1()=" << classWith1String->getString1() << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	    /**
     * testCreationOfClassWith1String
     */
    static long testSetterGetterClassWith1String()
    {
		ClassWith1StringGetterSetter* classWith1String = new ClassWith1StringGetterSetter();
        int count = 0;
		string* string1 = new string("string1");
		string* string2 = new string("string2");
        clock_t start = clock();
		for (int i = NB_SETTER_GETTER_STRING_TESTS; i != 0; i--)
		{
			// Write to force jre not to optimize the code
            if (i % 2 == 0)
			{
				classWith1String->setString1(string1);
			}
			else
			{
				classWith1String->setString1(string2);
			}
			count += classWith1String->getString1()->length();
		}
        clock_t end = clock();
		delete classWith1String;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[GetterSetterTest], Setter Getter of ClassWith1String() test=" << NB_SETTER_GETTER_STRING_TESTS << ", count=" << count << " classWith1String->getString1()=" << classWith1String->getString1() << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }
	

	/**
     * testCreationOfClassWith1String
     */
    static long testGetterClassWith10String()
    {
		ClassWith10StringGetterSetter* classWith10String = new ClassWith10StringGetterSetter();
        int count = 0;
		string* s1 = new string("s1");
		string* s2 = new string("s2");
		string* s3 = new string("s3");
		string* s4 = new string("s7");
		string* s5 = new string("s5");
		string* s6 = new string("s6");
		string* s7 = new string("s7");
		string* s8 = new string("s8");
		string* s9 = new string("s9");
		string* s10 = new string("s10");
        clock_t start = clock();
		for (int i = NB_SETTER_GETTER_STRING_TESTS; i != 0; i--)
		{
			// Write to force cpp and jre not to optimize the code, never executed
			bool testAlwaysFalse = (i % 2 == 2);
            if (testAlwaysFalse)
            {
				classWith10String->setString1(s1);
			}
			count += classWith10String->getString1()->length();

			if (testAlwaysFalse)
            {
				classWith10String->setString2(s2);
			}
			count += classWith10String->getString2()->length();

			if (testAlwaysFalse)
            {
				classWith10String->setString3(s3);
			}
			count += classWith10String->getString3()->length();

			if (testAlwaysFalse)
            {
				classWith10String->setString4(s4);
			}
			count += classWith10String->getString4()->length();

			if (testAlwaysFalse)
            {
				classWith10String->setString5(s5);
			}
			count += classWith10String->getString5()->length();

			if (testAlwaysFalse)
            {
				classWith10String->setString6(s6);
			}
			count += classWith10String->getString6()->length();

			if (testAlwaysFalse)
            {
				classWith10String->setString7(s7);
			}
			count += classWith10String->getString7()->length();

			if (testAlwaysFalse)
            {
				classWith10String->setString8(s8);
			}
			count += classWith10String->getString8()->length();

			if (testAlwaysFalse)
            {
				classWith10String->setString9(s9);
			}
			count += classWith10String->getString9()->length();

			if (testAlwaysFalse)
            {
				classWith10String->setString10(s10);
			}
			count += classWith10String->getString10()->length();
		}
        clock_t end = clock();
		delete classWith10String;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[GetterSetterTest], Getter of ClassWith10String() test=" << NB_SETTER_GETTER_STRING_TESTS << ", count=" << count << " classWith10String->getString1()=" << classWith10String->getString1() << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	 /**
     * testCreationOfClassWith1String
     */
    static long testSetterClassWith10String()
    {
		ClassWith10StringGetterSetter* classWith10String = new ClassWith10StringGetterSetter();
        int count = 0;
		string* string1 = new string("string1");
		string* string2 = new string("string2");
		string* string3 = new string("string3");
		string* string4 = new string("string4");
		string* string5 = new string("string5");
		string* string6 = new string("string6");
		string* string7 = new string("string7");
		string* string8 = new string("string8");
		string* string9 = new string("string9");
		string* string10 = new string("string10");
        clock_t start = clock();
		for (int i = NB_SETTER_GETTER_STRING_TESTS; i != 0; i--)
		{
			// Write to force jre not to optimize the code
            if (i % 2 == 0)
			{
				classWith10String->setString1(string1);
				classWith10String->setString2(string2);
				classWith10String->setString3(string3);
				classWith10String->setString4(string4);
				classWith10String->setString5(string5);
				classWith10String->setString6(string6);
				classWith10String->setString7(string7);
				classWith10String->setString8(string8);
				classWith10String->setString9(string9);
				classWith10String->setString10(string10);
			}
			else
			{
				classWith10String->setString1(string2);
				classWith10String->setString2(string3);
				classWith10String->setString3(string4);
				classWith10String->setString4(string5);
				classWith10String->setString5(string6);
				classWith10String->setString6(string7);
				classWith10String->setString7(string8);
				classWith10String->setString8(string9);
				classWith10String->setString9(string10);
				classWith10String->setString10(string1);
			}

		}
        clock_t end = clock();
		delete classWith10String;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[GetterSetterTest], Setter of ClassWith1String() test=" << NB_SETTER_GETTER_STRING_TESTS << ", count=" << count << " classWith10String->getString1()=" << classWith10String->getString1() << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

	/**
     * testCreationOfClassWith1String
     */
    static long testSetterGetterClassWith10String()
    {
		ClassWith10StringGetterSetter* classWith10String = new ClassWith10StringGetterSetter();
        int count = 0;
		string* string1 = new string("string1");
		string* string2 = new string("string2");
		string* string3 = new string("string3");
		string* string4 = new string("string4");
		string* string5 = new string("string5");
		string* string6 = new string("string6");
		string* string7 = new string("string7");
		string* string8 = new string("string8");
		string* string9 = new string("string9");
		string* string10 = new string("string10");
        clock_t start = clock();
		for (int i = NB_SETTER_GETTER_STRING_TESTS; i != 0; i--)
		{
			// Write to force jre not to optimize the code
            if (i % 2 == 0)
			{
				classWith10String->setString1(string1);
				classWith10String->setString2(string2);
				classWith10String->setString3(string3);
				classWith10String->setString4(string4);
				classWith10String->setString5(string5);
				classWith10String->setString6(string6);
				classWith10String->setString7(string7);
				classWith10String->setString8(string8);
				classWith10String->setString9(string9);
				classWith10String->setString1(string10);
			}
			else
			{
				classWith10String->setString1(string2);
				classWith10String->setString2(string3);
				classWith10String->setString3(string4);
				classWith10String->setString4(string5);
				classWith10String->setString5(string6);
				classWith10String->setString6(string7);
				classWith10String->setString7(string8);
				classWith10String->setString8(string9);
				classWith10String->setString9(string10);
				classWith10String->setString1(string1);
			}
			count += classWith10String->getString1()->length() 
				+ classWith10String->getString2()->length() 
				+ classWith10String->getString3()->length() 
				+ classWith10String->getString4()->length() 
				+ classWith10String->getString5()->length() 
				+ classWith10String->getString6()->length() 
				+ classWith10String->getString7()->length() 
				+ classWith10String->getString8()->length() 
				+ classWith10String->getString9()->length() 
				+ classWith10String->getString10()->length();
		}
        clock_t end = clock();
		delete classWith10String;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[GetterSetterTest], Setter Getter of ClassWith1String() test=" << NB_SETTER_GETTER_STRING_TESTS << ", count=" << count << " classWith10String->getString1()=" << classWith10String->getString1() << ", snapshot time," << executionTime << "\n";
        return executionTime;
    }

static int main(int size, char** args)
	{
		int nbTests = GetterSetterTest::NB_TESTS;
		int nbOfExclusionMinMax = GetterSetterTest::NB_OF_EXCLUSION_MIN_MAX;
	    
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
				(GetterSetterTest::testGetterClassWith1int());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[GetterSetterTest], Getter of ClassWith1int() test=" << GetterSetterTest::NB_SETTER_GETTER_INT_TESTS << ",, average time," << GetterSetterTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << GetterSetterTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
	    
		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(GetterSetterTest::testSetterClassWith1int());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[GetterSetterTest], Setter of ClassWith1int() test=" << GetterSetterTest::NB_SETTER_GETTER_INT_TESTS << ",, average time," << GetterSetterTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << GetterSetterTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(GetterSetterTest::testSetterGetterClassWith1int());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[GetterSetterTest], Setter Getter of ClassWith1int() test=" << GetterSetterTest::NB_SETTER_GETTER_INT_TESTS << ",, average time," << GetterSetterTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << GetterSetterTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(GetterSetterTest::testGetterClassWith10int());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[GetterSetterTest], Getter of ClassWith10int() test=" << GetterSetterTest::NB_SETTER_GETTER_INT_TESTS << ",, average time," << GetterSetterTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << GetterSetterTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
	  
		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(GetterSetterTest::testSetterClassWith10int());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[GetterSetterTest], Setter of ClassWith10int() test=" << GetterSetterTest::NB_SETTER_GETTER_INT_TESTS << ",, average time," << GetterSetterTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << GetterSetterTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(GetterSetterTest::testSetterGetterClassWith10int());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[GetterSetterTest], Setter Getter of ClassWith10int() test=" << GetterSetterTest::NB_SETTER_GETTER_INT_TESTS << ",, average time," << GetterSetterTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << GetterSetterTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
		executionTimes.push_back
				(GetterSetterTest::testGetterClassWith1String());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[GetterSetterTest], Getter of ClassWith1String() test=" << GetterSetterTest::NB_SETTER_GETTER_STRING_TESTS << ",, average time," << GetterSetterTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << GetterSetterTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
	    
		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(GetterSetterTest::testSetterClassWith1String());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[GetterSetterTest], Setter of ClassWith1String() test=" << GetterSetterTest::NB_SETTER_GETTER_STRING_TESTS << ",, average time," << GetterSetterTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << GetterSetterTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(GetterSetterTest::testSetterGetterClassWith1String());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[GetterSetterTest], Setter Getter of ClassWith1String() test=" << GetterSetterTest::NB_SETTER_GETTER_STRING_TESTS << ",, average time," << GetterSetterTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << GetterSetterTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
		executionTimes.push_back
				(GetterSetterTest::testGetterClassWith10String());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[GetterSetterTest], Getter of ClassWith10String() test=" << GetterSetterTest::NB_SETTER_GETTER_STRING_TESTS << ",, average time," << GetterSetterTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << GetterSetterTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
	    
		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(GetterSetterTest::testSetterClassWith10String());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[GetterSetterTest], Setter of ClassWith10String() test=" << GetterSetterTest::NB_SETTER_GETTER_STRING_TESTS << ",, average time," << GetterSetterTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << GetterSetterTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(GetterSetterTest::testSetterGetterClassWith10String());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[GetterSetterTest], Setter Getter of ClassWith10String() test=" << GetterSetterTest::NB_SETTER_GETTER_STRING_TESTS << ",, average time," << GetterSetterTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << GetterSetterTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";


		return 0;
	}

};



/*
int main(string* args, int size)
{
	GetterSetterTest::main(args, size);
}
*/
