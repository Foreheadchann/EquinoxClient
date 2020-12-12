package me.equinox.settings;

public class BooleanSetting extends Setting {
    public boolean enabled;

    public BooleanSetting(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public boolean get() {
        return enabled;
    }

    public void set(Boolean val) {
        enabled = val;
    }
}