package io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.enums;

import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.NegativeEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.NegativeCommandEffect;

/**
 * Enumeration representing the various commands that produce negative effects to a Player that drinks a {@linkplain
 * io.curiositycore.brewingrecordseconomywidget.model.brew.Brew Brew}.
 *
 */
public enum NegativeBrewCommandEffect implements NegativeCommandEffect, NegativeEffect {
    /**
     * Command that applies "Mining Fatigue" to a Player.
     */
    MINING_FATIGUE("Mining Fatigue", "", EffectType.UTILITY);
    /**
     * The display name of the Command Effect within the Application.
     */
    private String effectName;

    /**
     * The string executed to achieve the Command Effect.
     */
    private String commandString;

    /**
     * The designation of the Command Effect's influence on in-game mechanics.
     */
    private EffectType effectType;

    /**
     * Get the Class Type of this Command Effect, used for deserialization of JSON files.
     */
    private Class<?> effectClass = this.getClass();

    /**
     * Constructor that initialises the Command Effect's display name and type.
     * @param effectName The display name of the Command Effect.
     * @param effectType The Command Effect's type.
     */
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

    @Override
    public Class<?> getEffectClass() {
        return this.effectClass;
    }
}
