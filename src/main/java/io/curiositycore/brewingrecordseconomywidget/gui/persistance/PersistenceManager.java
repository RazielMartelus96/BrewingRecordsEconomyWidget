package io.curiositycore.brewingrecordseconomywidget.gui.persistance;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.curiositycore.brewingrecordseconomywidget.gui.persistance.brews.BrewConfigData;
import io.curiositycore.brewingrecordseconomywidget.model.brew.BrewFactory;
import java.io.File;
import java.io.IOException;

import java.util.*;

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
    private ObjectMapper objectMapper = new ObjectMapper();
    private final String persistenceDirectoryPath =System.getProperty("user.home")+"/brewData";
    BrewFactory brewFactory = new BrewFactory("/config.yml");;
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
    public void addSavedData(PersistentData persistentData){
        this.dataMap.put(persistentData.getFileName(),persistentData);
    }
    //TODO make custom exception here
    public<T extends PersistentData>  void readAllDataToCache(Class<T> classOfData) throws IOException {
        File directory = new File(this.persistenceDirectoryPath);
        if(!directory.exists() || !directory.isDirectory()){
            return;
        }
        for(File datafile : Objects.requireNonNull(directory.listFiles())){
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            BrewConfigData brewMap = mapper.readValue(datafile, BrewConfigData.class);
            this.dataMap.put(brewMap.getFileName(),brewMap);
        }
    }
    public File createPersistenceFile(String fileName){
        if(fileName == null){
            return null;
        }
        File directory = new File(this.persistenceDirectoryPath);
        if(!directory.exists()){
            directory.mkdirs();
        }
        return new File(this.persistenceDirectoryPath,fileName+".json");
    }
    public void loadData(String fileName){
        this.dataMap.get(fileName).load();
    }

    /**
     * Boolean that represents if a persistent data's potential file name available to use.
     * @return True if the name is not currently being used by another file, false if otherwise.
     */
    private boolean dataNameAvailable(PersistentData dataToCheck){
        return !this.dataMap.containsKey(dataToCheck.getFileName());
    }
    public List<String> getFileNames(){
        File directory = new File(this.persistenceDirectoryPath);
        if(!directory.exists() || !directory.isDirectory()){
            return Collections.emptyList();
        }
        try{
            return Arrays.stream(Objects.requireNonNull(directory.list())).toList();
        }
        catch(NullPointerException e){
            System.out.println("Directory was null");
            return Collections.emptyList();
        }

    }
}
