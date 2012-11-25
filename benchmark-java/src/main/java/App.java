import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * App.
 * 
 * @author anavarro122404 - 25 sept. 07
 * 
 * 
 * <!-- $Id: App.java,v 1.10 2008/01/10 10:55:58 anavarro Exp $ -->.
 * 
 */
/**
 * App.
 * 
 * @author anavarro - 25 Sep 2009
 * 
 */
public class App
{
    /**
     * NB_TESTS
     */
    private static final int NB_TESTS                                     = 10;
    
    /**
     * NB_OF_EXCLUSION_MIN_MAX
     * 
     * Number of max or min excluded to caculate the average time of execution time.
     */
    private static final int NB_OF_EXCLUSION_MIN_MAX                      = 0;
    
    /**
     * ARGS.
     */
    private static final String[]  ARGS                                         = new String[2]; // {String.valueOf(NB_TESTS), String.valueOf(NB_OF_EXCLUSION_MIN_MAX)}
    
    /**
     * TIME_BEETWEEN_FIRST_INVOKE_AND_SECOND_INVOKE
     */
    private static final int TIME_BETWEEN_FIRST_INVOKE_AND_SECOND_INVOKE = 5000;

    // initialition like that because of groovy ({, }) does not compile, change into
    static
    {
        ARGS[0] = String.valueOf(NB_TESTS);
        ARGS[1] = String.valueOf(NB_OF_EXCLUSION_MIN_MAX);
    }

    
    /**
     * launchGcAndSleep.
     * 
     */
    private static void launchGcAndSleep()
    {
        System.gc();
        System.runFinalization();
        System.gc();
        try
        {
            Thread.sleep(TIME_BETWEEN_FIRST_INVOKE_AND_SECOND_INVOKE);
        }
        catch (InterruptedException exception)
        {
            //
        }
    }
    
    /**
     * main.
     * 
     * @param args
     */
    public static void main(final String[] args)
    {        
        try
        {
            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("getter-setter-benchmark-java-firstinvoke.csv")));
            GetterSetterTest.main(ARGS);
            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("getter-setter-benchmark-java.csv")));
            GetterSetterTest.main(ARGS);

            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("invoke-benchmark-java-firstinvoke.csv")));
            InvokeTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("invoke-benchmark-java.csv")));
            InvokeTest.main(ARGS);

            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("creation-benchmark-java-firstinvoke.csv")));
            CreationTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("creation-benchmark-java.csv")));
            CreationTest.main(ARGS);

            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("collection-benchmark-java-firstinvoke.csv")));
            CollectionTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("collection-benchmark-java.csv")));
            CollectionTest.main(ARGS);
            
            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("threading-benchmark-java-firstinvoke.csv")));
            ThreadingTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("threading-benchmark-java.csv")));
            ThreadingTest.main(ARGS);

            // To stop the threadPool
            ThreadingFibo.getExecutorService().shutdown();
                        
            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("arithmetic-benchmark-java-firstinvoke.csv")));
            ArithmeticTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("arithmetic-benchmark-java.csv")));
            ArithmeticTest.main(ARGS);
		   
            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("timing-benchmark-java-firstinvoke.csv")));
            TimingTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("timing-benchmark-java.csv")));
            TimingTest.main(ARGS);
            
            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("autoboxing-benchmark-java-firstinvoke.csv")));
            AutoboxingTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("autoboxing-benchmark-java.csv")));
            AutoboxingTest.main(ARGS);
            
            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("file-benchmark-java-firstinvoke.csv")));
            FileTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("file-benchmark-java.csv")));
            FileTest.main(ARGS);

            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("exception-benchmark-java-firstinvoke.csv")));
            ExceptionTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("exception-benchmark-java.csv")));
            ExceptionTest.main(ARGS);

            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("recursive-benchmark-java-firstinvoke.csv")));
            RecursiveTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("recursive-benchmark-java.csv")));
            RecursiveTest.main(ARGS);
            
            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("stringconcat-benchmark-java-firstinvoke.csv")));
            StringConcatTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("stringconcat-benchmark-java.csv")));
            StringConcatTest.main(ARGS);
            
            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("nestedloops-benchmark-java-firstinvoke.csv")));
            NestedLoopsTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("nestedloops-benchmark-java.csv")));
            NestedLoopsTest.main(ARGS);

            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("heap-sort-benchmark-java-firstinvoke.csv")));
            HeapSortTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("heap-sort-benchmark-java.csv")));
            HeapSortTest.main(ARGS);

            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("matrix-multiply-benchmark-java-firstinvoke.csv")));
            MatrixMultiplyTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("matrix-multiply-benchmark-java.csv")));
            MatrixMultiplyTest.main(ARGS);

            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("reflection-benchmark-java-firstinvoke.csv")));
            ReflectionTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("reflection-benchmark-java.csv")));
            ReflectionTest.main(ARGS);
            
            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("enum-benchmark-java-firstinvoke.csv")));
            EnumTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("enum-benchmark-java.csv")));
            EnumTest.main(ARGS);
            
            
            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("trigo-benchmark-java-firstinvoke.csv")));
            TrigoTest.main(ARGS);

            launchGcAndSleep();
            System.setOut(new PrintStream(new FileOutputStream("trigo-benchmark-java.csv")));
            TrigoTest.main(ARGS);
            
            // TODO NetworkInvoke            

        }
        catch (FileNotFoundException exception)
        {
            //
        }  
    }

}
