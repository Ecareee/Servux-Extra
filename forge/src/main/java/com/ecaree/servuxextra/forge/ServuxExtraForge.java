package com.ecaree.servuxextra.forge;

import com.ecaree.servuxextra.ServuxExtra;
import net.minecraftforge.fml.common.Mod;

@Mod(ServuxExtra.MOD_ID)
public class ServuxExtraForge {
    public ServuxExtraForge() {
        ServuxExtra.init();
    }
}