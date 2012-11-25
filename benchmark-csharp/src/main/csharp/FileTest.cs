using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using System.Threading;


sealed class FileTest
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
     * NB_FILE_TESTS
     */
    private static int NB_FILE_TESTS = 1;
    
    /**
     * IO_MAX
     */
    private static int IO_MAX = 2 * 1000 * 1000;
    
    /**
     * FILE_NAME
     */
    private static string FILE_NAME = "test.txt";

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
     * testWriteFile.
     *
     * @return
     */
    public static long testWriteFile()
    {
        Random random = new Random();
        String fileName = random.Next() + ".txt";
        String textLine =
            "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz1234567890abcdefgh\n";
        String textLine2 =
          "1234567890\n";
        DateTime start = DateTime.Now;
		try
		{
            StreamWriter streamWriter = new StreamWriter(fileName);
            for (int i = IO_MAX; i != 0; i--)
			{
                if ((i % 2) == 0)
                {
                    streamWriter.Write(textLine);
                }
                else
                {
                    streamWriter.Write(textLine2);
                }
			}
			streamWriter.Close();
		}
		catch (IOException e)
		{
			//
		}

        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
	    Console.WriteLine("[FileTest], Write " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, snapshot time,"
            + executionTime.TotalMilliseconds);

        File.Delete(fileName);

        return (long)executionTime.TotalMilliseconds;
    }

    /**
 * testWriteFile.
 *
 * @return
 */
    public static long testWriteFileWithFlush()
    {
        Random random = new Random();
        String fileName = random.Next() + ".txt";
        String textLine =
            "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz1234567890abcdefgh\n";
        String textLine2 =
          "1234567890\n";
        DateTime start = DateTime.Now;
        try
        {
            StreamWriter streamWriter = new StreamWriter(fileName);
            for (int i = IO_MAX; i != 0; i--)
            {
                if ((i % 2) == 0)
                {
                    streamWriter.Write(textLine);
                }
                else
                {
                    streamWriter.Write(textLine2);
                }
                streamWriter.Flush();
            }        
            streamWriter.Close();
        }
        catch (IOException e)
        {
            //
        }

        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;
        Console.WriteLine("[FileTest], Write with flush " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, snapshot time,"
            + executionTime.TotalMilliseconds);

        File.Delete(fileName);

        return (long)executionTime.TotalMilliseconds;
    }
    
    
    /**
     * testReadFile.
     *
     * @return
     */
    public static long testReadFile()
    {
        String currentLine = "";
        int count = 0;
        DateTime start = DateTime.Now;
        try
        {
            StreamReader streamReader = new StreamReader(FILE_NAME);
            for (int i = IO_MAX; i != 0; i--)
            {
                currentLine = streamReader.ReadLine();
            }
            streamReader.Close();
        }
        catch (IOException e)
        {
            //
        }
        DateTime end = DateTime.Now;
        TimeSpan executionTime = end - start;

        Console.WriteLine("[FileTest], Read " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, snapshot time,"
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
	        executionTimes.Add(FileTest.testWriteFile());
        executionTimes.Sort();
	    Console.WriteLine("[FileTest], Write " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
	    executionTimes.Clear();

        for (int i = nbTests; i != 0; i--)
            executionTimes.Add(FileTest.testWriteFileWithFlush());
        executionTimes.Sort();
        Console.WriteLine("[FileTest], Write with flush " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();


        //Write File before read
        try
        {
            String textLine =
                "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz1234567890abcdefgh\n";
            String textLine2 =
              "1234567890\n";
            StreamWriter streamWriter = new StreamWriter(FILE_NAME);
            for (int i = IO_MAX; i != 0; i--)
            {
                if ((i % 2) == 0)
                {
                    streamWriter.Write(textLine);
                }
                else
                {
                    streamWriter.Write(textLine2);
                }
            }
            streamWriter.Close();
        }
        catch (IOException e)
        {
            //
        }
    	
	    for (int i = nbTests; i != 0; i--)
	        executionTimes.Add(FileTest.testReadFile());
        executionTimes.Sort();
        Console.WriteLine("[FileTest], Read " + NB_FILE_TESTS + " file(s) with " + IO_MAX + " lines,, average time,"
                + averageTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax) + ", min time," + executionTimes[0] + ", max time," + executionTimes[executionTimes.Count - 1]
                + ", relative deviation time," + relativeDeviationTimeWithoutMinMax(executionTimes, nbOfExclusionMinMax));
        executionTimes.Clear();

        // Delete the file after
        File.Delete(FILE_NAME);

    }

}

