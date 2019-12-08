import java.util.Iterator;
import java.util.Random;

public class Problem1TreeCreator {
	static Problem1SearchTree<Integer> tree;
	
	public static void main(String[] args) {
		for(int h = 4; h <= 10; h++) {
			int n = (int) (Math.pow(2, h) - 1);
			System.out.printf("Height: %d%n", treeTester(n));
			System.out.printf("Recommended Height: %d%n", h);
		}
	}
	
	public static int treeTester(int n) {
		tree = new Problem1SearchTree<Integer>();
		//Random rand = new Random();
		for(int i = 0; i <= n ; i++) {
			tree.add(i);
		}
		int height = tree.getHeight();
		Iterator<Integer> treeIterator = tree.getInorderIterator();
		while(treeIterator.hasNext()) {
			System.out.printf("%d%n", treeIterator.next());
		}
		return height;
	}
	
}
