package com.amazon.eighthouses;

import java.util.ArrayList;
import java.util.List;

public class EightHousesSolution {

	public static void main(String[] args) {
		int[] states = {1, 0, 0, 0, 0, 1, 0, 0};
		int days = 1;
		
		EightHousesSolution main = new EightHousesSolution();
		System.out.println(main.cellCompete(states, days));
	}
	
    public List<Integer> cellCompete(int[] states, int days)
    {
        Neighborhood cells = new Neighborhood(states);
        for(int i=0; i<days; i++) {
            cells.advanceOneDay();
        }
        return cells.toList();
    }
    
    /**
     * Represents an arrangement of houses or Cells.
     * @author tiago.luna
     */
    public class Neighborhood {

    	private Cell first;
    	
    	public Neighborhood(int[] states) {
    		if (states.length != 8) {
    			throw new IllegalArgumentException();
    		}
    		
    		Cell current = null;
    		for(int i = 0; i < states.length; i++) {
    			Cell c = new Cell(states[i]);
    			
    			if (current == null) {
    				first = c;
    				current = first;
    			}
    			else {
    				c.setPreviousCell(current);
    				current.setNextCell(c);
    				current = c;
    			}
    		}
    	}
    	
    	public void advanceOneDay() {
    		Cell c = first;
    		
    		while(c != null) {
    			c.changeState();
    			c = c.getNextCell();
    		}
    		
    		c = first;
    		while(c != null) {
    			c.setState(c.getStateNextDay());
    			c = c.getNextCell();
    		}
    	}
    	
    	public List<Integer> toList() {
    		List<Integer> list = new ArrayList<>();
    		
    		Cell c = first;
    		
    		while(c != null) {
    			list.add(c.getState());
    			c = c.getNextCell();
    		}
    		
    		return list;
    	}
    }

    /**
     * Represents a House that can be active or innactive, according to its neighbours' state.
     * @author tiago.luna
     */
    public class Cell {

    	private int state;
    	private int stateNextDay;
    	private Cell previousCell;
    	private Cell nextCell;
    	
    	public Cell() {
    		super();
    	}
    	
    	public Cell(int state) {
    		this.state = state;
    	}

    	public int getState() {
    		return state;
    	}
    	public void setState(int state) {
    		this.state = state;
    	}

    	public void setPreviousCell(Cell previousCell) {
    		this.previousCell = previousCell;
    	}

    	public Cell getNextCell() {
    		return nextCell;
    	}

    	public void setNextCell(Cell nextCell) {
    		this.nextCell = nextCell;
    	}

    	public int getStateNextDay() {
    		return stateNextDay;
    	}

    	public void changeState() {
    		int prevState = previousCell != null && previousCell.state == 1 ? 1 : 0;
    		int nextState = nextCell != null && nextCell.state == 1 ? 1 : 0;
    		stateNextDay = prevState == nextState ? 0 : 1;
    	}
    }
}
