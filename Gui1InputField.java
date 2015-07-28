package com.nethermole.bonfires;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class Gui1InputField extends GuiScreen{
	
	private BonfiresGUI parentGui;
	private GuiTextField textfield;
	
	public Gui1InputField(BonfiresGUI parent){
		super();
		parentGui = parent;
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3){
		super.drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		
		textfield.drawTextBox();
		this.drawString(fontRendererObj, "no spaces", 20, 60, Color.WHITE.getRGB());
		
	}
	
	@Override
	public void initGui(){		
		buttonList.add(new GuiButton(1, width / 2 + 2, height / 2 + 20, 98, 20, "Done"));
		
		//CHANGE FROM 1.7 I dont know what the first parameter to this is
		textfield = new GuiTextField(0, fontRendererObj, 20, 75, 200, 20);
		textfield.setFocused(true);
	}
	
	@Override
	public void updateScreen(){
		textfield.getText();
	}
	
	@Override
	public void mouseClicked(int par1, int par2, int par3){
		try {
			super.mouseClicked(par1, par2, par3);
		} catch (IOException e) {
			System.out.println("mouseClicked in Gui1InputField caused IOException");
		}
		textfield.mouseClicked(par1, par2, par3);
	}
	
	@Override
	public void keyTyped(char c, int i){
		try {
			super.keyTyped(c, i);
		} catch (IOException e) {
			System.out.println("keyTyped in Gui1InputField caused IOException");

		}
		textfield.textboxKeyTyped(c, i);
	}
	
	@Override
	public void actionPerformed(GuiButton gb){
		ArrayList<BonfireListElement> bonList = null;
		
		if(gb.id == 1){
			int blockX = mc.thePlayer.rayTrace(5, 1.0F).getBlockPos().getX();
			int blockY = mc.thePlayer.rayTrace(5, 1.0F).getBlockPos().getY();
			int blockZ = mc.thePlayer.rayTrace(5, 1.0F).getBlockPos().getZ();
			
			try {
				bonList = readBonfires();
			} catch (FileNotFoundException e) {
				System.out.println("could not find bonTextFile.txt");
			}
			
			for(int i = 0; i < bonList.size(); i++){
				if(bonList.get(i).getX() == blockX &&
				   bonList.get(i).getY() == blockY &&
				   bonList.get(i).getZ() == blockZ && 
				   (textfield.getText().length() > 0 && textfield.getText().contains(" ") == false)){
					
					bonList.get(i).setName(textfield.getText());
					
					//exit for loop
					i = bonList.size();
				}
			}
			
			bonfiresToFile(bonList);
			
			mc.displayGuiScreen(parentGui);
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
