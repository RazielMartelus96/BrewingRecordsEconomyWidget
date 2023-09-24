package io.curiositycore.brewingrecordseconomywidget.model.brew.types;

import io.curiositycore.brewingrecordseconomywidget.model.brew.AbstractBrew;
import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;

import java.util.Set;

public class UtilityBrew extends AbstractBrew {
    protected UtilityBrew(String name, int cost, Set<Effect> effects, Set<Ingredient> ingredients) {
        super(name, cost, effects, ingredients);
    }

    public static class UtilityBrewBuilder extends AbstractBrewBuilder<UtilityBrew> {

        @Override
        public UtilityBrew build() {
            return new UtilityBrew(this.name,this.cost,this.effects,this.ingredients);
        }

        @Override
        protected boolean isCorrectEffectType(Effect effectToCheck) {
            return effectToCheck.getEffectType().equals(EffectType.UTILITY);
        }
    }
}
