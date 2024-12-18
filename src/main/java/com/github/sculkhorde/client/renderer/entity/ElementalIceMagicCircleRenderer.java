package com.github.sculkhorde.client.renderer.entity;

import com.github.sculkhorde.client.model.enitity.ElementalIceMagicCircleModel;
import com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.ElementalIceMagicCircleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class ElementalIceMagicCircleRenderer extends GeoEntityRenderer<ElementalIceMagicCircleEntity> {

    public ElementalIceMagicCircleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ElementalIceMagicCircleModel());
    }


}
