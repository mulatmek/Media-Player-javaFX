package com.example.mediaplayer;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class MediaController {
    private String path;
    private MediaPlayer mediaPlayer;
    @FXML
    Slider volume;
    @FXML
    Slider slider;

    @FXML
    MediaView mediaView;
    public void chooseFile(ActionEvent event){
        FileChooser fileChooser =new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        this.path = file.toURI().toString();
        if(path!=null){
            Media media =new Media(path);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            DoubleProperty widthProperty =mediaView.fitWidthProperty();
            DoubleProperty heightProperty =mediaView.fitHeightProperty();

            widthProperty.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
            heightProperty.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));

            mediaPlayer.currentTimeProperty().addListener((new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration oldVal, Duration newVal) {
                    slider.setValue(newVal.toSeconds());
                }
            }));
            slider.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(Duration.seconds(slider.getValue()));
                }
            });
            slider.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(Duration.seconds(slider.getValue()));
                }
            });
            mediaPlayer.play();
        }
    }
    public void play(ActionEvent event){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }
    public  void pause(){
        mediaPlayer.pause();
    }
    public void stop(){
        mediaPlayer.stop();
    }
    public  void slowRate(){
        mediaPlayer.setRate(0.5);

    }
    public void fastForward(){
        mediaPlayer.setRate(2);
    }
    public  void goForward10Sec(){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(10)));

    }
    public void goBack10Sec(){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(-10)));
    }
    public void setVolume(){
        mediaPlayer.setVolume(volume.getValue());
    }
}