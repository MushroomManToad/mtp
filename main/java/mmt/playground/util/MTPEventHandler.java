package mmt.playground.util;

import java.util.ArrayList;

import com.mojang.blaze3d.systems.RenderSystem;

import mmt.playground.Main;
import mmt.playground.charm.Augment;
import mmt.playground.charm.CharmLoader;
import mmt.playground.client.gui.charms.ContainerCharm;
import mmt.playground.client.gui.charms.GUICharms;
import mmt.playground.items.CharmItem;
import mmt.playground.network.CtoSSyncFallDamagePacket;
import mmt.playground.network.MTPPacketHandler;
import mmt.playground.util.key.KeyBindingList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.FORGE)
@SuppressWarnings("deprecation")
public final class MTPEventHandler {
	
	static double fallSpeed = 0.1;
	static double jumpAmount = 0.5;
	
	/**
	 * Replace this with a charm system detection
	 */
	static boolean hasMC = true;
	
	/**
	 * Replace this with a charm system detection
	 */
	static boolean isHK = true;
	
	static ArrayList<Augment> aug;
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onCharmHover(final RenderTooltipEvent.PostBackground event)
	{
		if(event.getStack().getItem() instanceof CharmItem)
		{
			RenderSystem.pushMatrix();
			
			RenderSystem.translated(0, 0, 300);			
			
			for(int i = 0; i < ((CharmItem) event.getStack().getItem()).getAugments().size(); i++)
			{

				Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(new ItemStack(
						ForgeRegistries.ITEMS.getValue(((CharmItem) event.getStack().getItem()).getAugments().get(i).getSprite()))
						, -2 + event.getX()
						, 10 + (16 * i) + event.getY());
			}
		    
		    RenderSystem.popMatrix();
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onSpacePress(final InputUpdateEvent event)
	{
		PlayerEntity playerIn = event.getPlayer();
		World worldIn = playerIn.getEntityWorld();
		CompoundNBT nbt = playerIn.getPersistentData();
		
		if(isHK)
		{
			if(hasMC)
			{
				if(!nbt.contains("wallHopLock") || (nbt.getBoolean("wallHopLock") && !event.getMovementInput().jump))
				{
					nbt.putBoolean("wallHopLock", false);
				}
				
				if(event.getMovementInput().jump && !nbt.getBoolean("wallHopLock"))
				{
					if(playerIn.collidedHorizontally && !playerIn.isOnGround())
					{
						double offsetMotX = 0;
						double offsetMotZ = 0;
						
						// East
						if(computeVDirection(worldIn, playerIn, 1, 0, 0))
						{
							offsetMotX -= jumpAmount / 4;
						}
						
						// West
						
						if(computeVDirection(worldIn, playerIn, -1, 0, 0))
						{
							offsetMotX += jumpAmount / 4;
						}
						
						// South
						
						if(computeVDirection(worldIn, playerIn, 0, 0, 1))
						{
							offsetMotZ -= jumpAmount / 4;
						}
						
						// North
	
						if(computeVDirection(worldIn, playerIn, 0, 0, -1))
						{
							offsetMotZ += jumpAmount / 4;
						}
						
						if(worldIn.getBlockState(playerIn.getPosition()).getCollisionShape(worldIn, playerIn.getPosition()).isEmpty())
						playerIn.setMotion(playerIn.getMotion().x + offsetMotX, jumpAmount, playerIn.getMotion().z + offsetMotZ);
					}
					nbt.putBoolean("wallHopLock", true);
				}
			}
		}
	}
	
	private static boolean computeVDirection(World worldIn, PlayerEntity playerIn, double x, double y, double z)
	{
		AxisAlignedBB aabb = null;
		AxisAlignedBB pAABB = playerIn.getBoundingBox();
		Vector3i v3 = new Vector3i(x, y, z);
		VoxelShape cS3 = worldIn.getBlockState(playerIn.getPosition().add(v3)).getCollisionShape(worldIn, playerIn.getPosition().add(v3));
		if(!cS3.isEmpty())
		{
			aabb = cS3.getBoundingBox().offset(playerIn.getPosition().add(v3));
		}
		if(aabb != null && pAABB.intersects(aabb.grow(0.01)))
		{
			return true;
		}
		return false;
	}
	
	@SubscribeEvent
	public static void onFall(final LivingFallEvent event)
	{
		float f1 = event.getEntityLiving().fallDistance;
		
		if(isHK)
		{
			if(f1 < 4)
			{
				event.setCanceled(true);
			}
			else
			{
				event.setDistance(event.getDistance() / 4);
				
				World worldIn = event.getEntity().getEntityWorld();
				BlockPos pos = event.getEntity().getPosition();
				if(worldIn.isRemote)
					if(event.getDistance() < 3 && event.getDistance() > 1)
					{
						worldIn.playSound((int)pos.getX(), (int)pos.getY(), (int)pos.getZ(), SoundEvents.ENTITY_PLAYER_SMALL_FALL, SoundCategory.PLAYERS, 0.2f, 0.0f, false);
					}
			}
		}
	}
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onClientTick(final ClientTickEvent event)
	{
        if (KeyBindingList.KeyBindings[0].isPressed())
        {
        	ClientPlayerEntity playerIn = Minecraft.getInstance().player;
        	Minecraft.getInstance().displayGuiScreen(new GUICharms(playerIn, new ContainerCharm(playerIn.openContainer.windowId, playerIn.inventory)));
        }
	}
	
	@SubscribeEvent
	public static void onPlayerTick(final TickEvent.PlayerTickEvent event)
	{
		PlayerEntity playerIn = event.player;
		if(isHK)
		{
			if(hasMC)
			{
				if(playerIn.collidedHorizontally && playerIn.fallDistance > 0)
				{
					playerIn.setMotion(playerIn.getMotion().x, -fallSpeed, playerIn.getMotion().z);
					playerIn.fallDistance = 0.0F;
					MTPPacketHandler.CHANNEL.sendToServer(new CtoSSyncFallDamagePacket(playerIn.fallDistance));
				}
			}
		}
	}
}
