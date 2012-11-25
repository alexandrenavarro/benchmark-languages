Introduction :
--------------
Open a commandline in the directory benchmark-languages\benchmark-java



Compile : 
---------
1) If you want to compile, install maven2 (I used 2.0.7), install jdk (I used jdk-1.6.0_03, at least a jdk 1.5) and run

mvn clean install


2) If you do not want to compile with maven2, you can also compile with

javac -source 1.6 -target 1.6 -O -g:none -sourcepath src\main\java\*.java -d target\classes



Run : 
-----
1) If you have compiled with maven,

1.1) If you are under Linux, launch

sh ./target/appassembler/bin/benchmark-java

1.2) If you are under Windows, launch

target\appassembler\bin\benchmark-java.bat


2) If you have not compiled with maven, you can also run with

java -server -Xms1024m -Xmx1024m -XX:+AggressiveOpts -classpath target\classes App


Extra if your are under Linux and if you want to compile java with gcj (GNU Compiler for the Java) as native code : 
---------------------------------------------------------------------------------------

Compile : 
---------
1) If you want to compile, install maven2, gcj (I used 4.2.1) and run in the directory linux_x86 or linux-x86_64

mvn install

2) You can also compile with the gcj commandline

Launch theses lines to compile

gcj -w -O2 -I../src/main/java -o target/App.o -c ../src/main/java/App.java
gcj -w -O2 -I../src/main/java -o target/ArithmeticTest.o -c ../src/main/java/ArithmeticTest.java
gcj -w -O2 -I../src/main/java -o target/AutoboxingTest.o -c ../src/main/java/AutoboxingTest.java
gcj -w -O2 -I../src/main/java -o target/CollectionTest.o -c ../src/main/java/CollectionTest.java
gcj -w -O2 -I../src/main/java -o target/CreationTest.o -c ../src/main/java/CreationTest.java
gcj -w -O2 -I../src/main/java -o target/EnumTest.o -c ../src/main/java/EnumTest.java
gcj -w -O2 -I../src/main/java -o target/ExceptionTest.o -c ../src/main/java/ExceptionTest.java
gcj -w -O2 -I../src/main/java -o target/FileTest.o -c ../src/main/java/FileTest.java
gcj -w -O2 -I../src/main/java -o target/GetterSetterTest.o -c ../src/main/java/GetterSetterTest.java
gcj -w -O2 -I../src/main/java -o target/HeapSortTest.o -c ../src/main/java/HeapSortTest.java
gcj -w -O2 -I../src/main/java -o target/InvokeTest.o -c ../src/main/java/InvokeTest.java
gcj -w -O2 -I../src/main/java -o target/MatrixMultiplyTest.o -c ../src/main/java/MatrixMultiplyTest.java
gcj -w -O2 -I../src/main/java -o target/NestedLoopsTest.o -c ../src/main/java/NestedLoopsTest.java
gcj -w -O2 -I../src/main/java -o target/RecursiveTest.o -c ../src/main/java/RecursiveTest.java
gcj -w -O2 -I../src/main/java -o target/ReflectionTest.o -c ../src/main/java/ReflectionTest.java
gcj -w -O2 -I../src/main/java -o target/StringConcatTest.o -c ../src/main/java/StringConcatTest.java
gcj -w -O2 -I../src/main/java -o target/ThreadingTest.o -c ../src/main/java/ThreadingTest.java
gcj -w -O2 -I../src/main/java -o target/TimingTest.o -c ../src/main/java/TimingTest.java
gcj -w -O2 -I../src/main/java -o target/TrigoTest.o -c ../src/main/java/TrigoTest.java

Launch theses lines to link

gcj --main=App -o target/benchmark-java-linux-x86.exe target/App.o target/ArithmeticTest.o target/AutoboxingTest.o target/CollectionTest.o target/CreationTest.o target/EnumTest.o target/ExceptionTest.o target/FileTest.o target/GetterSetterTest.o target/HeapSortTest.o target/InvokeTest.o target/MatrixMultiplyTest.o target/NestedLoopsTest.o target/RecursiveTest.o target/ReflectionTest.o target/StringConcatTest.o target/ThreadingTest.o target/TimingTest.o target/TrigoTest.o

and/or see gcj documentation



Run : 
-----

1) If you have compiled with maven,

./target/benchmark-java-linux-x86.exe

or

./target/benchmark-java-linux-x86_64.exe


2) If you have not compiled with maven, 

./target/benchmark-java-linux-x86.exe

or

./target/benchmark-java-linux-x86_64.exe

and/or see gcj documentation
