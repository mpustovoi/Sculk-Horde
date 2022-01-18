package com.github.sculkhoard.common.entity.entity_factory;

import com.github.sculkhoard.common.entity.SculkLivingEntity;
import net.minecraft.entity.EntityType;

import javax.annotation.Nullable;

/**
 * This class is only used in the EntityFactory class which stores a list
 * of these entries. It is simply to store an EntityType and how much
 * sculk mass is required to spawn it.
 */
public class EntityFactoryEntry {

    private int orderCost = 0;
    private EntityType entity = null;

    public EntityFactoryEntry(EntityType entity, int orderCost)
    {
        this.entity = entity;
        this.orderCost = orderCost;
    }

    public int getCost()
    {
        return orderCost;
    }

    @Nullable
    public EntityType getEntity()
    {
        return entity;
    }

}