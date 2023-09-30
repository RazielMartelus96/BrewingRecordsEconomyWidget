package io.curiositycore.brewingrecordseconomywidget.model.brew.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.curiositycore.brewingrecordseconomywidget.model.brew.AbstractBrew;
import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;

import java.util.Set;

public class CombatBrew extends AbstractBrew {


    protected CombatBrew(@JsonProperty("internalName")String internalName, @JsonProperty("name") String name, @JsonProperty("cost") int cost, @JsonProperty("effects") Set<Effect> commandEffects, @JsonProperty("ingredients") Set<Ingredient> ingredients) {
        super(internalName,name, cost, commandEffects, ingredients);
    }

    public static class CombatBrewBuilder extends AbstractBrewBuilder<CombatBrew> {

        @Override
        public CombatBrew build() {
            return new CombatBrew(this.internalName, this.name,this.cost,this.commandEffects,this.ingredients);
        }

        @Override
        protected boolean isCorrectEffectType(Effect commandEffectToCheck) {
            return commandEffectToCheck.getEffectType().equals(EffectType.COMBAT);
        }
    }


}

