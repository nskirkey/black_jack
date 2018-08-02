class House {
	private Deck deck;
	private HouseHand hand;
	private int num_players;
	private int turn;

	public House(int num_players) {
		this.deck = new Deck();
		this.hand = new HouseHand();
		this.num_players = num_players;
		this.turn = 0;
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
		return this.hand.getInfo();
	}

	public HouseHand getHand() {
		return this.hand;
	}

	public String lookAtCard() {
		return this.hand.lookAtCard();
	}

	public int stayOrHit() {
		if (this.hand.getValue() < 18) {
			return 1;
		}
		else{
			return 2;
		}
	}

	public void hitHoleCard(Card card) {
		this.hand.setHoleCard(card);
	}

	public Card revealHoleCard() {
		return this.hand.getHoleCard();
	}

	public int getTurn() {
		return this.turn;
	}

	public void nextTurn() {
		if(this.turn == num_players) {
			this.turn = 0;
		}
		else {
			this.turn++;
		}
		
	}
}