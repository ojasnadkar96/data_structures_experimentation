import java.util.*;

public class SplayTree extends TreeOperations {

TreeNode root;
	
	public void create()
	{
		root = null;
	}
	
	public boolean search(int data) 
	{
		root = searchRecursive(root,data);
		return root.value == data;
	}

	public TreeNode searchRecursive(TreeNode root, int val) {
		return splay(root, val);
	}
	
	public void insert(int value) 
	{
		root = insertRecursive(root,value);
	}
	
	public TreeNode insertRecursive(TreeNode root, int data) 
	{
		if (root == null)
		{
			root = new TreeNode(data);
		}
		else if (data<root.value)
		{
			root.left = insertRecursive(root.left,data);
		}
		else if (data>root.value)
		{
			root.right = insertRecursive(root.right,data);
		}
		return splay(root,data);
	}
	
	private TreeNode rightRotate(TreeNode root) 
	{
		TreeNode newRoot = root.left;
		root.left = newRoot.right;
		newRoot.right = root;
		return newRoot;
	}

	private TreeNode leftRotate(TreeNode root) 
	{
		TreeNode newRoot = root.right;
		root.right = newRoot.left;
		newRoot.left = root;
		return newRoot;
	}
	
	public TreeNode splay(TreeNode root, int value) 
	{
		if (root==null || root.value==value)
		{
			return root;
		}
		if (root.value>value) 
		{
			if (root.left==null)
			{
				return root;
			}
			else if (root.left.value>value) 
			{
				root.left.left = splay(root.left.left, value);
				root = rightRotate(root);
			} 
			else if (root.left.value<value) 
			{
				root.left.right = splay(root.left.right, value);
				if (root.left.right!=null)
				{
					root.left = leftRotate(root.left);
				}
			}
			return (root.left==null)?root:rightRotate(root);
		} 
		else 
		{
			if (root.right==null)
			{
				return root;
			}
			else if (root.right.value>value) 
			{
				root.right.left = splay(root.right.left,value);
				if (root.right.left != null)
				{
					root.right = rightRotate(root.right);
				}
			} 
			else if (root.right.value<value) 
			{
				root.right.right = splay(root.right.right,value);
				root = leftRotate(root);
			}
			return (root.right==null)?root:leftRotate(root);
		}
	}
	
	public void delete(int data) 
	{
		root = deleteRecursive(root,data);
	}
	
	private TreeNode deleteRecursive(TreeNode root, int value) 
	{
		if (root==null)
		{
			return root;
		}
		root = splay(root,value);
		if (value==root.value) 
		{
			if (root.left==null)
			{
				return root.right;
			}
			else if (root.right==null)
			{
				return root.left;
			}
			TreeNode child = root.left;
			while(child.right!=null) 
			{
				child = child.right;
			}
			root.value = child.value;
			root.left = deleteRecursive(root.left,child.value);
		}
		return root;
	}
	
	public void printLevelOrder() {
		List<List<String>> tree = new ArrayList<>();
		List<String> temp = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		if(root == null) 
		{
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
		int value;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int value)
		{
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}
}
