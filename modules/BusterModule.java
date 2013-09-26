package zornco.reploidcraft.modules;

import java.util.List;

import net.machinemuse.api.IModularItem;
import net.machinemuse.api.ModuleManager;
import net.machinemuse.utils.MuseCommonStrings;
import net.machinemuse.utils.MuseItemUtils;
import net.machinemuse.utils.ElectricItemUtils;
import net.machinemuse.api.moduletrigger.IRightClickModule;
import net.machinemuse.powersuits.network.packets.MusePacketPlasmaBolt;
import net.machinemuse.powersuits.powermodule.PowerModuleBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.bullets.EntityBusterBullet;
import zornco.reploidcraftenv.ReploidCraftEnv;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class BusterModule extends PowerModuleBase implements IRightClickModule {
	public static final String MODULE_BUSTER_CANNON = "Buster";
	public static final String BUSTER_CANNON_ENERGY_PER_TICK = "Buster Energy Per Tick";
	public static final String BUSTER_CANNON_DAMAGE_AT_FULL_CHARGE = "Buster Damage At Full Charge";

	public BusterModule(List<IModularItem> validItems) {
		super(validItems);
		addBaseProperty(BUSTER_CANNON_ENERGY_PER_TICK, 10, "J");
		addBaseProperty(BUSTER_CANNON_DAMAGE_AT_FULL_CHARGE, 2, "pt");
		addTradeoffProperty("Amperage", BUSTER_CANNON_ENERGY_PER_TICK, 150, "J");
		addTradeoffProperty("Amperage", BUSTER_CANNON_DAMAGE_AT_FULL_CHARGE, 38, "pt");
		addInstallCost(MuseItemUtils.copyAndResize(new ItemStack(ReploidCraftEnv.component, 1, 1), 1));
	}

	@Override
	public String getCategory() {
		return MuseCommonStrings.CATEGORY_WEAPON;
	}

	@Override
	public String getName() {
		return MODULE_BUSTER_CANNON;
	}

	@Override
	public String getDescription() {
		return "Use the energy generated by the AEGD in the buster to generate a plasma shot and launch it at enemies.";
	}

	@Override
	public void onRightClick(EntityPlayer player, World world, ItemStack item) {
		if (ElectricItemUtils.getPlayerEnergy(player) > 500) {
			player.setItemInUse(item, 72000);
		}
	}

	@Override
	public String getTextureFile() {
		return "gravityweapon";
	}

	@Override
	public void onItemUse(ItemStack itemStack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		
	}

	@Override
	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		return false;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world,
			EntityPlayer player, int par4) {
		int chargeTicks = Math.max(itemStack.getMaxItemUseDuration() - par4, 10);

		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			double energyConsumption = ModuleManager.computeModularProperty(itemStack, BusterModule.BUSTER_CANNON_ENERGY_PER_TICK)
					* chargeTicks;
			if (ElectricItemUtils.getPlayerEnergy(player) > energyConsumption) {
				ElectricItemUtils.drainPlayerEnergy(player, energyConsumption);
				double damagingness = ModuleManager.computeModularProperty(itemStack, BusterModule.BUSTER_CANNON_DAMAGE_AT_FULL_CHARGE);

				EntityBusterBullet lemon = new EntityBusterBullet(world, player); // entity shot
				world.spawnEntityInWorld(lemon);
				MusePacketPlasmaBolt packet = new MusePacketPlasmaBolt((Player) player, lemon.entityId, 2.0D);
				PacketDispatcher.sendPacketToAllPlayers(packet.getPacket250());
			}
		}
	}

}
