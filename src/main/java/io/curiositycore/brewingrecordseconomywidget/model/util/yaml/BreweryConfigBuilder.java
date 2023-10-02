package io.curiositycore.brewingrecordseconomywidget.model.util.yaml;

import io.curiositycore.brewingrecordseconomywidget.model.brew.Brew;

import java.util.Map;

/**
 * Builder responsible for building Configuration Files that can be utilised within the Bukkit Brewery Plugin.
 */
public interface BreweryConfigBuilder extends YamlBuilder<BreweryConfigYaml>{

    /**
     * Adds the specified map of Brews to the Config File.
     * @param brewMap A map of user-defined Brews.
     */
    void addBrewsData(Map<String, Brew> brewMap);

    /**
     * Adds the specified map of Ingredients to the Config File.
     * @param brewMap A map of user-defined Ingredients.
     */
    void addIngredientsData(Map<String, Brew> brewMap);

}
