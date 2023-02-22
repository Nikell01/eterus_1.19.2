package net.nikell.eterus.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class EteriumLampBlock extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public EteriumLampBlock(Properties properties) {

        super(properties);
    }
}
