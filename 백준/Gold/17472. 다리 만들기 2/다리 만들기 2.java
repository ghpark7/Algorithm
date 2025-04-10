import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int islandCnt = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static List<Bridge> bridges = new ArrayList<>();

    static class Bridge implements Comparable<Bridge> {
        int from, to, length;

        Bridge(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public int compareTo(Bridge o) {
            return this.length - o.length;
        }
    }

    static class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n+1];
            for (int i = 1; i <= n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] == x) return x;
            return parent[x] = find(parent[x]);
        }

        boolean union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) return false;
            parent[b] = a;
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 번호 붙이기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    islandCnt++;
                    bfs(i, j, islandCnt);
                }
            }
        }

        // 다리 후보 찾기
        findBridges();

        // MST
        Collections.sort(bridges);
        UnionFind uf = new UnionFind(islandCnt);
        int total = 0;
        int count = 0;

        for (Bridge b : bridges) {
            if (uf.union(b.from, b.to)) {
                total += b.length;
                count++;
            }
        }

        if (count == islandCnt - 1) {
            System.out.println(total);
        } else {
            System.out.println(-1);
        }
    }

    // 섬 번호 붙이기 BFS
    static void bfs(int x, int y, int idx) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = idx;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (!visited[nx][ny] && map[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        map[nx][ny] = idx;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    // 다리 후보 찾기
    static void findBridges() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    int from = map[i][j];
                    for (int d = 0; d < 4; d++) {
                        int len = 0;
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        while (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                            if (map[nx][ny] == from) break; // 자기 섬
                            if (map[nx][ny] > 0) { // 다른 섬
                                if (len >= 2) {
                                    bridges.add(new Bridge(from, map[nx][ny], len));
                                }
                                break;
                            }

                            nx += dx[d];
                            ny += dy[d];
                            len++;
                        }
                    }
                }
            }
        }
    }
}
