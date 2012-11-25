import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * ThreadingTest.
 * 
 * @author anavarro122404 - 25 sept. 07
 *
 *
 * <!-- $Id: ThreadingTest.java,v 1.6 2008/01/10 10:55:58 anavarro Exp $ -->.
 *
 */
public final class ThreadingTest
{
    /**
     * NB_TESTS
     */
    private static final int    NB_TESTS                 = 5;

    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    private static final int    NB_OF_EXCLUSION_MIN_MAX  = 2;

    /**
     * NB_THREADING_TESTS
     */
    private static final int    NB_THREADING_TESTS       = 1 * 1000 * 1000;

    /**
     * NB_TO_CALCULATE_IN_THREAD_QUEUE
     */
    public static final int    NB_TO_CALCULATE_IN_THREAD_QUEUE = 1000;
    
    /**
     * NB_THREADING_CACHE_TESTS
     */
    private static final int    NB_THREADING_CACHE_TESTS = 2 * 1000 * 1000;

    /**
     * NB_RECURSIVE_TESTS
     */
    private static final int    NB_FIBO_TESTS            = 1;

    /**
     * FIBO_N
     */
    private static final int    FIBO_N                   = 41;

    /**
     * averageTimeWithoutMinMax.
     * 
     * @param executionTimes
     * @param numberOfMinMaxToRemove
     * @return
     */
    @SuppressWarnings("boxing")
    public static long averageTimeWithoutMinMax(final List<Long> executionTimes, final int numberOfMinMaxToRemove)
    {
        long average = 0;
        final int minMax = (numberOfMinMaxToRemove > 0) ? numberOfMinMaxToRemove : 0; 
        if (minMax != 0)
        {
            Collections.sort(executionTimes);
        }
        for (int i = minMax; i < executionTimes.size() - minMax; i++)
        {
            average += executionTimes.get(i);
        }
        average = average / (executionTimes.size() - minMax * 2);
        return average;
    }
    
    
    /**
     * relativeDeviationTimeWithoutMinMax.
     *
     * @param executionTimes
     * @param numberOfMinMaxToRemove
     * @return
     */
    @SuppressWarnings("boxing")
    public static double relativeDeviationTimeWithoutMinMax(final List<Long> executionTimes, final int numberOfMinMaxToRemove)
    {
        final long averageTimeWithoutMinMax = averageTimeWithoutMinMax(executionTimes, numberOfMinMaxToRemove);
        long deviation = 0;
        final int minMax = (numberOfMinMaxToRemove > 0) ? numberOfMinMaxToRemove : 0; 
        if (minMax != 0)
        {
            Collections.sort(executionTimes);
        }
        for (int i = minMax; i < executionTimes.size() - minMax; i++)
        {
            deviation += Math.pow(executionTimes.get(i) - averageTimeWithoutMinMax, 2);
        }
        return ((int) ((Math.sqrt(deviation / (executionTimes.size() - 2 * minMax)) / averageTimeWithoutMinMax * 100) * 100)) / 100d;
    }    
    

