package Mechanics;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.net.URISyntaxException;

public class Bratt {
    private static double bratts;
    private static double passiveIncome;
    private static double activeIncome;
    private static double pace;
    private static double bFactor = 1;
    private static double income = 0;
    private static double multiplier = 1;
    private static boolean current = false, last = false;
    private static Timeline multiplierLine;


    public Bratt() throws URISyntaxException {
        bratts = 1E12;
    }

    public static double getBratts() {
        return bratts;
    }

    public static double getIncome() {
        return income;
    }

    public static double getMultiplier() {
        return multiplier;
    }

    public static void withdraw(double amount) {
        if (amount > 0) {
            bratts -= amount;
        }
    }

    public static void addPace(double amount) {
        pace += amount;
        activeIncome = pace * bFactor;
        income = (activeIncome + passiveIncome) * multiplier;
        income = Math.abs(income);
    }

    public static void click() {
        bratts += 1 * bFactor * multiplier;
    }

    public static double getbFactor() {
        return bFactor;
    }

    public static void increseBFactor(double amount) {
        bFactor += Math.abs(amount);
    }

    public static void manageMultiplier() {
        current = pace >= 5;
        if (pace >= 5 && last != current) {
            last = true;
            try {
                multiplierLine.stop();
            } catch (Exception e) {
            } //Måste stoppa för att det inte ska bli problem med dubblicerane timelines o BS
            multiplierLine = new Timeline(new KeyFrame(Duration.seconds(1), a -> multiplier += .01));
            multiplierLine.setCycleCount(Timeline.INDEFINITE);
            multiplierLine.play();
        } else if (current != last) {
            last = false;
            multiplierLine.stop();
            multiplierLine = new Timeline(new KeyFrame(Duration.millis(500), e -> multiplier -= .1));
            multiplierLine.setCycleCount((int) ((multiplier - 1) / 0.1));
            multiplierLine.setOnFinished(e -> {
                multiplierLine = new Timeline(new KeyFrame(Duration.seconds(.1), a -> multiplier += .01));
                multiplier = 1;
            });
            multiplierLine.play();
        }
    }


    public static double getPace() {
        return pace;
    }

    public static void setPace(double pace) {
        Bratt.pace = pace;
    }
}
