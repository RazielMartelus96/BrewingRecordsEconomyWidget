package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
 * Interface representing the generalised functionality of any Ingredient that can be utilised within a {@linkplain
 * io.curiositycore.brewingrecordseconomywidget.model.util.Craftable}.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "ingredientClass")
@JsonSubTypes({
        @JsonSubTypes.Type(value = io.curiositycore.brewingrecordseconomywidget.model.ingredients.VanillaBrewIngredient.class,name = "io.curiositycore.brewingrecordseconomywidget.model.ingredients.VanillaBrewIngredient"),
        @JsonSubTypes.Type(value = io.curiositycore.brewingrecordseconomywidget.model.ingredients.CustomBrewIngredient.class,name = "io.curiositycore.brewingrecordseconomywidget.model.ingredients.CustomBrewIngredient")
})

public interface Ingredient extends Cloneable{
    /**
     * Get the internal name of the Ingredient.
     * @return The internal name of the Ingredient.
     */
    String getName();

    /**
     * Get the potential Bukkit Materials (as a String) that the Ingredient can be.
     * @return The potential Ingredient Materials.
     */
    String getPotentialMaterialChoices();

    /**
     * Get the potential, in-game, custom names the Ingredient can be.
     * @return The Ingredient's potential custom names.
     */
    String getPotentialCustomNames();

    /**
     * Get the amount of this Ingredient within the Craftable's recipe.
     * @return The amount of Ingredients Required for the recipe.
     */
    int getAmount();

    /**
     * Set the amount of this Ingredient Required within the Craftable's recipe.
     * @param amountToSet The amount of this Ingredient require.
     */
    void setAmount(int amountToSet);

    /**
     * Get the cost of a singular instance of this Ingredient.
     * @return The cost of a singular Ingredient.
     */
    int getCost();

    /**
     * Get the cost of all the instances of this Ingredient used in the Craftable's recipe
     * @return The cost of the collective ingredients of this type in the recipe.
     */
    int getAmountCost();

    /**
     * Set the cost of all the instances of this Ingredient used in the Craftable's recipe
     * @param costToSet The cost of the collective ingredients of this type in the recipe.
     */
    void setCost(int costToSet);

    /**
     * Get the potential Ingredients that can be used to craft this Ingredient.
     * @return The potential Ingredients.
     */
    String getPotentialCraftingIngredients();

    /**
     * Get the concrete class of this Ingredient <i>(utilised within the JSON reading for construction purposes)</i>.
     * @return The Ingredient's concrete class.
     */
    Class<?> getIngredientClass();

    /**
     * Produces a clone the Ingredient <i>(Utilised when adding ingredients to a Brew, so the generalised version,
     * of the Ingredient isn't effected by Brew Ingredient changes, such as amount)</i>.
     * @return A clone of the Ingredient.
     */
    Ingredient cloneable();

}
