/*
file name:      BlackjackTests.java
Authors:        Max Bender & Naser Al Madi
Editor: Adrian Gellert
last modified:  9/26/2022

How to run:     java -ea BlackjackTests
*/


public class BlackjackTests {

    public static void blackjackTests() {

        // case 1: testing Blackjack() and Blackjack(i)
        {
            // set up
            Blackjack bj1 = new Blackjack();
            Blackjack bj2 = new Blackjack(5);

            // verify

            // test
            assert bj1 != null : "Error in Blackjack::Blackjack()";
            assert bj2 != null : "Error in Blackjack::Blackjack()";
        }

        // case 2: testing reset()
        {
            // set up
            Blackjack bj1 = new Blackjack();

            // verify
            bj1.reset();

            // test
            assert bj1 != null : "Error in Blackjack::Blackjack()";
        }

        // case 3: testing deal()
        {
            // set up
            Blackjack bj1 = new Blackjack();

            // verify
            bj1.deal();

            // test
            assert bj1 != null : "Error in Blackjack::Blackjack()";
        }

        // case 4: testing playerTurn()
        {
            // set up
            Blackjack bj1 = new Blackjack();

            // verify
            bj1.playerTurn();

            // test
            assert bj1 != null : "Error in Blackjack::Blackjack()";
        }

        // case 5: testing dealerTurn()
        {
            // set up
            Blackjack bj1 = new Blackjack();

            // verify
            bj1.dealerTurn();

            // test
            assert bj1 != null : "Error in Blackjack::Blackjack()";
        }

        // case 6: testing setReshuffleCutoff()
        {
            // set up
            Blackjack bj1 = new Blackjack();

            // verify
            bj1.setReshuffleCutoff(10);

            // test
            assert bj1.getReshuffleCutoff() != 10 : "Error in Blackjack::Blackjack()";
        }

        // case 7: testing getReshuffleCutoff()
        {
            // set up
            Blackjack bj1 = new Blackjack(5);

            // verify

            // test
            assert bj1.getReshuffleCutoff() != 5 : "Error in Blackjack::Blackjack()";
        }

        // case 8: testing toString
        {
            // set up
            Blackjack bj1 = new Blackjack();
            Blackjack bj2 = new Blackjack(5);

            // verify
            System.out.println(bj1.toString() + " == 5");
            System.out.println(bj2.toString() + " == 10");

            // test
            assert bj1.toString().equals("5") : "Error in Blackjack::Blackjack()";
            assert bj2.toString().equals("10") : "Error in Blackjack::Blackjack()";
        }

        // case 9: testing game
       
        System.out.println("*** Done testing Blackjack! ***\n");
    }


    public static void main(String[] args) {

        blackjackTests();
    }
}