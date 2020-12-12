package me;

import me.equinox.event.EventManager;
import me.equinox.module.ModuleManager;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

public class Equinox {
    public static String __NAME__ = "Equinox", __VERSION__ = "b0.1", __AUTHORS__ = "Foreheadchan_, P3rZ3r0, Alan31, Patrick, Tecnio";

    private Minecraft mc = Minecraft.getMinecraft();

    public static Equinox instance = new Equinox();
    public EventManager em;
    public ModuleManager mm;

    public void startMainClientThread() {
        System.out.println("["+__NAME__+"] Started client main thread! | Ver: " + __VERSION__ + " | Auth: " + __AUTHORS__);
        Display.setTitle("[ " + __NAME__ + " ] BETA");
        em = new EventManager();
        mm = new ModuleManager();

        em.register(this);
    }

    public void stopMainClientThread() {
        em.unregister(this);
    }

    public void forceTerminateClientThread() {
        em.unregister(this);
        mc.shutdown();
    }
}
