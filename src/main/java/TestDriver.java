import java.util.Scanner;
import javax.swing.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.InputMismatchException;

public class TestDriver {
	public static void main(String[] args) {
		try {
				cls();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		catch(InterruptedException e) {
			System.out.println(e);	
		}

		House house = new House(1);
		Player player1 = new Player("Player 1");
		Scanner sc = new Scanner(System.in);
		
		int num_players = 1;
		int game_number = 1;

		System.out.println("Welcome to Blackjack, " + player1.getName());

		while(continueToPlay(sc, game_number, player1, house)) {
			house.concealHoleCard();
			System.out.println("Enter wager:");

			try {
				while(!player1.newBet(sc.nextDouble())) {
					System.out.println("Enter wager:");
				}
			}
			catch(InputMismatchException e) {
				System.out.println(e);
			}


			initialDeal(player1, house, 2);
			System.out.println("\nYour initial hand is:\n" + player1.getInfo());

			while(house.getTurn() <= num_players) {
				int turn_temp = house.getTurn();

				if(house.getTurn() != num_players) {
					System.out.println("Enter command: hold, hit, hands, wallet, ace, or bet");

					while(turnOver(turn_temp, house) == false) {
						switch(sc.next().toLowerCase()) {
							case "hold" : 
								if(player1.getHand().getValue() > 21) {
									System.out.println("You bust");									
									house.nextTurn();
									break;
								}	
								house.nextTurn();
								System.out.println("\nYou hold");
								break;
							case "hit" :
								player1.hit(house.deal());
								System.out.println("\nNew card: " + player1.lookAtCard());
								break;
							case "hands" :
								System.out.println("\nYour hand:\n" + player1.getInfo());
								System.out.println("\nHouse hand:\n" + house.getInfo());
								break;
							case "wallet" :
								System.out.println(String.format("\nWallet contents: $%.2f", player1.getWallet()));
								break;
							case "bet" :
								System.out.println(String.format("\nCurrent bet: $%.2f", player1.getBet()));
								break;
							case "ace" :
								System.out.println("Switching values of aces");
								LinkedList<Card> aces = player1.aceGet();
								if(aces.size() == 0) {
									System.out.println("No aces in hand");
									break;
								}
								else {
									for(Card ace : aces) {
										System.out.println("Change value of " + ace.getName() + " of " + ace.getSuit()+ "?");
										switch(sc.next().toLowerCase()) {
											case "yes" : 
												if(ace.getValue() == 11) {
													ace.setValue(1);
													System.out.println("The value of " + ace.getName() + " of " + ace.getSuit() + " is now 1");
												}
												else {
													ace.setValue(11);
													System.out.println("The value of " + ace.getName() + " of " + ace.getSuit() + " is now 11");
												}
											case "no" :
												continue;
										}
									}
									break;
								}
							default :
								System.out.println("\nInvalid command. Try again.");
								break;
						}	
					}
				}

				else {
					while(house.stayOrHit()) {
						house.hit(house.deal());
						System.out.println("House receives: " + house.lookAtCard());
					}
					System.out.println("House holds");
					house.nextTurn();
					house.revealHoleCard();
					System.out.println("\nFinal hands:\nYour hand:\n" + player1.getInfo() + "\nHouse hand:\n" + house.getInfo());

					switch(determineWinner(player1, house)) {
						case 0 : 	System.out.println("\nYou win");
									player1.winBet(2);
									break;
						case 1 :	System.out.println("\nHouse wins") ;
									player1.loseBet();
									break;
					}
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
		int house_score = house.getHand().getValue() + house.getHand().getHoleValue();

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
							player.newHand();
							house.newHand();
							house.newDeck();
							/*try {
								Runtime.getRuntime().exec("cls");
							}
							catch(IOException e) {
								System.out.println(e);
							}*/
							

							try {
								cls();
							}
							catch(IOException e) {
								System.out.println(e);
							}
							catch(InterruptedException e) {
								System.out.println(e);	
							}
							break;
			case "no" : 	on_off_switch = false;
							break;
		}
		return on_off_switch;
	}

	// Clear console screen in Windows.
	public static void cls() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start();
	}
}