package me.equinox.utils;

import net.minecraft.client.Minecraft;

public class MovementUtils {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static boolean isMoving() {
        return mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0;
    }

    public static boolean isAllowedToSprint() {
        return !mc.thePlayer.isUsingItem() && !mc.thePlayer.isCollidedHorizontally && mc.thePlayer.moveForward > 0 && !mc.thePlayer.isSneaking();
    }
}
