class House {
	private Deck deck;
	private HouseHand hand;
	private int num_players;
	private int turn;
	private boolean reveal;

	public House(int num_players) {
		this.deck = new Deck();
		this.hand = new HouseHand();
		this.num_players = num_players;
		this.turn = 0;
		this.reveal = false;
	}

	public Card deal() {
		return this.deck.getNextCard();
	}

	public void hit(Card card) {
		this.hand.receive(card);
	}			

	public int getDeckSize() {
		return this.deck.getSize();
	}

	public String getInfo() {
		String name = "???";
		String suit = "???";
		String value = String.valueOf(this.hand.getValue()) + " + ???";
		if(this.reveal == true) {
			name = this.hand.getHoleCard().getName();
			suit = this.hand.getHoleCard().getSuit();
			value = String.valueOf(this.hand.getValue() + this.hand.getHoleValue());
		}
		return this.hand.getInfo() + name + " of " + suit + "\n" +
				"Value: " + value;
	}

	public HouseHand getHand() {
		return this.hand;
	}

	public String lookAtCard() {
		return this.hand.lookAtCard();
	}

	public boolean stayOrHit() {
		if (this.hand.getValue() + this.hand.getHoleValue() < 18) {
			return true;
		}
		else{
			return false;
		}
	}

	public void hitHoleCard(Card card) {
		this.hand.setHoleCard(card);
	}

	public void revealHoleCard() {
		this.reveal = true;
	}

	public void concealHoleCard() {
		this.reveal = false;
	}

	public int getTurn() {
		return this.turn;
	}

	public void nextTurn() {
		this.turn++;		
	}

	public void newDeck() {
		this.deck = new Deck();
		this.turn = 0;
	}

	public void newHand(){
		this.hand = new HouseHand();
	}
}