import java.util.*;
import java.io.*;

public class Solution {

    static class State {
        int x, y, dir, cut, cnt;

        public State(int x, int y, int dir, int cut, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cut = cut;
            this.cnt = cnt;
        }
    }

    static int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dy = {0, 1, 0, -1};
    static int N, K;
    static char[][] map;
    static int min = Integer.MAX_VALUE;
    static boolean[][][][] visited; // x, y, dir, cut

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new char[N][N];

            int sx = 0, sy = 0, ex = 0, ey = 0;

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == 'X') {
                        sx = i;
                        sy = j;
                    } else if (map[i][j] == 'Y') {
                        ex = i;
                        ey = j;
                    }
                }
            }

            min = Integer.MAX_VALUE;
            visited = new boolean[N][N][4][K + 1];
            bfs(sx, sy, ex, ey);
            System.out.println("#" + t + " " + (min == Integer.MAX_VALUE ? -1 : min));
        }
    }

    static void bfs(int sx, int sy, int ex, int ey) {
        Queue<State> q = new LinkedList<>();
        q.offer(new State(sx, sy, 0, 0, 0)); // 시작 방향은 북쪽 (0)
        visited[sx][sy][0][0] = true;

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.x == ex && cur.y == ey) {
                min = Math.min(min, cur.cnt);
                continue;
            }

            // 전진
            int nx = cur.x + dx[cur.dir];
            int ny = cur.y + dy[cur.dir];
            if (isIn(nx, ny)) {
                char next = map[nx][ny];
                if ((next == 'G' || next == 'Y') && !visited[nx][ny][cur.dir][cur.cut]) {
                    visited[nx][ny][cur.dir][cur.cut] = true;
                    q.offer(new State(nx, ny, cur.dir, cur.cut, cur.cnt + 1));
                } else if (next == 'T' && cur.cut < K && !visited[nx][ny][cur.dir][cur.cut + 1]) {
                    visited[nx][ny][cur.dir][cur.cut + 1] = true;
                    q.offer(new State(nx, ny, cur.dir, cur.cut + 1, cur.cnt + 1));
                }
            }

            // 좌회전
            int ldir = (cur.dir + 3) % 4;
            if (!visited[cur.x][cur.y][ldir][cur.cut]) {
                visited[cur.x][cur.y][ldir][cur.cut] = true;
                q.offer(new State(cur.x, cur.y, ldir, cur.cut, cur.cnt + 1));
            }

            // 우회전
            int rdir = (cur.dir + 1) % 4;
            if (!visited[cur.x][cur.y][rdir][cur.cut]) {
                visited[cur.x][cur.y][rdir][cur.cut] = true;
                q.offer(new State(cur.x, cur.y, rdir, cur.cut, cur.cnt + 1));
            }
        }
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
