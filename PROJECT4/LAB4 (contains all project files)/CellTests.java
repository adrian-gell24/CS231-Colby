public class CellTests {
    
    public static void main(String[] args){
        // case 1: Constructor
        {
            // setup
            Cell c1 = new Cell();
            Cell c2 = new Cell(1, 2, 3);
            Cell c3 = new Cell(1, 2, 3, true);

            // verify
            System.out.println(c1);
            System.out.println(c2);
            System.out.println(c3);

            // test
            assert c1!=null : "Error in Cell::Cell()";
            assert c2!=null : "Error in Cell::Cell()";
            assert c3!=null : "Error in Cell::Cell()";
        }

        // case 2: value
        {
            // setup
            Cell c1 = new Cell();
            Cell c2 = new Cell(1, 2, 3);
            Cell c3 = new Cell(1, 2, 3, true);

            // verify
            System.out.println(c1.getValue() + " == 0");
            System.out.println(c2.getValue() + " == 3");
            System.out.println(c3.getValue() + " == 3");

            // test
            assert c1.getValue() == 0 : "Error in Cell::Cell()";
            assert c2.getValue() == 3 : "Error in Cell::Cell()";
            assert c3.getValue() == 3 : "Error in Cell::Cell()";
        }

        // case 3: locked
        {
            // setup
            Cell c1 = new Cell();
            Cell c2 = new Cell(1, 2, 3);
            Cell c3 = new Cell(1, 2, 3, true);

            // verify
            System.out.println(c1.isLocked() + " == false");
            System.out.println(c2.isLocked() + " == false");
            System.out.println(c3.isLocked() + " == true");

            // test
            assert c1.isLocked() == false : "Error in Cell::Cell()";
            assert c2.isLocked() == false : "Error in Cell::Cell()";
            assert c3.isLocked() == true : "Error in Cell::Cell()";
        }
    }

}
