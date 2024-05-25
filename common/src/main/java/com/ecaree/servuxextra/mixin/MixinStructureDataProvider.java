package com.ecaree.servuxextra.mixin;

import com.ecaree.servuxextra.ServuxExtra;
import fi.dy.masa.servux.dataproviders.DataProviderBase;
import fi.dy.masa.servux.dataproviders.StructureDataProvider;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = StructureDataProvider.class, remap = false)
public abstract class MixinStructureDataProvider extends DataProviderBase {

    protected MixinStructureDataProvider(String name, Identifier channel, int protocolVersion, String description) {
        super(name, channel, protocolVersion, description);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(MinecraftServer server, int tickCounter, CallbackInfo ci) {
        if ((tickCounter % 120) == 0) {
            ServuxExtra.printDebug("tick: " + tickCounter + " - " + this.isEnabled());
        }
    }
}