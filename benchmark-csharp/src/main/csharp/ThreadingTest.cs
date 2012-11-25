using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using System.Threading;


sealed class ThreadingTest
{
    
    /**
     * NB_TESTS
     */
    private static int NB_TESTS = 5;

    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    private static int NB_OF_EXCLUSION_MIN_MAX = 2;

    /**
     * NB_CREATION_TESTS
     */
    private static int NB_THREADING_TESTS = 1 * 1000 * 1000;

        /**
     * NB_TO_CALCULATE_IN_THREAD_QUEUE
     */
    public static int    NB_TO_CALCULATE_IN_THREAD_QUEUE = 1000;

    
    /**
     * NB_THREADING_CACHE_TESTS
     */
    private static int NB_THREADING_CACHE_TESTS = 2 * 1000 * 1000;


    /**
     * NB_RECURSIVE_TESTS
     */
    private static int NB_FIBO_TESTS = 1;
    
    /**
     * FIBO_N
     */
    private static int FIBO_N = 41;



    /**
     * averageTimeWithoutMinMax.
     *
     * @param executionTimes
     * @param numberOfMinMaxToRemove
     * @return
     */
    public static long averageTimeWithoutMinMax(List<long> executionTimes, int numberOfMinMaxToRemove)
    {
        long average = 0;
		int minMax = (numberOfMinMaxToRemove > 0) ? numberOfMinMaxToRemove : 0;
		if (minMax != 0) 
		{
        	executionTimes.Sort();
		}
        for (int i = minMax; i < executionTimes.Count - minMax; i++)
        {
            average += executionTimes[i];
        }
        average = average / (executionTimes.Count - minMax * 2);
        return average;
    }


    /**
     * relativeDeviationTimeWithoutMinMax.
     *
     * @param executionTimes
     * @param numberOfMinMaxToRemove
     * @return
     */
    public static double relativeDeviationTimeWithoutMinMax(List<long> executionTimes, int numberOfMinMaxToRemove)
    {
        long averageTimeWithoutMinMaxVariable = averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
        long deviation = 0;
		int minMax = (numberOfMinMaxToRemove > 0) ? numberOfMinMaxToRemove : 0;
		if (minMax != 0) 
		{
        	executionTimes.Sort();
		}
        for (int i = minMax; i < executionTimes.Count - minMax; i++)
        {
            deviation += (long)Math.Pow(executionTimes[i] - averageTimeWithoutMinMaxVariable, 2);
        }
        return ((int)((Math.Sqrt(deviation / (executionTimes.Count - 2 * minMax)) / averageTimeWithoutMinMaxVariable * 100) * 100)) / 100d;
    }
    
    

