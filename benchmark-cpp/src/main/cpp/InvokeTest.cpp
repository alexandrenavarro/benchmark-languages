#include <time.h>
#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;

class Invoke
{
	public:
		virtual string* echo(string* s) = 0;
		virtual string* echoWithGetVariable() = 0;
		virtual string* echoWithSetVariable(string* s) = 0;
        virtual void setEchoGet(string* s) = 0;

};

class InvokeImpl : public Invoke
{

	private: 
		string*       variable;
		string*       echoSet;
		string*       echoGet;

	public:
		//static string* STATIC_VARIABLE = " ";

	InvokeImpl () 
	{
		this->variable = new string(" ");
		this->echoGet = new string("echo ");
		this->echoSet = new string("echo ");
	}

	~InvokeImpl () 
	{
		delete variable;
		delete echoSet;
		delete echoGet;
	}

	/**
     * echoStatic.
     * 
     * @param s
     * @return
     */
    static string* echoStatic(string* s)
    {
        return s;
    }

	/**
     * echoStatic.
     * 
     * @param s
     * @return
     */
    static int echoStatic(int s)
    {
        return s;
    }

	/**
     * echo.
     * 
     * @param s
     * @return
     */
	string* echo(string* s)
    {
        return s;
    }

	/**
     * echoWithGetVariable.
     * 
     * @return
     */
	string* echoWithGetVariable()
    {
        return this->echoGet;
    }

	/**
     * echoWithGetVariable.
     * 
     * @return
     */
	string* echoWithSetVariable(string* s)
    {
        this->echoSet = s;
        return s;
    }

	
	void setEchoGet(string* s)
	{
		this->echoGet = s;
	}

};


class InvokeInstance
{

	private: 
		string*       variable;
		string*       echoSet;
		string*       echoGet;

	public:
		//static string* STATIC_VARIABLE = " ";

	InvokeInstance() 
	{
		this->variable = new string(" ");
		this->echoGet = new string("echo ");
		this->echoSet = new string("echo ");
	}

	~InvokeInstance() 
	{
		delete variable;
		delete echoSet;
		delete echoGet;
	}

	/**
     * echoStatic.
     * 
     * @param s
     * @return
     */
    static string* echoStatic(string* s)
    {
        return s;
    }

	/**
     * echoStatic.
     * 
     * @param s
     * @return
     */
    static int echoStatic(int s)
    {
        return s;
    }

	/**
     * echo.
     * 
     * @param s
     * @return
     */
	string* echo(string* s)
    {
        return s;
    }

	/**
     * echoWithGetVariable.
     * 
     * @return
     */
	string* echoWithGetVariable()
    {
        return this->echoGet;
    }

	/**
     * echoWithGetVariable.
     * 
     * @return
     */
	string* echoWithSetVariable(string* s)
    {
        this->echoSet = s;
        return s;
    }

	void setEchoGet(string* s)
	{
		this->echoGet = s;
	}

};


