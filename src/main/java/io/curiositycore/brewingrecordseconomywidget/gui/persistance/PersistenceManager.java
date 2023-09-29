package io.curiositycore.brewingrecordseconomywidget.gui.persistance;

import io.curiositycore.brewingrecordseconomywidget.model.util.exceptions.DataNotValidException;

import java.io.File;
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
    private String persistenceDirectoryPath;
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
     * Boolean that represents if a persistent data's potential file name available to use.
     * @return True if the name is not currently being used by another file, false if otherwise.
     */
    private boolean dataNameAvailable(PersistentData dataToCheck){
        return !this.dataMap.containsKey(dataToCheck.getFileName());
    }
}
