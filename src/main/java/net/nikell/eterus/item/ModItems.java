package net.nikell.eterus.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nikell.eterus.EterusMod;
import net.nikell.eterus.item.custom.EightBallItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EterusMod.MOD_ID);

    public static final RegistryObject<Item> ETERIUM = ITEMS.register("eterium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ETERUSMOD_TAB)));
    public static final RegistryObject<Item> RAW_ETERIUM = ITEMS.register("raw_eterium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ETERUSMOD_TAB)));

    public static final RegistryObject<Item> EIGHT_BALL = ITEMS.register("eight_ball",
            () -> new EightBallItem(new Item.Properties().tab(ModCreativeModeTab.ETERUSMOD_TAB).stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
