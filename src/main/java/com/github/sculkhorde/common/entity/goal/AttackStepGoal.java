package com.github.sculkhorde.common.entity.goal;

import net.minecraft.world.entity.ai.goal.Goal;

public class AttackStepGoal extends Goal
{

    protected AttackSequenceGoal sequenceParent;

    protected AttackSequenceGoal getSequenceParent()
    {
        return sequenceParent;
    }

    protected void setSequenceParent(AttackSequenceGoal parent)
    {
        sequenceParent = parent;
    }

    @Override
    public boolean canUse() {
        return false;
    }

    @Override
    public void stop() {
        super.stop();
        if(sequenceParent != null) { sequenceParent.incrementAttackIndexOrFinishSequence(); }
    }
}
