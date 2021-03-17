import java.util.*;

public class IntroductionToBinaryTree {
	public static class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

	}

	public static class Pair {
		Node node;
		int state;

		Pair(Node node, int state) {
			this.node = node;
			this.state = state;
		}
	}

	public static void display(Node node) {
		if(node == null) {
			return;	
		}
		// node selfwork
		String str = "";
		str = str + node.left == null ? "." : node.left.data + "";
		str = str + " <- " + node.data + " -> ";
		str = str + node.right == null ? "." : node.right.data + "";

		System.out.println(str);

		display(node.left);
		display(node.right);
	}

//	1 is for left
//	2 is for right
//	3 is for pop
	public static void main(String args[]) throws Exception {
		Integer[] arr = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
				null };
		Node root = new Node(arr[0], null, null);
		Pair rootPair = new Pair(root, 1);
		Stack<Pair> st = new Stack<>();
		st.push(rootPair);

		int index = 0;

		while (st.size() > 0) {
			Pair top = st.peek();
			if (top.state == 1) {
				index++;
				if (arr[index] != null) {
					Node ln = new Node(arr[index], null, null);// left node
					top.node.left = ln; // top k node k left mein abhi to new node banaya h usko point kar do

					Pair lp = new Pair(ln, 1);// left pair
					st.push(lp);

				} else {
					top.node.left = null;
				}
				top.state++; // left work is over
			} else if (top.state == 2) {
				index++;
				if (arr[index] != null) {

					Node rn = new Node(arr[index], null, null);// right node
					top.node.right = rn; // top k node k left mein abhi to new node banaya h usko point kar do

					Pair rp = new Pair(rn, 1);// right pair
					st.push(rp);

				} else {
					top.node.right = null;
				}
				top.state++; // right work is over
			} else {
				st.pop();
			}
		}
		display(root);

	}
}
