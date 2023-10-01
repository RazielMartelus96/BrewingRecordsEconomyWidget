package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

import io.curiositycore.brewingrecordseconomywidget.gui.persistance.ConfigData;
import io.curiositycore.brewingrecordseconomywidget.model.brew.BrewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Manager responsible for the handling, registration and general functionality of the {@linkplain
 * Ingredient User-Defined Ingredients} of the Application's "Ingredient List", a window defining the general properties
 * of Ingredients utilised within Application Brews.
 */
public class IngredientManager {

    /**
     * Map containing the Ingredients of the currently active config file, with the internal name of the Ingredient Keys
     * and respective Ingredient Values.
     */
    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    /**
     * Static instance of the Manager, as per the Singleton Pattern.
     */
    private static IngredientManager instance;

    /**
     * Gets the Singleton instance of the Manager. If not already, will also initialise the Manager.
     * @return The Singleton instance of the Manager.
     */
    public static IngredientManager getInstance(){
        if(instance == null){
            instance = new IngredientManager();
        }
        return instance;
    }

    /**
     * Registers an Ingredient with the Manager.
     * @param ingredientToRegister The Ingredient to register to the Manager.
     */
    public void register(Ingredient ingredientToRegister){
        this.ingredientMap.put(ingredientToRegister.getName(),ingredientToRegister);
    }

    /**
     * Unregisters an Ingredient from the Manager.
     * @param nameOfIngredientToUnregister The internal name of the Ingredient to unregister.
     */
    public void unregister(String nameOfIngredientToUnregister){
        this.ingredientMap.remove(nameOfIngredientToUnregister);
    }

    /**
     * Boolean check to see if the specified Ingredient internal name corresponds to an Ingredient registered with the
     * Manager.
     * @param ingredientNameToCheck The internal Ingredient name to check.
     * @return True if the name corresponds to a registered Ingredient, false if not.
     */
    public boolean isRegistered(String ingredientNameToCheck){
        return this.ingredientMap.values().stream()
                .anyMatch(ingredient ->
                        ingredient.getName().contains(ingredientNameToCheck.substring(0,ingredientNameToCheck.indexOf("/")))
                        &&
                        ingredient instanceof CustomBrewIngredient);
    }

    /**
     * Gets an instance of an Ingredient, based on its name. The Ingredient is typically an instance of a {@linkplain
     * CustomBrewIngredient Custom Brew Ingredient}
     * @param ingredientName The internal name of the Ingredient.
     * @return The Ingredient corresponding to the specified internal name.
     */
    public Ingredient getCustomIngredient(String ingredientName){
        if(ingredientName.contains("/")){
            ingredientName = ingredientName.substring(0,ingredientName.indexOf("/"));
        }
        return this.ingredientMap.get(ingredientName);
    }

    /**
     * Adds the Manager's Ingredients to the specified JavaFX Ingredient containing TableView.
     * @param ingredientTableToAdd The JavaFX TableView to add the Ingredients to.
     */
    public void addIngredientsToTable(TableView<Ingredient> ingredientTableToAdd){
        ObservableList<Ingredient> ingredientData = FXCollections.observableList(this.ingredientMap.values().stream().filter(Objects::nonNull).toList());
        ingredientTableToAdd.setItems(ingredientData);
    }

    /**
     * Adds the Ingredients of the Manager to a BrewConfigData instance, typically used for persistence purposes.
     * @param dataToAddTo The instance of the BrewConfigData to add the Ingredients to.
     * @return The, now populated, BrewConfigData instance.
     */
    public ConfigData addIngredientsToConfigData(ConfigData dataToAddTo){
        dataToAddTo.addData(this.ingredientMap);
        return dataToAddTo;
    }

    /**
     * Reload the Ingredients Map of this manager with the specified Map.
     * @param reloadedMap The Ingredients Map to reload the Manager with.
     */
    public void reloadIngredients(Map<String, Ingredient> reloadedMap){
        this.ingredientMap = reloadedMap;
    }

    /**
     * Edits the cost of a specified Ingredient, and the updates required to implement this change within the
     * Application.
     * @param ingredientName The internal name of the Ingredient to edit the cost of.
     * @param cost The cost value to edit the Ingredient with.
     */
    public void editIngredientCost(String ingredientName, int cost){
        Ingredient ingredient = this.ingredientMap.get(ingredientName);
        ingredient.setCost(cost);
        BrewManager.getInstance().editBrewCost(ingredient);

    }
}
