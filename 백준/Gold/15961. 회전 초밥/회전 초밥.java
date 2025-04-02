import java.util.*;
import java.io.*;

public class Main {

	static int n, d, k, c, unique = 0, result = 0;
	static int[] sushi, sushiCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		sushi = new int[n];
		
		for(int i = 0; i < n; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		sushiCnt = new int[d+1];
		
		for(int i = 0; i < k; i++) {		// 초기 윈도우 세팅
			if(sushiCnt[sushi[i]] == 0) unique++;
			sushiCnt[sushi[i]]++;
		}
		if(sushiCnt[c] == 0) {				// 쿠폰 초밥을 먹지 않았다면 종류 1 추가
			unique++;
			result = unique;
			unique--;						// 쿠폰 초밥은 슬라이딩 윈도우의 종류 카운팅에서 제거
		}
		else result = unique;
		
		for(int i = 1; i < n; i++) {
			if(i <= n-k) {
				if(sushiCnt[sushi[i-1]] == 1) unique--;
				sushiCnt[sushi[i-1]]--;
				if(sushiCnt[sushi[i+k-1]] == 0) unique++;
				sushiCnt[sushi[i+k-1]]++;
				if(sushiCnt[c] == 0) {
					unique++;
					result = Math.max(result, unique);
					unique--;
				}
				else result = Math.max(result, unique);
			}
			else {
				if(sushiCnt[sushi[i-1]] == 1) unique--;
				sushiCnt[sushi[i-1]]--;
				if(sushiCnt[sushi[k-n+i-1]] == 0) unique++;
				sushiCnt[sushi[k-n+i-1]]++;
				if(sushiCnt[c] == 0) {
					unique++;
					result = Math.max(result, unique);
					unique--;
				}
				else result = Math.max(result, unique);
			}
		}
		System.out.println(result);
	}
}