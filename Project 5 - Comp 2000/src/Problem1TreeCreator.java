import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Problem1TreeCreator {
	static Problem1SearchTree<Integer> intTree;
	static Problem1SearchTree<Name> nameTree;
	
	public static void main(String[] args) {
		//Part 2
		for(int h = 4; h <= 10; h++) {
			int n = (int) (Math.pow(2, h) - 1);
			System.out.printf("Height: %d%n", treeTester(n));
			System.out.printf("Recommended Height: %d%n", h);
		}
		
		//Part 3
		try {
			File names = new File("src\\names.txt");
			Scanner in = new Scanner(names);
			
			nameTree = new Problem1SearchTree<Name>();
			while(in.hasNextLine()) {
				String next = in.nextLine();
				if(!next.equals(""))
					nameTree.add(new Name(next));
			}
			int amnt = 0;
			Iterator<Name> treeIterator = nameTree.getInorderIterator();
			while(treeIterator.hasNext()) {
				System.out.printf("%s%n", treeIterator.next());
				amnt++;
			}
			System.out.printf("Number of names printed: %d%n", amnt);
			
			ArrayList<Name> allJohn = nameTree.findAllEntry(nameTree.getRoot(), new Name("John"));
			System.out.printf("Printing all Johns:%n");
			for(Name n: allJohn) {
				System.out.printf("%s%n", n);
			}
			
		} catch (FileNotFoundException e) {
			System.out.printf("Failed to find file.%n");
		}
		
	}
	
	public static int treeTester(int n) {
		intTree = new Problem1SearchTree<Integer>();
		Random rand = new Random();
		for(int i = 0; i < n ; i++) {
			intTree.add(rand.nextInt(51));
		}
		int height = intTree.getHeight();
		Iterator<Integer> treeIterator = intTree.getInorderIterator();
		while(treeIterator.hasNext()) {
			System.out.printf("%d%n", treeIterator.next());
		}
		return height;
	}
	
}
