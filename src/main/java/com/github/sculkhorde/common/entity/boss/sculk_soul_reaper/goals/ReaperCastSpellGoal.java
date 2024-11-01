package com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.goals;

import com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.SculkSoulReaperEntity;
import com.github.sculkhorde.common.entity.goal.AttackStepGoal;
import com.github.sculkhorde.util.TickUnits;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class ReaperCastSpellGoal extends AttackStepGoal {
    protected final SculkSoulReaperEntity mob;
    protected int cooldownTicksElapsed = getExecutionCooldown();
    protected int castingTime = 0;
    protected boolean spellCasted = false;
    protected float DAMAGE = 8F;
    protected int minDifficulty = 0;
    protected int maxDifficulty = 0;
    protected boolean isNextSpellToCast = false;
    protected boolean isPartOfAttackSequence = false;


    public ReaperCastSpellGoal(SculkSoulReaperEntity mob, int minDifficulty, int maxDifficulty) {
        this.mob = mob;
        this.minDifficulty = minDifficulty;
        this.maxDifficulty = maxDifficulty;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }


    protected int getBaseCastingTime() { return TickUnits.convertSecondsToTicks(1);}
    protected int getCastingTimeElapsed()
    {
        return castingTime;
    }

    protected int getExecutionCooldown() { return TickUnits.convertSecondsToTicks(5); }
    protected int getCooldownTicksElapsed() { return cooldownTicksElapsed; }

    protected boolean mustSeeTarget()
    {
        return true;
    }

    protected void setSpellCompleted()
    {
        spellCasted = true;
    }


    @Override
    public boolean canUse()
    {
        cooldownTicksElapsed++;

        if(mob.getTarget() == null)
        {
            return false;
        }

        if(getCooldownTicksElapsed() < getExecutionCooldown())
        {
            return false;
        }

        if(mustSeeTarget() && !mob.getSensing().hasLineOfSight(mob.getTarget()))
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
        return !spellCasted && mob.getTarget() != null;
    }

    @Override
    public void start()
    {
        super.start();

        if(mob.level().isClientSide())
        {
            return;
        }

        playCastingAnimation();
        mob.level().playSound(mob, mob.blockPosition(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.HOSTILE, 1.0F, 1.0F);
    }

    protected void playCastingAnimation()
    {

    }

    protected void playAttackAnimation()
    {

    }

    protected void doAttackTick()
    {
        setSpellCompleted();
    }

    @Override
    public void tick()
    {
        super.tick();

        if(mob.level().isClientSide())
        {
            return;
        }

        if(getCastingTimeElapsed() < getBaseCastingTime())
        {
            castingTime++;
            return;
        }

        if(spellCasted)
        {
            return;
        }
        doAttackTick();
    }

    @Override
    public void stop()
    {
        super.stop();
        cooldownTicksElapsed = 0;
        spellCasted = false;
        castingTime = 0;
    }
}
