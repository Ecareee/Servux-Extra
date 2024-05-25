package com.ecaree.servuxextra.util.forge;

import net.minecraftforge.fml.loading.FMLLoader;

public class DevEnvHelperImpl {
    public static boolean isDevEnv() {
        return !FMLLoader.isProduction();
    }
}