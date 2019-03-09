import java.util.Scanner;

class Main {
  public static void main(String[] args) {
		char player = 'x';
		Scanner sc = new Scanner(System.in);
		Connect4 c = new Connect4();
    while (c.winner() == '.')
		{
			System.out.println(c);
			System.out.println("Player " + player + "'s turn\nEnter a column");
			int choice = Integer.parseInt(sc.nextLine());
			if (c.addPiece(player, choice))
			{
				if (player == 'x') player = 'o';
				else player = 'x';
			}
		}
		System.out.println("The winner is " + c.winner());
  }
}