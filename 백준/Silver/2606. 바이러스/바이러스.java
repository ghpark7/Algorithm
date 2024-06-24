import java.util.Scanner;

class Main {
  	static int cnt = 0;
  	
	static void dfs(int[][] a, boolean[] check, int v) {
		if(check[v]==true)
            return;
		
		check[v] = true;
		cnt++;
		
		for(int i=0;i<a[v].length;i++) {
			if(a[v][i]==1 && !check[i])
				dfs(a,check,i);
		}				
	}
    
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 정점의 수
		int e = sc.nextInt(); // 간선의 수
		
		int adj[][] = new int[n+1][n+1];
		boolean visit[] = new boolean[n+1];
		
		for(int i = 0; i < e; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			
			adj[v1][v2] = 1;
			adj[v2][v1] = 1;
		}
			
		dfs(adj,visit,1);
		System.out.println(cnt-1);
	  }
}