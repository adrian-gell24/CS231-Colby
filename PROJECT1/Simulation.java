/**
 * File: Blackjack.java
 * Author: Adrian Gellert
 * Date: September 26, 2022
 * Section: Lecture A, Lab D
 * Course: CS231
 * Project: Monte-Carlo Simulation: Blackjack
 */

public class Simulation{
    public static void main(String[] args) {
        double player_w = 0;
        double dealer_w = 0;
        double push = 0;
        
        Blackjack newGame = new Blackjack(); 
    
        for (int i = 0; i<1000; i++){
            int result = newGame.game(true);
            if (result == 1){
                player_w += 1;
            }
            else if (result == -1){
                dealer_w += 1;
            }
            else{
                push ++;
            }
        }

        System.out.println("Total number of player wins:" + " " + player_w);
        System.out.println("Player wins as percentage:" + " " + (player_w / 1000)*100 + "%");
        System.out.println("Total number of dealer wins:" + " " + dealer_w);
        System.out.println("Dealer wins as percentage:" + " " + (dealer_w / 1000)*100 + "%");
        System.out.println("Total number of pushes:" + " " + push);
        System.out.println("Pushes as percentage:" + " " + (push / 1000)*100 + "%");
    }
        
}
    

