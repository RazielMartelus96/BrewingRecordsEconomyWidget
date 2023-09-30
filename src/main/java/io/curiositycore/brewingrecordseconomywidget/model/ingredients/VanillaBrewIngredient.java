package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.Material;

public class VanillaBrewIngredient implements Ingredient{
    private Class<?> ingredientClass = this.getClass();

    private Material ingredient;
    private int cost = 0;
    public VanillaBrewIngredient(@JsonProperty("potentialMaterialChoices") String ingredientName){
        if(ingredientName.contains("/")){
            this.ingredient = Material.valueOf(ingredientName.toUpperCase().substring(0,ingredientName.indexOf("/")));
        } else{

            this.ingredient = Material.valueOf(ingredientName.toUpperCase().replace(" ","_"));
        }
    }


    @Override
    public String getName() {
        return this.ingredient.name().toLowerCase().replace("_", " ");
    }

    @Override
    public String getPotentialMaterialChoices() {
        return this.ingredient.name().toLowerCase().replace("_"," ");
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
        return this.cost;
    }

    @Override
    public String getPotentialCraftingIngredients() {
        return "Not craftable";
    }
    @JsonIgnore
    @Override
    public Class<?> getIngredientClass() {
        return this.ingredientClass;
    }
    @Override
    public void setCost(int costToSet) {
        this.cost = costToSet;
    }
}