    /**
     * testQueueWith1ProducerAnd1Consumer.
     *
     * @return
     */
    public static long testQueueWith1ProducerAnd1Consumer()
    {
        Queue queue = Queue.Synchronized(new Queue());
        Producer producer1 = new Producer(queue, NB_THREADING_TESTS);
        Consumer consumer1 = new Consumer(queue, NB_THREADING_TESTS);
        Thread producerThread1 = new Thread(new ThreadStart(producer1.run));
        Thread consumerThread1 = new Thread(new ThreadStart(consumer1.run));
        DateTime start = DateTime.Now;
        producerThread1.Start();
        consumerThread1.Start();
        producerThread1.Join();
        consumerThread1.Join();
	    DateTime end = DateTime.Now;
	    TimeSpan executionTime = end - start;
        //Environment.ProcessorCount
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a queue with 1 Producer and Poll with 1 Consumer,, snapshot time,"
        + executionTime.TotalMilliseconds);
	    return (long) executionTime.TotalMilliseconds;
    }


    /**
 * testQueueWith1ProducerAnd1Consumer.
 *
 * @return
 */
    public static long testQueueWith1ProducerAnd5Consumers()
    {
        Queue queue = Queue.Synchronized(new Queue());
        Producer producer1 = new Producer(queue, NB_THREADING_TESTS);
        Consumer consumer1 = new Consumer(queue, NB_THREADING_TESTS / 5);
        Consumer consumer2 = new Consumer(queue, NB_THREADING_TESTS / 5);
        Consumer consumer3 = new Consumer(queue, NB_THREADING_TESTS / 5);
        Consumer consumer4 = new Consumer(queue, NB_THREADING_TESTS / 5);
        Consumer consumer5 = new Consumer(queue, NB_THREADING_TESTS / 5);
        Thread producerThread1 = new Thread(new ThreadStart(producer1.run));
        Thread consumerThread1 = new Thread(new ThreadStart(consumer1.run));
        Thread consumerThread2 = new Thread(new ThreadStart(consumer2.run));
        Thread consumerThread3 = new Thread(new ThreadStart(consumer3.run));
        Thread consumerThread4 = new Thread(new ThreadStart(consumer4.run));
        Thread consumerThread5 = new Thread(new ThreadStart(consumer5.run));
        DateTime start = DateTime.Now;
        producerThread1.Start();
        consumerThread1.Start();
        consumerThread2.Start();
        consumerThread3.Start();
        consumerThread4.Start();
        consumerThread5.Start();
        producerThread1.Join();
        consumerThread1.Join();
        consumerThread2.Join();
        consumerThread3.Join();
        consumerThread4.Join();
        consumerThread5.Join();
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a queue with 1 Producer and Poll with 5 Consumers,, snapshot time,"
        + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
 * testQueueWith1ProducerAnd1Consumer.
 *
 * @return
 */
    public static long testQueueWith5ProducersAnd1Consumer()
    {
        Queue queue = Queue.Synchronized(new Queue());
        Producer producer1 = new Producer(queue, NB_THREADING_TESTS / 5);
        Producer producer2 = new Producer(queue, NB_THREADING_TESTS / 5);
        Producer producer3 = new Producer(queue, NB_THREADING_TESTS / 5);
        Producer producer4 = new Producer(queue, NB_THREADING_TESTS / 5);
        Producer producer5 = new Producer(queue, NB_THREADING_TESTS / 5);
        Consumer consumer1 = new Consumer(queue, NB_THREADING_TESTS);
        Thread producerThread1 = new Thread(new ThreadStart(producer1.run));
        Thread producerThread2 = new Thread(new ThreadStart(producer2.run));
        Thread producerThread3 = new Thread(new ThreadStart(producer3.run));
        Thread producerThread4 = new Thread(new ThreadStart(producer4.run));
        Thread producerThread5 = new Thread(new ThreadStart(producer5.run));
        Thread consumerThread1 = new Thread(new ThreadStart(consumer1.run));
        DateTime start = DateTime.Now;
        producerThread1.Start();
        producerThread2.Start();
        producerThread3.Start();
        producerThread4.Start();
        producerThread5.Start();
        consumerThread1.Start();
        producerThread1.Join();
        producerThread2.Join();
        producerThread3.Join();
        producerThread4.Join();
        producerThread5.Join();
        consumerThread1.Join();
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a queue with 5 Producers and Poll with 1 Consumer,, snapshot time,"
        + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }


    /**
 * testQueueWith1ProducerAnd1Consumer.
 *
 * @return
 */
    public static long testQueueWith5ProducersAnd5Consumers()
    {
        Queue queue = Queue.Synchronized(new Queue());
        Producer producer1 = new Producer(queue, NB_THREADING_TESTS / 5);
        Producer producer2 = new Producer(queue, NB_THREADING_TESTS / 5);
        Producer producer3 = new Producer(queue, NB_THREADING_TESTS / 5);
        Producer producer4 = new Producer(queue, NB_THREADING_TESTS / 5);
        Producer producer5 = new Producer(queue, NB_THREADING_TESTS / 5);
        Consumer consumer1 = new Consumer(queue, NB_THREADING_TESTS / 5);
        Consumer consumer2 = new Consumer(queue, NB_THREADING_TESTS / 5);
        Consumer consumer3 = new Consumer(queue, NB_THREADING_TESTS / 5);
        Consumer consumer4 = new Consumer(queue, NB_THREADING_TESTS / 5);
        Consumer consumer5 = new Consumer(queue, NB_THREADING_TESTS / 5);
        Thread producerThread1 = new Thread(new ThreadStart(producer1.run));
        Thread producerThread2 = new Thread(new ThreadStart(producer2.run));
        Thread producerThread3 = new Thread(new ThreadStart(producer3.run));
        Thread producerThread4 = new Thread(new ThreadStart(producer4.run));
        Thread producerThread5 = new Thread(new ThreadStart(producer5.run));
        Thread consumerThread1 = new Thread(new ThreadStart(consumer1.run));
        Thread consumerThread2 = new Thread(new ThreadStart(consumer2.run));
        Thread consumerThread3 = new Thread(new ThreadStart(consumer3.run));
        Thread consumerThread4 = new Thread(new ThreadStart(consumer4.run));
        Thread consumerThread5 = new Thread(new ThreadStart(consumer5.run));
        DateTime start = DateTime.Now;
        producerThread1.Start();
        producerThread2.Start();
        producerThread3.Start();
        producerThread4.Start();
        producerThread5.Start();
        consumerThread1.Start();
        consumerThread2.Start();
        consumerThread3.Start();
        consumerThread4.Start();
        consumerThread5.Start();
        producerThread1.Join();
        producerThread2.Join();
        producerThread3.Join();
        producerThread4.Join();
        producerThread5.Join();
        consumerThread1.Join();
        consumerThread2.Join();
        consumerThread3.Join();
        consumerThread4.Join();
        consumerThread5.Join();
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a queue with 5 Producers and Poll with 5 Consumers,, snapshot time,"
        + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }




/**
* testQueueWith1ProducerAnd1Consumer.
*
* @return
*/
    public static long testCacheWithLockWith1ProducerAnd5Consumers()
    {
        Cache cache = new SyncCacheWithLock();
        ProducerCache producer1 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS);
        ConsumerStockCache consumer1 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerStockCache consumer2 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerStockCache consumer3 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerMarketCache consumer4 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerMarketCache consumer5 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        Thread producerThread1 = new Thread(new ThreadStart(producer1.run));
        Thread consumerThread1 = new Thread(new ThreadStart(consumer1.run));
        Thread consumerThread2 = new Thread(new ThreadStart(consumer2.run));
        Thread consumerThread3 = new Thread(new ThreadStart(consumer3.run));
        Thread consumerThread4 = new Thread(new ThreadStart(consumer4.run));
        Thread consumerThread5 = new Thread(new ThreadStart(consumer5.run));
        DateTime start = DateTime.Now;
        producerThread1.Start();
        consumerThread1.Start();
        consumerThread2.Start();
        consumerThread3.Start();
        consumerThread4.Start();
        consumerThread5.Start();
        producerThread1.Join();
        consumerThread1.Join();
        consumerThread2.Join();
        consumerThread3.Join();
        consumerThread4.Join();
        consumerThread5.Join();
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a SyncCacheWithLock with 1 Producer and Poll with 5 Consumers,, snapshot time,"
        + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }




    /**
* testQueueWith1ProducerAnd1Consumer.
*
* @return
*/
    public static long testCacheWithLockWith5ProducersAnd1Consumer()
    {
        Cache cache = new SyncCacheWithLock();
        ProducerCache producer1 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer2 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer3 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer4 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer5 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerStockCache consumer1 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS);
        Thread producerThread1 = new Thread(new ThreadStart(producer1.run));
        Thread producerThread2 = new Thread(new ThreadStart(producer2.run));
        Thread producerThread3 = new Thread(new ThreadStart(producer3.run));
        Thread producerThread4 = new Thread(new ThreadStart(producer4.run));
        Thread producerThread5 = new Thread(new ThreadStart(producer5.run));
        Thread consumerThread1 = new Thread(new ThreadStart(consumer1.run));
        DateTime start = DateTime.Now;
        producerThread1.Start();
        producerThread2.Start();
        producerThread3.Start();
        producerThread4.Start();
        producerThread5.Start();
        consumerThread1.Start();
        producerThread1.Join();
        producerThread2.Join();
        producerThread3.Join();
        producerThread4.Join();
        producerThread5.Join();
        consumerThread1.Join();
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a SyncCacheWithLock with 5 Producers and Poll with 1 Consumer,, snapshot time,"
        + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }



    /**
* testQueueWith1ProducerAnd1Consumer.
*
* @return
*/
    public static long testCacheWithLockWith5ProducersAnd5Consumers()
    {
        Cache cache = new SyncCacheWithLock();
        ProducerCache producer1 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer2 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer3 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer4 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer5 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerStockCache consumer1 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerStockCache consumer2 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerStockCache consumer3 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerMarketCache consumer4 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerMarketCache consumer5 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        Thread producerThread1 = new Thread(new ThreadStart(producer1.run));
        Thread producerThread2 = new Thread(new ThreadStart(producer2.run));
        Thread producerThread3 = new Thread(new ThreadStart(producer3.run));
        Thread producerThread4 = new Thread(new ThreadStart(producer4.run));
        Thread producerThread5 = new Thread(new ThreadStart(producer5.run));
        Thread consumerThread1 = new Thread(new ThreadStart(consumer1.run));
        Thread consumerThread2 = new Thread(new ThreadStart(consumer2.run));
        Thread consumerThread3 = new Thread(new ThreadStart(consumer3.run));
        Thread consumerThread4 = new Thread(new ThreadStart(consumer4.run));
        Thread consumerThread5 = new Thread(new ThreadStart(consumer5.run));
        DateTime start = DateTime.Now;
        producerThread1.Start();
        producerThread2.Start();
        producerThread3.Start();
        producerThread4.Start();
        producerThread5.Start();
        consumerThread1.Start();
        consumerThread2.Start();
        consumerThread3.Start();
        consumerThread4.Start();
        consumerThread5.Start();
        producerThread1.Join();
        producerThread2.Join();
        producerThread3.Join();
        producerThread4.Join();
        producerThread5.Join();
        consumerThread1.Join();
        consumerThread2.Join();
        consumerThread3.Join();
        consumerThread4.Join();
        consumerThread5.Join();
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a SyncCacheWithLock with 5 Producers and Poll with 5 Consumers,, snapshot time,"
        + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }




    /**
* testQueueWith1ProducerAnd1Consumer.
*
* @return
*/
    public static long testCacheWithSyncMapWith1ProducerAnd5Consumers()
    {
        Cache cache = new SyncCacheWithSyncMap();
        ProducerCache producer1 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS);
        ConsumerStockCache consumer1 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerStockCache consumer2 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerStockCache consumer3 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerMarketCache consumer4 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerMarketCache consumer5 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        Thread producerThread1 = new Thread(new ThreadStart(producer1.run));
        Thread consumerThread1 = new Thread(new ThreadStart(consumer1.run));
        Thread consumerThread2 = new Thread(new ThreadStart(consumer2.run));
        Thread consumerThread3 = new Thread(new ThreadStart(consumer3.run));
        Thread consumerThread4 = new Thread(new ThreadStart(consumer4.run));
        Thread consumerThread5 = new Thread(new ThreadStart(consumer5.run));
        DateTime start = DateTime.Now;
        producerThread1.Start();
        consumerThread1.Start();
        consumerThread2.Start();
        consumerThread3.Start();
        consumerThread4.Start();
        consumerThread5.Start();
        producerThread1.Join();
        consumerThread1.Join();
        consumerThread2.Join();
        consumerThread3.Join();
        consumerThread4.Join();
        consumerThread5.Join();
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a SyncCacheWithLock with 1 Producer and Poll with 5 Consumers,, snapshot time,"
        + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }




    /**
* testQueueWith1ProducerAnd1Consumer.
*
* @return
*/
    public static long testCacheWithSyncMapWith5ProducersAnd1Consumer()
    {
        Cache cache = new SyncCacheWithSyncMap();
        ProducerCache producer1 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer2 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer3 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer4 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer5 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerStockCache consumer1 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS);
        Thread producerThread1 = new Thread(new ThreadStart(producer1.run));
        Thread producerThread2 = new Thread(new ThreadStart(producer2.run));
        Thread producerThread3 = new Thread(new ThreadStart(producer3.run));
        Thread producerThread4 = new Thread(new ThreadStart(producer4.run));
        Thread producerThread5 = new Thread(new ThreadStart(producer5.run));
        Thread consumerThread1 = new Thread(new ThreadStart(consumer1.run));
        DateTime start = DateTime.Now;
        producerThread1.Start();
        producerThread2.Start();
        producerThread3.Start();
        producerThread4.Start();
        producerThread5.Start();
        consumerThread1.Start();
        producerThread1.Join();
        producerThread2.Join();
        producerThread3.Join();
        producerThread4.Join();
        producerThread5.Join();
        consumerThread1.Join();
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a SyncCacheWithLock with 5 Producers and Poll with 1 Consumer,, snapshot time,"
        + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }



    /**
* testQueueWith1ProducerAnd1Consumer.
*
* @return
*/
    public static long testCacheWithSyncMapWith5ProducersAnd5Consumers()
    {
        Cache cache = new SyncCacheWithSyncMap();
        ProducerCache producer1 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer2 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer3 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer4 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ProducerCache producer5 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerStockCache consumer1 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerStockCache consumer2 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerStockCache consumer3 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerMarketCache consumer4 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        ConsumerMarketCache consumer5 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        Thread producerThread1 = new Thread(new ThreadStart(producer1.run));
        Thread producerThread2 = new Thread(new ThreadStart(producer2.run));
        Thread producerThread3 = new Thread(new ThreadStart(producer3.run));
        Thread producerThread4 = new Thread(new ThreadStart(producer4.run));
        Thread producerThread5 = new Thread(new ThreadStart(producer5.run));
        Thread consumerThread1 = new Thread(new ThreadStart(consumer1.run));
        Thread consumerThread2 = new Thread(new ThreadStart(consumer2.run));
        Thread consumerThread3 = new Thread(new ThreadStart(consumer3.run));
        Thread consumerThread4 = new Thread(new ThreadStart(consumer4.run));
        Thread consumerThread5 = new Thread(new ThreadStart(consumer5.run));
        DateTime start = DateTime.Now;
        producerThread1.Start();
        producerThread2.Start();
        producerThread3.Start();
        producerThread4.Start();
        producerThread5.Start();
        consumerThread1.Start();
        consumerThread2.Start();
        consumerThread3.Start();
        consumerThread4.Start();
        consumerThread5.Start();
        producerThread1.Join();
        producerThread2.Join();
        producerThread3.Join();
        producerThread4.Join();
        producerThread5.Join();
        consumerThread1.Join();
        consumerThread2.Join();
        consumerThread3.Join();
        consumerThread4.Join();
        consumerThread5.Join();
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a SyncCacheWithLock with 5 Producers and Poll with 5 Consumers,, snapshot time,"
        + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testFibo1.
 *
 * @return
 */
    public static long testFibo()
    {
        DateTime start = DateTime.Now;
        for (int i = NB_FIBO_TESTS; i != 0; i--)
        {
            int fibo = ThreadFibo.fib(FIBO_N);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ThreadingTest], Invoke of " + NB_FIBO_TESTS + " int fibo = ThreadFibo.fib(" + FIBO_N + ")  ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

    /**
     * testFibo2.
     *
     * @return
     */
    public static long testFiboWith2Threads()
    {
        DateTime start = DateTime.Now;
        for (int i = NB_FIBO_TESTS; i != 0; i--)
        {
            int fibo = ThreadFibo.fibWith2Threads(FIBO_N);
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[ThreadingTest], Invoke of " + NB_FIBO_TESTS + " int fibo = ThreadFibo.fibWith2Threads(" + FIBO_N + ")  ,, snapshot time,"
            + executionTime.TotalMilliseconds);
        return (long)executionTime.TotalMilliseconds;
    }

	
	    /**
     * main.
     *
     * @param args
     */
    public static void main(string[] args)
    {
	    int nbTests = (args != null && args.Length >= 1) ? int.Parse(args[0]) : NB_TESTS;
	    int nbOfExclusionMinMax = (args != null && args.Length >= 2) ? int.Parse(args[1]) : NB_OF_EXCLUSION_MIN_MAX;

	    List<long> executionTimes = new List<long>(nbTests);
        
        
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ThreadingTest.testQueueWith1ProducerAnd1Consumer());
        executionTimes.Sort();
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a queue with 1 Producer and Poll with 1 Consumer,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ThreadingTest.testQueueWith1ProducerAnd5Consumers());
        executionTimes.Sort();
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a queue with 1 Producer and Poll with 5 Consumers,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
       
        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ThreadingTest.testQueueWith5ProducersAnd1Consumer());
        executionTimes.Sort();
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a queue with 5 Producer and Poll with 1 Consumer,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ThreadingTest.testQueueWith5ProducersAnd5Consumers());
        executionTimes.Sort();
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a queue with 5 Producers and Poll with 5 Consumers,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
		/*
        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ThreadingTest.testCacheWithSyncMapWith1ProducerAnd5Consumers());
        executionTimes.Sort();
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a SyncCacheWithSyncMap with 1 Producer and Poll with 5 Consumers,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ThreadingTest.testCacheWithSyncMapWith5ProducersAnd1Consumer());
        executionTimes.Sort();
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a SyncCacheWithSyncMap with 5 Producers and Poll with 1 Consumer,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ThreadingTest.testCacheWithSyncMapWith5ProducersAnd5Consumers());
        executionTimes.Sort();
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a SyncCacheWithSyncMap with 5 Producers and Poll with 5 Consumers,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        */
        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ThreadingTest.testCacheWithLockWith1ProducerAnd5Consumers());
        executionTimes.Sort();
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a SyncCacheWithLock with 1 Producer and Poll with 5 Consumers,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ThreadingTest.testCacheWithLockWith5ProducersAnd1Consumer());
        executionTimes.Sort();
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a SyncCacheWithLock with 5 Producers and Poll with 1 Consumer,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ThreadingTest.testCacheWithLockWith5ProducersAnd5Consumers());
        executionTimes.Sort();
        Console.WriteLine("[ThreadingTest], Add " + NB_THREADING_TESTS + " object in a SyncCacheWithLock with 5 Producers and Poll with 5 Consumers,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();
        

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ThreadingTest.testFibo());
        executionTimes.Sort();
        Console.WriteLine("[ThreadingTest], Invoke of " + NB_FIBO_TESTS + " int fibo = ThreadFibo.fib1(" + FIBO_N + ")  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
    	
	    for (int i = nbTests; i != 0; i--)
            executionTimes.Add(ThreadingTest.testFiboWith2Threads());
        executionTimes.Sort();
        Console.WriteLine("[ThreadingTest], Invoke of " + NB_FIBO_TESTS + " int fibo = ThreadFibo.fibWith2Threads(" + FIBO_N + ")  ,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();
        
	
    }


}



