import java.util.ArrayList;
import java.util.Random;

import Trees.BinaryNode;
import Trees.BinarySearchTree;
import Trees.BinaryTreeInterface;

public class Problem1SearchTree<T extends Comparable<? super T>> extends BinarySearchTree<T>{
	@Override
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(T entry) {
		return (getEntry(entry) != null);
	}

	@Override
	public T getEntry(T entry) {
		return findEntry(getRoot(), entry);
	}
	
	public T getEntryIterative(T entry) {
		BinaryNode<T> currNode = getRoot();
		if (currNode == null) {
			return null;
		}
		T rootEntry;
		int comparison;
		do {
			rootEntry = currNode.getData();
			comparison = entry.compareTo(rootEntry);
			if (comparison == 0) {
				return rootEntry;
			} else if (comparison < 0) {
				currNode = (BinaryNode<T>) currNode.getLeftChild();
			} else {
				currNode = (BinaryNode<T>) currNode.getRightChild();
			}
		} while (currNode != null);
		return null;
	}
	
	public T findEntry(BinaryNode<T> node, T entry) {
		if (node == null) {
			return null;
		}
		T rootEntry = node.getData();
		int comparison = entry.compareTo(rootEntry);
		if (comparison == 0) {
			return rootEntry;
		} else if (comparison < 0) {
			return findEntry((BinaryNode<T>) node.getLeftChild(), entry);
		} else {
			return findEntry((BinaryNode<T>) node.getRightChild(), entry);
		}
	}
	
	public ArrayList<T> findAllEntry(BinaryNode<T> node, T entry) {
		ArrayList<T> all = new ArrayList<T>();
		if (node == null) {
			return null;
		}
		T rootEntry = node.getData();
		int comparison = entry.compareTo(rootEntry);
		if (comparison == 0) {
			all.add(rootEntry);
			ArrayList<T> leftTreeMatches = findAllEntry((BinaryNode<T>) node.getLeftChild(), entry);
			ArrayList<T> rightTreeMatches = findAllEntry((BinaryNode<T>) node.getRightChild(), entry);
			if(!leftTreeMatches.equals(null)) {
				for(T o: leftTreeMatches) {
					all.add(o);
				}
			}
			if(!rightTreeMatches.equals(null)) {
				for(T o: rightTreeMatches) {
					all.add(o);
				}
			}
			return all;
		} else if (comparison < 0) {
			return findAllEntry((BinaryNode<T>) node.getLeftChild(), entry);
		} else {
			return findAllEntry((BinaryNode<T>) node.getRightChild(), entry);
		}
		
	}

	@Override
	public T add(T newEntry) {
		if (isEmpty()) {
			setRoot(new BinaryNode<T>(newEntry));
			return null;
		}
		else {
			return addEntry(getRoot(), newEntry);
		}
	}
	
	private T addEntry(BinaryNode<T> node, T newEntry) {
		assert(node != null);
		T rootEntry = node.getData();
		int comparison = newEntry.compareTo(rootEntry);
		
		if (comparison == 0) {
			Random rand = new Random();
			if(rand.nextInt(2) != 0) {
				comparison += 1;
			}else {
				comparison -= 1;
			}	
		}
		
		if (comparison < 0) {
			if (node.hasLeftChild()) {
				return addEntry((BinaryNode<T>) node.getLeftChild(), newEntry);
			} else {
				node.setLeftChild(new BinaryNode<T>(newEntry));
				return null;
			}
		} else {
			if (node.hasRightChild()) {
				return addEntry((BinaryNode<T>) node.getRightChild(), newEntry);
			} else {
				node.setRightChild(new BinaryNode<T>(newEntry));
				return null;
			}
		}
	}

	@Override
	public T remove(T entry) {
		if (getRoot() == null) {
			return null;
		}
		BinaryNode<T> removedNode = getRoot();
		BinaryNode<T> parentNode = null;
		T rootEntry = removedNode.getData();
		int comparison = entry.compareTo(rootEntry);
		boolean left = false;
		boolean right = false;
		
		while(comparison != 0) {
			rootEntry = removedNode.getData();
			comparison = entry.compareTo(rootEntry);
			
			if (comparison < 0) {
				parentNode = removedNode;
				left = true;
				right = false;
				removedNode = (BinaryNode<T>) parentNode.getLeftChild();
			} else if (comparison > 0){
				parentNode = removedNode;
				right = true;
				left = false;
				removedNode = (BinaryNode<T>) parentNode.getRightChild();
			}
			
			if(removedNode == null) {
				return null;
			}
		}
		
		if(!removedNode.hasLeftChild() && !removedNode.hasRightChild()) {
			if(left) {
				parentNode.setLeftChild(null);
			}else if(right) {
				parentNode.setRightChild(null);
			}
		}else if(!removedNode.hasLeftChild()) {
			if(left) {
				parentNode.setLeftChild(removedNode.getRightChild());
			}else if(right) {
				parentNode.setRightChild(removedNode.getRightChild());
			}
		}else if(!removedNode.hasRightChild()) {
			if(left) {
				parentNode.setLeftChild(removedNode.getLeftChild());
			}else if(right) {
				parentNode.setRightChild(removedNode.getLeftChild());
			}
		}else {
			BinaryNode<T> lastRightParent = removedNode;
			BinaryNode<T> lastRight = (BinaryNode<T>) lastRightParent.getLeftChild();
			while(lastRight.getRightChild() != null) {
				lastRightParent = lastRight;
				lastRight = (BinaryNode<T>) lastRightParent.getLeftChild();
			}
			
			lastRightParent.setRightChild(lastRight.getLeftChild());
			lastRight.setLeftChild(removedNode.getLeftChild());
			lastRight.setRightChild(removedNode.getRightChild());
			if(left) {
				parentNode.setLeftChild(lastRight);
			}else if (right) {
				parentNode.setRightChild(lastRight);
			}
		}
		
		return removedNode.getData();
	}
}
