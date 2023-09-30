package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.Material;

/**
 * Ingredient that consists of a Vanilla Minecraft Item.
 */
public class VanillaBrewIngredient implements Ingredient ,Cloneable{
    /**
     * The class of the Ingredient.
     */
    private final Class<?> ingredientClass = this.getClass();
    /**
     * The Bukkit Material of the Ingredient.
     */
    private final Material ingredient;

    /**
     * The cost of a singular Ingredient of this type.
     */
    private int cost = 0;

    /**
     * The amount of the Ingredient required for the Ingredient's Craftable recipe.
     */
    private int amount;

    /**
     * The cost of the amount of Ingredients required for the Ingredient's Craftable recipe.
     */
    private int amountCost;

    /**
     * Constructor of the Ingredient that initialises the Material based on the specified Material Name.
     * @param ingredientName The Bukkit Material name of the Ingredient.
     */
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
        return this.amount;
    }

    @Override
    public void setAmount(int amountToSet) {
        this.amount = amountToSet;
    }
    @Override
    public int getAmountCost() {
        return this.cost*this.amount;
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
        if(amountCost != 0){
            amountCost = amount*cost;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    @Override
    public Ingredient cloneable() {
        try {
            return (VanillaBrewIngredient) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should not happen
        }
    }
}
