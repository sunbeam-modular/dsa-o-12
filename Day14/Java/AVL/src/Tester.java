
public class Tester {

	public static void main(String[] args) {
		
		AVL avl = new AVL();
		
		avl.addNode(1);
		avl.addNode(2);
		avl.addNode(3);
		avl.addNode(4);
		avl.addNode(5);
		avl.addNode(6);
		avl.addNode(7);
		avl.addNode(8);
		avl.addNode(9);
		
		avl.deleteNode(1);
		avl.deleteNode(3);
		
		avl.inOrder();

	}

}
