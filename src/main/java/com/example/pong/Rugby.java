package com.example.pong;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class Rugby extends Ball {

    private double rX;
    private double rY;
    private double omega;
    private double rot;
    public Rugby(double xPos, double yPos, double xSpeed, double ySpeed, double omega, double rX, double rY) {
        super(xPos, yPos, xSpeed, ySpeed, rX, Color.RED); // Assuming you want to set the color to red
        this.rX = rX;
        this.rY = rY;
        this.omega = omega;
    }

    public Rugby(double xPos, double yPos, double xSpeed, double ySpeed, double omega, double rX, double rY, Color color) {
        this(xPos, yPos, xSpeed, ySpeed, omega, rX, rY);
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        gc.setFill(color);
        gc.transform(new Affine(new Rotate(rot,xPos,yPos)));
        gc.fillOval(xPos - rX, yPos - rY, 2 * rX, 2 * rY);
        gc.restore();
    }

    public void update(){
        super.update();
        rot += omega;
    }
}
