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
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * Controller for the secondary window that opens when the user wishes to analyse, edit and generally interact with the
 * set ingredients list (as defined by the chosen configuration file).
 */
public class IngredientMenuWindowController {
    /**
     * The Table View that shows the user all the currently defined ingredients within the executable.
     */
    @FXML
    private TableView<Ingredient> ingredientTable;

    /**
     * Column of the ingredients table representing the internal (config file) name of the ingredient.
     */
    @FXML
    private TableColumn<Ingredient,String> ingredientNameColumn;
    /**
     * Column of the ingredients table representing the potential custom names of the ingredient.
     */
    @FXML
    private TableColumn<Ingredient,String> ingredientCustomNameColumn;

    /**
     * Column of the ingredients table representing the potential materials the ingredient consists of.
     */
    @FXML
    private TableColumn<Ingredient,String> ingredientPotentialMaterialsColumn;

    /**
     * Column of the ingredients table representing the user-defined cost of the ingredient.
     */
    @FXML
    private TableColumn<Ingredient,Integer> ingredientCostColumn;

    /**
     * Button for adding additional ingredients to the table.
     */
    @FXML
    private Button addButton;

    /**
     * Button for removing existing ingredients to the table.
     */
    @FXML
    private Button removeButton;

    @FXML
    public void initialize(){
        ingredientNameColumn.setCellValueFactory(ingredient-> new SimpleStringProperty(ingredient.getValue().getName()));
        ingredientCustomNameColumn.setCellValueFactory(ingredient-> new SimpleStringProperty(ingredient.getValue().getPotentialCustomNames()));
        ingredientPotentialMaterialsColumn.setCellValueFactory(ingredient-> new SimpleStringProperty(ingredient.getValue().getPotentialMaterialChoices()));
        ingredientCostColumn.setCellValueFactory(ingredient-> new SimpleIntegerProperty(ingredient.getValue().getCost()).asObject());
        setEditPropertiesOnColumn(ingredientCostColumn);
        ingredientTable.setEditable(true);

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
        this.removeButton.setDisable(false);
    }

    private void setEditPropertiesOnColumn(TableColumn<Ingredient,Integer> columnToSett){
        StringConverter<Integer> converter = new IntegerStringConverter();
        columnToSett.setCellFactory(TextFieldTableCell.forTableColumn(converter));
        columnToSett.setOnEditCommit(
                (TableColumn.CellEditEvent<Ingredient, Integer> t) -> {
                    Ingredient ingredient = t.getRowValue();
                    ingredient.setCost(t.getNewValue());
                    IngredientManager.getInstance().editIngredientCost(ingredient.getName(),ingredient.getCost());
                }
        );
    }
}
