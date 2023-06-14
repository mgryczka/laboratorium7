package com.example.pong;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball {
    private double radius;
    protected double xSpeed;
    protected double ySpeed;
    protected double xPos;
    protected double yPos;
    protected Color color;

    Ball(double xPos, double yPos, double xSpeed, double ySpeed, double radius, Color color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.radius = radius;
        this.color = color;
    }


    public void checkBoundaryCollisions(double xLeft, double yTop, double xRight, double yBottom){
        if ((xPos - radius <= xLeft) || (xPos + radius >= xRight)) xSpeed = -xSpeed;
        if ((yPos - radius <= yTop) || (yPos + radius >= yBottom)) ySpeed = -ySpeed;
    }
    public void draw(GraphicsContext gc){
        gc.setFill(color);
        gc.fillOval(xPos - radius, yPos -radius, 2 * radius, 2*radius);
    }

    public  void  update(){
        xPos += xSpeed;
        yPos += ySpeed;
    }
}

