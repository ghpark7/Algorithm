import java.io.*;

public class Main {
	
	static class Node {
		int value;
		Node left, right;
		
		Node(int value) {
			this.value = value;
		}
		
		void insert(int val) {
			if(val < this.value) {
				if(this.left == null) left = new Node(val);
				else this.left.insert(val);
			} else {
				if(this.right == null) right = new Node(val);
				else this.right.insert(val);
			}
		}
		
		void postOrder(StringBuilder sb) {
			if(this.left != null) this.left.postOrder(sb);
			if(this.right != null) this.right.postOrder(sb);
			sb.append(this.value).append("\n");
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Node root = null;
		String str;
		
		while((str = br.readLine()) != null) {
			if(root == null) root = new Node(Integer.parseInt(str));
			else root.insert(Integer.parseInt(str));
		}
		
		StringBuilder sb = new StringBuilder();
		root.postOrder(sb);
		
		System.out.println(sb);
	
	}
}