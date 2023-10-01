package io.curiositycore.brewingrecordseconomywidget.model.util.yaml;

import io.curiositycore.brewingrecordseconomywidget.BrewingRecordsEcononyWidget;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * Utility class responsible for functionality relevant to the parsing of Yaml Files.
 */
public class YamlParser {

    /**
     * Private constructor, to prevent instances of the class being constructed from the implicit public one.
     */
    private YamlParser(){}

    /**
     * Gets the root map of a File with the Yaml Format. This allows for more in-depth parsing within other methods /
     * classes.
     * @param filepath The file path of the potential Yaml file.
     * @return The Yaml File's contents as a Map representation of the Yaml File.
     */
    public static Map<String,Object> getConfigMap(String filepath){
        Yaml configFile = new Yaml();
        InputStream inputStream = BrewingRecordsEcononyWidget.class.getResourceAsStream(filepath);
        return configFile.load(inputStream);

    }
}
