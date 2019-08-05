import java.util.*;

public class TreeHeap extends TreeOperations {
	
TreeNode root;
	
	public void create()
	{
		root = null;
	}
	
	public boolean search(int data) {
		return searchRecursive(root,data);
	}
	
	private boolean searchRecursive(TreeNode node, int value) {
		if (node==null) 
		{
			return false;
		}
		else if (node.value==value)
		{
			return true;
		}
		else if (value<node.value)
		{
			return searchRecursive(node.left,value);
		}
		else
		{
			return searchRecursive(node.right,value);
		}
	}
	
	public void insert(int data) {
		root = insertRecursive(root,data);
	}

	private TreeNode insertRecursive(TreeNode node, int value) {
		if (node==null)
		{
			return new TreeNode(value);
		}
		if (node.value>value) 
		{
			node.left = insertRecursive(node.left,value);
			if (node.priority<node.left.priority) 
			{
				node = rightRotate(node);
			}
		} 
		else 
		{
			node.right = insertRecursive(node.right,value);
			if (node.priority<node.right.priority) 
			{
				node = leftRotate(node);
			}
		}
		return node;
	}

	public TreeNode rightRotate(TreeNode root) {
		TreeNode newRoot = root.left;
		root.left = newRoot.right;
		newRoot.right = root;
		return newRoot;
	}

	public TreeNode leftRotate(TreeNode root) {
		TreeNode newRoot = root.right;
		root.right = newRoot.left;
		newRoot.left = root;
		return newRoot;
	}
	
	public void delete(int data) {
		root = deleteRecursive(root,data);
	}
	
	private TreeNode deleteRecursive(TreeNode root, int value) {
		if (root == null)
		{
			return root;
		}
		if (value<root.value)
		{
			root.left = deleteRecursive(root.left,value);
		}
		else if (value>root.value)
		{
			root.right = deleteRecursive(root.right,value);
		}
		else 
		{
			if (root.right!=null || root.left!=null) 
			{
				if (root.left==null) 
				{
					root = leftRotate(root);
					root.left = deleteRecursive(root.left,value);
				}
				else if (root.right == null || (root.left.priority > root.right.priority)) 
				{
					root = rightRotate(root);
					root.right = deleteRecursive(root.right,value);
				} 
				else 
				{
					root = leftRotate(root);
					root.left = deleteRecursive(root.left,value);
				}
			} 
			else 
			{
				return null;
			}
		}
		return root;
	}
	
	public void printLevelOrder() {
		List<List<String>> tree = new ArrayList<>();
		List<String> temp = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		if(root ==null) {
			System.out.println("Tree is Empty");
			return;
		}
		q.add(root);
		temp.add(String.valueOf(root.value));
		tree.add(new ArrayList<>(temp));
		while(true) 
		{
			temp.clear();
			int size = q.size();
			for(int i = 0; i < size; i++) 
			{
				TreeNode node = q.remove();
				if(node.left != null) 
				{
					q.add(node.left);
					temp.add(String.valueOf(node.left.value));
				}
				else 
				{
					temp.add("null");
				}
				if(node.right != null) 
				{
					q.add(node.right);
					temp.add(String.valueOf(node.right.value));
				}
				else 
				{
					temp.add("null");
				}
			}
			if(!q.isEmpty())
			{
				tree.add(new ArrayList<>(temp));
			}
			else
			{
				break;
			}
		}
		System.out.println(tree);
	}
	
	static class TreeNode 
	{
		int priority;
		int value;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int value)
		{
			this.priority = new Random().nextInt(100 - 1) + 1;
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}
}
