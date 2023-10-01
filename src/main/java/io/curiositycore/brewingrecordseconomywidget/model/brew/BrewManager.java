package io.curiositycore.brewingrecordseconomywidget.model.brew;

import io.curiositycore.brewingrecordseconomywidget.gui.persistance.ConfigData;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Manager responsible for the registering, handling and general functionality of {@linkplain Brew Brews} within the
 * Application.
 */
public class BrewManager {
    /**
     * Instance of the table used to show the various Brews of the current preset to the user.
     */
    private TableView<Brew> brewTable;

    /**
     * Map representing all the registered Brews within the Manager, with the Keys being the Brew's internal name.
     */
    private Map<String, Brew> brewMap = new HashMap<>();

    /**
     * Instance of the Manager (as per the Singleton Pattern).
     */
    private static BrewManager instance;

    /**
     * Gets the Singleton Instance of the Manager. If the Manager is not already initialised, does so.
     * @return The Singleton Instance of the Manager.
     */
    public static BrewManager getInstance(){
        if(instance == null){
            instance = new BrewManager();
        }
        return instance;
    }

    /**
     * Reload the Brews registered to this Manager with the specified Brew Map.
     * @param reloadedMap The Brew Map to be reloaded to.
     */
    public void reloadBrews(Map<String,Brew> reloadedMap){
        this.brewMap = reloadedMap;
    }

    /**
     * Sets the Brew Table contents of the specified table to the Registered Brews of this Manager.
     * @param brewTableToSet The Brew table to set the contents of.
     */
    public void setTableToBrews(TableView<Brew> brewTableToSet) {
        Platform.runLater(() -> {
            brewTableToSet.setItems(FXCollections.observableList(
                    this.brewMap.values().stream()
                            .sorted(Comparator.comparing(Brew::getInternalName))
                            .toList()
            ));
            if (this.brewTable == null) {
                this.brewTable = brewTableToSet;
            }
        });
    }

    /**
     * Sets the stored Brew Table of this Manager to the registered Brews of the Manager.
     */
    private void setTableToBrews(){
        Platform.runLater(() -> {
            this.brewTable.setItems(FXCollections.observableList(
                    this.brewMap.values().stream()
                            .sorted(Comparator.comparing(Brew::getInternalName))
                            .toList()
            ));
            this.brewTable.refresh();});
    }

    /**
     * Register a Brew to the Manager.
     * @param brewToRegister The Brew to register.
     */
    public void register(Brew brewToRegister){
        this.brewMap.put(brewToRegister.getInternalName(),brewToRegister);
    }

    /**
     * Unregister a Brew from the Manager.
     * @param nameOfBrewToUnregister The internal name of the Brew to unregister.
     */
    public void unregister(String nameOfBrewToUnregister){
        this.brewMap.remove(nameOfBrewToUnregister);
    }

    /**
     * Add the registered Brews of the Manager to a Brew Config Data Instance, for the purposes of saving to a JSON.
     * @param dataToAddTo The Brew Config Data Instance to add the Brews to.
     * @return The populated Brew Config Data Instance
     */
    public ConfigData addBrewsToConfigData(ConfigData dataToAddTo){
        dataToAddTo.addData(this.brewMap);
        return dataToAddTo;
    }

    /**
     * Edit the overall cost of all Brews that include Ingredients of the specified type.
     * @param ingredient The Ingredient required for a Brew to be edited.
     */
    public void editBrewCost(Ingredient ingredient){
        this.brewMap.values()
                .stream()
                .filter(brew-> brew.getIngredients()
                        .stream()
                        .anyMatch(brewIngredient -> brewIngredient.getName().equals(ingredient.getName())))
                .forEach(brew1 -> brew1.reloadCosts(ingredient));
        setTableToBrews();
    }

}
