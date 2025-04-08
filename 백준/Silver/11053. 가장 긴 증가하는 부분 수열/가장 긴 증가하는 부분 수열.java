import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] arr;
	static int[] C; // 각 길이를 LIS로 만족하는 가장 끝에 오는 최소값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		C = new int[N]; // 자신을 끝으로 하는 증가부분수열의 최장길이
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int max = 0; // 최장증가부분수열의 길이
		for(int i = 0; i < N; i++) {
            int pos = Arrays.binarySearch(C, 0, max, arr[i]);
            int insertPos = (pos >= 0) ? pos : -(pos + 1);
            C[insertPos] = arr[i];

            if (insertPos == max) max++;
        }
		System.out.println(max);
	}
}