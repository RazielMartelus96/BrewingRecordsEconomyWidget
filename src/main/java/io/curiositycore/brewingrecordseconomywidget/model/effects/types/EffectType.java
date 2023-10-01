package io.curiositycore.brewingrecordseconomywidget.model.effects.types;

/**
 * Enumeration representing how an {@linkplain
 * io.curiositycore.brewingrecordseconomywidget.model.effects.Effect Effect} is identified based on its influence on
 * particular activities.
 */
public enum EffectType {
    /**
     * An Effect that influences the mechanics of in-game combat.
     */
    COMBAT,
    /**
     * An Effect that influences the general mechanics of in-game activities (e.g: Mining, Farming, Enchanting etc).
     */
    UTILITY,
    /**
     * An Effect that has generally superficial effects in-game, and hence are mainly for Roleplaying purposes.
     */
    ROLEPLAY
}
