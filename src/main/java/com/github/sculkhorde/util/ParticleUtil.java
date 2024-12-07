package com.github.sculkhorde.util;

import com.github.sculkhorde.core.ModParticles;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import org.joml.Vector3f;

public class ParticleUtil {

    public static void spawnBurrowedBurstParticles(ServerLevel level, Vector3f position, int amount, float speed) {

        for (int i = 0; i < amount; i++) {
            spawnBurrowedParticle(level, position, speed);
        }
    }

    public static void spawnBurrowedParticle(ServerLevel level, Vector3f position, float speed)
    {
        spawnParticleOnServer(ModParticles.BURROWED_BURST_PARTICLE.get(), level, position, speed);
    }

    public static void spawnParticleOnServer(ParticleOptions particle, ServerLevel level, Vector3f position, float speed)
    {

        level.sendParticles(particle, position.x, position.y, position.z, 1, 0, 0, 0, speed);
    }


    public static void spawnColoredDustParticle(ServerLevel level, String hexColor, float alpha, Vector3f position, Vector3f deltaMovement)
    {
        level.sendParticles(new DustParticleOptions(ColorUtil.hexToVector3F(hexColor), alpha), position.x, position.y, position.z, 1, deltaMovement.x, deltaMovement.y, deltaMovement.z, 1);
    }

    public static void spawnSolidColoredDustParticle(ServerLevel level, String hexColor, Vector3f position, Vector3f deltaMovement)
    {
        spawnColoredDustParticle(level, hexColor, 1.0F, position, deltaMovement);
    }

    public static void spawnSnowflakeParticle(ServerLevel level, Vector3f position, Vector3f deltaMovement)
    {
        level.sendParticles(ParticleTypes.SNOWFLAKE, position.x, position.y, position.z, 1, deltaMovement.x, deltaMovement.y, deltaMovement.z, 1);
    }

    public static void spawnFlameParticle(ServerLevel level, Vector3f position, Vector3f deltaMovement)
    {
        level.sendParticles(ParticleTypes.FLAME, position.x, position.y, position.z, 1, deltaMovement.x, deltaMovement.y, deltaMovement.z, 1);
    }
}
