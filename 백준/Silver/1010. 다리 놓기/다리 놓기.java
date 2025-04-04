import java.util.*;
import java.io.*;

public class Main {
	
	static int t, n, m;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		dp = new int[31][31];
		for(int i = 0; i < 31; i++) {
			dp[i][0] = 1;
			dp[i][i] = 1;
			for(int j = 1; j < i; j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
			}
		}
		
		t = Integer.parseInt(st.nextToken());
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			System.out.println(dp[m][n]);
		}
	}
}