public class CellStackTests {

    public static void main(String[] args){
        // case 1: Constructor
        {
            // setup
            CellStack stack = new CellStack();

            // verify
            System.out.println(stack);

            // test
            assert stack != null : "Error in CellStack::CellStack()";
        }

        // case 2: push()
        {
            // setup
            CellStack stack = new CellStack();
            for(int i = 0; i < 5; i++){
                stack.push(new Cell());
            }

            // verify
            System.out.println(stack.size() + " == 5");
            
            // test
            assert stack.size() == 5 : "Error in CellStack::push()";
        }

        // case 3: peek()
        {
            // setup
            CellStack stack = new CellStack();
            for(int i = 1; i < 5; i++){
                stack.push(new Cell(i, i, i));
            }
            Cell peek = stack.peek();

            // verify
            System.out.println(peek.getValue() + " == 4");
            System.out.println(stack.size() + " == 4");

            // test
            assert peek.getValue() == 4 : "Error in CellStack::peek()"; 
            assert stack.size() == 4 : "Error in CellStack::peek()";
        }

        // case 4: pop()
        {
            // setup
            CellStack stack = new CellStack();
            for(int i = 1; i < 5; i++){
                stack.push(new Cell(i, i, i));
            }
            Cell pop = stack.pop();

            // verify
            System.out.println(pop.getValue() + " == 4");
            System.out.println(stack.size() + " == 3");

            // test
            assert pop.getValue() == 4 : "Error in CellStack::pop()"; 
            assert stack.size() == 3 : "Error in CellStack::pop()";
        }
    }

}