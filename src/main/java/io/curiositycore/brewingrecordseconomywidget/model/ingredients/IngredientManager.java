package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

import io.curiositycore.brewingrecordseconomywidget.gui.persistance.brews.BrewConfigData;
import io.curiositycore.brewingrecordseconomywidget.model.brew.BrewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IngredientManager {

    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    private static IngredientManager instance;
    public static IngredientManager getInstance(){
        if(instance == null){
            instance = new IngredientManager();
        }
        return instance;
    }
    public void register(Ingredient ingredientToRegister){
        this.ingredientMap.put(ingredientToRegister.getName(),ingredientToRegister);
    }

    public void unregister(String nameOfIngredientToUnregister){
        this.ingredientMap.remove(nameOfIngredientToUnregister);
    }

    public boolean isRegistered(String ingredientNameToCheck){
        return this.ingredientMap.values().stream()
                .anyMatch(ingredient ->
                        ingredient.getName().contains(ingredientNameToCheck.substring(0,ingredientNameToCheck.indexOf("/")))
                        &&
                        ingredient instanceof CustomBrewIngredient);
    }

    public Ingredient getCustomIngredient(String ingredientName){
        if(ingredientName.contains("/")){
            ingredientName = ingredientName.substring(0,ingredientName.indexOf("/"));
        }
        return this.ingredientMap.get(ingredientName);
    }
    public void addIngredientsToTable(TableView<Ingredient> ingredientTableToAdd){
        ObservableList<Ingredient> ingredientData = FXCollections.observableList(this.ingredientMap.values().stream().filter(Objects::nonNull).toList());
        ingredientTableToAdd.setItems(ingredientData);
    }

    public BrewConfigData addIngredientsToConfigData(BrewConfigData dataToAddTo){
        dataToAddTo.addData(this.ingredientMap);
        return dataToAddTo;
    }
    public void reloadIngredients(Map<String, Ingredient> reloadedMap){
        this.ingredientMap = reloadedMap;
    }

    public void editIngredientCost(String ingredientName, int cost){
        Ingredient ingredient = this.ingredientMap.get(ingredientName);
        ingredient.setCost(cost);
        BrewManager.getInstance().editBrewCost(ingredient);

    }
}
