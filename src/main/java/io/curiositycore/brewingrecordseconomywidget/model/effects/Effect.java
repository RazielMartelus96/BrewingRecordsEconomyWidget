package io.curiositycore.brewingrecordseconomywidget.model.effects;

import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;

public interface Effect {
    String getEffectName();
    EffectType getEffectType();
}
