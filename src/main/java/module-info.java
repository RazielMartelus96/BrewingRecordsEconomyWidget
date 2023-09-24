module io.curiositycore.brewingrecordseconomywidget {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.bukkit;

    requires org.controlsfx.controls;
    requires org.yaml.snakeyaml;

    opens io.curiositycore.brewingrecordseconomywidget to javafx.fxml;
    exports io.curiositycore.brewingrecordseconomywidget;
    exports io.curiositycore.brewingrecordseconomywidget.model.brew;

}