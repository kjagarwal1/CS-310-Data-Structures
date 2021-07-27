/**************************************************************************
 * @author Kshitij Agarwal
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: BoardSwitch.java
 *
 * Description: <INSERT DESCRIPTION>
 * 
 ***************************************************************************/
public class BoardSwitch<T extends Card> extends Board<T>{
	
	// TO DO: add your implementation and JavaDoc
	
	public BoardSwitch(Deck<T> deck){
		//constructor
		//start with zero players
		super(deck);
	}

	@Override
	public Player<T> getCurrentPlayer() {
		// return the current player
		// O(1)
		return currentPlayer;
	}

	@Override
	public int getNumPlayers() {
		// return how many players 
		// O(1)
		return numPlayer;
	}
	
	@Override
	public Deck<T> getDeck(){
		//return the current deck
		// O(1)
		return deck;
	}

	@Override
	public boolean changeTurn() {
		// move the current player to the next one in the linked list
		// return false if cannot change
		// O(1)
		if( !currentPlayer.hasNext() )
			return false;

		currentPlayer = currentPlayer.getNext();
		return true;
	}
	
	@Override
	public void addPlayer(Player<T> x) {
		// add another player in the linked list
		// should add to the left of currentPlayer
		// O(N)
		Player<T> temp = currentPlayer;

		if( temp != null ) {
			x.setNext(currentPlayer);
			while (temp.getNext() != currentPlayer) {
				temp = temp.getNext();
			}
			temp.setNext(x);
		}
		else {
			currentPlayer = x;
			currentPlayer.setNext(x);
		}

		numPlayer++;
	}
	
	public Player<T> findWinner(){
		// return the player with the highest point
		// O(N)
		Player<T> temp = currentPlayer, high = currentPlayer;

		while( temp.getNext() != currentPlayer ){
			if( temp.getPoints() > high.getPoints() )
				high = temp;
			temp = temp.getNext();
		}

		return high;
	}

	//-----------------------------------------------------
	// example test code... edit this as much as you want!
	// you will need working CardSwitch, Hand, Player, Deck and PlaySwitch classes to run the given code
	
	public static void main(String[] args) {
		Deck<CardSwitch> deck = new Deck<CardSwitch>();
		PlaySwitch.init_deck(deck);
			
		BoardSwitch<CardSwitch> myBoard = new BoardSwitch<CardSwitch>(deck);
		Player<CardSwitch> player1 = new Player<CardSwitch>("Tom");
		Player<CardSwitch> player2 = new Player<CardSwitch>("Jerry");

		myBoard.addPlayer(player1);
		
		if (myBoard.getNumPlayers() ==1  && myBoard.getCurrentPlayer() == player1
			&& player1.getNext() == player1){
			System.out.println("Yay 1");
		}

		myBoard.addPlayer(player2);
		if (myBoard.getNumPlayers() ==2  && myBoard.getCurrentPlayer() == player1
			&& (myBoard.changeTurn()==true) && myBoard.getCurrentPlayer() == player2){
			System.out.println("Yay 2");
		}
		
		player1.receiveCard(new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES));
		player1.receiveCard(new CardSwitch(Card.Rank.JACK, Card.Suit.CLUBS));
		player2.receiveCard(new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS));
		player2.receiveCard(new CardSwitch(Card.Rank.THREE, Card.Suit.SPADES));

		if (player1.getNext() == player2 && player2.getNext() == player1
			&& myBoard.findWinner() == player2){
			System.out.println("Yay 3");
		}
		
	
	}
	

}
