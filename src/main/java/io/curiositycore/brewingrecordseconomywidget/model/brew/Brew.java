package io.curiositycore.brewingrecordseconomywidget.model.brew;

import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.CommandEffect;
import io.curiositycore.brewingrecordseconomywidget.model.util.Craftable;

import java.util.Set;

public interface Brew extends Craftable {
    String getName();
    int getCost();
    Set<Effect> getEffects();
    String getPositiveEffectsAsString();
    String getNegativeEffectsAsString();
}
