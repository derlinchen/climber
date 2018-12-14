package com.climber.enums;

public enum DataAction {
	
	Create(1),Update(2),Delete(3),Deal(4),Reset(5);
	
	private final int action;
	
	private DataAction(int action) {
		this.action = action;
	}
	
	public int getAction () {
		return this.action;		
	}
	
}
