class Card{
	private String suit;
	private int value;
	public Card (String suit, int value){
		this.suit = suit;
		this.value = value;
	}

	public String getSuit(){return suit;}
	public int getValue(){return value;}

	/**
	 * Compare between two suits
	 * ♠ > ♥ > ♦ > ♣
	 * If the suit of c is smaller, return -1
	 * If the suit of c is larger, return 1
	 * If both are the same, return 0
	 */
	public int compareSuit(Card c){
		if (c.getSuit().equals(suit)) return 0;
		else if (
			suit.equals("♠") || 
			(suit.equals("♥") && (c.getSuit().equals("♦") || c.getSuit().equals("♣"))) ||
			(suit.equals("♦") && c.getSuit().equals("♣"))
		) return -1;
		else return 1;
	}

	/**
	 * Compare between two values
	 * return the value of c - this value
	 */
	public int compareValue(Card c){return c.getValue() - value;}
	
	public String toString(){
		switch (value){
			case 1: return suit + "A";
			case 11: return suit + "J";
			case 12: return suit + "Q";
			case 13: return suit + "K";
			default: return suit + value;
		}
	}
}
