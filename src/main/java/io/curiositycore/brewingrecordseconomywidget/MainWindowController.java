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
import java.util.List;
import java.util.Objects;

/**
 * Controller responsible for the functionality of the main JavaFX windows. Main responsibilities include the population
 * of the ingredient and brew tables, along with event handling of the various menu items.
 */
public class MainWindowController {
    /**
     * Table that contains all the brews within the currently loaded configuration.
     */
    @FXML
    private TableView<Brew> brewTable;

    /**
     * Column that defines the internal name of a Brew.
     */
    @FXML
    private TableColumn<Brew,String> brewInternalNameColumn;

    /**
     * Column that defines the in-game name of a Brew.
     */
    @FXML
    private TableColumn<Brew, String> brewNameColumn;

    /**
     * Column that defines the overall cost of the Brew (in terms of ingredients)
     */
    @FXML
    private TableColumn<Brew, Integer> brewCostColumn;

    /**
     * Column that defines the various positive effects that a Brew contains.
     */
    @FXML
    private TableColumn<Brew, String> brewPositiveEffectsColumn;

    /**
     * Column that defines the various negative effects that a Brew contains.
     */
    @FXML
    private TableColumn<Brew, String> brewNegativeEffectsColumn;

    /**
     * Column that defines the player that owns the Brew.
     */
    @FXML
    private TableColumn<Brew, String> brewOwnerColumn;

    /**
     * Table that contains all the ingredients that are within the brew currently selected in the Brew Table.
     */
    @FXML
    private TableView<Ingredient> ingredientTable;

    /**
     * Column that defines the name of the ingredient.
     */
    @FXML
    private TableColumn<Ingredient, String> ingredientNameColumn;

    /**
     * Column that defines the amount of the ingredient required for the specified Brew.
     */
    @FXML
    private TableColumn<Ingredient, Integer> ingredientAmountColumn;

    /**
     * Column that defines the overall cost of the ingredient (amount*base cost) required for the specified Brew.
     */
    @FXML
    private TableColumn<Ingredient, Integer> ingredientCostColumn;

    /**
     * Column that defines the crafting materials required to make this ingredient.
     */
    @FXML
    private TableColumn<Ingredient, String> ingredientCraftingMaterialsColumn;

    /**
     * The Menu that shows all the recently opened configs (presets that have been saved previously in the application)
     */
    @FXML
    private Menu openRecentConfigMenu;

    /**
     * Occurs upon initialisation of the window.
     * @throws IOException
     */
    @FXML
    public void initialize() throws IOException {
        PersistenceManager.getInstance().readAllDataToCache(BrewConfigData.class);
        createRecentConfigMenuItems();
        BrewFactory brewFactory = new BrewFactory("/config.yml");
        brewFactory.buildBrewSet();
        brewInternalNameColumn.setCellValueFactory(new PropertyValueFactory<>("internalName"));

        brewNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        try{
        brewPositiveEffectsColumn.setCellValueFactory(brew -> new SimpleStringProperty(brew.getValue().getPositiveEffectsAsString()));
        brewNegativeEffectsColumn.setCellValueFactory(brew -> new SimpleStringProperty(brew.getValue().getNegativeEffectsAsString()));
        brewCostColumn.setCellValueFactory(brew-> new SimpleIntegerProperty(brew.getValue().getCost()).asObject());
        brewOwnerColumn.setCellValueFactory(brew -> new SimpleStringProperty(brew.getValue().getOwner()));
        setEditPropertiesOnOwnerColumn();
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

    /**
     * Handler that loads and displays the Ingredients table for the specified Brew.
     * @param brewOnRow The brew that is currently selected within the Brew Table.
     */
    public void onRowClick(Brew brewOnRow){
        ingredientNameColumn.setCellValueFactory(ingredient-> new SimpleStringProperty(ingredient.getValue().getName()));
        ingredientCostColumn.setCellValueFactory(ingredient-> new SimpleIntegerProperty(ingredient.getValue().getCost()).asObject());
        ingredientAmountColumn.setCellValueFactory(ingredient-> new SimpleIntegerProperty(ingredient.getValue().getAmount()).asObject());
        ingredientCraftingMaterialsColumn.setCellValueFactory(ingredient-> new SimpleStringProperty(ingredient.getValue().getPotentialCraftingIngredients()));
        ObservableList<Ingredient> ingredientData = FXCollections.observableList(brewOnRow.getIngredients().stream().filter(Objects::nonNull).toList());
        this.ingredientTable.setItems(ingredientData);
    }

    /**
     * Handles when the IngredientsList Menu Item is clicked. This loads the Ingredient Menu, as controlled by the
     * {@linkplain IngredientMenuWindowController Ingredient Menu Controller}.
     * @throws IOException
     */
    @FXML
    public void onIngredientsListOpen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ingredient-menu-window.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Ingredients");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Saves the current preset to a JSON file for the sake of persistence. This file can be called via utilising the
     * "Open Recent Config" menu item.
     */
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

    /**
     * Creates the menu items to populate the "Open Recent Config" nested menu within the File Menu.
     */
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

    //TODO consider creating a more generalised version of this method in future, and potentially adding it to a type of
    //     util class.
    /**
     * Defines the properties for the Owner Column of a Brew Table, allowing for users to edit who owns the Brew.
     */
    private void setEditPropertiesOnOwnerColumn(){
        this.brewOwnerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.brewOwnerColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Brew, String> t) ->
                        t.getRowValue().setOwner(t.getNewValue()));

    }

}