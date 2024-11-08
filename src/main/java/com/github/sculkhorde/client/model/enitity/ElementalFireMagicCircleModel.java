package com.github.sculkhorde.client.model.enitity;

import com.github.sculkhorde.common.entity.boss.sculk_soul_reaper.ElementalFireMagicCircleEntity;
import com.github.sculkhorde.core.SculkHorde;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class ElementalFireMagicCircleModel extends DefaultedEntityGeoModel<ElementalFireMagicCircleEntity> {
    public ElementalFireMagicCircleModel() {
        super(new ResourceLocation(SculkHorde.MOD_ID, "elemental_fire_magic_circle"));
    }

    // We want our model to render using the translucent render type
    @Override
    public RenderType getRenderType(ElementalFireMagicCircleEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}
