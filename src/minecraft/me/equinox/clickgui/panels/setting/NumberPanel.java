package me.equinox.clickgui.panels.setting;

import me.Equinox;
import me.equinox.clickgui.panels.ModuleButton;
import me.equinox.module.Module;
import me.equinox.settings.ModeSetting;
import me.equinox.settings.NumberSetting;
import me.equinox.utils.ColorUtil;
import me.equinox.utils.FontUtils;
import me.equinox.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class NumberPanel extends SettingPanel {
    public boolean dragging;

    public NumberPanel(ModuleButton moduleButton, Module module, NumberSetting setting, int x, int y, int x2, int y2) {
        super(moduleButton, module, setting, x, y, x2, y2);
    }

    @Override
    public void drawPanel(int mouseX, int mouseY, float button) {
        ModeSetting style = ((ModeSetting) Equinox.instance.mm.getModuleByName("ClickGui").getSettingByName("style"));

        double diff = ((NumberSetting) setting).maximum - ((NumberSetting) setting).minimum;

        double percentWidth = (((NumberSetting) setting).get() - ((NumberSetting) setting).minimum)/(((NumberSetting) setting).maximum - ((NumberSetting) setting).minimum);

        RenderUtils.drawRect(moduleButton.catPanel.x2 + 1, (moduleButton.catPanel.y2Visual + moduleButton.y) + y, moduleButton.catPanel.x2 + 101, (moduleButton.catPanel.y2Visual + moduleButton.y) + y2, ColorUtil.settingRectColor(style));
        RenderUtils.drawRect(moduleButton.catPanel.x2 + 1, (moduleButton.catPanel.y2Visual + moduleButton.y) + y, (int) ((moduleButton.catPanel.x2 + 1) + (percentWidth * 100)), (moduleButton.catPanel.y2Visual + moduleButton.y) + y2, style.is("Tepno") ? 0xff404040 : ColorUtil.valueOf(0xff423C36).brighter().getRGB());

        if(dragging) {
            double val = ((NumberSetting) setting).minimum + (MathHelper.clamp_double((double)(mouseX - (moduleButton.catPanel.x2 + 1)) / 100, 0, 1)) * diff;
            ((NumberSetting) setting).setValue(val);
        }

        FontUtils.drawString(setting.name + ": " + Math.round(((NumberSetting) setting).get() * 100D)/ 100D, moduleButton.catPanel.x2 + 2, ((moduleButton.catPanel.y2Visual + moduleButton.y) + y) + (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2), ColorUtil.settingColor(style, isHovered(mouseX, mouseY)));
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= moduleButton.catPanel.x2 + 1 && mouseX <= moduleButton.catPanel.x2 + 101 && mouseY >= (moduleButton.catPanel.y2Visual + moduleButton.y) + y && mouseY <= (moduleButton.catPanel.y2Visual + moduleButton.y) + y2;
    }

    @Override
    public void buttonClick(int button) {
        if(button == 0) {
            dragging = true;
        }
    }

    @Override
    public void released() {
        dragging = false;
    }
}
