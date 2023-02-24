package net.nikell.eterus.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nikell.eterus.EterusMod;
import net.nikell.eterus.block.custom.*;
import net.nikell.eterus.item.ModCreativeModeTab;
import net.nikell.eterus.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EterusMod.MOD_ID);

    public static final RegistryObject<Block> ETERIUM_BLOCK = registerBlock("eterium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
            .strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.ETERUSMOD_TAB);

    public static final RegistryObject<Block> ETERIUM_ORE = registerBlock("eterium_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
            .strength(4f).requiresCorrectToolForDrops(),
                    UniformInt.of(3,7)), ModCreativeModeTab.ETERUSMOD_TAB);
    public static final RegistryObject<Block> DEEPSLATE_ETERIUM_ORE = registerBlock("deepslate_eterium_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops(),
                    UniformInt.of(3,7)), ModCreativeModeTab.ETERUSMOD_TAB);

    //Pathways
    public static final RegistryObject<Block> PATHWAY_AIR_BLOCK = registerBlock("pathway_air_block",
            () -> new PathwayAirBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f)), ModCreativeModeTab.ETERUSMOD_TAB);
    public static final RegistryObject<Block> PATHWAY_FIRE_BLOCK = registerBlock("pathway_fire_block",
            () -> new PathwayFireBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f)), ModCreativeModeTab.ETERUSMOD_TAB);
    public static final RegistryObject<Block> PATHWAY_WATER_BLOCK = registerBlock("pathway_water_block",
            () -> new PathwayWaterBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f)), ModCreativeModeTab.ETERUSMOD_TAB);
    public static final RegistryObject<Block> PATHWAY_EARTH_BLOCK = registerBlock("pathway_earth_block",
            () -> new PathwayEarthBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f)), ModCreativeModeTab.ETERUSMOD_TAB);

    public static final RegistryObject<Block> ETERIUM_LAMP = registerBlock("eterium_lamp",
            () -> new EteriumLampBlock(BlockBehaviour.Properties.of(Material.GLASS)
                    .strength(3f).lightLevel(state -> state.getValue(EteriumLampBlock.LIT) ? 15 : 0)), ModCreativeModeTab.ETERUSMOD_TAB);

    //Crops
    public static final RegistryObject<Block> HOP_CROP = BLOCKS.register("hop_crop",
            () -> new HopCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);

        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {

        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
