package concavebark.moreMinecraftMod;

import concavebark.moreMinecraftMod.lists.BlockList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class moreMinecraftModItemGroup extends ItemGroup {

	public moreMinecraftModItemGroup() {
		super("moreminecraft");
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(Item.BLOCK_TO_ITEM.get(BlockList.tutorial_block));
	}
}
