package net.nikell.eterus.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PathwayAirBlock extends Block {

    public PathwayAirBlock(Properties properties) {
        super(properties);
    }

    /*
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {

        if (player.level.isClientSide) {
            player.sendSystemMessage(Component.literal("Right Clicked this!"));
        }
        player.sendSystemMessage(Component.literal("Right Clicked this!"));

        return super.use(state, level, blockPos, player, hand, blockHitResult);
    }*/

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if(entity instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 100));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));

        }

        super.stepOn(level, pos, state, entity);
    }
}
