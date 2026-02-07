
public class Deque {
	LinkedList list;
	
	public Deque() {
		list = new LinkedList();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public int peekRear() {
		return list.getFirst();
	}
	
	public int peekFront() {
		return list.getLast();
	}
	
	public void pushRear(int value) {
		list.addFirst(value);
	}
	
	public void pushFront(int value) {
		list.addLast(value);
	}
	
	public int popRear() {
		int val = list.getFirst();
		list.deleteFirst();
		return val;
	}
	
	public int popFront() {
		int val = list.getLast();
		list.deleteLast();
		return val;
	}
}














