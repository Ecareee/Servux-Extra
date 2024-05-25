package com.ecaree.servuxextra.network;

import com.ecaree.servuxextra.ServuxExtra;
import com.ecaree.servuxextra.dataproviders.SimpleDataProvider;
import fi.dy.masa.servux.network.IPluginChannelHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.Identifier;

public abstract class SimpleDataPacketHandler implements IPluginChannelHandler {
    protected final Identifier channel;
    protected final int protocolVersion;
    protected final int packetS2CMetadata;
    protected final int packetS2CSendData;

    protected SimpleDataPacketHandler(Identifier channel, int protocolVersion, int packetS2CMetadata, int packetS2CSendData) {
        this.channel = channel;
        this.protocolVersion = protocolVersion;
        this.packetS2CMetadata = packetS2CMetadata;
        this.packetS2CSendData = packetS2CSendData;
    }

    @Override
    public Identifier getChannel() {
        return channel;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public int getPacketS2CMetadata() {
        return packetS2CMetadata;
    }

    public int getPacketS2CSendData() {
        return packetS2CSendData;
    }

    protected abstract SimpleDataProvider getDataProvider();

    @Override
    public boolean isSubscribable() {
        return true;
    }

    @Override
    public boolean subscribe(ServerPlayNetworkHandler netHandler) {
        ServuxExtra.printDebug(this.getClass().getSimpleName() + "#subscribe");
        return getDataProvider().register(netHandler.getPlayer());
    }

    @Override
    public boolean unsubscribe(ServerPlayNetworkHandler netHandler) {
        ServuxExtra.printDebug(this.getClass().getSimpleName() + "#unsubscribe");
    return getDataProvider().unregister(netHandler.getPlayer());
    }
}