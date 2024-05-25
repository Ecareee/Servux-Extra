package com.ecaree.servuxextra.mixin;

import com.ecaree.servuxextra.dataproviders.WanderingTraderDataProvider;
import com.ecaree.servuxextra.dataproviders.WeatherDataProvider;
import fi.dy.masa.servux.Servux;
import fi.dy.masa.servux.dataproviders.DataProviderManager;
import fi.dy.masa.servux.dataproviders.IDataProvider;
import fi.dy.masa.servux.dataproviders.StructureDataProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Servux.class, remap = false)
public class MixinServux {
    /**
     * Servux Extra is loaded later than Servux, registering providers in Servux Extra's init method and do not invoke
     * {@link DataProviderManager#readFromConfig()} method means that {@link DataProviderManager#setProviderEnabled(IDataProvider, boolean)}
     * method will not be invoked, as a result, the registered provider will not work.
     * Also, if readFromConfig method is invoked in Servux Extra's init method, because Servux Extra is loaded later than Servux,
     * {@link StructureDataProvider} will be added to the list of providers that actually work after invoking
     * readFromConfig method in Servux's init method, and when readFromConfig method is invoked again in Servux Extra's init method,
     * StructureDataProvider will be removed from the list of providers that actually work in setProviderEnabled method.
     * Therefore, using Mixin is the most suitable solution.
     */
    @Inject(method = "onInitialize", at = @At("HEAD"))
    public void onInitializeHead(CallbackInfo ci) {
        DataProviderManager.INSTANCE.registerDataProvider(WanderingTraderDataProvider.INSTANCE);
        DataProviderManager.INSTANCE.registerDataProvider(WeatherDataProvider.INSTANCE);
    }

//    @Inject(method = "onInitialize", at = @At("TAIL"))
//    public void onInitializeTail(CallbackInfo ci) {
//        DataProviderManager.INSTANCE.writeToConfig();
//    }
}