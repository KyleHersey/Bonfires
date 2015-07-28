package com.nethermole.bonfires;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabsBonfires extends CreativeTabs{

	public CreativeTabsBonfires(int id, String tabLabel){
		super(id, tabLabel);
	}

	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return Items.fire_charge;
	}
	
}
