Introduction :
--------------
Open a commandline in the directory benchmark-languages\benchmark-cpp



Compile : 
---------

1) If you compile with maven2, install a jre (I used 1.6.0_03) and maven2 (I used 2.0.7)

1.1) If you are under Linux, install g++ (I used 4.1.3), launch

mvn clean install

1.2) If you are under Windows, install Visual Studio C++ (I used Visual Studio 2008 C++ Express Edition) 
You must set some environment variables, generally you have a script C:\Program Files\Microsoft Visual Studio 8\Common7\Tools\vsvars32.bat

Launch it

or 

Add theses variables

set PATH=C:\Program Files\Microsoft Visual Studio 9\Common7\IDE;C:\Program Files\Microsoft Visual Studio 8\VC\BIN;C:\Program Files\Microsoft Visual Studio 9\Common7\Tools;C:\Program Files\Microsoft Visual Studio 9\SDK\v2.0\bin;C:\WINDOWS\Microsoft.NET\Framework\v2.0.50727;C:\Program Files\Microsoft Visual Studio 9\VC\VCPackages;%PATH%
set INCLUDE=C:\Program Files\Microsoft Visual Studio 8\VC\INCLUDE;%INCLUDE%
set LIB=C:\Program Files\Microsoft Visual Studio 9\VC\LIB;C:\Program Files\Microsoft Visual Studio 9\SDK\v2.0\lib;%LIB%
set LIBPATH=C:\WINDOWS\Microsoft.NET\Framework\v2.0.50727

After
Launch

mvn clean install


2) If you do not want to compile with maven2

2.1)  If you are under Linux, install g++ (I used 4.1.3), you can compile with g++ in command line 
Enter in linux-x86  (or linux-x86_64 if you under a linx 64) directory
Launch theses lines to compile

g++ -O2 -I../src/main/cpp -o target/App.o -c ../src/main/cpp/App.cpp
g++ -O2 -I../src/main/cpp -o target/ArithmeticTest.o -c ../src/main/cpp/ArithmeticTest.cpp
g++ -O2 -I../src/main/cpp -o target/CollectionTest.o -c ../src/main/cpp/CollectionTest.cpp
g++ -O2 -I../src/main/cpp -o target/CreationTest.o -c ../src/main/cpp/CreationTest.cpp
g++ -O2 -I../src/main/cpp -o target/ExceptionTest.o -c ../src/main/cpp/ExceptionTest.cpp
g++ -O2 -I../src/main/cpp -o target/FileTest.o -c ../src/main/cpp/FileTest.cpp
g++ -O2 -I../src/main/cpp -o target/GetterSetterTest.o -c ../src/main/cpp/GetterSetterTest.cpp
g++ -O2 -I../src/main/cpp -o target/HeapSortTest.o -c ../src/main/cpp/HeapSortTest.cpp
g++ -O2 -I../src/main/cpp -o target/InvokeTest.o -c ../src/main/cpp/InvokeTest.cpp
g++ -O2 -I../src/main/cpp -o target/MatrixMultiplyTest.o -c ../src/main/cpp/MatrixMultiplyTest.cpp
g++ -O2 -I../src/main/cpp -o target/NestedLoopsTest.o -c ../src/main/cpp/NestedLoopsTest.cpp
g++ -O2 -I../src/main/cpp -o target/RecursiveTest.o -c ../src/main/cpp/RecursiveTest.cpp
g++ -O2 -I../src/main/cpp -o target/StringConcatTest.o -c ../src/main/cpp/StringConcatTest.cpp
g++ -O2 -I../src/main/cpp -o target/TimingTest.o -c ../src/main/cpp/TimingTest.cpp
g++ -O2 -I../src/main/cpp -o target/TrigoTest.o -c ../src/main/cpp/TrigoTest.cpp

Launch theses lines to link

g++ -o target/benchmark-cpp-linux-x86.exe target/App.o target/ArithmeticTest.o target/CollectionTest.o target/CreationTest.o target/ExceptionTest.o target/FileTest.o target/GetterSetterTest.o target/HeapSortTest.o target/InvokeTest.o target/MatrixMultiplyTest.o target/NestedLoopsTest.o target/RecursiveTest.o target/StringConcatTest.o target/TimingTest.o target/TrigoTest.o

2.2) If you are under Windows, install Visual Studio C++ (I used Visual Studio 2008 C++ Express Edition), you can compile with Visual Studio,
with or without commandline (there is a Visual Studio C++ project in the directory, see the file benchmark-cpp.vcproj) 
and/or see Visual Studio documentation



Run : 
-----
1) If you have compiled with maven,

1.1) If you are under Linux, 
Launch (depends if you are on 32 or 64 bits environment)

./benchmark-cpp-gcc-linux-x86/target/benchmark-cpp-gcc-linux-x86.exe 

or 

./benchmark-cpp-gcc-linux-x86_64/target/benchmark-cpp-gcc-linux-x86_64.exe
 
1.2) If you are under Windows, 
Launch

./benchmark-cpp-msvc-windows-x86/target/benchmark-cpp-msvc-win32.exe


2) If you have not compiled with maven

1.2) If you are under Linux, 
Launch

./benchmark-cpp-gcc-linux-x86/target/benchmark-cpp-gcc-linux-x86.exe 

or 

./benchmark-cpp-gcc-linux-x86_64/target/benchmark-cpp-gcc-linux-x86_64.exe

2.2) If you are under Windows, you can launch with Visual Studio or
Launch

.\Release\benchmark-cpp.exe

and/or see Visual Studio documentation


