package view.panels;
import java.awt.*;
import javax.swing.*;
import constants.Constants;
import controller.Controller;
import view.panels.interfaces.Observer;

public class GamePanel extends AbstractMenuPanel implements Observer {

	public GamePanel() {

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(
			20 * Constants.CELL_SIZE + 4 * Constants.INTERVAL, 10 * Constants.CELL_SIZE));
		
        setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		panel. setLayout(new GridBagLayout());
		c.insets = new Insets(0, Constants.INTERVAL, 0, Constants.INTERVAL);
		panel.add(Controller.getModel().myGrid, c);
		c.gridx = 1;
		panel.add(Controller.getModel().enemyGrid, c);
		
		Controller.getModel().enemyGrid.addObserver(this);
		Controller.getModel().myGrid.addObserver(this);
		
		c.insets = new Insets(0, 0, 0, 0);
		add(panel, c);
		
		setOpaque(false);
	}

	@Override
	public void update() {
		repaint();
	}
	
}
