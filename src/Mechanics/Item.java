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
abstract class Item extends Pane {
    int cost;
    double costfactor;
    int amount;
    ImageView iv;
    Label lblAmount, lblCost, lblName;
    boolean enabled;
    private Image img;
    private ColorAdjust adjust;

    public Item(int c0, String name, String filename) {
        //Image with adjusts makes the foundation
        adjust = new ColorAdjust();
        adjust.setSaturation(-1);
        try {
            img = new Image(getClass().getResource(filename).toURI().toString());
        } catch (Exception e) {
            System.out.println(filename);
            System.out.println(e.getCause());
        }
        iv = new ImageView(img);
        iv.setFitWidth(380);
        iv.setFitHeight(100);
        iv.setPreserveRatio(false);
        iv.setEffect(adjust);
        //Class member values
        this.cost = c0;
        this.amount = 0;
        this.costfactor = 1 + (c0 / 1000.0);
        Color color = Color.CRIMSON;

        //Minor details showing the data aso...
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

    void buy() throws URISyntaxException {
        affordable();
        if (enabled) {
            amount++;
            Bratt.withdraw(cost);
            cost *= costfactor;
            lblAmount.setText("Antal:\t" + Integer.toString(amount));
            lblCost.setText("Pris:\t" + Integer.toString(cost));
        }
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


}

class ActiveItem extends Item {
    private double multiplier;

    public ActiveItem(int c0, String name, String filename, double multiplier) {
        super(c0, name, filename);
        this.multiplier = multiplier;
        iv.setOnMouseClicked(e -> {
            try {
                buy();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        });
    }

    void buy() throws URISyntaxException {
        super.buy();
        if (enabled) Bratt.increseBFactor(multiplier);
    }
}

class PassiveItem extends Item {
    double income;

    public PassiveItem(int c0, String name, String filename, double income) {
        super(c0, name, filename);
        this.income = income;
        iv.setOnMouseClicked(e -> {
            try {
                buy();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        });
    }

    void buy() throws URISyntaxException {
        super.buy();
        if (enabled) Bratt.addPassive(income);
    }
}
