package com.github.sculkhorde.client.renderer.entity;

import com.github.sculkhorde.client.model.enitity.ElementalPoisonMagicCircleModel;
import com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.ElementalPoisonMagicCircleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class ElementalPoisonMagicCircleRenderer extends GeoEntityRenderer<ElementalPoisonMagicCircleEntity> {

    public ElementalPoisonMagicCircleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ElementalPoisonMagicCircleModel());
    }


}