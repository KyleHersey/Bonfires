package com.nethermole.bonfires;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class BonfiresGUI extends GuiScreen{
	public static final int GUI_ID = 20;
	
	public final int xSizeOfTexture = 512;
	public final int ySizeOfTexture = 256;
	
	public static ResourceLocation greyArea = new ResourceLocation("bonfires", "textures/gui/grey.png");
	
	public BonfiresGUI(){
		super();
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3){
		this.drawDefaultBackground();
		
		this.mc.renderEngine.bindTexture(greyArea);
		//this.mc.renderEngine.bindTexture(greyArea);
		
		int posX = (this.width - xSizeOfTexture)/2;
		int posY = (this.height - ySizeOfTexture)/2;
		
		//GL11.glColor4f(1.0f,1.0f,1.0f,1.0f);
		
		this.drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
		
		//this.drawString(this.mc.fontRenderer, text, posX+50, posY+50, 0x000000);
		
        //this.drawCenteredString(this.fontRendererObj, "Select Bonfire", this.width / 2, 20, 16777215);
        
        super.drawScreen(par1, par2, par3);
        
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return true;
	}
	
	@Override
	public void initGui(){
		this.buttonList.clear();
		
		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;
		
		this.buttonList.add(new GuiButton(0, posX + 120, posY + 10, 90, 20, "Warp to Bonfire"));
		this.buttonList.add(new GuiButton(1, posX + 120, posY + 40, 90, 20, "Rename Bonfire"));
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
