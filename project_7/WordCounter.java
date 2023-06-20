/**
 * Author: Adrian Gellert
 * Date: October 31, 2022
 * File: WordCounter.java
 * Section: Lecture A, Lab D
 * Project: Word Frequency
 * Course: CS231 
 */

// import java.util.Map;
import java.util.ArrayList;
// import java.util.Collections;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// import java.io.PrintWriter;

public class WordCounter {

    MapSet<String, Integer> map;
    int WordCount;

    /**
     * Constructor, where data_structure is either "bst" or "hashmap". 
     * Creates the appropriate data structure and store it in the map field.
     * @param data_structure
     */
    public WordCounter(String data_structure){
        if (data_structure.equals("BSTMap")){
            map = new BSTMap<String, Integer>();
            WordCount = 0;
        } else if (data_structure.equals("HashMap")){
            map = new HashMap<String, Integer>(WordCount);
        }
    } 

    /**
     * Given the filename of a text file, read the text file and return an ArrayList list of all the words in the file.
     * @param filename
     * @return
     */
    public ArrayList<String> readWords(String filename){
        try {
            // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
            FileReader fr = new FileReader(filename);
            // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
            BufferedReader br = new BufferedReader(fr);
      
            // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
            String line = br.readLine();

            // create an empty ArrayList to contain list of words
            ArrayList<String> wordList = new ArrayList<String>();
      
            // start a while loop that loops while line isn't null
            while(line != null){

                // split lines into words
                String[] words = line.split("[^a-zA-Z0-9']");
      
                for (int i = 0; i < words.length; i++){
                    String word = words[i].trim().toLowerCase();
                    // don't process words of length 0
                    if (word.length() != 0){
                        wordList.add(word);
                    }
                    WordCount++;
                } line = br.readLine();
            }
      
            // call the close method of the BufferedReader
            br.close();
            return wordList;
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
     * Given an ArrayList of words, put the words into the map data structure. 
     * Return the time taken in ms. Record the time using System.nanoTime().
     * @param words
     * @return
     */
    public double buildMap(ArrayList<String> words){
        double startTime = System.nanoTime();

        for (String word : words){
            if (!map.containsKey(word)){
                map.put(word, 1);
            } else{
                int count = map.get(word);
                map.put(word, count + 1);
            }
        }

        double timeTaken = System.nanoTime() - startTime;
        return timeTaken;
    }

    /**
     * Clear the map data structure.
     */
    public void clearMap(){
        map.clear();
    }

    /**
     * Return the total word count from the last time readWords was called.
     * @return
     */
    public int totalWordCount(){
        return WordCount;
    }

    /**
     * Return the unique word count, which should be the size of the map data structure.
     * @return
     */
    public int uniqueWordCount(){
        int unique = map.size();
        return unique;
    }

    /**
     * Return the number of times the word occurred in the list of words. 
     * Query the data structure to get the information. Return 0 if the word does not exist in the data structure.
     * @param word
     * @return
     */
    public int getCount(String word){
        if (!map.containsKey(word)){
            System.out.println("No such word found in mapping");
        }
        return map.get(word);
    }

    /**
     * Return the frequency of the word in the list of words. 
     * Query the data structure to get the word count and then divide by the total number of words to get the frequency. 
     * Return 0 if the word does not exist in the data structure.
     * @param word
     * @return
     */
    public double getFrequency(String word){
        if (!map.containsKey(word)){
            return 0;
        }
        double frequency = (double)map.get(word) / (double)map.size();
        return frequency;
    }

    /**
     * Write a word count file given the current set of words in the data structure. 
     * The first line of the file should contain the total number of words. 
     * Each subsequent line should contain a word and its frequency.
     * @param filename
     * @return
     */
    public boolean writeWordCount(String filename){
        try {
            // writer to write the file
            FileWriter fWriter = new FileWriter(filename);

            //writing the total number of words in the mapping
            fWriter.write("totalWordCount: " + WordCount + "\n");

            //write the total number of unique words in the mapping
            fWriter.write("uniqueWordCount: " + map.size() + "\n");

            // writig subsequent key-value pairs
            for (MapSet.KeyValuePair<String, Integer> list: map.entrySet()){
                fWriter.write(list.getKey() + ": " + list.getValue() + "\n");
            }
            
            // close fWriter 
            fWriter.close();
            return true;
        }
        catch(FileNotFoundException ex) {
        System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
        System.out.println("Board.read():: error reading file " + filename);
        }
        return false;
    }

    /**
     * Read a word count file given the filename. 
     * The function should clear the map and then put all of the words, with their counts, into the map data structure.
     * @param filename
     * @return
     */
    public boolean readWordCount(String filename){
        map.clear();
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
              map.put(kvps[0], Integer.parseInt(kvps[1]));
              line = br.readLine();
            }
      
            // call the close method of the BufferedReader
            br.close();
            return true;
        }
        catch(FileNotFoundException ex) {
        System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
        System.out.println("Board.read():: error reading file " + filename);
        }
        return false;
    }

