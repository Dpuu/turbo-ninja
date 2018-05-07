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
public abstract class Shop extends VBox {
    final JSONObject obj = JSONUtils.getJSONObjectFromFile("/Mechanics/resources/Shop.json");
    private final StringProperty shopname;
    JSONObject a;
    Item[] items;
    //TODO FIX THE PROBLEM WITH OVERBUYING
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
    }

    public void update() {
        for (Item i : items) {
            i.affordable();
        }
    }
}