class Producer
{
    private int test;
    private Queue queue;

    public Producer(Queue aQueue, int aTest)
    {
        this.test = aTest;
        this.queue = aQueue;
    }

    public void run()
    {
        for (int i = this.test; i != 0; i--)
        {
            queue.Enqueue(ThreadingTest.NB_TO_CALCULATE_IN_THREAD_QUEUE);
        }
    }
}



class Consumer
{
    private int test;
    private Queue queue;
    private int count;

    public Consumer(Queue aQueue, int aTest)
    {
        this.test = aTest;
        this.queue = aQueue;
    }

    public void run()
    {
        for (int i = this.test; i != 0; i--)
        {
            // Possible just if you are 1 Consumer because it is not thread safe
            /*
            while (queue.Count != 0)
            {
                String s = (String) queue.Dequeue();
            }
            */
            bool isDone = false;
            while (!isDone)
            {
                int j = 0;
                try
                {
                    {
                        j = (int) queue.Dequeue();
                        isDone = true;
                    }
                }
                catch (InvalidOperationException e)
                {
                }

                // Just add to do something after polling, this code costs the same in java or C#
                int intResult = 1;
                int k = 1;
                while (k < j)
                {
                    intResult -= k++;
                    intResult += k++;
                    intResult *= k++;
                    intResult /= k++;
                }
                this.count += intResult;
            }
        }
    }
}



