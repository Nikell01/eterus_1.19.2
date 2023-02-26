package net.nikell.eterus.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier MYSTERY = new ForgeTier(5, 3000, 1f,
            5f, 25, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ModItems.MYSTERY_STEEL.get()));
}
