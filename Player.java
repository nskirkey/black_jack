class Player {
	private String name;
	private Hand hand;

	public Player(String name) {
		this.name = name;
		this.hand = new Hand();
	}
	
	public String getName() {
		return this.name;
	}

	public String getInfo() {
		return this.hand.getInfo();
	}

	public Hand getHand() {
		return this.hand;
	}

	public String lookAtCard() {
		return this.hand.lookAtCard();
	}

	public void hit(Card card) {
		this.hand.receive(card);
	}
}