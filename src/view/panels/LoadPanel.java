package view.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import constants.Constants;
import controller.Controller;

@SuppressWarnings("serial")
public class LoadPanel extends AbstractMenuPanel {
	
	public LoadPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Constants.MENU_COLOR);
		panel.setPreferredSize(Constants.PANEL_DIMENSION);
		GridBagConstraints c = new GridBagConstraints();
		panel.setLayout(new GridBagLayout());

		JLabel label = new JLabel("Load Game");
		label.setFont(Constants.WORD_FONT_42);
		c.insets = new Insets(2 * Constants.BIG_INTERVAL, 2 * Constants.INTERVAL,
				panel.getPreferredSize().height - 2 * 2 * Constants.BIG_INTERVAL -
				label.getPreferredSize().height - 
				Constants.buttonDimension.height,
				2 * Constants.INTERVAL);
		panel.add(label, c);
		
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBackground(Constants.BLANK_COLOR);
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, Constants.INTERVAL, 0, Constants.INTERVAL);
		buttonPanel.add(getLoadButton(), c);
		c.gridx = 1;
		buttonPanel.add(getCancelButton(), c);
		
		c.insets = new Insets(Constants.BIG_INTERVAL, 0, Constants.BIG_INTERVAL, 0);
		c.gridy = 1;
		c.gridx = 0;
		panel.add(buttonPanel, c);
	
		c.insets = new Insets(0, 0, 0, 0);
        setLayout(new GridBagLayout());
		add(panel, c);
		setOpaque(false);
	}
	
	
	private JButton getCancelButton() {//TODO in ExitPanel exist
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(Constants.buttonDimension);
		cancelButton.setFont(Constants.WORD_FONT_28);
		
		cancelButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Controller.getView().getFrame().openOnly(Constants.LOGO_PANEL_KEY, false);
			}
		});
		return cancelButton;
	}
	
	private JButton getLoadButton() {
		JButton loadButton = new JButton("Load");
		loadButton.setPreferredSize(Constants.buttonDimension);
		loadButton.setFont(Constants.WORD_FONT_28);
		loadButton.setEnabled(false);//TODO
		loadButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	//TODO Load
	        }
		});
		return loadButton;
	}
	
}
