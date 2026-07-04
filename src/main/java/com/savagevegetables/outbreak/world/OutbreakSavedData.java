package com.savagevegetables.outbreak.world;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class OutbreakSavedData extends SavedData {

    private boolean isStage2Active = false;

    public OutbreakSavedData() {
    }

    public static OutbreakSavedData load(CompoundTag nbt) {
        OutbreakSavedData data = new OutbreakSavedData();
        data.isStage2Active = nbt.getBoolean("IsStage2Active");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.putBoolean("IsStage2Active", isStage2Active);
        return nbt;
    }

    public boolean isStage2Active() {
        return isStage2Active;
    }

    public void setStage2Active(boolean stage2Active) {
        this.isStage2Active = stage2Active;
        this.setDirty();
    }

    public static OutbreakSavedData get(ServerLevel level) {
        // Typically stored in overworld so it's global
        ServerLevel overworld = level.getServer().getLevel(ServerLevel.OVERWORLD);
        if (overworld != null) {
            return overworld.getDataStorage().computeIfAbsent(
                    OutbreakSavedData::load,
                    OutbreakSavedData::new,
                    "savage_vegetables_outbreak"
            );
        }
        return new OutbreakSavedData();
    }
}
