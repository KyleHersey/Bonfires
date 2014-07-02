package com.nethermole.bonfires;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class BonfireGenerator implements IWorldGenerator{

	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		switch(world.provider.dimensionId){
			case -1:
				generateNether(world, random, chunkX * 16, chunkZ * 16);
				break;
			
			case 0:
				generateSurface(world, random, chunkX * 16, chunkZ * 16);
				break;
				
			case 1:
				generateEnd(world, random, chunkX * 16, chunkZ * 16);
				break;
		
		}
		
	}

	private void generateEnd(World world, Random random, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	private void generateSurface(World world, Random random, int i, int j) {
		
		int attempts = 500;	//number of times to try to generate (20)
		
		for(int k=0; k < attempts; k++){		
			int firstBlockXCoord = i + random.nextInt(16);
			int firstBlockYCoord = random.nextInt(world.getActualHeight());
			int firstBlockZCoord = j + random.nextInt(16);
			
			boolean placed = new WorldGenBonfire().generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
			
			if(placed){
				k = attempts;
			}
		}
		
	}

	private void generateNether(World world, Random random, int i, int j) {
		// TODO Auto-generated method stub
		
	}

}
