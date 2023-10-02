package io.curiositycore.brewingrecordseconomywidget.model.util.yaml;

import io.curiositycore.brewingrecordseconomywidget.gui.persistance.brews.BrewConfigData;
//TODO need to create the Yaml, populate the ingredients, populate the brews and then have the token settings at the
//     top of the newly created file (could even have a templated version of the config on hand?)
/**
 * Factory responsible for the construction of {@linkplain YamlConfig Config Files} that are utilised within the
 * Stoneworks version of the Brewery Plugin.
 */
public class StoneworksBreweryConfigFactory implements BreweryConfigFactory{
    /**
     * The data to be used to construct the Yaml file.
     */
    BrewConfigData brewConfigData;
    @Override
    public BreweryConfigYaml build() {
        return null;
    }

    @Override
    public void addConfigData(BrewConfigData brewConfigData) {
        this.brewConfigData = brewConfigData;
    }
}
