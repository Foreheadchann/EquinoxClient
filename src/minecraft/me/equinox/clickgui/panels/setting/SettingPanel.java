package me.equinox.clickgui.panels.setting;

import me.equinox.clickgui.panels.ModuleButton;
import me.equinox.module.Module;
import me.equinox.settings.Setting;

public abstract class SettingPanel {
    public Module module;
    public Setting setting;
    public ModuleButton moduleButton;

    public int x, y, x2, y2;

    public SettingPanel(ModuleButton moduleButton, Module module, Setting setting, int x, int y, int x2, int y2) {
        this.module = module;
        this.setting = setting;
        this.moduleButton = moduleButton;

        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void drawPanel(int mouseX, int mouseY, float button) {
    }

    public void buttonClick(int button) {
    }

    public void released() {
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
    }
}
