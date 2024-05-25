package com.ecaree.servuxextra.network;

import com.ecaree.servuxextra.dataproviders.SimpleDataProvider;
import com.ecaree.servuxextra.dataproviders.WeatherDataProvider;
import net.minecraft.util.Identifier;

public class WeatherDataPacketHandler extends SimpleDataPacketHandler {
    public static final WeatherDataPacketHandler INSTANCE = new WeatherDataPacketHandler();

    protected WeatherDataPacketHandler() {
        super(new Identifier("servuxextra:weather_data"), 1, 33, 34);
    }

    @Override
    protected SimpleDataProvider getDataProvider() {
        return WeatherDataProvider.INSTANCE;
    }
}