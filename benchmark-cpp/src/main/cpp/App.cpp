#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#define Centralized
#include "GetterSetterTest.cpp"
#include "InvokeTest.cpp"
#include "CreationTest.cpp"
#include "CollectionTest.cpp"
#include "ArithmeticTest.cpp"
#include "TimingTest.cpp"
#include "FileTest.cpp"
#include "ExceptionTest.cpp"
#include "RecursiveTest.cpp"
#include "StringConcatTest.cpp"
#include "NestedLoopsTest.cpp"
#include "MatrixMultiplyTest.cpp"
#include "HeapSortTest.cpp"
#include "TrigoTest.cpp"

using namespace std;

int main(int size, char** args)
{	
	GetterSetterTest::main(size, args);
	InvokeTest::main(size, args);
	CreationTest::main(size, args);
	CollectionTest::main(size, args);
	ArithmeticTest::main(size, args);
	TimingTest::main(size, args);
	FileTest::main(size, args);
	ExceptionTest::main(size, args);
	RecursiveTest::main(size, args);
	StringConcatTest::main(size, args);
	NestedLoopsTest::main(size, args);
	MatrixMultiplyTest::main(size, args);
	HeapSortTest::main(size, args);
	TrigoTest::main(size, args);
	
	return 0;
}



