package com.shunli.fxmusicplayer;

import com.shunli.fxmusicplayer.view.Slider;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public ScrollPane leftList;
    public ListView<Node> musicList;
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

        HashSet<String> set = new HashSet<>(Arrays.asList("mp3", "flac", "mid", "ape", "aac", "wma"));
        FileUtil.loadFile(OsUtils.getDownloads(), set, this::showMusicList);
    }

    private void showMusicList(List<File> result) {
        double width = (musicList).getWidth();
        System.out.println(width);
        musicList.getItems().clear();
        GridPane child = createChild(null);
        child.prefWidthProperty().bind(musicList.widthProperty());
        musicList.getItems().add(child);
        for (File file : result) {
            musicList.getItems().add(createChild(file));
        }
        System.out.println(musicList.getWidth());
    }

    private GridPane createChild(File file) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setHgap(20);
        for (int i = 0; i < 4; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            if (i == 0) {
                columnConstraints.setPercentWidth(40);
            } else {
                columnConstraints.setPercentWidth(20);
            }
            gridPane.getColumnConstraints().add(columnConstraints);
        }
        if (file == null) {
            gridPane.addColumn(0, new Label("歌曲名"));
            gridPane.addColumn(1, new Label("歌手"));
            gridPane.addColumn(2, new Label("专辑"));
            gridPane.addColumn(3, new Label("时长"));
        } else {
            gridPane.addColumn(0, new Label(file.getName()));
            Media media = null;
            try {
                media = new Media(file.toURI().toString());
            } catch (Exception e) {
                System.out.println("error: " + file.getAbsolutePath());
                return gridPane;
            }
            Duration duration = media.getDuration();
            ObservableMap<String, Object> metadata = media.getMetadata();
            Label artist = new Label("歌曲名");
            gridPane.addColumn(1, artist);
            Label album = new Label("歌曲名");
            gridPane.addColumn(2, album);
            Label durationLabel = new Label("歌曲名");
            durationLabel.setText(FileUtil.formatDuration((long) duration.toMillis()));
            gridPane.addColumn(3, durationLabel);
            metadata.addListener((MapChangeListener<String, Object>) change -> {
                if (change.wasAdded()) {
                    if ("artist".equals(change.getKey())) {
                        artist.setText(change.getValueAdded().toString());
                    } else if ("album".equals(change.getKey())) {
                        album.setText(change.getValueAdded().toString());
                    }
                }
            });
        }
        return gridPane;
    }
}