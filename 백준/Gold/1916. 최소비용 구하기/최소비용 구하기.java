import java.util.*;
import java.io.*;

public class Main {

	static class Node implements Comparable<Node> {
		int to, cost;
		Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	static int n, m;
	static List<Node>[] graph;
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[n+1];
		dist = new int[n+1];
		
		for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from].add(new Node(to, cost));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		
		System.out.println(dist[end]);
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.cost > dist[cur.to]) continue;
			
			for(Node next : graph[cur.to]) {
				if(dist[next.to] > dist[cur.to] + next.cost) {
					dist[next.to] = dist[cur.to] + next.cost;
					pq.add(new Node(next.to, dist[next.to]));
				}
			}
		}
	}

}