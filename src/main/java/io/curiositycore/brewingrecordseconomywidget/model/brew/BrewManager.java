package io.curiositycore.brewingrecordseconomywidget.model.brew;

import io.curiositycore.brewingrecordseconomywidget.gui.persistance.brews.BrewConfigData;

import java.util.HashMap;
import java.util.Map;

public class BrewManager {
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
    public void register(Brew brewToRegister){
        this.brewMap.put(brewToRegister.getName(),brewToRegister);
    }

    public void unregister(String nameOfBrewToUnregister){
        this.brewMap.remove(nameOfBrewToUnregister);
    }

    public BrewConfigData addBrewsToConfigData(BrewConfigData dataToAddTo){
        dataToAddTo.addData(this.brewMap);
        return dataToAddTo;
    }

}
