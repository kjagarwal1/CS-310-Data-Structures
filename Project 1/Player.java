/**************************************************************************
 * @author Kshitij Agarwal
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Player.java
 *
 * Description: <INSERT DESCRIPTION>
 * 
 ***************************************************************************/
class Player <T extends Card> {
	
	// required fields
	private String name;
	private int points;
	private Hand<T> hand;	
	private Player<T> next;
	
	// TO DO: add your implementation and JavaDoc

	public Player(String name){
		//constructor
		this.name = name;
		hand = new Hand<>();
	}
		
	public void setNext(Player<T> p){
		//set next player
		this.next = p;
	}
	
	public Player<T> getNext(){
		//return next player
		return next;
	}
	
	public boolean hasNext() {
		// whether there is a player after me
		return next != null;
	}
	
	public int getPoints(){
		// return points of this player
		// determined by cards in hand

		//return points;
		return this.points;
	}
		
	public String getName(){
		// return name of the player
		return name;
	}
	

	public boolean receiveCard(T c){
		// receive a card and add it to hand
		// return?
		int i = hand.indexOf(c);
		if( i < -1 )
			return false;
		hand.addCard(c);
		this.setPoints();
		return true;
	}
	
	public boolean hasCard(T c){
		// return checking: whether we have the card in hand
		return hand.indexOf(c) >= 0;
	}
	
	public boolean playCard(T c){
		// give away one card from hand
		// return false if card not present

		if(hasCard(c)){
			hand.removeCard(c);
			return true;
		}
		else
			return false;
	}

	public T playCard(int index){
		// give away the card at index
		// throw RuntimeException for invalid index
		return hand.removeCard(index);
	}
	
	private void setPoints(){
		int num = hand.numCards(), sum = 0;

		for(int i = 0; i < num; i++){
			sum += hand.getCard(i).getPoints();
		}
		this.points = sum;
	}

	//---------------------------------------------------
	//example test code... edit this as much as you want!
	// you will need working CardSwitch and Hand classes to run the given code
	
	
	public String toString(){
		// Not required; edit for your own testing 
		return "Player "+ name;
	}


	public static void main(String[] args) {
		CardSwitch card1 = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
		CardSwitch card2 = new CardSwitch(Card.Rank.JACK, Card.Suit.SPADES);
		CardSwitch card3 = new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS);
		Player<CardSwitch> player1 = new Player<CardSwitch>("Tom");
		Player<CardSwitch> player2 = new Player<CardSwitch>("Jerry");

		player1.receiveCard(card2);
		player1.receiveCard(card3);
		player2.receiveCard(card1);
		player1.setNext(player2);

		if (player1.getName().equals("Tom") && player1.getNext() == player2){
			System.out.println("Yay 1");
		}
		if (player1.hasCard(card2) == true && player1.getPoints() == 19){
			System.out.println("Yay 2");
		}
		if ((player2.hasNext()==false) && player1.playCard(0) == card2){
			System.out.println("Yay 3");
		}
	
	}


}