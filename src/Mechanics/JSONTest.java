package Mechanics;

import Mechanics.json.JSONUtils;
import org.json.JSONObject;

/**
 * Created by david on 23/04/2018.
 */
public class JSONTest {
    public static void main(String[] args) {
        JSONObject obj = JSONUtils.getJSONObjectFromFile("/Mechanics/resources/Shop1.json");
        String[] names = JSONObject.getNames(obj);
        for (String name : names) {
            System.out.printf("%s\t%s\n", name, obj.get(name));
        }
    }
}
