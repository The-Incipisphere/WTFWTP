package io.thedogofchaos.wtfwtp.mixins.early.minecraft.network;

import java.io.IOException;

import net.minecraft.network.PacketBuffer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import io.thedogofchaos.wtfwtp.WTFWTP_Plugin;

@Mixin(PacketBuffer.class)
public class WTF_PacketBuffer {

    static {
        WTFWTP_Plugin.LOGGER.info("PacketBuffer_WTF mixin loaded");
    }

    @Inject(
        method = "readStringFromBuffer",
        at = @At(value = "INVOKE", target = "java/lang/String.length()I", shift = At.Shift.AFTER),
        locals = LocalCapture.CAPTURE_FAILEXCEPTION,
        cancellable = true)
    private void wtfwtp$spitTheBadString(int maxLength, CallbackInfoReturnable<String> cir, int j, String s)
        throws IOException {
        if (s.length() > maxLength) {
            throw new IOException(
                "[WTFWTP] String too long: length=" + s
                    .length() + ", max=" + maxLength + ", encodedBytes=" + j + ", badString=\"" + s + "\"");
        }
    }
}
