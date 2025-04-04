import java.util.*;
import java.io.*;

public class Main {

	static int n, result = 0;
	static int[][] home;
	static int[][][] dp;	// 0: 가로, 1: 대각선, 2: 세로
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		home = new int[n+1][n+1];
		dp = new int[n+1][n+1][3];
		for(int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= n; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][2][0] = 1;
		
		for(int i = 1; i <= n; i++) {
			for(int j = 3; j <= n; j++) {
				if (home[i][j] == 1) continue;
				
				// 가로
				dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
				
				
				// 세로
				if(i > 1) {
					dp[i][j][2] = dp[i-1][j][2] + dp[i-1][j][1];
				}
				
				// 대각선
				if(i > 1 && home[i-1][j] == 0 && home[i][j-1] == 0) {
					dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		
		result = dp[n][n][0] + dp[n][n][1] + dp[n][n][2];
				
		System.out.println(result);
	}

}