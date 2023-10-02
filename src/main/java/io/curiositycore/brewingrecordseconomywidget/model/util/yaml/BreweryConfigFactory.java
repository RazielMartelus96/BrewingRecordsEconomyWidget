package io.curiositycore.brewingrecordseconomywidget.model.util.yaml;

import io.curiositycore.brewingrecordseconomywidget.gui.persistance.brews.BrewConfigData;

/**
 * Factory responsible for construction of Configuration Files that can be utilised within the Bukkit Brewery Plugin.
 */
public interface BreweryConfigFactory extends YamlFactory<BreweryConfigYaml> {

    /**
     * Adds the config preset data to the builder, allowing for the construction of the yaml file.
     */
    void addConfigData(BrewConfigData brewConfigData);

}
