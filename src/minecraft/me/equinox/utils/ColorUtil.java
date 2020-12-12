package me.equinox.utils;

import me.equinox.settings.ModeSetting;

import java.awt.*;

public class ColorUtil {

    public static Color valueOf(int color) {
        float r = ((color >> 16) & 0xff) / 255.0f;
        float g = ((color >>  8) & 0xff) / 255.0f;
        float b = ((color      ) & 0xff) / 255.0f;
        float a = ((color >> 24) & 0xff) / 255.0f;
        return new Color(r, g, b, a);
    }

    public static int categoryRectColor(ModeSetting style) {
        return style.is("Tepno") ? 0xffd99c3b : 0xff24221E;
    }

    public static int categoryTextColor(ModeSetting style) {
        return style.is("Tepno") ? 0xff454545 : 0xff736F6C;
    }

    public static int moduleRectColor(ModeSetting style, boolean hovered, boolean toggled) {
        return style.is("Tepno") ? (hovered ? 0xff404040 : 0xff151515) : (toggled ? 0xff423C36 : 0xff37312E);
    }

    public static int moduleTextColor(ModeSetting style, boolean hovered, boolean toggled) {
        int finalInt = style.is("Tepno") ? Color.WHITE.getRGB() : 0xff736F6C;

        if(toggled) finalInt = style.is("Tepno") ? 0xffd99c3b : RenderUtils.intentRainbow();

        if(hovered) finalInt = style.is("Tepno") ? valueOf(finalInt).darker().getRGB() : RenderUtils.intentRainbow();

        return finalInt;
    }

    public static int settingColor(ModeSetting style, boolean hovered) {
        return style.is("Tepno") ? (hovered ? Color.WHITE.darker().getRGB() : Color.WHITE.getRGB()) : (hovered ? RenderUtils.intentRainbow() : 0xff736F6C);
    }

    public static int settingRectColor(ModeSetting style) {
        return style.is("Tepno") ? 0xff151515 : 0xff423C36;
    }

    public static int boolColor(ModeSetting style, boolean hovered, boolean toggled) {
        int finalInt = style.is("Tepno") ? Color.WHITE.getRGB() : 0xff736F6C;

        if(toggled) finalInt = style.is("Tepno") ? 0xffd99c3b : RenderUtils.intentRainbow();

        if(hovered) finalInt = style.is("Tepno") ? valueOf(finalInt).darker().getRGB() : finalInt;

        return finalInt;
    }
}
