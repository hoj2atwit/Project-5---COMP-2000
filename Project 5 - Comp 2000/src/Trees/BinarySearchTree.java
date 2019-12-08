package Trees;

/**
 * Search Tree that when printed InOrder, would list out a sorted list of the items in tree
 * @author leey2
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> implements SearchTreeInterface<T>{
	
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
	
	private T findEntry(BinaryNode<T> node, T entry) {
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
			node.setData(newEntry);
			return rootEntry;
		} else if (comparison < 0) {
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
		// TODO Auto-generated method stub
		return null;
	}

}
