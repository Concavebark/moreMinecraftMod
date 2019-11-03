package concavebark.moreMinecraftMod.client.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class BreadOvenTileEntity extends AbstractFurnaceTileEntity {

	protected BreadOvenTileEntity(TileEntityType<?> tileTypeIn, IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn) {
		super(TileEntityType.FURNACE, IRecipeType.SMELTING);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.furnace");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new FurnaceContainer(id, player, this, this.furnaceData);
	}
	
}
