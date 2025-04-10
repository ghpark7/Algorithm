import java.util.*;
import java.io.*;

public class Main {

	static int[][] sudoku;
	static List<int[]> empty;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sudoku = new int[9][9];
		empty = new ArrayList<>();
		
		for(int i = 0; i < 9; i++) {
			String line = br.readLine();
			for(int j = 0; j < 9; j++) {
				sudoku[i][j] = line.charAt(j)-'0';
				if(sudoku[i][j] == 0) {
					empty.add(new int[] {i, j});
				}
			}
		}
		
		solve(0);
	}

	static void solve(int index) {
		if(index == empty.size()) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					System.out.print(sudoku[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}
		
		int row = empty.get(index)[0];
		int col = empty.get(index)[1];
		
		for(int i = 1; i <= 9; i++) {
			if(isValid(row, col, i)) {
				sudoku[row][col] = i;
				solve(index + 1);
				sudoku[row][col] = 0;
			}
		}
	}
	
	static boolean isValid(int row, int col, int target) {
		for(int i = 0; i < 9; i++) {
			if(sudoku[row][i] == target || sudoku[i][col] == target) {
				return false;
			}
		}
		
		int startRow = (row / 3) * 3;
		int startCol = (col / 3) * 3;
		for(int i = startRow; i <startRow+3; i++) {
			for(int j = startCol; j <startCol+3; j++)
				if(sudoku[i][j] == target)
					return false;
		}
		
		return true;
	}
}