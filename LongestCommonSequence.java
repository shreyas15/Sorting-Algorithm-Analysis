/**
 * Created by Allen on 12/4/16.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * Created by Allen, Lakshmi & Shreyas  on 11/30/16.
 */
public class LongestCommonSequence {
    public static ArrayList<Integer> al=null;
    public static int[][][] dynArray={};
    public static int[] firArray={};
    public static int[] secArray={};
    public static int[] thirArray={};
    //This method constructs the 3D table as part of the Dynamic programming
    public int lcsDynamic(int[] val1, int[] val2, int[] val3){
        al=new ArrayList<Integer>();
        int temp[][][] = new int[val1.length + 1][val2.length + 1][val3.length+1];
        int max = 0;
        //Creating the 3D table with the input sequences
        for(int i=1; i < temp.length; i++){
            for(int j=1; j < temp[i].length; j++) {
                for (int k = 1; k < temp[i][j].length; k++) {
                    if (val1[i - 1] == val2[j - 1] && val2[j - 1] == val3[k - 1]) {
                        temp[i][j][k] = temp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        temp[i][j][k] = Math.max( temp[i][j][k - 1], Math.max(temp[i][j - 1][k], temp[i - 1][j][k]));
                    }
                    if (temp[i][j][k] > max) {
                        max = temp[i][j][k];
                    }
                }
            }
        }
        //Assigning the 3D table to the static array
        dynArray=temp;
        return max;
    }
    //This method gets the LCS by traversing through the 3D table created
    public void printLcs(int a, int b, int c){
        //When the traversing ends
        if(dynArray[a][b][c]==0){
            System.out.println("LCS is now complete");
        }
        //When the
        else if(dynArray[a][b][c]==Math.max(Math.max(dynArray[a-1][b][c],dynArray[a][b-1][c]),dynArray[a][b][c-1])){
            if (dynArray[a][b][c]==dynArray[a-1][b][c]){
                printLcs(a-1,b,c);
            }
            else if (dynArray[a][b][c]==dynArray[a][b-1][c]){
                printLcs(a,b-1,c);
            }
            else if (dynArray[a][b][c]==dynArray[a][b][c-1]){
                printLcs(a,b,c-1);
            }
        }
        else if(dynArray[a][b][c]==Math.addExact(dynArray[a-1][b-1][c-1],1)){
            al.add(firArray[a-1]);
            printLcs(a-1,b-1,c-1);

        }
    }
    public static void main(String args[])
    {
        try{
            if(args.length<3){
                throw new FileNotFoundException();
            }
            MergeSort ms=new MergeSort();
            InsertionSort is=new InsertionSort();
            CountingSort cs=new CountingSort();
            //Calling the merge sort, insertion sort and counting sort with the input file names passed as parameters
            ms.run(args[0]);
            is.run(args[1]);
            cs.run(args[2]);
            LongestCommonSequence lcs = new LongestCommonSequence();
            //Getting the input file names from the commandline
            File fin1 = new File(args[0]);
            File fin2 = new File(args[1]);
            File fin3 = new File(args[2]);
            BufferedReader br1 = new BufferedReader(new FileReader(fin1));
            BufferedReader br2 = new BufferedReader(new FileReader(fin2));
            BufferedReader br3 = new BufferedReader(new FileReader(fin3));
            //Creating the output file to write the LCS output generated initially with the input files
            BufferedWriter outputWriter = null;
            outputWriter = new BufferedWriter(new FileWriter("LCS-IN.txt"));
            ArrayList<Integer> al1 = new ArrayList<Integer>();
            ArrayList<Integer> al2 = new ArrayList<Integer>();
            ArrayList<Integer> al3 = new ArrayList<Integer>();
            String eachLine = null;
            //Reading the data from the input files and storing into the respective arraylists
            while ((eachLine = br1.readLine()) != null){
                String tokens1[] = eachLine.split(", ");
                for (int i = 0; i<tokens1.length; i++)
                {
                    al1.add(Integer.parseInt(tokens1[i]));
                }
            }
            eachLine = null;
            while ((eachLine = br2.readLine()) != null){
                String tokens2[] = eachLine.split(", ");
                for (int i = 0; i<tokens2.length; i++)
                {
                    al2.add(Integer.parseInt(tokens2[i]));
                }
            }
            eachLine = null;
            while ((eachLine = br3.readLine()) != null){
                String tokens3[] = eachLine.split(", ");
                for (int i = 0; i<tokens3.length; i++)
                {
                    al3.add(Integer.parseInt(tokens3[i]));
                }
            }
            firArray=new int[al1.size()];
            secArray=new int[al2.size()];
            thirArray=new int[al3.size()];
            for (int i = 0; i<al1.size(); i++)
            {
                firArray [i] = al1.get(i);
            }
            for (int i = 0; i<al2.size(); i++)
            {
                secArray [i] = al2.get(i);
            }
            for (int i = 0; i<al3.size(); i++)
            {
                thirArray [i] = al3.get(i);
            }
            //Calling the method which creates the 3D table for manipulating the LCS
            lcs.lcsDynamic(firArray, secArray, thirArray);
            //Calling the method which prints the LCS by traversing through the 3D table
            lcs.printLcs(firArray.length-1,secArray.length-1, thirArray.length-1);
            //Write the static arraylist containing the LCS to the output file
            outputWriter.write("The LCS of unsorted input files is: ");
            Collections.reverse(al);
            outputWriter.write(al.toString());
            //Closing the buffered readers
            br1.close();
            br2.close();
            br3.close();
            outputWriter.flush();
            //Closing the Buffered writer instances
            outputWriter.close();
            File fin4 = new File("nlgnSORT.txt");
            File fin5 = new File("n2SORT.txt");
            File fin6 = new File("nSORT.txt");
            BufferedReader br4 = new BufferedReader(new FileReader(fin4));
            BufferedReader br5 = new BufferedReader(new FileReader(fin5));
            BufferedReader br6 = new BufferedReader(new FileReader(fin6));
            BufferedWriter outputWriter1 = null;
            //Creating the output file to write the LCS output generated Sorted with the input files
            outputWriter1 = new BufferedWriter(new FileWriter("LCS-OUT.txt"));

            eachLine = null;
            al1.clear();
            al2.clear();
            al3.clear();
            al.clear();
            //Reading the data from the input files and storing into the respective arraylists
            while ((eachLine = br4.readLine()) != null){
                String tokens1[] = eachLine.split(", ");
                for (int i = 0; i<tokens1.length; i++)
                {
                    al1.add(Integer.parseInt(tokens1[i]));
                }
            }
            eachLine = null;
            while ((eachLine = br5.readLine()) != null){
                String tokens2[] = eachLine.split(", ");
                for (int i = 0; i<tokens2.length; i++)
                {
                    al2.add(Integer.parseInt(tokens2[i]));
                }
            }
            eachLine = null;
            while ((eachLine = br6.readLine()) != null){
                String tokens3[] = eachLine.split(", ");
                for (int i = 0; i<tokens3.length; i++)
                {
                    al3.add(Integer.parseInt(tokens3[i]));
                }
            }
            firArray=new int[al1.size()];
            secArray=new int[al2.size()];
            thirArray=new int[al3.size()];
            for (int i = 0; i<al1.size(); i++)
            {
                firArray [i] = al1.get(i);
            }
            for (int i = 0; i<al2.size(); i++)
            {
                secArray [i] = al2.get(i);
            }
            for (int i = 0; i<al3.size(); i++)
            {
                thirArray [i] = al3.get(i);
            }
            //Calling the method which creates the 3D table for manipulating the LCS
            lcs.lcsDynamic(firArray, secArray, thirArray);
            //Calling the method which prints the LCS by traversing through the 3D table
            lcs.printLcs(firArray.length,secArray.length, thirArray.length);
            //Write the static arraylist containing the LCS to the output file
            outputWriter1.write("The LCS of sorted input files is: ");
            Collections.reverse(al);
            outputWriter1.write(al.toString());

            System.out.println("LCS: The output file has been written.");
            //Closing the buffered readers
            br4.close();
            br5.close();
            br6.close();
            //Closing the Buffered writer instances
            outputWriter1.flush();
            outputWriter1.close();
        }catch (FileNotFoundException ex){
            System.out.println("Please give the three file names as commandline input parameters. Kindly refer to the readme file for more instructions");
        }catch (IOException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            System.out.println("Please provide proper input files as parameters. Kindly refer to the readme file for more instructions");
        }
    }
}