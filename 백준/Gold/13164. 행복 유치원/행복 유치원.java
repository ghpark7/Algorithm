import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] height = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		if (k >= n) {
            System.out.println(0);
            return;
        }

        int[] diffs = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            diffs[i] = height[i+1] - height[i];
        }

        Arrays.sort(diffs);
        
        int totalCost = 0;
        for (int i = 0; i < n-k; i++) {
            totalCost += diffs[i];
        }

        System.out.println(totalCost);
	}

}
