package io.curiositycore.brewingrecordseconomywidget.model.effects.types.command;

import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;

public interface CommandEffect extends Effect {
    String getCommandString();
}
