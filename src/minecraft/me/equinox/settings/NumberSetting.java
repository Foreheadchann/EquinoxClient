package me.equinox.settings;

public class NumberSetting extends Setting {

    public double value, minimum, maximum, increment;

    public NumberSetting(String name, double value, double minimum, double maximum, double increment) {
        this.value = value;
        this.minimum = minimum;
        this.maximum = maximum;
        this.increment = increment;
        this.name = name;
    }

    public double get() {
        return value;
    }

    public void setValue(double value) {
        double precision = 1 / increment;
        this.value = Math.round(Math.max(minimum, Math.min(maximum, value)) * precision) / precision;
    }
}