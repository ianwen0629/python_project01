class Main {
  public static void main(String[] args) {
		System.out.println(">Running tests");
    Test test = new Test();
		System.out.println(">Testing finished");

		System.out.println("\n>Game 'Switch' starts!");
		Switch game = new Switch();
		System.out.println(">Initialize finished\n");
		game.newGame();
		System.out.println(">Game ends!");
  }
}
