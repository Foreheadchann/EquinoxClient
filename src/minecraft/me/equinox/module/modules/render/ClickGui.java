package me.equinox.module.modules.render;

import me.Equinox;
import me.equinox.module.Category;
import me.equinox.module.Module;
import me.equinox.module.ModuleInfo;
import me.equinox.settings.ModeSetting;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "ClickGui", description = "The fucking clickgui dumbass", key = Keyboard.KEY_RSHIFT, category = Category.RENDER)

public class ClickGui extends Module {
    private final ModeSetting style = new ModeSetting("Style", "Tepno", "Tepno", "Rise");

    @Override
    public void onEnable() {
        mc.displayGuiScreen(Equinox.instance.clickGui);
        toggle();
    }
}
