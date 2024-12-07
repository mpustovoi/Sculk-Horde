package com.github.sculkhorde.util;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import org.joml.Vector3f;

public class ParticleUtil {

    public static void spawnBurrowedBurstParticles(ServerLevel level, Vector3f position, int amount) {

        for (int i = 0; i < amount; i++) {
            Vector3f deltaMovement = new Vector3f(0, 0, 0);
            spawnBurrowedParticle(level, position, deltaMovement);
        }
    }

    public static void spawnBurrowedParticle(ServerLevel level, Vector3f position, Vector3f deltaMovement)
    {
        //level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, Items.ECHO_SHARD.getDefaultInstance()), position.x, position.y, position.z, 1, 0, 0, 0, 1);
        //level.sendParticles(ParticleTypes.SOUL, position.x, position.y, position.z, 0, 0, 2, 0, 1);
        //level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, Items.ECHO_SHARD.getDefaultInstance()), position.x, position.y, position.z, 1, (level.random.nextFloat() - 0.5F) * 0.08F, (level.random.nextFloat() - 0.5F) * 0.08F, (level.random.nextFloat() - 0.5F) * 0.08F, 1);

        //level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, Items.ECHO_SHARD.getDefaultInstance()), position.x, position.y, position.z, 1, deltaMovement.x, deltaMovement.y, deltaMovement.z, 1);
        //level.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SCULK.defaultBlockState()), position.x, position.y, position.z, 1, deltaMovement.x, deltaMovement.y, deltaMovement.z, 1);
        spawnParticleOnServer(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SCULK.defaultBlockState()), level, position, deltaMovement);
    }

    public static void spawnParticleOnServer(ParticleOptions particle, ServerLevel level, Vector3f position, Vector3f deltaMovement)
    {

        level.sendParticles(particle, position.x, position.y, position.z, 1, deltaMovement.x, deltaMovement.y, deltaMovement.z, 1.0D);
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
