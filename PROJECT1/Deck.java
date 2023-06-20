/**
 * File: Blackjack.java
 * Author: Adrian Gellert
 * Date: September 26, 2022
 * Section: Lecture A, Lab D 
 * Course: CS231
 * Project: Monte-Carlo Simulation: Blackjack
 */

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card>deck; 

    /**
     * Creates the underlying deck as an ArrayList of Card objects. 
     * Calls build() as a subroutine to build the deck itself.
     */
    public Deck() {
        deck = new ArrayList<Card>();
        build();
        // shuffle();
    }

    /**
     * Builds the underlying deck as a standard 52 card deck. 
     * Replaces any current deck stored. 
     */
    public void build() {
        deck.clear();
        for (int i = 0; i<4; i++){
            deck.add(new Card(11));
            for (int j = 2; j<10; j++){
                deck.add(new Card(j));
            }
        }
        for (int i = 0; i<16; i++){
            deck.add(new Card(10));
        }
    }

    /**
     * Returns the number of cards left in the deck. 
     * @return the number of cards left in the deck
     */
    public int size() {
        return deck.size();
    }

    /**
     * Returns and removes the first card of the deck.
     * @return the first card of the deck
     */
    public Card deal() {
        return deck.remove(0);
    }

    /**
     * Shuffles the cards currently in the deck. 
     */
    public void shuffle() {
        ArrayList<Card> mixedCards = new ArrayList<Card>();
        for (int i=0; i < 52; i++){
            Random ran = new Random();
            int move = ran.nextInt(0, this.deck.size());
            mixedCards.add(this.deck.get(move));
            this.deck.remove(move);
        }
        this.deck = mixedCards;
    }

    /**
     * Returns a string representation of the deck.
     * @return a string representation of the deck
     */
    public String toString() {
        return "" + deck;
    }
}
