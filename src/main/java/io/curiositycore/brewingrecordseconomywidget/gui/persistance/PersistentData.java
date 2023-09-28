package io.curiositycore.brewingrecordseconomywidget.gui.persistance;

/**
 * Interface representing the generalisation of data that is persistent between instances of the executable.
 */
public interface PersistentData {
    /**
     * Save the current instance of the data, for recall in future calls within the instance.
     */
    void save();

    /**
     * Load saved data from the persistent storage of the executable.
     */
    void load();

    /**
     * Boolean that represents if the data within this instance is valid-for-purpose.
     * @return True if the data is validated successfully, false if otherwise.
     */
    boolean validate();

    /**
     * Append an object to the persistent data.
     * @param objectToAppendToData
     */
    void addData(Object objectToAppendToData);

    /**
     * Gets the name of the persistent data's source file.
     * @return The name of the data's source file.
     */
    String getFileName();

    /**
     * Gets the path of the persistent data's source file, as a String.
     * @return The path of the persistent data's source file.
     */
    String getFilePath();
}
