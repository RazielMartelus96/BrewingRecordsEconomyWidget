package io.curiositycore.brewingrecordseconomywidget.model.util;

import io.curiositycore.brewingrecordseconomywidget.BrewingRecordsEcononyWidget;
import io.curiositycore.brewingrecordseconomywidget.MainWindowController;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
//TODO good example for KK to potentially have a go at doing documentation for
public class YamlParser {

    public static Map<String,Object> getConfigMap(String filepath){
        Map<String,Object> configFileMap;
        Yaml configFile = new Yaml();
        InputStream inputStream = BrewingRecordsEcononyWidget.class.getResourceAsStream(filepath);
        return configFile.load(inputStream);

    }
}
