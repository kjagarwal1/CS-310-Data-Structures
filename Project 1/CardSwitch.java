/**************************************************************************
 * @author <INSERT YOUR NAME>
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 *
 * File Name: CardSwitch.java
 *
 * Description: <INSERT DESCRIPTION>
 * 
 ***************************************************************************/

public class CardSwitch extends Card{

	// TO DO: fill the code below and add JavaDoc
	public CardSwitch(Rank r, Suit s){
        // constructor to create card for the game Switch
        super(r, s);
	}
	
	@Override
	public boolean equals(Card anotherCard){
		if( this.rank.equals(anotherCard.rank) )
			if( this.suit.equals(anotherCard.suit) )
				return true;
		return false;
		
	}
	
	@Override
    public int getPoints(){
	    // return points of the card
		switch(this.rank){
			case ACE:
				return 1;
			case TWO:
				return 2;
			case THREE:
				return 3;
			case FOUR:
				return 4;
			case FIVE:
				return 5;
			case SIX:
				return 6;
			case SEVEN:
				return 7;
			case EIGHT:
				return 8;
			case NINE:
				return 9;
			case TEN:
			case JACK:
			case QUEEN:
			case KING:
				return 10;
			default:
			    System.out.println("Sorry, there is no value in this card");
			    return 0;
		}

    }
	
	@Override
	public String toString(){
		// convert card to string consisting of as "(rank,suit)"
		// see examples below for format
        return "(" + this.rank + "," + this.suit + ")";
	}
	
	//----------------------------------------------------
	//example test code... edit this as much as you want!
	public static void main(String[] args) {
		CardSwitch card = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
		
		if (card.getRank().equals(Card.Rank.ACE)){
			System.out.println("Yay 1");
		}
		
		if (card.toString().equals("(ACE,SPADES)")){
			System.out.println("Yay 2");
		}

		if (card.getPoints()==1){
			System.out.println("Yay 3");
		}
	}

}