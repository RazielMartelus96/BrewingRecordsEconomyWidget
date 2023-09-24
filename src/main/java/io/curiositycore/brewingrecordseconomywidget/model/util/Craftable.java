package io.curiositycore.brewingrecordseconomywidget.model.util;

import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;

import java.util.Set;

public interface Craftable {

    Set<Ingredient> getIngredients();
}
