package com.ecaree.servuxextra.dataproviders;

import com.ecaree.servuxextra.ServuxExtra;
import com.ecaree.servuxextra.network.WanderingTraderDataPacketHandler;
import fi.dy.masa.servux.network.PacketSplitter;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.level.ServerWorldProperties;

public class WanderingTraderDataProvider extends SimpleDataProvider {
    public static final WanderingTraderDataProvider INSTANCE = new WanderingTraderDataProvider();

    protected int timeout = 30 * 20;
    protected int outputInterval = 120;
    private int wanderingTraderSpawnTimer;
    private int wanderingTraderSpawnDelay;
    private int wanderingTraderSpawnChance;

    protected WanderingTraderDataProvider() {
        super("wandering_trader", WanderingTraderDataPacketHandler.INSTANCE, "Wandering trader data");
    }

    @Override
    public void tick(MinecraftServer server, int tickCounter) {
        ServerWorldProperties properties = (ServerWorldProperties) server.getOverworld().getLevelProperties();
        wanderingTraderSpawnDelay = properties.getWanderingTraderSpawnDelay();
        wanderingTraderSpawnChance = properties.getWanderingTraderSpawnChance();
        if ((tickCounter % outputInterval) == 0) {
            ServuxExtra.printDebug("WanderingTraderDataProvider#tick:"
                    + " Timer: " + wanderingTraderSpawnTimer
                    + " Delay: " + wanderingTraderSpawnDelay
                    + " Chance: " + wanderingTraderSpawnChance);
        }
        if (tickCounter % timeout == 0 && properties.getGameRules().getBoolean(GameRules.DO_TRADER_SPAWNING)) {
            sendData(server);
        }
    }

    @Override
    protected void sendPacket(ServerPlayerEntity player) {

        NbtCompound tag = new NbtCompound();
        tag.putInt("wanderingTraderSpawnTimer", wanderingTraderSpawnTimer);
        tag.putInt("wanderingTraderSpawnDelay", wanderingTraderSpawnDelay);
        tag.putInt("wanderingTraderSpawnChance", wanderingTraderSpawnChance);

        PacketSplitter.sendPacketTypeAndCompound(getPacketHandler().getChannel(), getPacketHandler().getPacketS2CSendData(), tag, player);
    }

    public void setWanderingTraderSpawnTimer(int spawnTimer) {
        wanderingTraderSpawnTimer = spawnTimer;
    }
}