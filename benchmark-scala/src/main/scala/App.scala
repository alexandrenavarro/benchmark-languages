import java.io.FileOutputStream;
import java.io.PrintStream;

object App 
{
    val NbTests = 10
    val NbOfExclusionMinMax = 1
    val TimeBetweenFirstInvokeAndSecondInvoke = 5000
    val Args = Array[String](NbTests.toString, NbOfExclusionMinMax.toString)
    
    def launchGcAndSleep() =
    {
        System.gc();
        System.runFinalization();
        System.gc();
        Thread.sleep(TimeBetweenFirstInvokeAndSecondInvoke)
    }
    
    def main(args: Array[String]) =
    {
        launchGcAndSleep
        Console.setOut(new PrintStream(new FileOutputStream("invoke-benchmark-scala-firstinvoke.csv")))
        InvokeTest.main(Args)
        
        launchGcAndSleep
        Console.setOut(new PrintStream(new FileOutputStream("invoke-benchmark-scala.csv")))
        InvokeTest.main(Args)
        
        
        launchGcAndSleep
        Console.setOut(new PrintStream(new FileOutputStream("arithmetic-benchmark-scala-firstinvoke.csv")))
        ArithmeticTest.main(Args)
        
        launchGcAndSleep
        Console.setOut(new PrintStream(new FileOutputStream("arithmetic-benchmark-scala.csv")))
        ArithmeticTest.main(Args)
        
        
        launchGcAndSleep
        Console.setOut(new PrintStream(new FileOutputStream("timing-benchmark-scala-firstinvoke.csv")))
        TimingTest.main(Args)
        
        launchGcAndSleep
        Console.setOut(new PrintStream(new FileOutputStream("timing-benchmark-scala.csv")))
        TimingTest.main(Args)        
    }
    
}
