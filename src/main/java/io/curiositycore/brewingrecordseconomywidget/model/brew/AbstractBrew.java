package io.curiositycore.brewingrecordseconomywidget.model.brew;

import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.NegativeEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.PositiveEffect;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractBrew implements Brew{
    protected String name;
    protected int cost;
    protected Set<Effect> effects = new HashSet<>();
    protected Set<Ingredient> ingredients = new HashSet<>();
    protected AbstractBrew(String name, int cost, Set<Effect> effects, Set<Ingredient> ingredients){
        this.name = name;
        this.cost = cost;
        this.effects = effects;
        this.ingredients = ingredients;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public Set<Effect> getEffects() {
        return this.effects;
    }

    @Override
    public String getPositiveEffectsAsString() {
        String test = this.effects.stream().filter(effect -> effect instanceof PositiveEffect).map(Effect::getEffectName).collect(Collectors.joining(", "));
        return this.effects.stream().filter(effect -> effect instanceof PositiveEffect).map(Effect::getEffectName).collect(Collectors.joining(", "));
    }

    @Override
    public String getNegativeEffectsAsString() {
        return this.effects.stream().filter(effect -> effect instanceof NegativeEffect).map(Effect::getEffectName).collect(Collectors.joining(", "));
    }


    @Override
    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public abstract static class AbstractBrewBuilder<T extends Brew> implements BrewBuilder<T> {
        protected String name;
        protected int cost;
        protected Set<Effect> effects = new HashSet<>();
        protected Set<Ingredient> ingredients = new HashSet<>();

        @Override
        public AbstractBrewBuilder<T> setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public AbstractBrewBuilder<T> setCost(int cost) {
            this.cost = cost;
            return this;
        }

        @Override
        public AbstractBrewBuilder<T> addEffect(Effect effect) {
            if(effect == null){
                return this;
            }
            if(!isCorrectEffectType(effect) && !(effect instanceof NegativeEffect)){
                throw new RuntimeException("Effect was not of the correct type");
            }
            this.effects.add(effect);
            return this;
        }

        @Override
        public AbstractBrewBuilder<T> addIngredient(Ingredient ingredient) {
            this.ingredients.add(ingredient);
            return this;
        }
        protected abstract boolean isCorrectEffectType(Effect effectToCheck);

    }
}
