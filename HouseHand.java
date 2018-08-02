class HouseHand extends Hand {
	private Card hole_card;

	public HouseHand() {
		super();
	}

	public void setHoleCard(Card hole_card) {
		this.hole_card = hole_card;
	}

	public Card getHoleCard() {
		return this.hole_card;
	}
	@Override
	public int getValue() {
		return super.getValue() + this.hole_card.getValue();
	}
}