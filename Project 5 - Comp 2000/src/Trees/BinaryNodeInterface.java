package Trees;


public interface BinaryNodeInterface<T> {
	
	public T getData();
	
	public BinaryNodeInterface<T> getLeftChild();
	
	public BinaryNodeInterface<T> getRightChild();
	
	public boolean hasLeftChild();
	
	public boolean hasRightChild();
	
	public void setLeftChild(BinaryNodeInterface<T> leftChild);
	
	public void setRightChild(BinaryNodeInterface<T> rightChild);
	
	public int getHeight();
	
	public int getNumberOfNodes();
	
	public BinaryNodeInterface<T> copy();

}
