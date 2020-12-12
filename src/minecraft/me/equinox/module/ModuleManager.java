package me.equinox.module;

import me.Equinox;
import me.equinox.module.modules.movement.Fly;
import me.equinox.module.modules.movement.Sprint;
import me.equinox.module.modules.player.NoFall;
import me.equinox.module.modules.render.Fullbright;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        /* COMBAT */

        /* MOVEMENT */
        modules.add(new Fly());
        modules.add(new Sprint());

        /* PLAYER */
        modules.add(new NoFall());

        /* RENDER */
        modules.add(new Fullbright());

        /* WORLD */

        /* MISC */

    }

    public List<Module> getModules() { return modules; }
    public Module getModuleByName(String name) { return modules.stream().filter(module -> module.getName().equals(name)).findFirst().orElse(null); }
    public ArrayList<Module> getModulesByCategory(Category cate) {
        ArrayList<Module> modules = new ArrayList<Module>();
        for(Module module : Equinox.instance.mm.getModules()) {
            if(module.getCategory() == cate) {
                modules.add(module);
            }
        }
        return modules;
    }

}
