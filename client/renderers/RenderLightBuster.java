package zornco.reploidcraft.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderLightBuster implements IItemRenderer
{
	public ModelXBuster buster;

	public RenderLightBuster()
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
					GL11.glTranslatef(-.7950F, -.8F, -.066F);//extra offset when in third person
				}
				else
				{
					isFirstPerson = true;
					//play with these values till the hammer is positioned and sized right
					GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
					GL11.glTranslatef(-.295F, -1.525F, -.4019F);//extra offset when in first person
					if(((EntityPlayer)data[1]).getItemInUseCount() > 0 ) 
					{
						EnumAction action = item.getItemUseAction();
						if (action == EnumAction.bow)
						{
							GL11.glTranslatef(-0.0F, 0.7F, 0.4F);
						}
					}
				}
			}
			else
			{
				GL11.glTranslatef(-.7950F, -.8F, -.066F);
				if(((EntityPlayer)data[1]).getItemInUseCount() > 0 ) 
				{
					EnumAction action = item.getItemUseAction();
					if (action == EnumAction.bow)
					{
						//forgot what happens here, lol
						
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
}