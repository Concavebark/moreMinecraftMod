package concavebark.moreMinecraftMod.client.screen.inventory;

import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BreadOvenScreen extends AbstractFurnaceScreen<FurnaceContainer> {
	private static final ResourceLocation BREADOVEN_GUI_TEXTURES = new ResourceLocation("textures/gui/container/bread_oven.png");

	public BreadOvenScreen(FurnaceContainer p_i51104_1_, AbstractRecipeBookGui p_i51104_2_, PlayerInventory p_i51104_3_,
			ITextComponent p_i51104_4_, ResourceLocation p_i51104_5_) {
		super(p_i51104_1_, p_i51104_2_, p_i51104_3_, p_i51104_4_, BREADOVEN_GUI_TEXTURES);
	}
	
}
