/**
 * Author: Adrian Gellert
 * Date: November 10, 2022
 * File: LandscapeTest.java
 * Section: Lecture A, Lab D
 * Project: Grid Search
 * Course: CS231 
 */

public class LandscapeTest {

    public static void main(String[] args){
        Landscape test = new Landscape(50, 40, .2);
        System.out.println("Start cell info (row, column, type, previous, visited): " + "\n" + test.getStart().row + "\n" + test.getStart().col + "\n" + test.getStart().type + "\n" + test.getStart().prev + "\n" + test.getStart().visited);
        System.out.println("Type should be START, previous should be null, and visited should be false. Multiple runs should have different rows and columns.");
        System.out.println("Target cell info (row, column, type, previous, visited): " + "\n" + test.getTarget().row + "\n" + test.getTarget().col + "\n" + test.getTarget().type + "\n" + test.getTarget().prev + "\n" + test.getTarget().visited);
        System.out.println("This should be the same as START but with different rows and columns.");
        System.out.println("Number of rows: " + test.getRows() + "\n" + "Number of columns: " + test.getCols());
        System.out.println("There should be 50 rows and 40 columns.");
    }
}
