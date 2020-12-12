package net.minecraft.client.resources;

import com.google.gson.JsonParseException;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ResourcePackListEntryDefault extends ResourcePackListEntry {
    public static final Logger logger = LogManager.getLogger();
    public final IResourcePack field_148320_d;
    public final ResourceLocation resourcePackIcon;

    public ResourcePackListEntryDefault(GuiScreenResourcePacks resourcePacksGUIIn) {
        super(resourcePacksGUIIn);
        this.field_148320_d = this.mc.getResourcePackRepository().rprDefaultResourcePack;
        DynamicTexture dynamictexture;

        try {
            dynamictexture = new DynamicTexture(this.field_148320_d.getPackImage());
        } catch (IOException var4) {
            dynamictexture = TextureUtil.missingTexture;
        }

        this.resourcePackIcon = this.mc.getTextureManager().getDynamicTextureLocation("texturepackicon", dynamictexture);
    }

    public int func_183019_a() {
        return 1;
    }

    public String func_148311_a() {
        try {
            PackMetadataSection packmetadatasection = this.field_148320_d.getPackMetadata(this.mc.getResourcePackRepository().rprMetadataSerializer, "pack");

            if (packmetadatasection != null) {
                return packmetadatasection.getPackDescription().getFormattedText();
            }
        } catch (JsonParseException jsonparseexception) {
            logger.error("Couldn't load metadata info", jsonparseexception);
        } catch (IOException ioexception) {
            logger.error("Couldn't load metadata info", ioexception);
        }

        return EnumChatFormatting.RED + "Missing " + "pack.mcmeta" + " :(";
    }

    public boolean func_148309_e() {
        return false;
    }

    public boolean func_148308_f() {
        return false;
    }

    public boolean func_148314_g() {
        return false;
    }

    public boolean func_148307_h() {
        return false;
    }

    public String func_148312_b() {
        return "Default";
    }

    public void func_148313_c() {
        this.mc.getTextureManager().bindTexture(this.resourcePackIcon);
    }

    public boolean func_148310_d() {
        return false;
    }
}
