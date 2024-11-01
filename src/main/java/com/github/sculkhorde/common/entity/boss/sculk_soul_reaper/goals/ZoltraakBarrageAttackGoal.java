package com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.goals;

import com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.SculkSoulReaperEntity;
import com.github.sculkhorde.util.EntityAlgorithms;
import com.github.sculkhorde.util.TickUnits;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.Collections;

public class ZoltraakBarrageAttackGoal extends Goal
{
    private final SculkSoulReaperEntity mob;
    protected int maxAttackDuration = 0;
    protected int elapsedAttackDuration = 0;
    protected final int executionCooldown = TickUnits.convertSecondsToTicks(10);
    protected int ticksElapsed = executionCooldown;
    protected int attackIntervalTicks = TickUnits.convertSecondsToTicks(0.5F);
    protected int attackkIntervalCooldown = 0;
    protected int minDifficulty = 0;
    protected int maxDifficulty = 0;
    ArrayList<LivingEntity> targets = new ArrayList<>();


    public ZoltraakBarrageAttackGoal(SculkSoulReaperEntity mob, int durationInTicks, int minDifficulty, int maxDifficulty) {
        this.mob = mob;
        maxAttackDuration = durationInTicks;
        this.minDifficulty = minDifficulty;
        this.maxDifficulty = maxDifficulty;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public boolean canUse()
    {
        ticksElapsed++;

        if(mob.getTarget() == null)
        {
            return false;
        }

        if(ticksElapsed < executionCooldown)
        {
            return false;
        }

        if(!mob.getSensing().hasLineOfSight(mob.getTarget()))
        {
            return false;
        }

        if(mob.getMobDifficultyLevel() < minDifficulty)
        {
            return false;
        }

        if(mob.getMobDifficultyLevel() > maxDifficulty && maxDifficulty != -1)
        {
            return false;
        }

        return true;
    }

    @Override
    public boolean canContinueToUse()
    {
        return elapsedAttackDuration < maxAttackDuration;
    }

    protected BlockPos getRandomBlockPosAboveEntity()
    {
        int xOffset = mob.getRandom().nextInt(-2, 2);
        int yOffset = mob.getRandom().nextInt(-2, 2);
        int zOffset = mob.getRandom().nextInt(-2, 2);

        return new BlockPos(mob.getBlockX() + xOffset, mob.getBlockY() + 5 + yOffset, mob.getBlockZ() + zOffset);
    }

    @Override
    public void start()
    {
        super.start();
        //getEntity().triggerAnim("attack_controller", "fireball_sky_summon_animation");
        //getEntity().triggerAnim("twitch_controller", "fireball_sky_twitch_animation");
    }

    protected void populateTargetList()
    {
        int maxTargets = 5;

        AABB targetHitBox = EntityAlgorithms.createBoundingBoxCubeAtBlockPos(mob.position(), 10);
        targets.addAll(EntityAlgorithms.getHostileEntitiesInBoundingBox((ServerLevel) mob.level(), targetHitBox));

        if(targets.size() < maxTargets)
        {
            targets.clear();
            targetHitBox = EntityAlgorithms.createBoundingBoxCubeAtBlockPos(mob.position(), 20);
            targets.addAll(EntityAlgorithms.getHostileEntitiesInBoundingBox((ServerLevel) mob.level(), targetHitBox));
            //SculkHorde.LOGGER.debug("ZoltraakBarrageAttackGoal | Expanding hitbox to length of 20");
        }

        if(targets.size() < maxTargets)
        {
            targets.clear();
            targetHitBox = EntityAlgorithms.createBoundingBoxCubeAtBlockPos(mob.position(), 30);
            targets.addAll(EntityAlgorithms.getHostileEntitiesInBoundingBox((ServerLevel) mob.level(), targetHitBox));
            //SculkHorde.LOGGER.debug("ZoltraakBarrageAttackGoal | Expanding hitbox to length of 30");
        }

        if(targets.size() < maxTargets)
        {
            targets.clear();
            targetHitBox = EntityAlgorithms.createBoundingBoxCubeAtBlockPos(mob.position(), 40);
            targets.addAll(EntityAlgorithms.getHostileEntitiesInBoundingBox((ServerLevel) mob.level(), targetHitBox));
            //SculkHorde.LOGGER.debug("ZoltraakBarrageAttackGoal | Expanding hitbox to length of 40");
        }

        if(targets.size() < maxTargets)
        {
            targets.clear();
            targetHitBox = EntityAlgorithms.createBoundingBoxCubeAtBlockPos(mob.position(), 50);
            targets.addAll(EntityAlgorithms.getHostileEntitiesInBoundingBox((ServerLevel) mob.level(), targetHitBox));
            //SculkHorde.LOGGER.debug("ZoltraakBarrageAttackGoal | Expanding hitbox to length of 50");
        }

        if(targets.size() < maxTargets)
        {
            targets.clear();
            targetHitBox = EntityAlgorithms.createBoundingBoxCubeAtBlockPos(mob.position(), 60);
            targets.addAll(EntityAlgorithms.getHostileEntitiesInBoundingBox((ServerLevel) mob.level(), targetHitBox));
            //SculkHorde.LOGGER.debug("ZoltraakBarrageAttackGoal | Expanding hitbox to length of 60");
        }

        if(targets.size() < maxTargets && targets.isEmpty())
        {
            elapsedAttackDuration = maxAttackDuration;
        }


        Collections.shuffle(targets);


        // Trim the list to the desired size
        while (targets.size() > maxTargets) {
            targets.remove(targets.size() - 1);
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        elapsedAttackDuration++;

        if(targets.isEmpty())
        {
            populateTargetList();
            return;
        }
        mob.setTarget(targets.get(0));
        targets.remove(0);

        spawnSoulAndShootAtTarget(5);
    }

    @Override
    public void stop()
    {
        super.stop();
        elapsedAttackDuration = 0;
        ticksElapsed = 0;
    }


    public void spawnSoulAndShootAtTarget(int range)
    {
        attackkIntervalCooldown--;

        if(attackkIntervalCooldown > 0)
        {
            return;
        }

        if(mob.getTarget() == null)
        {
            return;
        }

        SculkSoulReaperEntity.shootZoltraakBeam(getRandomBlockPosAboveEntity().getCenter(), mob, mob.getTarget(), 8F, 0.3F, 10);

        attackkIntervalCooldown = attackIntervalTicks;
    }

}
//projectileEntity.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - mob.level().getDifficulty().getId() * 4));