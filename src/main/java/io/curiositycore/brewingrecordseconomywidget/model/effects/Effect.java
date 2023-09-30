package io.curiositycore.brewingrecordseconomywidget.model.effects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.NegativeEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.enums.NegativeBrewCommandEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.enums.PositiveBrewCommandEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.vanilla.NegativeBrewEffects;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.vanilla.PositiveBrewEffects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "effectClass")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PositiveBrewEffects.class, name = "PositiveBrewEffects"),
        @JsonSubTypes.Type(value = NegativeBrewEffects.class, name = "NegativeBrewEffects"),
        @JsonSubTypes.Type(value = PositiveBrewCommandEffect.class, name = "PositiveBrewCommandEffect"),
        @JsonSubTypes.Type(value = NegativeBrewCommandEffect.class, name = "NegativeBrewCommandEffect")
})
public interface Effect {
    String getEffectName();
    EffectType getEffectType();
    Class<?> getEffectClass();
}
