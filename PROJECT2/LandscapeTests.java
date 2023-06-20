/*
file name:      LandscapeTests.java
Authors:        Max Bender & Naser Al Madi
last modified:  9/18/2022

How to run:     java -ea LandscapeTests
*/


import java.util.ArrayList;

public class LandscapeTests {

    public static void landscapeTests() {

        // case 1: testing Landscape(int, int)
        {
            // set up
            Landscape l1 = new Landscape(2, 4);
            Landscape l2 = new Landscape(10, 10);

            // verify
            System.out.println(l1);
            System.out.println("\n");
            System.out.println(l2);

            // test
            assert l1 != null : "Error in Landscape::Landscape(int, int)";
            assert l2 != null : "Error in Landscape::Landscape(int, int)";
        }

        // case 2: testing reset()
        {
            // set up
            Landscape l1 = new Landscape(2, 4, .2);

            // verify
            System.out.println(l1);
            l1.reset();
            System.out.println("\n");
            System.out.println(l1);

            // test
            assert l1 != null : "Error in Landscape::reset()";
        }

        // case 3: testing getRows()
        {
            // set up
            Landscape l1 = new Landscape(8, 12, .2);

            // verify
            System.out.println(l1);

            // test
            assert l1.getRows() == 8 : "Error in Landscape::getRows()";
        }

        // case 4: testing getCols()
        {
            // set up
            Landscape l1 = new Landscape(8, 12, .2);

            // verify
            System.out.println(l1);

            // test
            assert l1.getCols() == 12 : "Error in Landscape::getCols()";
        }

        // case 5: testing getCell(int, int)
        {
            // set up
            Landscape l1 = new Landscape(8, 12, .2);

            // verify
            System.out.println(l1);

            // test
            assert l1.getCell(0, 8) != null : "Error in Landscape::getCell()";

        }

        // case 6: testing getNeighbors()
        {
            // set up
            Landscape l1 = new Landscape(8, 12, .2);

            // verify
            System.out.println(l1);

            // test
            assert l1.getNeighbors(7, 9) != null : "Error in Landscape::getNeighbors()";

        }

        // case 7: testing advance()
        {
            // set up
            Landscape l1 = new Landscape(4, 4, .2);

            // verify
            System.out.println(l1);
            l1.advance();
            System.out.print(l1);

            // test is visual in terminal
            System.out.println("*** Done testing Cell! ***\n");
        }

    }


    public static void main(String[] args) {

        landscapeTests();
    }
}
