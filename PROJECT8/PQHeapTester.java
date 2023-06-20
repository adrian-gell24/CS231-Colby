import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class PQHeapTester {

     
    static PrintStream originalStream = System.out;
    static PrintStream dummyStream = new PrintStream(new OutputStream(){
        public void write(int b) {
            // NO-OP
        }
    });

    public static void swapStream(){
        System.setOut(System.out==originalStream ? dummyStream : originalStream);
    }

    
    public static void main(String[] args){
        swapStream();
        PQHeap<Integer> heap = new PQHeap<Integer>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                return o1 - o2;
            }
            
        });

        int initialCapacity = heap.capacity();


        Random rand = new Random();
        Integer[] arr = new Integer[1000];
        for(int i = 0; i < 100; i++){
            arr[i] = rand.nextInt(10000);
            heap.offer(arr[i]);
        }

        int oldCapacity = heap.capacity();
        if(oldCapacity >= 1000) {
            swapStream();System.out.println("Wow, huge capacity");swapStream();
        }

        for(int i = 100; i < Math.min(oldCapacity + 1, 1000); i++){
            arr[i] = rand.nextInt(10000);
            heap.offer(arr[i]);
        }

        if (heap.capacity() != oldCapacity * 2){
            swapStream();
            System.out.println("Bad capacity set");
            swapStream();
        }

        for(int i = heap.size(); i < 1000; i++){
            arr[i] = rand.nextInt(10000);
            heap.offer(arr[i]);
        }

        if (heap.size() != 1000){
            swapStream();
            System.out.println("Bad size after offers");
            swapStream();
        }

        int sizeFaults = 0, halveFaults = 0;

        Arrays.sort(arr, new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                return o2 - o1;
            } 
            
        });
        for(int i = 0; i < 1000; i++){
            int poll = heap.poll();

            if (poll != arr[i]){ 
                swapStream();
                System.out.println("Bad poll on " + i + ": " + poll + ", " + arr[i]);
                swapStream();
                return;
            }

            if (sizeFaults < 5 && heap.size() != 1000 - i - 1){
                swapStream();
                System.out.println("Bad size after polling");
                swapStream();
                sizeFaults++;
            }

            if (heap.size() * 4 > initialCapacity && halveFaults < 5 && heap.size() * 4 < heap.capacity()) {
                swapStream();
                System.out.println("Didn't halve capacity at " + heap.size() + ", " + heap.capacity());
                halveFaults++;
                swapStream();
            }
        }
    }
}