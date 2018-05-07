package Mechanics;

public class ActiveShop extends Shop {
    public ActiveShop() {
        super("ActiveShop");
        String shopname = "ActiveShop";

        int len = obj.getJSONArray(shopname).length();
        for (int j = 0; j < len; j++) {
            a = obj.getJSONArray(shopname).getJSONObject(j);
            items[j] = new ActiveItem(a.getInt("c0"), a.getString("name"), "resources/Images/" + a.getString("filename"), a.getDouble("multiplier"));
        }
        this.getChildren().addAll(items);
    }
}
