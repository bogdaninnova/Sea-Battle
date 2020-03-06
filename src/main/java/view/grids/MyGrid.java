package view.grids;

import java.util.*;

import org.apache.log4j.Logger;
import view.panels.interfaces.Observable;
import view.panels.interfaces.Observer;

public class MyGrid extends Grid implements Observable, Observer {

	private List<Observer> observers = new ArrayList<>();
	public int shipsLeft = 20;

	static Logger logger = Logger.getLogger(EnemyGrid.class);

	public MyGrid() {
		logger.debug("Enemies Grid created");
	}

	public boolean setShot(int x, int y) {
		if (array[x][y].equals(Cell.WATER)) {
			array[x][y] = Cell.MISS;
			return false;
		} else {
			array[x][y] = Cell.DAMAGE;
			shipsLeft--;
			if (isShipDead(x, y))
				setMissAroundDead();
			return true;
		}
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
		logger.debug("My Ship is dead");
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
