module io.curiositycore.brewingrecordseconomywidget {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.bukkit;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.antdesignicons;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    opens io.curiositycore.brewingrecordseconomywidget.gui.persistance.brews to com.fasterxml.jackson.databind;
    opens io.curiositycore.brewingrecordseconomywidget.model.ingredients to com.fasterxml.jackson.databind;
    opens io.curiositycore.brewingrecordseconomywidget.model.brew to com.fasterxml.jackson.databind;


    requires org.controlsfx.controls;
    requires org.yaml.snakeyaml;

    opens io.curiositycore.brewingrecordseconomywidget to javafx.fxml;
    exports io.curiositycore.brewingrecordseconomywidget;
    exports io.curiositycore.brewingrecordseconomywidget.model.brew;
    exports io.curiositycore.brewingrecordseconomywidget.gui.persistance.brews;

}