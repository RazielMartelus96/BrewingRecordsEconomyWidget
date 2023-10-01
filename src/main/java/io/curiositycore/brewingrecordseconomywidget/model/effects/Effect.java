package io.curiositycore.brewingrecordseconomywidget.model.effects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.enums.NegativeBrewCommandEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.enums.PositiveBrewCommandEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.vanilla.NegativeBrewEffects;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.vanilla.PositiveBrewEffects;

/**
 * Interface that represents the general functionality of an in-game effect that can occur when a Player drinks a
 * {@linkplain io.curiositycore.brewingrecordseconomywidget.model.brew.Brew Brew}.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "effectClass")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PositiveBrewEffects.class, name = "PositiveBrewEffects"),
        @JsonSubTypes.Type(value = NegativeBrewEffects.class, name = "NegativeBrewEffects"),
        @JsonSubTypes.Type(value = PositiveBrewCommandEffect.class, name = "PositiveBrewCommandEffect"),
        @JsonSubTypes.Type(value = NegativeBrewCommandEffect.class, name = "NegativeBrewCommandEffect")
})
public interface Effect {
    /**
     * Get the Effect's display name within the application.
     * @return The Effect's display name.
     */
    String getEffectName();

    /**
     * Get the Effect's type, i.e. what sort of benefit does the Effect give.
     * @return The Effect's type.
     */
    EffectType getEffectType();

    /**
     * Get the class of the Effect instance. This is used within JSON file reading for the sake of aiding deserialization.
     * @return The class of the Effect instance.
     */
    Class<?> getEffectClass();
}
