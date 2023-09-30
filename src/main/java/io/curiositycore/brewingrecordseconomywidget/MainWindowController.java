package io.curiositycore.brewingrecordseconomywidget;

import io.curiositycore.brewingrecordseconomywidget.gui.persistance.PersistenceManager;
import io.curiositycore.brewingrecordseconomywidget.gui.persistance.brews.BrewConfigData;
import io.curiositycore.brewingrecordseconomywidget.gui.persistance.controls.SaveDialogBox;
import io.curiositycore.brewingrecordseconomywidget.model.brew.Brew;
import io.curiositycore.brewingrecordseconomywidget.model.brew.BrewFactory;
import io.curiositycore.brewingrecordseconomywidget.model.brew.BrewManager;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.IngredientManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MainWindowController {
    @FXML
    private TableView<Ingredient> ingredientTable;
    @FXML
    private TableColumn<Ingredient, String> ingredientNameColumn;
    @FXML
    private TableColumn<Ingredient, Integer> ingredientAmountColumn;
    @FXML
    private TableColumn<Ingredient, Integer> ingredientCostColumn;
    @FXML
    private TableColumn<Ingredient, String> ingredientCraftingMaterialsColumn;
    @FXML
    private TableView<Brew> brewTable;
    @FXML
    private TableColumn<Brew,String> brewInternalNameColumn;
    @FXML
    private TableColumn<Brew, String> brewNameColumn;
    @FXML
    private TableColumn<Brew, Integer> brewCostColumn;
    @FXML
    private TableColumn<Brew, String> brewNegativeEffectsColumn;
    @FXML
    private TableColumn<Brew, String> brewPositiveEffectsColumn;
    @FXML
    private TableColumn<Brew, String> brewOwnerColumn;

    @FXML
    private Menu openRecentConfigMenu;

    @FXML
    public void initialize() throws IOException {
        PersistenceManager.getInstance().readAllDataToCache(BrewConfigData.class);
        createRecentConfigMenuItems();
        BrewFactory brewFactory = new BrewFactory("/config.yml");
        ObservableList<Brew> brewData = FXCollections.observableList(brewFactory.buildBrewSet().stream().filter(Objects::nonNull).sorted(Comparator.comparing(Brew::getInternalName)).toList());
        brewInternalNameColumn.setCellValueFactory(new PropertyValueFactory<>("internalName"));

        brewNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        try{
        brewPositiveEffectsColumn.setCellValueFactory(brew -> new SimpleStringProperty(brew.getValue().getPositiveEffectsAsString()));
        brewNegativeEffectsColumn.setCellValueFactory(brew -> new SimpleStringProperty(brew.getValue().getNegativeEffectsAsString()));
        brewCostColumn.setCellValueFactory(brew-> new SimpleIntegerProperty(brew.getValue().getCost()).asObject());
        brewOwnerColumn.setCellValueFactory(brew -> new SimpleStringProperty(brew.getValue().getOwner()));
        setEditPropertiesOnColumn(brewOwnerColumn);
        brewTable.setEditable(true);
        }
        catch (NullPointerException nullPointerException){
            nullPointerException.printStackTrace();
        }
        brewTable.setRowFactory(tv -> {
            TableRow<Brew> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    onRowClick(row.getItem());

                }
            });
            return row;
        });

        BrewManager.getInstance().setTableToBrews(brewTable);
    }

    public void onRowClick(Brew brewOnRow){
        ingredientNameColumn.setCellValueFactory(ingredient-> new SimpleStringProperty(ingredient.getValue().getName()));
        ingredientCostColumn.setCellValueFactory(ingredient-> new SimpleIntegerProperty(ingredient.getValue().getCost()).asObject());
        ingredientAmountColumn.setCellValueFactory(ingredient-> new SimpleIntegerProperty(ingredient.getValue().getAmount()).asObject());
        ingredientCraftingMaterialsColumn.setCellValueFactory(ingredient-> new SimpleStringProperty(ingredient.getValue().getPotentialCraftingIngredients()));
        ObservableList<Ingredient> ingredientData = FXCollections.observableList(brewOnRow.getIngredients().stream().filter(Objects::nonNull).toList());
        this.ingredientTable.setItems(ingredientData);
    }

    @FXML
    public void onIngredientsListOpen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ingredient-menu-window.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Ingredients");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void saveBrewsToPersistentData(){
        TextInputDialog dialog = new SaveDialogBox();
        File fileToSave = PersistenceManager.getInstance().createPersistenceFile(dialog.showAndWait().orElse(null));
        if(fileToSave == null){
            return;
        }
        BrewConfigData configDataToSave = new BrewConfigData(fileToSave.getName());

        configDataToSave = BrewManager.getInstance().addBrewsToConfigData(configDataToSave);
        configDataToSave = IngredientManager.getInstance().addIngredientsToConfigData(configDataToSave);
        configDataToSave.save(fileToSave);
    }

    private void createRecentConfigMenuItems(){
        List<String> configDataNames = PersistenceManager.getInstance().getFileNames();
        if(configDataNames == null){
            return;
        }
        for (String dataName : configDataNames) {
            MenuItem menuItem = new MenuItem(dataName);
            menuItem.setOnAction(event -> {
                // Action to be performed when a recent file is selected
                PersistenceManager.getInstance().loadData(dataName);
                BrewManager.getInstance().setTableToBrews(this.brewTable);
            });
            this.openRecentConfigMenu.getItems().add(menuItem);
        }
    }
    private void setEditPropertiesOnColumn(TableColumn<Brew,String> columnToSett){
        columnToSett.setCellFactory(TextFieldTableCell.forTableColumn());
        columnToSett.setOnEditCommit(
                (TableColumn.CellEditEvent<Brew, String> t) -> {

                    t.getRowValue().setOwner(t.getNewValue());
                }
        );

    }

}