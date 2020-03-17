package view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;
import constants.Constants;
import controller.Controller;
import model.ModelBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import view.panels.*;
import view.panels.interfaces.Observer;

public class Frame extends JFrame implements Observer {

	private Map<String, AbstractMenuPanel> panelsList;
	private boolean isStarted = false;
	public boolean isGame = false;
	private ModelBean modelBeen;
	
	
	public Frame() {

		System.out.println("Frame created");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		modelBeen = context.getBean("modelBean", ModelBean.class);
		modelBeen.addObserver(this);

		panelsList = new HashMap<>();
			panelsList.put(Constants.START_PANEL_KEY, new StartPanel());
			panelsList.put(Constants.SETTINGS_PANEL_KEY, new SettingsPanel());
			panelsList.put(Constants.LOGO_PANEL_KEY, new LogoPanel(Constants.TITLE_LOGO ));
			panelsList.put(Constants.WIN_PANEL_KEY, new LogoPanel(Constants.WIN_LOGO ));
			panelsList.put(Constants.LOSE_PANEL_KEY, new LogoPanel(Constants.LOSE_LOGO ));
			panelsList.put(Constants.LOAD_PANEL_KEY, new LoadPanel());
			panelsList.put(Constants.EXIT_PANEL_KEY, new ExitPanel());
			panelsList.put(Constants.GAME_PANEL_KEY, new GamePanel());
			panelsList.put(Constants.MENU_PANEL_KEY, new MenuPanel());
			
		frameCreate();
		
		setActionsOnClick();
		
		add(panelsList.get(Constants.LOGO_PANEL_KEY), BorderLayout.CENTER);
		ViewBean.setOnCenter(this);
		pack();
		addDeleteKeyListener();
		
		if (Constants.USER_SETTINGS.isFullScreen()) 
			GraphicsEnvironment.getLocalGraphicsEnvironment().
					getDefaultScreenDevice().setFullScreenWindow(this);
	}
		
	public void closeLogo() {
		remove(panelsList.get(Constants.LOGO_PANEL_KEY));
		add(panelsList.get(Constants.MENU_PANEL_KEY), BorderLayout.WEST);
		add(panelsList.get(Constants.LOGO_PANEL_KEY), BorderLayout.CENTER);
		
		revalidate();
    	repaint();
	}
	
	private void frameCreate() {
		setTitle(Constants.APPLICATION_NAME);
		setResizable(false);
		setVisible(true);		
		setLayout(new BorderLayout());
	    setContentPane(new JLabel(Constants.BACKGROUND));
	    setBackground(new Color(0, 0, 0));
	    setLayout(new BorderLayout());
        setIconImage(Toolkit.getDefaultToolkit().getImage(Constants.ICON));
	    
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		pack();

	}
	
	public void openOnly(String panelsName, boolean isRemoveMenu) {
		
		boolean isEmpty = false;
		
			for (String name : panelsList.keySet()) {
				if (!name.equals(panelsName)) {
					remove(panelsList.get(name));
					panelsList.get(name).isOpen = false;
				} else
					if (panelsList.get(name).isOpen) {
						remove(panelsList.get(name));
						panelsList.get(panelsName).isOpen = false;
						isEmpty = true;
					} else {
						add(panelsList.get(panelsName), BorderLayout.CENTER);
						panelsList.get(panelsName).isOpen = true;
					}
			}
			if (isEmpty)
				add(panelsList.get(Constants.LOGO_PANEL_KEY), BorderLayout.CENTER);

			if (!isRemoveMenu)
				add(panelsList.get(Constants.MENU_PANEL_KEY), BorderLayout.WEST);
			
			revalidate();
        	repaint();
	}
	
	public void openGamePanel() {
		openOnly(Constants.GAME_PANEL_KEY, true);
		isGame = true;		
		revalidate();
    	repaint();
	}

	private void setActionsOnClick() {
		
		panelsList.get(Constants.WIN_PANEL_KEY).addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Frame.this.openOnly(Constants.LOGO_PANEL_KEY, true);
			}
		});
		
		panelsList.get(Constants.LOSE_PANEL_KEY).addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Frame.this.openOnly(Constants.LOGO_PANEL_KEY, true);
			}
		});
		
		panelsList.get(Constants.LOGO_PANEL_KEY).addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (!isStarted) {
					closeLogo();
					isStarted = true;
				}
			}
		});
	}

	@Override
	public void update() {
		
		if (modelBeen.myGrid.shipsLeft == 0) {
			openOnly(Constants.LOSE_PANEL_KEY, true);
			updatingAfterGame();
		}
				
		if	(modelBeen.enemyGrid.shipsLeft == 0) {
			openOnly(Constants.WIN_PANEL_KEY, true);
			updatingAfterGame();
		}
	}
	
	private void updatingAfterGame() {
		isStarted = false;
		isGame = false;
		modelBeen.newGrid.clear();
		modelBeen.myGrid.shipsLeft = 20;
		modelBeen.enemyGrid.shipsLeft = 20;
		modelBeen.enemyGrid.hideArray();
		panelsList.get(Constants.GAME_PANEL_KEY).repaint();
		modelBeen.setMyShot(true);
	}
	
	private void addDeleteKeyListener() {
		for (Component c : getComponents()) 
			addExitListener(c);
		addExitListener(this);
    }
	
	public void addExitListener(Component c) {
		c.setFocusable(true);
    	c.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
			    	if (isGame) {
			    		if (panelsList.get(Constants.EXIT_PANEL_KEY).isOpen)
			    			openOnly(Constants.GAME_PANEL_KEY, true);
			    		else
			    			openOnly(Constants.EXIT_PANEL_KEY, true);
			    	}
				}
			}
			public void keyReleased(KeyEvent event) {}
			public void keyTyped(KeyEvent event) {}
    	});
	}
	
}
