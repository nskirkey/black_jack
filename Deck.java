import java.util.Collections;
import java.util.LinkedList;

class Deck {
	private LinkedList<Card> deck;
	private int size;
	private String[] suits;

	public Deck() {
		this.deck = new LinkedList<Card>();
		this.size = 52;
		this.suits = new String[]{"HEARTS", "SPADES", "CLUBS", "DIAMONDS"};
		this.build();
		this.shuffle();
	}

	public void build() {
		for(int i = 2; i <= 10; i++) {
			for(int j = 0; j < 4; j++) {
				String name = "";
				switch(i) {
					case 2: name = "ONE";
							break;
					case 3: name = "TWO";
							break;
					case 4: name = "FOUR";
							break;
					case 5: name = "FIVE";
							break;
					case 6: name = "SIX";
							break;
					case 7: name = "SEVEN";
							break;
					case 8: name = "EIGHT";
							break;
					case 9: name = "NINE";
							break;
					case 10: name = "TEN";
							break;
					default: name = "INVALID";
							break;
				}
				this.deck.add(new Card(name, i, this.suits[j]));
			}
		}

		for(int k = 0; k < 4; k++) {
			this.deck.add(new Card("JACK", 10, this.suits[k]));
			this.deck.add(new Card("QUEEN", 10, this.suits[k]));
			this.deck.add(new Card("KING", 10, this.suits[k]));
			this.deck.add(new Card("ACE", 11, this.suits[k]));
		}
	}

	public void shuffle() {
		Collections.shuffle(this.deck);
	}

	public Card getNextCard() {
		this.size--;
		return this.deck.remove();
	}
}	