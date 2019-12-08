package Trees;

import Queue.*;

import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Iterator;

public class BinaryTree<T> implements BinaryTreeInterface<T>{
	
	private BinaryNode<T> root;
	
	/**
	 * Constructor without args
	 */
	public BinaryTree() {
		root = null;
	}
	
	/**
	 * Constructor with data to be set as root data
	 * @param rootData
	 */
	public BinaryTree(T rootData) {
		root = new BinaryNode<>(rootData);
	}

	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		initializeTree(rootData, leftTree, rightTree);
	}
	
	/**
	 * Method to set root node data out of constructor
	 */
	public void setTree(T rootData) {
		root = new BinaryNode<>(rootData);
	}
	
	/**
	 * Method to set root node data and left/right child out of constructor
	 * @param rootData
	 * @param leftTree
	 * @param rightTree
	 */
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		initializeTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
	}
	
	/**
	 * Setting the root as the input node
	 * @param newRoot
	 */
	public void setRoot(BinaryNode<T> newRoot) {
		root = newRoot;
	}
	
	/**
	 * Initializes tree given rootData, left and right tree binaryTree
	 * @param rootData
	 * @param leftTree
	 * @param rightTree
	 */
	private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		root = new BinaryNode<>(rootData);
		if (leftTree != null && !leftTree.isEmpty()) {
			root.setLeftChild(leftTree.getRoot());
		}
		if (rightTree != null && !rightTree.isEmpty()) {
			if (rightTree == leftTree) {
				root.setRightChild(rightTree.getRoot().copy());
			} else {
				root.setRightChild(rightTree.getRoot());
			}
		}
		if (leftTree != null && leftTree != this) {
			leftTree.clear();
		}
		if (rightTree != null && rightTree != this) {
			rightTree.clear();
		}
	}
	
	public BinaryNode<T> getRoot(){
		return root;
	}
	
	/**
	 * Returns root data
	 */
	@Override
	public T getRootData() {
		return root.getData();
	}

	/**
	 * Gets height of the BinaryNode root
	 */
	@Override
	public int getHeight() {
		if (isEmpty()) {
			return 0;
		}
		return root.getHeight();
	}
	
	/**
	 * Returns number of nodes in root
	 */
	@Override
	public int getNumberOfNodes() {
		if (isEmpty()) {
			return 0;
		}
		return root.getNumberOfNodes();
	}

	/**
	 * Returns if root is null
	 */
	@Override
	public boolean isEmpty() {
		return (root == null);
	}

	/**
	 * Sets root to null
	 */
	@Override
	public void clear() {
		root = null;
	}

	@Override
	public Iterator<T> getInorderIterator() {
		return new InorderIterator();
	}

	@Override
	public Iterator<T> getPostorderIterator() {
		return new PostorderIterator();
	}

	@Override
	public Iterator<T> getPreorderIterator() {
		return new PreorderIterator();
	}

	@Override
	public Iterator<T> getLevelorderIterator() {
		return new LevelorderIterator();
	}
	
	public class PostorderIterator implements Iterator<T>{
		private Stack<BinaryNode<T>> nodeStack;
		
		public PostorderIterator() {
			nodeStack = new Stack<>();
			addToStack(root);
		}
		
		private void addToStack(BinaryNode<T> node) {
			nodeStack.push(node);
			if (node.hasRightChild()) {
				addToStack((BinaryNode<T>) node.getRightChild());
			}
			if (node.hasLeftChild()) {
				addToStack((BinaryNode<T>) node.getLeftChild());
			}
		}

		@Override
		public boolean hasNext() {
			return (!nodeStack.isEmpty());
		}

		@Override
		public T next() {
			return nodeStack.pop().getData();
		}
		
		
	}
	
	public class PreorderIterator implements Iterator<T>{
		private Stack<BinaryNode<T>> nodeStack;
		
		public PreorderIterator() {
			nodeStack = new Stack<>();
			nodeStack.push(root);
		}
		
		/**
		 * Iterates to next item in nodeStack
		 * POPS stack
		 * PUSHES rightchild of popped item, then leftchild (if exists for both)
		 */
		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			BinaryNode<T> currNode = nodeStack.pop();
			T item = currNode.getData();
			if (currNode.hasRightChild()) {
				nodeStack.push((BinaryNode<T>) currNode.getRightChild());
			}
			if (currNode.hasLeftChild()) {
				nodeStack.push((BinaryNode<T>) currNode.getLeftChild());
			}
			return item;
		}
		
		/**
		 * Returns if nodeStack empty or not
		 */
		@Override
		public boolean hasNext() {
			return (!nodeStack.isEmpty());
		}	
		
	}
	
	public class InorderIterator implements Iterator<T>{
		private Stack<BinaryNode<T>> nodeStack;
		
		public InorderIterator() {
			// Code for non-implementation of addToStack
			nodeStack = new Stack<>();
			BinaryNode<T> currNode = root;
			while (currNode != null) {
				nodeStack.push(currNode);
				currNode = (BinaryNode<T>) currNode.getLeftChild();
			}
			
			
			//addToStack(root);
		}
		
		/**
		 * Use if you want to simplify next; simply addToStack(node) in constructor, and T next() would just be pop
		 * @param node
		 */
		private void addToStack(BinaryNode<T> node) {
			if (node.hasRightChild()) {
				addToStack((BinaryNode<T>) node.getRightChild());
			}
			nodeStack.push(node);
			if (node.hasLeftChild()) {
				addToStack((BinaryNode<T>) node.getLeftChild());
			}
		}

		/**
		 * Returns if nodeStack is empty
		 */
		@Override
		public boolean hasNext() {
			return (!nodeStack.isEmpty());
		}

		/**
		 * Pops nodeStack, sets right child of popped node as current, then iterates through leftchild of currNode and pushes to nodeStack
		 * Returns item of initially popped node
		 */
		@Override
		public T next() {
			// Code for non-implementation of addToStack
			
			BinaryNode<T> currNode = nodeStack.pop();
			T item = currNode.getData();
			currNode = (BinaryNode<T>) currNode.getRightChild();
			while (currNode != null) {
				nodeStack.push(currNode);
				currNode = (BinaryNode<T>) currNode.getLeftChild();
			}
			return item;
			
			//return (nodeStack.pop().getData());
		}
	}

	public class LevelorderIterator implements Iterator<T>{
		
		private LinkedQueue<BinaryNode<T>> nodeQueue;
		
		public LevelorderIterator() {
			nodeQueue = new LinkedQueue<>();
			nodeQueue.enqueue(root);
		}

		@Override
		public boolean hasNext() {
			return (!nodeQueue.isEmpty());
		}

		@Override
		public T next() {
			BinaryNode<T> currNode = nodeQueue.dequeue();
			T item = currNode.getData();
			if (currNode.hasLeftChild()) {
				nodeQueue.enqueue((BinaryNode<T>) currNode.getLeftChild());
			}
			if (currNode.hasRightChild()) {
				nodeQueue.enqueue((BinaryNode<T>) currNode.getRightChild());
			}
			return item;
		}
		
	}
}
