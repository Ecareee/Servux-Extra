package com.ecaree.servuxextra.network;

import com.ecaree.servuxextra.dataproviders.SimpleDataProvider;
import com.ecaree.servuxextra.dataproviders.WanderingTraderDataProvider;
import net.minecraft.util.Identifier;

public class WanderingTraderDataPacketHandler extends SimpleDataPacketHandler {
    public static final WanderingTraderDataPacketHandler INSTANCE = new WanderingTraderDataPacketHandler();

    protected WanderingTraderDataPacketHandler() {
        super(new Identifier("servuxextra:wandering_trader_data"), 1, 31, 32);
    }

    @Override
    protected SimpleDataProvider getDataProvider() {
        return WanderingTraderDataProvider.INSTANCE;
    }
}