package io.curiositycore.brewingrecordseconomywidget.model.effects.types.command;

import io.curiositycore.brewingrecordseconomywidget.model.effects.NegativeCommandEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.NegativeEffect;

public enum NegativeBrewCommandEffect implements NegativeCommandEffect , NegativeEffect {
    //TODO you assumed it was only ocmmands but there is actually an "Effects" part of a brew's map. needs implementing.
    MINING_FATIGUE("Mining Fatigue","", EffectType.UTILITY);
    private String effectName;
    private String commandString;
    private EffectType effectType;
    NegativeBrewCommandEffect(String effectName, String commandString, EffectType effectType){
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
