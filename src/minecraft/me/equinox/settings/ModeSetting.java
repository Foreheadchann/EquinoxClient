package me.equinox.settings;

import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting {

    public int index;
    public List<String> modes;

    public ModeSetting(String name, String defVal, String... modes) {
        this.name = name;
        this.modes = Arrays.asList(modes);
        index = this.modes.indexOf(defVal);
    }

    public String get() {
        return modes.get(index);
    }

    public boolean is(String mode) {
        return index == modes.indexOf(mode);
    }

    public void nextMode() {
        if(index < modes.size() - 1) index++; else index = 0;
    }

    public void prevMode() {
        if(index > 0) index--; else index = modes.size() - 1;
    }

    public void setVal(String mode) {
        index = modes.indexOf(mode);
    }

    public List<String> getSettings() {
        return modes;
    }
}