/**
 * Feed.
 *
 * @author Alexandre
 *
 */
class Feed
{
    private String stock;
    private String market;
    private int way;
    private int quantity;
    private double price;
    
    /**
     * Constructor.
     *
     * @param aStock
     * @param aMarket
     * @param aWay
     * @param aQuantity
     * @param aPrice
     */
    public Feed(String aStock, String aMarket, int aWay, int aQuantity, double aPrice)
    {
	    this.stock = aStock;
	    this.market = aMarket;
	    this.way = aWay;
	    this.quantity = aQuantity;
	    this.price = aPrice;
    }

    public String Stock
    {
        get { return stock; }
        set { stock = value; }
    }

    public String Market
    {
        get { return market; }
        set { market = value; }
    }

    public int Way
    {
        get { return way; }
        set { way = value; }
    }

    
    public int Quantity
    {
        get { return quantity; }
        set { quantity = value; }
    }


    public double Price
    {
        get { return price; }
        set { price = value; }
    }

}



/**
 * Cache.
 *
 * @author Alexandre
 *
 */
interface Cache
{

    /**
     * addFeed.
     *
     * @param aFeed
     */
    void addFeedInStockMap(Feed aFeed);

    /**
     * addFeed.
     *
     * @param aFeed
     */
    void addFeedInMarketMap(Feed aFeed);

