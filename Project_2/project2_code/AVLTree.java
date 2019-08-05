import java.util.*;

public class AVLTree extends TreeOperations {
	
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
	
	public int getHeight(TreeNode rootNode)
	{
		if(rootNode==null)
		{
			return 0;
		}
		else
		{
			return rootNode.height;
		}
	}
	
	TreeNode rightRotate(TreeNode rootNode)
	{
		TreeNode a = rootNode.left;
		TreeNode b = a.right;
		a.right = rootNode;
		rootNode.left = b;
		rootNode.height = Math.max(getHeight(rootNode.left),getHeight(rootNode.right))+1;
		a.height = Math.max(getHeight(a.left),getHeight(a.right))+1;
		return a;
	}
	
	TreeNode leftRotate(TreeNode rootNode)
	{
		TreeNode a = rootNode.right;
		TreeNode b = a.left;
		a.left = rootNode;
		rootNode.right = b;
		rootNode.height = Math.max(getHeight(rootNode.left),getHeight(rootNode.right))+1;
		a.height = Math.max(getHeight(a.left),getHeight(a.right))+1;
		return a;
	}
	
	int getBalanceFactor(TreeNode rootNode)
	{
		if(rootNode==null)
		{
			return 0;
		}
		else
		{
			return getHeight(rootNode.left)-getHeight(rootNode.right);
		}
	}
	
	public void insert(int value)
	{
		root = insertRotation(root,value);
	}
	
	TreeNode insertRotation(TreeNode rootNode, int data)
	{
		if(rootNode==null)
		{
			return (new TreeNode(data));
		}
		if(data<rootNode.value)
		{
			rootNode.left = insertRotation(rootNode.left,data);
		}
		else if(data>rootNode.value)
		{
			rootNode.right = insertRotation(rootNode.right,data);
		}
		else
		{
			return rootNode;
		}
		rootNode.height = 1 + Math.max(getHeight(rootNode.left),getHeight(rootNode.right));
		int balance = getBalanceFactor(rootNode);
		if(balance>1 && data<rootNode.left.value)
		{
			return rightRotate(rootNode);
		}
		if(balance<-1 && data>rootNode.right.value)
		{
			return leftRotate(rootNode);
		}
		if(balance>1 && data>rootNode.left.value)
		{
			rootNode.left = leftRotate(rootNode.left);
			return rightRotate(rootNode);
		}
		if(balance<-1 && data<rootNode.right.value)
		{
			rootNode.right = rightRotate(rootNode.right);
			return leftRotate(rootNode);
		}
		return rootNode;
	}
	
	public TreeNode findMinInorder(TreeNode rootNode)
	{
		TreeNode currentValue = rootNode;
		while(currentValue.left!=null)
		{
			currentValue = currentValue.left;
		}
		return currentValue;
	}
	
	public void delete(int value)
	{
		root = deleteRotation(root,value);
	}
	
	public TreeNode deleteRotation(TreeNode rootNode, int data)
	{
		if(rootNode==null)
		{
			return rootNode;
		}
		if(data<rootNode.value)
		{
			rootNode.left = deleteRotation(rootNode.left,data);
		}
		else if(data>rootNode.value)
		{
			rootNode.right = deleteRotation(rootNode.right,data);
		}
		else
		{
			if((rootNode.left==null) || (rootNode.right==null))
			{
				TreeNode temp = null;
				if(temp==rootNode.left)
				{
					temp = rootNode.right;
				}
				else
				{
					temp = rootNode.left;
				}
				if(temp==null)
				{
					temp = rootNode;
					rootNode = null;
				}
				else
				{
					rootNode = temp;
				}
			}
			else
			{	
				TreeNode temp = findMinInorder(rootNode.right);
				rootNode.value = temp.value;
				rootNode.right = deleteRotation(rootNode.right,temp.value);
			}
		}
		if(rootNode==null)
		{
			return rootNode;
		}
		rootNode.height = Math.max(getHeight(rootNode.left),getHeight(rootNode.right))+1;
		int balance = getBalanceFactor(rootNode);
		if(balance>1 && getBalanceFactor(rootNode.left)>=0)
		{
			return rightRotate(rootNode);
		}
		if(balance>1 && getBalanceFactor(rootNode.left)<0)
		{
			rootNode.left = leftRotate(rootNode.left);
			return rightRotate(root);
		}
		if(balance<-1 && getBalanceFactor(rootNode.right)<=0)
		{
			return leftRotate(rootNode);
		}
		if(balance<-1 && getBalanceFactor(rootNode.right)>0)
		{
			rootNode.right = rightRotate(rootNode.right);
			return leftRotate(rootNode);
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
		int height;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int value)
		{
			this.value = value;
			this.left = null;
			this.right = null;
			this.height = 1;
		}
	}
}

