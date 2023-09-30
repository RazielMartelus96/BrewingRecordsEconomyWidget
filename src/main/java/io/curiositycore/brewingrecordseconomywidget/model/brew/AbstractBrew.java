package io.curiositycore.brewingrecordseconomywidget.model.brew;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.curiositycore.brewingrecordseconomywidget.model.effects.Effect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.NegativeEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.PositiveEffect;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "brewClass")
@JsonSubTypes({
        @JsonSubTypes.Type(value = io.curiositycore.brewingrecordseconomywidget.model.brew.types.RoleplayBrew.class, name = "io.curiositycore.brewingrecordseconomywidget.model.brew.types.RoleplayBrew"),
        @JsonSubTypes.Type(value = io.curiositycore.brewingrecordseconomywidget.model.brew.types.CombatBrew.class, name = "io.curiositycore.brewingrecordseconomywidget.model.brew.types.CombatBrew"),
        @JsonSubTypes.Type(value = io.curiositycore.brewingrecordseconomywidget.model.brew.types.UtilityBrew.class, name = "io.curiositycore.brewingrecordseconomywidget.model.brew.types.UtilityBrew")
})
public abstract class AbstractBrew implements Brew{
    protected String internalName;
    protected String name;
    protected int cost;
    protected Set<Effect> effects;
    protected Set<Ingredient> ingredients = new HashSet<>();
    protected Class<?> brewClass;
    protected AbstractBrew(String internalName,String name, int cost, Set<Effect> effects, Set<Ingredient> ingredients){
        this.internalName = internalName;
        this.name = name;
        this.ingredients = ingredients;
        this.cost = getOverallCost();
        this.effects = effects;
        this.brewClass = this.getClass();
    }

    @Override
    public String getInternalName() {
        return this.internalName;
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
    public void reloadCosts() {
        this.cost = getOverallCost();
    }

    @Override
    public Set<Effect> getEffects() {
        return this.effects;
    }

    @Override
    public String getPositiveEffectsAsString() {
        if(effects.stream().noneMatch(PositiveEffect.class::isInstance)){
            return "No Effects";
        }
        return this.effects.stream().filter(PositiveEffect.class::isInstance).map(effect -> effect.getEffectName().replace("_"," ")).collect(Collectors.joining(", ")).toUpperCase();
    }

    @Override
    public String getNegativeEffectsAsString() {
        if(effects.stream().noneMatch(NegativeEffect.class::isInstance)){
            return "No Effects";
        }
        return this.effects.stream().filter(NegativeEffect.class::isInstance).map(effect -> effect.getEffectName().replace("_"," ")).collect(Collectors.joining(", ")).toUpperCase();    }
    @JsonIgnore
    @Override
    public Class<?> getBrewClass() {
        return this.brewClass;
    }


    @Override
    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }
    @JsonIgnore
    protected int getOverallCost(){
        int cost = 0;
        for(Ingredient ingredient : this.ingredients){
            try{
                cost += ingredient.getCost();
            }
            catch(NullPointerException e){
                //Placeholder this is just here to ensure that the loop continues

            }
        }
        return cost;
    }
    public abstract static class AbstractBrewBuilder<T extends Brew> implements BrewBuilder<T> {
        @JsonIgnore
        protected String internalName;
        @JsonIgnore
        protected String name;
        @JsonIgnore
        protected int cost;
        @JsonIgnore
        protected Set<Effect> commandEffects = new HashSet<>();
        @JsonIgnore
        protected Set<Ingredient> ingredients = new HashSet<>();

        @Override
        public AbstractBrewBuilder<T> setName(String name) {
            this.name = name;
            return this;
        }
        @JsonIgnore
        @Override
        public AbstractBrewBuilder<T> setInternalName(String internalName){
            this.internalName = internalName;
            return this;
        }
        @JsonIgnore
        @Override
        public AbstractBrewBuilder<T> setCost(int cost) {
            this.cost = cost;
            return this;
        }
        @JsonIgnore
        @Override
        public AbstractBrewBuilder<T> addEffect(Effect commandEffect) {
            if(commandEffect == null){
                return this;
            }
            /*if(!isCorrectEffectType(commandEffect) && !(commandEffect instanceof NegativeEffect)){
                throw new RuntimeException("Effect:'" + commandEffect + "' was not of the correct type");
            }*/
            this.commandEffects.add(commandEffect);
            return this;
        }
        @JsonIgnore
        @Override
        public AbstractBrewBuilder<T> addIngredient(Ingredient ingredient) {
            this.ingredients.add(ingredient);
            return this;
        }

        @JsonIgnore
        protected abstract boolean isCorrectEffectType(Effect commandEffectToCheck);

    }
}
