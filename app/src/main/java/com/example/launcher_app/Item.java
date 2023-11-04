package com.example.launcher_app;

import android.graphics.drawable.Drawable;

public class Item {
    CharSequence label; //packageName
    CharSequence name; //appname
    Drawable icon;// appicon

    public CharSequence getLabel() {
        return label;
    }

    public void setLabel(CharSequence label) {
        this.label = label;
    }

    public CharSequence getName() {
        return name;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Item(CharSequence label, CharSequence name, Drawable icon) {
        this.label = label;
        this.name = name;
        this.icon = icon;
    }

}
