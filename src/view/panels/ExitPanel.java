package view.panels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import constants.Constants;
import controller.Controller;

@SuppressWarnings("serial")
public class ExitPanel extends AbstractMenuPanel {

	public ExitPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Constants.MENU_COLOR);
		panel.setPreferredSize(Constants.PANEL_DIMENSION);
		
		GridBagConstraints c = new GridBagConstraints();
		
		panel.setLayout(new GridBagLayout());
		
		c.gridwidth = 2;

		
		JLabel label = new JLabel("Do you realy want to exit?");
		label.setFont(Constants.WORD_FONT_42);
		
		c.insets = new Insets(2 * Constants.BIG_INTERVAL, 2 * Constants.INTERVAL,
				panel.getPreferredSize().height - 4 * Constants.BIG_INTERVAL -
				label.getPreferredSize().height - 
				Constants.buttonDimension.height,
				2 * Constants.INTERVAL);
		
		panel.add(label, c);
		c.gridwidth = 1;
		c.gridy = 1;
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBackground(Constants.BLANK_COLOR);
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, Constants.INTERVAL, 0, Constants.INTERVAL);
		buttonPanel.add(getExitButton(), c);
		c.gridx = 1;
		buttonPanel.add(getCancelButton(), c);
		
		c.insets = new Insets(Constants.INTERVAL, 0, Constants.INTERVAL, 0);
		c.gridy = 3;
		c.gridx = 0;
		c.gridwidth = 3;
		panel.add(buttonPanel, c);

		setLayout(new GridBagLayout());
		c.insets = new Insets(0, 0, 0, 0);
		add(panel, c);
		
		setOpaque(false);
	}
	
	private JButton getCancelButton() {
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(Constants.buttonDimension);
		cancelButton.setFont(Constants.WORD_FONT_28);
		
		cancelButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if (Controller.getView().getFrame().isGame)
	        		Controller.getView().getFrame().openOnly(Constants.GAME_PANEL_KEY, true);
	        	else
	        		Controller.getView().getFrame().openOnly(Constants.EXIT_PANEL_KEY, false);
			}
		});
		return cancelButton;
	}
	
	private JButton getExitButton() {
		JButton exitButton = new JButton("Exit");
		exitButton.setPreferredSize(Constants.buttonDimension);
		exitButton.setFont(Constants.WORD_FONT_28);
		exitButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	System.exit(0);
			}
		});
		return exitButton;
	}
	
}
