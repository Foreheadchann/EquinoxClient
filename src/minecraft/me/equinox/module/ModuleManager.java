package me.equinox.module;

import me.Equinox;
import me.equinox.module.modules.movement.*;
import me.equinox.module.modules.player.*;
import me.equinox.module.modules.render.*;
import me.equinox.settings.BooleanSetting;
import me.equinox.settings.ModeSetting;
import me.equinox.settings.NumberSetting;
import me.equinox.settings.Setting;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        /* COMBAT */

        /* MOVEMENT */
        registerModule(new Fly());
        registerModule(new Sprint());

        /* PLAYER */
        registerModule(new NoFall());

        /* RENDER */
        registerModule(new Fullbright());
        registerModule(new ClickGui());

        /* WORLD */

        /* MISC */

    }

    private void registerModule(Module module) {
        modules.add(module);

        for (Field field : module.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType().equals(BooleanSetting.class) || field.getType().equals(NumberSetting.class) || field.getType().equals(ModeSetting.class)) {
                try {
                    module.settings.add(((Setting) field.get(module)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Module> getModules() { return modules; }
    public Module getModuleByName(String name) { return modules.stream().filter(module -> module.getName().equals(name)).findFirst().orElse(null); }
    public ArrayList<Module> getModulesByCategory(Category cate) {
        ArrayList<Module> modules = new ArrayList<Module>();
        for(Module module : Equinox.instance.mm.getModules()) {
            if(module.getCategory() == cate) {
                registerModule(module);
            }
        }
        return modules;
    }

}
