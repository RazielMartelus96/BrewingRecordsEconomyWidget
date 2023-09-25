package io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.enums;

import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.PositiveEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.PositiveCommandEffect;

public enum PositiveBrewCommandEffect implements PositiveCommandEffect, PositiveEffect {
    WEATHER("Weather Effect","weather", EffectType.UTILITY),EXPERIENCE("XP","xp give", EffectType.UTILITY),
    DISGUISE("Disguise","disguiseplayer", EffectType.UTILITY),
    TITLE("Title","title", EffectType.ROLEPLAY),
    SUMMON("Summon","run summon", EffectType.UTILITY),
    SOUND("Sound","run playsound", EffectType.ROLEPLAY),
    FIREWORKS("XP","run summon minecraft:firework_rocket", EffectType.ROLEPLAY), //Could also be run summon firework_rocket OR conflict with SUMMON
    PARTICLES("Particles","run particle", EffectType.ROLEPLAY),
    WINGS("Wings","wings setwing", EffectType.ROLEPLAY),
    EFFECT("Effect Give","effect give", EffectType.UTILITY),
    BURN("Burn","burn", EffectType.ROLEPLAY),
    PTIME("Ptime","ptime", EffectType.ROLEPLAY),
    PUKE("Brew Puke","brew puke", EffectType.ROLEPLAY),
    EFFECTCLEAR("Effect Clear","effect clear", EffectType.COMBAT),
    RACE("Set Race","setrace", EffectType.ROLEPLAY),
    NATION("Set Nation","setnation", EffectType.ROLEPLAY),
    LOOTBOX("Lootbox","custom_brew_loot", EffectType.UTILITY),
    PARTICLEFUNCTION("Particle Function","function particles", EffectType.ROLEPLAY);

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
