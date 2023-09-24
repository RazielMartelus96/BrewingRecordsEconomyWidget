package io.curiositycore.brewingrecordseconomywidget.model.effects.types.vanilla;

import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.NegativeEffect;

public enum NegativeBrewEffects implements NegativeEffect {
    POISON("Poison",EffectType.COMBAT),
    WEAKNESS("Weakness",EffectType.COMBAT);
    private String name;
    private EffectType effectType;
    NegativeBrewEffects(String name, EffectType effectType){
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
