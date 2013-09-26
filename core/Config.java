package zornco.reploidcraft.core;

import java.util.Arrays;
import java.util.List;

import net.machinemuse.api.IModularItem;
import net.machinemuse.api.IPowerModule;
import net.machinemuse.api.ModuleManager;
import net.machinemuse.powersuits.powermodule.armor.BasicPlatingModule;
import net.machinemuse.powersuits.powermodule.armor.DiamondPlatingModule;
import net.machinemuse.powersuits.powermodule.armor.EnergyShieldModule;
import net.machinemuse.powersuits.powermodule.energy.AdvancedBatteryModule;
import net.machinemuse.powersuits.powermodule.energy.BasicBatteryModule;
import net.machinemuse.powersuits.powermodule.energy.EliteBatteryModule;
import net.machinemuse.powersuits.powermodule.misc.TintModule;
import net.machinemuse.powersuits.powermodule.movement.ShockAbsorberModule;
import net.machinemuse.utils.MuseItemUtils;
import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.bullets.EntityBusterBullet;
import zornco.reploidcraft.crafting.RecipeHandler;
import zornco.reploidcraft.items.ItemChip;
import zornco.reploidcraft.items.armors.ItemReploidArmorBase;
import zornco.reploidcraft.items.busters.ItemXBuster;
import zornco.reploidcraft.modules.BusterModule;
import zornco.reploidcraft.modules.EnhancedFormModule;
import zornco.reploidcraft.modules.ExplosiveModule;
import zornco.reploidcraft.modules.SpreadArrowModule;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Config {
	//ID's
	public static int spikesRI = -1;
	public static int bossDoorRI = -1;

	private int busterID;
	private int weaponChipID;
	private int healthBitID;
	private int healthByteID;
	//private int weaponBitID;
	//private int weaponByteID;
	private int healthTankID;
	//private int weaponTankID;
	//private int extraManID;
	private int reploidPlateID;
	private int componentID;

	private int reploidHelmID;
	private int reploidChestID;
	private int reploidBeltID;
	private int reploidBootsID;
	private int platformPlacerID;
	private int doorBossItemID;

	private int upgradeStationID;
	private int spikesID;
	private int doorBossBlockID;
	public static EnumArmorMaterial enumReploidArmor = EnumHelper.addArmorMaterial("ReploidArmor", 16, new int[]{2,7,6,3}, 20);

	public static RecipeHandler recipes = new RecipeHandler();
	public static void addModule(IPowerModule module) {
		ModuleManager.addModule(module);
	}
	public void loadConfig(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		//Items
		int itemID = 22000;
		busterID = config.getItem(config.CATEGORY_ITEM,"X Buster", itemID++).getInt();
		weaponChipID = config.getItem(config.CATEGORY_ITEM,"Wweapon Chips", itemID++).getInt();
		reploidHelmID = config.getItem(config.CATEGORY_ITEM,"Reploid Helm", itemID++).getInt();
		reploidChestID = config.getItem(config.CATEGORY_ITEM,"Reploid Chest", itemID++).getInt();
		reploidBeltID = config.getItem(config.CATEGORY_ITEM,"Reploid Belt", itemID++).getInt();
		reploidBootsID = config.getItem(config.CATEGORY_ITEM,"Reploid Boots", itemID++).getInt();

		//Keys
		ReploidCraft.instance.proxy.setKeyBinding("Weapon Change", config.get("Keybinds", "key.change", 48).getInt(48));
		ReploidCraft.instance.proxy.setKeyBinding("Buster Menu", config.get("Keybinds", "key.menu", 48).getInt(49));

		config.save();
	}
	/**
	 * Load all the modules in the config file into memory. Eventually. For now, they are hardcoded.
	 */
	public static void loadPowerModules() {
		IPowerModule module;
		List<IModularItem> ALLITEMS = Arrays.asList((IModularItem) ReploidCraft.buster, (IModularItem) ReploidCraft.reploidHelm, (IModularItem) ReploidCraft.reploidChest, (IModularItem) ReploidCraft.reploidBelt, (IModularItem) ReploidCraft.reploidBoots);
		List<IModularItem> ARMORONLY = Arrays.asList((IModularItem) ReploidCraft.reploidHelm, (IModularItem) ReploidCraft.reploidChest, (IModularItem) ReploidCraft.reploidBelt, (IModularItem) ReploidCraft.reploidBoots);
		List<IModularItem> HEADONLY = Arrays.asList((IModularItem) ReploidCraft.reploidHelm);
		List<IModularItem> CHESTONLY = Arrays.asList((IModularItem) ReploidCraft.reploidChest);
		List<IModularItem> LEGSONLY = Arrays.asList((IModularItem) ReploidCraft.reploidBelt);
		List<IModularItem> FEETONLY = Arrays.asList((IModularItem) ReploidCraft.reploidBoots);
		List<IModularItem> BUSTERONLY = Arrays.asList((IModularItem) ReploidCraft.buster);


		addModule(new BusterModule(BUSTERONLY));
		addModule(new SpreadArrowModule(BUSTERONLY));
		addModule(new ExplosiveModule(BUSTERONLY));

		// Armor
		addModule(new BasicPlatingModule(ARMORONLY));
		addModule(new DiamondPlatingModule(ARMORONLY));
		addModule(new EnergyShieldModule(ARMORONLY));
		// Energy
		addModule(new BasicBatteryModule(ALLITEMS));
		addModule(new AdvancedBatteryModule(ALLITEMS));
		addModule(new EliteBatteryModule(ALLITEMS));

		// Cosmetic
		addModule(new TintModule(ALLITEMS));
		addModule(new EnhancedFormModule(ARMORONLY).
				addInstallCost(MuseItemUtils.copyAndResize(new ItemStack(Item.netherStar, 1), 1)));
		addModule(new EnhancedFormModule(BUSTERONLY).
				addInstallCost(MuseItemUtils.copyAndResize(new ItemStack(Block.dragonEgg, 1), 1)));

		// Head ======================================
		// Torso =====================================
		// Legs =======================================
		// Feet =======================================
		addModule(new ShockAbsorberModule(FEETONLY));
	}

	public void addNames() {
		/** Names **/
		LanguageRegistry.addName(ReploidCraft.buster, "X Buster");

		LanguageRegistry.addName(ReploidCraft.reploidHelm, "Reploid Helm");
		LanguageRegistry.addName(ReploidCraft.reploidChest, "Reploid Chest");
		LanguageRegistry.addName(ReploidCraft.reploidBelt, "Reploid Belt");
		LanguageRegistry.addName(ReploidCraft.reploidBoots, "Reploid Boots");

		LanguageRegistry.instance().addStringLocalization("entity.Met.name", "en_US", "Met");
		LanguageRegistry.instance().addStringLocalization("death.bullet", "en_US", "Pewpew Dead!");
		LanguageRegistry.instance().addStringLocalization("itemGroup.Reploid", "en_US", "Reploid");
		for(String a : ItemChip.chipTypeNames)
		{
			LanguageRegistry.instance().addStringLocalization("item.chip."+a+".name", "en_US", a);
		}
	}

	public void registerEntities() {
		/** Registers **/

		EntityRegistry.registerGlobalEntityID(EntityBusterBullet.class, "lemonBullet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityBusterBullet.class, "lemonBullet", 1, ReploidCraft.instance, 250, 1, true);
	}

	public void addItems() {
		/** Items **/
		ReploidCraft.buster = new ItemXBuster(busterID, 1, false).setUnlocalizedName("XBuster").setFull3D();
		// add weapon types
		// Guts type weapon that would act like a macerator
		// cut type weapon that act like shears
		// fire type weapon that acts like flint and steel
		// water type weapon that places a water(non-source) block
		ReploidCraft.weaponChip = new ItemChip(weaponChipID).setUnlocalizedName("chip");

		ReploidCraft.reploidHelm = new ItemReploidArmorBase(reploidHelmID, enumReploidArmor, ReploidCraft.instance.proxy.addArmor("ReploidBasic"),0).setUnlocalizedName("ReploidHelm");
		ReploidCraft.reploidChest = new ItemReploidArmorBase(reploidChestID, enumReploidArmor, ReploidCraft.instance.proxy.addArmor("ReploidBasic"),1).setUnlocalizedName("ReploidChest");
		ReploidCraft.reploidBelt = new ItemReploidArmorBase(reploidBeltID, enumReploidArmor, ReploidCraft.instance.proxy.addArmor("ReploidBasic"),2).setUnlocalizedName("ReploidBelt");
		ReploidCraft.reploidBoots = new ItemReploidArmorBase(reploidBootsID, enumReploidArmor, ReploidCraft.instance.proxy.addArmor("ReploidBasic"),3).setUnlocalizedName("ReploidBoots");
	}
	/**
	 * Helper function for making recipes. Returns a copy of the itemstack with the specified stacksize.
	 * 
	 * @param stack
	 *            Itemstack to copy
	 * @param number
	 *            New Stacksize
	 * @return A new itemstack with the specified properties
	 */
	public static ItemStack copyAndResize(ItemStack stack, int number) {
		ItemStack copy = stack.copy();
		copy.stackSize = number;
		return copy;
	}
}
