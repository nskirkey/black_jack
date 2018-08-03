import java.util.Scanner;

public class TestDriver {
	public static void main(String[] args) {
		House house = new House(1);
		Player player1 = new Player("Big Dicky");
		Scanner sc = new Scanner(System.in);
		int num_players = 1;
		int game_number = 1;

		System.out.println("Welcome to Blackjack, " + player1.getName());

		while(continueToPlay(sc, game_number, player1, house)) {
			System.out.println("Enter wager:");

			while(!player1.newBet(sc.nextDouble())) {
				System.out.println("Enter wager:");
			}

			initialDeal(player1, house, 2);
			System.out.println("Your initial hand is:\n" + player1.getInfo());

			while(house.getTurn() <= num_players) {
				int turn_temp = house.getTurn();

				if(house.getTurn() != num_players) {
					System.out.println("Enter command: hold, hit, hands, wallet, or bet");

					while(turnOver(turn_temp, house) == false) {
						switch(sc.next()) {
							case "hold" : 	
								house.nextTurn();
								System.out.println("You hold");
								break;
							case "hit" :
								player1.hit(house.deal());
								System.out.println("New card: " + player1.lookAtCard());
								break;
							case "hands" :
								System.out.println("Your hand:\n" + player1.getInfo());
								System.out.println("House hand:\n" + house.getInfo());
								break;
							case "wallet" :
								System.out.println("Wallet contents: " + player1.getWallet());
								break;
							case "bet" :
								System.out.println("Current bet: " + player1.getBet());
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

				System.out.println("\nFinal hands:\nYour hand:\n" + player1.getInfo() + "\nHouse hand:\n" + house.getInfo());

				switch(determineWinner(player1, house)) {
					case 0 : 	System.out.println("You win");
								player1.winBet(2);
								break;
					case 1 :	System.out.println("House wins") ;
								player1.loseBet();
								break;
				}	
			}
			game_number++;
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

	public static boolean turnOver(int turn_temp, House house) {
		if(turn_temp != house.getTurn()) {
			return true;
		}
		else {
			return false;
		}
	}

	public static int determineWinner(Player player, House house) {
		int player_score = player.getHand().getValue();
		int house_score = house.getHand().getValue();

		if(player_score > house_score && player_score <= 21) {
			return 0;
		}
		else if(player_score <= 21 && house_score > 21) {
			return 0;
		}
		else {
			return 1;
		}
	}

	public static boolean continueToPlay(Scanner sc, int game_number, Player player, House house) {
		boolean on_off_switch = true;
		if(game_number == 1) {
			return on_off_switch;
		}
		System.out.println("Play again? (enter: yes/no)");
		switch(sc.next().toLowerCase()) {
			case "yes" : 	on_off_switch = true;
							break;
			case "no" : 	on_off_switch = false;
							break;
		}	
		player.newHand();
		house.newHand();
		house.newDeck();
		return on_off_switch;
	}
}