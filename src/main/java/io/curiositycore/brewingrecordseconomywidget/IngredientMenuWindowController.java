package io.curiositycore.brewingrecordseconomywidget;

import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.IngredientManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;

public class IngredientMenuWindowController {
    @FXML
    private TableView<Ingredient> ingredientTable;
    @FXML
    private TableColumn<Ingredient,String> ingredientNameColumn;
    @FXML
    private TableColumn<Ingredient,String> ingredientCustomNameColumn;
    @FXML
    private TableColumn<Ingredient,String> ingredientPotentialMaterialsColumn;
    @FXML
    private TableColumn<Ingredient,Integer> ingredientCostColumn;

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;

    @FXML
    public void initialize(){
        ingredientNameColumn.setCellValueFactory(ingredient-> new SimpleStringProperty(ingredient.getValue().getName()));
        ingredientCustomNameColumn.setCellValueFactory(ingredient-> new SimpleStringProperty(ingredient.getValue().getPotentialMaterialChoices()));
        ingredientPotentialMaterialsColumn.setCellValueFactory(ingredient-> new SimpleStringProperty(ingredient.getValue().getPotentialMaterialChoices()));
        ingredientCostColumn.setCellValueFactory(ingredient-> new SimpleIntegerProperty(ingredient.getValue().getCost()).asObject());
        this.ingredientTable.setRowFactory(tv -> {
            TableRow<Ingredient> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    onTableRowClick();
                }
            });
            return row;
        });
        IngredientManager.getInstance().addIngredientsToTable(this.ingredientTable);

    }

    public void onTableRowClick(){
        this.editButton.setDisable(false);
        this.removeButton.setDisable(false);
    }
}
