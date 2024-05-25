package com.ecaree.servuxextra;

import com.ecaree.servuxextra.util.DevEnvHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServuxExtra {
    public static final String MOD_ID = "servuxextra";
    public static final String MOD_NAME = "ServuxExtra";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
//        DataProviderManager.INSTANCE.registerDataProvider(WanderingTraderDataProvider.INSTANCE);
//        DataProviderManager.INSTANCE.registerDataProvider(MyDataProvider.INSTANCE);
//        DataProviderManager.INSTANCE.setProviderEnabled(WanderingTraderDataProvider.INSTANCE, true);
//        DataProviderManager.INSTANCE.setProviderEnabled(MyDataProvider.INSTANCE, true);
//        DataProviderManager.INSTANCE.readFromConfig();

        if (DevEnvHelper.isDevEnv()) {
            LOGGER.info("Development environment detected, Servux Extra will output more logs");
        }
    }

    public static void printDebug(String text) {
//        if (DevEnvHelper.isDevEnv()) {
//            LOGGER.info(text);
//        }
        LOGGER.info(text);
    }
}