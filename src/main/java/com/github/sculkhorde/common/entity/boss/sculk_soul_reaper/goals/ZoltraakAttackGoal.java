package com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.goals;

import com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.SculkSoulReaperEntity;
import com.github.sculkhorde.util.TickUnits;

import static com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.SculkSoulReaperEntity.shootZoltraakBeam;

public class ZoltraakAttackGoal extends ReaperCastSpellGoal
{


    public ZoltraakAttackGoal(SculkSoulReaperEntity mob, int minDifficulty, int maxDifficulty) {
        super(mob, minDifficulty, maxDifficulty);
    }

    @Override
    protected int getBaseCastingTime() {
        return TickUnits.convertSecondsToTicks(2);
    }

    @Override
    protected int getCooldownTicksElapsed() {
        return TickUnits.convertSecondsToTicks(8);
    }

    @Override
    protected void doAttackTick() {
        shootZoltraakBeam(mob.getEyePosition(), mob, mob.getTarget(), DAMAGE, 0.3F, 10F);
        setSpellCompleted();
    }
}