    /**
     * addFeed.
     *
     * @param aFeed
     */
    void addFeed(Feed aFeed);

    /**
     * getFeed.
     *
     * @param stock
     * @return
     */
    Feed getFeedFromStockMap(String stock);

    /**
     * getFeed.
     *
     * @param stock
     * @return
     */
     List<String> getStockFromMarketMap(String market);

    /**
     * getFeed.
     *
     * @param stock
     * @return
     */
    List<Feed> getFeedFromMarketMap(String market);

}


/**
 * SyncCacheWithLock.
 *
 * @author Alexandre
 *
 */

class SyncCacheWithLock : Cache
{
    private Hashtable stockMap = new Hashtable();

    private Hashtable marketMap = new Hashtable();

    public static Object[] waitObject = new Object[0];
    //private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    //private final Lock readLock = this.rwl.readLock();

   // private final Lock writeLock = this.rwl.writeLock();

    /**
     * addFeed.
     *
     * @param aFeed
     */
    public void addFeedInStockMap(Feed aFeed)
    {
        Monitor.Enter(waitObject);
	    try
	    {
            if (this.stockMap.ContainsKey(aFeed.Stock))
                this.stockMap.Remove(aFeed.Stock);
	        this.stockMap.Add(aFeed.Stock, aFeed);
	    }
	    finally
	    {
            Monitor.Exit(waitObject);
	    }
    }

