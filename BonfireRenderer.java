package com.nethermole.bonfires;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class BonfireRenderer extends TileEntitySpecialRenderer{

private final ModelLitBonfire model;
	
	public BonfireRenderer(){
		this.model = new ModelLitBonfire();
	}
	
	private void adjustRotatePivotViaMeta(World world, int x, int y, int z){
		int meta = world.getBlockMetadata(x,y,z);
		GL11.glPushMatrix();
		GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y,
			double z, float scale) {
		
		GL11.glPushMatrix();														//Tells renderer to do something
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F,(float) z + 0.5F);		//Set initial location
		ResourceLocation textures = (new ResourceLocation("bonfires:textures/blocks/modellitbonfire.png"));	//Set texture
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);				//bind the texture
		
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	//set lighting stuff
	private void adjustLightFixture(World world, int x, int y, int z, Block block){
		Tessellator tess = Tessellator.instance;
		float brightness = block.getLightValue(world, x, y, z);
		int skyLight = world.getLightBrightnessForSkyBlocks(x, y, z, 0);
		int modulousModifier = skyLight / 65536;
		int divModifier = skyLight / 65535;
		tess.setColorOpaque_F(brightness,  brightness,  brightness);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier);
	}
}
