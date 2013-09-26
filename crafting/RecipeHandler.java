package zornco.reploidcraft.crafting;

import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraftenv.ReploidCraftEnv;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHandler {

	public void registerRecipes() {
		/** Recipes **/


		GameRegistry.addRecipe(new ItemStack(ReploidCraft.reploidHelm), 
				new Object[] { 
			"pep", "d d",  
			Character.valueOf('p'), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 4),
			Character.valueOf('e'), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 1),
			Character.valueOf('d'), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 12)
		}
				);

		GameRegistry.addRecipe(new ItemStack(ReploidCraft.reploidChest), 
				new Object[] { 
			"p p", "pep", "ddd",  
			Character.valueOf('p'), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 4),
			Character.valueOf('e'), new ItemStack(ReploidCraftEnv.component, 1, 1),
			Character.valueOf('d'), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 12)
		}
				);

		GameRegistry.addRecipe(new ItemStack(ReploidCraft.reploidBelt), 
				new Object[] { 
			"ppp", "d d", "d d",  
			Character.valueOf('p'), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 4),
			Character.valueOf('d'), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 12)
		}
				);

		GameRegistry.addRecipe(new ItemStack(ReploidCraft.reploidBoots), 
				new Object[] { 
			"p p", "d d",   
			Character.valueOf('p'), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 4),
			Character.valueOf('d'), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 12)
		}
				);

		GameRegistry.addRecipe(new ItemStack(ReploidCraft.buster), 
				new Object[] { 
			"pcp", "dep", "ppp",  
			Character.valueOf('p'), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 4),
			Character.valueOf('e'), new ItemStack(ReploidCraftEnv.component, 1, 1),
			Character.valueOf('c'), Item.lightStoneDust,
			Character.valueOf('d'), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 8)
		}
				);
	}

	public static void addOreRecipe(ItemStack output, Object[] input) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, new Object[] { Boolean.valueOf(true), input }));
	}

	public static void addShapelessOreRecipe(ItemStack output, Object[] input)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, input));
	}
}
