package io.curiositycore.brewingrecordseconomywidget.model.util.yaml;

import io.curiositycore.brewingrecordseconomywidget.model.brew.Brew;
import io.curiositycore.brewingrecordseconomywidget.model.util.Builder;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

/**
 * Interface representing the general functionality of a class relevant to the building of {@linkplain
 * YamlConfig Yaml Configuration Files} that store user-defined data.
 */
public interface YamlBuilder<T extends YamlConfig> extends Builder<T> {

}
