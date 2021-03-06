package zornco.reploidcraft.core;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.sounds.Sounds;
/**
 * Name and cast of this class are irrelevant
 */
public class EventBus
{

	public EventBus()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}

	@ForgeSubscribe
	public void onEntityDrop(LivingDropsEvent event) {
		EntityLiving victim = event.entityLiving;
		if(ReploidCraft.instance.rand.nextInt(64) == 0) {
			System.out.println("DING");
			if (victim instanceof EntityBlaze) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraft.weaponChip, 1, 1)));
			}
			if (victim instanceof EntityCreeper) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraft.weaponChip, 1, 3)));
			}
			if (victim instanceof EntityEnderman) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraft.weaponChip, 1, 4)));
			}
			if (victim instanceof EntityDragon) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraft.weaponChip, 1, 5)));
			}
			if (victim instanceof EntityGhast) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraft.weaponChip, 1, 6)));
			}
			if (victim instanceof EntityMagmaCube) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraft.weaponChip, 1, 7)));
			}
			if (victim instanceof EntityPigZombie) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraft.weaponChip, 1, 8)));
			}
			if (victim instanceof EntitySkeleton) {
				if(((EntitySkeleton)victim).getSkeletonType() == 1)
					event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraft.weaponChip, 1, 13)));
				else
					event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraft.weaponChip, 1, 9)));
			}
			if (victim instanceof EntitySlime) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraft.weaponChip, 1, 10)));
			}
			if (victim instanceof EntitySpider) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraft.weaponChip, 1, 11)));
			}
			if (victim instanceof EntityWither) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraft.weaponChip, 1, 12)));
			}
			if (victim instanceof EntityZombie) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraft.weaponChip, 1, 14)));
			}
		}
	}
}
