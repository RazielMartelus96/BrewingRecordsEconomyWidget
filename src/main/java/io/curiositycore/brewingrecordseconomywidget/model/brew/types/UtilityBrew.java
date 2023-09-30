package io.curiositycore.brewingrecordseconomywidget.model.brew.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.curiositycore.brewingrecordseconomywidget.model.brew.AbstractBrew;
import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;

import java.util.Set;

public class UtilityBrew extends AbstractBrew {
    protected UtilityBrew(@JsonProperty("internalName")String internalName, @JsonProperty("name") String name, @JsonProperty("effects") Set<Effect> commandEffects, @JsonProperty("ingredients") Set<Ingredient> ingredients, @JsonProperty("owner") String owner) {
        super(internalName, name, commandEffects, ingredients,owner);
    }

    public static class UtilityBrewBuilder extends AbstractBrewBuilder<UtilityBrew> {

        @Override
        public UtilityBrew build() {
            return new UtilityBrew(this.internalName, this.name, this.commandEffects,this.ingredients,null);
        }

    }
}
