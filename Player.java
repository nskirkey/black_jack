class Player {
	private String name;
	private Hand hand;
	private double wallet;
	private double bet;

	public Player(String name) {
		this.name = name;
		this.hand = new Hand();
		this.wallet = 1000.00;
		this.bet = 0.00;
	}
	
	public String getName() {
		return this.name;
	}

	public String getInfo() {
		return this.hand.getInfo() + "Value: " + this.hand.getValue() + "\n";
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

	public void newHand() {
		this.hand = new Hand();
	}

	public double getWallet() {
		return this.wallet;
	}

	public void setWallet(double amount) {
		this.wallet = amount;
	}

	public boolean newBet(double amount) {
		if(this.wallet - amount < 0) {
			return false;
		}
		else {
			this.wallet -= amount;
			this.bet = amount;
			return true;
		}
	}

	public void winBet(int multiplier) {
		this.wallet += bet * multiplier;
		this.bet = 0;
	}

	public void loseBet() {
		this.bet = 0;
	}

	public double getBet() {
		return this.bet;
	}
}