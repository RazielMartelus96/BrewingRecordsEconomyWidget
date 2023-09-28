package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

import org.bukkit.Material;
//TODO good example for KK to potentially have a go at doing documentation for

public class VanillaBrewIngredient implements Ingredient{
    private Material ingredient;
    public VanillaBrewIngredient(String ingredientName){
        this.ingredient = Material.valueOf(ingredientName.toUpperCase().substring(0,ingredientName.indexOf("/")));
    }
    @Override
    public String getName() {
        return this.ingredient.name().toLowerCase().replace("_", " ");
    }

    @Override
    public String getPotentialMaterialChoices() {
        return this.ingredient.name().toLowerCase().replace("_","");
    }

    @Override
    public String getPotentialCustomNames() {
        return "Vanilla";
    }

    @Override
    public int getAmount() {
        return 0;
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public String getPotentialCraftingIngredients() {
        return "Not craftable";
    }

}
