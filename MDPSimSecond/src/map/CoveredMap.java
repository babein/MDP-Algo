package map;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import exploration.SimRobot;
import exploration.RobotData;

public class CoveredMap extends JPanel implements MapData, RobotData{
	
	/**
	 * V0.0 author Li_Yuekang
	 */
	private static final long serialVersionUID = 1L;
	
	private Cell[][] newCell = new Cell[ROWS][COLS];
	private SimRobot r;

	public CoveredMap(RealMap realMap, SimRobot r) {
		this.r=r;
		this.initiate(realMap);
	}
	
    public void paintComponent(Graphics g)
    {
    	int j;
    	int i;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.RED);
        g.fillRect(33, 33, this.getWidth() - 66, this.getHeight() - 66);
        for(j=0;j < COLS; j++){
        		g.setColor(Color.RED);
        		g.fillRect(30+j*33,0, 3, 558);
        }
        for(i=0;i < ROWS; i++){
            	g.setColor(Color.RED);
            	g.fillRect(0,30+i*33, 723, 3);
        }
        
		
		//this is to dye the explored cells
		/*for (i= 0; i < ROWS; i++) {
			for (j = 0; j < COLS; j++) {
				if(newCell[i][j].getExplored()) {
					g.setColor(newCell[i][j].getColor());
					g.fillRect(newCell[i][j].getXC()-15, newCell[i][j].getYC()-15, 30, 30);
				}
			}
		}
		
		//this is to dye the start & goal
		
		// this is to dye the path
		for ( i = 0; i < ROWS; i++) {
			for ( j = 0; j < COLS; j++) {
				switch(newCell[i][j].getPathStatus()){
				case "east":
				case "west":
					g.setColor(PATH);
					g.fillRect(newCell[i][j].getXC()-15, newCell[i][j].getYC()-2, 30, 4);

				break;
				case "north":
				case "south":
					g.setColor(PATH);
					g.fillRect(newCell[i][j].getXC()-2, newCell[i][j].getYC()-15, 4, 30);
					break;
				default: //do nothing
					break;
				}
			}
		}*/
		
        //this part will dye the robot
        g.setColor(ROBOTB);
        g.fillRect(this.r.getXC()-48, this.r.getYC()-48, 96, 96);
		switch (this.r.getDirection()){
		case EAST: g.setColor(ROBOTF);
					g.fillRect(this.r.getXC()+18, this.r.getYC()-15, 30, 30);
				   break;
		case SOUTH: g.setColor(ROBOTF);
					g.fillRect(this.r.getXC()-15, this.r.getYC()+18, 30, 30);
					break;
		case WEST:  g.setColor(ROBOTF);
					g.fillRect(this.r.getXC()-48, this.r.getYC()-15, 30, 30);
					break;
		case NORTH: g.setColor(ROBOTF);
					g.fillRect(this.r.getXC()-15, this.r.getYC()-48, 30, 30);
					break;
		}
    }
    
	protected void initiate(RealMap realMap) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				newCell[i][j] = new Cell(i, j);
				this.newCell[i][j].setColor(realMap.newCell[i][j].getColor());
			}
		}
	}
	
	public Cell uncoverACell(int x, int y, String s){
		newCell[y][x].setPathStatus(s);
		newCell[y][x].exploreACell();
		return newCell[y][x];
	}
	
	public Cell[][] getNewCell() {
		return newCell;
	}
}
