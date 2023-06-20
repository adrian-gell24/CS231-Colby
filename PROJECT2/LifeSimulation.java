/**
 * Author: Adrian Gellert
 * Date: September 26, 2022
 * File: Cell.java
 * Section: Lecture A, Lab D
 * Project: Conway's Game of Life
 * Course: CS231 
 */

import java.util.Scanner;

/**
 * Life simulation class based on Landscape Display that 
 * calls the advance() method of the Landscape, 
 * calls the repaint() method of LandscapeDisplay, 
 * and calls Thread.sleep( 250 ) to pause for 250 milliseconds before starting the next iteration
 */
public class LifeSimulation {
    public static void main(String[] args) throws InterruptedException {
        // Use scanner to set rows, columns, and chance based on user input into terminal
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many rows should the landscape have?");
        int rows = scanner.nextInt();
        System.out.println("How many columns should the landscape have?");
        int columns = scanner.nextInt();
        System.out.println("What should the chance that a cell in the landscape is alive be?");
        Double chance = scanner.nextDouble();
        Landscape scape = new Landscape(rows, columns, chance);

        LandscapeDisplay display = new LandscapeDisplay(scape, 6);

        // use scanner to choose how many time steps to go through with instead of a while loop
        System.out.println("How many time steps do you want simulation to have?");
        int iterations = scanner.nextInt();
        scanner.close();
        for (int i = 0; i < iterations; i++) {
        Thread.sleep(250);
        scape.advance();
        display.repaint();
        display.saveImage( "data/life_frame_" + String.format( "%03d", i ) + ".png" );
        }
    }
}
