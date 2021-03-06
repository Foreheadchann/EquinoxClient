package me.equinox.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.Equinox;
import me.equinox.notification.NotificationHandler;
import me.equinox.notification.utils.NotificationManager;
import me.equinox.notification.utils.NotificationType;
import me.equinox.settings.Setting;
import me.equinox.utils.MCInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;

public class Module extends MCInstance {
    private String name, displayName, description;
    private int key;
    private Category category;
    private boolean toggled;
    public final ArrayList<Setting> settings = new ArrayList<>();

    public Module() {
        ModuleInfo module = this.getClass().getAnnotation(ModuleInfo.class);
        name = module.name();
        displayName = module.name();
        description = module.description();
        key = module.key();
        category = module.category();

        toggled = false;
    }

    public Setting getSettingByName(String name) {
        return settings.stream().filter(setting -> setting.name.equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void onEnable() {
        Equinox.instance.em.register(this);
        NotificationManager.displayNotification(new NotificationHandler(NotificationType.INFO, "§aEnabled §f" + name, null, 3, EnumChatFormatting.GREEN.getColorIndex()));
    }

    public void onDisable() {
        Equinox.instance.em.unregister(this);
        NotificationManager.displayNotification(new NotificationHandler(NotificationType.INFO, "§cDisabled §f" + name, null, 3, EnumChatFormatting.RED.getColorIndex()));
    }

    public void onToggle() {

    }

    public void toggle() {
        toggled = !toggled;
        if(toggled)
            onEnable();
        else
            onDisable();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName == null ? name : displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }
}

