import java.util.Arrays;

public class Inversions {
    // Helper method to create subArrays from arrays
    // The indexes work just like substring: begin is included
    // but end is excluded
    public static int[] subArray(int[] arr, int begin, int end) {
        int[] output = new int[end-begin];
        for (int i = 0; i < output.length; i++) {
            output[i] = arr[begin+i];
        }
        return output;
    }

    // Helper method to print array to aid debugging
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // Give two sorted arrays A and B return one
    // large combined sorted array


    // Recursively sorts the array using Merge Sort

    // Merges two arrays AND counts the number of inversions between them
    public static InversionResult mergeAndCount(int[] A, int[] B) {
        int[] C = new int[A.length + B.length];
        int totalCount = 0;
        int aCount = 0;
        int bCount = 0;
        // add the min between a and b until the size is matched
        for(int i = 0; i<A.length+B.length;i++){
            if(aCount >= A.length){
                C[i] = B[bCount];
                bCount++;

            }
            else if(bCount >= B.length){
                C[i] = A[aCount];
                aCount++;
            }
            else if(A[aCount] < B[bCount]){
                C[i] = A[aCount];
                aCount ++;
            }
            else{
                C[i] = B[bCount];
                bCount++;
                totalCount+= A.length-aCount;
            }
        }
        return new InversionResult(C,totalCount);
    }

    // Sorts two arrays AND sums the number of inversions between them
    public static InversionResult sortAndCount(int[] arr) {
        if(arr.length > 1){
            InversionResult a = sortAndCount(subArray(arr, 0, arr.length/2));
            InversionResult b = sortAndCount(subArray(arr, arr.length/2, arr.length));
            InversionResult c = mergeAndCount(a.arr,b.arr);
            int count = a.count+b.count+c.count;
            return new InversionResult(c.arr, count);
        }
        else{
            return new InversionResult(arr, 0);
        }
    }

    public static void main(String[] args) {
        // --------------------------
        // Test 1: Merge and Count Method
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 1: Merge and Count Method");
        System.out.println("Expected:");
        System.out.println("[1, 2, 3, 4, 5, 6, 7, 8]\n" +
                "8");

        System.out.println("\nGot:");


        int[] arr1 = {1, 3, 6, 8};
        int[] arr2 = {2, 4, 5, 7};
        InversionResult m1 = mergeAndCount(arr1, arr2);

        printArray(m1.arr);
        System.out.println(m1.count);

        // --------------------------
        // Test 2: Sort and Count
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 2: Sort and Count");
        System.out.println("Expected:");
        System.out.println("[1, 2, 3, 4]\n" +
                "5\n" +
                "[1, 2, 3, 4, 5, 6, 7, 8]\n" +
                "7\n" +
                "[1, 2, 3, 4, 5, 6, 7, 8]\n" +
                "8");

        System.out.println("\nGot:");

        InversionResult A = sortAndCount(new int[]{4, 2, 3, 1});
        printArray(A.arr);
        // [1, 2, 3, 4]
        System.out.println(A.count);
        // 5

        InversionResult B = sortAndCount(new int[]{2, 3, 4, 5, 6, 7, 8, 1});
        printArray(B.arr);
        // [1, 2, 3, 4, 5, 6, 7, 8]
        System.out.println(B.count);
        // 7

        InversionResult C = sortAndCount(new int[]{2, 3, 7, 4, 1, 5, 8, 6});
        printArray(C.arr);
        // [1, 2, 3, 4, 5, 6, 7, 8]
        System.out.println(C.count);
        // 8
    }

    static class InversionResult {
        int[] arr;
        int count;

        public InversionResult(int[] arr, int count) {
            this.arr = arr;
            this.count = count;
        }
    }
}
