package io.curiositycore.brewingrecordseconomywidget.model.util.yaml;

import io.curiositycore.brewingrecordseconomywidget.gui.persistance.brews.BrewConfigData;
import io.curiositycore.brewingrecordseconomywidget.model.brew.Brew;

import java.util.Map;

/**
 * Builder responsible for building Configuration Files that can be utilised within the Bukkit Brewery Plugin.
 */
public interface BreweryConfigBuilder extends YamlBuilder<BreweryConfigYaml>{

    /**
     * Adds the config preset data to the builder, allowing for the building of the yaml file.
     */
    void addConfigData(BrewConfigData brewConfigData);

}
