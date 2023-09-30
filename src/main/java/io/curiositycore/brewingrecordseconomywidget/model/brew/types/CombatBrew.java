package io.curiositycore.brewingrecordseconomywidget.model.brew.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.curiositycore.brewingrecordseconomywidget.model.brew.AbstractBrew;
import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;

import java.util.Set;

/**
 * Brew that causes Combat benefiting effects after drinking.
 */
public class CombatBrew extends AbstractBrew {


    protected CombatBrew(@JsonProperty("internalName")String internalName, @JsonProperty("name") String name, @JsonProperty("effects") Set<Effect> commandEffects, @JsonProperty("ingredients") Set<Ingredient> ingredients, @JsonProperty("owner") String owner) {
        super(internalName,name, commandEffects, ingredients,owner);
    }

    public static class CombatBrewBuilder extends AbstractBrewBuilder<CombatBrew> {

        @Override
        public CombatBrew build() {
            return new CombatBrew(this.internalName, this.name, this.effects,this.ingredients,null);
        }

    }


}

