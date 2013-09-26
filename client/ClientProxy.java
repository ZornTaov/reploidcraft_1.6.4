package zornco.reploidcraft.client;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.bullets.EntityBusterBullet;
import zornco.reploidcraft.client.fx.BusterFX;
import zornco.reploidcraftenv.client.renderers.RenderBulletBase;
import zornco.reploidcraft.client.renderers.RenderXBuster;
import zornco.reploidcraft.core.CommonProxy;
import zornco.reploidcraft.core.helper.KeyBindingHelper;
import zornco.reploidcraft.sounds.Sounds;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

	Sounds sounds;
	public ClientProxy()
	{
		
	}
	public void registerSounds() {
		sounds = new Sounds();
	}
	@Override
	public void initTickHandlers()
	{

		TickRegistry.registerTickHandler(new TickHandlerClient(), Side.CLIENT);
	}
	@Override
	public void registerRenderInformation()
	{
		MinecraftForgeClient.registerItemRenderer(ReploidCraft.buster.itemID, new RenderXBuster());
		//MinecraftForgeClient.preloadTexture( "/zornco/megax/textures/MegaXItemTextures.png" );
		MinecraftForgeClient.preloadTexture( "/mods/ReploidCraft/textures/gui/UpgradeStation.png" );
		MinecraftForgeClient.preloadTexture( "/mods/ReploidCraft/textures/fx/buster0.png" );
		MinecraftForgeClient.preloadTexture( "/mods/ReploidCraft/textures/fx/buster1.png" );
		MinecraftForgeClient.preloadTexture( "/mods/ReploidCraft/textures/fx/buster2.png" );
		MinecraftForgeClient.preloadTexture( "/mods/ReploidCraft/textures/models/X1LightBusterDetailed.png" );
		RenderingRegistry.registerEntityRenderingHandler(EntityBusterBullet.class, new RenderBulletBase());  

	}
	@Override
	public void registerKeyBindingHandler() {

		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
	}
	@Override
	public void setKeyBinding(String name, int value) {

		KeyBindingHelper.addKeyBinding(name, value);
		KeyBindingHelper.addIsRepeating(false);
	}
	@Override
	public int addArmor(String path) {
		return RenderingRegistry.addNewArmourRendererPrefix(path);
	}
	@Override
	public void busterShot(World worldObj, double sx, double sy, double sz, float size, int type)
	{
		BusterFX ef = new BusterFX(worldObj, sx, sy, sz, size, type);
		ModLoader.getMinecraftInstance().effectRenderer.addEffect(ef);
	}
	@Override
	public Object getSoundManager()
	{
		return Minecraft.getMinecraft().sndManager;
	}
}