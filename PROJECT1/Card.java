/**
 * File: Card.java
 * Author: Adrian Gellert
 * Date: September 26, 2022
 * Section: Lecture A, Lab D
 * Course: CS231
 * Project: Monte-Carlo Simulation: Blackjack
 */

public class Card {

    /**
     * The value of the card.
     */
    private int value;

    /**
     * Constructs a card with the specified value.
     * @param val
     */
    public Card(int val) {
        value = val;
    }

    /**
     * Returns the value of the card.
     * @return the value of the card
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Returns a string representation of this card.
     * @return a string representation of this card
     */
    public String toString() {
        return ""+ value;
    }
}
