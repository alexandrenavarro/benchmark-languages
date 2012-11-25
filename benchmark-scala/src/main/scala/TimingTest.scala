import scala.util.Sorting
import scala.collection.mutable.ListBuffer
import scala.compat.Platform

object TimingTest 
{
    val NbTests = 5
    val NbOfExculsionMinMax = 2
    val NbTimingTests =  50 * 1000 * 1000
                                                            
    
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

    

    def testCurrentTime: Long = 
    {
        var currentTime = Platform.currentTime
        var i = NbTimingTests
        val start = Platform.currentTime
        while (i != 0)
        {
            currentTime = Platform.currentTime
            i -= 1
        }
        val end = Platform.currentTime
        val executionTime = end - start
        println("[TimingTest], Invoke of " + NbTimingTests  + " currentTime = Platform.currentTime, snapshot time," + executionTime)
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
            executionTimes + testCurrentTime 
        }
        println("[TimingTest], Invoke of " + NbTimingTests  + " currentTime = Platform.currentTime,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes.foldLeft(Long.MinValue)(_ min _) + ", max time," + executionTimes.foldLeft(Long.MinValue)(_ max _) + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));   
        
        executionTimes.clear
         
    }
}
