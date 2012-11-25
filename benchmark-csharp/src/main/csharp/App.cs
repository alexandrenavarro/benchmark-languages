using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using System.Threading;
sealed class App
{
     /**
     * NB_TESTS
     */
    private static int NB_TESTS = 10;
    
    /**
     * NB_OF_EXCLUSION_MIN_MAX
     * 
     * Number of max or min excluded to caculate the average time of execution time.
 */
    private static int NB_OF_EXCLUSION_MIN_MAX = 0;
    
    /**
     * ARGS.
     */
    private static string[] ARGS = { NB_TESTS.ToString(), NB_OF_EXCLUSION_MIN_MAX.ToString() };


    /**
     * TIME_BEETWEEN_FIRST_INVOKE_AND_SECOND_INVOKE
     */
    private static int TIME_BETWEEN_FIRST_INVOKE_AND_SECOND_INVOKE = 2000;

	private static void LaunchGcAndSleep()
	{
        System.GC.Collect();
        System.GC.WaitForPendingFinalizers();
        System.GC.Collect();
        Thread.Sleep(TIME_BETWEEN_FIRST_INVOKE_AND_SECOND_INVOKE);		
	}
	
    public static void Main(string[] args)
    {

        StreamWriter sw = null;
        
        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("getter-setter-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);   
        GetterSetterTest.main(ARGS);
        sw.Close();
		
        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("getter-setter-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        GetterSetterTest.main(ARGS);
        sw.Close();
        
		
        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("invoke-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        InvokeTest.main(ARGS);
        sw.Close();

        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("invoke-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        InvokeTest.main(ARGS);
        sw.Close();

		
        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("creation-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        CreationTest.main(ARGS);
        sw.Close();
        
        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("creation-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        CreationTest.main(ARGS);
        sw.Close();
        

        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("collection-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        CollectionTest.main(ARGS);
        sw.Close();

        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("collection-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        CollectionTest.main(ARGS);
        sw.Close();

        
        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("arithmetic-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        ArithmeticTest.main(ARGS);
        sw.Close();
        
        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("arithmetic-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        ArithmeticTest.main(ARGS);
        sw.Close();
        
		
        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("threading-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        ThreadingTest.main(ARGS);
        sw.Close();
        
        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("threading-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        ThreadingTest.main(ARGS);
        sw.Close();
        
		
        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("timing-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        TimingTest.main(ARGS);
        sw.Close();
        
        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("timing-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        TimingTest.main(ARGS);
        sw.Close();
        
		
        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("autoboxing-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        AutoboxingTest.main(ARGS);
        sw.Close();

        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("autoboxing-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        AutoboxingTest.main(ARGS);
        sw.Close();
        
		
        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("file-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        FileTest.main(ARGS);
        sw.Close();

        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("file-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        FileTest.main(ARGS);
        sw.Close();
        
		
        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("exception-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        ExceptionTest.main(ARGS);
        sw.Close();

        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("exception-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        ExceptionTest.main(ARGS);
        sw.Close();
        
		
        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("recursive-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        RecursiveTest.main(ARGS);
        sw.Close();

        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("recursive-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        RecursiveTest.main(ARGS);
        sw.Close();
        
		
        LaunchGcAndSleep();
		sw = new StreamWriter(new FileStream("stringconcat-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        StringConcatTest.main(ARGS);
        sw.Close();

        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("stringconcat-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        StringConcatTest.main(ARGS);
        sw.Close();
        
		
        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("nestedloops-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        NestedLoopsTest.main(ARGS);
        sw.Close();

        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("nestedloops-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        NestedLoopsTest.main(ARGS);
        sw.Close();
        

        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("heap-sort-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        HeapSortTest.main(ARGS);
        sw.Close();

        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("heap-sort-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        HeapSortTest.main(ARGS);
        sw.Close();
        
       
        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("matrix-multiply-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        MatrixMultiplyTest.main(ARGS);
        sw.Close();

        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("matrix-multiply-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        MatrixMultiplyTest.main(ARGS);
        sw.Close();

		
        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("reflection-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        ReflectionTest.main(ARGS);
        sw.Close();

       	LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("reflection-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        ReflectionTest.main(ARGS);
        sw.Close();


        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("enum-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        EnumTest.main(ARGS);
        sw.Close();

        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("enum-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        EnumTest.main(ARGS);
        sw.Close();
        
		
        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("trigo-benchmark-csharp-firstinvoke.csv", FileMode.Create));
        Console.SetOut(sw);
        TrigoTest.main(ARGS);
        sw.Close();

        LaunchGcAndSleep();
        sw = new StreamWriter(new FileStream("trigo-benchmark-csharp.csv", FileMode.Create));
        Console.SetOut(sw);
        TrigoTest.main(ARGS);
        sw.Close();        
        

        //TODO NetworkInvoke.main(ARGS);
    }
}

