package io.thedogofchaos.wtfwtp;

import java.io.File;
import java.util.Collections;
import java.util.Set;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.VersionParser;
import cpw.mods.fml.common.versioning.VersionRange;

public class WTFContainer extends DummyModContainer {

    public WTFContainer() {
        super(new ModMetadata());
        ModMetadata md = this.getMetadata();

        // NOTE: If you change this, change mcmod.info too!
        md.authorList = Collections.singletonList("TheDogOfChaos");
        md.description = "Makes PacketBuffer actually spit out what was 'too long' for it.";
        md.modId = "wtfwtp";
        md.name = "What the Frick Was That Packet?";
        md.url = "https://github.com/The-Incipisphere/WTFWTP";
        md.version = Tags.VERSION;
    }

    @Override
    public VersionRange acceptableMinecraftVersionRange() {
        return VersionParser.parseRange("[1.7.10]");
    }

    @Override
    public Set<ArtifactVersion> getRequirements() {
        return Collections.singleton(VersionParser.parseVersionReference("gtnhmixins"));
    }

    @Override
    public File getSource() {
        return WTFWTP_Plugin.location;
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }
}
