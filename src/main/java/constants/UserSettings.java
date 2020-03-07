package constants;

import java.util.prefs.Preferences;

public class UserSettings {

	private Preferences prefs = Preferences.userNodeForPackage(constants.Constants.class);
	
	private static final String PREF_DELAY = "DELAY_VALUE";
	private static final String PREF_IS_FULLSCREEN = "IS_FULLSCREEN_VALUE";
	
	public UserSettings() {
		setDelay(Integer.parseInt(prefs.get(PREF_DELAY, "500")));
		setFullScreen(Boolean.parseBoolean(prefs.get(PREF_IS_FULLSCREEN, "true")));
	}
	
	private int delay;
	private boolean isFullScreen;
	public int getDelay() {
		return delay;
	}
	
	public void setDelay(int delay) {
		prefs.put(PREF_DELAY, Integer.toString(delay));
		this.delay = delay;
	}
	
	public boolean isFullScreen() {
		return isFullScreen;
	}
	
	public void setFullScreen(boolean isFullScreen) {
		prefs.put(PREF_IS_FULLSCREEN, String.valueOf(isFullScreen));
		this.isFullScreen = isFullScreen;
	}
}
