package cn.evole.mods.elona.api.common.net;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Name: Elona-forged / IPacket
 * Author: cnlimiter
 * CreateTime: 2023/9/6 20:03
 * Description:
 */

public abstract class IPacket<T extends IPacket<T>> {

    public IPacket() {
    }

    public abstract T read(FriendlyByteBuf buf);

    public abstract void write(T msg, FriendlyByteBuf buf);

    public abstract void run(T msg, Supplier<NetworkEvent.Context> ctx);
}
