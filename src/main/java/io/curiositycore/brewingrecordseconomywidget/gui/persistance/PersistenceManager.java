package io.curiositycore.brewingrecordseconomywidget.gui.persistance;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.curiositycore.brewingrecordseconomywidget.gui.persistance.brews.BrewConfigData;
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

    /**
     * Path to the default location to create the brewData directory.
     */
    private final String persistenceDirectoryPath =System.getProperty("user.home")+"/brewData";
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
     * Registers data to the Manager.
     * @param persistentData The data to register.
     */
    public void register(PersistentData persistentData){
        this.dataMap.put(persistentData.getFileName(),persistentData);
    }
    //TODO This is sloppy and doesnt allow for varying persistent data types to be read (and will likely cause an error).

    /**
     * Deserializes all the currently saved JSON files within the default brewData directory and registers them to
     * the Manager.
     * @param classOfData The class of the persistent data to read from the JSON files.
     * @param <T> The type parameter of the data to read from the JSON files.
     * @throws IOException
     */
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

    /**
     * Creates a new json file within the default brewData directory.
     * @param fileName The name of the file to create.
     * @return The empty json file with the specified file name.
     */
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

    /**
     * Loads the specified persistent data from the Manager.
     * @param fileName The name of the persistent data's source file.
     */
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

    //TODO also sloppy, doesnt take into account that soon there will be other persistent data types other than just the
    //     Config Preset JSONs.
    /**
     * Get the name of all persistent files within the Manager.
     * @return List of all persistent file names.
     */
    public List<String> getFileNames(){
        File directory = new File(this.persistenceDirectoryPath);
        if(!directory.exists() || !directory.isDirectory()){
            return Collections.emptyList();
        }
        try{
            return Arrays.stream(Objects.requireNonNull(directory.list())).toList();
        }
        catch(NullPointerException e){
            return Collections.emptyList();
        }

    }
}
