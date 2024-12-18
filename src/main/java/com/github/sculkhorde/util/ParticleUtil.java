package com.github.sculkhorde.util;

import com.github.sculkhorde.core.ModParticles;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
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

    public static void spawnParticleBeam(ServerLevel level, ParticleOptions particle, Vec3 origin, Vec3 direction, float length, float radius, float thickness)
    {
        Vec3 up = new Vec3(0, 1, 0);
        Vec3 right = direction.cross(up).normalize();
        Vec3 forward = direction.cross(right).normalize();

        // Spawn Particles
        for (float i = 1; i < Mth.floor(length) + 1; i += 0.3F) {
            Vec3 vec33 = origin.add(direction.scale((double) i));

            // Create a circle of particles around vec33
            for (int j = 0; j < thickness; ++j) {
                double angle = 2 * Math.PI * j / thickness;
                double xOffset = radius * Math.cos(angle);
                double zOffset = radius * Math.sin(angle);
                Vec3 offset = right.scale(xOffset).add(forward.scale(zOffset));
                level.sendParticles(particle, vec33.x + offset.x, vec33.y + offset.y, vec33.z + offset.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
