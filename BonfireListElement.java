package com.nethermole.bonfires;

public class BonfireListElement {
	
	String name;
	int coordX;
	int coordY;
	int coordZ;
	
	public BonfireListElement(String bonName, int X, int Y, int Z){
		
		this.name = bonName;
		coordX = X;
		coordY = Y;
		coordZ = Z;
	}
	
	public void setName(String input){
		this.name = input;
	}
	
	public String getName(){
		return name;
	}
	
	public int getX(){
		return coordX;
	}
	
	public int getY(){
		return coordY;
	}
	
	public int getZ(){
		return coordZ;
	}
	
	
}
