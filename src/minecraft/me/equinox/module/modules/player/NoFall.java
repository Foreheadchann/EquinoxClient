package me.equinox.module.modules.player;

import me.equinox.event.EventTarget;
import me.equinox.event.callable.EventUpdate;
import me.equinox.module.Category;
import me.equinox.module.Module;
import me.equinox.module.ModuleInfo;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "NoFall", description = "Makes you take no fall damage", key = Keyboard.KEY_N, category =  Category.PLAYER)
public class NoFall extends Module {

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if(mc.thePlayer.fallDistance > 2f) {
            mc.getNetHandler().addToSendQueue(new C03PacketPlayer(true));
        }
    }
}
