package me.equinox.clickgui.panels;

import me.Equinox;
import me.equinox.settings.ModeSetting;
import me.equinox.utils.ColorUtil;
import me.equinox.utils.FontUtils;
import me.equinox.utils.RenderUtils;

public abstract class Panel {
    public final String name;
    public int x;
    public int y;
    public int x2;
    public int y2;

    public int yVisual;
    public int y2Visual;

    public Panel(String name, int x, int y, int x2, int y2) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void drawPanel(int mouseX, int mouseY, float button) {
//        if(((ModeSetting)Client.instance.moduleManager.getModuleByName("ClickGui").getSettingByName("style")).is("Tepno")) {

        RenderUtils.drawRect(x, yVisual, x2, y2Visual, ColorUtil.categoryRectColor(((ModeSetting) Equinox.instance.mm.getModuleByName("ClickGui").getSettingByName("style"))));

        FontUtils.drawTotalCenteredString(name, (x + x2) / 2, yVisual + FontUtils.getFontHeight(), ColorUtil.categoryTextColor(((ModeSetting)Equinox.instance.mm.getModuleByName("ClickGui").getSettingByName("style"))));
//        } else {
//            RenderUtils.drawRect(x, y, x2, y2, 0xff24221E);
//
//            FontUtils.drawTotalCenteredString(name, (x + x2) / 2, y + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT, 0xff736F6C);
//        }
    }

    public void buttonClicked(int mouseX, int mouseY, int button) {
    }

    public void released() {
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
    }
}
