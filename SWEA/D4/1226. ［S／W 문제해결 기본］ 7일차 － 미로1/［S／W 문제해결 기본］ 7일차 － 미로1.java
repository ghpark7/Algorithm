import java.awt.Point;
import java.io.*;

public class Solution {
	
	static int[][] maze;
	static boolean[][] visited;
	static Point start, end;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0 ; i < 10; i++) {
			int tc = Integer.parseInt(br.readLine());
			maze = new int[16][16];
			visited = new boolean[16][16];
			result = 0;
			
			for(int x = 0; x < 16; x++) {
				String line = br.readLine();
				for(int y = 0; y < 16; y++) {
					maze[x][y] = line.charAt(y) - '0';
					if(maze[x][y] == 2) {
						start = new Point(x, y);
						visited[x][y] = true;
					} else if(maze[x][y] == 3) {
						end = new Point(x, y);
					}
				}
			}
			
			dfs(start, end);
			
			System.out.println("#" + tc + " " + result);
		}
	}
	
	static void dfs(Point a, Point b) {
		
		if(a.equals(b)) {
			result = 1;
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = a.x + dx[i];
			int ny = a.y + dy[i];
			
			if(0 <= nx && nx < 16 && 0 <= ny && ny < 16) {
				if((maze[nx][ny] == 0 || maze[nx][ny] == 3) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(new Point(nx, ny), b);
					
					if(result == 1) return;
				}
			}
		}
	}

}
