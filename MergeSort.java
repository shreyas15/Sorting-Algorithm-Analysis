//import java.util.ArrayList;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Allen, Lakshmi & Shreyas  on 11/25/16.
 */
//The method performs the merge sort
public class MergeSort {
	public static Integer[] a={};
    public void mSort(Integer[] a, int p, int r){
        if(p<r){
            int q=(p+r)/2;
            mSort(a,p,q);
            mSort(a,q+1,r);
            merge(a,p,q,r);
        }
    }
    //This method performs the merge operation on the sorted sub-arrays
    public void merge(Integer[] a, int p, int q, int r){
        int n1=q-p+2;
        int n2=r-q+1;
        int[] temp1=new int[n1];
        int[] temp2=new int[n2];;
        for (int i=0;i<n1-1;i++){
            int h=p+i;
            temp1[i]=a[h];
        }
        for (int j=0;j<n2-1;j++){
            int g=q+j+1;
            temp2[j]=a[g];
        }
        temp1[n1-1] = 2147483647;
        temp2[n2-1] = 2147483647;
        int i=0,j=0;
        for (int k=p;k<=r; k++){
            if(temp1[i]<=temp2[j]){
                a[k]=temp1[i];
                i++;
            }
            else {
                a[k]=temp2[j];
                j++;
            }
        }
    }
    
    public void run(String args){
    	try{
		    	File fin = new File(args);
				BufferedReader br = new BufferedReader(new FileReader(fin));
				BufferedWriter outputWriter = null;
			    outputWriter = new BufferedWriter(new FileWriter("nlgnSORT.txt"));
			        
		        ArrayList<Integer> al1 = new ArrayList<Integer>();
		        String eachLine = null;
		        
		        while ((eachLine = br.readLine()) != null){
		            String tokens[] = eachLine.split(", ");
		            for (int i = 0; i<tokens.length; i++)
		            {
			            al1.add(Integer.parseInt(tokens[i]));
		            }
		        }
		        a=new Integer[al1.size()];
		        for (int i = 0; i<al1.size(); i++) {
					a[i] = al1.get(i);
				}
		        MergeSort ms = new MergeSort();
		        long startTimeReverseSorted = System.nanoTime();
		        ms.mSort(a, 0,a.length-1);
				for (int j = 0; j < a.length; j++) {
						outputWriter.write(a[j]+", ");
				}
		        br.close();
		        outputWriter.flush();
		        outputWriter.close();	
		        System.out.println("Merge Sort: The output file has been written to nlgnSORT.txt.");
    	}
    	
    	catch (Exception e)
    	{
    		e.printStackTrace();
            System.out.println("Exception caught: " + e);
    	}
    }
    public static void main(String args[]){
		try{
			if(args.length==0){
			throw new FileNotFoundException();
			}
			File fin = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(fin));
			BufferedWriter outputWriter = null;
			//defining the output file name
			outputWriter = new BufferedWriter(new FileWriter("MergeSortOutput.txt"));
			ArrayList<Integer> al1 = new ArrayList<Integer>();
			String eachLine = null;
			//Parsing the input and populating it to the arraylist
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
			a=new Integer[al1.size()];
			for (int i = 0; i<al1.size(); i++)
			{
				a [i] = al1.get(i);
			}
			outputWriter.write("The input array size is: " + al1.size() + " ");
			outputWriter.newLine();
			MergeSort ms = new MergeSort();
			//recording the start time
			long startTimeReverseSorted = System.nanoTime();
			//Calling the method where the algorithm is implemented
			ms.mSort(a, 0,a.length-1);
			//recording the end time
			long endTimeReverseSorted = System.nanoTime();
			//Writing the output to the file
			outputWriter.write("This array took " + (endTimeReverseSorted - startTimeReverseSorted) + " ns. ");
			outputWriter.newLine();
			outputWriter.write("The sorted array is: ");
			outputWriter.write(Arrays.toString(a));
			outputWriter.newLine();
			//closing the buffered reader and writer used
			br.close();
			outputWriter.flush();
			outputWriter.close();
			System.out.println("Merge Sort: The output file has been written to MergeSortOutput.txt.");
		}catch (NumberFormatException ex){
			System.out.println("There is only one element provided in the input file");
		}
		catch (FileNotFoundException ex){
			System.out.println("Please give the file name as commandline input parameter");
		}
		catch (Exception e) {
			System.out.println("Please provide proper input files as parameters. Kindly refer to the readme file for more instructions");
		}
	}
}

