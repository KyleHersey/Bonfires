package com.nethermole.bonfires;


import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Bonfires.MODID, version = Bonfires.VERSION)

public class Bonfires
{
	@Instance("bonfires")
	public static Bonfires instance;
	
	@SidedProxy(clientSide = "com.nethermole.bonfires.ClientProxy", serverSide = "com.nethermole.bonfires.CommonProxy")
	public static CommonProxy proxy;
	
    public static final String MODID = "bonfires";
    public static final String VERSION = "0.1";
    
    public static Block litBonfire;
    public static Block unlitBonfire;
    
    public static CreativeTabs tabBonfires = new CreativeTabsBonfires(CreativeTabs.getNextID(), "Bonfires");
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	
    	for(int i = 0 ; i < 100; i++){
    		System.out.println("Item: " + ("Fire"));
    	}
       	
		//litBonfire = new LitBonfire().setUnlocalizedName("litBonfire");
		//unlitBonfire = new UnlitBonfire().setUnlocalizedName("unlitBonfire");
		
		//register blocks... maybe not used in 1.8
		
		//GameRegistry.registerTileEntity(LitBonfireTileEntity.class, "LitBonfire_TileEntity");
		//ClientRegistry.bindTileEntitySpecialRenderer(LitBonfireTileEntity.class, new BonfireRenderer());	//binds tile entity to renderer
		
		//register generation of bonfires
		GameRegistry.registerWorldGenerator(new BonfireGenerator(), 1);
		
		/*
		//add recipe for bonfire
		GameRegistry.addRecipe(new ItemStack(unlitBonfire), new Object[]{
			"LSL",
			" S ",
			"BBB",
			'L', (new ItemStack(Items.dye,1,4)),
			'S', Items.iron_sword,
			'B', Blocks.iron_block
			});
			*/
		
		/* how to remove a recipe
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		Iterator<IRecipe> iter = recipes.iterator();
		while(iter.hasNext()){
			ItemStack output = iter.next().getRecipeOutput();
			if(output != null && output.getItem() == Items.bed){
				iter.remove();
			}
		}
		*/
		
		
	}

    
    
    @EventHandler
    public void Init(FMLInitializationEvent event){
    	
    	if(event.getSide() == Side.CLIENT)
    	{
    	RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    
    	//blocks
    	//renderItem.getItemModelMesher().register(Item.getItemFromBlock(litBonfire), 0, new ModelResourceLocation(Bonfires.MODID + ":" + ((LitBonfire) litBonfire).getName(), "inventory"));
        
    	//items
    	}
    	
    	NetworkRegistry.INSTANCE.registerGuiHandler(instance,proxy);
    }
}
