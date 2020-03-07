package view.panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import constants.Constants;
import controller.Controller;

public class SettingsPanel extends AbstractMenuPanel  {
	

	
	private JCheckBox checkBoxIsFullScreen = new JCheckBox();
	
	private JSlider delaySlider = new JSlider(
			JSlider.HORIZONTAL, Constants.DELAY_MIN, Constants.DELAY_MAX, 0);
	
	private JTextField delayValue = new JTextField();
	
	public SettingsPanel() {
		
		reset();
		JPanel panel = new JPanel();
		panel.setPreferredSize(Constants.PANEL_DIMENSION);
		panel.setBackground(Constants.MENU_COLOR);
		GridBagConstraints c = new GridBagConstraints();
		panel.setLayout(new GridBagLayout());

		JLabel label = new JLabel("Settings");
		label.setFont(Constants.WORD_FONT_42);
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 3;
		c.insets = new Insets(2 * Constants.BIG_INTERVAL, 2 * Constants.INTERVAL,
				panel.getPreferredSize().height - 4 * Constants.BIG_INTERVAL -
				label.getPreferredSize().height - 
				3 * Constants.buttonDimension.height,
				2 * Constants.INTERVAL);
		
		panel.add(label, c);
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 0);
		c.gridy = 1;
		
		JLabel fullScreenLabel = new JLabel("FullScreen");
		
		fullScreenLabel.setPreferredSize(Constants.buttonDimension);
		fullScreenLabel.setFont(Constants.WORD_FONT_28);
		checkBoxIsFullScreen.setBackground(Constants.BLANK_COLOR);
		
		panel.add(fullScreenLabel, c);
		c.gridx = 1;
		panel.add(checkBoxIsFullScreen, c);
		
		c.gridy = 2;
		c.gridx = 0;
		JLabel delayLabel = new JLabel("Delay: ");
		delayLabel.setPreferredSize(Constants.buttonDimension);
		delayLabel.setFont(Constants.WORD_FONT_28);
		delaySlider.setPreferredSize(Constants.smallButtonDimension);
		delaySlider.addChangeListener(new SliderListener());

		delaySlider.setBackground(new Color(0, 0, 0));

		panel.add(delayLabel, c);
		c.gridx = 1;
		panel.add(delaySlider, c);
		c.gridx = 2;
		delayValue.setPreferredSize(Constants.smallButtonDimension);
		delayValue.setBackground(new Color(0, 0, 0));
		delayValue.setEnabled(false);
		panel.add(delayValue, c);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBackground(Constants.BLANK_COLOR);
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, Constants.INTERVAL, 0, Constants.INTERVAL);
		buttonPanel.add(getSaveButton(), c);
		c.gridx = 1;
		buttonPanel.add(getCancelButton(), c);
		
		c.insets = new Insets(Constants.BIG_INTERVAL, 0, Constants.BIG_INTERVAL, 0);
		c.gridy = 3;
		c.gridx = 0;
		c.gridwidth = 3;
		panel.add(buttonPanel, c);
		
		
        setLayout(new GridBagLayout());
        c.insets = new Insets(0, 0, 0, 0);
		add(panel, c);
		
		setBackground(Constants.BLANK_COLOR);
	}
	
	class SliderListener implements ChangeListener {
		
	    public void stateChanged(ChangeEvent e) {
	        JSlider source = (JSlider)e.getSource();
	        if (!source.getValueIsAdjusting()) {
	            delayValue.setText(source.getValue() + " ms");
	        }    
	    }
	}
	
	private JButton getCancelButton() {
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(Constants.buttonDimension);
		cancelButton.setFont(Constants.WORD_FONT_28);
		cancelButton.addActionListener(ae -> {
            Controller.getView().getFrame().openOnly(Constants.LOGO_PANEL_KEY, false);
            reset();
        });
		return cancelButton;
	}
	
	private JButton getSaveButton() {
		JButton exitButton = new JButton("Save");
		exitButton.setPreferredSize(Constants.buttonDimension);
		exitButton.setFont(Constants.WORD_FONT_28);
		exitButton.addActionListener(ae -> {
            Controller.getView().getFrame().openOnly(Constants.LOGO_PANEL_KEY, false);
            Constants.USER_SETTINGS.setDelay(delaySlider.getValue());
            Constants.USER_SETTINGS.setFullScreen(checkBoxIsFullScreen.isSelected());
            reset();
        });
		return exitButton;
	}
	
	private void reset() {
		checkBoxIsFullScreen.setSelected(Constants.USER_SETTINGS.isFullScreen());
		delaySlider.setValue(Constants.USER_SETTINGS.getDelay());
		delayValue.setText(delaySlider.getValue() + " ms");
	}
	
}
