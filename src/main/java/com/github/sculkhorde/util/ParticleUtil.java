package com.github.sculkhorde.util;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

public class ParticleUtil {

    public static void spawnColoredDustParticle(Level level, String hexColor, float alpha, Vector3f position, Vector3f deltaMovement)
    {
        level.addParticle(
                new DustParticleOptions(ColorUtil.hexToVector3F(hexColor), alpha),
                position.x, position.y, position.z,
                deltaMovement.x ,deltaMovement.y ,deltaMovement.z);
    }

    public static void spawnSolidColoredDustParticle(Level level, String hexColor, Vector3f position, Vector3f deltaMovement)
    {
        spawnColoredDustParticle(level, hexColor, 1.0F, position, deltaMovement);
    }
}
