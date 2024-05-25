package com.ecaree.servuxextra.fabric;

import com.ecaree.servuxextra.ServuxExtra;
import net.fabricmc.api.ModInitializer;

public class ServuxExtraFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ServuxExtra.init();
    }
}