package net.nikell.eterus.world.feature;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nikell.eterus.EterusMod;
import net.nikell.eterus.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, EterusMod.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ETERIUM_ORES = Suppliers.memoize(()-> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ETERIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ETERIUM_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_ETERIUM_ORES = Suppliers.memoize(()-> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.NETHERRACK_ETERIUM_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> END_ETERIUM_ORES = Suppliers.memoize(()-> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), ModBlocks.END_ETERIUM_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ETERIUM_ORE = CONFIGURED_FEATURES.register("eterium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_ETERIUM_ORES.get(), 5)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> END_ETERIUM_ORE = CONFIGURED_FEATURES.register("end_eterium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(END_ETERIUM_ORES.get(), 9)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHERRACK_ETERIUM_ORE = CONFIGURED_FEATURES.register("nether_eterium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_ETERIUM_ORES.get(), 7)));


    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
