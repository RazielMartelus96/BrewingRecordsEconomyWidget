package io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.enums;

import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.PositiveEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.PositiveCommandEffect;

/**
 * Enumeration representing the various commands that produce positive effects to a Player that drinks a {@linkplain
 * io.curiositycore.brewingrecordseconomywidget.model.brew.Brew Brew}.
 *
 */
public enum PositiveBrewCommandEffect implements PositiveCommandEffect, PositiveEffect {
    /**
     * Command that effects the in-game Weather.
     */
    WEATHER("Weather Effect","weather", EffectType.UTILITY),

    /**
     * Command that provides a Player with an amount of Experience.
     */
    EXPERIENCE("XP","xp give", EffectType.UTILITY),

    /**
     * Command that can disguise a Player as another Entity.
     */
    DISGUISE("Disguise","disguiseplayer", EffectType.UTILITY),

    /**
     * Command that grants a title to a Player.
     */
    TITLE("Title","title", EffectType.ROLEPLAY),

    /**
     * Command that summons a mob to location of the Player.
     */
    SUMMON("Summon","run summon", EffectType.UTILITY),

    /**
     * Command that plays a specified sound at the Player's location.
     */
    SOUND("Sound","run playsound", EffectType.ROLEPLAY),

    /**
     * Command that summons fireworks to the Player's location. Utilises the "summon" command as its foundation.
     */
    FIREWORKS("Fireworks","run summon minecraft:firework_rocket", EffectType.ROLEPLAY),

    /**
     * Command that summons particles to the Player's location.
     */
    PARTICLES("Particles","run particle", EffectType.ROLEPLAY),

    /**
     * Command that grants the player wings on a Player
     */
    WINGS("Wings","wings setwing", EffectType.ROLEPLAY),

    /**
     * Command that applies a Vanilla Potion Effect on a Player.
     */
    EFFECT("Effect Give","effect give", EffectType.UTILITY),

    /**
     * Command that applies burning to the Player.
     */
    BURN("Burn","burn", EffectType.ROLEPLAY),
    //TODO unsure what this does
    PTIME("Ptime","ptime", EffectType.ROLEPLAY),

    /**
     * Command that causes the player to "puke" a discharge of soulsand like blocks to simulating vomiting.
     */
    PUKE("Brew Puke","brew puke", EffectType.ROLEPLAY),
    /**
     * Command that clears a specified (or all) Vanilla potion effects from a Player.
     */
    EFFECTCLEAR("Effect Clear","effect clear", EffectType.COMBAT),

    //TODO unsure what this does.
    RACE("Set Race","setrace", EffectType.ROLEPLAY),

    /**
     * Command that sets the nation of a Player, as per the Lands Plugin.
     */
    NATION("Set Nation","setnation", EffectType.ROLEPLAY),

    /**
     * Command that generates a custom lootbox for the Player.
     */
    LOOTBOX("Lootbox","custom_brew_loot", EffectType.UTILITY),

    //TODO unsure what this does.
    PARTICLEFUNCTION("Particle Function","function particles", EffectType.ROLEPLAY);

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

    @Override
    public Class<?> getEffectClass() {
        return this.effectClass;
    }
}
