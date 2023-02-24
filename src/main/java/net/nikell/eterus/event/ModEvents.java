package net.nikell.eterus.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nikell.eterus.EterusMod;
import net.nikell.eterus.item.ModItems;
import net.nikell.eterus.villager.ModVillagers;

import java.util.List;

@Mod.EventBusSubscriber(modid = EterusMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.EIGHT_BALL.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    stack, 10,8,0.02F));
        }

        if(event.getType() == ModVillagers.MYSTERY_BREWMASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.HOP_SEEDS.get(), 7);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    stack, 10,8,0.02F));
        }
    }
}
