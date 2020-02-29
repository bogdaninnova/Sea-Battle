package view.grids;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import controller.Controller;
import view.panels.interfaces.Observable;
import view.panels.interfaces.Observer;

public class EnemyGrid extends Grid implements Observable, Observer {

	private List<Observer> observers = new ArrayList<>();
	private Cell[][] hiddenArray = new Cell[10][10];
	public int shipsLeft = 20;
	
	public EnemyGrid() {

		hideArray();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				if (Controller.getModel().isMyShot()) {
					
					int x = getCoordinatesOnGrid(arg0.getX());
					int y = getCoordinatesOnGrid(arg0.getY());
					if ((x != -1) && (y != -1) && (array[x][y].equals(Cell.WATER))) {
						
						switch (hiddenArray[x][y]) {
							case SHIP :
								array[x][y] = Cell.DAMAGE;
								hiddenArray[x][y] = Cell.DAMAGE;
								shipsLeft--;
								
								Controller.getModel().setMyShot(true);
								break;
							default:
								array[x][y] = Cell.MISS;
								hiddenArray[x][y] = Cell.MISS;
								Controller.getModel().setMyShot(false);
								break;
						}
						
						if (isShipDead(x, y))
							setMissAroundDead();
						
						notifyObservers();
						
					}
				}
			}
			});
	}
	
	public void hideArray() {
		autoSetting();
		for (int i = 0; i < 10; i++)
            System.arraycopy(array[i], 0, hiddenArray[i], 0, 10);
		clear();
	}

	private boolean isShipDead(int x, int y) {
		
		if (this.isShipsAround(hiddenArray, x, y))
			return false;
		
		for (int i = x - 1; i <= x + 1; i++) 
			for (int j = y - 1; j <= y + 1; j++) 
				if ((i != -1) && (j != -1) && (i != hiddenArray.length) && (j != hiddenArray.length))
					if (hiddenArray[i][j].equals(Cell.DAMAGE)) {
						hiddenArray[i][j] = Cell.SOME;
						if (!isShipDead(i, j)) {
							restore(hiddenArray, Cell.SOME, Cell.DAMAGE);
							return false;
						}
					}
		
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (hiddenArray[i][j].equals(Cell.SOME)) {
					hiddenArray[i][j] = Cell.DEAD;
					array[i][j] = Cell.DEAD;
				}
		return true;
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

	@Override
	public void update() {
		notifyObservers();
	}
	
	
}