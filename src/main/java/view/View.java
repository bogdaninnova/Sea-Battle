package view;

import java.awt.*;

import javax.swing.*;

import constants.Constants;

public class View {

	private Frame frame;

	public View() {
		
		UIManager.put("Button.font", Constants.WORD_FONT_16);
		UIManager.put("Label.font", Constants.WORD_FONT_16);
		UIManager.put("RadioButton.font", Constants.WORD_FONT_16);
		UIManager.put("CheckBox.font", Constants.WORD_FONT_16);
		UIManager.put("TextField.font", Constants.WORD_FONT_16);
		UIManager.put("ComboBox.font", Constants.WORD_FONT_16);
		UIManager.put("TextArea.font", Constants.WORD_FONT_16);
		UIManager.put("List.font", Constants.WORD_FONT_16);
		
		frame = new Frame();
		
	}
	
	public static void setOnCenter(Window frame) {
		frame.setLocation(
				(Constants.SCREEN_WIDTH - frame.getWidth()) / 2,
				(Constants.SCREEN_HEIGHT - frame.getHeight()) / 2);
	}
	
	public static JPanel createFlowPanel(Component... c) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		for (int i = 0; i < c.length; i++) {
			gbc.gridx = i;
			panel.add(c[i], gbc);
		}
		return panel;
	}
	
	public static JPanel createDownPanel(Component... c) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		for (int i = 0; i < c.length; i++) {
			gbc.gridy = i;
			panel.add(c[i], gbc);
		}
		return panel;
	}
	
	public Frame getFrame() {
		return frame;
	}
}
