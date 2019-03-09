import java.util.ArrayList;
import java.util.Scanner;
class Switch{
	private Pack p;
	private CardCollection player,com1,com2,com3,pile;
	private CardCollection[] computers = new CardCollection[3];
	private boolean change,addTwo,skip,reversed;
	private String suit;
	private Scanner reader,nothing;
	public Switch(){
		p = new Pack();
		player = new CardCollection();
		com1 = new CardCollection();
		com2 = new CardCollection();
		com3 = new CardCollection();
		pile = new CardCollection();
		computers[0] = com1;
		computers[1] = com2;
		computers[2] = com3;
		suit = "";
		reader = new Scanner(System.in);
		nothing = new Scanner(System.in);
	}

	/**
	 * To call for a new game
	 */
	public void newGame(){
		p.shuffle();
		deal();
		System.out.println(player + "\n" + com1 + "\n" + com2 + "\n" + com3);
		Card first = p.draw();
		while(first.getValue() == 1 || first.getValue() == 2 || first.getValue() == 11 || first.getValue() == 12 || first.getValue() == 13){
			first = p.draw();
		}
		System.out.println("\n>Scroll down\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println(">The first card of the game: " + first);
		pile.addCard(first);
		while (true){
			System.out.println("\n\n\n\n");
			if (player()) break;
			System.out.println(player + "\n" + com1 + "\n" + com2 + "\n" + com3);
			if (com()) break;
			if (p.getSize() < 5){
				while (pile.getSize() > 1){
					p.addCard(pile.removeCard(1));
				}
				p.shuffle();
				System.out.println("The pack is refilled");
			}
		}
		System.out.println();
		if (player.getSize() == 0) System.out.println(">You win!");
		else if (com1.getSize() == 0) System.out.println(">Com1 win!");
		else if (com2.getSize() == 0) System.out.println(">Com2 win!");
		else System.out.println(">Com3 win!");
		reader.close();
	}

	/**
	 * Initialize everyone's deck
	 * Each player get 7 cards at the beginning
	 */
	public void deal(){
		for (int i = 0; i < 7; i++){
			player.addCard(p.draw());
			com1.addCard(p.draw());
			com2.addCard(p.draw());
			com3.addCard(p.draw());
		}
	}

	/**
	 * To check if the card that it about to be played is available
	 * return true if the card can be played
	 */
	public boolean inspect(Card c){
		if (change){
			if (c.getSuit().equals(suit)) return true;
			else return false;
		}
		return c.getValue() == 1 || pile.getCard(0).compareValue(c) == 0 || pile.getCard(0).compareSuit(c) == 0;
	}

	/**
	 * Play a card
	 * Includes adding a card to a pile
	 * And removing a card from the player's deck
	 */
	public void play(CardCollection c,int i){
		pile.addCard(c.getCard(i));
		System.out.println("Plays " + c.getCard(i));
		c.removeCard(i);
	}

	/**
	 * To check if the player need to draw card(s)
	 * Include draw a card when no card is available to play
	 * And draw two cards when the last player plays a 2
	 */
	public boolean checkDraw(CardCollection c){
		if (addTwo){
			if (c.equals(player)){
				System.out.println("You need to draw two cards");
				System.out.print(">>Press enter to continue");
				nothing.nextLine();
			}else System.out.println("Draw two cards");
			c.addCard(p.draw());
			c.addCard(p.draw());
			addTwo = false;
			return true;
		}
		for (int i = 0; i < c.getSize(); i++){
			if (inspect(c.getCard(i))){
				return false;
			}
		}
		if (c.equals(player)){
			System.out.println("You need to draw a card");
			System.out.print(">>Press enter to continue");
			nothing.nextLine();
		}else System.out.println("Draw a card");
		c.addCard(p.draw());
		return true;
	}

	/**
	 * Reverse the order
	 */
	public void reverse(){
		reversed = !reversed;
		if (reversed){
			computers[0] = com3;
			computers[1] = com2;
			computers[2] = com1;
			System.out.println("The order is reversed");
		}else{
			computers[0] = com1;
			computers[1] = com2;
			computers[2] = com3;
			System.out.println("The order is back to normal");
		}
	}

	/**
	 * This is the player controlled by inputs
	 * Returns true when all the cards in the deck is finished
	 */
	public boolean player(){
		System.out.println("Com1 have " + com1.getSize() + " cards");
		System.out.println("Com2 have " + com2.getSize() + " cards");
		System.out.println("Com3 have " + com3.getSize() + " cards");

		if (skip){
			System.out.println("Your turn is skipped");
			System.out.print(">>Press enter to continue");
			nothing.nextLine();
			skip = false;
			return false;
		}

		System.out.println("Your deck: [ " + player + "]");
		while (!checkDraw(player)){
			System.out.println("Please choose a number from 1 ~ " + player.getSize() + " to play a card.");
			int n;
			while(true){
				System.out.print(">>Enter a number: ");
				n = reader.nextInt();
				if (n > player.getSize() || n < 1) System.out.println("Please enter a valid number");
				else break;
			}
			Card input = player.getCard(n - 1);
			if (input.getValue() == 1){
				play(player,n - 1);
				if (player.getSize() == 0) break;
				System.out.print(">>Which suit do you want to change to? ");
				Scanner read = new Scanner(System.in);
				while (!change){
					String s = read.nextLine();
					switch (s){
						case "spade": case "Spade": case "♠": 
							suit = "♠";
							System.out.println("Changed to spade ♠");
							change = true;
							break;
						case "heart": case "Heart": case "♥":
							suit = "♥";
							System.out.println("Changed to heart ♥");
							change = true;
							break;
						case "diamond": case "Diamond": case "♦": 
							suit = "♦";
							System.out.println("Changed to diamond ♦");
							change = true;
							break;
						case "club": case "Club": case "♣": 
							suit = "♣";
							System.out.println("Changed to club ♣");
							change = true;
							break;
						default:
							suit = "N/A";
							System.out.println("Please enter a valid suit: spade/heart/diamond/club");
					}
				}
				break;
			}else if (inspect(input) && input.getValue() == 2){
				play(player,n - 1);
				addTwo = true;
				break;
			}else if (inspect(input) && input.getValue() == 11){
				play(player,n - 1);
				skip = true;
				break;
			}else if (inspect(input) && input.getValue() == 12){
				play(player,n - 1);
				System.out.println("\nYou can play another card!");
				System.out.println("Your deck: [" + player + "]");
			}else if (inspect(input) && input.getValue() == 13){
				play(player,n - 1);
				reverse();
				break;
			}else if (inspect(input)){
				play(player,n - 1);
				break;
			}else System.out.println("Please play a valid card");
		}
		System.out.println();
		return player.getSize() == 0;
	}

	/**
	 * Computer player
	 * return true when one of the computer player finish its deck
	 */
	public boolean com(){
		int comPlaying = 0;
		for (int i = 0; i < 3; i++){
			if (reversed) comPlaying = 3 - i;
			else comPlaying = i + 1;

			System.out.println("\nCom" + comPlaying + "'s turn");
			if (skip){
				i++;
				if (reversed) comPlaying--;
				else comPlaying++;
				System.out.println("Skipped");
				skip = false;
				if (i > 2) break;
				System.out.println("\nCom" + comPlaying + "'s turn");
			}

			CardCollection c = computers[i];
			int n = 0;

			while (!checkDraw(c)){
				Card input = c.getCard(n);

				if (input.getValue() == 1){
					play(c,n);
					if (c.getSize() == 0) break;
					suit = c.getCard(0).getSuit();
					System.out.println("Change the suit to " + suit);
					change = true;
					break;
				}else if (inspect(input) && input.getValue() == 2){
					play(c,n);
					addTwo = true;
					change = false;
					break;
				}else if (inspect(input) && input.getValue() == 11){
					play(c,n);
					skip = true;
					change = false;
					break;
				}else if (inspect(input) && input.getValue() == 12){
					play(c,n);
					change = false;
					n = 0;
				}else if (inspect(input) && input.getValue() == 13){
					play(c,n);
					reverse();
					i = 2 - i;
					break;
				}else if (inspect(input)){
					play(c,n);
					change = false;
					break;
				}else n++;
			}
			if (c.getSize() == 0) return true;
		}
		return false;
	}
}