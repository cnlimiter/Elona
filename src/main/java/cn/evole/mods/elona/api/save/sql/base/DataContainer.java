package cn.evole.mods.elona.api.save.sql.base;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.ChunkPos;

import java.util.UUID;

/**
 * Name: Elona / DataContainer
 * Author: cnlimiter
 * CreateTime: 2023/9/3 21:58
 * Description:
 */

public class DataContainer {
    private final Table table;
    private final SQLConnection sqlConnection;
    private final String id;

    public DataContainer(String id, Table table, SQLConnection sqlConnection) {
        this.id = id;
        this.table = table;
        this.sqlConnection = sqlConnection;
    }

    public String getIdAsString() {
        return id;
    }

    public UUID getIdAsUUID() {
        return UUID.fromString(id);
    }

    public int getIdAsInt() {
        return Integer.parseInt(id);
    }

    public void put(String field, String value) {
        sqlConnection.writeField(table, id, field, value);
    }

    public void put(String field, int value) {
        sqlConnection.writeField(table, id, field, value);
    }

    public void put(String field, double value) {
        sqlConnection.writeField(table, id, field, value);
    }

    public void put(String field, long value) {
        sqlConnection.writeField(table, id, field, value);
    }

    public void put(String field, boolean value) {
        sqlConnection.writeField(table, id, field, value ? 1 : 0); // Convert bool to int, SQLite compat
    }

    public void put(String field, BlockPos value) {
        sqlConnection.writeField(table, id, field, value.asLong());
    }

    public void put(String field, ChunkPos value) {
        sqlConnection.writeField(table, id, field, value.toLong());
    }

    public void put(String field, JsonElement value) {
        sqlConnection.writeField(table, id, field, value.toString());
    }

    public void put(String field, Tag value) {
        sqlConnection.writeField(table, id, field, value.getAsString());
    }

    public void put(String field, MutableComponent value) {
        sqlConnection.writeField(table, id, field, MutableComponent.Serializer.toJson(value));
    }

    public void put(String field, UUID value) {
        sqlConnection.writeField(table, id, field, value.toString());
    }

    public String getString(String field) {
        return sqlConnection.readField(table, id, field, String.class);
    }

    public int getInt(String field) {
        return sqlConnection.readField(table, id, field, Integer.class);
    }

    public double getDouble(String field) {
        return sqlConnection.readField(table, id, field, Double.class);
    }

    public double getLong(String field) {
        return sqlConnection.readField(table, id, field, Long.class);
    }

    public boolean getBool(String field) {
        return sqlConnection.readField(table, id, field, Integer.class) > 0; //Int to bool, SQLite compat
    }

    public BlockPos getBlockPos(String field) {
        return BlockPos.of(sqlConnection.readField(table, id, field, Long.class));
    }

    public ChunkPos getChunkPos(String field) {
        return new ChunkPos(sqlConnection.readField(table, id, field, Long.class));
    }

    public JsonElement getJson(String field) {
        return JsonParser.parseString(sqlConnection.readField(table, id, field, String.class));
    }

    public Tag getNbt(String field) {
        try {
            return TagParser.parseTag(sqlConnection.readField(table, id, field, String.class));
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MutableComponent getMutableText(String field) {
        return Component.Serializer.fromJson(sqlConnection.readField(table, id, field, String.class));
    }

    public UUID getUUID(String field) {
        return UUID.fromString(sqlConnection.readField(table, id, field, String.class));
    }
}
