package io.curiositycore.brewingrecordseconomywidget.model.brew;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;
import io.curiositycore.brewingrecordseconomywidget.model.util.Craftable;

import java.util.Set;

/**
 * Interface representing the general functionality of any defined Brew within the Application.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "brewClass")
@JsonSubTypes({
        @JsonSubTypes.Type(value = io.curiositycore.brewingrecordseconomywidget.model.brew.types.RoleplayBrew.class, name = "io.curiositycore.brewingrecordseconomywidget.model.brew.types.RoleplayBrew"),
        @JsonSubTypes.Type(value = io.curiositycore.brewingrecordseconomywidget.model.brew.types.CombatBrew.class, name = "io.curiositycore.brewingrecordseconomywidget.model.brew.types.CombatBrew"),
        @JsonSubTypes.Type(value = io.curiositycore.brewingrecordseconomywidget.model.brew.types.UtilityBrew.class, name = "io.curiositycore.brewingrecordseconomywidget.model.brew.types.UtilityBrew")
})
public interface Brew extends Craftable {
    /**
     * The internal name of the Brew, typically the designation within its respective Config File.
     * @return The Brew's internal name.
     */
    String getInternalName();

    /**
     * Get the Owner of the brew, typically the Player who bought it.
     * @return The Owner of the Brew.
     */
    String getOwner();

    /**
     * Set the Owner of the Brew.
     * @param ownerToSet The Owner to set the Brew to.
     */
    void setOwner(String ownerToSet);

    /**
     * Get the in-game name of the Brew.
     * @return The in-game name of the Brew.
     */
    String getName();

    /**
     * Get the cost of the Brew.
     * @return The cost of the Brew.
     */
    int getCost();

    /**
     * Reload the costs of the Brew with a newly loaded Ingredient.
     * @param newIngredient The new Ingredient that triggered the reload.
     */
    void reloadCosts(Ingredient newIngredient);

    /**
     * Get the effects caused by drinking this Brew.
     * @return The Brew's effects.
     */
    Set<Effect> getEffects();

    /**
     * Get the Positive Effects of the Brew, as a singular String.
     * @return The Positive Effects of the Brew.
     */
    String getPositiveEffectsAsString();

    /**
     * Get the Negative Effects of the Brew, as a singular String.
     * @return The Negative Effects of the Brew.
     */
    String getNegativeEffectsAsString();

    /**
     * Get the class of the Brew Instance.
     * @return The Class of the Brew.
     */
    Class<?> getBrewClass();
}
