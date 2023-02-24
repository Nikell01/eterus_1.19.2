package net.nikell.eterus.block.custom;

import net.minecraft.client.particle.DustParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class PathwayAirBlock extends Block {

    public PathwayAirBlock(Properties properties) {
        super(properties);
    }

    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if(entity instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 100));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));

            if (!entity.isSteppingCarefully()) {
                interact(state, level, pos);
            }
            //level.setBlock(pos, state.cycle(LIT), 3);
        }

        super.stepOn(level, pos, state, entity);
    }

    private static void interact(BlockState state, Level level, BlockPos pos) {
        spawnParticles(level, pos);
        if (!state.getValue(LIT)) {
            level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(true)), 3);
        }

    }

    private static void spawnParticles(Level level, BlockPos blockPos) {
        double d0 = 0.5625D;
        RandomSource randomsource = level.random;

        for(Direction direction : Direction.values()) {
            BlockPos blockpos = blockPos.relative(direction);
            if (!level.getBlockState(blockpos).isSolidRender(level, blockpos)) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)randomsource.nextFloat();
                double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)randomsource.nextFloat();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)randomsource.nextFloat();
                level.addParticle(DustParticleOptions.REDSTONE, (double)blockPos.getX() + d1, (double)blockPos.getY() + d2, (double)blockPos.getZ() + d3, 0.0D, 0.0D, 0.0D);
            }
        }

    }

    public boolean isRandomlyTicking(BlockState p_55486_) {
        return p_55486_.getValue(LIT);
    }

    public void randomTick(BlockState p_221918_, ServerLevel p_221919_, BlockPos p_221920_, RandomSource p_221921_) {
        if (p_221918_.getValue(LIT)) {
            p_221919_.setBlock(p_221920_, p_221918_.setValue(LIT, Boolean.valueOf(false)), 3);
        }

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
