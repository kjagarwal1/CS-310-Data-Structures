/**************************************************************************
 * @author <INSERT YOUR NAME>
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Hand.java
 *
 * Description: <INSERT DESCRIPTION>
 * 
 ***************************************************************************/

public class Hand<T extends Card>{

	// TO DO: add your implementation and JavaDoc

	private T [] cards;
	private int numCards;
	
	public Hand(){
		// constructor
		// initial size of cards must be no greater than 5


		cards = (T[])new Card[5];
		numCards = 0;

	}
	
	public int numCards(){
		// return the number of cards
		// O(1)
		return numCards;
	}	
	
	
	public T getCard(int index){
		// return card at index 
		// throw RuntimeException for invalid index
		// O(1)
		return cards[index];
	}

	public void setCard(int index, T c){
		// change the card at index to be c	
		// throw RuntimeException for invalid index
		// O(1)
		cards[index] = c;
	}

	public void addCard(T c) {
		// add card c at the end 
		// O(N)
		if (cards[cards.length - 1] != null) {
			T[] temp = (T[]) new Card[cards.length*2];
			for (int i = 0; i < cards.length; i++) {
				temp[i] = cards[i];
			}
			cards = temp;
		}
		cards[numCards] = c;
		numCards++;
	}

	
	public int indexOf(T c){
		 // find the index of a given card c, 
		 // returns -1 if not found	
		 // O(N) 

		for(int i = 0; i < cards.length; i++){
			if( cards[i] != null && cards[i].equals(c) )
				return i;
			}
		return -1;
	}
		
	 
	public T removeCard(int index){
		// remove the card at index, 
		// throw RuntimeException for invalid index
		// O(N)
		T temp = cards[index];

		for(int i = index; i < (numCards-1); i++)
				cards[i] = cards[i+1];

		cards[numCards-1] = null;
		numCards--;
		return temp;
	}
	
	public boolean removeCard(T c){
		// remove card c, 
		// returns false if no such card
		// O(N)
		int i = indexOf(c);
		if( i < 0 )
			return false;
		else
			removeCard(i);
		return true;
	}
	
	
  
	// --------------------------------------------------------
	// example test code... edit this as much as you want!
	// you will need a working CardSwitch class to run the given code


	// Not required, update for your testing purpose
	@Override
	public String toString(){
		// return string representation of hand
		// update if you want to include information for all cards in hand
		return "Hand with "+numCards+" cards";
		
		
  	}


	public static void main(String[] args) {
	
		CardSwitch card1 = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
		CardSwitch card2 = new CardSwitch(Card.Rank.JACK, Card.Suit.SPADES);
		CardSwitch card3 = new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS);
		
		
		Hand<CardSwitch> myHand = new Hand<CardSwitch>();
		myHand.addCard(card1);
		myHand.addCard(card2);
		
		if ((myHand.numCards() == 2) && (myHand.getCard(0).equals(card1))){
			System.out.println("Yay 1");
		}
		
		myHand.addCard(card3);
		
		if ( card2.equals(myHand.removeCard(1)) && myHand.getCard(1).equals(card3)){
			System.out.println("Yay 2");
		}

		if ((myHand.indexOf(card1)==0) && (myHand.indexOf(card2) == -1 )){
			System.out.println("Yay 3");
		}

	}


}