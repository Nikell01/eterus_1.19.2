package net.nikell.eterus.villager;

import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nikell.eterus.EterusMod;
import net.nikell.eterus.block.ModBlocks;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, EterusMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, EterusMod.MOD_ID);

    public static final RegistryObject<PoiType> PATHWAY_EARTH_BLOCK_POI = POI_TYPES.register("pathway_earth_block_poi",
            ()-> new PoiType(ImmutableSet.copyOf(ModBlocks.PATHWAY_EARTH_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> MYSTERY_BREWMASTER = VILLAGER_PROFESSIONS.register("mystery_brewmaster",
            () -> new VillagerProfession("mystery_brewmaster", x -> x.get() == PATHWAY_EARTH_BLOCK_POI.get(),
                    x -> x.get() == PATHWAY_EARTH_BLOCK_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER));


    public static void registerPOIs(){
        try{
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, PATHWAY_EARTH_BLOCK_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception){
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
