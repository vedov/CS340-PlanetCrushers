package gui;
/** @author Vedad Vreto 
 * */
import java.io.File;
import java.io.IOException;
import javax.swing. *;
import java.awt. *;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import gui.GameGUI;
import logic.Settings;

public class SettingsGUI extends JFrame {
  private JFrame settings_frame;
  private JPanel settings_panel;
  private ImageIcon settings_icon;
  private JButton hard;
  private JButton normal;
  private JLabel settings_title;
  private JLabel about_text;
  private Font title_font;
  private Boolean difficulty;
  private int row_size, column_size;

  /**
   * CREATE THE GUI
   */
  public void BuildGUI() {
    settings_frame = new JFrame("PLANET CRUSHERS");
    settings_panel = new JPanel();
    settings_icon = new ImageIcon(MenuGUI.class.getResource("/res/asset8.png"));
    hard = new JButton();
    normal = new JButton();
    settings_title = new JLabel();
    about_text = new JLabel();
    /**
     * SET PANEL BACKGROUND IMAGE
     */
    settings_panel = new JPanel() {
      public void paintComponent(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(
        MenuGUI.class.getResource("/res/asset7.png"));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
      }
    };

    settings_panel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();


    /**
     * TITLE ATTRIBUTES
     */

    settings_title.setText("SELECT DIFFICULTY");
    settings_title.setFont(MenuGUI.getTITLE_FONT());
    settings_title.setForeground(Color.white);
    c.gridx = 0;
    c.gridy = 0;
    c.weighty = 5;
    settings_panel.add(settings_title, c);

    /**
     * BUTTON ATTRIBUTES
     */
    
    normal.setText("NORMAL");
    normal.setFocusPainted(false);
    normal.setContentAreaFilled(false);
    normal.setBorderPainted(false);
    normal.setForeground(Color.white);
    normal.setFont(MenuGUI.getTITLE_FONT().deriveFont(20f));
    c.gridx = 0;
    c.gridy++;
    c.weighty = 2;
    settings_panel.add(normal, c);

    
    hard.setText("HARD");
    hard.setFocusPainted(false);
    hard.setContentAreaFilled(false);
    hard.setBorderPainted(false);
    hard.setForeground(Color.white);
    hard.setFont(MenuGUI.getTITLE_FONT().deriveFont(20f));
    c.gridx = 0;
    c.gridy++;
    c.weighty = 2;
    settings_panel.add(hard, c);

    about_text.setText("2021 VEDAD VRETO");
    about_text.setFont(new Font("Arial", Font.BOLD, 8));
    about_text.setForeground(Color.white);
    c.gridx = 0;
    c.gridy++;
    settings_panel.add(about_text, c);

    c.weightx = 0;
    c.gridy = 0;
    c.weighty = 1;
    c.gridheight = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.VERTICAL;

    /**
     * DIFFICULTY SETTER 			
     */
    hard.addMouseListener(new MouseAdapter() {@Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Settings.setMode(true);
        Settings.setRowSize(8);
        Settings.setColumnSize(12);
        new GameGUI();
        settings_frame.dispose();
      }@Override
      public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        hard.setIcon(settings_icon);
        hard.setForeground(Color.decode("#d66bc2"));
      }@Override
      public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        hard.setIcon(null);
        hard.setForeground(Color.white);
      }
    });

    /**
     * MENU SETTINGS AND HOVER EFFECTS
     */
    normal.addMouseListener(new MouseAdapter() {@Override
      public void mouseClicked(MouseEvent e) {
    	super.mouseClicked(e);
    	Settings.setMode(false);
    	Settings.setRowSize(6);
    	Settings.setColumnSize(10);
        new GameGUI();
        settings_frame.dispose();

      }@Override
      public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        normal.setIcon(settings_icon);
        normal.setForeground(Color.decode("#d66bc2"));
      }@Override
      public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        normal.setIcon(null);
        normal.setForeground(Color.white);
      }
    });

    settings_frame.setIconImage(settings_icon.getImage());
    settings_frame.getContentPane().add(settings_panel);
    settings_frame.pack();
    settings_frame.setSize(400, 400);
    settings_frame.setLocation(300, 100);
    settings_frame.setVisible(true);
    settings_frame.setResizable(false);
    settings_frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }
  
  public SettingsGUI() {
    BuildGUI();
  }

  public static void main(String[] args) {
    new SettingsGUI();
  }

}