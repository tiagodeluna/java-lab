package com.amazn.test;

import java.util.ArrayList;
import java.util.List;

/**
 * In a neighborhood formed by eight houses (represented by an Array), the houses compete with 
 * each other every day. For each house, the "competition" follows the following logic:
 * 	- If both neighbors are active (1) or inactive (0), the current house will be inactive next day.
 *  - In the other cases, the current house will be active in the next day.
 * The algorithm processes the state of all houses after a given number of days. 
 * 
 * @author Tiago Luna
 */
public class EightHouses {

	public static void main(String[] args) {
		int[] states = {1, 0, 0, 0, 0, 1, 0, 0};
		int days = 1;
		
		EightHouses main = new EightHouses();
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
     * Represents an arrangement of houses or Houses.
     * @author tiago.luna
     */
    public class Neighborhood {

    	private House first;
    	
    	public Neighborhood(int[] states) {
    		if (states.length != 8) {
    			throw new IllegalArgumentException();
    		}
    		
    		House current = null;
    		for(int i = 0; i < states.length; i++) {
    			House c = new House(states[i]);
    			
    			if (current == null) {
    				first = c;
    				current = first;
    			}
    			else {
    				c.setPrevious(current);
    				current.setNext(c);
    				current = c;
    			}
    		}
    	}
    	
    	public void advanceOneDay() {
    		House c = first;
    		
    		while(c != null) {
    			c.changeState();
    			c = c.getNext();
    		}
    		
    		c = first;
    		while(c != null) {
    			c.setState(c.getStateNextDay());
    			c = c.getNext();
    		}
    	}
    	
    	public List<Integer> toList() {
    		List<Integer> list = new ArrayList<>();
    		
    		House c = first;
    		
    		while(c != null) {
    			list.add(c.getState());
    			c = c.getNext();
    		}
    		
    		return list;
    	}
    }

    /**
     * Represents a House that can be active or innactive, according to its neighbours' state.
     * @author tiago.luna
     */
    public class House {

    	private int state;
    	private int stateNextDay;
    	private House previous;
    	private House next;
    	
    	public House() {
    		super();
    	}
    	
    	public House(int state) {
    		this.state = state;
    	}

    	public int getState() {
    		return state;
    	}
    	public void setState(int state) {
    		this.state = state;
    	}

    	public void setPrevious(House previous) {
    		this.previous = previous;
    	}

    	public House getNext() {
    		return next;
    	}

    	public void setNext(House next) {
    		this.next = next;
    	}

    	public int getStateNextDay() {
    		return stateNextDay;
    	}

    	public void changeState() {
    		int prevState = previous != null && previous.state == 1 ? 1 : 0;
    		int nextState = next != null && next.state == 1 ? 1 : 0;
    		stateNextDay = prevState == nextState ? 0 : 1;
    	}
    }
}
