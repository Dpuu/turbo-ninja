package Mechanics.json;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by david on 23/04/2018.
 */
public class JSONUtils {

    public static String getJSONStringFromFile(String path) {
        Scanner scanner;
        InputStream in = FileHandle.inputStreamFromFile(path);
        scanner = new Scanner(in);
        String json = scanner.useDelimiter("\\Z").next();
        scanner.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static JSONObject getJSONObjectFromFile(String path) {
        return new JSONObject(getJSONStringFromFile(path));
    }

    public static boolean objectExists(JSONObject jsonObject, String key) {
        Object o;
        try {
            o = jsonObject.get(key);
            return o != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
