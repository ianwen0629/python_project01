import java.util.ArrayList;
class CardCollection{
	private ArrayList<Card> deck;
	public CardCollection(){deck = new ArrayList<Card>();}

	public void addCard(Card c){deck.add(0,c);}
	public Card removeCard(int i){return deck.remove(i);}
	public Card getCard(int i){return deck.get(i);}
	public void setCard(int i, Card c){deck.set(i,c);}
	public int getSize(){return deck.size();}
	public String toString(){
		String str = "";
		for (Card i : deck){
			str += i + " ";
		}
		return str;
	}
}