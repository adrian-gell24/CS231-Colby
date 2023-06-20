/**
 * File: Blackjack.java
 * Author: Adrian Gellert
 * Date: September 26, 2022
 * Section: Lecture A, Lab D
 * Course: CS231
 * Project: Monte-Carlo Simulation: Blackjack
 * Description of project: This project implements the Monte-Carlo Simulation method with the Java language to create a text-based game of Blackjack that runs in terminal.
 * The purpose of this project is to practice creating and using Java classes and to see how a system changes over a period of time.
 * Simplified rule set: two players (player and dealer), ace worth 11 points, 
 */


public class Blackjack{
    /**
     * Creates a private variable for the cutoff number of cards under which the deck must be shuffled
     * Creates the needed aspects of a Blackjack game (deck, card, player hand, and dealer hand) by calling other classes.
    */
    private Deck deck = new Deck();
    private Hand player = new Hand();
    private Hand dealer = new Hand();
    private Card card = deck.deal();
    private int cutoff;

    /**
     * This method sets the private cutoff value to the parameter reshuffleCutoff as a way to store the cutoff value.
     * The method also calls the reset method, which begins a new game.
    */
    public Blackjack(int reshuffleCutoff ){
        this.cutoff = reshuffleCutoff;
        this.reset();
    }
    
    /**
     * This method sets an initial cutoff value for Blackjack
    */
    public Blackjack(){
        this.cutoff = 5;
    }
    
    /**
     * This method creates a new shuffled deck if the deck size goes below the cutoff value.
     * Otherwise, it only clears the cards which were in the player and dealer's hand at the end of a round.
    */
    public void reset(){
        if (deck.size() < cutoff){
            deck = new Deck();
            deck.shuffle();
        }
        player.reset();
        dealer.reset();
    }
    
    /**
     * This method loops twice to create the initial hand of each player before they take a hit.
    */
    public void deal(){
        for (int i = 0; i<2; i++){
            player.add(deck.deal());
            dealer.add(deck.deal());
        }
    }
    
    public boolean playerTurn(){
        
        while (player.getTotalValue() < 16){
            // loop to add cards from the deck to the player until the total value in the deck is equal to or greater than 16. 
            player.add(card);
        }
        
        // after the player's hand value reaches 16 or greater, check whether the hand value is above or below 21
        // return a boolean based on total value
        if (player.getTotalValue() > 21){
            return false;
        }
        else {
            return true;
        }
    }
    
    public boolean dealerTurn(){

        while (dealer.getTotalValue() < 17){
            // loop to add cards from the deck to the dealer until the total value in the deck is equal to or greater than 17
            dealer.add(card);
        }

        // after the dealer's hand value reaches 16 or greater, check whether the hand value is above or below 21
        // return a boolean based on total value
        if (dealer.getTotalValue() > 21){
            return false;
        }
        else {
            return true;
        }
    }
    
    public void setReshuffleCutoff(int cutoff){
        // changes the current cutoff value 
        this.cutoff = 10;
    }
    
    public int getReshuffleCutoff(){
        // returns the cutoff value
        return this.cutoff;
    }
    
    public String toString(){
        // returns each hand as a string
        return "Player hand: " + player + " " + "Dealer hand: " + dealer;
    }
    
    
    
    public int game(boolean verbose){
        // call reset method to clear each player's hand for a new game
        this.reset();
        // call deal method to provide two initial cards to each hand
        this.deal();
        // first player hits until their hand's value is at or above 16.
        this.playerTurn();
        // then dealer hits until their hand's value is at or above 17.
        this.dealerTurn();

        // Next, check hands to see who won the round
        if (verbose == true){
            System.out.println(toString());
        }
        if (player.getTotalValue() > 21){
            return -1;
        }
        else if (dealer.getTotalValue() > 21){
            return 1;
        }
        else if (player.getTotalValue() > dealer.getTotalValue()){
            return 1;
        }
        else if (player.getTotalValue() < dealer.getTotalValue()){
            return -1;
        }
        else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack();
        // set up a new blackjack game
        for (int i=0; i<10; i++){
            // loop through 10 rounds with given deck 
            int result = blackjack.game(true);
            if (result == 1){
                System.out.println("Player has won!");
            }
            else if (result == -1){
                System.out.println("Dealer has won!");
            }
            else {
                System.out.println("It's a draw.");
            }
        }
    }
}

