/**
 * Author: Adrian Gellert
 * Date: November 10, 2022
 * File: CellTest.java
 * Section: Lecture A, Lab D
 * Project: Grid Search
 * Course: CS231 
 */

public class CellTest {
    public static void main(String[] args){
        Cell test = new Cell(0, 0, null);
        System.out.println("Location of test: " + test.getRow() + ", " + test.getCol() + "\n" + "Type of test: " + test.getType() + "\n" + "Previous of test: " + test.getPrev());
        test.setType(Cell.Type.TARGET);
        test.prev = new Cell(25, 30, Cell.Type.OBSTACLE);
        Cell prev = test.prev;
        System.out.println("\n" + "Location of test: " + test.getRow() + ", " + test.getCol() + "\n" + "Type of test: " + test.getType() + "\n" + "Previous of test: " + test.getPrev());
        System.out.println("\n" + "Location of test's previous: " + prev.getRow() + ", " + prev.getCol() + "\n" + "Type of test's previous: " + prev.getType() + "\n" + "Test previous's previous: " + prev.getPrev() + "\n" + "visited?: " + prev.visited());
        Cell visit = new Cell(22, 36, Cell.Type.FREE);
        prev.visitFrom(visit);
        System.out.println("\n" + "Prev's new previous: " + prev.getPrev() + "\n" + "Prev's new visited parameter: " + prev.visited());
        prev.reset();
        System.out.println("\n" + "Prev's new previous: " + prev.getPrev() + "\n" + "Prev's new visited parameter: " + prev.visited());
    }
}
