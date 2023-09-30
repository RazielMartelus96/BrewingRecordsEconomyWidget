package io.curiositycore.brewingrecordseconomywidget.gui.persistance.brews;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.curiositycore.brewingrecordseconomywidget.gui.persistance.PersistenceManager;
import io.curiositycore.brewingrecordseconomywidget.gui.persistance.PersistentData;
import io.curiositycore.brewingrecordseconomywidget.model.brew.Brew;
import io.curiositycore.brewingrecordseconomywidget.model.brew.BrewManager;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.IngredientManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The persistent data of a Configuration Preset saved by a user during a previous session of the Application. Contains
 * the general Ingredients list and all the Brews defined within said previous session.
 */
public class BrewConfigData implements PersistentData {
    /**
     * The name of the pre-set's JSON file.
     */
    private final String configDataName;

    /**
     * Map representing the registered Brews of the preset config file.
     */
    @JsonProperty
    private Map<String, Brew> brewMap = new HashMap<>();
    /**
     * Map representing the general ingredients list of the preset config file.
     */
    @JsonProperty
    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    /**
     * Constructor which initialises the Config Data's JSON file name.
     * @param configDataName The Config Data's file name.
     */
    public BrewConfigData(String configDataName){
        this.configDataName = configDataName;
    }

    /**
     * Constructor called during read of a JSON file, to allow for the creation of the pre-set within the application.
     * @param configDataName The name of the JSON file.
     * @param brewMap Map representing the defined Brews within the JSON file.
     * @param ingredientMap Map representing the general Ingredients defined within the JSON file.
     */
    public BrewConfigData(@JsonProperty("fileName") String configDataName,@JsonProperty("brewMap") Map<String,Brew> brewMap,@JsonProperty("ingredientMap") Map<String,Ingredient> ingredientMap ){
        this.brewMap = brewMap;
        this.ingredientMap = ingredientMap;
        this.configDataName = configDataName;
    }
    @Override
    public void save(File configFile) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(configFile,this);
            PersistenceManager.getInstance().register(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        BrewManager.getInstance().reloadBrews(this.brewMap);
        IngredientManager.getInstance().reloadIngredients(this.ingredientMap);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public <K, V> void addData(Map<K, V> dataMapToAdd) {
        if (dataMapToAdd.isEmpty()) {
            return;
        }
        V firstValue = dataMapToAdd.values().iterator().next();
        if (firstValue instanceof Ingredient) {
            this.ingredientMap.putAll((Map<String, Ingredient>) dataMapToAdd);
        } else if (firstValue instanceof Brew) {
            this.brewMap.putAll((Map<String, Brew>) dataMapToAdd);
        } else {
            throw new IllegalArgumentException("Provided map contains unsupported data types.");
        }
    }

    @Override
    public String getFileName() {
        return this.configDataName;
    }

}
