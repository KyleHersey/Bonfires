package com.nethermole.bonfires;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements net.minecraftforge.fml.common.network.IGuiHandler{

	//@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
		return null;
	}
	
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
		if(ID == BonfiresGUI.GUI_ID){
			return new BonfiresGUI();
		}
		
		return null;
	}
}
