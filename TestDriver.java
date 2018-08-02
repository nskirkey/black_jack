public class TestDriver {
	public static void main(String[] args) {
		Hand hand1 = new Hand();
		Hand hand2 = new Hand();
		House house = new House();
		for(int i = 0; i < 10; i++) {
			house.deal(hand1);
			house.deal(hand2);
		}
		System.out.println(hand1.getInfo());
		System.out.println("Value: " + hand1.getValue());
		System.out.println(hand2.getInfo());
		System.out.println("Value: " + hand2.getValue());
	}
}