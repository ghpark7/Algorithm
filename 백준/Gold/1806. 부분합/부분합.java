import java.util.*;
import java.io.*;

public class Main {

	static int n, s, result = 100001;
	static int[] arr;
	static int[] sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1];
		sum = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(i == 1) sum[i] = arr[i];
			else sum[i] = sum[i-1] + arr[i];
		}
		
		int start = 0;
		int end = 1;
		
		while(end <= n) {
			if(sum[end] - sum[start] < s) {
				end++;
			} else {
				result = Math.min(result, end - start);
				start++;
			}
		}
		
		if(arr[n] > s) result = 1;
		
		if(result == 100001) result = 0;
		
		System.out.println(result);
	}
}