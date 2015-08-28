package model;

import java.util.ArrayList;
import java.util.List;

import view.grids.EnemyGrid;
import view.grids.MyGrid;
import view.grids.NewGrid;
import view.panels.interfaces.Observable;
import view.panels.interfaces.Observer;

public class Model implements Observable {
	
	private List<Observer> observers = new ArrayList<Observer>();
	public Enemy enemy = new Enemy();
	public MyGrid myGrid = new MyGrid();
	public NewGrid newGrid = new NewGrid();
	public EnemyGrid enemyGrid = new EnemyGrid();

	private boolean isMyShot = true;
	
	public Model() {
		addObserver(enemyGrid);
		addObserver(myGrid);
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
	
	public Enemy getEnemy() {
		return enemy;
	}

	public boolean isMyShot() {
		return isMyShot;
	}

	public void setMyShot(boolean isMyShot) {
		this.isMyShot = isMyShot;
		enemy.shot();
		notifyObservers();
	}
	
}