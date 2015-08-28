package view.grids;

import java.util.*;

import view.grids.Cell;
import view.panels.interfaces.Observable;
import view.panels.interfaces.Observer;

@SuppressWarnings("serial")
public class MyGrid extends Grid implements Observable, Observer {

	private List<Observer> observers = new ArrayList<Observer>();
	public int shipsLeft = 20;
	
	public boolean setShot(int x, int y) {
		boolean isShoot = false;
		if (array[x][y].equals(Cell.WATER)) 
			array[x][y] = Cell.MISS;
		else {
			array[x][y] = Cell.DAMAGE;
			shipsLeft--;
			isShoot = true;
		}
		if (isShipDead(x, y))
			setMissAroundDead();
		
		return isShoot;
	}
	
	private boolean isShipDead(int x, int y) {
		
		if (this.isShipsAround(array, x, y))
			return false;
		
		for (int i = x - 1; i <= x + 1; i++) 
			for (int j = y - 1; j <= y + 1; j++) 
				if ((i != -1) && (j != -1) && (i != array.length) && (j != array.length)) 
					if (array[i][j].equals(Cell.DAMAGE)) {
						array[i][j] = Cell.SOME;
						if (!isShipDead(i, j)) {
							restore(Cell.SOME, Cell.DAMAGE);
							return false;
						}
					}
		restore(Cell.SOME, Cell.DEAD);
		return true;
	}

	@Override
    public void addObserver(Observer... obs) {
		for (Observer observer : obs)
			observers.add(observer);
    }
    
    @Override
    public boolean removeObserver(Observer o) {
        return observers.remove(o);
    }
 
    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update();
    }	
	
	@Override
	public void update() {
		notifyObservers();
	}
}
