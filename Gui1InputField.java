package com.nethermole.bonfires;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class Gui1InputField extends GuiScreen{

	private GuiTextField textfield;
	
	public Gui1InputField(){
		super();
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3){
		super.drawDefaultBackground();
		textfield.drawTextBox();
		
		super.drawScreen(par1, par2, par3);
	}
	
	@Override
	public void initGui(){		
		buttonList.add(new GuiButton(1, width / 2 + 2, height / 2 + 20, 98, 20, "Done"));
		
		textfield = new GuiTextField(fontRendererObj, 20, 75, 200, 20);
		textfield.setFocused(false);
	}
	
	@Override
	public void updateScreen(){
		textfield.getText();
	}
	
	@Override
	public void mouseClicked(int par1, int par2, int par3){
		super.mouseClicked(par1, par2, par3);
		textfield.mouseClicked(par1, par2, par3);
	}
	
	@Override
	public void keyTyped(char c, int i){
		super.keyTyped(c, i);
		textfield.textboxKeyTyped(c, i);
	}
	
	@Override
	public void actionPerformed(GuiButton gb){
		ArrayList<BonfireListElement> bonList = null;
		
		if(gb.id == 1){
			int blockX = mc.thePlayer.rayTrace(5, 1.0F).blockX;
			int blockY = mc.thePlayer.rayTrace(5, 1.0F).blockY;
			int blockZ = mc.thePlayer.rayTrace(5, 1.0F).blockZ;
			
			try {
				bonList = readBonfires();
			} catch (FileNotFoundException e) {
				System.out.println("Something blew up");
			}
			
			for(int i = 0; i < bonList.size(); i++){
				if(bonList.get(i).getX() == blockX &&
				   bonList.get(i).getY() == blockY &&
				   bonList.get(i).getZ() == blockZ){
					
					bonList.get(i).setName(textfield.getText());
					
					//exit for loop
					i = bonList.size();
				}
			}
			
			bonfiresToFile(bonList);
		}
	}
	
	public void bonfiresToFile(ArrayList<BonfireListElement> bonList){
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new File("bonTextFile.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("cannot create bonTextFile.txt");
		}
		
		for(int i = 0; i < bonList.size(); i++){
			pw.print(" " + bonList.get(i).getName() + " ");
			pw.print(bonList.get(i).getX() + " ");
			pw.print(bonList.get(i).getY() + " ");
			pw.print(bonList.get(i).getZ() + " ");
		}
		
		pw.close();
	}
	
	public ArrayList<BonfireListElement> readBonfires() throws FileNotFoundException{
		ArrayList<BonfireListElement> bonList = new ArrayList<BonfireListElement>();
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
		return bonList;
	}
}
