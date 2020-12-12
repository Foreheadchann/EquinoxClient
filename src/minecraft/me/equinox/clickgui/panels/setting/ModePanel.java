package me.equinox.clickgui.panels.setting;

import me.Equinox;
import me.equinox.clickgui.panels.ModuleButton;
import me.equinox.module.Module;
import me.equinox.settings.ModeSetting;
import me.equinox.utils.ColorUtil;
import me.equinox.utils.FontUtils;
import me.equinox.utils.RenderUtils;
import net.minecraft.client.Minecraft;

public class ModePanel extends SettingPanel{
    public ModePanel(ModuleButton moduleButton, Module module, ModeSetting setting, int x, int y, int x2, int y2) {
        super(moduleButton, module, setting, x, y, x2, y2);
    }

    @Override
    public void drawPanel(int mouseX, int mouseY, float button) {
        ModeSetting style = ((ModeSetting) Equinox.instance.mm.getModuleByName("ClickGui").getSettingByName("style"));

        RenderUtils.drawRect(moduleButton.catPanel.x2 + 1, (moduleButton.catPanel.y2Visual + moduleButton.y) + y, moduleButton.catPanel.x2 + 101, (moduleButton.catPanel.y2Visual + moduleButton.y) + y2, ColorUtil.settingRectColor(style));

        FontUtils.drawString(setting.name + " " + ((ModeSetting)setting).get(), moduleButton.catPanel.x2 + 2, ((moduleButton.catPanel.y2Visual + moduleButton.y) + y) + (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2), ColorUtil.settingColor(style, isHovered(mouseX, mouseY)));
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= moduleButton.catPanel.x2 + 1 && mouseX <= moduleButton.catPanel.x2 + 101 && mouseY > (moduleButton.catPanel.y2Visual + moduleButton.y) + y && mouseY <= (moduleButton.catPanel.y2Visual + moduleButton.y) + y2;
    }

    @Override
    public void buttonClick(int button) {
        if(button == 0) ((ModeSetting)setting).nextMode();
    }
}
