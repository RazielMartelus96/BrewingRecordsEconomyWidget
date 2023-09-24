package io.curiositycore.brewingrecordseconomywidget.model.effects.types.command;

import io.curiositycore.brewingrecordseconomywidget.model.effects.PositiveCommandEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.PositiveEffect;

public enum PositiveBrewCommandEffect implements PositiveCommandEffect, PositiveEffect {
    WEATHER_CLEAR("Clear Weather","weather clear", EffectType.UTILITY);

    private String effectName;
    private String commandString;
    private EffectType effectType;
    PositiveBrewCommandEffect(String effectName, String commandString, EffectType effectType){
        this.effectName = effectName;
        this.commandString = commandString;
        this.effectType = effectType;
    }

    @Override
    public String getEffectName() {
        return this.effectName;
    }

    @Override
    public String getCommandString() {
        return this.commandString;
    }

    @Override
    public EffectType getEffectType() {
        return this.effectType;
    }

}
