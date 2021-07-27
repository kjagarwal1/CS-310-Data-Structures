// This is the class for a generic stack (FILO)
// Your header comments here


public class Stack<AnyType>{

	// You decide internal attributtes or additional classes to implement a stack
	// All those must be private
	

	public Stack() {
		// Stack Constructor
		// use this to initialize internal attributes
		// could be empty
	}

	public boolean isEmpty() {
		// check if stack is empty
		// return true if empty, false otherwise
		// O(1)
		return false;
	}

	public AnyType peek() {
		// peek the stack top
		// return stack top but do not pop
		// return null for empty stack
		// O(1)
		return null;
	}

	public void push(AnyType value) {
		// push an element to the stack
		// O(1)


	}

	public AnyType pop(){
		// pop from the top
		// remove and return the stack top
		// return null for empty stack
		// O(1)

		return null;
	}

	//----------------------------------------------------
	// example testing code... make sure you pass all ...
	// and edit this as much as you want!

	public static void main(String[] args){
		Stack<Integer> iStack = new Stack<Integer>();
		if (iStack.isEmpty() && iStack.peek()==null){
			System.out.println("Yay 1");
		}
		
		iStack.push(new Integer(12));
		iStack.push(new Integer(20));
		iStack.push(new Integer(-233));
		
		if (iStack.pop()==-233 && iStack.peek()==20 ){
			System.out.println("Yay 2");
		}
		
		if (iStack.pop()==20 && iStack.pop()==12 && iStack.isEmpty() ){
			System.out.println("Yay 3");
		}

		
	}

}