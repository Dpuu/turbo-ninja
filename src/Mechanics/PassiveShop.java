package Mechanics;

public class PassiveShop extends Shop {
    public PassiveShop() {
        super("PassiveShop");
        String shopname = "PassiveShop";
        int len = obj.getJSONArray(shopname).length();
        for (int j = 0; j < len; j++) {
            a = obj.getJSONArray(shopname).getJSONObject(j);
            items[j] = new PassiveItem(a.getInt("c0"), a.getString("name"), "resources/Images/" + a.getString("filename"), a.getDouble("income"));
        }
        this.getChildren().addAll(items);
    }
}
