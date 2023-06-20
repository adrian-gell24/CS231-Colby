/**
 * Author: Adrian Gellert
 * Date: December 18, 2022
 * File: WordTrendsFinder.java
 * Section: Lecture A, Lab D
 * Project: Word Trends (8)
 * Course: CS231 
*/

public class WordTrendsFinder {
    public static void main(String[] args) {
        // checking that all necessary command line argument indices are inputted
        if (args[0] == null || args[1] == null || args[2] == null || args[3] == null || args[4] == null || args[5] == null || args[6] == null || args[7] == null){
            System.out.println("Please enter an integer and all 8 reddit word count files");
        }

        // Input file names into a string array
        String[] wordCountFiles = {args[0], args[1],args[2],args[3], args[4], args[5], args[6], args[7]};
        // String[] words = {"online", "keyboard", "email", "download", "upload", "browser", "search", "cache", "virus", "firewall"};
        // String[] words = {"earthquake", "drought", "storm", "tornado", "wildfire", "flood", "hurricane", "landslide", "avalanche", "pandemic"};
        // String[] words = {"storj", "essentia", "eos", "akasha", "dropbox", "ios", "android", "twitter"};
        // String[] words = {"smartphone", "facebook", "twitter", "uber", "lyft", "netflix", "hulu", "tinder"};
        // String[] words = {"broadway", "hollywood", "silicon", "wallstreet", "nashville", "fashion"};
        // String[] words = {"protest", "activism", "blm", "suffrage", "boycott", "mlk", "gandhi"};
        // String[] words = {"ronaldo", "messi", "lebron", "usain", "williams", "jordan", "gretzky", "federer", "pele"};
        // String[] words = {"messi", "lebron", "williams", "jordan", "brady", "ma"};
        // String[] words = {"messi", "lebron", "williams", "kobe", "brady"};
        // String[] words = {"greta", "meek", "yousafzai", "margolin", "hirsi", "desmond"};
        String[] words = {"apartheid", "metoo", "blm", "xr", "amnesty", "lgbt"};
        
        // Create instance of WordCounter
        WordCounter finder = new WordCounter("HashMap");

        // For each of the files
        // 1. call readWordCount on the file
        // 2. print out the frequency of each word in words
        for (String file : wordCountFiles){
            System.out.println("Processing file: " + file);
            finder.readWordCount(file);
            for (String word : words){
                System.out.println("word: " + word + "\n" + "frequency: " + finder.getFrequency(word));
            }
            System.out.println("\n");
        }
    }
}