    /**
     * addFeed.
     *
     * @param aFeed
     */
    public void addFeedInMarketMap(Feed aFeed)
    {
        Monitor.Enter(waitObject);
	    try
	    {
            List<String> stockList = (List<String>) this.marketMap[aFeed.Market];
	        if (stockList != null)
	        {
		    stockList.Add(aFeed.Stock);
	        }
	        else
	        {
		    stockList = new List<String>();
		    stockList.Add(aFeed.Stock);
	        }
	    }
	    finally
	    {
            Monitor.Exit(waitObject);
	    }
    }

    /**
     * addFeed.
     * 
     * @param aFeed
     */
    public void addFeed(Feed aFeed)
    {
        Monitor.Enter(waitObject);
	    try
	    {
            if (this.stockMap.ContainsKey(aFeed.Stock))
                this.stockMap.Remove(aFeed.Stock);
	        this.stockMap.Add(aFeed.Stock, aFeed);
	        List<String> stockList =  (List<String>) this.marketMap[aFeed.Market];
	        if (stockList != null)
	        {
                stockList.Add(aFeed.Stock);
	        }
	        else
	        {
		    stockList = new List<String>();
		    stockList.Add(aFeed.Stock);
	        }
	    }
	    finally
	    {
            Monitor.Exit(waitObject);
	    }
    }

