package Trees;


public class BinaryNode<T> implements BinaryNodeInterface<T>{
	
	private T data;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;
	
	public BinaryNode(T rootData, BinaryNode<T> leftChild, BinaryNode<T> rightChild) {
		data = rootData;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public BinaryNode(T rootData) {
		this(rootData, null, null);
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public T getData() {
		return data;
	}
	
	@Override
	public BinaryNodeInterface<T> getLeftChild(){
		return leftChild;
	}
	
	@Override
	public boolean hasLeftChild() {
		return (leftChild != null);
	}
	
	@Override
	public void setLeftChild(BinaryNodeInterface<T> leftChild) {
		this.leftChild = (BinaryNode<T>) leftChild;
	}
	
	@Override
	public BinaryNodeInterface<T> getRightChild(){
		return rightChild;
	}
	
	@Override
 	public boolean hasRightChild() {
		return (rightChild != null);
	}

	@Override
	public void setRightChild(BinaryNodeInterface<T> rightChild) {
		this.rightChild = (BinaryNode<T>) rightChild;
		
	}
	@Override
	public BinaryNodeInterface<T> copy() {
		BinaryNode<T> copied = new BinaryNode<>(data);
		if (hasLeftChild()) {
			copied.setLeftChild(leftChild.copy());
		}
		if (hasRightChild()) {
			copied.setRightChild(rightChild.copy());
		}
		return copied;
	}
	
	@Override
	public int getHeight() {
		int leftHeight = 0;
		int rightHeight = 0;
		if (leftChild != null) {
			leftHeight = leftChild.getHeight();
		}
		if (rightChild != null) {
			rightHeight = rightChild.getHeight();
		}
		return (1 + Math.max(leftHeight, rightHeight));
	}

	@Override
	public int getNumberOfNodes() {
		int numNodes = 1;
		if (hasRightChild()) {
			numNodes += rightChild.getNumberOfNodes();
		}
		if (hasLeftChild()) {
			numNodes += leftChild.getNumberOfNodes();
		}
		return numNodes;
	}

}
