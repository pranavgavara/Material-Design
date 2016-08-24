package com.pranav.materialdesigncardview;

/**
 * Created by Pranav on 8/23/2016.
 */
public class Single_Row {
    int iconID;
    String title;

    public Single_Row(int iconID, String title) {
        this.iconID = iconID;
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getIconID() {
        return iconID;
    }
    public void setIconID(int iconID) {
        this.iconID = iconID;
    }
}
