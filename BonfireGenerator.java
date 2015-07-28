package com.nethermole.bonfires;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class BonfireGenerator implements net.minecraftforge.fml.common.IWorldGenerator{

	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		switch(world.provider.getDimensionId()){
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
						
		if(random.nextInt(80) == 0){
			int XCoord = i + random.nextInt(16);
			int ZCoord = j + random.nextInt(16);
			
			//CHANGED FROM 1.7			
			int YCoord = world.getTopSolidOrLiquidBlock(new BlockPos(XCoord, ZCoord, 0)).getZ();
			
			new WorldGenBonfire().generate(world, random, new BlockPos(XCoord, YCoord, ZCoord));
		}
		
	}

	private void generateNether(World world, Random random, int i, int j) {
		// TODO Auto-generated method stub
		
	}

}
