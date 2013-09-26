package zornco.reploidcraft.core;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy {

	// Client stuff
	public void registerRenderInformation () {
		// Nothing here as this is the server side proxy
	}
	public void registerKeyBindingHandler() {

	}
	public void initTickHandlers()
	{

	}
	public int addArmor(String path) {
		return 0;
	}
	public void busterShot(World worldObj, double sx, double sy, double sz, float size, int type)
	{
	}
	public void setKeyBinding(String name, int value) {

	}
	public void registerSounds() {
		
	}
	public Object getSoundManager() { return null;}
}