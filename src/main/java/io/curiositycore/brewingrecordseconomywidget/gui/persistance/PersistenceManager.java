package io.curiositycore.brewingrecordseconomywidget.gui.persistance;

import io.curiositycore.brewingrecordseconomywidget.model.util.exceptions.DataNotValidException;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager responsible for the registering, validating and general functionality of data persistent between instances
 * of the executable.
 */
public class PersistenceManager {
    /**
     * Map containing the various persistent data instances within the executable. The key of the map is the persistent
     * data's file name, as a String.
     */
    private Map<String, PersistentData> dataMap = new HashMap<>();
    /**
     * Instance of the Persistence Manager, as per the Singleton Pattern.
     */
    private static PersistenceManager instance;

    /**
     * Method for retrieving the Singleton Instance of the Persistence Manager.
     */
    public static PersistenceManager getInstance(){
        if(instance == null){
            instance = new PersistenceManager();
        }
        return instance;
    }

    /**
     * Saves the persistent data to the persistent storage of the executable.
     * @param dataToSave The persistent data to save.
     * @throws DataNotValidException
     */
    public void savePersistenceData(PersistentData dataToSave) throws DataNotValidException{
        if(dataToSave.validate()){
            dataToSave.save();
        }
        else{
            throw new DataNotValidException(dataToSave);
        }
    }
}
