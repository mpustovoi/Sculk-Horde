package com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.goals;

import com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.SculkSoulReaperEntity;
import com.github.sculkhorde.common.entity.goal.AttackSequenceGoal;
import com.github.sculkhorde.common.entity.goal.AttackStepGoal;
import net.minecraft.world.entity.Mob;

public class ReaperAttackSequenceGoal extends AttackSequenceGoal {

    protected int minDifficulty = 0;
    protected int maxDifficulty = 0;

    public ReaperAttackSequenceGoal(Mob mob, long executionCooldown, int minDifficulty, int maxDifficulty, AttackStepGoal... attacksIn) {
        super(mob, executionCooldown, attacksIn);
        this.minDifficulty = minDifficulty;
        this.maxDifficulty = maxDifficulty;
    }

    public SculkSoulReaperEntity getReaper()
    {
        return(SculkSoulReaperEntity) mob;
    }

    @Override
    public boolean canUse() {
        if(!super.canUse())
        {
            return false;
        }

        if(!getReaper().isCurrentAttackOrThereIsNone(this))
        {
            return false;
        }

        if(getReaper().getMobDifficultyLevel() < minDifficulty)
        {
            return false;
        }

        if(getReaper().getMobDifficultyLevel() > maxDifficulty && maxDifficulty != -1)
        {
            return false;
        }

        return true;
    }

    @Override
    public void start() {
        super.start();

        if(getReaper().getCurrentAttack() == null)
        {
            getReaper().setCurrentAttack(this);
        }

    }

    @Override
    public void stop() {
        super.stop();

        if(finishedAttackSequence)
        {
            getReaper().clearCurrentAttack();
        }
    }
}
