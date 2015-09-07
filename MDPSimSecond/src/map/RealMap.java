package map;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import exploration.RobotData;

public class RealMap extends JPanel implements MapData, RobotData {
	/**
	 *  V0.0 author @ Li_Yuekang
	 */
	private static final long serialVersionUID = 1L;
	
	protected JPanel[][] entireMap = new JPanel[ROWS][COLS];
	protected Cell[][] newCell = new Cell[ROWS][COLS];
	
	public RealMap() {
		initiate();
	}
	
	protected void initiate() {
		this.setLayout(new GridLayout(ROWS, COLS));
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				newCell[i][j] = new Cell(i, j);
				newCell[i][j].setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				entireMap[i][j] = newCell[i][j];
				this.add(newCell[i][j]);
			}
		}
	}
	
	public void removeWalls() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if(!(newCell[i][j].IsStart() || newCell[i][j].IsBoarder())) {
					newCell[i][j].setBackground(FREE);
					newCell[i][j].setColor(FREE);
				}
			}
		}
	}
	
	public JPanel[][] getEntireMap() {
		return entireMap;
	}
	
	public Cell[][] getNewCell() {
		return newCell;
	}
}
