package io.curiositycore.brewingrecordseconomywidget.model.util.exceptions;

import io.curiositycore.brewingrecordseconomywidget.gui.persistance.PersistentData;
//TODO investigate if this is actually required now, probably still has a use within sanity checks.
/**
 * Exception to represent when data within executable is not found to be a valid format. This Exception is to be
 * potentially thrown before any functionality of persistent data occurs.
 */
public class DataNotValidException extends RuntimeException{
    /**
     * The path, as a String, of the invalid data's source file.
     */
    private final String filePath;

    /**
     * Constructor which initialises the exception's message and the filepath of the invalid data's source file.
     * @param invalidData The data that was determined to be invalid.
     */
    public DataNotValidException(PersistentData invalidData){
        super("The data within file: "+ invalidData.getFileName()+ " is not valid.");
        this.filePath = invalidData.getFileName();
    }

    /**
     * Gets the path of the invalid data's source file.
     * @return The path of the invalid data's source file.
     */
    public String getInvalidFilePath(){
        return this.filePath;
    }
}
