import scala.util.Sorting
import scala.collection.mutable.ListBuffer
import scala.compat.Platform

object InvokeTest 
{
    val NbTests = 5
    val NbOfExculsionMinMax = 2
    val NbInvokeTests = 2 * 1000 * 1000 * 1000
    val NbInvokeGetterTests = 2 * 1000 * 1000 * 1000
    val NbInvokeSetterTests = 2 * 1000 * 1000 * 1000
                                                                     
    def averageTimeWithoutMinMax(executionTimes: Seq[Long], numberOfMinMaxToRemove: Int): Long = 
    {   
        var average: Long = 0
        val minMax = if (numberOfMinMaxToRemove > 0) numberOfMinMaxToRemove else 0
        val orderedExecutionTimes = if (minMax != 0) Sorting.stableSort(executionTimes) else executionTimes
        for (i <- minMax.until(orderedExecutionTimes.size - minMax))
        {
           average += orderedExecutionTimes(i)
        }
        average / (orderedExecutionTimes.size - 2 * minMax)
    }


    def relativeDeviationTimeWithoutMinMax(executionTimes: Seq[Long], numberOfMinMaxToRemove: Int): Double = 
    {
        val avgTime: Long = averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove)
        var deviation: Double = 0
        val minMax = if (numberOfMinMaxToRemove > 0) numberOfMinMaxToRemove else 0
        val orderedExecutionTimes = if (minMax != 0) Sorting.stableSort(executionTimes) else executionTimes
        for (i <- minMax.until(orderedExecutionTimes.size - minMax))
        {
          deviation += Math.pow(orderedExecutionTimes(i) - avgTime, 2)
        }
        (((Math.sqrt(deviation / (orderedExecutionTimes.size - 2 * minMax)) / avgTime * 100) * 100).asInstanceOf[Int]) / 100d
    }

   

    
    def testInvokeOfAStaticMethodString(): Long =
    {
        var count = 0
        var i = NbInvokeTests
        val start = Platform.currentTime
        while (i != 0)
        {
          // Write to force jre not to optimize the code
          if (i % 2 == 0)
          {
              count = count + InvokeObject.echoStatic("0").length
          }
          else
          {
              count = count + InvokeObject.echoStatic("1").length
          }
          i -= 1
        }
        val end = Platform.currentTime
        val executionTime = end - start
        println("[InvokeTest], Invoke Of A Static Method String " + NbInvokeTests + " InvokeObject.echoStatic(\"\").length, count=" + count + ", snapshot time," + executionTime)
        executionTime
    }
        
        
    def testInvokeOfAStaticMethodInt(): Long =
    {
        var count = 0
        var i = NbInvokeTests
        val start = Platform.currentTime
        while (i != 0)
        {
            count += InvokeObject.echoStatic(i)
            i -= 1
        }
        val end = Platform.currentTime
        val executionTime = end - start
        println("[InvokeTest], Invoke Of A Static Method Int " + NbInvokeTests + " InvokeObject.echoStatic(\"\").length, count=" + count + ", snapshot time," + executionTime)
        executionTime
    }
    
    def testInvokeOfAInstanceMethodWithInterface(): Long =
    {
        var count = 0
        var i = NbInvokeTests
        val invoke : Invoke = new InvokeClass
        val start = Platform.currentTime
        while (i != 0)
        {
            if (i % 2 == 0)
            {
                count += invoke.echo("1").length
            }
            else
            {
                count += invoke.echo("0").length
            }
            i -= 1
        }
        val end = Platform.currentTime
        val executionTime = end - start
        println("[InvokeTest], Invoke Of An Instance Method With Interface " + NbInvokeTests + " invoke.echo(\"1\").length, count=" + count + ", snapshot time," + executionTime)
        executionTime
    }

    def testInvokeOfAnInstanceMethodWithGetInstanceVariableWithInterface() : Long =
    {
        var count = 0
        var i = NbInvokeTests
        val invoke : Invoke = new InvokeClass
        var start = Platform.currentTime
        while (i != 0)
        {
            // Write to force cpp not to optimize the code, never executed
            if (i % 2 == 2)
            {
                invoke.echoWithGetVariable
            }
            count += invoke.echoWithGetVariable.length;
            i -= 1
        }
        val end = Platform.currentTime 
        var executionTime = end - start
        println("[InvokeTest], Invoke Of An Instance Method With Get Instance Variable With Interface " + NbInvokeTests + " invoke.echoWithGetVariable.length, count=" + count + ", snapshot time," + executionTime)
        executionTime
    }
    
    def main(args : Array[String]) =
    {
        val nbTests = if (args.size >= 1) args(0).toInt else NbTests;
        val nbOfExclusionMinMax = if (args.size >= 2) args(1).toInt else NbOfExculsionMinMax
        
        val executionTimes = new ListBuffer[Long]

        executionTimes.clear
        for (i <- 1 to nbTests)
        {
            executionTimes + testInvokeOfAStaticMethodString 
        }
        
        
        println("[InvokeTest], Invoke Of A Static Method String " + NbInvokeTests + " InvokeImpl.echoStatic(\"\").length,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," +  executionTimes.foldLeft(Long.MinValue)(_ min _) + ", max time," + executionTimes.foldLeft(Long.MinValue)(_ max _) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax))
        
        executionTimes.clear
        for (i <- 1 to nbTests)
        {
            executionTimes + testInvokeOfAStaticMethodString 
        }
        println("[InvokeTest], Invoke Of A Static Method Int " + NbInvokeTests + " InvokeImpl.echoStaticInt(\"\").length,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes.foldLeft(Long.MinValue)(_ min _) + ", max time," + executionTimes.foldLeft(Long.MinValue)(_ max _) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax))
        
        executionTimes.clear
        for (i <- 1 to nbTests)
        {
            executionTimes+ testInvokeOfAnInstanceMethodWithGetInstanceVariableWithInterface 
        }
        println("[InvokeTest], Invoke Of An Instance Method With Get Instance Variable With Interface " + NbInvokeTests + " invoke.echoWithGetVariable.length,,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes.foldLeft(Long.MinValue)(_ min _) + ", max time," + executionTimes.foldLeft(Long.MinValue)(_ max _) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax))
    
        executionTimes.clear
    }
    
    trait Invoke
    {
        var echoGet : String = ""
        var echoSet : String = ""
        def echo(s : String) : String
        def echoWithGetVariable() : String
        def echoWithSetVariable(s : String) : String
        def returnBlank() : String
    }
    
    object InvokeObject
    {
        def echoStatic(s : String) : String = s
        def echoStatic(i : Int) : Int = i
        def returnBlankStatic(s : String) : String = "" 
    }
    
    final class InvokeClass() extends Invoke
    {
        def echo(s : String) : String = s
        def echoWithGetVariable() : String = this.echoGet
        def echoWithSetVariable(s : String) : String = 
        {
            echoSet = s
            s
        }
        def returnBlank() : String = ""
    }

    final class InvokeInstance()
    {
        var echoGet : String = ""
        var echoSet : String = ""
        def echo(s : String) : String = s
        def echoWithGetVariable() : String = this.echoGet
        def echoWithSetVariable(s : String) : String = 
        {
            echoSet = s
            s
        }
        def returnBlank() : String = ""
    }
}
