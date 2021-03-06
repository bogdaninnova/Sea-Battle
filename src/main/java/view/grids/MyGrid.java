package view.grids;

import org.apache.log4j.Logger;

public class MyGrid extends Grid {

	public int shipsLeft = 20;

	static Logger logger = Logger.getLogger(MyGrid.class.getName());

	public MyGrid() {
		logger.debug("My Grid created");
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
}
