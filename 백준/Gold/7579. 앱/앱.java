import java.util.*;
import java.io.*;

public class Main {

	static int n, m, memSum = 0, costSum = 0;
	static int[] memory;
	static int[] cost;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		memory = new int[n];
		cost = new int[n];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
			memSum += memory[i];
		}
		
		dp = new int[memSum];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			costSum += cost[i];
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = memSum - m; memory[i] <= j; j--) {
				dp[j] = Math.max(dp[j], dp[j-memory[i]] + cost[i]);
			}
		}
		
		System.out.println(costSum - dp[memSum-m]);
	}
}