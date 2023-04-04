import java.util.Arrays;

public class SubsetSum {
    // Given an array of integers and an int sum, determine if some subset
    // of those integers adds up to exactly sum

    // For example, if our input array is [1, 1, 4], the possible sums which would
    // return true are 0, 1, 2, 4, 5, 6. Any other sum value would return false.
    public static boolean subsetSum(int[] arr, int sum) {
        boolean[][] mat  = new boolean[arr.length+1][sum+1];
        // all mat[i][0] is true, all mat[0][i] is false if i != 0
        for(int r = 0; r<mat.length;r++){
            mat[r][0] = true;
        }
        for(int c=1;c<mat.length;c++){
            mat[0][c] = false;
        }

        for(int r = 1; r < mat.length; r++){
            int[] subArr = new int[r];
            // update subarr to hold arr's contents
            System.arraycopy(arr, 0, subArr, 0, r);
            for (int c = 1; c < mat[r].length;c++){
                // get the latest value and check the 2 cases
                int value = subArr[subArr.length-1];
                // case 1: up 1 left 1
                // else false
                if(c - value >= 0 && mat[r-1][c-value]){
                    mat[r][c] = true;
                }
                //case 2: up 1
                else mat[r][c] = mat[r - 1][c];
            }
        }

        return mat[mat.length-1][sum];
    }

    // Given some items with both weight and value, as well as a max capacity
    // determine the most value we can fit in the knapsack

    // Note that the weight and value of each item is stored in same index of the relevant array
    // (e.g. weights[2] is the weight of item 2 and values[2] is that same item's value)
    public static int knapsack(int[] weights, int[] values, int capacity) {
        // make a 2d array of all possible combinations of smaller ones and solve from small to big
        // 10, 15, cap is 20


        // the item is either included or not included
        // check the value if included and the value if not included
        int[][] mat = new int[weights.length+1][capacity+1];
        for(int r = 0; r < mat.length; r++){
            mat[r][0] = 0;

        }
        for(int c = 1; c<mat[0].length;c++){
            mat[0][c] = 0;
        }

        for(int r = 1; r < mat.length; r++){
            int[] subArr = new int[r];
            System.arraycopy(weights,0,subArr,0,r);
            for(int c=1; c<mat.length;c++){
                int itemIncluded = mat[r-1][c - subArr[subArr.length-1]] + weights[subArr.length-1];
            }
        }


        return -1;
    }

    public static void main(String[] args) {
        // --------------------------
        // Test 1: Basic Subset Sum
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 1: Basic Subset Sum");
        System.out.println("Expected:");
        System.out.println("true\n" +
                "true\n" +
                "true\n" +
                "false");

        System.out.println("\nGot:");


        // Does {1, 1, 3, 7} have a subset sum of 5?
        int[] arr1 = {1, 1, 3, 7};
        System.out.println(subsetSum(arr1, 5));
        // true

        // Does {1, 1, 3, 7} have a subset sum of 11?
        System.out.println(subsetSum(arr1, 11));
        // true

        // Does {1, 1, 3, 7} have a subset sum of 12?
        System.out.println(subsetSum(arr1, 12));
        // true

        // Does {1, 1, 3, 7} have a subset sum of 6?
        System.out.println(subsetSum(arr1, 6));
        // false

        // --------------------------
        // Test 2: More Subset Sum
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 2: More Subset Sum");
        System.out.println("Expected:");
        System.out.println("true\n" +
                "true\n" +
                "true\n" +
                "false");

        System.out.println("\nGot:");


        // Does {1, 1, 5, 6, 8, 10, 12, 14, 16} have a subset sum of 11?
        int[] arr2 = {1, 1, 5, 6, 8, 10, 12, 14, 16};
        System.out.println(subsetSum(arr2, 11));
        // true

        // Does {1, 1, 5, 6, 8, 10, 12, 14, 16} have a subset sum of 28?
        System.out.println(subsetSum(arr2, 28));
        // true

        // Does {1, 1, 5, 6, 8, 10, 12, 14, 16} have a subset sum of 32?
        System.out.println(subsetSum(arr2, 32));
        // true

        // Does {1, 1, 5, 6, 8, 10, 12, 14, 16} have a subset sum of 68?
        System.out.println(subsetSum(arr1, 68));
        // true

        // --------------------------
        // Test 3: Basic Knapsack
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 3: Basic Knapsack");
        System.out.println("Expected:");
        System.out.println("160\n" +
                "290\n" +
                "200");

        System.out.println("\nGot:");

        // Basic Tests
        int[] weights1 = {5, 10, 25};
        int[] values1 = {70, 90, 140};
        System.out.println(knapsack(weights1, values1, 25));
        // 160

        int[] weights2 = {5, 10, 20};
        int[] values2 = {150, 60, 140};
        System.out.println(knapsack(weights2, values2, 30));
        // 290

        int[] weights3 = {5, 20, 10};
        int[] values3 = {50, 140, 60};
        System.out.println(knapsack(weights3, values3, 30));
        // 200

        // --------------------------
        // Test 4: Advanced Knapsack
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 4: Advanced Knapsack");
        System.out.println("Expected:");
        System.out.println("117");

        System.out.println("\nGot:");

        // More advanced test
        int[] weights4 = {85, 26, 48, 21, 22, 95, 43, 45, 55, 52};
        int[] values4 = {79, 32, 47, 18, 26, 85, 33, 40, 45, 59};
        System.out.println(knapsack(weights4, values4, 101));
        // 117
    }
}
