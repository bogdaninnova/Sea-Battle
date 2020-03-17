package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import view.grids.EnemyGrid;
import view.grids.MyGrid;
import view.grids.NewGrid;
import view.panels.interfaces.Observable;
import view.panels.interfaces.Observer;

@Component
public class ModelBean implements Observable {
	
	private List<Observer> observers = new ArrayList<>();
	public Enemy enemy;
	public MyGrid myGrid;
	public NewGrid newGrid;
	public EnemyGrid enemyGrid;

	private boolean isMyShot = true;
	
	public ModelBean() {
		System.out.println("ModelBeen created");
	}

	public void initiate() {
		enemy = new Enemy();
		myGrid = new MyGrid();
		newGrid = new NewGrid();
		enemyGrid = new EnemyGrid();

		addObserver(enemyGrid);
		addObserver(myGrid);
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
        observers.forEach(view.panels.interfaces.Observer::update);
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