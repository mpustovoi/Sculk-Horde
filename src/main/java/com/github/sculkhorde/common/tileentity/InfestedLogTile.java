package com.github.sculkhorde.common.tileentity;

import com.github.sculkhorde.common.block.BlockInfestation.SpreadingTile;
import com.github.sculkhorde.core.TileEntityRegistry;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeTileEntity;

public class InfestedLogTile extends SpreadingTile implements IForgeTileEntity {

    /**
     * The Constructor that takes in properties
     * @param type The Tile Entity Type
     */
    public InfestedLogTile(TileEntityType<?> type) {
        super(type);
    }

    /**
     * A simpler constructor that does not take in entity type.<br>
     * I made this so that registering tile entities can look cleaner
     */
    public InfestedLogTile() {
        this(TileEntityRegistry.INFESTED_LOG_TILE.get());
    }

    @Override
    protected boolean doesSpreadRandomly()
    {
        return false;
    }


}