    /**
     * getFeed.
     *
     * @param stock
     * @return
     */
    public Feed getFeedFromStockMap(String stock)
    {
        Monitor.Enter(waitObject);
	    try
	    {
	        Feed feed = (Feed) this.stockMap[stock];
	        return feed;
	    }
	    finally
	    {
            Monitor.Exit(waitObject);
	    }
    }

    /**
     * getFeed.
     *
     * @param stock
     * @return
     */
    public List<String> getStockFromMarketMap(String market)
    {
        Monitor.Enter(waitObject);
	    try
	    {
	        List<String> stockList = (List<String>) this.marketMap[market];
	        return stockList;
	    }
	    finally
	    {
            Monitor.Exit(waitObject);
	    }

    }

    /**
     * getFeed.
     *
     * @param stock
     * @return
     */
    public List<Feed> getFeedFromMarketMap(String market)
    {
	    List<Feed> feedList = new List<Feed>();
        Monitor.Enter(waitObject);
	    try
	    {
            List<String> stockList = (List<String>) this.marketMap[market];
	        for (int i = stockList.Count; i != 0; i--)
	        {
		    feedList.Add(this.getFeedFromStockMap(stockList[i]));
	        }
	        return feedList;
	    }
	    finally
	    {
            Monitor.Exit(waitObject);
	    }
    }
}


/**
 * SyncCacheWithSyncMap.
 *
 * @author Alexandre
 *
 */
class SyncCacheWithSyncMap : Cache
{
    private Hashtable stockMap = Hashtable.Synchronized(new Hashtable());

    private Hashtable marketMap = Hashtable.Synchronized(new Hashtable());

    private Object addFeedInStockMapLock = new Object();
    private Object addFeedLock = new Object();
    private Object getFeedFromMarketMapLock = new Object();

    /**
     * @see Cache#addFeedInStockMap(Feed)
     */
    public void addFeedInStockMap(Feed aFeed)
    {
        if (this.stockMap.ContainsKey(aFeed.Stock))
            this.stockMap.Remove(aFeed.Stock);
	    this.stockMap.Add(aFeed.Stock, aFeed);
    }

    /**
     * @see Cache#addFeedInMarketMap(Feed)
     */
    // synchronized
    public void addFeedInMarketMap(Feed aFeed)
    {
        lock (addFeedInStockMapLock)
        {
            List<String> stockList = (List<String>)this.marketMap[aFeed.Market];
            if (stockList != null)
            {
                stockList.Add(aFeed.Stock);
            }
            else
            {
                stockList = new List<String>();
                stockList.Add(aFeed.Stock);
            }
        }
    }

