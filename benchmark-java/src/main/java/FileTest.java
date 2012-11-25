import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
//import java.nio.CharBuffer;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * FileTest.
 * 
 * @author anavarro122404 - 25 sept. 07
 * 
 * 
 * <!-- $Id: FileTest.java,v 1.2 2007/11/20 13:17:31 anavarro Exp $ -->.
 * 
 */
public final class FileTest
{
    /**
     * NB_TESTS
     */
    private static final int    NB_TESTS                = 5;
    
    /**
     * NB_OF_EXCLUSION_MIN_MAX
     */
    private static final int    NB_OF_EXCLUSION_MIN_MAX = 2;
    
    /**
     * NB_FILE_TESTS
     */
    private static final int    NB_FILE_TESTS           = 1;
    
    /**
     * IO_MAX
     */
    private static final int    IO_MAX                  = 2 * 1000 * 1000;
    
    /**
     * FILE_NAME
     */
    private static final String FILE_NAME               = "test.txt";
    
    
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
     * testWriteFile.
     * 
     * @return
     */
    public static long testWriteFile()
    {
        final Random random = new Random();
        long start = System.currentTimeMillis();
        final String textLine = "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz1234567890abcdefgh\n";
        final String textLine2 = "1234567890\n";
        int randomFile = random.nextInt();
        final File deleteFile = new File(randomFile + ".txt");
        deleteFile.delete();
        final File file = new File(randomFile + ".txt");
        start = System.currentTimeMillis();
        try
        {
            final FileWriter fileWriter = new FileWriter(file);
            final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = IO_MAX; i-- != 0;)
            {
                if ((i % 2) == 0)
                {
                    bufferedWriter.write(textLine);
                }
                else
                {
                    bufferedWriter.write(textLine2);
                }
            }
            bufferedWriter.close();
            
        }
        catch (IOException e)
        {
            //
        }
        
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        deleteFile.delete();
        System.out.println("[FileTest], Write " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testWriteFile.
     * 
     * @return
     */
    public static long testWriteFileWithFlush()
    {
        final Random random = new Random();
        long start = System.currentTimeMillis();
        final String textLine = "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz1234567890abcdefgh\n";
        final String textLine2 = "1234567890\n";
        int randomFile = random.nextInt();
        final File deleteFile = new File(randomFile + ".txt");
        deleteFile.delete();
        final File file = new File(randomFile + ".txt");
        start = System.currentTimeMillis();
        try
        {
            final FileWriter fileWriter = new FileWriter(file);
            final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = IO_MAX; i-- != 0;)
            {
                if ((i % 2) == 0)
                {
                    bufferedWriter.write(textLine);
                }
                else
                {
                    bufferedWriter.write(textLine2);
                }
                bufferedWriter.flush();
            }
            bufferedWriter.close();
            
        }
        catch (IOException e)
        {
            //
        }
        
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        deleteFile.delete();
        System.out.println("[FileTest], Write with flush " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testReadFile.
     * 
     * @return
     */
    public static long testReadFile()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        try
        {
            final File file = new File(FILE_NAME);
            final FileReader inputFileReader = new FileReader(file);
            final BufferedReader bufferedReader = new BufferedReader(inputFileReader, 2048);
            @SuppressWarnings("unused")
            String line = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                //
            }
            bufferedReader.close();
        }
        catch (IOException e)
        {
            //
        }
        
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[FileTest], Read " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, snapshot time," + executionTime);
        return executionTime;
    }
    
    /**
     * testReadFile.
     * 
     * @return
     */
    public static long testOptimizedReadFile()
    {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        try
        {
            double count = 0;
            final FileChannel fileChannel = (new FileInputStream(FILE_NAME)).getChannel();
            final int size = (int) fileChannel.size();
            final MappedByteBuffer buf = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);
            final CharBuffer charBuffer = buf.asCharBuffer();
            // Just for doing the same thing of readLine
            while (charBuffer.hasRemaining())
            {
                if (charBuffer.get() == '\n')
                {
                    count++;
                }
            }
        }
        catch (IOException e)
        {
            //
        }
        
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("[FileTest], Optimized Read " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, snapshot time," + executionTime);
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
            executionTimes.add(FileTest.testWriteFile());
        System.out.println("[FileTest], Write " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(FileTest.testWriteFileWithFlush());
        System.out.println("[FileTest], Write with flush " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        // Write file for read Test
        try
        {
            final String textLine = "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz1234567890abcdefgh\n";
            final String textLine2 = "1234567890\n";
            final File file = new File(FILE_NAME);
            final FileWriter fileWriter = new FileWriter(file);
            final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = IO_MAX; i-- != 0;)
            {
                if ((i % 2) == 0)
                {
                    bufferedWriter.write(textLine);
                }
                else
                {
                    bufferedWriter.write(textLine2);
                }
            }
            bufferedWriter.close();
        }
        catch (IOException e)
        {
            //
        }
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(FileTest.testReadFile());
        System.out.println("[FileTest], Read " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, average time," + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax)
                + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes) + ", relative deviation time,"
                + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        for (int i = nbTests; i != 0; i--)
            executionTimes.add(FileTest.testOptimizedReadFile());
        System.out.println("[FileTest], Optimized Read " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + Collections.min(executionTimes) + ", max time," + Collections.max(executionTimes)
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.clear();
        
        // Delete File
        final File deleteFile = new File(FILE_NAME);
        deleteFile.delete();
    }

}
