package io.curiositycore.brewingrecordseconomywidget.gui.persistance.controls;

import javafx.scene.control.TextInputDialog;

/**
 * Dialog box for choosing the file name when saving a Brew Configuration Preset.
 */
public class SaveDialogBox extends TextInputDialog {
    /**
     * Constructor for the dialog box which initialises the box's title, header and content text.
     */
    public SaveDialogBox(){

        this.setTitle("Choose a file name");
        this.setHeaderText("Choose a name for the saved config preset!");
        this.setContentText("File Name");
    }
}
