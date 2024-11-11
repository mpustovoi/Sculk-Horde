package com.github.sculkhorde.common.entity.goal;

import com.github.sculkhorde.util.TickUnits;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.ArrayList;

public class AttackSequenceGoal extends Goal
{

    protected ArrayList<AttackStepGoal> attacks = new ArrayList<>();
    protected int currentAttackIndex = 0;
    protected long timeOfLastExecution = 0;
    protected Mob mob;
    protected boolean finishedAttackSequence = false;
    protected long executionCooldown = 0;

    protected long timeOfLastEnemy = 0;
    protected final long NO_ENEMY_TIMEOUT = TickUnits.convertSecondsToTicks(15);

    public AttackSequenceGoal(Mob mob, long executionCooldown, AttackStepGoal... attacksIn)
    {
        this.mob = mob;
        this.executionCooldown = executionCooldown;
        for(AttackStepGoal goal : attacksIn)
        {
            goal.setSequenceParent(this);
            attacks.add(goal);
        }
    }

    protected Goal getCurrentGoal()
    {
        return attacks.get(currentAttackIndex);
    }

    protected void incrementAttackIndexOrFinishSequence()
    {
        if(currentAttackIndex + 1 >= attacks.size())
        {
            finishedAttackSequence = true;
            return;
        }

        currentAttackIndex += 1;
    }

    protected long getExecutionCooldown() { return executionCooldown; }

    @Override
    public void start() {
        super.start();
        getCurrentGoal().start();
    }

    @Override
    public boolean canUse() {

        if(attacks.isEmpty()) { return false; }

        if(Math.abs(mob.level().getGameTime() - timeOfLastExecution) < getExecutionCooldown())
        {
            return false;
        }

        return getCurrentGoal().canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return getCurrentGoal().canContinueToUse();
    }

    @Override
    public void tick() {

        if(timeOfLastEnemy != 0 && mob.getTarget() != null)
        {
            timeOfLastEnemy = 0;
        }
        else if(timeOfLastEnemy == 0 && mob.getTarget() == null)
        {
            timeOfLastEnemy = mob.level().getGameTime();
        }

        if(mob.level().getGameTime() - timeOfLastEnemy >= NO_ENEMY_TIMEOUT)
        {
            finishedAttackSequence = true;
        }

        getCurrentGoal().tick();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return getCurrentGoal().requiresUpdateEveryTick();
    }

    @Override
    public void stop() {
        if(finishedAttackSequence)
        {
            currentAttackIndex = 0;
            timeOfLastExecution = mob.level().getGameTime();
            finishedAttackSequence = false;
        }
        else
        {
            getCurrentGoal().stop();
        }
        super.stop();
    }
}