/**
 * Author: Adrian Gellert
 * Date: November 8, 2022
 * File: WordCounter.java
 * Section: Lecture A, Lab D
 * Project: Word Frequency
 * Course: CS231 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WordCounter {
    BSTMap<String, Integer> map;
    int WordCount;

    /**
     * Constructor that makes an empty BSTMap and sets the total word count to zero.
     */
    public WordCounter(){
        map = new BSTMap<String, Integer>();
        WordCount = 0;
    }

    /**
     * Generates the word counts from a file of words.
     * @param filename
     */
    public void analyze(String filename){
        try {
            // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
            FileReader fr = new FileReader(filename);
            // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
            BufferedReader br = new BufferedReader(fr);
      
            // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
            String line = br.readLine();
      
            // start a while loop that loops while line isn't null
            while(line != null){

                // split lines into words
                String[] words = line.split("[^a-zA-Z0-9']");
      
                for (int i = 0; i < words.length; i++){
                String word = words[i].trim().toLowerCase();
                // don't process words of length 0
                if (word.length() != 0){
                    // update the map
                    // if map does not contain the word, put a new key-value pair into the tree
                    // otherwise, increment the value associated with the word by one
                    if (!map.containsKey(word)){
                        map.put(word, 1);
                    } else{
                        int count = map.get(word);
                        map.put(word, count + 1);
                    } WordCount++;
                }
                } line = br.readLine();
            }
      
            // call the close method of the BufferedReader
            br.close();
        }
        catch(FileNotFoundException ex) {
        System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
        System.out.println("Board.read():: error reading file " + filename);
        }
    }

    /**
     * Returns the total word count 
     * @return
     */
    public int getTotalWordCount(){
        return WordCount;
    }

    /**
     * Returns the number of unique words
     * @return
     */
    public int getUniqueWordCount(){
        int unique = map.size();
        return unique;
    }

    /**
     * Returns the frequency value associated with a given word.
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
     * Return the percentage of total words in the map that are the inputted word.
     * @param word
     * @return
     */
    public double getFrequency(String word){
        double frequency = (double)map.get(word) / (double)map.size();
        return frequency;
    }

    // /**
    //  * I don't think I need this. It's already in BSTMap
    //  */
    // public String toString(){
    //     return map.toString();
    // }

    /**
     * Writes the contents of the BSTMap to a word count file.
     * @param filename
     */
    public void writeWordCountFile(String filename){
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
        }
        catch(FileNotFoundException ex) {
        System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
        System.out.println("Board.read():: error reading file " + filename);
        }
    }

    /**
     * Reads the contents of a word count file and reconstructs the fields of the WordCount object, including the BSTMap.
     * @param filename
     */
    public void readWordCountFile(String filename){
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
        }
        catch(FileNotFoundException ex) {
        System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
        System.out.println("Board.read():: error reading file " + filename);
        }
    }

    public static void main(String[] args){

        // The following chunk of code is to test methods with the counttest.txt file
        
        /* WordCounter testCounter = new WordCounter();
        String test = args[0];

        testCounter.analyze(test);
        System.out.println(testCounter.getTotalWordCount());
        System.out.println(testCounter.getUniqueWordCount());
        System.out.println(testCounter.getFrequency("best"));
        System.out.println(testCounter.toString());

        while (true){
            System.out.println("Starting to process file: " + args[0]);
            long startTime = System.currentTimeMillis();
            testCounter.analyze(test);
            testCounter.writeWordCountFile(test);
            testCounter.readWordCountFile(test);
            long timeTaken = System.currentTimeMillis() - startTime;
            System.out.println("Process Time: " + timeTaken);
            return;
        } */

        // checking that files are inputted via command line arguments
        if (args[0] == null || args[1] == null || args[2] == null || args[3] == null || args[4] == null || args[5] == null || args[6] == null || args[7] == null){
            System.out.println("Please enter file names");
        }

        // Input file names into a string array
        String[] redditFiles = {args[0], args[1],args[2],args[3], args[4], args[5], args[6], args[7]};

        // Analyze files, print out processing times, and create word count files with file names given by 
        // each index in the outputFileStrings array. 
        WordCounter redditReader = new WordCounter();
        String[] outputFileStrings = {"2008_WordCount", "2009_WordCount","2010_WordCount","2011_WordCount",
                            "2012_WordCount","2013_WordCount","2014_WordCount","2015_WordCount"};
        long[] processTimes = new long[8]; 
        for (int i = 0; i < redditFiles.length; i++){
            System.out.println("Starting to process file: " + redditFiles[i]);
            long startTime = System.currentTimeMillis();
            redditReader.analyze(redditFiles[i]);
            redditReader.writeWordCountFile(outputFileStrings[i]+ ".txt");
            redditReader.readWordCountFile(outputFileStrings[i]+ ".txt");
            processTimes[i] = System.currentTimeMillis() - startTime;
            System.out.println("Process Time: " + processTimes[i]);
        }

        System.out.println("Finished Processing Files!");
    } 
}
