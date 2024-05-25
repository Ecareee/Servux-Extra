package com.ecaree.servuxextra.mixin;

import com.ecaree.servuxextra.dataproviders.WanderingTraderDataProvider;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.WanderingTraderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WanderingTraderManager.class)
public class MixinWanderingTraderManager {
    @Shadow
    private int spawnTimer;

    // Accessor doesn't seem to work, have to do it this way
    @Inject(method = "spawn", at = @At("RETURN"))
    private void onSpawn(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals, CallbackInfoReturnable<Integer> cir) {
        WanderingTraderDataProvider.INSTANCE.setWanderingTraderSpawnTimer(spawnTimer);
    }
}