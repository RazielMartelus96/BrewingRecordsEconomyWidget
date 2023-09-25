package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

import java.util.Arrays;
import org.bukkit.Material;

public class CustomBrewIngredient implements Ingredient{
    private String name;
    private String[] material;
    private String[] customNames;
    //TODO get the custom names from the factory.
    public CustomBrewIngredient(String name, String materialName){
        this.name = name;
        this.material = initMaterialArray(materialName);
    }
    public CustomBrewIngredient(String name){
        this.name = name;

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
        return null;
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

    private String[] initMaterialArray(String materialName){
        if(!materialName.contains("[")){
            return new String[]{materialName};
        }
        return materialName.substring(1, materialName.length() - 1).split(", ");
    }
}

