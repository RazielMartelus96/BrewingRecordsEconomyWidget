package io.curiositycore.brewingrecordseconomywidget.model.effects.types.vanilla;

import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.PositiveEffect;

public enum PositiveBrewEffects implements PositiveEffect {
    STRENGTH("Strength",EffectType.COMBAT);
    private String name;
    private EffectType effectType;
    PositiveBrewEffects(String name, EffectType effectType){
        this.name = name;
        this.effectType = effectType;
    }

    @Override
    public String getEffectName() {
        return this.name.toUpperCase();
    }

    @Override
    public EffectType getEffectType() {
        return this.effectType;
    }
}
