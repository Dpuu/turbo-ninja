package Mechanics.json;

import java.io.InputStream;

/**
 * Created by david on 23/04/2018.
 */

public class FileHandle {

    public static InputStream inputStreamFromFile(String path) {
        try {
            InputStream inputStream = FileHandle.class.getResourceAsStream(path);
            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
