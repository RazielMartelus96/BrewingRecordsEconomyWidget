package io.curiositycore.brewingrecordseconomywidget.model.brew;

import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;
import io.curiositycore.brewingrecordseconomywidget.model.util.Builder;

/**
 * Implementation of the Builder Interface that represents the functionality of a Builder responsible for constructing
 * instances of the {@linkplain Brew Brew Interface}.
 * @param <T> The Class that implements the Brew Interface.
 */
public interface BrewBuilder<T extends Brew> extends Builder<T> {
    /**
     * Set the internal name of the Brew and return the newly formatted Builder.
     * @param internalName The internal name of the Brew.
     * @return The formatted Builder.
     */
    BrewBuilder<T> setInternalName(String internalName);

    /**
     * Set the in-game name of the Brew and return the newly formatted Builder.
     * @param name The in game name of the Brew
     * @return The formatted Builder.
     */
    BrewBuilder<T> setName(String name);

    /**
     * Set the overall cost of the Brew and return the newly formatted Builder.
     * @param cost The overall cost of the Brew
     * @return The formatted Builder.
     */
    BrewBuilder<T> setCost(int cost);

    /**
     * Add an effect to the Brew and return the newly formatted Builder.
     * @param effect The command effect to add.
     * @return The formatted Builder.
     */
    BrewBuilder<T> addEffect(Effect effect);

    /**
     * Add an Ingredient to the Brew and return the newly formatted Builder.
     * @param ingredient The Ingredient to add.
     * @param amount The amount of the specified Ingredient required in the Brew recipe.
     * @return The formatted Builder.
     */
    BrewBuilder<T> addIngredient(Ingredient ingredient, int amount);

}
