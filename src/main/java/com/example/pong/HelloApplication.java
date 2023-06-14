package com.example.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.EventListener;
import java.util.Random;

public class HelloApplication extends Application {

    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private static final double MARGIN = 50;
    private static final double ARENAWIDTH = WIDTH - 2*MARGIN;
    private static final double ARENAHEIGHT = HEIGHT - 2*MARGIN;
    private static final double ARENAX1 = MARGIN;
    private static final double ARENAY1 = MARGIN;
    private static final double ARENAX2 = ARENAX1 + ARENAWIDTH;
    private static final double ARENAY2 = ARENAY1 + ARENAHEIGHT;
    private static final double R = 10;

    private static final int NUMBEROFBALLS = 15;
    private Ball[] balls = new Ball[NUMBEROFBALLS];


    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline t = new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));
        t.setCycleCount(Timeline.INDEFINITE);
        initBall();
        t.play();

        stage.setTitle("Balls!");
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
    }

    private void initBall() {
        Random rnd = new Random();
        for (int i = 0; i < NUMBEROFBALLS; i++) {
            if (i < NUMBEROFBALLS / 2) {
                balls[i] = new Rugby(
                        rnd.nextDouble() * ARENAWIDTH + ARENAX1,
                        rnd.nextDouble() * ARENAHEIGHT + ARENAY1,
                        5 + rnd.nextDouble() * 20,
                        5 + rnd.nextDouble() * 20,
                        20 + rnd.nextDouble() * 5,
                        8 + rnd.nextDouble() * 5,
                        10 + rnd.nextDouble() * 5,
                        Color.color(rnd.nextDouble(), rnd.nextDouble(), rnd.nextDouble())
                );
            } else {
                balls[i] = new Ball(
                        rnd.nextDouble() * ARENAWIDTH + ARENAX1,
                        rnd.nextDouble() * ARENAHEIGHT + ARENAY1,
                        5 + rnd.nextDouble() * 20,
                        5 + rnd.nextDouble() * 20,
                        8 + rnd.nextDouble() * 5,
                        Color.color(rnd.nextDouble(), rnd.nextDouble(), rnd.nextDouble())
                );
            }
        }
    }



    private void run(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
        gc.fillRect(ARENAX1, ARENAY1, ARENAWIDTH, ARENAHEIGHT);

        for (int i = 0; i < NUMBEROFBALLS; i++){
            balls[i].checkBoundaryCollisions(ARENAX1, ARENAY1,ARENAX2,ARENAY2);
            balls[i].update();
            balls[i].draw(gc);
        }
    }

    @FunctionalInterface
    public interface EventHandler<T extends Event> extends EventListener{
        void handle(T event);
    }

    public static void main(String[] args) {
        launch();
    }
}