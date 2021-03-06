import java.io.*;
import java.util.*;

public class largestBSTSubTree {
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

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair leftPair = new Pair(top.node.left, 1);
          st.push(leftPair);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rightPair = new Pair(top.node.right, 1);
          st.push(rightPair);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

  public static int height(Node node) {
    if (node == null) {
      return -1;
    }

    int lh = height(node.left);
    int rh = height(node.right);

    int th = Math.max(lh, rh) + 1;
    return th;
  }

 public static class BSTPair{
	 boolean isBST;
	 int min;
	 int max;
	 Node largestBST_Root; //largest BST ki root
	 int largestBST_Size; //largest BST ki size
 }
 
 public static BSTPair isBSTYN(Node node){
    if(node == null) {
    	BSTPair bp = new BSTPair();
    	bp.min = Integer.MAX_VALUE;
    	bp.max = Integer.MIN_VALUE;
    	bp.isBST = true;
    	bp.largestBST_Root = null;
    	bp.largestBST_Size = 0;
    	return bp;
    }
    
    BSTPair leftPair = isBSTYN(node.left); //is BST yes or No
    BSTPair rightPair = isBSTYN(node.right);
    
    BSTPair myPair = new BSTPair();
    myPair.isBST = leftPair.isBST && rightPair.isBST && (node.data >= leftPair.max && node.data <=rightPair.min);
    myPair.min = (Math.min(node.data, Math.min(leftPair.min, rightPair.min)));
    myPair.max = (Math.max(node.data, Math.max(leftPair.max, rightPair.max)));
    
    if(myPair.isBST){
        myPair.largestBST_Root = node;
        myPair.largestBST_Size = leftPair.largestBST_Size + rightPair.largestBST_Size + 1;
        
    }else if(leftPair.largestBST_Size > rightPair.largestBST_Size){
        myPair.largestBST_Root = leftPair.largestBST_Root;
        myPair.largestBST_Size = leftPair.largestBST_Size;
    }
    else if(rightPair.largestBST_Size > leftPair.largestBST_Size){
        myPair.largestBST_Root = rightPair.largestBST_Root;
        myPair.largestBST_Size = rightPair.largestBST_Size;
    }
    
    return myPair;
  
 }
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    Node root = construct(arr);
    
    BSTPair bp = isBSTYN(root);
    System.out.println(bp.largestBST_Root.data+"@"+bp.largestBST_Size);
  }

}