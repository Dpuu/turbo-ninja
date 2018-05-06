package Mechanics;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URISyntaxException;

/**
 * Created by david on 20/04/2018.
 */
public class Item extends Pane {
    private int cost;
    private double costfactor;
    private int amount;
    private Image img;
    private ImageView iv;
    Label lblAmount, lblCost, lblName;
    private ColorAdjust adjust;
    private boolean enabled;
    private double multiplier;

    public Item(int c0, String name, String filename, double multiplier) {
        adjust = new ColorAdjust();
        adjust.setSaturation(-1);
        this.cost = c0;
        this.amount = 0;
        this.multiplier = multiplier;
        this.costfactor = 1 + (c0 / 1000.0);
        Color color = Color.CRIMSON;
        try {
            img = new Image(getClass().getResource(filename).toURI().toString());
        } catch (URISyntaxException e) {
        }
        iv = new ImageView(img);
        iv.setFitWidth(400);
        iv.setFitHeight(100);
        iv.setPreserveRatio(false);
        iv.setOnMouseClicked(e -> {
            try {
                buy();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        });
        iv.setEffect(adjust);
        lblName = new Label(name);
        lblAmount = new Label();
        lblCost = new Label();
        lblCost.setPrefWidth(200);
        lblAmount.setPrefWidth(200);
        lblAmount.setAlignment(Pos.CENTER_LEFT);
        lblCost.setAlignment(Pos.CENTER_RIGHT);
        lblAmount.setText("Antal:\t" + Integer.toString(amount));
        lblCost.setText("Pris:\t" + Integer.toString(cost));
        lblName.setTextFill(color);
        lblAmount.setTextFill(color);
        lblCost.setTextFill(color);

        this.getChildren().addAll(iv, lblName, lblCost, lblAmount);
        lblAmount.relocate(0, 80);
        lblCost.relocate(180, 80);
    }

    public void affordable() {
        if (Bratt.getBratts() > cost) {
            enabled = true;
            adjust.setSaturation(0);
        } else {
            enabled = false;
            adjust.setSaturation(-1);
        }
    }

    private void buy() throws URISyntaxException {
        if (enabled) {
            amount++;
            Bratt.withdraw(cost);
            cost *= costfactor;
            lblAmount.setText("Antal:\t" + Integer.toString(amount));
            lblCost.setText("Pris:\t" + Integer.toString(cost));
            Bratt.increseBFactor(multiplier);
        }
    }

}
