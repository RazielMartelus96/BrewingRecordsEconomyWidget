package io.curiositycore.brewingrecordseconomywidget.model.effects.types.vanilla;

import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.PositiveEffect;

/**
 * Enumeration representing the types of Positive in-game effects that can occur upon drinking a {@linkplain
 * io.curiositycore.brewingrecordseconomywidget.model.brew.Brew Brew}.
 */
public enum PositiveBrewEffects implements PositiveEffect {
    /**
     * Grants the Player the vanilla "Strength" effect.
     */
    STRENGTH("Strength",EffectType.COMBAT),

    /**
     * Grants the Player the vanilla "Heal" effect.
     */
    HEAL("Heal",EffectType.COMBAT),

    /**
     * Grants the Player the vanilla "Haste" effect.
     */
    FAST_DIGGING("Haste",EffectType.UTILITY),

    /**
     * Grants the Player the vanilla "Conduit" effect.
     */
    CONDUIT_POWER("Conduit Power",EffectType.UTILITY),

    /**
     * Grants the Player the vanilla "Luck" effect.
     */
    LUCK("Luck",EffectType.UTILITY),

    /**
     * Grants the Player the vanilla "Unlucky" effect.
     */
    UNLUCK("Bad Luck",EffectType.UTILITY),

    /**
     * Grants the Player the vanilla "Night Vision" effect.
     */
    NIGHT_VISION("Night Vision",EffectType.UTILITY),

    /**
     * Grants the Player the vanilla "Invisibility" effect.
     */
    INVISIBILITY("Invisibility",EffectType.UTILITY),

    /**
     * Grants the Player the vanilla "Water Breathing" effect.
     */
    WATER_BREATHING("Water Breathing",EffectType.UTILITY),

    /**
     * Grants the Player the vanilla "Slow Falling" effect.
     */
    SLOW_FALLING("Slow Falling",EffectType.UTILITY),

    /**
     * Grants the Player the vanilla "Levitation" effect.
     */
    LEVITATION("Levitation",EffectType.UTILITY),

    /**
     * Grants the Player the vanilla "Potion of Leaping" effect.
     */
    JUMP("Jump",EffectType.UTILITY),

    /**
     * Grants the Player the vanilla excess Saturation.
     */
    SATURATION("Saturation",EffectType.UTILITY),

    /**
     * Grants the Player the vanilla "Glow" effect.
     */
    GLOWING("Glowing",EffectType.ROLEPLAY),

    /**
     * Grants the Player the vanilla "Fire Resistance" effect.
     */
    FIRE_RESISTANCE("Fire_Resistance",EffectType.COMBAT),

    /**
     * Grants the Player the vanilla "Resistance" effect.
     */
    DAMAGE_RESISTANCE("Damage Resistance",EffectType.COMBAT),

    /**
     * Grants the Player the vanilla levels of "Absorption".
     */
    ABSORPTION("Absorption",EffectType.COMBAT),

    /**
     * Grants the Player the excess vanilla "Health".
     */
    HEALTH_BOOST("Health Boost",EffectType.COMBAT),

    /**
     * Grants the Player the vanilla "Regeneration" effect.
     */
    REGENERATION("Regeneration",EffectType.COMBAT),

    /**
     * Grants the Player the vanilla "Speed" effect.
     */
    SPEED("Speed",EffectType.COMBAT),

    /**
     * Grants the Player the excess vanilla "Damage".
     */
    INCREASE_DAMAGE("Strength",EffectType.COMBAT);
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

    @Override
    public Class<?> getEffectClass() {
        return this.effectClass;
    }
}
