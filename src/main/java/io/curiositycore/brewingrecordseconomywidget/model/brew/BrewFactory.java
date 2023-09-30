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

/**
 * <p>Factory responsible for the construction of {@linkplain Brew} and {@linkplain Ingredient} instances within a
 * specified Brewery Plugin Config File.
 * </p>
 * Factory also registers the constructed instances into their respective Managers.
 *
 */
public class BrewFactory {
    /**
     * Set of Brews constructed by the Factory.
     */
    private Set<Brew> brewSet = new HashSet<>();

    /**
     * The path to the currently selected Brewery Plugin Config File.
     */
    private String filePath;

    /**
     * Constructor which initialises the path to the Brewery Plugin Config File.
     * @param filePath The path to the currently selected Brewery Plugin Config File.
     */
    public BrewFactory(String filePath){
        this.filePath = filePath;
    }

    /**
     * Build all the Brews contained within the Factory's Brewery Plugin Config File.
     * @return The set of Constructed Brews.
     */
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

    /**
     * Constructs a brew from a single Configuration Section of the Factory's Config File.
     * @param brewMap Map representing the Configuration Section.
     * @param internalName The internal name of the Brew from the Config File.
     * @return The constructed Brew.
     */
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

    /**
     * Gets a Brew Effect based on the name provided.
     * @param effectName The name of the Effect to get.
     * @return The specified Effect.
     */
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
    /**
     * Gets a Brew Command Effect based on the name provided.
     * @param commandName The name of the Command Effect to get.
     * @return The specified Command Effect.
     */
    private Effect getEffectCommandFromName(String commandName){

        for(PositiveBrewCommandEffect positiveBrewCommandEffect : PositiveBrewCommandEffect.values()){
            if(commandName.contains(positiveBrewCommandEffect.getCommandString())){
                return positiveBrewCommandEffect;
            }
        }
        return null;
    }

    /**
     * Initialises a {@linkplain CustomBrewIngredient Custom Brew Ingredient} based on a Configuration Section from
     * the Brewery Plugin Config File.
     * @param ingredientMap Map representing the Ingredient's Configuration Section.
     * @param ingredientInternalName The Ingredient's internal name.
     * @return The constructed Custom Brew Ingredient.
     * @throws NullPointerException
     */
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

    /**
     * Get a Brew Ingredient from its specified name.
     * @param ingredientName The name of the Ingredient.
     * @return The specified Ingredient.
     */
    private Ingredient getIngredientFromName(String ingredientName){

        if(ingredientName.contains("Brewery")){
            String brewName = ingredientName.replace("Brewery:","");
            return new CustomBrewIngredient(brewName);
        }
        if(!IngredientManager.getInstance().isRegistered(ingredientName)){
            VanillaBrewIngredient vanillaBrewIngredient = new VanillaBrewIngredient(ingredientName);
            IngredientManager.getInstance().register(vanillaBrewIngredient);
            return vanillaBrewIngredient;
        }
        return IngredientManager.getInstance().getCustomIngredient(ingredientName);
    }

    /**
     * Utilise a specified Configuration section to construct a Combat Brew.
     * @param combatBrewMap Map representing the Combat Brew's Configuration Section.
     * @param internalName The internal name of the Combat Brew.
     * @return The constructed Combat Brew
     */
    private CombatBrew buildCombatBrew(Map<String, Object> combatBrewMap,String internalName) {
        return buildBrew(combatBrewMap, new CombatBrew.CombatBrewBuilder(), internalName).build();
    }

    /**
     * Utilise a specified Configuration section to construct a Roleplay Brew.
     * @param roleplayBrewMap Map representing the Roleplay Brew's Configuration Section.
     * @param internalName The internal name of the Roleplay Brew.
     * @return The constructed Roleplay Brew
     */
    private RoleplayBrew buildRoleplayBrew(Map<String, Object> roleplayBrewMap,String internalName) {
        return buildBrew(roleplayBrewMap, new RoleplayBrew.RoleplayBrewBuilder(), internalName).build();
    }

    /**
     * Utilise a specified Configuration section to construct a Utility Brew.
     * @param utilityBrewMap Map representing the Utility Brew's Configuration Section.
     * @param internalName The internal name of the Utility Brew.
     * @return The constructed Utility Brew
     */
    private UtilityBrew buildUtilityBrew(Map<String, Object> utilityBrewMap,String internalName) {
        return buildBrew(utilityBrewMap, new UtilityBrew.UtilityBrewBuilder(), internalName).build();
    }

    /**
     * Construct a brew utilising a specified AbstractBrewBuilder implementation from a Child Class of the {@linkplain
     * io.curiositycore.brewingrecordseconomywidget.model.brew.AbstractBrew.AbstractBrewBuilder}.
     * @param brewMap Map representing the Configuration Section of the Brew.
     * @param brewBuilder The BrewBuilder to utilise.
     * @param brewInternalName The internal name of the Brew.
     * @return The constructed Brew.
     * @param <T> Type parameter of the Abstract Brew Builder to utilise.
     */
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
            //Placeholder
        }

        try {
            ((List<String>) brewMap.get("effects")).forEach(commandName ->
                    brewBuilder.addEffect(getEffectFromName(commandName)));
        } catch (NullPointerException nullPointerException) {
            //Placeholder
        }

        ((List<String>) brewMap.get("ingredients")).forEach(ingredientName ->
                brewBuilder.addIngredient(getIngredientFromName(ingredientName), Integer.parseInt(ingredientName.substring(ingredientName.indexOf("/")+1,ingredientName.length()))));

        return brewBuilder;
    }

}
