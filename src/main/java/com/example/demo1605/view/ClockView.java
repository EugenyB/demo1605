package com.example.demo1605.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.time.LocalTime;
import java.time.ZoneId;

public class ClockView {
    private double size;
    private double cx;
    private double cy;

    private int position;

    public ClockView() {
        size = 100;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.WHITE);
        gc.strokeOval(cx-size/2, cy-size/2, size, size);
        drawHands(gc);
    }

    private void drawHands(GraphicsContext gc) {
        LocalTime time = LocalTime.now(ZoneId.of("Europe/Kiev"));
        int hour = time.getHour() % 12;
        int minute = time.getMinute();
        int second = time.getSecond();

        double hR = size / 4;
        double hx = cx + hR * Math.sin(Math.toRadians(hour * 30 + minute * 0.5));
        double hy = cy - hR * Math.cos(Math.toRadians(hour * 30 + minute * 0.5));

        double mR = size / 3;
        double mx = cx + mR * Math.sin(Math.toRadians(minute * 6));
        double my = cy - mR * Math.cos(Math.toRadians(minute * 6));

        double sR = size / 2;
        double sx = cx + sR * Math.sin(Math.toRadians(second * 6));
        double sy = cy - sR * Math.cos(Math.toRadians(second * 6));

        gc.setLineWidth(6);
        gc.setStroke(Color.BLUE);
        gc.strokeLine(cx, cy, hx, hy);

        gc.setLineWidth(3);
        gc.setStroke(Color.RED);
        gc.strokeLine(cx, cy, mx, my);

        gc.setLineWidth(1);
        gc.setStroke(Color.GREEN);
        gc.strokeLine(cx, cy, sx, sy);

        drawMarker(gc);
    }

    public void drawMarker(GraphicsContext gc) {
        double x = cx + size/2 * Math.sin(position*Math.PI/6);
        double y = cy - size/2 * Math.cos(position*Math.PI/6);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(x-5, y-5, 10, 10);
    }

    public double getCx() {
        return cx;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }

    public double getCy() {
        return cy;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }

    public int getPosition() {
        return position;
    }

    public void move(int dx) {
        position += dx;
    }
}
