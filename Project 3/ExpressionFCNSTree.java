
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ExpressionFCNSTree{

	//==========================
	// DO NOT CHANGE
	FCNSTreeNode root;

	public ExpressionFCNSTree(){
		root = null;
	}

	public ExpressionFCNSTree(FCNSTreeNode root){
		this.root = root;
	}

	public boolean equals(ExpressionFCNSTree another){
		return root.equals(another.root);

	}

	// END OF DO NOT CHANGE
	//==========================

	public int size() {
		if( root != null ) {
			if( root.firstChild != null && root.nextSibling != null )			//gets size of both left and right
				return 1 + (new ExpressionFCNSTree(root.firstChild).size()) + (new ExpressionFCNSTree(root.nextSibling).size());
			else if( root.firstChild != null )
				return 1 + (new ExpressionFCNSTree(root.firstChild).size());	//gets size of left
			else if( root.nextSibling != null )
				return 1 + (new ExpressionFCNSTree(root.nextSibling).size());	//gets size of right
			else
				return 1;
		}

		return 0;
	}

	public int height(){
		// return the height of the tree
		// return -1 for null tree
		if( root != null ) {
			if( root.firstChild == null && root.nextSibling == null )
				return 0;

			int r = 0, l = 0;
			if( root.firstChild != null )
				l = 1 + (new ExpressionFCNSTree(root.firstChild).height());				//calculates height of right
			if( root.nextSibling != null)
				r = 1 + (new ExpressionFCNSTree(root.nextSibling).height());			//calculates height of left

			return Math.max(l, r);														//returns the greater size
		}

		return -1;
	}

	public int countNode(String s){
		// count how many nodes in the tree are with the specified symbol s
		if( root == null )
			return 0;

		int count = 0;

		if( root.element.equals(s) )													//base case
			count++;

		if( root.firstChild != null )
			count += (new ExpressionFCNSTree(root.firstChild).countNode(s));			//right side counting

		if( root.nextSibling != null )
			count += (new ExpressionFCNSTree(root.nextSibling).countNode(s));			//left side counting

		return count;
	}

	public int countNan(){
		// count and return how many nodes are marked as not-a-number
		if( root == null )
			return 0;

		int count = 0;

		if( root.nan )
			count++;

		if( root.firstChild != null )
			count += (new ExpressionFCNSTree(root.firstChild).countNan());			//right side counting

		if( root.nextSibling != null )
			count += (new ExpressionFCNSTree(root.nextSibling).countNan());			//left side counting

		return count;
	}	

	public String toStringPreFix(){
		// return a string representation of pre-order tree traversal
		// there should be exactly one single space after each tree node 
		// see main method below for examples
		String base, child = "", sibling = "";

		base = root.element + " ";														//base case

		if( root.firstChild != null )
			child = new ExpressionFCNSTree(root.firstChild).toStringPreFix();			//left side toString

		if( root.nextSibling != null )
			sibling = new ExpressionFCNSTree(root.nextSibling).toStringPreFix();		//right side toString


		return base + child + sibling;
	}

	public String toStringPostFix(){
		// return a string representation of post-order tree traversal
		// there should be exactly one single space after each tree node 
		// see main method below for examples
		String base, child = "", sibling = "";

		base = root.element + " ";														//base case

		if( root.firstChild != null )
			child = new ExpressionFCNSTree(root.firstChild).toStringPostFix();			//left side toString

		if( root.nextSibling != null )
			sibling = new ExpressionFCNSTree(root.nextSibling).toStringPostFix();		//right side toString


		return child + sibling + base;

	}

	public String toStringLevelOrder(){
		// return a string representation of level-order (breadth-first) tree traversal
		// there should be exactly one single space after each tree node 
		// see main method below for examples
		FCNSTreeNode loTree[] = new FCNSTreeNode[this.size()];		//calculates size
		int count = 1;												//counts the next open spot in array
		loTree[0] = root;
		String s = "";

		for(int i = 0; i < loTree.length - 1; i++) {				//adds children or siblings of nodes from tree based when node comes into tree
			if( loTree[i].firstChild != null ) {
				loTree[count] = loTree[i].firstChild;
				count++;
			}

			if( loTree[i].nextSibling != null ) {
				loTree[count] = loTree[i].nextSibling;
				count++;
			}
		}

		for(int i = 0; i < loTree.length; i++) {
			s += loTree[i].element + " ";
		}


		return s;
	}


	public void buildTree(String fileName) throws FileNotFoundException{
		// This method need to open file specified by the string fileName, 
		// read in a one-line numeric expression in prefix notation, and 
		// construct a first-child-next-sibling expression tree base on the input

		// set root to be the newly constructed tree root	
		// if there is any exception, root should be null
		try{
			File file = new File(fileName);
			Scanner scan = new Scanner(file);
			NodeQueue pointer = new NodeQueue();

			while(scan.hasNext()) {
				pointer.enqueue(scan.next());				//scans numbers or operations into queue
			}

			scan.close();
			root = arrangeTree(pointer);					//queue is sent to be set

		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private FCNSTreeNode arrangeTree(NodeQueue node) {

		FCNSTreeNode base = null;
		String s;
		if( node.hasNext() ) {
			s = node.dequeue();
			if( isNumber(s) ) {
				base = new FCNSTreeNode(s);									//based on queue, numbers are organized to FCNS
			}
			else if( s.equals("~") ) {
				base = new FCNSTreeNode(s);
				base.firstChild = arrangeTree(node);
			}
			else {
				base = new FCNSTreeNode(s);
				base.firstChild = arrangeTree(node);
				base.firstChild.nextSibling = arrangeTree(node);
			}
		}

		return base;
	}

	private boolean isNumber(String s) {					// checks to see if a string is a number

		try {
			@SuppressWarnings("unused")
			int x = Integer.parseInt(s);
		} catch(NumberFormatException e){
			return false;
		}
		return true;
	}

	public ExpressionBinaryTree buildBinaryTree(){
		// construct the binary tree representation of this expression 
		// and return the tree root
		return new ExpressionBinaryTree(this.BinaryTreeNodeBuilder());
	}

	private BinaryTreeNode BinaryTreeNodeBuilder() {		//builds binary tree
		if( root == null )
			return null;
		if( isNumber(root.element) )
			return new BinaryTreeNode(root.element);		//number roots will have no children
		else if( root.element.equals("~") )
			return new BinaryTreeNode(root.element, new ExpressionFCNSTree(root.firstChild).BinaryTreeNodeBuilder(), null);		//negative values only have one child
		else 								//sets child to left, and child's sibling to right
			return new BinaryTreeNode(root.element, new ExpressionFCNSTree(root.firstChild).BinaryTreeNodeBuilder(), new ExpressionFCNSTree(root.firstChild.nextSibling).BinaryTreeNodeBuilder());
		//s,BinaryTreeNode l, BinaryTreeNode r )
	}


	public String toStringPrettyInFix(){
		// return a string representation as the normal human-friendly infix expression
		// it is like we are simulating in-order tree traversal of the binary tree
		// Format of the infix string: 
		//		- there should be no space, but  
		//		- parenthesizes must be inserted wrapping around the sub-expression of each operator
		// see main method below for examples


		if( !isNumber(root.element) ) { 		//check to see if root contains operator

			if( root.element.equals("~") ) //negative sign
				return "(-" + (new ExpressionFCNSTree(root.firstChild).toStringPrettyInFix()) + ")";
			else // +-*/% operation
				return "(" + (new ExpressionFCNSTree(root.firstChild).toStringPrettyInFix()) + root.element + (new ExpressionFCNSTree(root.firstChild.nextSibling).toStringPrettyInFix()) + ")";
		}

		else { //numbers
			return root.element;
		}
	}

	public Integer evaluate(){
		// This method evaluates the expression and marks every tree node with:
		//    - operand node: node.value should be the integer value of the operand
		//    - operator node: node.value should be the integer value associated with
		//                     the sub-expression rooted at the node 
		// Return: the integer value of the root node 

		// return null for null tree

		// if there is a division by zero: keep node.value to be null and set node.nan to be true.
		// not-a-number should be propagated: for an operator, if any of its operand is not-a-number,
		//			then the node of this operator should also be marked as not-a-number
		if(root == null)
			return null;

		Integer value = null, child, sibling;

		if( isNumber(root.element) )										//returns value if root is number
			value =  Integer.parseInt(root.element);
		else if( root.firstChild.nan || root.firstChild.nextSibling.nan )	//makes sure any nan roots dont get calcuated
			root.nan = true;
		else{
			child = new ExpressionFCNSTree(root.firstChild).evaluate();
			sibling = new ExpressionFCNSTree(root.firstChild.nextSibling).evaluate();

			if( root.element.equals("~") )									//checks to see what root is set and does math of child and child's sibling
				value = (-1) * child;
			else if( root.element.equals("+") )
				value = child + sibling;
			else if( root.element.equals("-") )
				value = child - sibling;
			else if( root.element.equals("*") )
				value = child * sibling;
			else if( root.element.equals("/") )
				if( sibling == 0 )
					root.nan = true;
				else
					value = child / sibling;
			else if( root.element.equals("%") )
				if( sibling == 0 )
					root.nan = true;
				else
					value = child % sibling;
		}

		root.value = value;
		return value;
	}

	public Integer evaluateNonRec(){
		// This method evaluates the expression leaving the tree unchanged 
		// You must implement it as a non-recursive method
		// Return: the integer value of answer

		// return null for null tree
		if( root == null )
			return null;

		// For this method only, assume there are no division-by-zero issues in the input
		NodeStack stack = new NodeStack();
		boolean hasNum = false;
		int num = this.size(), answer;
		
		
		return 0;
	}




	//----------------------------------------------------
	// example testing code... make sure you pass all ...
	// and edit this as much as you want!

	public static void main(String[] args) throws FileNotFoundException{

		//     *					*
		//   /  \				   /
		//  /    \                1
		//  1     +			==>    \
		//       / \                +
		//      2   3			   /
		//                        2
		//						   \
		//                          3
		//
		// prefix: * 1 + 2 3 (expr1.txt)

		FCNSTreeNode n1 = new FCNSTreeNode("3");
		FCNSTreeNode n2 = new FCNSTreeNode("2",null,n1);
		FCNSTreeNode n3 = new FCNSTreeNode("+",n2,null);
		FCNSTreeNode n4 = new FCNSTreeNode("1",null,n3);
		FCNSTreeNode n5 = new FCNSTreeNode("*",n4,null);
		ExpressionFCNSTree etree = new ExpressionFCNSTree(n5);

		if (etree.size()==5 && etree.height()==4 && etree.countNan()==0 && etree.countNode("+") == 1){
			System.out.println("Yay 1");
		}

		if (etree.toStringPreFix().equals("* 1 + 2 3 ") && etree.toStringPrettyInFix().equals("(1*(2+3))")){
			System.out.println("Yay 2");

		}

		if (etree.toStringPostFix().equals("3 2 + 1 * ") && etree.toStringLevelOrder().equals("* 1 + 2 3 ")){
			System.out.println("Yay 3");

		}

		if (etree.evaluateNonRec() == 5)
			System.out.println("Yay 4");


		if (etree.evaluate() == 5  && n4.value==1 && n3.value==5 && !n5.nan){
			System.out.println("Yay 5");

		}

		ExpressionFCNSTree etree2 = new ExpressionFCNSTree();
		etree2.buildTree("expressions/expr1.txt"); // construct expression tree from pre-fix notation

		if (etree2.equals(etree)){
			System.out.println("Yay 6");
		}

		BinaryTreeNode bn1 = new BinaryTreeNode("1");
		BinaryTreeNode bn2 = new BinaryTreeNode("2");
		BinaryTreeNode bn3 = new BinaryTreeNode("3");
		BinaryTreeNode bn4 = new BinaryTreeNode("+",bn2,bn3);
		BinaryTreeNode bn5 = new BinaryTreeNode("*",bn1,bn4);
		ExpressionBinaryTree btree = new ExpressionBinaryTree(bn5);

		//construct binary tree from first-child-next-sibling tree
		ExpressionBinaryTree btree2 = etree.buildBinaryTree(); 
		if (btree2.equals(btree)){
			System.out.println("Yay 7");
		}


		ExpressionFCNSTree etree3 = new ExpressionFCNSTree();
		etree3.buildTree("expressions/expr5.txt"); // an example of an expression with division-by-zero
		if (etree3.evaluate() == null && etree3.countNan() == 1){
			System.out.println("Yay 8");

		}


	}
}


//=======================================
// Tree node class implemented for you
// DO NOT CHANGE
class FCNSTreeNode{

	//members
	String element;	//symbol represented by the node, can be either operator or operand (integer)
	Boolean nan;	//boolean flag, set to be true if the expression is not-a-number
	Integer value;  //integer value associated with the node, used in evaluation
	FCNSTreeNode firstChild;
	FCNSTreeNode nextSibling;

	//constructors
	public FCNSTreeNode(String el){
		element = el;
		nan = false;
		value = null;
		firstChild = null;
		nextSibling = null;
	}

	//constructors
	public FCNSTreeNode(String el,FCNSTreeNode fc, FCNSTreeNode ns ){
		element = el;
		nan = false;
		value = null;
		firstChild = fc;
		nextSibling = ns;
	}

	// toString
	@Override 
	public String toString(){
		return element.toString();
	}

	// compare two nodes 
	// return true if: 1) they have the same element; and
	//                 2) their have matching firstChild (subtree) and nextSibling (subtree)
	public boolean equals(FCNSTreeNode another){
		if (another==null)
			return false;

		if (!this.element.equals(another.element))
			return false;

		if (this.firstChild==null){
			if (another.firstChild!=null)
				return false;
		}
		else if (!this.firstChild.equals(another.firstChild))
			return false;

		if (this.nextSibling==null){
			if (another.nextSibling!=null)
				return false;
		}
		else if (!this.nextSibling.equals(another.nextSibling))
			return false;

		return true;

	}
}

class NodeQueue{

	private String element;
	private NodeQueue next;

	private NodeQueue(String newNode) {
		element = newNode;
		next = null;
	}

	public NodeQueue() {								//public constructor is pointer. that is why element will always be null
		element = null;
		next = null;
	}

	public void enqueue(String newNode) {
		NodeQueue temp = next;
		if( next == null )
			next = new NodeQueue(newNode);				//if its empty, the fist next is set to value
		else {
			while( temp.next != null )					//finds the last node in queue and sets its next value to newNode
				temp = temp.next;
			temp.next = new NodeQueue(newNode);	
		}
	}

	public String dequeue() {
		String temp = null;

		if( next != null ) {
			temp = next.element;
			next = next.next;
		}

		return temp;
	}

	public boolean hasNext() {
		// check if queue is empty 
		// return true if empty, false otherwise
		// O(1)
		if( next != null )
			return true;
		return false;
	}

	public String getFront() {
		// peek the front element
		// return the front element if there is any but do not dequeue
		// return null if queue is empty
		// O(1)
		if( next != null )
			return next.element;
		return null;
	}

}
class NodeStack{

	private String element;
	private NodeStack next;

	private NodeStack(String newNode) {
		element = newNode;
		next = null;
	}

	public NodeStack() {									///public constructor is pointer. that is why element will always be null
		element = null;
		next = null;
	}

	public void push(String newNode) {						// adds new value right at top and sets current next to the new value's next
		NodeStack temp = new NodeStack(newNode);
		temp.next = this.next;
		this.next = temp;
	}

	public String pop() {									// removes top element
		String temp = null;

		if( next != null ) {
			temp = next.element;
			next = next.next;
		}

		return temp;
	}

	public boolean hasNext() {
		// check if queue is empty 
		// return true if empty, false otherwise
		// O(1)
		if( next != null )
			return true;
		return false;
	}

	public String peak() {
		// peek the front element
		// return the front element if there is any but do not dequeue
		// return null if queue is empty
		// O(1)
		if( next != null )
			return next.element;
		return null;
	}

}