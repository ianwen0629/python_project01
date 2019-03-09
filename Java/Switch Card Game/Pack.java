import java.util.ArrayList;

//getSize and toString method are the same with the methods in CardCollection class
//So I extend this class from CardCollection class

class Pack extends CardCollection{
	public Pack(){
		super();
		for (int i = 1; i <= 13; i++){
			Card spades = new Card("♣",i);
			Card diamonds = new Card("♦",i);
			Card hearts = new Card("♥",i);
			Card clubs = new Card("♠",i);
			super.addCard(spades);
			super.addCard(diamonds);
			super.addCard(hearts);
			super.addCard(clubs);
		}
	}

	/**
	 * It's the same with the removeCard method in the CardCollection class
	 * Just a different name
	 */
	public Card draw(){return super.removeCard(0);}

	/**
	 * Shuffle the deck
	 */
	public void shuffle(){
		for (int i = 0; i < super.getSize(); i++){
			int r = (int)(Math.random() * super.getSize());
			Card temp = super.getCard(r);
			super.setCard(r,getCard(i));
			super.setCard(i,temp);
		}
	}
}
