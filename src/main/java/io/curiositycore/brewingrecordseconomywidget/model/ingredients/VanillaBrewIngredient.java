package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

import org.bukkit.Material;

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
    public String getPotentialItemNames() {
        return this.ingredient.name().toLowerCase().replace("_","");
    }

}