    public static void main(String[] args){

        /* String test = args[0];

        WordCounter testCounter = new WordCounter("HashMap");
        double processTime = 0;

        System.out.println("Starting to process file: " + args[0]);
        testCounter.clearMap();
        processTime = testCounter.buildMap(testCounter.readWords(test));
        System.out.println("Process time: " + processTime);
        System.out.println("Done Processing"); */
        
        // checking that files are inputted via command line arguments
        if (args[1] == null || args[2] == null || args[3] == null || args[4] == null || args[5] == null || args[6] == null || args[7] == null || args[8] == null){
            System.out.println("Please enter file names");
        }
        // Input file names into a string array
        String[] redditFiles = {args[1], args[2],args[3],args[4], args[5], args[6], args[7], args[8]};

        // Analyze files, print out processing times, and create word count files with file names given by 
        // each index in the outputFileStrings array. 
        // `

        try{
            WordCounter redditReader = new WordCounter(args[0]);

            // writer to write the file
            if (args[0].equals("BSTMap")){
                File file = new File("BSTData1.csv");
                FileWriter fWriter = new FileWriter(file, true);
                double[] processTimes = new double[5];
                fWriter.write("Reddit File, Total Word Count, Unique Words, Processing Time (ms), Depth, File Size (byte)" + "\n");
                for (String filename : redditFiles){
                    System.out.println("Starting to process file: " + filename);
                    ArrayList<String> fileContent = redditReader.readWords(filename);
                    for (int i = 0; i < 5; i++){
                        System.out.print("Processing: " + filename + "\n");
                        redditReader.clearMap();
                        processTimes[i] = redditReader.buildMap(fileContent);
                    }
                    double totalTime = 0;
                    double maxValue = processTimes[0];
                    double minValue = processTimes[0];
                    for (double time : processTimes){
                        if (time > maxValue){
                            maxValue = time;
                        }
                        if (time < minValue){
                            minValue = time;
                        }
                        totalTime += time;
                    }
                    totalTime -= maxValue;
                    totalTime -= minValue;
        
                    double aveTime = 0;
                    aveTime = totalTime / 3;
                    fWriter.write(filename + "," + redditReader.totalWordCount() + "," + redditReader.uniqueWordCount() + "," + aveTime + "," + ((BSTMap<String, Integer>) redditReader.map).furthestLeaf() + "\n");
                    System.out.println("average runtime: " + aveTime + "\n");
                    System.out.println("total word count: " + redditReader.totalWordCount() + "\n");
                    System.out.println("unique word count: " + redditReader.uniqueWordCount() + "\n");
                    System.out.println("Depth of BSTMap: " + ((BSTMap<String, Integer>) redditReader.map).furthestLeaf());
                }
                fWriter.close();
                System.out.println("Finished Processing Files!");
            }
            else if (args[0].equals("HashMap")){
                File file = new File("Hashdata2.csv");
                FileWriter fWriter = new FileWriter(file, true);
                double[] processTimes = new double[5];
                fWriter.write("Reddit File, Total Word Count, Unique Words, Processing Time (ms), Collisions, Maximum Bucket Size, File Size (byte)" + "\n");
                for (String filename : redditFiles){
                    System.out.println("Starting to process file: " + filename);
                    ArrayList<String> fileContent = redditReader.readWords(filename);
                    for (int i = 0; i < 5; i++){
                        System.out.print("Processing: " + filename + "\n");
                        redditReader.clearMap();
                        processTimes[i] = redditReader.buildMap(fileContent);
                    }
                    double totalTime = 0;
                    double maxValue = processTimes[0];
                    double minValue = processTimes[0];
                    for (double time : processTimes){
                        if (time > maxValue){
                            maxValue = time;
                        }
                        if (time < minValue){
                            minValue = time;
                        }
                        totalTime += time;
                    }
                    totalTime -= maxValue;
                    totalTime -= minValue;
        
                    double aveTime = 0;
                    aveTime = totalTime / 3;
                    fWriter.write(filename + "," + redditReader.totalWordCount() + "," + redditReader.uniqueWordCount() + "," + aveTime + "," + ((HashMap<String, Integer>) redditReader.map).collision() + "," + ((HashMap<String, Integer>) redditReader.map).maxBucketSize() + "\n");
                    System.out.println("average runtime: " + aveTime + "\n");
                    System.out.println("total word count: " + redditReader.totalWordCount() + "\n");
                    System.out.println("unique word count: " + redditReader.uniqueWordCount() + "\n");
                }
                fWriter.close();
                System.out.println("Finished Processing Files!");
            }

        }
        catch(FileNotFoundException ex) {
        System.out.println("Board.read():: unable to open file ");
        }
        catch(IOException ex) {
        System.out.println("Board.read():: error reading file ");
        }
    }
}
