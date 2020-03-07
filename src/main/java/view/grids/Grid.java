package view.grids;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import constants.Constants;


public class Grid extends JPanel {


	
	protected Cell[][] array = new Cell[10][10];
	public int[] shipsLeft = new int[4];

	public Grid() {
		setOpaque(false);
		clear();
		setPreferredSize(
				new Dimension(10 * Constants.CELL_SIZE, 10 * Constants.CELL_SIZE));
	}
	
	public void paint(Graphics g) {
		
		   for (int j = 0; j < 10; j++)
			   for (int i = 0; i < 10; i++) {
				   
					switch(array[i][j]) {
						case SHIP   : g.setColor(Constants.shipColor); break;
						case DAMAGE : g.setColor(Constants.damageColor); break;
						case DEAD   : g.setColor(Constants.deadColor); break;
						default 	: g.setColor(Constants.waterColor); break;
					}
					g.fillRect (
							i * Constants.CELL_SIZE,
							j * Constants.CELL_SIZE,
							Constants.CELL_SIZE - 1,
							Constants.CELL_SIZE - 1);
				   
					if (array[i][j].equals(Cell.MISS)) {
						g.setColor(Color.white);
						
						g.fillOval(
							i * Constants.CELL_SIZE + (Constants.CELL_SIZE - Constants.poinDiam) / 2,
							j * Constants.CELL_SIZE + (Constants.CELL_SIZE - Constants.poinDiam) / 2,
							Constants.poinDiam, Constants.poinDiam);
					}
			   }
	}
	
	public boolean isItAll() {
		for (int i = 0; i < 4; i++)
			if (shipsLeft[i] != 0) 
				return false;
		return true;
	}
	
	public int getCoordinatesOnGrid(int x) {
		for (int i = 0; i <= 10; i++) 
			if (x < Constants.CELL_SIZE * i) 
				return i - 1;
		return -1;
	}

	protected void setMissAroundDead() {
		for (int i = 0; i < 10; i++) 
			for (int j = 0; j < 10; j++) 
				if (array[i][j].equals(Cell.DEAD))
					for (int m = i - 1; m <= i + 1; m++) 
						for (int n = j - 1; n <= j + 1; n++) 
							if ((m != -1) && (n != -1) && (m != 10) && (n != 10))
								if (array[m][n].equals(Cell.WATER))
									array[m][n] = Cell.MISS;
	}
		

	public Cell[][] getArray() {
		return array;
	}
	
	public void setArray(Cell[][] array) {
		this.array = array;
	}
	
	protected boolean isShipBorder(int x, int y, int size, boolean rotate) {
		for (int i = 0; i < size; i++) {
			if (isCellBorder(x, y))
				return true;
			if ( rotate) 
				x++;
			else
				y++;
		}
		return false;
	}
	
	private boolean isCellBorder(int x, int y) {
		for (int i = x - 1; i <= x + 1; i++) 
			for (int j = y - 1; j <= y + 1; j++)
				if (i >= 0 && j >= 0 && i < 10 && j < 10)
					if (array[i][j].equals(Cell.SHIP))
						return true;
		return false;
	}
	
	protected void setShip(int x, int y, int size, boolean rotate, Cell cell) {
		
		if ((isShipBorder(x, y, size, rotate)) ||
				(shipsLeft[size - 1] == 0) ||
				(x + size > 10 && rotate) ||
				(y + size > 10 && !rotate))
			return;

		for (int i = 0; i < size; i++) {
			array[x][y] = cell;
			if (rotate) x++;
			else y++;
		}
		if (cell.equals(Cell.SHIP))
			shipsLeft[size - 1]--;
	}
	
	public void clear() {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				array[i][j] = Cell.WATER;
		
		shipsLeft[0] = 4;
		shipsLeft[1] = 3;
		shipsLeft[2] = 2;
		shipsLeft[3] = 1;
	}
	
	protected void autoSetting() {
		clear();
		Random rand = new Random();
		for (int i = 0; i < 4; i++)
			while (shipsLeft[i] != 0)
				setShip(rand.nextInt(10), rand.nextInt(10), i + 1, rand.nextBoolean(), Cell.SHIP);
	}

	protected void restore(Cell oldCell, Cell newCell) {
		restore(array, oldCell, newCell);
	}
	
	protected void restore(Cell[][] array, Cell oldCell, Cell newCell) {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) 
				if (array[i][j].equals(oldCell))
					array[i][j] = newCell;
	}
	
	protected boolean isShipsAround(Cell[][] array, int x, int y) {
		for (int i = x - 1; i <= x + 1; i++) 
			for (int j = y - 1; j <= y + 1; j++)
				if ((i != -1) && (j != -1) && (i != array.length) && (j != array.length))
					if (array[i][j].equals(Cell.SHIP))
						return true;
		return false;
	}
}
