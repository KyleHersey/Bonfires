package com.nethermole.bonfires;


import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

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
    public static Block glassStairs;
    public static Block glassDoor;
    
    public static CreativeTabs tabBonfires = new CreativeTabsBonfires("Bonfires");
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
       	
		litBonfire = new LitBonfire().setBlockName("litBonfire");
		unlitBonfire = new UnlitBonfire().setBlockName("unlitBonfire");
		
		//register blocks
		GameRegistry.registerBlock(litBonfire, litBonfire.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(unlitBonfire, unlitBonfire.getUnlocalizedName().substring(5));
		
		//register generation of bonfires
		GameRegistry.registerWorldGenerator(new BonfireGenerator(), 1);
		
		//add recipe for bonfire
		GameRegistry.addRecipe(new ItemStack(unlitBonfire), new Object[]{
			" S ",
			" S ",
			"BBB",
			'S', Items.iron_sword,
			'B', Blocks.iron_block
			});
		
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
    	NetworkRegistry.INSTANCE.registerGuiHandler(instance,proxy);
    }
}
