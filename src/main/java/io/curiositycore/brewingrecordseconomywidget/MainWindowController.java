package io.curiositycore.brewingrecordseconomywidget;

import io.curiositycore.brewingrecordseconomywidget.model.brew.Brew;
import io.curiositycore.brewingrecordseconomywidget.model.brew.BrewFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainWindowController {
    @FXML
    private TableView<Brew> brewTable;
    @FXML
    private TableColumn<Brew, String> brewNameColumn;
    @FXML
    private TableColumn<Brew, String> brewNegativeEffectsColumn;
    @FXML
    private TableColumn<Brew, String> brewPositiveEffectsColumn;
    @FXML
    public void initialize(){
        BrewFactory brewFactory = new BrewFactory("/config.yml");
        ObservableList<Brew> brewData = FXCollections.observableList(brewFactory.buildBrewSet().stream().toList());
        brewNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        brewPositiveEffectsColumn.setCellValueFactory(brew -> new SimpleStringProperty(brew.getValue().getPositiveEffectsAsString()));
        brewNegativeEffectsColumn.setCellValueFactory(brew -> new SimpleStringProperty(brew.getValue().getNegativeEffectsAsString()));
        brewTable.setItems(brewData);
    }
}