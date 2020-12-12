package me.equinox.notification;

import me.equinox.notification.utils.NotificationType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class NotificationHandler {
    private NotificationType type;
    private String header;
    private String details;
    private long start;
    private int c;

    private long f1;
    private long f2;
    private long e;

    public NotificationHandler(NotificationType type, String header, String details, int l, int c) {
        this.type = type;
        this.header = header;
        this.details = details;
        this.c = c;

        f1 = 100 * l;
        f2 = f1 + 200 * l;
        e = f2 + f1;
    }

    public void display() { start = System.currentTimeMillis(); }
    public boolean isBeingDisplayed() { return getTime() <= e; }
    private long getTime() { return System.currentTimeMillis() - start; }

    public void render() {
        double off = 0;
        int notificationWidth = 120;
        int notificationHeight = 40;
        long time = getTime();
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        if(time < f1) { off = Math.tanh(time / (double) f1 * 5.0) * notificationWidth; } else if(time > f2) { off = (Math.atan(1.0 - (time - f2) / (double) (e - f2) * 3.0) * notificationWidth); } else { off = notificationWidth; }

        drawRect(7, (int) (GuiScreen.width - off), GuiScreen.height - 5 - notificationHeight, GuiScreen.width, GuiScreen.height - 10, 0xcc000000);
        drawRect(7, (int) (GuiScreen.width - off), GuiScreen.height - 5 - notificationHeight, (int) (GuiScreen.width - off - 3), GuiScreen.height - 10, c);
        fr.drawString(this.header, (int) (GuiScreen.width - off) + 2, GuiScreen.height - notificationHeight + 6, -1, true);
        fr.drawString(this.details, (int) (GuiScreen.width - off) + 2, GuiScreen.height - notificationHeight + 15, -1, true);

        if(type == NotificationType.INFO)
            fr.drawString("Notice!", (int) (GuiScreen.width - off) + 2, GuiScreen.height - notificationHeight - 3, -1, true);
        if(type == NotificationType.WARNING)
            fr.drawString("Warning!", (int) (GuiScreen.width - off) + 2, GuiScreen.height - notificationHeight - 3, -1, true);
        if(type == NotificationType.ALERT)
            fr.drawString("Alert!", (int) (GuiScreen.width - off) + 2, GuiScreen.height - notificationHeight - 3, -1, true);
        if(type == NotificationType.ERROR)
            fr.drawString("Error!", (int) (GuiScreen.width - off) + 2, GuiScreen.height - notificationHeight - 3, -1, true);
    }

    public void drawRect(int mode, int left, int top, int right, int bottom, int color) {
        if (left < right) {
            int i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            int j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(mode, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, top, 0.0D).endVertex();
        worldrenderer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}
