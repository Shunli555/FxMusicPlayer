package com.shunli.fxmusicplayer;

import com.shunli.fxmusicplayer.view.Slider;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public ScrollPane leftList;
    public ListView musicList;
    public HBox toolbar;
    public ImageView back;
    public ImageView minus;
    public ImageView next;
    public ImageView max;
    public ImageView close;
    public TextField searchBox;
    public ImageView headerImg;
    public VBox controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox content = (VBox) leftList.getContent();
        content.prefWidthProperty().bind(leftList.widthProperty());
        content.prefHeightProperty().bind(leftList.heightProperty());

        Slider slider = new Slider();
        Background background = new Background(new BackgroundFill(Paint.valueOf("#b2b2b2"), new CornerRadii(10), null));
        slider.setBackgroundColor(background);
        Background foreground = new Background(new BackgroundFill(Paint.valueOf("#F4503E"), new CornerRadii(10), null));
        slider.setForeground(foreground);
        slider.setThumbSize(10);
        slider.setThumbFill(Color.WHITE);
        slider.setThumbStroke(4, Paint.valueOf("#F4503E"));
        slider.setTrackHeight(4);
        controller.getChildren().add(0, slider);
    }
}