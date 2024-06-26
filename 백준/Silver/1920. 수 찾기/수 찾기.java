import java.util.Scanner;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
        
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		int M = sc.nextInt();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			if(binarySearch(arr, sc.nextInt()) >= 0) {
				sb.append(1).append('\n');
			}
			else {
				sb.append(0).append('\n');
			}
		}
		System.out.println(sb);
	}
	
	public static int binarySearch(int[] arr, int key) {
 
		int le = 0;					// 왼쪽 끝 인덱스
		int ri = arr.length - 1;	// 오른쪽 끝 인덱스
 
		while(le <= ri) {
 
			int mid = (le + ri) / 2;

			if(key < arr[mid]) {
				ri = mid - 1;
			}
			else if(key > arr[mid]) {
				le = mid + 1;
			}
			else {
				return mid;
			}
		}
		return -1;
	}
}