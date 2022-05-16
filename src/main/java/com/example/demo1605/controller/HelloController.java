package com.example.demo1605.controller;

import com.example.demo1605.view.ClockView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class HelloController {
    @FXML private Canvas canvas;
    @FXML private Pane pane;

    private ClockView clockView;

    int position = 0;

    public void initialize() {
        clockView = new ClockView();
        canvas.heightProperty().bind(pane.heightProperty());
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.widthProperty().addListener(e->draw());
        canvas.heightProperty().addListener(e->draw());

//        new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                draw();
//            }
//        }.start();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1000), e -> processTick())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        pane.requestFocus();
    }

    private void processTick() {
        // some actions
        draw();
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.DARKGRAY);
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        gc.fillRect(0, 0, w, h);
        clockView.setSize(Math.min(w, h) - 50);
        clockView.setCx(w/2);
        clockView.setCy(h/2);
        clockView.draw(gc);
    }

    public void processKey(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.LEFT) {
            clockView.move(-1);
        } else if (keyEvent.getCode() == KeyCode.RIGHT) {
            clockView.move(1);
        }
        draw();
    }
}