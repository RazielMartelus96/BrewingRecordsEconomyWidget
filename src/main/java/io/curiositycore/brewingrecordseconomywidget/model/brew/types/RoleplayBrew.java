package io.curiositycore.brewingrecordseconomywidget.model.brew.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.curiositycore.brewingrecordseconomywidget.model.brew.AbstractBrew;
import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.CommandEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;

import java.util.Set;

public class RoleplayBrew extends AbstractBrew {
    protected RoleplayBrew(@JsonProperty("internalName")String internalName,@JsonProperty("name") String name,@JsonProperty("cost") int cost,@JsonProperty("effects") Set<Effect> commandEffects,@JsonProperty("ingredients") Set<Ingredient> ingredients, @JsonProperty("owner") String owner) {
        super(internalName,name, cost, commandEffects, ingredients, owner);
    }

    public static class RoleplayBrewBuilder extends AbstractBrewBuilder<RoleplayBrew> {

        @Override
        public RoleplayBrew build() {
            return new RoleplayBrew(this.internalName, this.name,this.cost,this.commandEffects,this.ingredients,null);
        }

        @Override
        protected boolean isCorrectEffectType(Effect commandEffectToCheck) {
            return commandEffectToCheck.getEffectType().equals(EffectType.ROLEPLAY);

        }
    }
}
