package com.nethermole.bonfires;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class BonfiresGUI extends GuiScreen{
	public static final int GUI_ID = 20;
	
	public static ResourceLocation greyArea = new ResourceLocation("bonfires", "textures/gui/grey2.png");
	
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
		
		int buttonWidth = 90;
		int buttonHeight = 20;
		int distanceBetweenButtons = 50;
		
		int posX = (this.width - buttonWidth) / 2;
		int posY = (this.height - buttonHeight) / 2;
		
		this.buttonList.add(new GuiButton(0, posX, posY, buttonWidth, buttonHeight, "Warp to Bonfire"));
		this.buttonList.add(new GuiButton(1, posX, posY + distanceBetweenButtons, buttonWidth, buttonHeight, "Rename Bonfire"));
	}
	
	public void actionPerformed(GuiButton button){
		
		switch(button.id){
		case 0: this.mc.displayGuiScreen(new GuiSelectString(this));
		break;
		
		case 1: this.mc.displayGuiScreen(new Gui1InputField());
		break;
		
		}
	}
	
	
	
	
}
