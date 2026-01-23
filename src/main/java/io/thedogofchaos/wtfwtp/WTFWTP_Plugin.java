package io.thedogofchaos.wtfwtp;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.Name;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import cpw.mods.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@MCVersion("1.7.10")
@Name("What the Frick Was That Packet?")
@TransformerExclusions("io.thedogofchaos.wtfwtp.WTFWTP_Plugin")
public class WTFWTP_Plugin implements IFMLLoadingPlugin, IEarlyMixinLoader {
    public static final Logger LOGGER = LogManager.getLogger("wtfwtp");
    static File location;

    @Override
    public String getMixinConfig() {
        return "mixins.wtfwtp.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        return Collections.singletonList("minecraft.network.PacketBuffer_WTF");
    }

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        if (FMLLaunchHandler.side() == Side.CLIENT) {
            return WTFContainer.class.getName();
        }
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        location = (File) data.get("coremodLocation");
        if (location == null) {
            location = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        }
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
