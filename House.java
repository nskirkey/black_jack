class House {
	private Deck deck;
	private Hand hand;

	public House() {
		this.deck = new Deck();
		this.hand = new Hand();
	}

	public void deal(Hand hand) {
		hand.receive(this.deck.getNextCard());
	}

}