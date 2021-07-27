// TO DO: add your implementation and JavaDocs

public class DynamicArray<T>{
	
	private static final int INITCAP = 2;	// default initial capacity / minimum capacity
	private T[] storage;	// underlying array
	private int nextSpot;
	
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

		
	@SuppressWarnings("unchecked")
	public DynamicArray(){
		// constructor
		// initial capacity of the array should be INITCAP
		storage = (T[])(new Object[INITCAP]);
		nextSpot = 0;
	}

	@SuppressWarnings("unchecked")
	public DynamicArray(int initCapacity){
		// constructor
		// set the initial capacity of the array as initCapacity

		// throw IllegalArgumentException if initCapacity < 1

		storage = (T[])(new Object[initCapacity]);
	}


	public int size() {	  
		//report the number of elements in the list
		// O(1)
		return nextSpot;
	}
		
	public int capacity() { 
		//report the max number of elements before the next expansion
		// O(1)
		return storage.length;
	}
		
	public T set(int index, T value){
		// change item x at index i to be value	
		// return old item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		try {
			T item = storage[index];
			storage[index] = value;
			return item;
		}catch(IndexOutOfBoundsException e){
			System.out.println("Index does not exist currently");
		}
		// Note: you cannot add new items with this method
		return null;
	}

	public T get(int index){
		// return the item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		try {
			return storage[index];
		}catch(IndexOutOfBoundsException e){
			System.out.println("Index does not exist currently");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public boolean add(T value){
		// add value to the end of the list (append)
		// return true	 
		
		// double the capacity if no space is available
		// amortized O(1)
		
		// Note: Remember... code reuse is awesome...
		if(this.nextSpot > this.capacity()){
			resizeArray(this.capacity()*2);
		}
		storage[this.nextSpot] = value;
		nextSpot++;
		return true;
	}

	private void resizeArray(int newCap){
		T[] temp = (T[])(new Object[newCap]);
		for(int i = 0; i < storage.length && i < temp.length; i++){
			temp[i] = storage[i];
		}
		storage = temp;
	}
	
	@SuppressWarnings("unchecked")
	public void add(int index, T value){
		// insert value at index, shift elements if needed  
		// double the capacity if no space is available
		// throw IndexOutOfBoundsException for invalid index
		// O(N) where N is the number of elements in the list
		
		// Note: this method may be used to append items as
		// well as insert items
		if(this.nextSpot > this.capacity()){
			resizeArray(this.capacity()*2);
		}
		if(index > this.nextSpot)
			index = this.nextSpot;

		for(int i = this.nextSpot; i > index; i--){
			storage[i] = storage[i-1];
		}
		storage[index] = value;
		nextSpot++;
	}
	
	
	@SuppressWarnings("unchecked")
	public T remove(int index){
		// remove and return element at position index
		// shift elements to remove any gap in the list
		// throw IndexOutOfBoundsException for invalid index
		
		// halve capacity if the number of elements falls below 1/3 of the capacity
		// capacity should NOT go below INITCAP
		
		// O(N) where N is the number of elements in the list

		T temp = storage[index];

		for(int i = index; i < this.nextSpot-1; i++)
			storage[i] = storage[i+1];

		if( (((double)this.nextSpot)/this.capacity()) < (1.0/3) && this.capacity()>2 )
			resizeArray(this.capacity()/2);

		this.nextSpot--;
		return temp;
	}  
	
	
	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------


	// Not required, update for your own testing
	@Override
	public String toString(){
		// return string representation of DynamicArray
		// update if you want to include more information 
		return "DynamicArray with size "+this.storage.length+", capacity "+this.capacity();
		
  	}
  	
	public static void main (String args[]){
		// new list?
		DynamicArray<Integer> ida = new DynamicArray<>();
		if ((ida.size() == 0) && (ida.capacity() == 2)){
			System.out.println("Yay 1");
		}

		// adding to the list?
		boolean ok = true;
		for (int i=0; i<3;i++)
			ok = ok && ida.add(i*5);
		
		if (ok && ida.size()==3 && ida.get(2) == 10 && ida.capacity() == 4 ){
			System.out.println("Yay 2");
		}
		
		ida.add(1,-10);
		ida.add(4,100);
		if (ida.set(1,-20)==-10 && ida.get(2) == 5 && ida.size() == 5 
			&& ida.capacity() == 8 ){
			System.out.println("Yay 3");
		}
		
		// removing from the list?
		if (ida.remove(0) == 0 && ida.remove(0) == -20 && ida.remove(2) == 100 
			&& ida.size() == 2 && ida.capacity() == 4 ){
			System.out.println("Yay 4");
		}
		
		// remember to tests more things...
	}

}