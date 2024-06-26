import java.util.Arrays;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int x = scanner.nextInt();

        Arrays.sort(arr);

        int start = 0;
        int end = n - 1;
        int answer = 0;

        while(start < end) {
            int sum = arr[start] + arr[end];
            if (sum == x) {
                answer++;
                start++;
            }
            else if (sum < x)
                start++;
            else
                end--;
        }

        System.out.println(answer);
    }
}