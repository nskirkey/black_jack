class Card {
	private String name;
	private int value;
	private String suit;

	public Card(String name, int value, String suit) {
		this.name = name;
		this.value = value;
		this.suit = suit;
	}

	public String getName(){
		return this.name;
	}

	public int getValue() {
		return this.value;
	}

	public String getSuit() {
		return this.suit;
	}

	public void setValue(int value) {
		this.value = value
;	}
}