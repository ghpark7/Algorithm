import java.util.*;

public class Main {
	
	static int N, M;
	static int[] arr = new int[9];
	static boolean[] isVisited = new boolean[9];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		comb(1,0);
	}

	static void comb(int x, int cnt) {
		if(cnt == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.print("\n");
			return;
		}
		
		for(int i = x; i <= N; i++) {
			if(!isVisited[i]) {
				isVisited[i] = true;
				arr[cnt] = i;
				comb(i+1, cnt+1);
				isVisited[i] = false;
			}
		}
	}
}