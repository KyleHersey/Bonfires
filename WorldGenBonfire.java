package com.nethermole.bonfires;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBonfire extends WorldGenerator{

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		
		for (int l = 0; l < 2; ++l) //l to 2
        {
            int i1 = par3 + par2Random.nextInt(8);
            int j1 = par4 + par2Random.nextInt(4);
            int k1 = par5 + par2Random.nextInt(8);

            if ((par1World.isAirBlock(i1, j1, k1) || 
            		par1World.getBlock(i1, j1, k1) == Blocks.snow_layer ||
            		par1World.getBlock(i1, j1,  k1) == Blocks.tallgrass ||
            		par1World.getBlock(i1, j1, k1) == Blocks.reeds) 
            		//block to spawn in
            		&& 
            		//block below
            		((par1World.getBlock(i1, j1 - 1, k1) == Blocks.grass) ||
            		(par1World.getBlock(i1, j1 - 1, k1) == Blocks.stone)  ||
            		(par1World.getBlock(i1, j1 - 1, k1) == Blocks.sand)   ||
            		(par1World.getBlock(i1, j1 - 1, k1) == Blocks.gravel) ||
            		(par1World.getBlock(i1, j1 - 1, k1) == Blocks.dirt)
            				))
            {
                par1World.setBlock(i1, j1, k1, Bonfires.unlitBonfire, 0, 2);
                return true;
            }
        }

        return true;
	}

}
