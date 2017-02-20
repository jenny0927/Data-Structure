/**
 * This BinarySearchTree class insert,remove value to binary tree, and print out the tree in order
 */
import java.util.Iterator;
import java.util.LinkedList;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	private static class Node<AnyType> {
		private AnyType data;
		private Node<AnyType> parent;
		private Node<AnyType> left;
		private Node<AnyType> right;

		public Node(AnyType d, Node<AnyType> p, Node<AnyType> l, Node<AnyType> r) 
		{
			setData(d);
			setParent(p);
			setLeft(l);
			setRight(r);
		}

		public AnyType getData() {	return data; }

		public Node<AnyType> getParent() { return parent;	}

		public Node<AnyType> getLeft() { return left; }

		public Node<AnyType> getRight() { return right; }

		public void setData(AnyType d) { data = d; }

		public void setParent(Node<AnyType> p) {	parent = p; }

		public void setLeft(Node<AnyType> l) { left = l;	}

		public void setRight(Node<AnyType> r) {	right = r; }
	}

	private Node<AnyType> root;

	public BinarySearchTree() {	makeEmpty(); }

	public void makeEmpty() {	root = null; }

	public boolean isEmpty() {	return (root == null); }

	public boolean contains(AnyType v) {	return contains(v, root);	}

	private boolean contains(AnyType v, Node<AnyType> t) 
	{
		if (t == null)	return false;

		int compareResult = v.compareTo(t.getData());

		if (compareResult < 0)
			return contains(v, t.getLeft());
		else if (compareResult > 0)
			return contains(v, t.getRight());
		else
			return true;
	}

	public AnyType findMin() throws IllegalStateException 
	{
		if (isEmpty())
			throw new IllegalStateException("Search Tree is empty.");

		Node<AnyType> minNode = findMin(root);
		return minNode.getData();
	}

	private Node<AnyType> findMin(Node<AnyType> t) 
	{
		if (t == null)
			return null;
		else if (t.getLeft() == null)
			return t;

		return findMin(t.getLeft());
	}

	public AnyType findMax() throws IllegalStateException 
	{
		if (isEmpty())
			throw new IllegalStateException("Search Tree is empty.");

		Node<AnyType> maxNode = findMax(root);
		return maxNode.getData();
	}

	private Node<AnyType> findMax(Node<AnyType> t)
	{
		if (t == null)	return null;
		
		else if (t.getRight() == null)	return t;

		return findMax(t.getRight());
	}

	public void insert(AnyType v) 
	{
		// Write the iterative version of the insert method here.
		if (isEmpty())
			root = new Node<AnyType>(v, null, null, null);
		else
		{
			Node<AnyType> currentNode = root;
			
			while(currentNode!=null)
			{
				int compareResult = v.compareTo(currentNode.getData());
				
				if (compareResult < 0)
				{
					if(currentNode.left==null)
					{
						// create a new node on the left
						currentNode.left = new Node<AnyType>(v, currentNode, null, null);						
					}
					else
					{
						currentNode = currentNode.left; //continue going left
					}
				}
				else if (compareResult > 0)
				{
					if(currentNode.right==null)
					{
						// create a new node on the left
						currentNode.right = new Node<AnyType>(v, currentNode, null, null);						
					}
					else
					{
						currentNode = currentNode.right; //continue going right
					}
					
				}
				else
					break; // Duplicate value, exit the loop
			};
		
			
		}

	}


	public void remove(AnyType v) {
		// Write the iterative version of the remove method here.		

		Iterator<Node<AnyType>> iterator = createNodeIterator();
		
		while(iterator.hasNext())
		{
			Node<AnyType> node = iterator.next();
			
			// if v is found
			if(node!=null && node.data.compareTo(v) == 0)
			{
				if (node.getLeft() != null && node.getRight() != null) 
				{ 
					Node<AnyType> minNodeRightSubtree = findMin(node.getRight()); 
					node.setData(minNodeRightSubtree.getData()); 
					removeNode(minNodeRightSubtree, minNodeRightSubtree.parent.left == minNodeRightSubtree);
				} 
				else 
				{ 
					removeNode(node, node.parent.getLeft() == node);
				}
				return;
			}	
		}	
	}

	private void removeNode(Node<AnyType> node, boolean leftChild)
	{		
			Node<AnyType> parentOfT = node.getParent(); 
			Node<AnyType> childOfT = (node.getLeft() != null) ? node.getLeft() : node.getRight(); 
			if (parentOfT == null) 
				root = childOfT; 
			else if (leftChild) 
				parentOfT.setLeft(childOfT); 
			else parentOfT.setRight(childOfT); 
			
			if (childOfT != null) 
				childOfT.setParent(parentOfT); 
	}

	public int getMaxLevel(){	return getMaxLevel(root); }
	
	private int getMaxLevel(Node<AnyType> node)
	{
		if(node==null)	return 0;
		
		int leftMax = getMaxLevel(node.left);
		int rightMax = getMaxLevel(node.right);
		int max = (leftMax > rightMax) ? leftMax: rightMax;
		
		return max+1;
	}
	
	public void printTree()
	{
		int maxLevel = getMaxLevel(root);		
		
		if(maxLevel==0)
		{
			System.out.println("it's an empty tree. please add some nodes.");
			return;
		}
		
		System.out.println("[-------------- MaxLevel:" + maxLevel + " ------------]");
		LinkedList<Node<AnyType>> levelNodes = new LinkedList<Node<AnyType>>();
		levelNodes.add(root);
		
		printTree(levelNodes, maxLevel ,1);
		System.out.println("[-------------- End of the Tree ---------------]");
	}
	
	
	public void printTree(LinkedList<Node<AnyType>> levelNodes, int maxLevel, int curLevel) {

		if(this.AreAllCurrentLevelNodesEmpty(levelNodes))
			return;

		
		int indent = (int) (Math.pow(2, maxLevel - curLevel)); // space from the first element
		int spacing = (int) (Math.pow(2, maxLevel - curLevel+1)); // space between each element expect firsrt element
		
		Iterator<Node<AnyType>> iterator = levelNodes.iterator();

		int count = 0;
		int prevElementWidth = 0;
		
		while(iterator.hasNext())
		{
			Node<AnyType> node = iterator.next();

			
			if(count==0) // print ident before the first element
				printWhiteSpace(indent);			
			else
				printWhiteSpace(spacing-prevElementWidth);			
						
			if(node!=null && node.data != null)
			{
				prevElementWidth = node.data.toString().length();
				System.out.print(node.data.toString());
			}
			else
			{
				prevElementWidth = 1;
				System.out.print(" ");				
			}
			
			count++;
		}

		
		System.out.println(""); // go to next line
		
		
		LinkedList<Node<AnyType>> nextLevelNodes = new LinkedList<Node<AnyType>>();

		iterator = levelNodes.iterator();

		// print based on level
		while(iterator.hasNext())
		{
			Node<AnyType> node = iterator.next();
									
			if(node!=null)
			{
				nextLevelNodes.add(node.left);
				nextLevelNodes.add(node.right);
			}
			else
			{
				// create place holders for nodes not exist.
				nextLevelNodes.add(null);
				nextLevelNodes.add(null);
			}
		}

		this.printTree(nextLevelNodes, maxLevel, curLevel+1);
	}
	
	
	private boolean AreAllCurrentLevelNodesEmpty(LinkedList<Node<AnyType>> levelNodes)
	{
		Iterator<Node<AnyType>> iterator = levelNodes.iterator();

		// print based on level
		while(iterator.hasNext())
		{
			Node<AnyType> node = iterator.next();
			
			if(node!=null)	return false;
		}
		
		return true;
	}
	
	private void printWhiteSpace(int numberOfWhiteSpaceToPrint){		
		
		for(int i=0;i<numberOfWhiteSpaceToPrint;i++)
			System.out.print(" ");
		
	}

	public Iterator<AnyType> iterator() {
		LinkedList<AnyType> snapshot = new LinkedList<>();

		inOrderTraversal(root, snapshot);
		return snapshot.iterator();
	}

	private void inOrderTraversal(Node<AnyType> t, LinkedList<AnyType> l) {
		if (t != null) {
			inOrderTraversal(t.getLeft(), l);
			l.add(t.getData());
			System.out.println(t.getData());
			inOrderTraversal(t.getRight(), l);			
		}
	}

	private Iterator<Node<AnyType>> createNodeIterator() {
		LinkedList<Node<AnyType>> snapshot = new LinkedList<>();

		if(!this.isEmpty())
		{
			addNodeToList(root, snapshot);
		}
		
		return snapshot.iterator();
	}

	private void addNodeToList(Node<AnyType> node, LinkedList<Node<AnyType>> list) {
		
		if(node!=null)
		{
			list.add(node);
			addNodeToList(node.left, list);
			addNodeToList(node.right, list);			
		}

	}

}