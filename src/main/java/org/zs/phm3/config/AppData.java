package org.zs.phm3.config;

import java.util.HashMap;
import java.util.Map;

public class AppData {
    public static Map< String, String > URLs = new HashMap< >(); // GLOBAL VARIABLE

    public static Map<String, String> getURLs() {
        return URLs;
    }

    public static void setURLs(Map<String, String> URLs) {
        AppData.URLs = URLs;
    }
}
