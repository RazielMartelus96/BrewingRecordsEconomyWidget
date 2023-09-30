package io.curiositycore.brewingrecordseconomywidget.model.effects.types.vanilla;

import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.PositiveEffect;

public enum PositiveBrewEffects implements PositiveEffect {
    STRENGTH("Strength",EffectType.COMBAT),
    HEAL("Heal",EffectType.COMBAT),
    FAST_DIGGING("Haste",EffectType.UTILITY),
    CONDUIT_POWER("Conduit Power",EffectType.UTILITY),
    LUCK("Luck",EffectType.UTILITY),
    UNLUCK("Bad Luck",EffectType.UTILITY),
    NIGHT_VISION("Night Vision",EffectType.UTILITY),
    INVISIBILITY("Invisibility",EffectType.UTILITY),
    WATER_BREATHING("Water Breathing",EffectType.UTILITY),
    SLOW_FALLING("Slow Falling",EffectType.UTILITY),
    LEVITATION("Levitation",EffectType.UTILITY),
    JUMP("Jump",EffectType.UTILITY),
    SATURATION("Saturation",EffectType.UTILITY),
    GLOWING("Glowing",EffectType.ROLEPLAY),
    FIRE_RESISTANCE("Fire_Resistance",EffectType.COMBAT),
    DAMAGE_RESISTANCE("Damage Resistance",EffectType.COMBAT),
    ABSORPTION("Absorption",EffectType.COMBAT),
    HEALTH_BOOST("Health Boost",EffectType.COMBAT),
    REGENERATION("Regeneration",EffectType.COMBAT),
    SPEED("Speed",EffectType.COMBAT),
    INCREASE_DAMAGE("Strength",EffectType.COMBAT);
    private String name;
    private EffectType effectType;
    private Class<?> effectClass = this.getClass();
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
