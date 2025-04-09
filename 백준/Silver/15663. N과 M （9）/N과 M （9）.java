import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] result;
    static boolean[] visited;
    static Set<String> set = new LinkedHashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        result = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0);

        for (String s : set) {
            sb.append(s).append("\n");
        }

        System.out.print(sb.toString());
    }

    static void dfs(int depth) {
        if (depth == M) {
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < M; i++) {
                temp.append(result[i]).append(" ");
            }
            set.add(temp.toString().trim());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = arr[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}
