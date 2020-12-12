package me.equinox.module.modules.render;

import me.equinox.event.EventTarget;
import me.equinox.event.callable.EventUpdate;
import me.equinox.module.Category;
import me.equinox.module.Module;
import org.lwjgl.input.Keyboard;

public class Fullbright extends Module {
    private float oldValue;

    public Fullbright() {
        super("Fullbright", "Makes your game's brightness over vanilla limit", Keyboard.KEY_G, Category.RENDER);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        mc.gameSettings.gammaSetting = 10;
    }

    @Override
    public void onEnable() {
        super.onEnable();

        oldValue = mc.gameSettings.gammaSetting;
    }

    @Override
    public void onDisable() {
        super.onDisable();

        mc.gameSettings.gammaSetting = oldValue;
    }
}
