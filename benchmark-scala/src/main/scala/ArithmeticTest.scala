import scala.util.Sorting
import scala.collection.mutable.ListBuffer
import scala.compat.Platform

/**
 * ArithmeticTest$.
 * 
 * @author anavarro - 6 Sep 2009
 * 
 */
object ArithmeticTest
{
    val NbTests = 5
    val NbOfExculsionMinMax = 2
    val NbArithmeticTests = 1
    val IntMax = 1 * 1000 * 1000 * 1000
    val DoubleMin = 10 * 1000 * 1000 * 1000.0
    val DoubleMax = 11 * 1000 * 1000 * 1000.0
    val LongMin = 10 * 1000 * 1000 * 1000L
    val LongMax = 11 * 1000 * 1000 * 1000L
                                                            
    
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

    
    def testIntArithmetic: Long = 
    {
        val start = Platform.currentTime
        var intResult = 1
        var i = 1
        while (i < IntMax)
        {
          intResult -= i
          i += 1
          intResult += i
          i += 1
          intResult *= i
          i += 1
          intResult /= i
          i += 1
        }
        val end = Platform.currentTime
        val executionTime = end - start
        println("[ArithmeticTest], Calculation of " + NbArithmeticTests  + " var intResult = 1  var i = 1  while (i < " + IntMax + ") {intResult -= i  i += 1  intResult += i i += 1  intResult *= i  i += 1  intResult /= i  i += 1},intResult=" + intResult + ", snapshot time," + executionTime)
        executionTime
    }

    def testDoubleArithmetic: Long = 
    {
        val start = Platform.currentTime
        var doubleResult: Double = DoubleMin
        var i : Double = DoubleMin
        while (i < DoubleMax)
        {
          doubleResult -= i
          i += 1
          doubleResult += i
          i += 1
          doubleResult *= i
          i += 1
          doubleResult /= i
          i += 1
        }
        val end = Platform.currentTime
        val executionTime = end - start
        println("[ArithmeticTest], Calculation of " + NbArithmeticTests  + " var doubleResult : Double = doubleMin  var i : Double = doubleMin  while (i < " + DoubleMax + ") {doubleResult -= i  i += 1  doubleResult += i i += 1  doubleResult *= i  i += 1  doubleResult /= i  i += 1}, doubleResult=" + doubleResult + ", snapshot time," + executionTime)
        executionTime
    }
        
    def testLongArithmetic: Long = 
    {
        val start = Platform.currentTime
        var longResult: Long = LongMin
        var i : Long = LongMin
        while (i < LongMax)
        {
          longResult -= i
          i += 1
          longResult += i
          i += 1
          longResult *= i
          i += 1
          longResult /= i
          i += 1
        }
        val end = Platform.currentTime
        val executionTime = end - start
        println("[ArithmeticTest], Calculation of " + NbArithmeticTests  + " var longResult : Long = longMin  var i = 1  while (i < " + LongMax + ") {longResult -= i  i += 1  longResult += i i += 1  longResult *= i  i += 1  longResult /= i  i += 1}, longResult=" + longResult + ", snapshot time," + executionTime)
        executionTime
    }

    
    /**
     * main.
     * 
     * @param args
     */
    def main(args: Array[String]) =
    {
        val nbTests = if (args.size >= 1) args(0).toInt else NbTests;
        val nbOfExclusionMinMax = if (args.size >= 2) args(1).toInt else NbOfExculsionMinMax
        
        val executionTimes = new ListBuffer[Long]
        executionTimes.clear
        for (i <- 1 to nbTests)
        {
            executionTimes + testIntArithmetic 
        }
        println("[ArithmeticTest], Calculation of " + NbArithmeticTests + "  var intResult = 1  var i = 1  while (i < " + IntMax + ") {intResult -= i++  intResult += i++  intResult *= i++  intResult /= i++ },, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes.foldLeft(Long.MinValue)(_ min _) + ", max time," + executionTimes.foldLeft(Long.MinValue)(_ max _) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));   
        
        executionTimes.clear
        for (i <- 1 to nbTests)
        {
            executionTimes + testDoubleArithmetic
        }
        println("[ArithmeticTest], Calculation of " + NbArithmeticTests  + " var doubleResult : Double = doubleMin  var i : Double = doubleMin  while (i < " + DoubleMax + ") {doubleResult -= i  i += 1  doubleResult += i i += 1  doubleResult *= i  i += 1  doubleResult /= i  i += 1},, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes.foldLeft(Long.MinValue)(_ min _) + ", max time," + executionTimes.foldLeft(Long.MinValue)(_ min _) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));   
        
        executionTimes.clear
        for (i <- 1 to nbTests)
        {
            executionTimes + testLongArithmetic
        }
        println("[ArithmeticTest], Calculation of " + NbArithmeticTests  + " var longResult : Long = longMin  var i = 1  while (i < " + LongMax + ") {longResult -= i  i += 1  longResult += i i += 1  longResult *= i  i += 1  longResult /= i  i += 1},, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes.foldLeft(Long.MinValue)(_ min _) + ", max time," + executionTimes.foldLeft(Long.MinValue)(_ max _) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));   
        
    }
}

