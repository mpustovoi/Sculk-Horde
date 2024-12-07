package com.github.sculkhorde.common.entity.boss.sculk_soul_reaper;

import com.github.sculkhorde.common.entity.boss.SpecialEffectEntity;
import com.github.sculkhorde.core.ModEntities;
import com.github.sculkhorde.util.TickUnits;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * The following java files were created/edited for this entity.<br>
 * Edited {@link ModEntities}<br>
 * Edited {@link com.github.sculkhorde.client.ClientModEventSubscriber}<br>
 * Added {@link SoulSpearSummonerEntity}<br>
 * Added {@link com.github.sculkhorde.client.model.enitity.ChaosTeleporationRiftModel}<br>
 * Added {@link com.github.sculkhorde.client.renderer.entity.ChaosTeleporationRiftRenderer}
 */
public class SoulSpearSummonerEntity extends SpecialEffectEntity implements GeoEntity
{
    public static int LIFE_TIME = TickUnits.convertSecondsToTicks(10);
    public int currentLifeTicks = 0;




    public SoulSpearSummonerEntity(EntityType<?> entityType, Level level)
    {
        super(entityType, level);
    }


    @Override
    public void tick() {
        super.tick();

        if(level().isClientSide()) { return; }

        if (getOwner() != null && !getOwner().isAlive()) {
            this.discard();
            return;
        }


        currentLifeTicks++;

        // If the entity is alive for more than LIFE_TIME, discard it
        if(currentLifeTicks >= LIFE_TIME && LIFE_TIME != -1) this.discard();

    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    // Data Code

    // Animation Code

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(
                DefaultAnimations.genericLivingController(this)
        );
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
