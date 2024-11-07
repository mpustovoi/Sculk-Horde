package com.github.sculkhorde.client.renderer.entity;

import com.github.sculkhorde.client.model.enitity.ElementalFireMagicCircleModel;
import com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.goals.ElementalFireMagicCircleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class ElementalFireMagicCircleRenderer extends GeoEntityRenderer<ElementalFireMagicCircleEntity> {

    public ElementalFireMagicCircleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ElementalFireMagicCircleModel());
    }


}
