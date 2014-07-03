package com.nethermole.bonfires;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class UnlitBonfire extends Block{
	
	protected UnlitBonfire(){
		super(Material.ground);
		this.setCreativeTab(Bonfires.tabBonfires);
		this.setLightLevel(0.75F);
		this.setHardness(-1.0F);
		this.setBlockUnbreakable();
		this.setResistance(6000000.0F);
	}
	
	@SideOnly(Side.CLIENT)
	protected IIcon blockIcon;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister p_149651_1_){
		blockIcon = p_149651_1_.registerIcon(Bonfires.MODID + ":" + this.getUnlocalizedName().substring(5));
		//substring trims '.name' from the unlocalized name
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_){
		return blockIcon;
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, 
		EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		
		//ensures that the player is holding an item
		if(null != par5EntityPlayer.getHeldItem()){
			
			if(par5EntityPlayer.getHeldItem().getItem() == Items.flint_and_steel){
				par1World.setBlock(x, y, z, Bonfires.litBonfire);
				return true;
			}
			
			else{
				return false;
			}
		}
		
		else {
			return false;
		}
	}
}
