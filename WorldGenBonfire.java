package com.nethermole.bonfires;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBonfire extends WorldGenerator{

	
	@Override
	public boolean generate(World par1World, Random par2Random, BlockPos bp) {
		
		int x = bp.getX();
		int y = bp.getY();
		int z = bp.getZ();
		
		/*
           if(par1World.getTopBlock(x, z) != Blocks.water){
        	   
        	   for(int i = x-2; i <= x+2; i++){
        		   for(int j = z-2; j <= z+2; j++){
        			   par1World.setBlock(i,y,j, Blocks.stonebrick);
        		   }
        	   }
                par1World.setBlock(x, y+1, z, Bonfires.unlitBonfire, 0, 2);
                
                return true;
           }
       */    
           
           return false;        
                
	}
	

}
