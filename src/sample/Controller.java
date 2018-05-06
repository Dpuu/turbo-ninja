package sample;

import Mechanics.Bratt;
import Mechanics.Shop;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URISyntaxException;

import static Mechanics.Bratt.addPace;


public class Controller {
    private static final double y0 = 500;
    @FXML
    Pane rot;
    @FXML
    private Label balance;
    @FXML
    private Label multiplier;
    @FXML
    private Label income;
    @FXML
    private Shop activeShop;
    private Timeline timeline;

    public Controller() throws URISyntaxException {
        updateLabels();
    }

    @FXML
    protected void click() {
        Image image;
        //Tries to create the animation that's supposed to happen
        Circle clip = new Circle(20);
        try {
            //Creates the image
            image = new Image("Mechanics/resources/Images/Bratt.jpg");
            final ImageView temp = new ImageView(image);

            //Makes the image a circle centered on the middle and the desired size
            clip.setTranslateX(temp.getLayoutX() + 20);
            clip.setTranslateY(temp.getLayoutY() + 20);
            temp.setClip(clip);
            temp.setFitHeight(40);
            temp.setPreserveRatio(true);
            rot.getChildren().add(temp);

            //Got to store it because it is relevant for the animation, it shall not jump from side to side
            final double xCoord = 80 + Math.random() * 180;
            final double speedF = 4 + (4 * Math.random());

            //Places the object in starting position
            temp.relocate(xCoord, y0);
            final int[] i = {0}; // Have to use finals in animation, workaround
            timeline = new Timeline(new KeyFrame(Duration.millis(87), (ActionEvent e) -> {
                i[0]++;
                temp.relocate(xCoord, y0 - (speedF * i[0])); //moves it up
                temp.setOpacity(8.0 / i[0]); //Fading
            }));
            timeline.setCycleCount(42);

            // Cleanup, otherwise there would be a million nodes
            timeline.setOnFinished((ActionEvent e) -> rot.getChildren().remove(temp));
            timeline.play();
        } catch (Exception e) { //Should never happen
            e.printStackTrace();
        }
        Bratt.click(); //Game mechanic related
        double bFactor = Bratt.getbFactor(); //Got to store it to prevent negative values if you're buying a lot while pace >= 1
        addPace(1);
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(1000), (ActionEvent e) -> {
            addPace(-1);
        }));
        timeline2.play();
    }

    @FXML
    public void updateLabels() {
        Timeline labels = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            balance.setText(String.format("%.1f", Bratt.getBratts()));
            income.setText(String.format("%.2f/s", Bratt.getIncome()));
            multiplier.setText(String.format("x%.3f", Bratt.getMultiplier()));
//            updateShops();
            Bratt.manageMultiplier();
        }));
        labels.setCycleCount(Timeline.INDEFINITE);
        labels.play();
    }

    private void updateShops() {
        activeShop.update();
    }
}
