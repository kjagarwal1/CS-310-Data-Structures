import java.util.Iterator;

// your header comments

class SimpleList<T> implements Iterable<T>{
	
	// a linked list class 
	// you decide the internal attributes and node structure
	// but they should all be private
	T data;
	SimpleList<T> next, last;
	int size;

	public SimpleList(){
		//constructor
		next = null;
		last = null;
		size = 0;
	}
	
	public void add(T value){
		// add a new node to the end of the linked list to hold value
		// O(1)
		SimpleList<T> temp = new SimpleList<>();
		temp.data = value;

		if(next == null && last == null)
			next = temp;
		else
			last.next = temp;

		last = temp;
		size++;
	}
	
		
	public boolean remove(T value){
		// given a value, remove the first occurrence of that value
		// return true if value removed
		// return false if value not present
		// O(N) where N is the number of nodes returned by size()

		SimpleList<T> temp = this;

		if( temp.next == null )
			return false;

		while( !temp.next.data.equals(value) ){
			temp = temp.next;
			if( temp.next == null )
				return false;
		}

		if(temp.next == last)
			last = temp;
		temp.next = temp.next.next;
		size--;
		return true;
	}
	
	public int indexOf(T value){
		// return index of a value (0 to size-1)
		// if value not present, return -1
		// O(N)
		int i = 0;
		SimpleList<T> temp = next;

		while( temp != null ){
			if(temp.data.equals(value))
				return i;
			temp = temp.next;
			i++;
		}
		return -1;
	}

	public boolean contains(T value){
		// return true if value is present
		// false otherwise
		// O(N) where N is the number of nodes returned by size()
		if(indexOf(value) >= 0)
			return true;
		return false;
	}

	public T get(T value){
		// search for the node with the specified value:
		// if not found, return null;
		// if found, RETURN VALUE STORED from linked list, NOT the incoming value
		// Note: two values might be considered "equal" but not identical
		//       example: Pair <k,v1> and <k,v2> "equal" for different v1 and v2 
		// O(N) where N is the number of nodes returned by size()

		SimpleList<T> temp = next;

		while( temp != null ){
			if(temp.data.equals(value))
				return temp.data;
			temp = temp.next;
		}

		return null;
	}
	
	
	public int size(){
		//return how many nodes are there
		//O(1)
		return size;
	}


	public Iterator<T> iterator(){
		// return a basic iterator
		// .hasNext() and .next() required 
		// both should be of O(1)
		Iterator<T> temp = new Iterator<T>() {
			SimpleList current = SimpleList.this.next;
			@Override
			public boolean hasNext() {
				if(current != null)
					return true;
				return false;
			}

			@Override
			public T next() {
				if(hasNext()) {
					T item = (T)current.data;
					current = current.next;
					return item;
				}
				return null;
			}
		};

		return temp;
	}


	
	//----------------------------------------------------
	// example testing code... make sure you pass all ...
	// and edit this as much as you want!
	// also, consider add a toString() for your own debugging

	public static void main(String[] args){
		SimpleList<Integer> ilist = new SimpleList<Integer>();
		ilist.add(new Integer(11));
		ilist.add(new Integer(20));
		ilist.add(new Integer(5));
		
		if (ilist.size()==3 && ilist.contains(new Integer(5)) && 
			!ilist.contains(new Integer(2)) && ilist.indexOf(new Integer(20)) == 1){
			System.out.println("Yay 1");
		}

		if (!ilist.remove(new Integer(16)) && ilist.remove(new Integer(11)) &&
			!ilist.contains(new Integer(11)) && ilist.get(new Integer(20)).equals(new Integer(20))){
			System.out.println("Yay 2");			
		} 
		
		Iterator iter = ilist.iterator();
		if (iter.hasNext() && iter.next().equals(new Integer(20)) && iter.hasNext() &&
			iter.next().equals(new Integer(5)) && !iter.hasNext()){
			System.out.println("Yay 3");						
		}
		
	}

}