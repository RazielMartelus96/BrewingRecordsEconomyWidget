package io.curiositycore.brewingrecordseconomywidget.gui.persistance.controls;

import javafx.scene.control.TextInputDialog;

public class SaveDialogBox extends TextInputDialog {
    public SaveDialogBox(){

        this.setTitle("Choose a file name");
        this.setHeaderText("Choose a name for the saved config preset!");
        this.setContentText("File Name");
    }
}