    /**
     * @see Cache#addFeed(Feed)
     */
    // synchronized
    public void addFeed(Feed aFeed)
    {
        lock (addFeedLock)
        {
            if (this.stockMap.ContainsKey(aFeed.Stock))
                this.stockMap.Remove(aFeed.Stock);
                this.stockMap.Add(aFeed.Stock, aFeed);
            List<String> stockList = (List<String>)this.marketMap[aFeed.Market];
            if (stockList != null)
            {
                stockList.Add(aFeed.Stock);
            }
            else
            {
                stockList = new List<String>();
                stockList.Add(aFeed.Stock);
            }
        }
    }

    /**
     * @see Cache#getFeedFromStockMap(java.lang.String)
     */
    public Feed getFeedFromStockMap(String stock)
    {
        return (Feed) this.stockMap[stock];
    }

    /**
     * @see Cache#getStockFromMarketMap(java.lang.String)
     */
    public List<String> getStockFromMarketMap(String market)
    {
        return (List<String>) this.marketMap[market];
    }

    /**
     * @see Cache#getFeedFromMarketMap(java.lang.String)
     */
    public List<Feed> getFeedFromMarketMap(String market)
    {
        List<Feed> feedList = new List<Feed>();
        lock (getFeedFromMarketMapLock)
        {
            List<String> stockList = (List<String>)this.marketMap[market];
            for (int i = 0; i < stockList.Count; i++)
            {
                feedList.Add(this.getFeedFromStockMap(stockList[i]));
            }
        }
        return feedList;
    }
}


/**
 * Producer.
 *
 * @author Alexandre
 *
 */
class ProducerCache
{
    private int test;
    private Cache cache;
    
    public ProducerCache(Cache aCache, int aTest)
    {
	    this.test = aTest;
	    this.cache = aCache;
    }

    /**
     * @see java.lang.Thread#run()
     */
    public void run()
    {
	    for (int i = this.test; i != 0; i--)
	    {
	        this.cache.addFeed(new Feed("STC+" + i, "FR" + (i%10),  1, 1000, 5.5));
	    }
    }
}

/**
 * Consumer.
 *
 * @author Alexandre
 *
 */
class ConsumerStockCache
{
    private int test;
    private Cache cache;
    
    
    /**
     * Constructor.
     *
     * @param aQueue
     * @param aTest
     */
    public ConsumerStockCache(Cache aCache, int aTest)
    {
	    this.test = aTest;
	    this.cache = aCache;
    }

    
    /**
     * @see java.lang.Thread#run()
     */
    public void run()
    {
	    for (int i = this.test; i != 0; i--)
	    {
	        Feed feed = this.cache.getFeedFromStockMap("STC+" + i);
	    }
    }
}

/**
 * ConsumerMarketCache.
 *
 * @author Alexandre
 *
 */
class ConsumerMarketCache
{
    private int test;
    private Cache cache;
    
    
    /**
     * Constructor.
     *
     * @param aQueue
     * @param aTest
     */
    public ConsumerMarketCache(Cache aCache, int aTest)
    {
	    this.test = aTest;
	    this.cache = aCache;
    }

    
    /**
     * @see java.lang.Thread#run()
     */
    public void run()
    {
	    for (int i = this.test; i != 0; i--)
	    {
	        List<String> stock = cache.getStockFromMarketMap("FR" + (i%10));
	    }
    }
}


/**
 * Fibo.
 *
 * @author Alexandre
 *
 */
class ThreadFibo
{

    /**
     * fib.
     *
     * @param n
     * @return
     */
    public static int fib(int n)
    {
        if (n < 2)
            return (1);
        return (fib(n - 2) + fib(n - 1));
    }

    public static int fibWith2Threads(int n)
    {
        if (n == 0 || n == 1) return (1);
        {
            FiboCallable fibon2 = new FiboCallable(n - 2);
            FiboCallable fibon1 = new FiboCallable(n - 1);
            Thread f1 = new Thread(new ThreadStart(fibon2.call));
            Thread f2 = new Thread(new ThreadStart(fibon1.call));
            f1.Start();
            f2.Start();
            f1.Join();
            f2.Join();
            return (fibon1.getResult() + fibon2.getResult());
        }

    }

}


sealed class FiboCallable
{
    private int n;
    private int result;

    public FiboCallable(int n)
    {
        this.n = n;
    }

    public void call()
    {
        result = ThreadFibo.fib(n);
    }

    public int getResult()
    {
        return this.result;
    }

}

