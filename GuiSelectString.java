package com.nethermole.bonfires;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiSelectString extends GuiScreen{

	protected BonfiresGUI parentGui;
	
	protected GuiSlotList list;
	
	public GuiSelectString(BonfiresGUI parent){
		this.parentGui = parent;
			try {
				this.list = new GuiSlotList(parent);	//gets bonfire information
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
	}
		//adds buttons and other controls
		public void initGui(){
			
			//StringTranslate var1 = StringTranslate.getInstance();			tutorial code
			this.buttonList.add(new GuiButton(0, 25, this.height-38, 120, 20, "Back"));
		}
		
		protected void actionPerformed(GuiButton par1GuiButton){
			
			//returns to the previous gui
			if (par1GuiButton.enabled){
				switch(par1GuiButton.id){
					case 0: this.mc.displayGuiScreen(this.parentGui);
					PrintWriter print = null;
					
					try {
						print = new PrintWriter(new File("bonWarpTo.txt"));
					} catch (FileNotFoundException e) {
						System.out.println("cannot create bonWarpTo.txt");
					}
					
					print.print("false");
					print.close();
				}
			}
		}
		
		public void drawScreen(int par1, int par2, float par3){
			this.list.drawScreen(par1, par2, par3);
			super.drawScreen(par1, par2, par3);
			this.drawCenteredString(fontRendererObj, "Select Bonfire", this.width/2, 17, Color.white.getRGB());
		}
	}
	

