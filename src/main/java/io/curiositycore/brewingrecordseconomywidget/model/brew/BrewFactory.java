package io.curiositycore.brewingrecordseconomywidget.model.brew;

import io.curiositycore.brewingrecordseconomywidget.model.brew.types.CombatBrew;
import io.curiositycore.brewingrecordseconomywidget.model.brew.types.RoleplayBrew;
import io.curiositycore.brewingrecordseconomywidget.model.brew.types.UtilityBrew;
import io.curiositycore.brewingrecordseconomywidget.model.effects.*;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.EffectType;
import io.curiositycore.brewingrecordseconomywidget.model.effects.types.PositiveBrewEffect;
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
        Map<String,Object> configMap = YamlParser.getConfigMap(this.filePath);
        Map<String,Object> customItemMap = ((Map<String,Object>)configMap.get("customItems"));
        customItemMap
                .keySet()
                .stream()
                .forEach(customItemName-> IngredientManager
                .getInstance()
                .register(initCustomIngredient(((Map<String,Object>)customItemMap.get(customItemName)),customItemName)));

        Map<String,Object> brewMap = ((Map<String,Object>)configMap.get("recipes"));

        brewMap.keySet().stream().forEach(brewName-> brewSet.add(getBrewFromConfigSectionMap((Map<String, Object>) brewMap.get(brewName))));
        return this.brewSet;
    }

    private Brew getBrewFromConfigSectionMap(Map<String, Object> brewMap) {
        List<String> commands = (List<String>) brewMap.get("servercommands");
        if(commands == null){
            return buildRoleplayBrew(brewMap);
        }
        for (String command : commands) {
            Effect brewEffect = getEffectFromName(command);
            if (brewEffect.getEffectType().equals(EffectType.COMBAT)) {
                return buildCombatBrew(brewMap);
            } else if (brewEffect.getEffectType().equals(EffectType.UTILITY)) {
                return buildUtilityBrew(brewMap);
            } else if (brewEffect.getEffectType().equals(EffectType.ROLEPLAY)) {
                return buildRoleplayBrew(brewMap);
            }
        }
        return null;
    }

    private Effect getEffectFromName(String commandName){
        for(PositiveBrewEffect positiveBrewEffect : PositiveBrewEffect.values()){
            if(commandName.contains(positiveBrewEffect.getCommandString())){
                return positiveBrewEffect;
            }
        }
        return null;
    }

    private Ingredient initCustomIngredient(Map<String,Object> ingredientMap, String ingredientInternalName){
        try{
        return new CustomBrewIngredient(ingredientInternalName,ingredientMap.get("material").toString());
        }
        catch(NullPointerException nullPointerException){
            if(ingredientMap.get("name") == null){
                return new CustomBrewIngredient(ingredientInternalName,ingredientMap.get("material").toString());
            }
            return new CustomBrewIngredient(ingredientMap.get("name").toString());
        }

    }
    private Ingredient getIngredientFromName(String ingredientName){
        if(ingredientName.contains("Brewery")){
            String brewName = ingredientName.replace("Brewery:","");
            return new CustomBrewIngredient(brewName.substring(0,brewName.indexOf("/")));
        }
        if(!IngredientManager.getInstance().isRegistered(ingredientName)){
            return new VanillaBrewIngredient(ingredientName);
        }
        return IngredientManager.getInstance().getCustomIngredient(ingredientName);
    }

    private CombatBrew buildCombatBrew(Map<String, Object> combatBrewMap){
        CombatBrew.CombatBrewBuilder combatBrewBuilder = new CombatBrew.CombatBrewBuilder();
        String brewName = combatBrewMap.get("name").toString();
        if(brewName.contains("/")){
            combatBrewBuilder.setName(brewName.substring(0,brewName.indexOf("/")));
        } else{
            combatBrewBuilder.setName(brewName);
        }
        ((List<String>) combatBrewMap.get("servercommands")).forEach(commandName->
                combatBrewBuilder.addEffect(getEffectFromName(commandName)));
        ((List<String>) combatBrewMap.get("ingredients")).forEach(ingredientName->
                combatBrewBuilder.addIngredient(getIngredientFromName(ingredientName)));
        return combatBrewBuilder.build();


    }

    private RoleplayBrew buildRoleplayBrew(Map<String, Object> roleplayBrewMap){
        RoleplayBrew.RoleplayBrewBuilder roleplayBrewBuilder = new RoleplayBrew.RoleplayBrewBuilder();
        String brewName = roleplayBrewMap.get("name").toString();
        if(brewName.contains("/")){
            roleplayBrewBuilder.setName(brewName.substring(0,brewName.indexOf("/")));
        } else{
            roleplayBrewBuilder.setName(brewName);
        }
        try{
        ((List<String>) roleplayBrewMap.get("servercommands")).forEach(commandName->
                roleplayBrewBuilder.addEffect(getEffectFromName(commandName)));
        }
        catch(NullPointerException nullPointerException){
            System.out.println("The brew called: "+ brewName + " has no commands");
        }
        ((List<String>) roleplayBrewMap.get("ingredients")).forEach(ingredientName->
                roleplayBrewBuilder.addIngredient(getIngredientFromName(ingredientName)));
        return roleplayBrewBuilder.build();
    }

    private UtilityBrew buildUtilityBrew(Map<String, Object> combatBrewMap){
        UtilityBrew.UtilityBrewBuilder utilityBrewBuilder = new UtilityBrew.UtilityBrewBuilder();
        String brewName = combatBrewMap.get("name").toString();
        if(brewName.contains("/")){
            utilityBrewBuilder.setName(brewName.substring(0,brewName.indexOf("/")));
        } else{
            utilityBrewBuilder.setName(brewName);
        }
        ((List<String>) combatBrewMap.get("servercommands")).forEach(commandName->
                utilityBrewBuilder.addEffect(getEffectFromName(commandName)));
        ((List<String>) combatBrewMap.get("ingredients")).forEach(ingredientName->
                utilityBrewBuilder.addIngredient(getIngredientFromName(ingredientName)));
        return utilityBrewBuilder.build();
    }

}
