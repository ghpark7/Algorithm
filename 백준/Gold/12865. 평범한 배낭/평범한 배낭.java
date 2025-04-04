import java.util.*;
import java.io.*;

public class Main {

	static int n, k;
	static int[] weight;
	static int[] value;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		weight = new int[n];
		value = new int[n];
		dp = new int[k+1];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = k; j >= weight[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j-weight[i]] + value[i]);
			}
		}
		
		System.out.println(dp[k]);
	}
}