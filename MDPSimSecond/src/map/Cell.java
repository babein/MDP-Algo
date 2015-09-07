package map;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;

public class Cell extends JPanel implements MapData{
	
	/**
	 * V0.0 author @ Li_Yuekang
	 */
	
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;
	private Color color;
	private boolean explored = false;
	
	//this will keep track of the path
	private String pathStatus;
	
	Cell(int row, int col) {
		this.row = row;
		this.col = col;
		
		BuildUpCell();
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JFrame frame = null;
				if(color != START){
					setBackground(WALL);
					setColor(WALL);
				}
				else
				JOptionPane.showMessageDialog(frame,"Cannot put walls in start or goal area ! ");
			}
		});
	}
	
	private void BuildUpCell() {
		//set up walls
		if(this.IsBoarder()) {
			this.setBackground(WALL);
			this.setColor(WALL);
		}
		//set up start & goal
		else if( this.IsStart() ){
			this.setBackground(START);
			this.setColor(START);
			this.exploreACell();
		}
		//set up free space
		else {
			this.setBackground(FREE);
			this.setColor(FREE);
		}
	}
	
	public boolean IsBoarder() {
		if(this.row == 0 || this.row == ROWS-1 || this.col == COLS-1 || this.col == 0)
			return true;
		else
			return false;
	}
	
	public boolean IsStart() {
		if( ( (0<this.row && this.row<4) && (0<this.col && this.col<4) ) || ( (COLS-5<this.col && this.col<COLS-1) && (ROWS-5<this.row && this.row<ROWS-1) ) || ( (7<this.col && this.col<11) && (5<this.row && this.row<9) ))
			return true;
		else
			return false;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public boolean getExplored(){
		return this.explored;
	}
	
	public void exploreACell(){
		this.explored = true;
	}

	public int getXC(){
		return (this.col-1)*33+15;
	}
	
	public int getYC(){
		return (this.row-1)*33+15;
	}
	
	public String getPathStatus(){
		return this.pathStatus;
	}
	
	public void setPathStatus(String s){
		this.pathStatus = s;
	}
}
