package io.curiositycore.brewingrecordseconomywidget.model.brew;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;
import io.curiositycore.brewingrecordseconomywidget.model.util.Craftable;

import java.util.Set;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "brewClass")
@JsonSubTypes({
        @JsonSubTypes.Type(value = io.curiositycore.brewingrecordseconomywidget.model.brew.types.RoleplayBrew.class, name = "io.curiositycore.brewingrecordseconomywidget.model.brew.types.RoleplayBrew"),
        @JsonSubTypes.Type(value = io.curiositycore.brewingrecordseconomywidget.model.brew.types.CombatBrew.class, name = "io.curiositycore.brewingrecordseconomywidget.model.brew.types.CombatBrew"),
        @JsonSubTypes.Type(value = io.curiositycore.brewingrecordseconomywidget.model.brew.types.UtilityBrew.class, name = "io.curiositycore.brewingrecordseconomywidget.model.brew.types.UtilityBrew")
})
public interface Brew extends Craftable {
    String getInternalName();
    String getOwner();
    String setOwner(String ownerToSet);
    String getName();
    int getCost();
    void reloadCosts(Ingredient newIngredient);
    Set<Effect> getEffects();
    String getPositiveEffectsAsString();
    String getNegativeEffectsAsString();
    Class<?> getBrewClass();
}
