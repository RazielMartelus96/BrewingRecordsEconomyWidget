package io.curiositycore.brewingrecordseconomywidget.model.brew;

import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;
import io.curiositycore.brewingrecordseconomywidget.model.util.Builder;

public interface BrewBuilder<T extends Brew> extends Builder<T> {
    BrewBuilder setName(String name);
    BrewBuilder setCost(int cost);
    BrewBuilder addEffect(Effect commandEffect);
    BrewBuilder addIngredient(Ingredient ingredient);
}
