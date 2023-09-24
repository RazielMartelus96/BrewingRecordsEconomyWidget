package io.curiositycore.brewingrecordseconomywidget.model.brew;

import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.NegativeEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.PositiveEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.CommandEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.NegativeCommandEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.PositiveCommandEffect;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractBrew implements Brew{
    protected String name;
    protected int cost;
    protected Set<Effect> commandEffects = new HashSet<>();
    protected Set<Ingredient> ingredients = new HashSet<>();
    protected AbstractBrew(String name, int cost, Set<Effect> commandEffects, Set<Ingredient> ingredients){
        this.name = name;
        this.cost = cost;
        this.commandEffects = commandEffects;
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
        return this.commandEffects;
    }

    @Override
    public String getPositiveEffectsAsString() {
        String test = this.commandEffects.stream().filter(effect -> effect instanceof PositiveCommandEffect).map(Effect::getEffectName).collect(Collectors.joining(", "));
        return this.commandEffects.stream().filter(effect -> effect instanceof PositiveEffect).map(Effect::getEffectName).collect(Collectors.joining(", "));
    }

    @Override
    public String getNegativeEffectsAsString() {
        return this.commandEffects.stream().filter(effect -> effect instanceof NegativeEffect).map(Effect::getEffectName).collect(Collectors.joining(", "));
    }


    @Override
    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public abstract static class AbstractBrewBuilder<T extends Brew> implements BrewBuilder<T> {
        protected String name;
        protected int cost;
        protected Set<Effect> commandEffects = new HashSet<>();
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
        public AbstractBrewBuilder<T> addEffect(Effect commandEffect) {
            if(commandEffect == null){
                return this;
            }
            if(!isCorrectEffectType(commandEffect) && !(commandEffect instanceof NegativeEffect)){
                throw new RuntimeException("Effect was not of the correct type");
            }
            this.commandEffects.add(commandEffect);
            return this;
        }

        @Override
        public AbstractBrewBuilder<T> addIngredient(Ingredient ingredient) {
            this.ingredients.add(ingredient);
            return this;
        }
        protected abstract boolean isCorrectEffectType(Effect commandEffectToCheck);

    }
}
