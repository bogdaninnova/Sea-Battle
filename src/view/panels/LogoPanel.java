package view.panels;

import java.awt.*;
import javax.swing.*;

public class LogoPanel extends AbstractMenuPanel {

	public LogoPanel(ImageIcon imageIcon) {
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel logo = new JLabel();
		logo.setIcon(imageIcon);
		logo.setOpaque(false);
		panel.add(logo, c);
		setOpaque(false);
	    setLayout(new GridBagLayout());
		add(panel, c);
	}
}
