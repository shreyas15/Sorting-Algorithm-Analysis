import java.io.*;
import java.util.*;
//import java.lang.*;

/**
 * Created by Allen, Lakshmi & Shreyas on 11/25/16.
 */

public class InsertionSort {
public void run(String args)throws IOException
{
  
     try 
     {
    	 //read input and store in an ArrayList (dynamic)
    	 File fin = new File(args);
      	 BufferedReader br = new BufferedReader(new FileReader(fin));
      	 BufferedWriter outputWriter = null;
      	 outputWriter = new BufferedWriter(new FileWriter("n2SORT.txt"));
          
         ArrayList<Integer> al1 = new ArrayList<Integer>();
         String eachLine = null;
         
         while ((eachLine = br.readLine()) != null){
             String tokens[] = eachLine.split(", ");
             for (int i = 0; i<tokens.length; i++)
             {
              al1.add(Integer.parseInt(tokens[i]));
             }
         }
         
         int arrayB[] = new int[al1.size()];
         for (int i = 0; i < al1.size(); i++)
         {
        	 arrayB[i] = al1.get(i);
         }
         
         final long startTime = System.nanoTime(); //start clock here
         
         //Insertion sort algorithm implementation
              
               int temp;
               for (int i = 1; i < arrayB.length; i++) {
                   for(int j = i ; j > 0 ; j--){
                       if(arrayB[j] < arrayB[j-1])
                       {
                           temp = arrayB[j];
                           arrayB[j] = arrayB[j-1];
                           arrayB[j-1] = temp;
                       }
                   }
               }
              
         final long endTime = System.nanoTime(); //end clock here

         //write sorted arrayB to File
         for (int j = 0; j < arrayB.length; j++)
         {
          outputWriter.write(arrayB[j]+", ");
         }
         
         br.close();
         outputWriter.flush();  
         outputWriter.close();
         System.out.println("Insertion Sort: The output file has been written to n2SORT.txt.");
     }

     catch(Exception e)
     {
      System.out.println("Exception caught: " + e);
     }
    
 }
 public static void main(String args[]) {
     try {
         if(args.length==0){
             throw new FileNotFoundException();
         }
         //read input and store in an ArrayList (dynamic)
         File fin = new File(args[0]);
         BufferedReader br = new BufferedReader(new FileReader(fin));
         BufferedWriter outputWriter = null;
         outputWriter = new BufferedWriter(new FileWriter("InsertionSortOutput.txt"));

         ArrayList<Integer> al1 = new ArrayList<Integer>();
         String eachLine = null;

         while ((eachLine = br.readLine()) != null) {
             String tokens[] = eachLine.split(", ");
             for (int i = 0; i < tokens.length; i++) {
                 al1.add(Integer.parseInt(tokens[i]));
             }
         }
         if(al1.size()==1){
             throw new NumberFormatException();
         }
         outputWriter.write("The input array size is: " + al1.size() + " ");

         int arrayB[] = new int[al1.size()];
         for (int i = 0; i < al1.size(); i++) {
             arrayB[i] = al1.get(i);
         }

         final long startTime = System.nanoTime(); //start clock here

         //Insertion sort algorithm implementation

         int temp;
         for (int i = 1; i < arrayB.length; i++) {
             for (int j = i; j > 0; j--) {
                 if (arrayB[j] < arrayB[j - 1]) {
                     temp = arrayB[j];
                     arrayB[j] = arrayB[j - 1];
                     arrayB[j - 1] = temp;
                 }
             }
         }

         final long endTime = System.nanoTime(); //end clock here

         outputWriter.write("and the run time was: " + (endTime - startTime) + " ns.");
         outputWriter.newLine();
         outputWriter.write("The sorted array is: ");

         //write sorted arrayB to File
         for (int j = 0; j < arrayB.length; j++) {
             outputWriter.write(arrayB[j] + " ");
         }

         br.close();
         outputWriter.flush();
         outputWriter.close();
         System.out.println("Insertion Sort: The output file has been written to InsertionSortOutput.txt.");
     }catch (NumberFormatException ex){
         System.out.println("There is only one element provided in the input file");
     }catch (FileNotFoundException ex){
         System.out.println("Please give the file name as commandline input parameter");
     }catch (Exception e) {
         System.out.println("Please provide proper input files as parameters. Kindly refer to the readme file for more instructions");
     }
 }
}


