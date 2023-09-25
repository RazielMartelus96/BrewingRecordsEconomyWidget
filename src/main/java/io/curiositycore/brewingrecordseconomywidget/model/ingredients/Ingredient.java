package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

public interface Ingredient {
    String getName();
    String getPotentialMaterialChoices();
    String getPotentialCustomNames();
    int getAmount();
    int getCost();
    String getPotentialCraftingIngredients();

}
