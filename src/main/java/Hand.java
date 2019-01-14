import java.util.LinkedList;

class Hand {
	private LinkedList<Card> hand;
	private int size;
	/*private int value;*/

	public Hand() {
		this.hand = new LinkedList<Card>();
		this.size = 0;
	/*this.value = 0;*/
	}

	public void receive(Card card) {
		this.hand.add(card);
		/*this.value += card.getValue();*/
		this.size++;		
	}

	public String getInfo() {
		String info = "";
		for(Card card : hand) {
			info += card.getName() + " of " + card.getSuit() + "\n";
		}
		return info;
	}

	public int getSize() {
		return this.size;
	}

	public int getValue() {
		int sum = 0;
		for(Card card : hand) {
			sum += card.getValue();
		}
		return sum;
	}

	public String lookAtCard() {
		Card card = this.hand.get(size - 1);
		return card.getName() + " of " + card.getSuit();
	}
	
	public LinkedList<Card> aceGet() {
		LinkedList<Card> acelist = new LinkedList<Card>();
		for(Card card : hand) {
			if(card.getName() == "ACE") {
				acelist.add(card);
			}
		}

		return acelist;

		/*switch(ace.getValue()) {
			case 11 : 
				ace.setValue(1);
				fulfilled = true;
				break;
			case 1 :
				ace.setValue(11); 
				fulfilled = true;
				break;
			default : 
				System.out.println("No such ace. Please try again.");
				fulfilled = false;
				break;
		}
		return fulfilled;*/
	}
}