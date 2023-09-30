package io.curiositycore.brewingrecordseconomywidget.model.util;

import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;

import java.util.Set;

/**
 * Interface representing anything that is craftable with Minecraft items.
 */
public interface Craftable {
    /**
     * Get the Ingredients required to craft the craftable item.
     * @return The craftable item's crafting ingredients.
     */
    Set<Ingredient> getIngredients();
}
