package com.nethermole.bonfires;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;

public class GuiSlotList extends GuiSlot{
	
	protected Minecraft mc;
	protected int slotHeight;
	private ArrayList<BonfireListElement> bonList;
	private String[] strings;
	private BonfiresGUI gui;
	
	public GuiSlotList(BonfiresGUI gui) throws FileNotFoundException{
		super(gui.mc, gui.width, gui.height, 32, gui.height, 24);
		
		this.readBonfires();
		this.populateStringList();
		
		this.slotHeight= 24;
		this.mc = gui.mc;
		this.gui = gui;
	}
	
	protected int getSize(){
		return this.strings.length;
	}
	
	public void readBonfires() throws FileNotFoundException{
		
		bonList = new ArrayList<BonfireListElement>();
		Scanner scan;
		
		//get list of bonfires
		try {
			scan = new Scanner(new File("bonTextFile.txt"));
		} catch (FileNotFoundException e) {								//if no bonfires, create file
			PrintWriter print = new PrintWriter(new File("bonTextFile.txt"));
			print.close();
			scan = new Scanner(new File("bonTextFile.txt"));
		}
		
		while(scan.hasNext()){
	
			String tempName = scan.next();
			int tempX = Integer.parseInt(scan.next());
			int tempY = Integer.parseInt(scan.next());
			int tempZ = Integer.parseInt(scan.next());
			
			BonfireListElement tempBonfire = new BonfireListElement(tempName, tempX, tempY, tempZ);
			bonList.add(tempBonfire);
		}
		
		scan.close();
	}
	
	public void populateStringList(){
		strings = new String[bonList.size()];
		
		for(int i = 0; i < bonList.size(); i++){
			strings[i] = bonList.get(i).getName();
		}
	}
	
	public boolean isSelected(int par1){
		return false;
	}
	
	protected int getContentHeight(){
		return this.getSize() * 24;
	}
	
	@Override
	protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator, int par6, int par7){
		this.mc.fontRenderer.setBidiFlag(true);
		this.gui.drawCenteredString(this.mc.fontRenderer, strings[par1], this.gui.width/2, par3 + 1, 16777215);
		//fix strange default string thing here
	}
	
	@Override
	protected void drawBackground(){
		this.gui.drawDefaultBackground();
	}

	@Override
	protected void elementClicked(int var1, boolean var2, int var3, int var4) {
		
	
		
		//find the bonfire list element
		BonfireListElement destination = null;
		
		for(int i = 0; i < bonList.size(); i++){
			if(bonList.get(i).getName().equals(strings[var1])){
				destination = bonList.get(i);
			}
		}
		
		PrintWriter print = null;
		
		try {
			print = new PrintWriter(new File("bonWarpTo.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("cannot create warpTo.txt");
		}
		
		print.print("true" + " " + destination.getX() + " " + destination.getY() + " " + destination.getZ());
		print.close();
		
		Robot rob = null;
		try {
			rob = new Robot();
		} catch (AWTException e) {
			System.out.println("cant create robot");
		}
		
		mc.thePlayer.closeScreen();
		
		rob.keyPress(KeyEvent.VK_SHIFT);
		rob.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		rob.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		rob.keyRelease(KeyEvent.VK_SHIFT);
		
		System.out.println("clicked");
		
	}
}

