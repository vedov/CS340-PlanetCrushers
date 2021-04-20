package gui;

/** @author Vedad Vreto 
 * */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import logic.Board;
import logic.Settings;

import java.awt.*;

public class BoardGUI extends JFrame implements ActionListener{
	public static Board board;
	public static int score;
	public int moves;
	public static int row_size;
	public static int column_size;
	public boolean selected;
	public boolean difficulty;
	public static int selectedx = -1;
	public static int selectedy = -1;
	public JFrame game_frame;
	public JPanel game_board;
	public JPanel main_panel;
	public JPanel info_panel;
	public JLabel score_text;
	public JLabel moves_text;
	public JLabel title_text;
	public static JButton [][] board_planets;
	public ImageIcon game_logo;  
	public static ImageIcon [] planet_icons;
	public ImageIcon [] planet_icons_active;
	public URL [] planet_icons_paths;
	public URL [] planet_icons_paths_active;
	public static Color [] colors;

    /**
     * DEFAULT CONSTRUCTOR
     */
    public BoardGUI() {
    	game_frame = new JFrame();
    	game_logo = new ImageIcon(BoardGUI.class.getResource("/res/asset8.png"));
    	
    	/**
    	 * GET DIFFICULTY BASED ON SELECTION IN MENU
    	 */
    	difficulty = Settings.getMode();
    	row_size = Settings.getRowSize();
    	column_size = Settings.getColumnSize();
    	
    	/**
    	 * CREATE BOARD BASED ON DIFFICULTY
    	 */  	
    	board = new Board(row_size,column_size);
    	
    	board_planets = new JButton[row_size][column_size]; 	
    	planet_icons = new ImageIcon[6];
    	planet_icons_active = new ImageIcon[6];
    	planet_icons_paths = new URL[6];
    	planet_icons_paths_active = new URL[6];
    	
    	main_panel = new JPanel();
    	
    	info_panel = new JPanel(){
    	      public void paintComponent(Graphics g) {
    	          Image img = Toolkit.getDefaultToolkit().getImage(
    	          MenuGUI.class.getResource("/res/asset11.png"));
    	          g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    	        }
    	      };
    	
    	colors =  new Color[6];
    	score = 0;	
    	selected = false;
    	
    	/**
    	 * SET AVAILABLE MOVES BASED ON DIFFICULTY
    	 */
    	if(difficulty == true) { 
    		moves = 35;
    		System.out.println("Difficulty: HARD");
    		System.out.println("Row Size: " + row_size);
    		System.out.println("Column Size: " + column_size);
    	}
    	else { 
    		moves = 25;
    		System.out.println("Difficulty: NORMAL");
    		System.out.println("Row Size: " + row_size);
    		System.out.println("Column Size: " + column_size);
    		}
    	
    	title_text = new JLabel("PLANET CRUSHERS");
    	
    	title_text.setForeground(Color.white);
    	score_text = new JLabel("Score "+score);
    	moves_text = new JLabel("Moves "+moves);
    	
    	title_text.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    	score_text.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    	moves_text.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    	
    	title_text.setFont(MenuGUI.getTITLE_FONT().deriveFont(30f));
    	score_text.setFont(MenuGUI.getTITLE_FONT().deriveFont(18f));
    	moves_text.setFont(MenuGUI.getTITLE_FONT().deriveFont(18f));
    	
    	title_text.setHorizontalAlignment(JLabel.CENTER);
    	
    	/**
    	 * CREATE AND SET BOARD PANEL BACKGROUND IMAGE
    	 */
    	game_board = new JPanel() {
  	      public void paintComponent(Graphics g) {
	          Image img = Toolkit.getDefaultToolkit().getImage(
	          MenuGUI.class.getResource("/res/asset10.png"));
	          g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	        }
	      };
    	
    	/**
    	 * SET LAYOUT OF BOARD 
    	 */
    	game_board.setLayout(new GridLayout(row_size,column_size));
    	game_board.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    	
    	/**
    	 * BACKGROUND COLORS
    	 */
        colors[0] = Color.PINK;
        colors[1] = Color.ORANGE;
        colors[2] = Color.BLUE;
        colors[3] = Color.RED;
        colors[4] = Color.YELLOW;
        colors[5] = Color.GREEN;

        
        /**
         * GET ICON PATHS
         */
        planet_icons_paths[0] = getClass().getResource("/res/asset1.png");
        planet_icons_paths[1] = getClass().getResource("/res/asset2.png");
        planet_icons_paths[2] = getClass().getResource("/res/asset3.png");
        planet_icons_paths[3] = getClass().getResource("/res/asset4.png");
        planet_icons_paths[4] = getClass().getResource("/res/asset5.png");
        planet_icons_paths[5] = getClass().getResource("/res/asset6.png");
        
        /**
         * GET ACTIVE ICON PATHS
         */
        planet_icons_paths_active[0] = getClass().getResource("/res/asset1_active.png");
        planet_icons_paths_active[1] = getClass().getResource("/res/asset2_active.png");
        planet_icons_paths_active[2] = getClass().getResource("/res/asset3_active.png");
        planet_icons_paths_active[3] = getClass().getResource("/res/asset4_active.png");
        planet_icons_paths_active[4] = getClass().getResource("/res/asset5_active.png");
        planet_icons_paths_active[5] = getClass().getResource("/res/asset6_active.png");
        
        /**
         * SET ICONS
         */
        for(int i = 0 ; i < 6; i++) {
            planet_icons[i] = new ImageIcon(planet_icons_paths[i]);
        	planet_icons_active[i] = new ImageIcon(planet_icons_paths_active[i]);
        }
        
        /** 
         * CREATE GRID OF BUTTONS
         */
        for(int i=0; i < row_size; i++) {
            for(int j=0; j < column_size; j++ )
            {
                board_planets[i][j] = new JButton();
                board_planets[i][j].setOpaque(false);
                board_planets[i][j].setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                board_planets[i][j].setActionCommand(String.valueOf(i) + " " + String.valueOf(j));
                board_planets[i][j].addActionListener(this);
                board_planets[i][j].setFocusable(false);
                game_board.add(board_planets[i][j]);
                	
            }
        }

        
        /**
         * SET GAME LAYOUT
         */
        moves_text.setForeground(Color.white);
        score_text.setForeground(Color.white);
    	info_panel.setPreferredSize(new Dimension(50,50));
        info_panel.setBackground(Color.decode("#6B476B"));
        info_panel.setLayout(new BorderLayout());
        info_panel.add(score_text,BorderLayout.WEST);
        info_panel.add(moves_text,BorderLayout.EAST);
        info_panel.add(title_text,BorderLayout.CENTER);
        main_panel.setLayout(new BorderLayout());
        main_panel.add(info_panel,BorderLayout.NORTH);
        main_panel.add(game_board,BorderLayout.CENTER);
        game_frame.getContentPane().add(main_panel);
        setBoard();
        
	}
    
