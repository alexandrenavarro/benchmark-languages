Introduction :
--------------
Open a commandline in the directory benchmark-languages\benchmark-csharp



Compile : 
---------
1) If you compile with maven2, install a jre (I used 2.0.7) and maven2 (I used 2.0.7)

1.1) If you are under Linux, install mono and mono-gmcs (I used 1.2.4), launch

mvn clean compiler:compile

1.2) If you are under Windows, install Visual Studio C# (I used Visual Studio 2008 C# Express Edition) and/or Mono (I used Mono-1.2.6.0) in C:\Program Files\Mono-1.2.6.0
You must set some environment variables, generally you have a script C:\Program Files\Microsoft Visual Studio 9\Common7\Tools\vsvars32.bat

Launch it

or 

Add theses variables

set PATH=C:\Program Files\Microsoft Visual Studio 9\Common7\IDE;C:\Program Files\Microsoft Visual Studio 9\VC\BIN;C:\Program Files\Microsoft Visual Studio 9\Common7\Tools;C:\Program Files\Microsoft Visual Studio 9\SDK\v2.0\bin;C:\WINDOWS\Microsoft.NET\Framework\v2.0.50727;C:\Program Files\Microsoft Visual Studio 9\VC\VCPackages;%PATH%
set INCLUDE=C:\Program Files\Microsoft Visual Studio 9\VC\INCLUDE;%INCLUDE%
set LIB=C:\Program Files\Microsoft Visual Studio 9\VC\LIB;C:\Program Files\Microsoft Visual Studio 9\SDK\v2.0\lib;%LIB%
set LIBPATH=C:\WINDOWS\Microsoft.NET\Framework\v2.0.50727

After
Launch

mvn clean compiler:compile


2) If you want to compile with nant (I used 0.85), install Visual Studio (I used Visual Studio C# 2008 Express Edition), nant and run

2.1) If you are under Linux, install nant, uncomment gmcs task int the default.build, comment csc task, launch

nant

2.2) If you are under Windows, install Visual Studio C# (I used Visual Studio 2008 C# Express Edition) 
You must set some environment variables, generally you have a script C:\Program Files\Microsoft Visual Studio 9\Common7\Tools\vsvars32.bat

Launch it

or 

Add theses variables

set PATH=C:\Program Files\Microsoft Visual Studio 9\Common7\IDE;C:\Program Files\Microsoft Visual Studio 8\VC\BIN;C:\Program Files\Microsoft Visual Studio 9\Common7\Tools;C:\Program Files\Microsoft Visual Studio 9\SDK\v2.0\bin;C:\WINDOWS\Microsoft.NET\Framework\v2.0.50727;C:\Program Files\Microsoft Visual Studio 9\VC\VCPackages;%PATH%
set INCLUDE=C:\Program Files\Microsoft Visual Studio 9\VC\INCLUDE;%INCLUDE%
set LIB=C:\Program Files\Microsoft Visual Studio 9\VC\LIB;C:\Program Files\Microsoft Visual Studio 8\SDK\v2.0\lib;%LIB%
set LIBPATH=C:\WINDOWS\Microsoft.NET\Framework\v2.0.50727

After
Launch

nant


3) If you do not want to compile with nant, you can also compile with (I used Visual Studio C++ 2008 Express Edition), 
with or without commandline (there is a Visual Studio C# project in the directory, see the file benchmark-csharp.csproj) and/or see Visual Studio documentation



Run : 
-----
1) If you have compiled with maven,

1.1) If you are under Linux, 
Launch (depends if you are on 32 or 64 bits environment)

./benchmark-csharp-gmcs-linux-x86/target/benchmark-csharp-gmcs-linux-x86-1.0-SNAPSHOT.exe 

or 

./benchmark-csharp-gmcs-linux-x86_64/target/benchmark-csharp-gmcs-linux-x86_64-1.0-SNAPSHOT.exe
 
1.2) If you are under Windows, 

1.2.1) If you have compiled with csc (Microsoft C# Compiler )

Launch

./benchmark-csharp-csc-windows-x86/target/benchmark-cpp-csc-win32-1.0-SNAPSHOT.exe

1.2.2) If you have compiled with gmcs (Mono C# Compiler )

./benchmark-csharp-gmcs-windows-x86/target/benchmark-cpp-gmcs-win32-1.0-SNAPSHOT.exe


2) If you have compiled with nant

2.1) If you are under Linux, 
Launch

./target/benchmark-csharp.exe

2.2) If you are under Windows, 
Launch

./target/benchmark-csharp.exe


3) If you have not compiled with maven nor nant

3.1) If you are under Linux, see Mono documentation

3.2) If you are under Windows, you can launch with Visual Studio or
Launch

.\Release\benchmark-csharp.exe

and/or see Visual Studio documentation



