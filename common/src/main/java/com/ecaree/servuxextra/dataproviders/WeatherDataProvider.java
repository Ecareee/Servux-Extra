package com.ecaree.servuxextra.dataproviders;

import com.ecaree.servuxextra.ServuxExtra;
import com.ecaree.servuxextra.network.WeatherDataPacketHandler;
import fi.dy.masa.servux.network.PacketSplitter;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.level.ServerWorldProperties;

public class WeatherDataProvider extends SimpleDataProvider {
    public static final WeatherDataProvider INSTANCE = new WeatherDataProvider();

    protected int outputInterval = 120;
    private int clearWeatherTime;
    private boolean raining;
    private int rainTime;
    private boolean thundering;
    private int thunderTime;

    protected WeatherDataProvider() {
        super("weather", WeatherDataPacketHandler.INSTANCE, "Weather data");
    }

    @Override
    public void tick(MinecraftServer server, int tickCounter) {
        ServerWorldProperties properties = (ServerWorldProperties) server.getOverworld().getLevelProperties();
        clearWeatherTime = properties.getClearWeatherTime();
        raining = properties.isRaining();
        rainTime = properties.getRainTime();
        thundering = properties.isThundering();
        thunderTime = properties.getThunderTime();

        if ((tickCounter % outputInterval) == 0) {
            ServuxExtra.printDebug("WeatherDataProvider#tick:"
                    + " clearWeatherTime: " + clearWeatherTime
                    + " raining: " + raining
                    + " rainTime: " + rainTime
                    + " thundering: " + thundering
                    + " thunderTime: " + thunderTime);
        }

        sendData(server);
    }

    @Override
    protected void sendPacket(ServerPlayerEntity player) {
        NbtCompound tag = new NbtCompound();
        tag.putInt("clearWeatherTime", clearWeatherTime);
        tag.putBoolean("raining", raining);
        tag.putInt("rainTime", rainTime);
        tag.putBoolean("thundering", thundering);
        tag.putInt("thunderTime", thunderTime);

        PacketSplitter.sendPacketTypeAndCompound(getPacketHandler().getChannel(), getPacketHandler().getPacketS2CSendData(), tag, player);
    }
}