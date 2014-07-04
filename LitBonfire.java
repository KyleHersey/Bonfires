package com.nethermole.bonfires;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LitBonfire extends BlockContainer{
	
	protected LitBonfire(){
		super(Material.ground);
		this.setCreativeTab(Bonfires.tabBonfires);
		this.setLightLevel(1F);
		this.setHardness(-1F);
		this.setResistance(6000000.0F);
		this.setTickRandomly(true);
	}
	
	@SideOnly(Side.CLIENT)
	protected IIcon blockIcon;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_){
		blockIcon = p_149651_1_.registerIcon(Bonfires.MODID + ":" + this.getUnlocalizedName().substring(5));
		//substring trims '.name' from the unlocalized name
	}
	
	@Override
	public int getRenderType(){
		return -1;
	}
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand){
		
		float f;
        float f1;
        float f2;
		
		for (int i = 0; i < 1; i++)
        {
            f = (float)x + rand.nextFloat();
            f1 = (float)(y + 1) - rand.nextFloat() * 0.1F;
            f2 = (float)z + rand.nextFloat();
            world.spawnParticle("smoke", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
        }
		
		for (int i = 0; i < 2; i++)
        {
            f = (float)(x + 0.25) + rand.nextFloat()/2;
            f1 = (float)(y + 0.1) - rand.nextFloat() * 0.1F;
            f2 = (float)(z + 0.25) + rand.nextFloat()/2;
            world.spawnParticle("flame", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);	//firebase
            
            //9.5/16 is the center of the sword
            f = (float)(x + .4) + rand.nextFloat()/5;
            f1 = (float)(y - (rand.nextFloat() * 0.5));
            f2 = (float)(z + .4) + rand.nextFloat()/5;
            world.spawnParticle("flame", (double)f, (double)f1 + rand.nextDouble(), (double)f2, 0.0D, 0.0D, 0.0D);	//sword
        }
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}

	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_){
		return blockIcon;
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, 
			EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		
		addThisToBonfireList(par1World, par2, par3, par4);
		ChunkCoordinates coords = new ChunkCoordinates(par2, par3 + 1, par4);
		par5EntityPlayer.setSpawnChunk(coords, true);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			if(!par1World.isRemote){
				
				Scanner scan = null;
				try {
					scan = new Scanner(new File("bonWarpTo.txt"));
				} catch (FileNotFoundException e) {
					System.out.println("bonWarpTo.txt not found");
				}
				
				if(scan.next().equals("true")){
					int destX = Integer.parseInt(scan.next());
					int destY = Integer.parseInt(scan.next()) + 1;
					int destZ = Integer.parseInt(scan.next());
					
					if(par1World.getBlock(destX, destY - 1, destZ) == Bonfires.litBonfire){					
						par5EntityPlayer.setPositionAndUpdate(destX + 0.5, destY, destZ + 0.5);
						coords = new ChunkCoordinates(destX, destY, destZ);
						par5EntityPlayer.setSpawnChunk(coords, true);
					} 
				}
			}
		}
		else{			
			
			par5EntityPlayer.openGui(Bonfires.instance, 20, par1World, par2, par3, par4);
			
		}
							
			
		return true;
	}
	
	public void addThisToBonfireList(World wo, int x, int y, int z){
		
		
			Scanner scan = null;
			PrintWriter print = null;
			//get list of bonfires
			try {
				scan = new Scanner(new File("bonTextFile.txt"));
			} catch (FileNotFoundException e) {								//if no bonfires, create file
								try {
									print = new PrintWriter(new File("bonTextFile.txt"));
								} catch (FileNotFoundException e1) {
									System.out.println("bonTextFile.txt cannot be created");
								}
				print.close();
			}	
								try {
									scan = new Scanner(new File("bonTextFile.txt"));
								} catch (FileNotFoundException e1) {
									System.out.println("bonTextFile.txt cannot be read after creation");
								}
			
							
								
			ArrayList<BonfireListElement> bonList = new ArrayList<BonfireListElement>();				
			
			//collect bonfires
			while(scan.hasNext()){
				
				String tempName = scan.next();
				int tempX = Integer.parseInt(scan.next());
				int tempY = Integer.parseInt(scan.next());
				int tempZ = Integer.parseInt(scan.next());
				
				BonfireListElement tempBonfire = new BonfireListElement(tempName, tempX, tempY, tempZ);
				bonList.add(tempBonfire);
			}
			
			scan.close();
			
			//check if this bonfire is on the list
			boolean found = false;
			
			for(int i = 0; i < bonList.size(); i++){
				BonfireListElement tempBonfire = bonList.get(i);
				
				if(	tempBonfire.getX() == x &&
					tempBonfire.getY() == y &&
					tempBonfire.getZ() == z){	
						
					found = true;
				}	
			}
			
			//if not on list
			if(found == false){
				try {
					print = new PrintWriter(new FileOutputStream(new File("bonTextFile.txt"), true));
				} catch (FileNotFoundException e1) {
					System.out.println("cannot open bonTextFile.txt");
				}
				
				//print.println();
				print.append("x"+x+"y"+y+"z"+z+" ");
				print.append(x+" ");
				print.append(y+" ");
				print.append(z+" ");
				
				print.close();
			}	
	}

	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new LitBonfireTileEntity();
	}
}
	
