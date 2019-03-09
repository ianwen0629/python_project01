class Test{
	private Pack testPack;
	private Switch testGame;
	public Test(){
		testPack = new Pack();
		//System.out.println("Initialize: " + testPack + "\n" + "Size: " + testPack.getSize() + "\n");
		testPack.shuffle();
		/*System.out.println("Shuffled: " + testPack + "\n" + "Size: " + testPack.getSize() + "\n");
		for (int i = 0; i < 13; i++){
			testPack.draw();
			System.out.println("Draw " + i + ": " + testPack + "\n" + "Size: " + testPack.getSize() + "\n");
		}

		testGame = new Switch();
		testGame.newGame();*/
	}
}