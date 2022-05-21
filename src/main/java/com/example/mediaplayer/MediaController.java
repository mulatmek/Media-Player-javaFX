package com.example.mediaplayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

public class MediaController {
    private String path;
    public void chooseFile(ActionEvent event){
        FileChooser fileChooser =new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        this.path = file.toURI().toString();
    }
}