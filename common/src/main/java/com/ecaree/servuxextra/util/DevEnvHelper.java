package com.ecaree.servuxextra.util;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class DevEnvHelper {
    @ExpectPlatform
    public static boolean isDevEnv() {
        throw new AssertionError();
    }
}