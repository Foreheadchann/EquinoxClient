package net.minecraft.client.renderer.texture;

import net.minecraft.client.resources.IResourceManager;
import optifine.Config;
import shadersmod.client.ShadersTex;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class DynamicTexture extends AbstractTexture {
    public static final String __OBFID = "CL_00001048";
    public final int[] dynamicTextureData;
    /**
     * width of this icon in pixels
     */
    public final int width;
    /**
     * height of this icon in pixels
     */
    public final int height;
    public boolean shadersInitialized;

    public DynamicTexture(int textureWidth, int textureHeight) {
        this.shadersInitialized = false;
        this.width = textureWidth;
        this.height = textureHeight;
        this.dynamicTextureData = new int[textureWidth * textureHeight * 3];

        if (Config.isShaders()) {
            ShadersTex.initDynamicTexture(this.getGlTextureId(), textureWidth, textureHeight, this);
            this.shadersInitialized = true;
        } else {
            TextureUtil.allocateTexture(this.getGlTextureId(), textureWidth, textureHeight);
        }
    }

    public DynamicTexture(BufferedImage bufferedImage) {
        this(bufferedImage.getWidth(), bufferedImage.getHeight());
        bufferedImage.getRGB(0, 0, width, height, this.dynamicTextureData, 0, width);
        this.updateDynamicTexture();
    }

    public void loadTexture(IResourceManager resourceManager) throws IOException {
    }

    public void updateDynamicTexture() {
        if (Config.isShaders()) {
            if (!this.shadersInitialized) {
                ShadersTex.initDynamicTexture(this.getGlTextureId(), this.width, this.height, this);
                this.shadersInitialized = true;
            }

            ShadersTex.updateDynamicTexture(this.getGlTextureId(), this.dynamicTextureData, this.width, this.height, this);
        } else {
            TextureUtil.uploadTexture(this.getGlTextureId(), this.dynamicTextureData, this.width, this.height);
        }
    }

    public int[] getTextureData() {
        return this.dynamicTextureData;
    }
}
