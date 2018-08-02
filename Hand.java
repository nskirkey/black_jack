import java.util.LinkedList;

class Hand {
	private LinkedList<Card> hand;
	private int size;
	private int value;

	public Hand() {
		this.hand = new LinkedList<Card>();
		this.size = 0;
		this.value = 0;
	}

	public void receive(Card card) {
		this.hand.add(card);
		this.value += card.getValue();
		this.size++;		
	}

	public String getInfo() {
		String info = "Hand:\n";
		for(Card card : hand) {
			info += card.getName() + " of " + card.getSuit() + "\n";
		}
		return info;
	}

	public int getSize() {
		return this.size;
	}

	public int getValue() {
		return this.value;
	}
}