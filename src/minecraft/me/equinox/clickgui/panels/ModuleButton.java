package me.equinox.clickgui.panels;

import me.Equinox;
import me.equinox.clickgui.panels.setting.BooleanPanel;
import me.equinox.clickgui.panels.setting.ModePanel;
import me.equinox.clickgui.panels.setting.NumberPanel;
import me.equinox.clickgui.panels.setting.SettingPanel;
import me.equinox.module.Module;
import me.equinox.settings.BooleanSetting;
import me.equinox.settings.ModeSetting;
import me.equinox.settings.NumberSetting;
import me.equinox.settings.Setting;
import me.equinox.utils.ColorUtil;
import me.equinox.utils.FontUtils;
import me.equinox.utils.RenderUtils;

import java.awt.*;
import java.util.ArrayList;

public class ModuleButton extends Panel{
    public Module module;
    public CategoryPanel catPanel;
    public ArrayList<SettingPanel> settings = new ArrayList<>();
    public boolean extended;

    public ModuleButton(String name, int x, int y, int x2, int y2, Module module, CategoryPanel panel) {
        super(name, x, y, x2, y2);
        this.module = module;
        catPanel = panel;

        int settingNum = 0;
        for(Setting setting : module.settings) {
            if(setting instanceof BooleanSetting) {
                settings.add(new BooleanPanel(this, module, (BooleanSetting)setting, catPanel.x2 + 1, (15 * settingNum), catPanel.x2 + 100, (15 * (settingNum + 1))));
            } else if(setting instanceof ModeSetting) {
                settings.add(new ModePanel(this, module, (ModeSetting) setting, catPanel.x2 + 1, (15 * settingNum), catPanel.x2 + 100, (15 * (settingNum + 1))));
            } else if(setting instanceof NumberSetting) {
                settings.add(new NumberPanel(this, module, (NumberSetting) setting, catPanel.x2 + 1, (15 * settingNum), catPanel.x2 + 100, (15 * (settingNum + 1))));
            }
            settingNum++;
        }
    }

    @Override
    public void drawPanel(int mouseX, int mouseY, float button) {
        x = catPanel.x;
        x2 = catPanel.x2;

        ModeSetting style = ((ModeSetting) Equinox.instance.mm.getModuleByName("ClickGui").getSettingByName("style"));

        RenderUtils.drawRect(x, catPanel.y2Visual + y, x2, catPanel.y2Visual + y2, ColorUtil.moduleRectColor(style, isHovered(mouseX, mouseY), module.isToggled()));

        FontUtils.drawString("> " + name, catPanel.x + 3, (catPanel.y2Visual + y) + FontUtils.getFontHeight() / 2, ColorUtil.moduleTextColor(style, isHovered(mouseX, mouseY), module.isToggled()));

        if(isHovered(mouseX, mouseY)) {
            RenderUtils.drawRect(mouseX, mouseY, mouseX + FontUtils.getStringWidth(module.getDescription()), mouseY + FontUtils.getFontHeight(), 0xff151515);
            FontUtils.drawString(module.getDescription(), mouseX, mouseY, Color.WHITE.getRGB());
        }
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= catPanel.x && mouseX <= catPanel.x2 && mouseY >= catPanel.y2 + y && mouseY < catPanel.y2 + y2;
    }

    @Override
    public void buttonClicked(int mouseX, int mouseY, int button) {
        if(button == 0) module.toggle(); else extended = !extended;
    }
}
