import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        result = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        Set<String> set = new LinkedHashSet<>();
        backtrack(0, 0, set);

        for (String s : set) {
            sb.append(s).append("\n");
        }

        System.out.print(sb.toString());
    }

    static void backtrack(int depth, int start, Set<String> set) {
        if (depth == M) {
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < M; i++) {
                temp.append(result[i]).append(" ");
            }
            set.add(temp.toString().trim());
            return;
        }

        for (int i = start; i < N; i++) {
            result[depth] = arr[i];
            backtrack(depth + 1, i, set);
        }
    }
}
