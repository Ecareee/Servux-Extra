package com.ecaree.servuxextra.dataproviders;

import com.ecaree.servuxextra.ServuxExtra;
import com.ecaree.servuxextra.network.SimpleDataPacketHandler;
import fi.dy.masa.servux.dataproviders.DataProviderBase;
import fi.dy.masa.servux.network.PacketSplitter;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public abstract class SimpleDataProvider extends DataProviderBase {
    protected final ArrayList<UUID> registeredPlayers = new ArrayList<>();
    protected final NbtCompound metadata = new NbtCompound();
    private final SimpleDataPacketHandler dataPacketHandler;

    protected SimpleDataProvider(String name, SimpleDataPacketHandler dataPacketHandler, String description) {
        super(name, dataPacketHandler.getChannel(), dataPacketHandler.getProtocolVersion(), description);
        this.dataPacketHandler = dataPacketHandler;
        setTickRate(getDefaultTickRate());
        metadata.putString("id", dataPacketHandler.getChannel().toString());
        metadata.putInt("version", dataPacketHandler.getProtocolVersion());
    }

    @Override
    public SimpleDataPacketHandler getPacketHandler() {
        return this.dataPacketHandler;
    }

    @Override
    public boolean shouldTick() {
        return true;
    }

    @Override
    public abstract void tick(MinecraftServer server, int tickCounter);

    protected abstract void sendPacket(ServerPlayerEntity player);

    protected void sendData(MinecraftServer server) {
//        ServuxExtra.printDebug(getClass().getSimpleName() + "#sendData");

        if (!registeredPlayers.isEmpty()) {
            Iterator<UUID> uuidIter = registeredPlayers.iterator();

            while (uuidIter.hasNext()) {
                UUID uuid = uuidIter.next();
                ServerPlayerEntity player = server.getPlayerManager().getPlayer(uuid);

                if (player != null) {
                    sendPacket(server.getPlayerManager().getPlayer(uuid));
                } else {
                    uuidIter.remove();
                }
            }
        }
    }

    public boolean register(ServerPlayerEntity player) {
        ServuxExtra.printDebug(getClass().getSimpleName() + "#register");

        boolean registered = false;
        UUID uuid = player.getUuid();

        if (!registeredPlayers.contains(uuid)) {
            PacketSplitter.sendPacketTypeAndCompound(getNetworkChannel(), dataPacketHandler.getPacketS2CMetadata(), metadata, player);
            registeredPlayers.add(uuid);
            sendPacket(player);
            registered = true;
        }

        return registered;
    }

    public boolean unregister(ServerPlayerEntity player) {
        ServuxExtra.printDebug(getClass().getSimpleName() + "#unregister");
        return registeredPlayers.remove(player.getUuid());
    }

    protected int getDefaultTickRate() {
        return 1;
    }
}