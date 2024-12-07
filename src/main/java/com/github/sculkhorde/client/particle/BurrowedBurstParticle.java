package com.github.sculkhorde.client.particle;

import com.github.sculkhorde.util.TickUnits;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class BurrowedBurstParticle extends TextureSheetParticle
{
    protected BurrowedBurstParticle(ClientLevel clientLevel, double x, double y, double z, double dx, double dy, double dz) {
        super(clientLevel, x, y, z, dx, dy, dz);
    }

    protected BurrowedBurstParticle(ClientLevel clientLevel, double x, double y, double z) {
        super(clientLevel, x, y, z);
    }


    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public Provider(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType defaultParticleType, ClientLevel clientWorld, double x, double y, double z, double dx, double dy, double dz) {
            RandomSource random = clientWorld.random;
            BurrowedBurstParticle particle = new BurrowedBurstParticle(clientWorld, x, y, z, dx, dy, dz);
            particle.pickSprite(this.spriteProvider);
            particle.quadSize *= random.nextFloat() * 0.4F + 0.1F;
            particle.lifetime = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
            particle.setLifetime(TickUnits.convertSecondsToTicks(1));
            return particle;
        }
    }
}
