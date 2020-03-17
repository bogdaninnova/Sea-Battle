package constants;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.swing.ImageIcon;

public class Constants {

	public static final UserSettings USER_SETTINGS = new UserSettings();
	public static final String APPLICATION_NAME = "Sea Battle";
    public static int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static final int CELL_SIZE = getCellSize();

	private static int getCellSize() {
    	if (SCREEN_WIDTH > 1280)
    		return 1280 / 35;
    	return SCREEN_WIDTH / 35;
    		
    }
    
    public static final ImageIcon BACKGROUND = getBackground();
    
    public static final  ImageIcon TITLE_LOGO = getLogo("logo.gif");
    public static final  ImageIcon WIN_LOGO = getLogo("win.gif");
    public static final  ImageIcon LOSE_LOGO = getLogo("lose.gif");
    public static final String ICON = "icon.png";
   
	public static final Timer TIMER = new Timer();
    
	public static final Font WORD_FONT_16 = createFont();
	public static final Font WORD_FONT_28 =
			WORD_FONT_16.deriveFont((float)(SCREEN_HEIGHT / 39));
	
	public static final Font WORD_FONT_42 =
			WORD_FONT_16.deriveFont((float)(SCREEN_HEIGHT / 26));
	
    
	public static final Dimension buttonDimension = new Dimension(
			(int) (0.2 * SCREEN_WIDTH), (int) (0.05 * SCREEN_HEIGHT));
	
	public static final Dimension smallButtonDimension = new Dimension(
			(int) (0.08 * SCREEN_WIDTH), (int) (0.03 * SCREEN_HEIGHT));
	
	public static final Dimension PANEL_DIMENSION =
			new Dimension(17 * Constants.CELL_SIZE, 10 * Constants.CELL_SIZE);
	
	public static final int INTERVAL = SCREEN_WIDTH / 256;
	public static final int BIG_INTERVAL = 5 * INTERVAL;
	
	public static final Color BLANK_COLOR = new Color(0, 0, 0, 0);
	public static final Color MENU_COLOR = new Color(0, 190, 255, 50);
	
	public static final int DELAY_MIN = 0;
	public static final int DELAY_MAX = 2000;
	
    private static ImageIcon getBackground() {
    	ImageIcon bg =  new ImageIcon("background.png");
    	if (!USER_SETTINGS.isFullScreen()) return bg;
    	return new ImageIcon(
    			bg.getImage().getScaledInstance(SCREEN_WIDTH, -1, Image.SCALE_DEFAULT));
    }
    
    public static final int poinDiam = Constants.CELL_SIZE / 3;
    public static final Color shipColor = new Color(0, 0, 0, 150);
    public static final Color waterColor = new Color(0, 140, 255, 150);
    public static final Color deadColor = new Color(128, 128, 128, 150);
    public static final Color damageColor = new Color(255, 0, 0, 150);
    
    public static final String START_PANEL_KEY = "Start";
    public static final String SETTINGS_PANEL_KEY = "Settings";
    public static final String LOAD_PANEL_KEY = "Load";
    public static final String EXIT_PANEL_KEY = "Exit";
    public static final String LOGO_PANEL_KEY = "Logo";
    public static final String WIN_PANEL_KEY = "Win";
    public static final String LOSE_PANEL_KEY = "Lose";
    public static final String GAME_PANEL_KEY = "Game";
    public static final String MENU_PANEL_KEY = "Menu";
    		
	private static ImageIcon getLogo(String fileName) {
		
		final Color color = new Color(254, 255, 252);
	    				
		ImageFilter filter = new RGBImageFilter() {
			public int markerRGB = color.getRGB() | 0xFF000000;
			public final int filterRGB(int x, int y, int rgb) {
				if ( ( rgb | 0xFF000000 ) == markerRGB ) 
					return 0x40000000 & rgb;
				else 
					return rgb;
			}
		}; 

		Image image = Toolkit.getDefaultToolkit().getImage(fileName);
		ImageProducer ip = new FilteredImageSource(image.getSource(), filter);

		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(ip));	
	}
	
	private static Font createFont() {
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT,
					new File("ComicSans.ttf")).deriveFont((float)(SCREEN_HEIGHT / 57));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("ComicSans.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return font;
	}
}
