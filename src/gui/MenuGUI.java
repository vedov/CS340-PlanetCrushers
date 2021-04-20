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

public class MenuGUI extends JFrame {
  private JFrame menu_frame;
  private JPanel menu_panel;
  private ImageIcon menu_icon;
  private JButton menu_start;
  private JButton menu_settings;
  private JLabel menu_title;
  private JLabel about_text;
  private static Font title_font;

 /**
  * CREATE GUI
  */
  public void BuildGUI() {
    menu_frame = new JFrame("PLANET CRUSHERS");
    menu_panel = new JPanel();
    menu_icon = new ImageIcon(MenuGUI.class.getResource("/res/asset8.png"));
    menu_start = new JButton();
    menu_settings = new JButton();
    menu_title = new JLabel();
    about_text = new JLabel();

    /**
     * SET PANEL BACKGROUND IMAGE
     */
    menu_panel = new JPanel() {
      public void paintComponent(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(
        MenuGUI.class.getResource("/res/asset7.png"));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
      }
    };

    menu_panel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    /**
     * ADD CUSTOM FONT FOR TITLE
     */
    try {
      setTITLE_FONT(Font.createFont(Font.TRUETYPE_FONT, new File("src\\res\\GAMERIA.ttf")).deriveFont(35f));
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\res\\GAMERIA.ttf")));

    } catch(FontFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch(IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    /**
     * TITLE ATTRIBUTES
     */

    menu_title.setText("PLANET CRUSHERS");
    menu_title.setFont(getTITLE_FONT());
    menu_title.setForeground(Color.white);
    c.gridx = 0;
    c.gridy = 0;
    c.weighty = 5;
    menu_panel.add(menu_title, c);

    /**
     * BUTTON ATTRIBUTES
     */
    
    menu_start.setText("START");
    menu_start.setFocusPainted(false);
    menu_start.setContentAreaFilled(false);
    menu_start.setBorderPainted(false);
    menu_start.setForeground(Color.white);
    menu_start.setFont(getTITLE_FONT().deriveFont(20f));
    c.gridx = 0;
    c.gridy++;
    c.weighty = 2;
    menu_panel.add(menu_start, c);

    menu_settings.setText("SETTINGS");
    menu_settings.setFocusPainted(false);
    menu_settings.setContentAreaFilled(false);
    menu_settings.setBorderPainted(false);
    menu_settings.setForeground(Color.white);
    menu_settings.setFont(getTITLE_FONT().deriveFont(20f));
    c.gridx = 0;
    c.gridy++;
    c.weighty = 2;
    menu_panel.add(menu_settings, c);

    about_text.setText("2021 VEDAD VRETO");
    about_text.setFont(new Font("Arial", Font.BOLD, 8));
    about_text.setForeground(Color.white);
    c.gridx = 0;
    c.gridy++;
    menu_panel.add(about_text, c);

    c.weightx = 0;
    c.gridy = 0;
    c.weighty = 1;
    c.gridheight = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.VERTICAL;

    /**
     * START THE MENU ON CLICK AND HOVER EFFECTS 			
     */
    menu_start.addMouseListener(new MouseAdapter() {@Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Settings.setNormalMode(false);
        new GameGUI();
        menu_frame.dispose();
      }@Override
      public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        menu_start.setIcon(menu_icon);
        menu_start.setForeground(Color.decode("#d66bc2"));
      }@Override
      public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        menu_start.setIcon(null);
        menu_start.setForeground(Color.white);
      }
    });

    /**
     * MENU SETTINGS AND HOVER EFFECTS
     */
    menu_settings.addMouseListener(new MouseAdapter() {@Override
      public void mouseClicked(MouseEvent e) {
    	
        super.mouseClicked(e);
        new SettingsGUI();
        menu_frame.dispose();

      }@Override
      public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        menu_settings.setIcon(menu_icon);
        menu_settings.setForeground(Color.decode("#d66bc2"));
      }@Override
      public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        menu_settings.setIcon(null);
        menu_settings.setForeground(Color.white);
      }
    });

    menu_frame.setIconImage(menu_icon.getImage());
    menu_frame.getContentPane().add(menu_panel);
    menu_frame.pack();
    menu_frame.setSize(400, 400);
    menu_frame.setLocation(300, 100);
    menu_frame.setVisible(true);
    menu_frame.setResizable(false);
    menu_frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  public MenuGUI() {
    BuildGUI();
  }

  public static void main(String[] args) {
    new MenuGUI();
  }

  public static Font getTITLE_FONT() {
	return title_font;
  }

  public void setTITLE_FONT(Font title_font) {
	this.title_font = title_font;
  }

}