package Mechanics;

import Mechanics.json.JSONUtils;
import javafx.beans.NamedArg;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

/**
 * Created by david on 20/04/2018.
 */
public class Shop extends VBox {
    private final StringProperty shopname;
    private Item[] items;

    //Behöver använda @namedarg för att kunna skapa shops i FXML
    //Med de labels som vi har så kan vi nu skapa instanser av shop som tar vilken av parametrarna från shop som ska
    //Användas

    public Shop(@NamedArg("ShopID") String shopname) {
        super();
        this.shopname = new SimpleStringProperty(this, "shopID", shopname);

        JSONObject obj = JSONUtils.getJSONObjectFromFile("/Mechanics/resources/Shop.json");
        JSONObject a;
        int len = obj.getJSONArray(shopname).length();
        items = new Item[len];
        for (int j = 0; j < len; j++) {
            a = obj.getJSONArray(shopname).getJSONObject(j);
            items[j] = new Item(a.getInt("c0"), a.getString("name"), "resources/Images/" + a.getString("filename"), a.getDouble("multiplier"));
        }
        this.getChildren().addAll(items);
        return;
    }

    public void update() {
        for (Item i : items) {
            i.affordable();
        }
    }

//    public void add(VBox b) {
//        b.getChildren().addAll(items);
//    }
//
//    public Pane[] getContent() {
//        return items;
//    }
//
//    public Item[] getItems() {
//        return items;
//    }

//    public String getFilename() {
//        return filename;
//    }
}
/*"Mattelektion": {
        "c0": 100,
                "name": "Mattelektion",
                "filename": "ML.jpg",
                "multiplier": .1
                },


        [100, "Mattelektion", "ML.jpg", .1],
       [150, "Matteprov", "Mathstest.jpg", .2],
       [250, "Fysiklektion", "FL.jpg", .25],
       [500, "Fysikprov", "FP.jpg", .33],
       [1500, "TisdagsProgrammering", "TP.jpg", .4],
       [3200, "FredagsProgrammering", "FPR.jpg", .5],
       [6800, "Minirobot", "MR.jpg", .56],
       [12500, "Komplexa frågor", "CQ.jpg", .63],
       [22200, "Ironiska svar", "SB.png", .72],
       [500000, "Drönare", "DR.jpg", .79],
       [10000000, "Undervattensrugby", "UR.jpg", 2]*/