import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		for(int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int func = Integer.parseInt(st.nextToken());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			if(func == 0) {
				parentUnion(n1, n2);
			}else {
				if(parentFind(n1) == parentFind(n2)) {
					System.out.println("YES");
				}
				else {
					System.out.println("NO");
				}
			}
		}
	}
	
	private static int parentFind(int a) {
		if(parents[a] != a) {
			parents[a] = parentFind(parents[a]);
		}
		return parents[a];
	}
	
	private static void parentUnion(int a, int b) {
		a = parentFind(a);
		b = parentFind(b);
		if(a < b) {
			parents[b] = a;
		}else {
			parents[a] = b;
		}
	}
}