class InvokeTest 
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
     * NB_INVOKE_TESTS
     */
    static const int NB_INVOKE_TESTS         = 2 * 1000 * 1000 * 1000;
    
    /**
     * NB_INVOKE_GETTER_SETTER_TESTS
     */
    static const int NB_INVOKE_GETTER_TESTS  = 2 * 1000 * 1000 * 1000;
    
    /**
     * NB_INVOKE_GETTER_SETTER_TESTS
     */
    static const int NB_INVOKE_SETTER_TESTS  = 2 * 1000 * 1000 * 1000;


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
	long averageTime = InvokeTest::averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
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
    static long testInvokeOfAStaticMethodString()
    {
        int count = 0;
		string* string1 = new string("1");
		string* string0 = new string("0");
        clock_t start = clock();
		for (int i = NB_INVOKE_TESTS; i != 0; i--)
		{
			if (i % 2 == 0)
            {
                count = count + InvokeImpl::echoStatic(string1)->length();
            }
            else
            {
                count = count + InvokeImpl::echoStatic(string0)->length();
            }
		}
		delete string1;
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[InvokeTest], Invoke Of A Static Method String " << NB_INVOKE_TESTS << " InvokeImpl.echoStatic(\"\").length(), count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}

	    /**
     * testCreationOfClassWith1String
     */
    static long testInvokeOfAStaticMethodInt()
    {
        int count = 0;
        clock_t start = clock();
		for (int i = NB_INVOKE_TESTS; i != 0; i--)
		{
			count = count + InvokeImpl::echoStatic(i);
		}
        clock_t end = clock();
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[InvokeTest], Invoke Of A Static Method Int " << NB_INVOKE_TESTS << " InvokeImpl.echoStatic(\"\").length(), count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}

	    /**
     * testCreationOfClassWith1String
     */
    static long testInvokeOfAnInstanceMethodWithInterface()
    {
        int count = 0;
		string* string1 = new string("1");
		string* string2 = new string("0");
		Invoke* invoke = new InvokeImpl();
        clock_t start = clock();
		for (int i = NB_INVOKE_TESTS; i != 0; i--)
		{
			// Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
				count = count + invoke->echo(string1)->length();
			}
			else
			{
				count = count + invoke->echo(string2)->length();
			}
		}
        clock_t end = clock();
		delete string1;
		delete invoke;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[InvokeTest], Invoke Of An Instance Method With Interface " << NB_INVOKE_TESTS << " invoke.echo(\"1\").length(), count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}


	/**
     * testCreationOfClassWith1String
     */
	
    static long testInvokeOfAnInstanceMethodWithGetInstanceVariableWithInterface()
    {
        int count = 0;
		string* string1 = new string("1");
		Invoke* invoke = new InvokeImpl();
        clock_t start = clock();
		for (int i = NB_INVOKE_TESTS; i != 0; i--)
		{
			// Write to force cpp not to optimize the code, never executed
			if ( i % 2 == 2 )
			{
				invoke->setEchoGet(string1);
			}

			count = count + invoke->echoWithGetVariable()->length();
		}
        clock_t end = clock();
		delete invoke;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[InvokeTest], Invoke Of An Instance Method With Get Instance Variable With Interface " << NB_INVOKE_TESTS << " invoke.echo(\"1\").length(), count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}

		/**
     * testCreationOfClassWith1String
     */
	
    static long testInvokeOfAnInstanceMethodWithSetInstanceVariableWithInterface()
    {
        int count = 0;
		string* string1 = new string("1");
		string* string2 = new string("0");
		Invoke* invoke = new InvokeImpl();
        clock_t start = clock();
		for (int i = NB_INVOKE_TESTS; i != 0; i--)
		{
			// Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
				count = count + invoke->echoWithSetVariable(string1)->length();
			}
			else
			{
				count = count + invoke->echoWithSetVariable(string2)->length();
			}
		}
        clock_t end = clock();
		delete invoke;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[InvokeTest], Invoke Of An Instance Method With Set Instance Variable With Interface " << NB_INVOKE_TESTS << " invoke.echo(\"1\").length(), count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}


		    /**
     * testCreationOfClassWith1String
     */
    static long testInvokeOfAnInstanceMethodWithoutInterface()
    {
        int count = 0;
		string* string1 = new string("1");
		string* string2 = new string("0");
		InvokeImpl* invoke = new InvokeImpl();
        clock_t start = clock();
		for (int i = NB_INVOKE_TESTS; i != 0; i--)
		{
			// Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
				count = count + invoke->echo(string1)->length();
			}
			else
			{
				count = count + invoke->echo(string2)->length();
			}
		}
        clock_t end = clock();
        delete string1;
		delete invoke;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[InvokeTest], Invoke Of An Instance Method Without Interface " << NB_INVOKE_TESTS << " invoke.echo(\"1\").length(), count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}


	/**
     * testCreationOfClassWith1String
     */
	
    static long testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterface()
    {
        int count = 0;
		string* string1 = new string("1");
		InvokeImpl* invoke = new InvokeImpl();
        clock_t start = clock();
		for (int i = NB_INVOKE_TESTS; i != 0; i--)
		{
			// Write to force cpp not to optimize the code, never executed
			if ( i % 2 == 2 )
			{
				invoke->setEchoGet(string1);
			}

			count = count + invoke->echoWithGetVariable()->length();
		}
        clock_t end = clock();
		delete invoke;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[InvokeTest], Invoke Of An Instance Method With Get Instance Variable Without Interface " << NB_INVOKE_TESTS << " invoke.echo(\"1\").length(), count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}

	/**
     * testCreationOfClassWith1String
     */
	
    static long testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface()
    {
        int count = 0;
		string* string1 = new string("1");
		string* string2 = new string("0");
		InvokeImpl* invoke = new InvokeImpl();
        clock_t start = clock();
		for (int i = NB_INVOKE_TESTS; i != 0; i--)
		{
			// Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
				count = count + invoke->echoWithSetVariable(string1)->length();
			}
			else
			{
				count = count + invoke->echoWithSetVariable(string2)->length();
			}
		}
        clock_t end = clock();
		delete invoke;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[InvokeTest], Invoke Of An Instance Method With Set Instance Variable Without Interface " << NB_INVOKE_TESTS << " invoke.echo(\"1\").length(), count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}


   /**
     * testCreationOfClassWith1String
     */
    static long testInvokeOfAnInstanceMethod()
    {
        int count = 0;
		string* string1 = new string("1");
		string* string2 = new string("0");
		InvokeInstance* invoke = new InvokeInstance();

        clock_t start = clock();
		for (int i = NB_INVOKE_TESTS; i != 0; i--)
		{
			// Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
				count = count + invoke->echo(string1)->length();
			}
			else
			{
				count = count + invoke->echo(string2)->length();
			}
		}
        clock_t end = clock();
        delete string1;
		delete invoke;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[InvokeTest], Invoke Of An Instance Method  " << NB_INVOKE_TESTS << " invoke.echo(\"1\").length(), count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}


	/**
     * testCreationOfClassWith1String
     */
	
    static long testInvokeOfAnInstanceMethodWithGetInstanceVariable()
    {
        int count = 0;
		string* string1 = new string("1");
		InvokeInstance* invoke = new InvokeInstance();

        clock_t start = clock();
		for (int i = NB_INVOKE_TESTS; i != 0; i--)
		{
			// Write to force cpp not to optimize the code, never executed
			if ( i % 2 == 2 )
			{
				invoke->setEchoGet(string1);
			}
			count = count + invoke->echoWithGetVariable()->length();
		}
        clock_t end = clock();
		delete invoke;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[InvokeTest], Invoke Of An Instance Method With Get Instance Variable " << NB_INVOKE_TESTS << " invoke.echo(\"1\").length(), count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}

		/**
     * testCreationOfClassWith1String
     */
	
    static long testInvokeOfAnInstanceMethodWithSetInstanceVariable()
    {
        int count = 0;
		string* string1 = new string("1");
		string* string2 = new string("0");
		InvokeInstance* invoke = new InvokeInstance();
        clock_t start = clock();
		for (int i = NB_INVOKE_TESTS; i != 0; i--)
		{
			// Write to force jre not to optimize the code
            if (i % 2 == 0)
            {
				count = count + invoke->echoWithSetVariable(string1)->length();
			}
			else
			{
				count = count + invoke->echoWithSetVariable(string2)->length();
			}
		}
        clock_t end = clock();
		delete invoke;
        long executionTime = (long) ((end - start) / ((double) CLOCKS_PER_SEC) * 1000.0);
		cout << "[InvokeTest], Invoke Of An Instance Method With Set Instance Variable " << NB_INVOKE_TESTS << " invoke.echo(\"1\").length(), count=" << count << ", snapshot time," << executionTime << "\n";
        return executionTime;
	}



	static int main (int size, char** args)
	{
		int nbTests = InvokeTest::NB_TESTS;
		int nbOfExclusionMinMax = InvokeTest::NB_OF_EXCLUSION_MIN_MAX;
	    
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
				(InvokeTest::testInvokeOfAStaticMethodString());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[InvokeTest], Invoke Of A Static Method String " << InvokeTest::NB_INVOKE_TESTS << " InvokeImpl.echoStatic(\"\").length(),, average time," << InvokeTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << InvokeTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
	 
		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(InvokeTest::testInvokeOfAStaticMethodInt());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[InvokeTest], Invoke Of A Static Method Int " << InvokeTest::NB_INVOKE_TESTS << " InvokeImpl.echoStatic(i),, average time," << InvokeTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << InvokeTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(InvokeTest::testInvokeOfAnInstanceMethodWithInterface());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[InvokeTest], Invoke Of An Instance Method With Interface " << InvokeTest::NB_INVOKE_TESTS << " invoke.echo(\"1\").length(),, average time," << InvokeTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << InvokeTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(InvokeTest::testInvokeOfAnInstanceMethodWithGetInstanceVariableWithInterface());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[InvokeTest], Invoke Of An Instance Method With Get Instance Variable With Interface " << InvokeTest::NB_INVOKE_TESTS << " invoke.echo(\"1\").length(),, average time," << InvokeTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << InvokeTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(InvokeTest::testInvokeOfAnInstanceMethodWithSetInstanceVariableWithInterface());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[InvokeTest], Invoke Of An Instance Method With Set Instance Variable With Interface " << InvokeTest::NB_INVOKE_TESTS << " invoke.echo(\"1\").length(),, average time," << InvokeTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << InvokeTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(InvokeTest::testInvokeOfAnInstanceMethodWithoutInterface());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[InvokeTest], Invoke Of An Instance Method Without Interface " << InvokeTest::NB_INVOKE_TESTS << " invoke.echo(\"1\").length(),, average time," << InvokeTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << InvokeTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(InvokeTest::testInvokeOfAnInstanceMethodWithGetInstanceVariableWithoutInterface());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[InvokeTest], Invoke Of An Instance Method With Get Instance Variable Without Interface " << InvokeTest::NB_INVOKE_TESTS << " invoke.echo(\"1\").length(),, average time," << InvokeTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << InvokeTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(InvokeTest::testInvokeOfAnInstanceMethodWithSetInstanceVariableWithoutInterface());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[InvokeTest], Invoke Of An Instance Method With Set Instance Variable Without Interface " << InvokeTest::NB_INVOKE_TESTS << " invoke.echo(\"1\").length(),, average time," << InvokeTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << InvokeTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";
		
	    executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(InvokeTest::testInvokeOfAnInstanceMethod());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[InvokeTest], Invoke Of An Instance Method " << InvokeTest::NB_INVOKE_TESTS << " invoke.echo(\"1\").length(),, average time," << InvokeTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << InvokeTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(InvokeTest::testInvokeOfAnInstanceMethodWithGetInstanceVariable());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[InvokeTest], Invoke Of An Instance Method With Get Instance Variable " << InvokeTest::NB_INVOKE_TESTS << " invoke.echo(\"1\").length(),, average time," << InvokeTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << InvokeTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";

		executionTimes.clear();
		for (int i = nbTests; i != 0; i--)	
			executionTimes.push_back
				(InvokeTest::testInvokeOfAnInstanceMethodWithSetInstanceVariable());
		std::sort(executionTimes.begin(), executionTimes.end());
		cout << "[InvokeTest], Invoke Of An Instance Method With Set Instance Variable " << InvokeTest::NB_INVOKE_TESTS << " invoke.echo(\"1\").length(),, average time," << InvokeTest::averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << ", min time," << executionTimes[0] << ", max time," << executionTimes[executionTimes.size() - 1] << ", relative deviation time," << InvokeTest::relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) << "\n";


		return 0;
	}

	



};




/*
int main(string* args, int size)
{
	InvokeTest::main(args, size);
}
*/
