
public class DoublyCircularLinkedListMain {

	public static void main(String[] args) {

		LinkedList list = new LinkedList();
		
		list.addFirst(30);
		list.addFirst(20);
		list.addFirst(10);
		
		list.addLast(40);
		list.addLast(50);
		
		list.deleteFirst();
		list.deleteLast();
		
		list.fDisplay();
		list.rDisplay();

	}

}
