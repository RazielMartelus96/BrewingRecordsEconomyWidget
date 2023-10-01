package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Ingredient that consists of a Custom Minecraft Item
 */
public class CustomBrewIngredient implements Ingredient, Cloneable{
    /**
     * The internal name of the Ingredient.
     */
    private final String name;
    /**
     * The Bukkit Materials the Ingredient can be.
     */
    private String[] material;

    /**
     * The potential custom names of the Ingredient.
     */
    private String[] customNames;

    /**
     * The cost of a singular Ingredient of this type.
     */
    private int cost;

    /**
     * The cost of the amount of Ingredients required for the Ingredient's Craftable recipe.
     */
    private int amountCost;
    /**
     * The amount of the Ingredient required for the Ingredient's Craftable recipe.
     */
    private int amount;

    /**
     * The class of the Ingredient.
     */
    private final Class<?> ingredientClass = this.getClass();

    /**
     * Constructor for a Custom Ingredient that has multiple custom names.
     * @param name The internal name of the Ingredient.
     * @param materialName The name of the Ingredient's materials, formatted as a String[] cast to a String.
     * @param customNames The potential custom names of the Ingredient.
     * @param cost The cost of a singular instance of this Ingredient.
     */
    public CustomBrewIngredient(String name, String materialName,String[] customNames, int cost){
        this.name = name;
        this.material = initMaterialArray(materialName);
        this.customNames = customNames;
        this.cost = cost;
    }

    /**
     * Constructor for a Custom Ingredient that has a singular Custom Name.
     * @param name The internal name of the Ingredient.
     * @param materialName The name of the Ingredient's materials, formatted as a String[] cast to a String.
     * @param customName The singular custom name of the Ingredient.
     * @param cost The cost of a singular instance of this Ingredient.
     */
    public CustomBrewIngredient(@JsonProperty("name")String name, @JsonProperty("potentialMaterialChoices")String materialName, @JsonProperty("potentialCustomNames") String customName, @JsonProperty("cost")int cost){
        this.name = name;
        this.material = initMaterialArray(materialName);
        this.customNames = new String[]{customName};
        this.cost = cost;
    }

    /**
     * Constructor for an Ingredient with just an Internal Name.
     * @param name The internal name of the Ingredient.
     */
    public CustomBrewIngredient(String name){
        this.name = name;

    }

    /**
     * Constructor for an Ingredient that has just an Internal Name and Custom Name.
     * @param ingredientInternalName The internal name of the Ingredient.
     * @param ingredientCustomName The Ingredient's custom name.
     */
    public CustomBrewIngredient(String ingredientInternalName, String ingredientCustomName) {
        this.name = ingredientInternalName;
        this.customNames = new String[]{ingredientCustomName};
    }

    /**
     * Initialises the array of Material names for this Ingredient.
     * @param materialName The names of the Bukkit Materials that this Ingredient can be, cast to a String.
     * @return The Material names for this Ingredient.
     */
    private String[] initMaterialArray(String materialName){
        if(!materialName.contains("[")){
            return new String[]{materialName};
        }
        return materialName.substring(1, materialName.length() - 1).split(", ");
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
        return this.amount;
    }

    @Override
    public void setAmount(int amountToSet) {
        this.amount = amountToSet;
    }

    @Override
    public int getCost() {
        if(amountCost != 0 && amount != 0){
            return amountCost;
        } else if (amount!= 0) {
            amountCost = amount*cost;
            return amountCost;
        }
        return this.cost;
    }

    @Override
    public int getAmountCost() {
        return this.amountCost;
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

    @Override
    public Ingredient cloneable() {
        try {
            return (CustomBrewIngredient) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should not happen
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

