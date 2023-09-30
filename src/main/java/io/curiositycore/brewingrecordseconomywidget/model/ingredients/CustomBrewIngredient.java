package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.Material;

public class CustomBrewIngredient implements Ingredient{
    private String name;
    private String[] material;
    private String[] customNames;
    private int cost;
    private int amount;
    private Class<?> ingredientClass = this.getClass();

    public CustomBrewIngredient(String name, String materialName,String[] customNames, int cost){
        this.name = name;
        this.material = initMaterialArray(materialName);
        this.customNames = customNames;
        this.cost = cost;
    }
    public CustomBrewIngredient(@JsonProperty("name")String name, @JsonProperty("potentialMaterialChoices")String materialName, @JsonProperty("potentialCustomNames") String customName, @JsonProperty("cost")int cost){
        this.name = name;
        this.material = initMaterialArray(materialName);
        this.customNames = new String[]{customName};
        this.cost = cost;
    }
    public CustomBrewIngredient(String name){
        this.name = name;

    }

    public CustomBrewIngredient(String ingredientInternalName, String ingredientCustomName) {
        this.name = ingredientInternalName;
        this.customNames = new String[]{ingredientCustomName};
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPotentialMaterialChoices() {
        if(this.material == null){
            return "No Preference";
        }
        if(this.material.length == 1){
            return material[0];
        }
        return Arrays.toString(material).replace("[","").replace("]","");
    }

    @Override
    public String getPotentialCustomNames() {
        if(customNames == null||customNames.length == 0){
            return "No custom names";
        }
        return Arrays.toString(customNames).replace("[","").replace("]","");
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
    public void setCost(int costToSet) {
        this.cost = costToSet;
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

    private String[] initMaterialArray(String materialName){
        if(!materialName.contains("[")){
            return new String[]{materialName};
        }
        return materialName.substring(1, materialName.length() - 1).split(", ");
    }
}

