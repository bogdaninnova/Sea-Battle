package view.panels;

import java.awt.*;
import javax.swing.*;
import view.View;
import view.panels.interfaces.Observer;
import constants.Constants;
import controller.Controller;

public class StartPanel extends AbstractMenuPanel implements Observer {

	public JButton startButton = createStartButton();
	private final String[] ships = {"1-dimension", "2-dimension", "3-dimension", "4-dimension"};
	public JComboBox<String> chooseShip = new JComboBox<>(ships);
	public JTextField[] texts = createTexts();
	private JPanel shipsLeftPanel = shipsLeftPanel();
	
	public StartPanel() {
		
		Controller.getModel().newGrid.addObserver(this);

		chooseShip.setPreferredSize(new Dimension(
				Constants.smallButtonDimension.width * 2 + Constants.INTERVAL,
				Constants.smallButtonDimension.height));
		setOpaque(false);
        setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(Constants.BIG_INTERVAL, 0, Constants.BIG_INTERVAL, 0);

		add(createPanel(), c);
		c.gridx = 1;
		c.insets = new Insets(0, Constants.INTERVAL, 0, 0);
		add(Controller.getModel().newGrid, c);
	}
	
	private JPanel createPanel() {
		
		JPanel buttonsPanel = View.createFlowPanel(createCleanButton(), createAutoButton());

		buttonsPanel.setOpaque(false);
		
		JPanel panel = View.createDownPanel(
				chooseShip,
				new JLabel("<html><u><i>Right click to rotate</i>"),
				shipsLeftPanel,
				buttonsPanel,
				startButton);

		panel.setBackground(Constants.MENU_COLOR);
		if (Constants.SCREEN_WIDTH <= 1024)
			panel.setPreferredSize(new Dimension(
					buttonsPanel.getPreferredSize().width + 8 * Constants.INTERVAL,
					12 * Constants.CELL_SIZE));
		else
			panel.setPreferredSize(new Dimension(
					buttonsPanel.getPreferredSize().width + 8 * Constants.INTERVAL,
					10 * Constants.CELL_SIZE));
		return panel;
	}
	
	private JButton createStartButton() {
		JButton startButton = new JButton("Start");
		startButton.setEnabled(false);
		startButton.addActionListener(ae -> {
            Controller.getView().getFrame().openGamePanel();
            Controller.getModel().myGrid.setArray(Controller.getModel().newGrid.getArray());
        });
		startButton.setPreferredSize(new Dimension(
				Constants.smallButtonDimension.width * 2 + 5,
				Constants.smallButtonDimension.height));
		return startButton;
	}
	
	private JButton createAutoButton() {
		JButton autoButton = new JButton("Auto");
		autoButton.setPreferredSize(Constants.smallButtonDimension);
		autoButton.addActionListener(ae -> {
            Controller.getModel().newGrid.autoSetting();
            repaint();
        });
		return autoButton;
	}
	
	private JButton createCleanButton() {
		JButton cleanButton = new JButton("Clean");
		cleanButton.setPreferredSize(Constants.smallButtonDimension);
		cleanButton.addActionListener(ae -> {
            Controller.getModel().newGrid.clear();
            startButton.setEnabled(false);
            repaint();
        });
		return cleanButton;
	}
	
	private JTextField[] createTexts() {
		JTextField[] res = new JTextField[4];
		
		for (int i = 0; i < 4; i++) {
			JTextField field = new JTextField(1);
			field.setEditable(false);
			field.setCursor(null);
			field.setOpaque(false);
			field.setFocusable(false);
			
			res[i] = field;
		}
		return res;

	}
	
	/**
	 * @return panel with left ships 
	 */
	public JPanel shipsLeftPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		panel.add(new JLabel("<html><u>Ships left:"), c);
		panel.setBackground(Constants.BLANK_COLOR);
		for (int i = 0; i < 4; i++) {
			texts[i].setEditable(false);
			texts[i].setBackground(Constants.BLANK_COLOR);
			c.gridy = i + 1;
			c.gridx = 0;
			panel.add(new JLabel((i + 1) + "-dimension - "), c);
			c.gridx = 1;
			texts[i].setText(Integer.toString(Controller.getModel().newGrid.shipsLeft[i]));
			panel.add(texts[i], c);
		}
		return panel;
	}

	@Override
	public void update() {
		startButton.setEnabled(Controller.getModel().newGrid.isItAll());
		for (int i = 0; i < 4; i++) 
			texts[i].setText(Integer.toString(Controller.getModel().newGrid.shipsLeft[i]));
		repaint();
		
	}
}
