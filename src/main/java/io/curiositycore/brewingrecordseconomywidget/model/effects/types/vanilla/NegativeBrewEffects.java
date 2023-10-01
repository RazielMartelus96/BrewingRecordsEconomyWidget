package io.curiositycore.brewingrecordseconomywidget.model.effects.types.vanilla;

import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.NegativeEffect;

/**
 * Enumeration representing the types of Negative in-game effects that can occur upon drinking a {@linkplain
 * io.curiositycore.brewingrecordseconomywidget.model.brew.Brew Brew}.
 */
public enum NegativeBrewEffects implements NegativeEffect {
    /**
     * Inflicts the Player the vanilla "Poison" effect.
     */
    POISON("Poison",EffectType.COMBAT),

    /**
     * Inflicts the Player the vanilla "Weakness" effect.
     */
    WEAKNESS("Weakness",EffectType.COMBAT);

    /**
     * Display name of the Effect within the Application.
     */
    private String name;

    /**
     * The designation of the Effect's influence on in-game mechanics.
     */
    private EffectType effectType;

    /**
     * Get the Class Type of this Effect, used for deserialization of JSON files.
     */
    private Class<?> effectClass = this.getClass();

    /**
     * Constructor that initialises the Effect's display name and type.
     * @param name The display name of the Effect.
     * @param effectType The effect's type.
     */
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

    @Override
    public Class<?> getEffectClass() {
        return this.effectClass;
    }
}
