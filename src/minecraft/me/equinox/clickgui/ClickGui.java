package me.equinox.clickgui;

import me.equinox.clickgui.panels.CategoryPanel;
import me.equinox.clickgui.panels.ModuleButton;
import me.equinox.clickgui.panels.Panel;
import me.equinox.clickgui.panels.setting.SettingPanel;
import me.equinox.module.Category;
import me.equinox.utils.FontUtils;
import me.equinox.utils.MathUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.io.IOException;
import java.util.ArrayList;

public class ClickGui extends GuiScreen {
    private final ArrayList<Panel> panels = new ArrayList<>();

    public ClickGui() {
        FontUtils.setupFontUtils();

        int cat = 15;
        for(Category category : Category.values()) {
            panels.add(new CategoryPanel(String.valueOf(category), cat, 0, cat + 100, 15, category));
            cat += 115;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(mc);

        super.drawScreen(mouseX, mouseY, partialTicks);

        for(Panel panel : panels) {
            if(panel instanceof CategoryPanel) {
                if(!((CategoryPanel) panel).isDragged) {
                    panel.yVisual = (int) MathUtils.lerp(panel.yVisual, panel.y, 0.1f);
                    panel.y2Visual = (int) MathUtils.lerp(panel.y2Visual, panel.y2, 0.1f);
                } else {
                    panel.yVisual = panel.y;
                    panel.y2Visual = panel.y2;
                }
            }

            panel.drawPanel(mouseX, mouseY, partialTicks);

            for (ModuleButton modBut : ((CategoryPanel) panel).modules) {
                if(modBut.extended) {
                    for(SettingPanel setPan : modBut.settings) {
                        setPan.drawPanel(mouseX, mouseY, partialTicks);
                    }
                }
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        for(Panel panel : panels) {
            if(panel.isHovered(mouseX, mouseY)) {
                panel.buttonClicked(mouseX, mouseY, mouseButton);
                break;
            }
            if(panel instanceof CategoryPanel && ((CategoryPanel) panel).extended) {
                for (ModuleButton modBut : ((CategoryPanel) panel).modules) {
                    if(modBut.isHovered(mouseX, mouseY)) {
                        modBut.buttonClicked(mouseX, mouseY, mouseButton);
                        break;
                    }
                    if(modBut.extended) {
                        for(SettingPanel setPan : modBut.settings) {
                            if(setPan.isHovered(mouseX, mouseY)) setPan.buttonClick(mouseButton);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        for(Panel panel : panels) {
            panel.released();

            for (ModuleButton modBut : ((CategoryPanel) panel).modules) {
                if(modBut.extended) {
                    for(SettingPanel setPan : modBut.settings) {
                        setPan.released();
                    }
                }
            }
        }
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        final ScaledResolution sr = new ScaledResolution(mc);

        for(Panel panel : panels) {
            panel.yVisual = sr.getScaledHeight();
            panel.y2Visual = sr.getScaledHeight() + 15;
        }
    }
}
