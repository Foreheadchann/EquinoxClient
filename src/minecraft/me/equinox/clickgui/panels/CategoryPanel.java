package me.equinox.clickgui.panels;

import me.Equinox;
import me.equinox.module.Category;
import me.equinox.module.Module;

import java.util.ArrayList;

public class CategoryPanel extends Panel {
    private final Category category;
    public boolean isDragged, extended;
    public ArrayList<ModuleButton> modules = new ArrayList<>();
    public int mouseeX;
    public int mouseeY;

    public CategoryPanel(String name, int x, int y, int x2, int y2, Category category) {
        super(name, x, y, x2, y2);
        this.category = category;

        int moduleNum = 0;

        for(Module module : Equinox.instance.mm.getModules()) {
            if(module.getCategory() == category) {
                modules.add(new ModuleButton(module.getName(), x, (15 * moduleNum), x2, (15 * (moduleNum + 1)), module, this));
                moduleNum++;
            }
        }
    }

    @Override
    public void drawPanel(int mouseX, int mouseY, float button) {

        super.drawPanel(mouseX, mouseY, button);

        if(extended) {
            for (ModuleButton modBut : modules) {
                modBut.drawPanel(mouseX, mouseY, button);
            }
        }

        if(isDragged) {
            this.x = mouseeX + mouseX;
            this.y = mouseeY + mouseY;
            this.x2 = x + 100;
            this.y2 = y + 15;
        }
    }

    @Override
    public void buttonClicked(int mouseX, int mouseY, int button) {
        if(button == 1) {
            extended = !extended;
        } else {
            mouseeX = x - mouseX;
            mouseeY = y - mouseY;
            isDragged = true;
        }
    }

    @Override
    public void released() {
        isDragged = false;
    }
}
