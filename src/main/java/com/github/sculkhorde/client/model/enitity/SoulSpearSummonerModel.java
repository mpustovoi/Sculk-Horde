package com.github.sculkhorde.client.model.enitity;

import com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.SoulSpearSummonerEntity;
import com.github.sculkhorde.core.SculkHorde;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SoulSpearSummonerModel extends DefaultedEntityGeoModel<SoulSpearSummonerEntity> {
    public SoulSpearSummonerModel() {
        super(new ResourceLocation(SculkHorde.MOD_ID, "soul_spear_summoner"));
    }

    // We want our model to render using the translucent render type
    @Override
    public RenderType getRenderType(SoulSpearSummonerEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}
