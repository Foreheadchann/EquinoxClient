package me.equinox.module.modules.movement;

import me.equinox.event.EventTarget;
import me.equinox.event.callable.EventUpdate;
import me.equinox.module.Category;
import me.equinox.module.Module;
import me.equinox.module.ModuleInfo;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Fly", description = "Makes you float in the air one way or another", key = Keyboard.KEY_F, category =  Category.MOVEMENT)
public class Fly extends Module {

    @EventTarget
    public void onUpdate(EventUpdate event) {
        mc.thePlayer.motionY = 0;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.thePlayer.motionX = 0;
        mc.thePlayer.motionY = 0;
        mc.thePlayer.motionZ = 0;
    }
}
