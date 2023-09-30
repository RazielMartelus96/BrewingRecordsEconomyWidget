package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "ingredientClass")
@JsonSubTypes({
        @JsonSubTypes.Type(value = io.curiositycore.brewingrecordseconomywidget.model.ingredients.VanillaBrewIngredient.class,name = "io.curiositycore.brewingrecordseconomywidget.model.ingredients.VanillaBrewIngredient"),
        @JsonSubTypes.Type(value = io.curiositycore.brewingrecordseconomywidget.model.ingredients.CustomBrewIngredient.class,name = "io.curiositycore.brewingrecordseconomywidget.model.ingredients.CustomBrewIngredient")
})
public interface Ingredient {
    String getName();
    String getPotentialMaterialChoices();
    String getPotentialCustomNames();
    int getAmount();
    int getCost();
    void setCost(int costToSet);
    String getPotentialCraftingIngredients();
    Class<?> getIngredientClass();

}
