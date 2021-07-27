// This is the class for a generic queue (FIFO)
// Your header comments here

public class Queue<AnyType>{

	// You decide internal attributtes or additional classes to implement a queue
	// All those must be private
	
	public Queue() {
		// Queue Constructor: should not need any argument
		// initialize your internal attributes, could be empty
	
	}

	public boolean isEmpty() {
		// check if queue is empty 
		// return true if empty, false otherwise
		// O(1)
		return false;
	}

	public AnyType getFront() {
		// peek the front element
		// return the front element if there is any but do not dequeue
		// return null if queue is empty
		// O(1)
		return null;
	}

	public void enqueue(AnyType value) {
   		// add an element to the back
		// O(1)
		
	}

	public AnyType dequeue(){
   		// remove and return an element from the front
   		// return null if queue empty
		// O(1)
		return null;
	}

	//----------------------------------------------------
	// example testing code... make sure you pass all ...
	// and edit this as much as you want!

	public static void main(String[] args){
		Queue<Integer> iq = new Queue<Integer>();
		if (iq.isEmpty() && iq.getFront()==null){
			System.out.println("Yay 1");
		}
		
		iq.enqueue(new Integer(12));
		iq.enqueue(new Integer(20));
		iq.enqueue(new Integer(-233));
		
		if (iq.dequeue()==12 && iq.getFront()==20 ){
			System.out.println("Yay 2");
		}
		
		if (iq.dequeue()==20 && iq.dequeue()==-233 && iq.isEmpty() ){
			System.out.println("Yay 3");
		}
		
	}

  }