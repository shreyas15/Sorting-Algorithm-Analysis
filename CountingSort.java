import java.io.*;
import java.util.*;
//import java.lang.*;
/**
 * Created by Allen, Lakshmi & Shreyas  on 11/25/16.
 */

public class CountingSort {
	public void run(String args)throws IOException
	{
	    try 
	    {
	    	if(args.isEmpty()){
			throw new FileNotFoundException();
			}
	    	//read input and store in an ArrayList (dynamic)
	    	File fin = new File(args);
			BufferedReader br = new BufferedReader(new FileReader(fin));
			BufferedWriter outputWriter = null;
		    outputWriter = new BufferedWriter(new FileWriter("nSORT.txt"));
		        
	        ArrayList<Integer> al1 = new ArrayList<Integer>();
	        String eachLine = null;
	        
	        while ((eachLine = br.readLine()) != null){
	            String tokens[] = eachLine.split(", ");
	            for (int i = 0; i<tokens.length; i++)
	            {
		            al1.add(Integer.parseInt(tokens[i]));
	            }
	        }
	        
	        //Counting sort algorithm implementation
	        Iterator<Integer> iterator = al1.iterator();
	        
	        int maxEle = al1.get(0);
	        while (iterator.hasNext()) //finding the the Array C's max index
			{
				int temp = iterator.next();
				if(temp > maxEle) maxEle = temp;
			}
	        
	        int arrayC [] = new int [maxEle+1]; //new arrayC initialized to 0 automatically by Java.	        
	        for (int j = 0; j < al1.size(); j++) //prep arrayC with count of each element
	        {
	        	arrayC[al1.get(j)] += 1;
	        }
	        
	        //start actual algorithm here, now that we have input arrays al1 and arrayC.
	        final long startTime1 = System.nanoTime(); //start clock here
	        
	        for (int i = 1; i < arrayC.length; i++) //accumulate values in arrayC
	        {
	        	arrayC[i] = arrayC[i] + arrayC[i-1];
	        }
	        
	        //now start filling array B which is the final sorted array.
	        int arrayB[] = new int[al1.size()];

	        for (int j = arrayB.length-1; j >= 0; j--)
	        {
	        	arrayB[arrayC[al1.get(j)]-1] = al1.get(j);
	        	arrayC[al1.get(j)] -= 1; 
	        }
	        
	        final long endTime1 = System.nanoTime(); //end clock here
	        //write sorted arrayB to File
	        for (int j = 0; j < arrayB.length; j++)
	        {
	        	outputWriter.write(arrayB[j]+", ");
	        	//System.out.print(arrayB[j] + ", "); 
	        }
	        
	        br.close();
	        outputWriter.flush();  
	        outputWriter.close();
	        System.out.println("Counting Sort: The output file has been written to nSORT.txt.");
	    }catch (FileNotFoundException ex){
			System.out.println("Please give the file name as commandline input parameter");
		}
	    
	    catch(Exception e)
	    {
	    	System.out.println("Exception caught: " + e);
	    }
    
	}
	public static void main(String[] args)
	{
		try
		{
			if(args.length==0){
			throw new FileNotFoundException();
			}
			//read input and store in an ArrayList (dynamic)
			File fin = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(fin));
			BufferedWriter outputWriter = null;
			outputWriter = new BufferedWriter(new FileWriter("CountingSortOutput.txt"));

			ArrayList<Integer> al1 = new ArrayList<Integer>();
			String eachLine = null;

			while ((eachLine = br.readLine()) != null){
				String tokens[] = eachLine.split(", ");
				for (int i = 0; i<tokens.length; i++)
				{
					al1.add(Integer.parseInt(tokens[i]));
				}
			}
			if(al1.size()==1){
				throw new NumberFormatException();
			}
			outputWriter.write("The input array size is: " + al1.size() + " ");

			//Counting sort algorithm implementation
			Iterator<Integer> iterator = al1.iterator();

			int maxEle = al1.get(0);
			while (iterator.hasNext()) //finding the the Array C's max index
			{
				int temp = iterator.next();
				if(temp > maxEle) maxEle = temp;
			}

			int arrayC [] = new int [maxEle+1]; //new arrayC initialized to 0 automatically by Java.
			for (int j = 0; j < al1.size(); j++) //prep arrayC with count of each element
			{
				arrayC[al1.get(j)] += 1;
			}

			//start actual algorithm here, now that we have input arrays al1 and arrayC.
			final long startTime1 = System.nanoTime(); //start clock here

			for (int i = 1; i < arrayC.length; i++) //accumulate values in arrayC
			{
				arrayC[i] = arrayC[i] + arrayC[i-1];
			}

			//now start filling array B which is the final sorted array.
			int arrayB[] = new int[al1.size()];

			for (int j = arrayB.length-1; j >= 0; j--)
			{
				arrayB[arrayC[al1.get(j)]-1] = al1.get(j);
				arrayC[al1.get(j)] -= 1;
			}

			final long endTime1 = System.nanoTime(); //end clock here

			outputWriter.write("and the run time was: " + (endTime1 - startTime1) + " ns.");
			outputWriter.newLine();
			outputWriter.write("The sorted array is: ");

			//write sorted arrayB to File
			for (int j = 0; j < arrayB.length; j++)
			{
				outputWriter.write(arrayB[j]+" ");
			}

			br.close();
			outputWriter.flush();
			outputWriter.close();
			System.out.println("Counting Sort: The output file has been written to CountingSortOutput.txt.");
			}catch (NumberFormatException ex){
			System.out.println("There is only one element provided in the input file");
		}
		catch (FileNotFoundException ex){
			System.out.println("Please give the file name as commandline input parameter");
		}
		catch(Exception e)
		{
			System.out.println("Please provide proper input files as parameters. Kindly refer to the readme file for more instructions");
		}

	}
}


