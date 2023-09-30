package io.curiositycore.brewingrecordseconomywidget.model.brew;

import io.curiositycore.brewingrecordseconomywidget.gui.persistance.brews.BrewConfigData;
import io.curiositycore.brewingrecordseconomywidget.model.ingredients.Ingredient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class BrewManager {
    private TableView<Brew> brewTable;
    private Map<String, Brew> brewMap = new HashMap<>();
    private static BrewManager instance;
    public static BrewManager getInstance(){
        if(instance == null){
            instance = new BrewManager();
        }
        return instance;
    }
    public void reloadBrews(Map<String,Brew> reloadedMap){
        this.brewMap = reloadedMap;
    }
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
    private void setTableToBrews(){
        Platform.runLater(() -> {
            this.brewTable.setItems(FXCollections.observableList(
                    this.brewMap.values().stream()
                            .sorted(Comparator.comparing(Brew::getInternalName))
                            .toList()
            ));
            this.brewTable.refresh();});
    }
    public void register(Brew brewToRegister){
        this.brewMap.put(brewToRegister.getInternalName(),brewToRegister);
    }

    public void unregister(String nameOfBrewToUnregister){
        this.brewMap.remove(nameOfBrewToUnregister);
    }

    public BrewConfigData addBrewsToConfigData(BrewConfigData dataToAddTo){
        dataToAddTo.addData(this.brewMap);
        return dataToAddTo;
    }
    public void editBrewCost(Ingredient ingredient){
        this.brewMap.values().stream().filter(brew-> brew.getIngredients().contains(ingredient)).forEach(Brew::reloadCosts);
        setTableToBrews();
    }

}
