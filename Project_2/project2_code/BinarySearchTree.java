import java.util.*;

public class BinarySearchTree extends TreeOperations {
	
	TreeNode root;
	
	public void create()
	{
		root = null;
	}
	
	public boolean search(int value)
	{
		return (searchRecursive(root,value)!=null)?true:false;
	}
	
	public TreeNode searchRecursive(TreeNode rootNode, int data)
	{
		if(rootNode==null || rootNode.value==data)
		{
			return rootNode;
		}
		if(rootNode.value>data)
		{
			return searchRecursive(rootNode.left,data);
		}
		else
		{
		return searchRecursive(rootNode.right,data);
		}
	}
	
	public void insert(int value)
	{
		root = insertRecursive(root,value);
	}
	
	TreeNode insertRecursive(TreeNode rootNode, int data)
	{
		if(rootNode==null)
		{
			rootNode = new TreeNode(data);
			return rootNode;
		}
		if(data<rootNode.value)
		{
			rootNode.left = insertRecursive(rootNode.left,data);
		}
		else
		{
			rootNode.right = insertRecursive(rootNode.right,data);
		}
		return rootNode;
	}
	
	public int findPreInorder(TreeNode rootNode)
	{
		int min = rootNode.value;
		while(rootNode.left!=null)
		{
			min = rootNode.left.value;
			rootNode = rootNode.left;
		}
		return min;
	}
	
	public void delete(int value)
	{
		root = deleteRecursive(root,value);
	}
	
	public TreeNode deleteRecursive(TreeNode rootNode, int data)
	{
		if(rootNode==null)
		{
			return rootNode;
		}
		if(data<rootNode.value)
		{
			rootNode.left = deleteRecursive(rootNode.left,data);
		}
		else if(data>rootNode.value)
		{
			rootNode.right = deleteRecursive(rootNode.right,data);
		}
		else
		{
			if(rootNode.left==null)
			{
				return rootNode.right;
			}
			else if(rootNode.right==null)
			{
				return rootNode.left;
			}
			else
			{
			rootNode.value = findPreInorder(rootNode.right);
			rootNode.right = deleteRecursive(rootNode.right,rootNode.value);
			}
		}
		return rootNode;
	}
	
	public void printLevelOrder()
	{
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty())
		{
			TreeNode temp = q.poll();
			System.out.print(temp.value + " ");
			if(temp.left!=null)
			{
				q.add(temp.left);
			}
			if(temp.right!=null)
			{
				q.add(temp.right);
			}
		}
	}
	
	static class TreeNode {
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

