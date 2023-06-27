package com.shunli.fxmusicplayer.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Slider extends Pane {

    private final Pane background;
    private final Pane foreground;
    private final Circle track;
    private final IntegerProperty mProgress;
    private int max = 100;

    public Slider() {
        mProgress = new SimpleIntegerProperty();
        background = new Pane();
        foreground = new Pane();
        background.prefWidthProperty().bind(this.widthProperty());
        track = new Circle();
        track.centerXProperty().bindBidirectional(foreground.prefWidthProperty());
        track.centerYProperty().bind(foreground.heightProperty().divide(2));
        getChildren().addAll(background, foreground, track);

        track.setOnMouseDragged(event -> {
            setProgress((int) (event.getX() / getWidth() * max));
            positionTrack(event.getX());
        });
        EventHandler<MouseEvent> hander = event -> {
            setProgress((int) (event.getX() / getWidth() * getMax()));
            positionTrack(event.getX());
        };
        background.setOnMouseClicked(hander);
        foreground.setOnMouseClicked(hander);
        this.setOnMouseClicked(hander);
    }

    private void positionTrack(double x) {
        if (x < 0)
            x = 0;
        if (x > getWidth())
            x = getWidth();
        track.setCenterX(x);
    }

    public void setBackgroundColor(Background background) {
        this.background.setBackground(background);
    }

    public void setForeground(Background foreground) {
        this.foreground.setBackground(foreground);
    }

    public void setThumbFill(Paint paint) {
        this.track.setFill(paint);
    }

    public void setThumbStroke(double strokeWidth, Paint color) {
        this.track.setStroke(color);
        this.track.setStrokeWidth(strokeWidth);
    }

    public void setTrackHeight(double height) {
        background.setPrefHeight(height);
        foreground.setPrefHeight(height);
    }

    public void setThumbSize(double size) {
        track.setRadius(size / 2);
    }

    public void setProgress(int progress) {
        if (progress < 0)
            progress = 0;
        if (progress > 100) {
            progress = 100;
        }
        this.mProgress.set(progress);
    }

    public IntegerProperty progressProperty() {
        return mProgress;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    public int getProgress() {
        return mProgress.get();
    }
}
