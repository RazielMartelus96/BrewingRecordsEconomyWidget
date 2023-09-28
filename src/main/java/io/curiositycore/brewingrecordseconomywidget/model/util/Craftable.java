package io.curiositycore.brewingrecordseconomywidget.model.util;

import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;

import java.util.Set;
//TODO good example for KK to potentially have a go at doing documentation for

public interface Craftable {

    Set<Ingredient> getIngredients();
}
