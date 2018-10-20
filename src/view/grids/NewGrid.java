package view.grids;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;
import view.panels.StartPanel;
import view.panels.interfaces.Observable;
import view.panels.interfaces.Observer;

public class NewGrid extends Grid implements Observable {
	
	private List<Observer> observers = new ArrayList<>();
	private boolean rotate = false;

	
	public NewGrid() {
		setMouseAction();
	}
	
	private StartPanel getPanel() {
		return ((StartPanel) observers.get(0));
	}
	
	private void mousePressed(int x, int y) {
		
		x = getCoordinatesOnGrid(x);
		y = getCoordinatesOnGrid(y);
		
		if ((x != -1) && (y != -1)) {
			setShip(x, y, getPanel().chooseShip.getSelectedIndex() + 1, rotate, Cell.SHIP);
			notifyObservers();
		}
	}

	private void mouseMoved(int x, int y) {
		x = getCoordinatesOnGrid(x);
		y = getCoordinatesOnGrid(y);
		
		if ((x != -1) && (y != -1)) 
			if (!isShipBorder(x, y, getPanel().chooseShip.getSelectedIndex() + 1, rotate)) {
				restore(Cell.DEAD, Cell.WATER);
				setShip(x, y, getPanel().chooseShip.getSelectedIndex() + 1, rotate, Cell.DEAD);
				notifyObservers();
			} else {
				restore(Cell.DEAD, Cell.WATER);
				notifyObservers();
			}
	}
	
	private void setMouseAction() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(SwingUtilities.isLeftMouseButton(arg0)) {
					NewGrid.this.mousePressed(arg0.getX(), arg0.getY());
				} else {
					rotate = !rotate;
					NewGrid.this.mouseMoved(arg0.getX(), arg0.getY());
				}
				notifyObservers();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				restore(Cell.DEAD, Cell.WATER);
				notifyObservers();
			}
		});
		
		super.addMouseMotionListener(new MouseMotionAdapter(){
			@Override
			public void mouseMoved(MouseEvent arg0) {
				NewGrid.this.mouseMoved(arg0.getX(), arg0.getY());
			} 
		});
	}
	
	public void autoSetting() {
		super.autoSetting();
		notifyObservers();
	}
	
	@Override
    public void addObserver(Observer... obs) {
        Collections.addAll(observers, obs);
    }
    
    @Override
    public boolean removeObserver(Observer o) {
        return observers.remove(o);
    }
 
    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }
	
}