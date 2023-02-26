package net.nikell.eterus.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EightBallItem extends Item {
    public EightBallItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {

        if(!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND){
            // Output a Random Number
            outputRandomNumber(player);
            // Set a Cooldown
            player.getCooldowns().addCooldown(this, 200);
            //Add Player Effects
            if(getRandomNumber() == 0) {
                player.addEffect(new MobEffectInstance(MobEffects.WITHER, 200));
            }
            if(getRandomNumber() == 1) {
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 200));
            }
            if(getRandomNumber() == 2) {
                player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 600));
            }
            if(getRandomNumber() == 3) {
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 500));
            }
            if(getRandomNumber() == 4) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 400, 2));
            }
            if(getRandomNumber() == 5) {
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200));
            }
            if(getRandomNumber() == 6) {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 500,1));
            }
            if(getRandomNumber() == 7) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 800));
            }
            if(getRandomNumber() == 8) {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1000, 2));
            }
        }

        return super.use(level, player, interactionHand);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()){
            components.add(Component.literal("Right click to get a random number 0-8").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
        else {
            components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.DARK_PURPLE));
        }


        super.appendHoverText(itemStack, level, components, flag);
    }

    private void outputRandomNumber(Player player){
        player.sendSystemMessage(Component.literal("Your Number is " + getRandomNumber()));
    }

    private int getRandomNumber() {
        return RandomSource.createNewThreadLocalInstance().nextInt(9);
    }
}
