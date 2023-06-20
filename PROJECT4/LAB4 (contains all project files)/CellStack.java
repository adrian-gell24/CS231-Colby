public class CellStack {

    private class Node{
        Cell cell;
        Node next;
        
        /**
         * A constructor that initializes next to null and replaces cell field with a given cell
         * @param cell
         */
        public Node(Cell cell){
            this.cell = cell;
            this.next = null;
        }
    }

    private Node head; // this should always be the top of my stack
    private int size; // the size of the stack

    /**
     * Initialize the stack's fields.
     */
    public CellStack(){
        head = null;
        size = 0;
    }

    /**
     * Push c onto the stack.
     * @param c a new Cell
     */
    public void push(Cell c){
        Node newNode = new Node(c);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Return the top Cell on the stack.
     * @return head Cell
     */
    public Cell peek(){
        return head.cell; 
    }

    /**
     * Remove and return the top element from the stack; return null if the stack is empty.
     * @return null if the stack is empty, otherwise the data held by head before it was moved to the next Node
     */
    public Cell pop(){
        if (this.head == null){
            return null;
        }
        Node temp = this.head;
        this.head = this.head.next;
        size--;
        
        return temp.cell;
    }

    /**
     * Return the number of elements in the stack.
     * @return size
     */
    public int size(){
        return size;
    }

    /**
     * Return true if the stack is empty.
     * @return boolean corresponding with whether the stack is empty or not
     */
    public boolean empty(){
        if (size != 0){
            return false;
        }
        return true;
    }
}