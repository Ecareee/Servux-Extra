package com.ecaree.servuxextra.util.fabric;

import net.fabricmc.loader.api.FabricLoader;

public class DevEnvHelperImpl {
    public static boolean isDevEnv() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}