import java.util.*;

public class PartA_AutoboxingUnboxing {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        
        // Autoboxing - converting int to Integer automatically
        for (int i = 1; i <= 5; i++) {
            numbers.add(i); // int is automatically converted to Integer
        }

        int sum = 0;

        // Unboxing - converting Integer to int automatically
        for (Integer num : numbers) {
            sum += num; // Integer is automatically converted to int
        }

        System.out.println("Sum of integers: " + sum);
    }
}
