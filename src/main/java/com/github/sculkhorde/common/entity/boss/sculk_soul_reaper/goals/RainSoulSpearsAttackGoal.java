package com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.goals;

import com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.SculkSoulReaperEntity;
import com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.SoulSpearProjectileEntity;
import com.github.sculkhorde.common.entity.projectile.AbstractProjectileEntity;
import com.github.sculkhorde.util.TickUnits;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.Vec3;

public class RainSoulSpearsAttackGoal extends ReaperCastSpellGoal
{
    protected final long SPELL_DURATION = TickUnits.convertSecondsToTicks(5);
    protected long spellStartTime = 0;

    protected final long ATTACK_INTERVAL = TickUnits.convertSecondsToTicks(0.25F);
    protected long lastTimeOfAttack = 0;

    public RainSoulSpearsAttackGoal(SculkSoulReaperEntity mob) {
        super(mob);
    }

    @Override
    protected boolean mustSeeTarget() {
        return false;
    }

    @Override
    public void start()
    {
        super.start();
        if(mob.level().isClientSide())
        {
            return;
        }

        spellStartTime = mob.level().getGameTime();
        this.mob.getNavigation().stop();
    }

    @Override
    protected void doAttackTick() {

        if(Math.abs(mob.level().getGameTime() - spellStartTime) >= SPELL_DURATION)
        {
            setSpellCompleted();
        }

        if(Math.abs(mob.level().getGameTime() - lastTimeOfAttack) > ATTACK_INTERVAL)
        {
            shootProjectileAtTarget();
            lastTimeOfAttack = mob.level().getGameTime();
        }
    }


    public double getRandomDoubleInRange(double min, double max)
    {
        return min + (mob.getRandom().nextFloat() * (max + min));
    }

    protected Vec3 getRandomBlockPosAboveTarget()
    {
        int xOffset = mob.getTarget().getRandom().nextInt(-2, 2);
        int yOffset = mob.getTarget().getRandom().nextInt(-2, 2);
        int zOffset = mob.getTarget().getRandom().nextInt(-2, 2);

        return new Vec3(mob.getTarget().getBlockX() + xOffset, mob.getTarget().getBlockY() + 20 + yOffset, mob.getTarget().getBlockZ() + zOffset);
    }

    public void shootProjectileAtTarget()
    {

        if(mob.getTarget() == null)
        {
            return;
        }

        AbstractProjectileEntity projectile =  new SoulSpearProjectileEntity(mob.level(), mob, 20F);

        Vec3 spawnPos = getRandomBlockPosAboveTarget();
        projectile.setPos(spawnPos);

        double targetPosX = mob.getTarget().getX() - spawnPos.x()  + getRandomDoubleInRange(0, 1);
        double targetPosY = mob.getTarget().getEyePosition().y() - spawnPos.y() + getRandomDoubleInRange(0, 1);
        double targetPosZ = mob.getTarget().getZ() - spawnPos.z() + getRandomDoubleInRange(0, 1);

        // Create a vector for the direction
        Vec3 direction = new Vec3(targetPosX, targetPosY, targetPosZ).normalize();

        // Shoot the projectile in the direction vector
        projectile.shoot(direction);

        mob.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 1.0F / (mob.getRandom().nextFloat() * 0.4F + 0.8F));
        mob.level().addFreshEntity(projectile);
    }
}