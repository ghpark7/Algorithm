import java.util.*;

public class Solution {
    static int N;
    static int[][] grid, dp;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int maxMove, startRoom;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            grid = new int[N][N];
            dp = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            maxMove = 0;
            startRoom = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int moveCount = dfs(i, j);
                    if (moveCount > maxMove || (moveCount == maxMove && grid[i][j] < startRoom)) {
                        maxMove = moveCount;
                        startRoom = grid[i][j];
                    }
                }
            }
            System.out.println("#" + t + " " + startRoom + " " + maxMove);
        }

        sc.close();
    }

    public static int dfs(int x, int y) {
        if (dp[x][y] != 0) return dp[x][y];

        dp[x][y] = 1;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N && grid[nx][ny] == grid[x][y] + 1) {
                dp[x][y] = Math.max(dp[x][y], 1 + dfs(nx, ny));
            }
        }

        return dp[x][y];
    }
}
