package io.curiositycore.brewingrecordseconomywidget;

import io.curiositycore.brewingrecordseconomywidget.model.brew.Brew;
import io.curiositycore.brewingrecordseconomywidget.model.brew.BrewFactory;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
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
    private TableColumn<Brew, String> brewNameColumn;
    @FXML
    private TableColumn<Brew, String> brewNegativeEffectsColumn;
    @FXML
    private TableColumn<Brew, String> brewPositiveEffectsColumn;
    @FXML
    public void initialize(){
        BrewFactory brewFactory = new BrewFactory("/config.yml");
        ObservableList<Brew> brewData = FXCollections.observableList(brewFactory.buildBrewSet().stream().filter(Objects::nonNull).toList());

        brewNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        try{
        brewPositiveEffectsColumn.setCellValueFactory(brew -> new SimpleStringProperty(brew.getValue().getPositiveEffectsAsString()));
        brewNegativeEffectsColumn.setCellValueFactory(brew -> new SimpleStringProperty(brew.getValue().getNegativeEffectsAsString()));
        }
        catch (NullPointerException nullPointerException){}
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

        brewTable.setItems(brewData);
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
        stage.setTitle("Second Window");
        stage.setScene(new Scene(root));
        stage.show();
    }
}