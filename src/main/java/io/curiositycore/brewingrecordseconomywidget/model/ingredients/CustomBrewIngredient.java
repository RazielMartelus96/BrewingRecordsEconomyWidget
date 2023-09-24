package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

import org.bukkit.Material;

public class CustomBrewIngredient implements Ingredient{
    private String name;
    private String[] material;
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
    public String getPotentialItemNames() {
        if(this.material == null){
            return "No Preference";
        }
        if(this.material.length == 1){
            return material[0];
        }
        return material.toString().replace("[","").replace("]","");
    }

    private String[] initMaterialArray(String materialName){
        if(!materialName.contains("[")){
            return new String[]{materialName};
        }
        return materialName.substring(1, materialName.length() - 1).split(", ");
    }
}

