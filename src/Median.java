import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class Median {
    // Wrapper method to call the recursive select method
    public static int median(LinkedList<Integer> S) {
        return select(S, (S.size() + 1)/2);
    }

    // Returns the k-th largest value in S, recursively
    public static int select(LinkedList<Integer> S, int k) {
        int x = S.get(0);
        ArrayList<Integer> Sm = new ArrayList<>();
        ArrayList<Integer> Sp = new ArrayList<>();
        for(int i = 1; i < S.size(); i++){
            Integer B = S.get(i);
            if(B < x){Sm.add(B);}
            else{Sp.add(B);}
        }
        if(Sm.size() == k - 1){
            return x;
        }
        else if (Sm.size() >= k){
            return select(new LinkedList<>(Sm),k);
        }
        else {
            return select(new LinkedList<>(Sp), k - 1 - Sm.size());
        }
    }

    public static void main(String[] args) {
        // --------------------------
        // Test 1: Median
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 1: Median");
        System.out.println("Expected:");
        System.out.println("5\n" +
                "5\n" +
                "34");

        System.out.println("\nGot:");

        LinkedList<Integer> list1 = new LinkedList<>(Arrays.asList(9, 5, 1, 8, 2, 3, 4, 6, 7));
        System.out.println(median(list1));
        // 5

        LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(9, 5, 1, 8, 2, 3, 4, 6, 7, 10));
        System.out.println(median(list2));
        // 5

        LinkedList<Integer> list3 = new LinkedList<>(Arrays.asList(13, 24, 51, 67, 32, 78, 12, 66, 34, 56, 21, 53, 87, 93, -11, 23, 0));
        System.out.println(median(list3));
        // 34
    }
}
