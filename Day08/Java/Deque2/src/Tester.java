
public class Tester {

	public static void main(String[] args) {
		
		Deque dq = new Deque();
		
		System.out.println("is Empty : " + dq.isEmpty());
		
		dq.pushFront(10);
		dq.pushFront(20);
		dq.pushRear(30);
		dq.pushRear(40);
		
		// 20 - 10 - 30 - 40
		
		System.out.println("front data : " + dq.getFront());	// 20
		System.out.println("Rear data : " + dq.getRear());		// 40
		
		System.out.println("Poped from front : " + dq.popFront());// 20
		System.out.println("Poped from rear : " + dq.popRear());// 40
		
		System.out.println("front data : " + dq.getFront());	// 10
		System.out.println("Rear data : " + dq.getRear());		// 30
		
		System.out.println("is Empty : " + dq.isEmpty());
	}

}
