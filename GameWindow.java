import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
	final private String buttonText = "Click";

	public GameWindow() {
		super("blackjack");
	}

	public void addComponenets(Container pane) {
		JButton button = new JButton(buttonText);
		JPanel tabelPanel = new JPanel();
		pane.setLayout(new GroupLayout(pane));
		pane.setBackground(Color.green);
		pane.add(button);
	}

	public static void main(String[] args) {
		GameWindow game = new GameWindow();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.addComponenets(game.getContentPane());
		game.pack();
		game.setVisible(true);
	}
}