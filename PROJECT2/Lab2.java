
import java.util.Arrays;
import java.util.Random;

public class Lab2{
    public static void main(String[] args){
        // use command line to print out various strings
        if (args.length < 1){
            System.out.println("Please provide a command line argument");
        }
        else {
            for (int string = 0; string < args.length; string++){
            System.out.println(args[string]);
            }
        }

        String[][] grid;
        grid = new String[3][5];
        // The following prints out the local storage location of grid
        // System.out.println(grid);
        // The following prints out what is located at grid[0][0]
        // System.out.println(grid[0][0]);

        grid = new String[3][];
        // System.out.println(grid[0][0]); --> this throws an exception
        // grid with rows of different lengths.
        for (int i = 0; i < 3; i++){
            grid[i] = new String[i+3];
        }

        // printing out each list array in the grid to see that the rows are of different length.
        // for (int n = 0; n < grid.length; n++){
        //     System.out.println(Arrays.toString(grid[n]));
        // }

        // choose a random Dec to find a character 
        Random Dec = new Random();
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[r].length; c++){
                // use ascii table to assign random charracter to letterRandom based on Dec value
                char letterRandom = (char) Dec.nextInt(65,90);
                // assign random character to grid as a String
                grid[r][c] = "" + letterRandom;
            }
        }

        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[r].length; c++){
                System.out.format("\nThe Grid Item at " + r + "," + c +
                " position = " + grid[r][c]);
            }
        }

        // for (int n = 0; n < grid.length; n++){
        //     System.out.println(Arrays.toString(grid[n]));
        // }

        // for (int r = 0; r < grid.length; r++){
        //     for (int c = 0; c < grid[r].length; c++){
        //         System.out.print(grid[r][c]);
        //     }
        // }

        int[][] matrix;
        matrix = new int[3][5];
        // System.out.println(grid[0][0]); --> this throws an exception
        // grid with rows of different lengths.
        // for (int i = 0; i < 3; i++){
        //     matrix[i] = new int[i+3];
        // }
        Random randNumber = new Random();
        for (int r = 0; r < matrix.length; r++){
            for (int c = 0; c < matrix[r].length; c++){
                matrix[r][c] = randNumber.nextInt(0, 5);
            }
        }

        for (int r = 0; r < matrix.length; r++){
            for (int c = 0; c < matrix[r].length; c++){
                System.out.format("\nThe Matrix Item at " + r + "," + c +
                " position = " + matrix[r][c]);
            }
        }

        rotate(matrix);

        // for (int r = 0; r < rotate(matrix).length; r++){
        //     for (int c = 0; c < rotate(matrix)[r].length; c++){
        //         System.out.format("\nThe Matrix Item at " + r + "," + c +
        //         " position = " + rotate(matrix)[r][c]);
        //     }
        // }

        // int[][] matrixturn = rotate(matrix);
        // for (int n = 0; n < matrix.length; n++){
        //     System.out.println(Arrays.toString(matrixturn[n]));
        // }


        // char letterA = (char) 65;
        // System.out.println(letterA);

        // This block of code shows that the == operator when used on arrays
        // tests whether the memory location is the same.
        int[][] arr1 = new int[2][2];
        int[][] arr2 = new int[2][2];
        // int[][] arr3;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                arr1[i][j] = i+j;
                arr2[i][j] = i+j;
            }
        } 
        
        // arr3 = arr1; // allocate arr1's memory location to arr3
        // System.out.println(arr1 == arr2);
        // System.out.println(arr1 == arr3);

        // boolean a = gridEquals(arr2, arr3);
        // System.out.println(a);
    }

    public static boolean gridEquals(int[][] arr1, int[][] arr2){

        if (arr1.length != arr2.length){
            return false;
        }
        
        for (int r = 0; r < arr1.length; r++){
            for (int c = 0; c < arr1[r].length; c++){
                if (arr2 [c][arr1.length - 1 - r] != arr1[r][c]){
                    return false;
                }
                if (arr1[c].length != arr2[c].length){
                    return false;
                }
                if (arr1[r][c] != arr2[r][c]){
                    return false;
                }
            }
        }

        return true;
    }

    public static int[][] rotate(int[][] arr){
        int [][] output = arr;
    
        for (int r = 0; r < arr.length; r ++){
            for (int c = 0; c < arr[r].length; c ++){
                arr[r][c] = output[c][arr.length - 1 - r];
                // System.out.println(arr.length);
                // System.out.println(arr[r].length);
            }
        }
        arr = output;

        for (int r = 0; r < output.length; r++){
                for (int c = 0; c < output[r].length; c++){
                    System.out.format("\nThe Matrix Item at " + r + "," + c +
                    " position = " + output[r][c]);
                }
            }
    
        return output;
    }
}