package zornco.reploidcraft.client.renderers;

import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;

import zornco.reploidcraft.ReploidCraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class RenderXBuster implements IItemRenderer
{
	public ModelXBuster buster;

	public RenderXBuster()
	{
		this.buster = new ModelXBuster();
	}

	public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
		if (type == IItemRenderer.ItemRenderType.EQUIPPED)
		{
			return true;
		}
		else return false;
	}

	public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {

		return false;

	}
	float count = 0;
	@Override

	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		switch(type)
		{
		case ENTITY:
		{
			break;	
		}
		case EQUIPPED:
		{
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture("/mods/ReploidCraft/textures/models/XBusterDetailed.png");

			GL11.glRotatef(100.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(-55.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
			boolean isFirstPerson = false;
			if(data[1] != null && data[1] instanceof EntityPlayer)
			{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !(Minecraft.getMinecraft().currentScreen instanceof GuiInventory && RenderManager.instance.playerViewY == 180.0F)))
				{
					GL11.glTranslatef(-.7950F, -.8F, -.066F);//third person
				}
				else
				{
					isFirstPerson = true;
					GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
					GL11.glTranslatef(-.295F, -1.525F, -.4019F);//first person
					if(((EntityPlayer)data[1]).getItemInUseCount() > 0 ) /*item.getItem().getMaxItemUseDuration(item)) */
					{
						EnumAction action = item.getItemUseAction();
						if (action == EnumAction.bow)
						{
							GL11.glTranslatef(-0.0F, 0.7F, 0.4F);
						}
						GL11.glEnable(GL12.GL_RESCALE_NORMAL);
						for (int i = 0; i < 3; i++) {
							double angle1 = (Math.random() * 1 * Math.PI);
							double angle2 = (Math.random() * 1 * Math.PI);
							drawLightning(Math.cos(angle1) * 1, Math.sin(angle1) * 1, Math.cos(angle1) * 1, Math.cos(angle2) * 1, Math.cos(angle2) * 1, Math.cos(angle2) * 1);
						}

						GL11.glDisable(GL12.GL_RESCALE_NORMAL);
					}
				}
			}
			else
			{
				GL11.glTranslatef(-.7950F, -.8F, -.066F);//third other person
				if(((EntityPlayer)data[1]).getItemInUseCount() > 0 ) /*item.getItem().getMaxItemUseDuration(item)) */
				{
					EnumAction action = item.getItemUseAction();
					if (action == EnumAction.bow)
					{
						/*GL11.glRotatef(-18.0F, 0.0F, 0.0F, 1.0F);
	                    GL11.glRotatef(-12.0F, 0.0F, 1.0F, 0.0F);
	                    GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);*/
						//GL11.glTranslatef(-0.0F, 0.7F, 0.4F);
						/*float var13 = (float)item.getMaxItemUseDuration() - ((float)((EntityPlayer)data[1]).getItemInUseCount() - 0.0625F + 1.0F);
	                    float var14 = var13 / 20.0F;
	                    var14 = (var14 * var14 + var14 * 2.0F) / 3.0F;

	                    if (var14 > 1.0F)
	                    {
	                        var14 = 1.0F;
	                    }

	                    if (var14 > 0.1F)
	                    {
	                        GL11.glTranslatef(0.0F, MathHelper.sin((var13 - 0.1F) * 1.3F) * 0.01F * (var14 - 0.1F), 0.0F);
	                    }

	                    GL11.glTranslatef(0.0F, 0.0F, var14 * 0.1F);
	                    GL11.glRotatef(-335.0F, 0.0F, 0.0F, 1.0F);
	                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
	                    GL11.glTranslatef(0.0F, 0.5F, 0.0F);
	                    float var15 = 1.0F + var14 * 0.2F;
	                    GL11.glScalef(1.0F, 1.0F, var15);
	                    GL11.glTranslatef(0.0F, -0.5F, 0.0F);
	                    GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
	                    GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);*/
					}
					GL11.glEnable(GL12.GL_RESCALE_NORMAL);
					for (int i = 0; i < 1; i++) {
						double angle1 = (Math.random() * 1 * Math.PI);
						double angle2 = (Math.random() * 1 * Math.PI);
						drawLightning(Math.cos(angle1) * 1, Math.sin(angle1) * 1, Math.cos(angle1) * 1, Math.cos(angle2) * 1, Math.cos(angle2) * 1, Math.cos(angle2) * 1);
					}

					GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				}
			}

			int color = item.getItem().getColorFromItemStack(item, 0);
			GL11.glColor4f((color >> 16 & 0xFF)/255.0F, (color >> 8 & 0xFF)/255.0F, (color & 0xFF)/255.0F, 1);
			this.buster.render((Entity)data[1], 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.buster.render2((Entity)data[1], 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
			break;
		}
		case INVENTORY:
		{
			break;
		}
		default:
		{
			break;
		}
		}

	}
	public static void drawLightning(double x1, double y1, double z1, double x2, double y2, double z2/*, Colour colour*/) {
		double tx = x2 - x1, ty = y2 - y1, tz = z2 - z1, cx = 0, cy = 0, cz = 0;
		double jagfactor = 0.3;
		texturelessOn();
		blendingOn();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glBegin(GL11.GL_LINE_STRIP);
		while (Math.abs(cx) < Math.abs(tx) && Math.abs(cy) < Math.abs(ty) && Math.abs(cz) < Math.abs(tz)) {
			GL11.glColor4d(1, 1, 1, 1);
			// GL11.glLineWidth(1);
			double ox = x1 + cx;
			double oy = y1 + cy;
			double oz = z1 + cz;
			cx += Math.random() * tx * jagfactor - 0.1 * tx;
			cy += Math.random() * ty * jagfactor - 0.1 * ty;
			cz += Math.random() * tz * jagfactor - 0.1 * tz;
			GL11.glVertex3d(x1 + cx, y1 + cy, z1 + cz);

			// GL11.glLineWidth(3);
			// colour.withAlpha(0.5).doGL();
			// GL11.glVertex3d(ox, oy, oz);
			//
			// GL11.glLineWidth(5);
			// colour.withAlpha(0.1).doGL();
			// GL11.glVertex3d(x1 + cx, y1 + cy, z1 + cz);
		}
		GL11.glEnd();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_CULL_FACE);
		//blendingOff();
		texturelessOff();

	}
	/**
	 * Call before doing any pure geometry (ie. with colours rather than
	 * textures).
	 */
	public static void texturelessOn() {
		GL11.glDisable(GL11.GL_TEXTURE_2D);

	}

	/**
	 * Call after doing pure geometry (ie. with colours) to go back to the
	 * texture mode (default).
	 */
	public static void texturelessOff() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	/**
	 * Call before doing anything with alpha blending
	 */
	public static void blendingOn() {
		if (Minecraft.getMinecraft().isFancyGraphicsEnabled()) {
			GL11.glShadeModel(GL11.GL_SMOOTH);
			// GL11.glEnable(GL11.GL_LINE_SMOOTH);
			// GL11.glEnable(GL11.GL_POLYGON_SMOOTH);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}
	}

	/**
	 * Call after doing anything with alpha blending
	 */
	public static void blendingOff() {
		GL11.glShadeModel(GL11.GL_FLAT);
		//GL11.glDisable(GL11.GL_LINE_SMOOTH);
		//GL11.glDisable(GL11.GL_POLYGON_SMOOTH);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
}