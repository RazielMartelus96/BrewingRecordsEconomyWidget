package io.curiositycore.brewingrecordseconomywidget.model.util.yaml;

import org.yaml.snakeyaml.Yaml;

/**
 * Interface representing the general functionality of a Yaml Configuration File that's capable of being generated via
 * user-request.
 */
public interface YamlConfig {
    /**
     * Create the Config File within the specified file path.
     * @param filePath The file path to create the generated Config File.
     * @return
     */
    Yaml createYamlFile(String filePath);

    /**
     * Validate the Config File is in a valid form for its specified purpose.
     * @return True if the Config File is valid, false if otherwise.
     */
    boolean validate();
}
