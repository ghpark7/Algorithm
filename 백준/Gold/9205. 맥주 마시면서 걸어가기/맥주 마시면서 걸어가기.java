import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
	
	static int t, n;
	static Point home, festival;
	static Point[] store;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			isVisited = new boolean[n];
			store = new Point[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				store[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			festival = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			if(bfs()) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
		}
	}
	
	static boolean bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(home);
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			if(distance(current, festival) <= 1000) return true;
			for(int i = 0; i < n; i++) {
				if(!isVisited[i] && distance(current, store[i]) <= 1000 ) {
					isVisited[i] = true;
					queue.offer(store[i]);
				}
			}
		}
		
		return false;
	}
	
	static int distance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

}