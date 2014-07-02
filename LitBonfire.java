package com.nethermole.bonfires;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LitBonfire extends Block{
	
	protected LitBonfire(){
		super(Material.ground);
		this.setCreativeTab(Bonfires.tabBonfires);
		this.setLightLevel(1F);
		this.setHardness(-1F);
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
						par5EntityPlayer.setPositionAndUpdate(destX, destY, destZ);
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
	
	
}
	
