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
        if (shopname.equals("ActiveShop")) {
            for (int j = 0; j < len; j++) {
                a = obj.getJSONArray(shopname).getJSONObject(j);
                items[j] = new Item(a.getInt("c0"), a.getString("name"), "resources/Images/" + a.getString("filename"), a.getDouble("multiplier"));
            }
        } else if (shopname.equals("PassiveShop")) {
            for (int j = 0; j < len; j++) {
                a = obj.getJSONArray(shopname).getJSONObject(j);
                items[j] = new Item(a.getInt("c0"), a.getString("name"), "resources/Images/" + a.getString("filename"), a.getDouble("income"));
            }
        }
        this.getChildren().addAll(items);
        return;
    }

    public void update() {
        for (Item i : items) {
            i.affordable();
        }
    }
}
