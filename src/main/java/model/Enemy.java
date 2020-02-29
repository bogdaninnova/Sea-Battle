package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import constants.Constants;
import controller.Controller;
import view.grids.Cell;

public class Enemy {

	
	private Random rand = new Random();

	public void shot() {
		if (Controller.getModel().myGrid.shipsLeft != 0)
			if (!Controller.getModel().isMyShot()) {
				Constants.TIMER.schedule(new TimerTask() {
					public void run() {
						
						int[] shot = getSmartShot();
						Controller.getModel().setMyShot(
								!Controller.getModel().myGrid.setShot(shot[0], shot[1])); 
						
					}
				}, Constants.USER_SETTINGS.getDelay());
			}
	}
	
	private int[] getRandomShot() {
		int x, y;
		while (true) {
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			if (mayShoot(x, y))
				return new int[] {x, y};
		}
	}
	
	private int[] getSmartShot() {
		
		ArrayList<Integer> xList = new ArrayList<>();
		ArrayList<Integer> yList = new ArrayList<>();
		
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (Controller.getModel().myGrid.getArray()[i][j].equals(Cell.DAMAGE)) {
					xList.add(i);
					yList.add(j);
				}
		
		if (xList.size() == 0)
			return getRandomShot();
		
		if (xList.size() == 1)
			return getShotForOne(xList.get(0), yList.get(0));
		
		boolean r1 = rand.nextBoolean();
		boolean isFirst = true;
		boolean isSecond = true;
		
		if (yList.get(0) == yList.get(1)) {

			int x1 = xList.get(0) - 1;
			int x2 = xList.get(1) + 1;
			
			if (x1 == -1) 
				isFirst = false;
			if (isFirst)
				if (Controller.getModel().myGrid.getArray()[x1][yList.get(0)].equals(Cell.MISS))
					isFirst = false;
			
			if (isFirst)
				if (Controller.getModel().myGrid.getArray()[x1][yList.get(0)].equals(Cell.DAMAGE))
					x1--;
			
			if (x1 == -1) 
				isFirst = false;
			if (isFirst)
				if (Controller.getModel().myGrid.getArray()[x1][yList.get(0)].equals(Cell.MISS))
					isFirst = false;
			
			if (x2 == 10) 
				isSecond = false;
			if (isSecond)
				if (Controller.getModel().myGrid.getArray()[x2][yList.get(0)].equals(Cell.MISS))
					isSecond = false;
			
			if (isSecond)
				if (Controller.getModel().myGrid.getArray()[x2][yList.get(0)].equals(Cell.DAMAGE))
					x2++;
			if (x2 == 10) 
				isSecond = false;
			if (isSecond)
				if (Controller.getModel().myGrid.getArray()[x2][yList.get(0)].equals(Cell.MISS))
					isSecond = false;
			
			if (r1) {
				if (isFirst)
					return new int[] {x1, yList.get(0)};
				return new int[] {x2, yList.get(0)};
			} else {
				if (isSecond)
					return new int[] {x2, yList.get(0)};
				return new int[] {x1, yList.get(0)};
			}
		} else {
			
			int y1 = yList.get(0) - 1;
			int y2 = yList.get(1) + 1;
			
			if (y1 == -1) 
				isFirst = false;
			if (isFirst)
				if (Controller.getModel().myGrid.getArray()[xList.get(0)][y1].equals(Cell.MISS))
					isFirst = false;
			
			if (isFirst)
				if (Controller.getModel().myGrid.getArray()[xList.get(0)][y1].equals(Cell.DAMAGE))
					y1--;
			if (y1 == -1) 
				isFirst = false;
			if (isFirst)
				if (Controller.getModel().myGrid.getArray()[xList.get(0)][y1].equals(Cell.MISS))
					isFirst = false;
			
			
			if (y2 == 10) 
				isSecond = false;
			if (isSecond)
				if (Controller.getModel().myGrid.getArray()[xList.get(0)][y2].equals(Cell.MISS))
					isSecond = false;
			
			if (isSecond)
				if (Controller.getModel().myGrid.getArray()[xList.get(0)][y2].equals(Cell.DAMAGE))
					y2++;
			if (y2 == 10) 
				isSecond = false;
			if (isSecond)
				if (Controller.getModel().myGrid.getArray()[xList.get(0)][y2].equals(Cell.MISS))
					isSecond = false;
			
			
			if (r1) {
				if (isFirst)
					return new int[] {xList.get(0), y1};
				return new int[] {xList.get(0), y2};
			} else {
				if (isSecond)
					return new int[] {xList.get(0), y2};
				return new int[] {xList.get(0), y1};
			}
		}
	}
		
	private int[] getShotForOne(int x, int y) {
		while (true) {
			boolean r1 = rand.nextBoolean();
			boolean r2 = rand.nextBoolean();
			try {
				if (r1) {
					if (r2) {
						if (mayShoot(x-1, y)) 
							return new int[] {x-1, y};
					} else {
						if (mayShoot(x+1, y)) 
							return new int[] {x+1, y};
					}
				} else {
					if (r2) {
						if (mayShoot(x, y-1)) 
							return new int[] {x, y-1};
					} else {
						if (mayShoot(x, y+1)) 
							return new int[] {x, y+1};
					}
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
	}
	
	private boolean mayShoot(int x, int y) {
		return (Controller.getModel().myGrid.getArray()[x][y].equals(Cell.WATER) ||
				Controller.getModel().myGrid.getArray()[x][y].equals(Cell.SHIP));
	}
	
}
