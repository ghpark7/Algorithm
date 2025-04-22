import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][][] dp = new int[n + 1][2][3];
        dp[0][0][0] = 1;

        for (int day = 0; day < n; day++) {
            for (int late = 0; late < 2; late++) {
                for (int absent = 0; absent < 3; absent++) {
                    int curr = dp[day][late][absent];
                    if (curr == 0) continue;

                    dp[day + 1][late][0] = (dp[day + 1][late][0] + curr) % 1000000;

                    if (late + 1 < 2)
                        dp[day + 1][late + 1][0] = (dp[day + 1][late + 1][0] + curr) % 1000000;

                    if (absent + 1 < 3)
                        dp[day + 1][late][absent + 1] = (dp[day + 1][late][absent + 1] + curr) % 1000000;
                }
            }
        }

        int result = 0;
        for (int late = 0; late < 2; late++) {
            for (int absent = 0; absent < 3; absent++) {
                result = (result + dp[n][late][absent]) % 1000000;
            }
        }

        System.out.println(result);
    }
}