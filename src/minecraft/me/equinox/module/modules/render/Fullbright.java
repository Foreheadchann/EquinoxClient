package me.equinox.module.modules.render;

import me.equinox.event.EventTarget;
import me.equinox.event.callable.EventUpdate;
import me.equinox.module.Category;
import me.equinox.module.Module;
import me.equinox.module.ModuleInfo;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Fullbright", description = "Makes your game's brightness over vanilla limit", key = Keyboard.KEY_G, category =  Category.RENDER)
public class Fullbright extends Module {
    private float oldValue;

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
