package io.curiositycore.brewingrecordseconomywidget.model.util.yaml;

import org.yaml.snakeyaml.Yaml;

/**
 * Wrapper for a configuration file capable of being generated on user-request, that is capable of being utilised
 * as the Bukkit Brewery Plugin Config File.
 */
public class BreweryConfigYaml implements YamlConfig{
    @Override
    public Yaml createYamlFile(String filePath) {
        return null;
    }

    @Override
    public boolean validate() {
        return false;
    }
}