    /**
     * testQueueWith1ProducerAnd1Consumer.
     * 
     * @return
     */
    public static long testQueueWith1ProducerAnd1Consumer()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        final Queue<Integer> queue = 
           new ConcurrentLinkedQueue<Integer>();
         //new ArrayBlockingQueue<Integer>(NB_THREADING_TESTS);
        final Producer producer1 = new Producer(queue, NB_THREADING_TESTS);
        final Consumer consumer2 = new Consumer(queue, NB_THREADING_TESTS);
        start = System.currentTimeMillis();
        producer1.start();
        consumer2.start();
        try
        {
            producer1.join();
            consumer2.join();
        }
        catch (InterruptedException e)
        {
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a queue with 1 Producer and Poll with 1 Consumer,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testQueueWith1ProducerAnd1Consumer.
     * 
     * @return
     */
    public static long testQueueWith1ProducerAnd5Consumers()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        final Queue<Integer> queue = 
            new ConcurrentLinkedQueue<Integer>();
         //new ArrayBlockingQueue<Integer>(NB_THREADING_TESTS);
        final Producer producer1 = new Producer(queue, NB_THREADING_TESTS);
        final Consumer consumer1 = new Consumer(queue, NB_THREADING_TESTS / 5);
        final Consumer consumer2 = new Consumer(queue, NB_THREADING_TESTS / 5);
        final Consumer consumer3 = new Consumer(queue, NB_THREADING_TESTS / 5);
        final Consumer consumer4 = new Consumer(queue, NB_THREADING_TESTS / 5);
        final Consumer consumer5 = new Consumer(queue, NB_THREADING_TESTS / 5);
        start = System.currentTimeMillis();
        producer1.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
        try
        {
            producer1.join();
            consumer1.join();
            consumer2.join();
            consumer3.join();
            consumer4.join();
            consumer5.join();
        }
        catch (InterruptedException e)
        {
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a queue with 1 Producer and Poll with 5 Consumers,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testQueueWith1ProducerAnd1Consumer.
     * 
     * @return
     */
    public static long testQueueWith5ProducersAnd1Consumer()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        final Queue<Integer> queue = 
            new ConcurrentLinkedQueue<Integer>();
         //new ArrayBlockingQueue<Integer>(NB_THREADING_TESTS);
        final Producer producer1 = new Producer(queue, NB_THREADING_TESTS / 5);
        final Producer producer2 = new Producer(queue, NB_THREADING_TESTS / 5);
        final Producer producer3 = new Producer(queue, NB_THREADING_TESTS / 5);
        final Producer producer4 = new Producer(queue, NB_THREADING_TESTS / 5);
        final Producer producer5 = new Producer(queue, NB_THREADING_TESTS / 5);
        final Consumer consumer1 = new Consumer(queue, NB_THREADING_TESTS);
        start = System.currentTimeMillis();
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        consumer1.start();
        try
        {
            producer1.join();
            producer2.join();
            producer3.join();
            producer4.join();
            producer5.join();
            consumer1.join();
        }
        catch (InterruptedException e)
        {
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a queue with 5 Producers and Poll with 1 Consumer,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testQueueWith1ProducerAnd1Consumer.
     * 
     * @return
     */
    public static long testQueueWith5ProducersAnd5Consumers()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        final Queue<Integer> queue = 
            new ConcurrentLinkedQueue<Integer>();
         //new ArrayBlockingQueue<Integer>(NB_THREADING_TESTS);
        final Producer producer1 = new Producer(queue, NB_THREADING_TESTS / 5);
        final Producer producer2 = new Producer(queue, NB_THREADING_TESTS / 5);
        final Producer producer3 = new Producer(queue, NB_THREADING_TESTS / 5);
        final Producer producer4 = new Producer(queue, NB_THREADING_TESTS / 5);
        final Producer producer5 = new Producer(queue, NB_THREADING_TESTS / 5);
        final Consumer consumer1 = new Consumer(queue, NB_THREADING_TESTS / 5);
        final Consumer consumer2 = new Consumer(queue, NB_THREADING_TESTS / 5);
        final Consumer consumer3 = new Consumer(queue, NB_THREADING_TESTS / 5);
        final Consumer consumer4 = new Consumer(queue, NB_THREADING_TESTS / 5);
        final Consumer consumer5 = new Consumer(queue, NB_THREADING_TESTS / 5);
        start = System.currentTimeMillis();
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
        try
        {
            producer1.join();
            producer2.join();
            producer3.join();
            producer4.join();
            producer5.join();
            consumer1.join();
            consumer2.join();
            consumer3.join();
            consumer4.join();
            consumer5.join();
        }
        catch (InterruptedException e)
        {
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a queue with 5 Producers and Poll with 5 Consumers,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCacheWith5ProducersAnd5Consumers.
     * 
     * @return
     */
    public static long testCacheWithSyncMapWith1ProducerAnd5Consumers()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        final Cache cache = new SyncCacheWithSyncMap();
        final ProducerCache producer1 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS);
        final ConsumerStockCache consumer1 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerStockCache consumer2 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerStockCache consumer3 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerMarketCache consumer4 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerMarketCache consumer5 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        start = System.currentTimeMillis();
        producer1.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
        try
        {
            producer1.join();
            consumer1.join();
            consumer2.join();
            consumer3.join();
            consumer4.join();
            consumer5.join();
        }
        catch (InterruptedException e)
        {
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a SyncCacheWithSyncMap with 1 Producer and Poll with 5 Consumers,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCacheWith5ProducersAnd5Consumers.
     * 
     * @return
     */
    public static long testCacheWithSyncMapWith5ProducersAnd1Consumer()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        final Cache cache = new SyncCacheWithSyncMap();
        final ProducerCache producer1 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer2 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer3 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer4 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer5 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerStockCache consumer1 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS);
        start = System.currentTimeMillis();
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        consumer1.start();
        try
        {
            producer1.join();
            producer2.join();
            producer3.join();
            producer4.join();
            producer5.join();
            consumer1.join();
        }
        catch (InterruptedException e)
        {
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a SyncCacheWithSyncMap with 5 Producers and Poll with 1 Consumers,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCacheWith5ProducersAnd5Consumers.
     * 
     * @return
     */
    public static long testCacheWithSyncMapWith5ProducersAnd5Consumers()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        final Cache cache = new SyncCacheWithSyncMap();
        final ProducerCache producer1 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer2 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer3 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer4 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer5 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerStockCache consumer1 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerStockCache consumer2 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerStockCache consumer3 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerMarketCache consumer4 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerMarketCache consumer5 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        start = System.currentTimeMillis();
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
        try
        {
            producer1.join();
            producer2.join();
            producer3.join();
            producer4.join();
            producer5.join();
            consumer1.join();
            consumer2.join();
            consumer3.join();
            consumer4.join();
            consumer5.join();
        }
        catch (InterruptedException e)
        {
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a SyncCacheWithSyncMap with 5 Producers and Poll with 5 Consumers,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCacheWith5ProducersAnd5Consumers.
     * 
     * @return
     */
    public static long testCacheWithLockWithSyncMapWith1ProducerAnd5Consumers()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        final Cache cache = new SyncCacheWithLock();
        final ProducerCache producer1 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS);
        final ConsumerStockCache consumer1 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerStockCache consumer2 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerStockCache consumer3 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerMarketCache consumer4 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerMarketCache consumer5 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        start = System.currentTimeMillis();
        producer1.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
        try
        {
            producer1.join();
            consumer1.join();
            consumer2.join();
            consumer3.join();
            consumer4.join();
            consumer5.join();
        }
        catch (InterruptedException e)
        {
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a SyncCacheWithLock with 1 Producer and Poll with 5 Consumers,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCacheWith5ProducersAnd5Consumers.
     * 
     * @return
     */
    public static long testCacheWithLockWith5ProducersAnd1Consumer()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        final Cache cache = new SyncCacheWithLock();
        final ProducerCache producer1 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer2 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer3 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer4 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer5 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerStockCache consumer1 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS);
        start = System.currentTimeMillis();
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        consumer1.start();
        try
        {
            producer1.join();
            producer2.join();
            producer3.join();
            producer4.join();
            producer5.join();
            consumer1.join();
        }
        catch (InterruptedException e)
        {
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a SyncCacheWithLock with 5 Producers and Poll with 1 Consumers,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testCacheWith5ProducersAnd5Consumers.
     * 
     * @return
     */
    public static long testCacheWithLockWith5ProducersAnd5Consumers()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        final Cache cache = new SyncCacheWithLock();
        final ProducerCache producer1 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer2 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer3 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer4 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ProducerCache producer5 = new ProducerCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerStockCache consumer1 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerStockCache consumer2 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerStockCache consumer3 = new ConsumerStockCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerMarketCache consumer4 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        final ConsumerMarketCache consumer5 = new ConsumerMarketCache(cache, NB_THREADING_CACHE_TESTS / 5);
        start = System.currentTimeMillis();
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
        try
        {
            producer1.join();
            producer2.join();
            producer3.join();
            producer4.join();
            producer5.join();
            consumer1.join();
            consumer2.join();
            consumer3.join();
            consumer4.join();
            consumer5.join();
        }
        catch (InterruptedException e)
        {
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a SyncCacheWithLock with 5 Producers and Poll with 5 Consumers,, snapshot time," + executionTime);
        return executionTime;
    }

    /**
     * testFibo2.
     * 
     * @return
     */
    public static long testFibo()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_FIBO_TESTS; i-- != 0;)
        {
            @SuppressWarnings("unused")
            int fibo = ThreadingFibo.fib(FIBO_N);
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ThreadingTest], Invoke of " + NB_FIBO_TESTS + " int fibo = ThreadFibo.fib(" + FIBO_N + "),, snapshot time,"
                + executionTime);
        return executionTime;
    }

    /**
     * testFibo2.
     * 
     * @return
     */
    public static long testFibWith2Threads()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        for (int i = NB_FIBO_TESTS; i-- != 0;)
        {
            @SuppressWarnings("unused")
            int fibo = ThreadingFibo.fibWith2Threads(FIBO_N);
        }
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[ThreadingTest], Invoke of " + NB_FIBO_TESTS + " int fibo = ThreadFibo.fibWith2Threads(" + FIBO_N + "),, snapshot time,"
                + executionTime);
        return executionTime;
    }

    
    /**
     * main.
     * 
     * @param args
     */
    @SuppressWarnings("boxing")
    public static void main(final String[] args)
    {
        final int nbTests = (args != null && args.length >= 1 && args[0] != null) ?  Integer.valueOf(args[0]): NB_TESTS;
        final int nbOfExclusionMinMax = (args != null && args.length >= 2 && args[1] != null) ? Integer.valueOf(args[1]) : NB_OF_EXCLUSION_MIN_MAX;

        final List<Long> executionTimes = new ArrayList<Long>(nbTests);
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ThreadingTest.testQueueWith1ProducerAnd1Consumer());
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a queue with 1 Producer and Poll with 1 Consumer,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        /*
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ThreadingTest.testQueueWith1ProducerAnd5Consumers());
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a queue with 1 Producer and Poll with 5 Consumers,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        */
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ThreadingTest.testQueueWith5ProducersAnd1Consumer());
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a queue with 5 Producers and Poll with 1 Consumer,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ThreadingTest.testQueueWith5ProducersAnd5Consumers());
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a queue with 5 Producers and Poll with 5 Consumers,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time,"
                + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ThreadingTest.testCacheWithSyncMapWith1ProducerAnd5Consumers());
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a SyncCacheWithSyncMap with 1 Producer and Poll with 5 Consumers,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ThreadingTest.testCacheWithSyncMapWith5ProducersAnd1Consumer());
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a SyncCacheWithSyncMap with 5 Producers and Poll with 1 Consumer,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ThreadingTest.testCacheWithSyncMapWith5ProducersAnd5Consumers());
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a SyncCacheWithSyncMap with 5 Producers and Poll with 5 Consumers,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ThreadingTest.testCacheWithLockWith5ProducersAnd1Consumer());
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a SyncCacheWithLock with 1 Producer and Poll with 5 Consumers,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ThreadingTest.testCacheWithLockWith5ProducersAnd1Consumer());
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a SyncCacheWithLock with 5 Producers and Poll with 1 Consumer,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ThreadingTest.testCacheWithLockWith5ProducersAnd5Consumers());
        System.out.println("[ThreadingTest], Add " + NB_THREADING_TESTS
                + " object in a SyncCacheWithLock with 5 Producers and Poll with 5 Consumers,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ThreadingTest.testFibo());
        System.out.println("[ThreadingTest], Invoke of " + NB_FIBO_TESTS + " int fibo = ThreadFibo.fib2(" + FIBO_N + "),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(ThreadingTest.testFibWith2Threads());
        System.out.println("[ThreadingTest], Invoke of " + NB_FIBO_TESTS + " int fibo = ThreadFibo.fibWith2Threads(" + FIBO_N + "),, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
    }


}

/**
 * Producer.
 * 
 * @author Alexandre
 * 
 */
final class Producer extends Thread
{
    private int            test;
    private Queue<Integer> queue;

    public Producer(final Queue<Integer> aQueue, final int aTest)
    {
        super();
        this.test = aTest;
        this.queue = aQueue;
    }

    /**
     * @see java.lang.Thread#run()
     */
    @Override
    public void run()
    {
        for (int i = this.test; i != 0; i--)
        {
            while (!this.queue.offer(ThreadingTest.NB_TO_CALCULATE_IN_THREAD_QUEUE))
            {
                this.queue.offer(ThreadingTest.NB_TO_CALCULATE_IN_THREAD_QUEUE);
            }
        }
    }
}

/**
 * Consumer.
 * 
 * @author Alexandre
 * 
 */
final class Consumer extends Thread
{
    private int            test;
    private Queue<Integer> queue;
    private int count;

    /**
     * Constructor.
     * 
     * @param aQueue
     * @param aTest
     */
    public Consumer(final Queue<Integer> aQueue, final int aTest)
    {
        super();
        this.test = aTest;
        this.queue = aQueue;
    }

    /**
     * @see java.lang.Thread#run()
     */
    @Override
    public void run()
    {
        for (int i = this.test; i != 0; i--)
        {
            Integer j = this.queue.poll();
            while (j == null)
            {
                j = this.queue.poll();
            }
            
            // Just add to do something after polling, this code costs the same in java or C#
            int intResult = 1; 
            int k = 1;
            while (k < j.intValue())
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

/**
 * Feed.
 * 
 * @author Alexandre
 * 
 */
final class Feed
{
    private String stock;
    private String market;
    private int    way;
    private int    quantity;
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
    public Feed(final String aStock, final String aMarket, final int aWay, final int aQuantity, final double aPrice)
    {
        super();
        this.stock = aStock;
        this.market = aMarket;
        this.way = aWay;
        this.quantity = aQuantity;
        this.price = aPrice;
    }

    /**
     * Get the field market
     * 
     * @return the market
     */
    public String getMarket()
    {
        return this.market;
    }

    /**
     * Set the field market
     * 
     * @param aMarket
     *            the market to set
     */
    public void setMarket(final String aMarket)
    {
        this.market = aMarket;
    }

    /**
     * Get the field stock
     * 
     * @return the stock
     */
    public String getStock()
    {
        return this.stock;
    }

    /**
     * Set the field stock
     * 
     * @param aStock
     *            the stock to set
     */
    public void setStock(final String aStock)
    {
        this.stock = aStock;
    }

    /**
     * Get the field way
     * 
     * @return the way
     */
    public int getWay()
    {
        return this.way;
    }

    /**
     * Set the field way
     * 
     * @param aWay
     *            the way to set
     */
    public void setWay(final int aWay)
    {
        this.way = aWay;
    }

    /**
     * Get the field quantity
     * 
     * @return the quantity
     */
    public int getQuantity()
    {
        return this.quantity;
    }

    /**
     * Set the field quantity
     * 
     * @param aQuantity
     *            the quantity to set
     */
    public void setQuantity(int aQuantity)
    {
        this.quantity = aQuantity;
    }

    /**
     * Get the field price
     * 
     * @return the price
     */
    public double getPrice()
    {
        return this.price;
    }

    /**
     * Set the field price
     * 
     * @param aPrice
     *            the price to set
     */
    public void setPrice(final double aPrice)
    {
        this.price = aPrice;
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
    public abstract void addFeedInStockMap(final Feed aFeed);

    /**
     * addFeed.
     * 
     * @param aFeed
     */
    public abstract void addFeedInMarketMap(final Feed aFeed);

    /**
     * addFeed.
     * 
     * @param aFeed
     */
    public abstract void addFeed(final Feed aFeed);

    /**
     * getFeed.
     * 
     * @param stock
     * @return
     */
    public abstract Feed getFeedFromStockMap(final String stock);

    /**
     * getFeed.
     * 
     * @param stock
     * @return
     */
    public abstract List<String> getStockFromMarketMap(final String market);

    /**
     * getFeed.
     * 
     * @param stock
     * @return
     */
    public abstract List<Feed> getFeedFromMarketMap(final String market);

}

/**
 * SyncCacheWithLock.
 * 
 * @author Alexandre
 * 
 */
final class SyncCacheWithLock implements Cache
{
    private Map<String, Feed>            stockMap  = new HashMap<String, Feed>();

    private Map<String, List<String>>    marketMap = new HashMap<String, List<String>>();

    private final ReentrantReadWriteLock rwl       = new ReentrantReadWriteLock();

    private final Lock                   readLock  = this.rwl.readLock();

    private final Lock                   writeLock = this.rwl.writeLock();

    /**
     * addFeed.
     * 
     * @param aFeed
     */
    public void addFeedInStockMap(final Feed aFeed)
    {
        this.writeLock.lock();
        try
        {
            if (this.stockMap.containsKey(aFeed.getStock()))
                this.stockMap.remove(aFeed.getStock());
            this.stockMap.put(aFeed.getStock(), aFeed);
        }
        finally
        {
            this.writeLock.unlock();
        }
    }

    /**
     * addFeed.
     * 
     * @param aFeed
     */
    public void addFeedInMarketMap(final Feed aFeed)
    {
        this.writeLock.lock();
        try
        {
            List<String> stockList = this.marketMap.get(aFeed.getMarket());
            if (stockList != null)
            {
                stockList.add(aFeed.getStock());
            }
            else
            {
                stockList = new ArrayList<String>();
                stockList.add(aFeed.getStock());
            }
        }
        finally
        {
            this.writeLock.unlock();
        }
    }

    /**
     * addFeed.
     * 
     * @param aFeed
     */
    public void addFeed(final Feed aFeed)
    {
        this.writeLock.lock();
        try
        {
            if (this.stockMap.containsKey(aFeed.getStock()))
                this.stockMap.remove(aFeed.getStock());
            this.stockMap.put(aFeed.getStock(), aFeed);
            List<String> stockList = this.marketMap.get(aFeed.getMarket());
            if (stockList != null)
            {
                stockList.add(aFeed.getStock());
            }
            else
            {
                stockList = new ArrayList<String>();
                stockList.add(aFeed.getStock());
            }
        }
        finally
        {
            this.writeLock.unlock();
        }
    }

    /**
     * getFeed.
     * 
     * @param stock
     * @return
     */
    public Feed getFeedFromStockMap(final String stock)
    {
        this.readLock.lock();
        try
        {
            final Feed feed = this.stockMap.get(stock);
            return feed;
        }
        finally
        {
            this.readLock.unlock();
        }
    }

    /**
     * getFeed.
     * 
     * @param stock
     * @return
     */
    public List<String> getStockFromMarketMap(final String market)
    {
        this.readLock.lock();
        try
        {
            final List<String> stockList = this.marketMap.get(market);
            return stockList;
        }
        finally
        {
            this.readLock.unlock();
        }

    }

    /**
     * getFeed.
     * 
     * @param stock
     * @return
     */
    public List<Feed> getFeedFromMarketMap(final String market)
    {
        final List<Feed> feedList = new ArrayList<Feed>();
        this.readLock.lock();
        try
        {
            final List<String> stockList = this.marketMap.get(market);
            for (int i = stockList.size(); i-- != 0;)
            {
                feedList.add(this.getFeedFromStockMap(stockList.get(i)));
            }
            return feedList;
        }
        finally
        {
            this.readLock.unlock();
        }
    }
}

/**
 * SyncCacheWithSyncMap.
 * 
 * @author Alexandre
 * 
 */
final class SyncCacheWithSyncMap implements Cache
{
    private Map<String, Feed>         stockMap  = new ConcurrentHashMap<String, Feed>();//Collections.synchronizedMap(new HashMap<String, Feed>());

    private Map<String, List<String>> marketMap  = new ConcurrentHashMap<String, List<String>>();//Collections.synchronizedMap(new HashMap<String, List<String>>());

    /**
     * @see Cache#addFeedInStockMap(Feed)
     */
    public void addFeedInStockMap(final Feed aFeed)
    {
        if (this.stockMap.containsKey(aFeed.getStock()))
            this.stockMap.remove(aFeed.getStock());
        this.stockMap.put(aFeed.getStock(), aFeed);
    }

    /**
     * @see Cache#addFeedInMarketMap(Feed)
     */
    public synchronized void addFeedInMarketMap(final Feed aFeed)
    {
        List<String> stockList = this.marketMap.get(aFeed.getMarket());
        if (stockList != null)
        {
            stockList.add(aFeed.getStock());
        }
        else
        {
            stockList = new ArrayList<String>();
            stockList.add(aFeed.getStock());
        }
    }

    /**
     * @see Cache#addFeed(Feed)
     */
    public synchronized void addFeed(final Feed aFeed)
    {
        if (this.stockMap.containsKey(aFeed.getStock()))
            this.stockMap.remove(aFeed.getStock());
        this.stockMap.put(aFeed.getStock(), aFeed);
        List<String> stockList = this.marketMap.get(aFeed.getMarket());
        if (stockList != null)
        {
            stockList.add(aFeed.getStock());
        }
        else
        {
            stockList = new ArrayList<String>();
            stockList.add(aFeed.getStock());
        }
    }

    /**
     * @see Cache#getFeedFromStockMap(java.lang.String)
     */
    public Feed getFeedFromStockMap(final String stock)
    {
        return this.stockMap.get(stock);
    }

    /**
     * @see Cache#getStockFromMarketMap(java.lang.String)
     */
    public List<String> getStockFromMarketMap(final String market)
    {
        return this.marketMap.get(market);
    }

    /**
     * @see Cache#getFeedFromMarketMap(java.lang.String)
     */
    public synchronized List<Feed> getFeedFromMarketMap(final String market)
    {
        final List<Feed> feedList = new ArrayList<Feed>();
        final List<String> stockList = this.marketMap.get(market);
        for (int i = stockList.size(); i-- != 0;)
        {
            feedList.add(this.getFeedFromStockMap(stockList.get(i)));
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
final class ProducerCache extends Thread
{
    private int   test;
    private Cache cache;

    public ProducerCache(final Cache aCache, final int aTest)
    {
        super();
        this.test = aTest;
        this.cache = aCache;
    }

    /**
     * @see java.lang.Thread#run()
     */
    @Override
    public void run()
    {
        for (int i = this.test; i != 0; i--)
        {
            this.cache.addFeed(new Feed("STC+" + i, "FR" + (i % 10), 1, 1000, 5.5));
        }
    }
}

/**
 * Consumer.
 *
 * @author Alexandre
 *
 */
final class ConsumerStockCache extends Thread
{
    private int   test;
    private Cache cache;

    /**
     * Constructor.
     *
     * @param aQueue
     * @param aTest
     */
    public ConsumerStockCache(final Cache aCache, final int aTest)
    {
        super();
        this.test = aTest;
        this.cache = aCache;
    }

    /**
     * @see java.lang.Thread#run()
     */
    @Override
    public void run()
    {
        for (int i = this.test; i != 0; i--)
        {
            @SuppressWarnings("unused")
            final Feed feed = this.cache.getFeedFromStockMap("STC+" + i);
        }
    }
}

/**
 * ConsumerMarketCache.
 *
 * @author Alexandre
 *
 */
final class ConsumerMarketCache extends Thread
{
    private int   test;
    private Cache cache;

    /**
     * Constructor.
     *
     * @param aQueue
     * @param aTest
     */
    public ConsumerMarketCache(final Cache aCache, final int aTest)
    {
        super();
        this.test = aTest;
        this.cache = aCache;
    }

    /**
     * @see java.lang.Thread#run()
     */
    @Override
    public void run()
    {
        for (int i = this.test; i != 0; i--)
        {
            @SuppressWarnings("unused")
            final List<String> stock = this.cache.getStockFromMarketMap("FR" + (i % 10));
        }
    }
}

/**
 * Fibo.
 *
 * @author Alexandre
 *
 */
final class ThreadingFibo
{
    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    /**
     * fib2.
     *
     * @param n
     * @return
     */
    public final static int fib(final int n)
    {
        if (n == 0 || n == 1)
            return 1;
        return (fib(n - 2) + fib(n - 1));
    }

    public final static int fibWith2Threads(int n)
    {
        if (n == 0 || n == 1)
        {
            return (1);
        }
        else
        {
            final Future<Integer> fibn2 = executorService.submit(new FiboCallable(n - 2));
            final Future<Integer> fibn1 = executorService.submit(new FiboCallable(n - 1));
            try
            {
                return (fibn2.get() + fibn1.get());
            }
            catch (InterruptedException e)
            {
                return 0;
            }
            catch (ExecutionException e)
            {
                return 0;
            }
        }
    }
    
    /**
     * Returns the executorService.
     * 
     * @return The executorService to return.
     */
    public static final ExecutorService getExecutorService()
    {
        return executorService;
    }
    
}

/**
 * FiboCallable.
 *
 * @author Alexandre
 *
 */
class FiboCallable implements Callable<Integer>
{
    private int n;

    /**
     * Constructor.
     * 
     * @param n
     */
    public FiboCallable(int n)
    {
        this.n = n;
    }

    /**
     * @see java.util.concurrent.Callable#call()
     */
    //@Override
    public Integer call() throws Exception
    {
        return (ThreadingFibo.fib(n));
    }
}
