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

public class BrewConfigData implements PersistentData {
    private String configDataName;

    @JsonProperty
    private Map<String, Brew> brewMap = new HashMap<>();
    @JsonProperty
    private Map<String, Ingredient> ingredientMap = new HashMap<>();
    public BrewConfigData(String configDataName){
        this.configDataName = configDataName;
    }
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
            PersistenceManager.getInstance().addSavedData(this);

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
