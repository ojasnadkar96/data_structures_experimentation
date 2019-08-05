//TESTCASE (Treap) 1: Checks correctness of the algorithm based on a small input example

public class testcaseTreeHeap1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeOperations treefunc = new TreeHeap();
		treefunc.insert(10);
		treefunc.insert(20);
		treefunc.insert(30);
		treefunc.insert(40);
		treefunc.insert(50);
		treefunc.insert(60);
		treefunc.delete(40); 
		treefunc.search(30); 
		treefunc.printLevelOrder(); //prints level order of the tree 
	}

}