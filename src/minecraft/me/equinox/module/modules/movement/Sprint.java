package me.equinox.module.modules.movement;

import me.equinox.event.EventTarget;
import me.equinox.event.callable.EventUpdate;
import me.equinox.module.Category;
import me.equinox.module.Module;
import me.equinox.module.ModuleInfo;
import me.equinox.utils.MovementUtils;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Sprint", description = "Makes you automatically sprint", key = Keyboard.KEY_M, category =  Category.MOVEMENT)
public class Sprint extends Module {
    @EventTarget
    public void onUpdate(EventUpdate event) {
        if(MovementUtils.isAllowedToSprint())
            mc.thePlayer.setSprinting(true);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.thePlayer.setSprinting(true);
    }
}
