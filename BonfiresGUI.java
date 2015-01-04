package com.nethermole.bonfires;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class BonfiresGUI extends GuiScreen{
	public static final int GUI_ID = 20;
	
	public BonfiresGUI(){
		super();
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3){
		this.drawDefaultBackground();
        super.drawScreen(par1, par2, par3);
        
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return true;
	}
	
	@Override
	public void initGui(){
		this.buttonList.clear();
		
		int buttonWidth = 135;
		int buttonHeight = 20;
		int posX = (this.width - buttonWidth) / 2;
		int posY = (this.height - buttonHeight) / 2;
		this.buttonList.add(new GuiButton(0, posX, posY, buttonWidth, buttonHeight, "Warp to Bonfire"));
		
		buttonWidth = 90;
		buttonHeight = 20;
		posX = (this.width - buttonWidth) / 2;
		posY = (this.height - buttonHeight) / 2;
		this.buttonList.add(new GuiButton(1, posX, posY + 50, buttonWidth, buttonHeight, "Rename Bonfire"));
	}
	
	public void actionPerformed(GuiButton button){
		
		switch(button.id){
		case 0: this.mc.displayGuiScreen(new GuiSelectString(this));
		break;
		
		case 1: this.mc.displayGuiScreen(new Gui1InputField(this));
		break;
		
		}
	}
	
	
	
	
}
