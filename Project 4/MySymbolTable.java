// your implementation of SymbolTable interface
// header comments here

public class MySymbolTable<SymbolType, RecordType> implements SymbolTable<SymbolType, RecordType>{

	// you decide the internal design of your class:
	//  - it must implement the provided SymbolTable interface
	//  - it cannot have any other public members (attributes or method) other than
	//    the public constructor and the methods defined in SymbolTable interface

	public MySymbolTable(){

	}


	// make sure you implement all methods in SymbolTable interface




	//------------------------------------
	// example test code... edit this as much as you want!
	public static void main(String[] args){
		MySymbolTable<String,Integer> table = new MySymbolTable<String,Integer>();

		if(table.size()==0 && !table.hasSymbol("a")) 	{
			System.out.println("Yay 1");
		}

		table.putRecord("a",new Integer(136));
		table.putRecord("b",new Integer(112));

		if(table.size()==2 && table.hasSymbol("a") && table.getRecord("b").equals(new Integer(112))) 	{
			System.out.println("Yay 2");
		}

		table.putRecord("b",new Integer(211));
		Integer v = table.removeSymbol("a");
		if(table.size()==1 && v.equals(new Integer(136)) && !table.hasSymbol("a")
				&& table.getRecord("b").equals(new Integer(211))) 	{
			System.out.println("Yay 3");
		}

	}


}