    /**
     * SET THE BOARD
     */
	public static void setBoard() {
		board.clearBoard();
		score = 0;
		while(board.clearedElement()) {
			board.fillBoard();
			board.eliminate();
		}
		refreshBoard();
	
	}
	
    /**
     * GET MAIN PANEL
     * @return
     */
    public JPanel getMainPanel() {
    	return main_panel;
    }
    
    /**
     * GET GAME ICON
     * @return
     */
    public ImageIcon getIcon() {
    	return game_logo;
    } 
    
    /**
     * GET GAME FRAME
     * @return
     */
    public JFrame getFrame() {
    	return game_frame;
    }
    
    /**
     * ACTION LISTENER ON CLICK
     */
    public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String[] src = e.getActionCommand().split(" ");
    	int ROW = Integer.parseInt(src[0]);
    	int COLUMN = Integer.parseInt(src[1]);
    	swapPiece(ROW,COLUMN);
    	
	}
    
    /**
     * SWAP SELECTED PIECE
     * @param row
     * @param column
     */
    public void swapPiece(int row, int column) {
    	
    	/**
    	 * FIRST SELECTED
    	 */
    	if(!selected) {
    		selected = true;
    		selectedx = row;
    		selectedy = column;
    		board_planets[row][column].setIcon(planet_icons_active[board.getPiece(row, column)]);
    		return;
    	}
    	
    	/**
    	 * CHECK IF MOVE IS VALID, IF NOT RESET SELECTED PIECE
    	 */
    	if(!(board.checkMatch(row, column, selectedx, selectedy))) {
    		selected = false;
    		selectedx = -1;
    		selectedy = -1;
    		System.out.println("Invalid Move");
    		return;
    	}
    	
    	score = Board.score;
    	moves--;
    	score_text.setText("Score "+score);
    	moves_text.setText("Moves "+moves);
    	board.swapPieces(row, column, selectedx, selectedy);
    	selected = false;
    	selectedx = -1;
    	selectedy = -1;

    	refreshBoard();
    	if(moves==0) endOfGame();
    }
    
    /**
     * REFRESH THE BOARD    
     */
    public static void refreshBoard() {
    	for(int i=0;i<row_size;i++) {
			for(int j=0;j<column_size;j++) {
				board_planets[i][j].setBackground(colors[board.getPiece(i, j)]);	
                board_planets[i][j].setIcon(planet_icons[board.getPiece(i, j)]);
			}
		}
    	
    }
    
    /**
     * END OF GAME SCREEN
     */
    public void endOfGame() {
    	
    	/**
    	 * SET BACKGROUND OF END SCREEN
    	 */
    	 UIManager UI=new UIManager();
    	 UI.put("OptionPane.background", Color.decode("#240046"));
    	 UI.put("Panel.background", Color.decode("#240046"));
    	 UI.put("OptionPane.messageForeground", Color.white);
    	 UI.put("Button.background", Color.decode("#240046"));
    	 UI.put("Button.font", MenuGUI.getTITLE_FONT().deriveFont(22f));
    	 UI.put("Button.border", BorderFactory.createLineBorder(Color.white));
    	 UI.put("Button.foreground",Color.white);
    	 
    	 
    	int check = JOptionPane.showConfirmDialog(null,
            		"Your Score is: "+ score +  " \nDo you want to Play again?","PLANET CRUSHERS", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, game_logo);

    	if(check == JOptionPane.YES_OPTION)
            {	
    			game_frame.dispose();
                new GameGUI();
            }
            else if(check == JOptionPane.NO_OPTION)
            	game_frame.dispose();
                System.exit(0);
    }
	
	public static void main(String[] args) {
		new BoardGUI();
	}

}
