package io.curiositycore.brewingrecordseconomywidget.model.brew;

import io.curiositycore.brewingrecordseconomywidget.model.brew.types.CombatBrew;
import io.curiositycore.brewingrecordseconomywidget.model.brew.types.RoleplayBrew;
import io.curiositycore.brewingrecordseconomywidget.model.brew.types.UtilityBrew;
import io.curiositycore.brewingrecordseconomywidget.model.effects.*;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.command.enums.PositiveBrewCommandEffect;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.vanilla.NegativeBrewEffects;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.vanilla.PositiveBrewEffects;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.CustomBrewIngredient;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.IngredientManager;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.VanillaBrewIngredient;
import io.curiositycore.brewingrecordseconomywidget.model.util.YamlParser;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BrewFactory {
    private Set<Brew> brewSet = new HashSet<>();

    private String filePath;
    public BrewFactory(String filePath){
        this.filePath = filePath;
    }

    public Set<Brew> buildBrewSet(){
        BrewManager brewManager = BrewManager.getInstance();
        Map<String,Object> configMap = YamlParser.getConfigMap(this.filePath);
        Map<String,Object> customItemMap = ((Map<String,Object>)configMap.get("customItems"));
        customItemMap
                .keySet()
                .stream()
                .forEach(customItemName-> IngredientManager
                .getInstance()
                .register(initCustomIngredient(((Map<String,Object>)customItemMap.get(customItemName)),customItemName)));

        Map<String,Object> brewMap = ((Map<String,Object>)configMap.get("recipes"));

        brewMap.keySet().stream().forEach(brewName-> {
            Brew brewToAdd = getBrewFromConfigSectionMap((Map<String, Object>) brewMap.get(brewName),brewName);
            if(brewToAdd != null){
                brewSet.add(brewToAdd);
                brewManager.register(brewToAdd);
            }
        });
        return this.brewSet;
    }

    private Brew getBrewFromConfigSectionMap(Map<String, Object> brewMap, String internalName) {
        List<String> commands = (List<String>) brewMap.get("servercommands");
        List<String> effectNames = (List<String>) brewMap.get("effects");
        if(commands != null){


            for (String command : commands) {
                Effect brewCommandEffect = getEffectCommandFromName(command);
                if (brewCommandEffect.getEffectType().equals(EffectType.COMBAT)) {
                    return buildCombatBrew(brewMap,internalName);
                } else if (brewCommandEffect.getEffectType().equals(EffectType.UTILITY)) {
                    return buildUtilityBrew(brewMap,internalName);
                } else if (brewCommandEffect.getEffectType().equals(EffectType.ROLEPLAY)) {
                    return buildRoleplayBrew(brewMap,internalName);
                }
            }
        }
        if(effectNames != null){
            for (String effectName : effectNames) {
                Effect brewEffect = getEffectFromName(effectName);
                if(brewEffect == null){
                    System.out.println("The effect: '"+effectName+"' is not defined");
                    return null;
                }
                if (brewEffect.getEffectType().equals(EffectType.COMBAT)) {
                    return buildCombatBrew(brewMap,internalName);
                } else if (brewEffect.getEffectType().equals(EffectType.UTILITY)) {
                    return buildUtilityBrew(brewMap,internalName);
                } else if (brewEffect.getEffectType().equals(EffectType.ROLEPLAY)) {
                    return buildRoleplayBrew(brewMap,internalName);
                }
            }
        }
        return buildRoleplayBrew(brewMap,internalName);
    }
    //TODO add these to a utility class
    private Effect getEffectFromName(String effectName) {
        for (PositiveBrewEffects positiveBrewEffects : PositiveBrewEffects.values()) {
            String trueEffectName = effectName.substring(0,effectName.indexOf("/"));
            if (trueEffectName.contains(positiveBrewEffects.getEffectName().toUpperCase())) {
                return positiveBrewEffects;
            }
        }
        for (NegativeBrewEffects negativeBrewEffects : NegativeBrewEffects.values()) {
            if (effectName.substring(0,effectName.indexOf("/")).contains(negativeBrewEffects.getEffectName())) {
                return negativeBrewEffects;
            }
        }
        return null;
    }
    private Effect getEffectCommandFromName(String commandName){

        for(PositiveBrewCommandEffect positiveBrewCommandEffect : PositiveBrewCommandEffect.values()){
            if(commandName.contains(positiveBrewCommandEffect.getCommandString())){
                return positiveBrewCommandEffect;
            }
        }
        return null;
    }

    private Ingredient initCustomIngredient(Map<String,Object> ingredientMap, String ingredientInternalName) throws NullPointerException{
        try{
            if(ingredientMap.get("name") instanceof String[] ingredientCustomNames){
                return new CustomBrewIngredient(ingredientInternalName,ingredientMap.get("material").toString(),ingredientCustomNames,0);
            } else if (ingredientMap.get("name") instanceof String ingredientCustomName) {
                return new CustomBrewIngredient(ingredientInternalName,ingredientMap.get("material").toString(),ingredientCustomName,0);

            } else if (ingredientMap.get("name") == null) {
                throw new NullPointerException();

            }
        }
        catch(NullPointerException nullPointerException){
            if(ingredientMap.get("name") == null){
                CustomBrewIngredient customBrewIngredient = new CustomBrewIngredient(ingredientInternalName,ingredientMap.get("material").toString(),new String[0],0);
                IngredientManager.getInstance().register(customBrewIngredient);
                return customBrewIngredient;
            }
            if(ingredientMap.get("material") == null){
                return new CustomBrewIngredient(ingredientInternalName,ingredientMap.get("name").toString());

            }
        }
        return new CustomBrewIngredient(ingredientInternalName);
    }
    private Ingredient getIngredientFromName(String ingredientName){
        if(ingredientName.contains("Brewery")){
            String brewName = ingredientName.replace("Brewery:","");
            return new CustomBrewIngredient(brewName.substring(0,brewName.indexOf("/")));
        }
        if(!IngredientManager.getInstance().isRegistered(ingredientName)){
            VanillaBrewIngredient vanillaBrewIngredient = new VanillaBrewIngredient(ingredientName);
            IngredientManager.getInstance().register(vanillaBrewIngredient);
            return vanillaBrewIngredient;
        }
        return IngredientManager.getInstance().getCustomIngredient(ingredientName);
    }

    private CombatBrew buildCombatBrew(Map<String, Object> combatBrewMap,String internalName) {
        return buildBrew(combatBrewMap, new CombatBrew.CombatBrewBuilder(), internalName).build();
    }

    private RoleplayBrew buildRoleplayBrew(Map<String, Object> roleplayBrewMap,String internalName) {
        return buildBrew(roleplayBrewMap, new RoleplayBrew.RoleplayBrewBuilder(), internalName).build();
    }

    private UtilityBrew buildUtilityBrew(Map<String, Object> utilityBrewMap,String internalName) {
        return buildBrew(utilityBrewMap, new UtilityBrew.UtilityBrewBuilder(), internalName).build();
    }
    private <T extends AbstractBrew.AbstractBrewBuilder> T buildBrew(Map<String, Object> brewMap, T brewBuilder,String brewInternalName) {
        brewBuilder.setInternalName(brewInternalName);
        String brewName = brewMap.get("name").toString();
        if (brewName.contains("/")) {
            brewBuilder.setName(brewName.substring(0, brewName.indexOf("/")));
        } else {
            brewBuilder.setName(brewName);
        }

        try {
            ((List<String>) brewMap.get("servercommands")).forEach(commandName ->
                    brewBuilder.addEffect(getEffectCommandFromName(commandName)));
        } catch (NullPointerException nullPointerException) {
            System.out.println("The brew called: " + brewName + " has no commands");
        }

        try {
            ((List<String>) brewMap.get("effects")).forEach(commandName ->
                    brewBuilder.addEffect(getEffectFromName(commandName)));
        } catch (NullPointerException nullPointerException) {
            System.out.println("The brew called: " + brewName + " has no vanilla commands");
        }

        ((List<String>) brewMap.get("ingredients")).forEach(ingredientName ->
                brewBuilder.addIngredient(getIngredientFromName(ingredientName)));

        return brewBuilder;
    }

}
