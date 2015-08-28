package view.panels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import constants.Constants;
import controller.Controller;

@SuppressWarnings("serial")
public class MenuPanel extends AbstractMenuPanel {

	public MenuPanel() {
		
		int indentX = (Constants.BACKGROUND.getIconHeight() -
				4 * Constants.buttonDimension.height) / 10;
		
		int indentY = 6 * Constants.INTERVAL;
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);

		panel.setPreferredSize(
				new Dimension(Constants.buttonDimension.width + 2 * indentY,
				(int) (Constants.BACKGROUND.getIconHeight())));
		
        setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		panel.setLayout(new GridBagLayout());

		c.insets = new Insets(indentX, indentY, indentX, indentY);
		panel.add(createButton(Constants.START_PANEL_KEY), c);
		c.gridy = 1;
		panel.add(createButton(Constants.LOAD_PANEL_KEY), c);
		c.gridy = 2;
		panel.add(createButton(Constants.SETTINGS_PANEL_KEY), c);
		c.gridy = 3;
		panel.add(createButton(Constants.EXIT_PANEL_KEY), c);
		
		c.insets = new Insets(0, 0, 0, 0);
		add(panel, c);
		setOpaque(false);

	}
	
	private JButton createButton(final String name) {
		JButton button = new JButton(name);
		button.setPreferredSize(Constants.buttonDimension);
		button.setFont(Constants.WORD_FONT_28);
		button.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Controller.getView().getFrame().openOnly(name, false);
			}
		});
		return button;
	}
	
}
