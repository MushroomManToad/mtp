package mmt.playground.charm;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class Augment 
{
	String name;
	TranslationTextComponent displayName;
	Object defaultValue;
	ResourceLocation sprite;
	/**
	 * @param name A string used for the internal name
	 * @param displayName A TranslationTextComponent used for displaying the Augment name on hover-over
	 * @param defaultValue Gives the default value this Augment should have. Can take a boolean or int
	 */
	public Augment(String name, TranslationTextComponent displayName, Object defaultValue, ResourceLocation sprite)
	{
		this.name = name;
		this.displayName = displayName;
		this.defaultValue = defaultValue;
		this.sprite = sprite;
	}
	
	public String getName() {return name;}
	public TranslationTextComponent getDisplayName() {return displayName;}
	public Object getDefaultValue() {return defaultValue;}
	public ResourceLocation getSprite() {return sprite;}
}
