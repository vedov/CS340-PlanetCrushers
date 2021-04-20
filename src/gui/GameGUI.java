package gui;
/** @author Vedad Vreto 
 * */
import javax.swing.*;


public class GameGUI {
    
    private JFrame game_frame;
	private BoardGUI game;
	private int moves;
	public void BuildGUI() {
	  	game = new BoardGUI();
    	game_frame = game.getFrame();    	
        game_frame.pack();
        game_frame.setTitle("PLANET CRUSHERS");
        game_frame.setIconImage(game.getIcon().getImage());
        game_frame.setSize(900,700);
        game_frame.setLocation(100,0);
        game_frame.setResizable(false);
        game_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_frame.setVisible(true);
	}
	
    public GameGUI(){
    	BuildGUI();
    }

    public static void main(String[] args) {
		new GameGUI();
	}
}
