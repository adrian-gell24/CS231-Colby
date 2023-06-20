/**
 * Author: Adrian Gellert
 * Date: December 18, 2022
 * File: CommonWordsFinder.java
 * Section: Lecture A, Lab D
 * Project: Word Trends
 * Course: CS231 
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;

public class CommonWordsFinder {

    private PQHeap<MapSet.KeyValuePair<String, Integer>> heap;

    /**
     * Constructor
     */
    public CommonWordsFinder() {
        heap = new PQHeap<MapSet.KeyValuePair<String,Integer>> (new KeyValuePairComparatorByValue<String, Integer>());
    }

    /**
     * Read a word count file given the filename. 
     * Put words and their associate into PQHeap. 
     * @param filename
     * @return
     */
    public PQHeap<MapSet.KeyValuePair<String,Integer>> readWordCount(String filename){
        try {
            // writer to write the file
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
            String line = br.readLine();
            line = br.readLine();
      
            // start a while loop that loops while line isn't null
            while(line != null){
              String[] kvps = line.split("[ ,]+");
              heap.offer(new MapSet.KeyValuePair<String, Integer> (kvps[0], Integer.parseInt(kvps[1])));
              line = br.readLine();
            }
      
            // call the close method of the BufferedReader and return heap
            br.close();
            return heap;
        }
        catch(FileNotFoundException ex) {
        System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
        System.out.println("Board.read():: error reading file " + filename);
        }
        return null;
    }

    /**
     * Method to return the string representation of the heap
     * @return
     */
    public String PQHeapToString(){
        return heap.toString();
    }

    public static void main(String[] args) throws IOException {
        // checking that all necessary command line argument indices are inputted
        if (args[0] == null || args[1] == null || args[2] == null || args[3] == null || args[4] == null || args[5] == null || args[6] == null || args[7] == null || args[8] == null){
            System.out.println("Please enter an integer and all 8 reddit word count files");
        }

        // Input file names into a string array
        String[] wordCountFiles = {args[1], args[2],args[3],args[4], args[5], args[6], args[7], args[8]};

        // Create instance
        CommonWordsFinder finder = new CommonWordsFinder();

        File file = new File("CommonWords.csv");
        FileWriter fWriter = new FileWriter(file, true);
        fWriter.write("file, word, frequency" + "\n");

        // For each of the files
        // 1. Create a PQHeap holding the key-value pairs
        // 2. Poll from the PQHeap and print the word and its frequency
        // 3. Repeat step 2 args[0] - 1 times
        for (String files : wordCountFiles){
            System.out.println("Processing file: " + files + "\n");
            PQHeap<MapSet.KeyValuePair<String,Integer>> printHeap = finder.readWordCount(files);
            System.out.println(printHeap.size());
            for (int i = 0; i < Integer.parseInt(args[0]); i++){
                MapSet.KeyValuePair<String,Integer> kvp = printHeap.poll();
                fWriter.write("" + files + ", " + kvp.getKey() + ", " + (((double) kvp.getValue()) / ((double) (printHeap.size() + i))) + "\n");
                System.out.print(kvp.getValue());
                System.out.println("#" + (i + 1) + "\n" + "word: " + kvp.getKey() + "\n" + "frequency: " + (((double) kvp.getValue()) / ((double) (printHeap.size() + i))) + "\n");
            }
        }
        fWriter.close();
    }
}