import java.util.Scanner;

public class TestDriver {
	public static void main(String[] args) {
		House house = new House(1);
		Player player1 = new Player("Big Dicky");
		Scanner sc = new Scanner(System.in);
		int num_players = 1;

		System.out.println("Welcome to Blackjack, " + player1.getName());
		initialDeal(player1, house, 2);
		System.out.println("Your initial hand is:\n" + player1.getInfo());

		while(gameOver(player1, house) == false) {
			int turn_temp = house.getTurn();
			if(house.getTurn() != num_players) {
				System.out.println("Enter command: hold, hit, or hands");
				while(turnOver(turn_temp, house) == false) {
					switch(sc.next()) {
						case "hold" : 	
							house.nextTurn();
							System.out.println("You hold");
							break;
						case "hit" :
							player1.hit(house.deal());
							house.nextTurn();
							System.out.println("New card: " + player1.lookAtCard());
							break;
						case "hands" :
							System.out.println("Your hand:\n" + player1.getInfo());
							System.out.println("House hand:\n" + house.getInfo());
							break;
						case "default" :
							System.out.println("Invalid command. Try again.");
							break;
					}	
				}
			}
			else {
				switch(house.stayOrHit()) {
					case 1 : 	house.hit(house.deal());
								house.nextTurn();
								System.out.println("House receives: " + house.lookAtCard());
								break;
					case 2 : 
								System.out.println("House holds");
								house.nextTurn();
								break;
				}
			}	
		}
	}

	public static void initialDeal(Player player, House house, int n) {
		for(int i = 0; i < n; i++) {
			player.hit(house.deal());
			if(i == n - 1) {
				house.hitHoleCard(house.deal());
			}
			else {
				house.hit(house.deal());	
			}
			
		}
	}

	public static boolean gameOver(Player player, House house) {
		if(player.getHand().getValue() >= 21 || house.getHand().getValue() >= 21) {
			return true;
		}
		else {
			return false;
		} 
	} 
	
	public static boolean turnOver(int turn_temp, House house) {
		if(turn_temp != house.getTurn()) {
			return true;
		}
		else {
			return false;
		}
	}
}