import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static String id;
    static Map<Character, List<int[]>> charPositions = new HashMap<>();
    static Map<Character, Integer> idCount = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                char c = map[i][j];
                if (c != '.') {
                    charPositions.computeIfAbsent(c, k -> new ArrayList<>()).add(new int[]{i, j});
                }
            }
        }
        id = br.readLine();

        for (char c : id.toCharArray()) {
            idCount.put(c, idCount.getOrDefault(c, 0) + 1);
        }

        int K = Integer.MAX_VALUE;
        for (char c : idCount.keySet()) {
            List<int[]> positions = charPositions.get(c);
            if (positions == null || positions.size() < idCount.get(c)) {
                K = 0;
                break;
            }
            K = Math.min(K, positions.size() / idCount.get(c));
        }

        if (K == Integer.MAX_VALUE) K = 0; 

        List<int[]> allTargets = new ArrayList<>();
        for (int k = 0; k < K; k++) {
            Map<Character, Integer> pointers = new HashMap<>();
            for (char c : idCount.keySet()) {
                pointers.put(c, k * idCount.get(c));
            }
            for (char c : id.toCharArray()) {
                int ptr = pointers.get(c);
                allTargets.add(charPositions.get(c).get(ptr));
                pointers.put(c, ptr + 1);
            }
        }

        List<Character> commands = new ArrayList<>();
        int x = 0, y = 0;

        for (int[] target : allTargets) {
            int tx = target[0], ty = target[1];
            appendMoves(commands, x, y, tx, ty);
            commands.add('P');
            x = tx;
            y = ty;
        }

        appendMoves(commands, x, y, N - 1, M - 1);

        System.out.println(K + " " + commands.size());
        for (char c : commands) System.out.print(c);
        System.out.println();
    }

    static void appendMoves(List<Character> commands, int x1, int y1, int x2, int y2) {
        int dy = y2 - y1;
        if (dy > 0) {
            for (int i = 0; i < dy; i++) commands.add('R');
        } else if (dy < 0) {
            for (int i = 0; i < -dy; i++) commands.add('L');
        }

        int dx = x2 - x1;
        if (dx > 0) {
            for (int i = 0; i < dx; i++) commands.add('D');
        } else if (dx < 0) {
            for (int i = 0; i < -dx; i++) commands.add('U');
        }
    }
}