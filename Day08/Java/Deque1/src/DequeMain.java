
public class DequeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Deque dq = new Deque();
		
		dq.pushFront(10);
		dq.pushFront(20);
		
		dq.pushRear(30);
		dq.pushRear(40);
		
		System.out.println("Peek from front : " + dq.peekFront());
		System.out.println("Peek from rear : " + dq.peekRear());
		
		System.out.println("Pop from front : " + dq.popFront());
		System.out.println("Pop from rear : " + dq.popRear());
		
		System.out.println("Peek from front : " + dq.peekFront());
		System.out.println("Peek from rear : " + dq.peekRear());
	}

}
