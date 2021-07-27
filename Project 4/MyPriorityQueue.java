// your implementation of PriorityQueue interface
// header comments here


// One additional requirement is the value type T must be Comparable.
// When two items are compared to determine the order in priority queue, follow the rules below:
//  1. use the priority of them to determine the order 
//  2. if they are of the same priority, use their values (of type T) to determine the order
//  3. if they are of the same priority and same value, any order is fine

public class MyPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T>
{
	
	// you decide the internal design of your class:
	//  - it must implement the provided PriorityQueue interface
	//  - it cannot have any other public members (attributes or method) other than
	//    the public constructor and the methods defined in PriorityQueue interface


	static final int INF = Integer.MAX_VALUE; // the max priority to use: infinity
	private int size;
	T first;
    
	
	/**
     * Construct an empty PriorityQueue.
     */
    public MyPriorityQueue()
    {
		first = null;
		size = 0;
    }
    
	// make sure you implement all methods in PriorityQueue interface

	//------------------------------------
	// example test code... edit this as much as you want!
	// note: you might want to add a method like printPQ() for debugging purpose


	@Override
	public int size() {
		return size;
	}

	@Override
	public T peek() {
		return first;
	}

	@Override
	public T remove() {
		return first;
	}

	@Override
	public void insert(T v, int p) {

	}

	@Override
	public void updatePriority(T v, int p) {

	}

	@Override
	public boolean contains(T v, int p) {
		return false;
	}
	
	public static void main(String[] args){
		MyPriorityQueue<String> pq = new MyPriorityQueue<String>();
		
		if(pq.size()==0 && pq.remove()==null && !pq.contains("a", 4)) 	{
			System.out.println("Yay 1");
		}
		
		pq.insert("a",4);
		pq.insert("b",10);
		pq.insert("h",2);
		
		if(pq.size()==3 && (pq.peek()).equals("b") && pq.contains("a", 4) && pq.contains("h", 2)
			&& pq.contains("b",10)) {
			System.out.println("Yay 2");
		}

		if((pq.remove()).equals("b") && !pq.contains("b",10) & pq.size()==2 
			&& (pq.peek().equals("a")) ) {
			System.out.println("Yay 3");
		}

		pq.insert("d",4);
		if ((pq.peek()).equals("d")) {System.out.println("Yay 4");}
				
		pq.insert("b",10);
		pq.insert("f",3);
		pq.updatePriority("a",3);
		if (pq.size() == 5 && pq.contains("a",3) && pq.contains("b",10) && pq.contains("d",4) 
			&& pq.contains("f",2) && pq.contains("h",1)) {
			System.out.println("Yay 5");
		}


	}

}




