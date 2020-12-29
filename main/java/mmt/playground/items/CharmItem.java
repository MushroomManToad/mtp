package mmt.playground.items;

import java.util.ArrayList;
import java.util.List;

import mmt.playground.charm.Augment;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CharmItem extends Item
{	
	ArrayList<Augment> augments = new ArrayList<>();
	
	public CharmItem(Properties properties, Augment...augments) 
	{
		super(properties);
		for(Augment a : augments)
		{
			this.augments.add(a);
		}
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(new StringTextComponent(""));
		super.addInformation(stack, worldIn, tooltip, flagIn);
		for(int i = 0; i < augments.size(); i++)
		{
			tooltip.add(new StringTextComponent(""));
		}
	}
	
	public ArrayList<Augment> getAugments()
	{
		return this.augments;
	}
}
