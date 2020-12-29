package mmt.playground.client.gui.charms;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import mmt.playground.Main;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

@SuppressWarnings("deprecation")
public class GUICharms extends ContainerScreen<ContainerCharm>
{
	static final ResourceLocation CHARM_BACKGROUND = new ResourceLocation(Main.MODID, "textures/gui/charms.png");
	
	public GUICharms(ContainerCharm container, PlayerInventory player, ITextComponent name) {
		super(container, player, name);
	}
	
	public GUICharms(PlayerEntity player, ContainerCharm container) 
	{
		super(container, player.inventory, new TranslationTextComponent("mtp.container.charms"));
		this.passEvents = true;
		this.titleX = 97;
	}
	
	public void tick()
	{
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) 
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(CHARM_BACKGROUND);
		int i = this.guiLeft;
		int j = this.guiTop;
		this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
	}
}
