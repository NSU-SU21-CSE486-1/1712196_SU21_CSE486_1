package com.istiaksaif.hellosharedprefs;

import android.app.Application;

public class userSetting extends Application {
    public static final String PREF = "preference";
    public static final String Custom_Theme = "customTheme";
    public static final String Light_Theme = "lightTheme";
    public static final String Dark_Theme = "darkTheme";

    private String customTheme;

    public String getCustomTheme() {
        return customTheme;
    }

    public void setCustomTheme(String customTheme) {
        this.customTheme = customTheme;
    }
}
