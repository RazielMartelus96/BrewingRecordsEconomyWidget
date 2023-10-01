package io.curiositycore.brewingrecordseconomywidget.model.effects.types.command;

import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;

/**
 * An Effect that causes a specified in-game command to occur when drinking a {@linkplain
 * io.curiositycore.brewingrecordseconomywidget.model.brew.Brew Brew}.
 */
public interface CommandEffect extends Effect {
    /**
     * Get the string of the command to perform upon drinking a Brew.
     * @return The Effect's command string.
     */
    String getCommandString();
}
