package cn.evole.mods.elona.api.init.handler;

import cn.evole.mods.elona.api.common.net.IPacket;
import lombok.Getter;
import net.minecraft.network.Connection;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Objects;

/**
 * Name: Elona-forged / NetBaseHandler
 * Author: cnlimiter
 * CreateTime: 2023/9/6 20:03
 * Description:
 */

public class NetBaseHandler {
    @Getter
    private final SimpleChannel channel;
    private int id = 0;

    public NetBaseHandler(ResourceLocation id) {
        this.channel = NetworkRegistry.newSimpleChannel(id, () -> {
            return "1.0";
        }, (s) -> {
            return true;
        }, (s) -> {
            return true;
        });
    }

    public <T extends IPacket<T>> void register(Class<T> clazz, IPacket<T> message) {
        SimpleChannel.MessageBuilder<T> messageBuilder = this.channel.messageBuilder(clazz, this.id++);
        Objects.requireNonNull(message);
        messageBuilder = messageBuilder.encoder(message::write);
        Objects.requireNonNull(message);
        messageBuilder = messageBuilder.decoder(message::read);
        Objects.requireNonNull(message);
        messageBuilder.consumerNetworkThread(message::run).add();
    }
    public <M> void sendToServer(M message) {
        this.channel.sendToServer(message);
    }

    public <M> void sendTo(M message, Connection manager, NetworkDirection direction) {
        this.channel.sendTo(message, manager, direction);
    }

    public <M> void send(PacketDistributor.PacketTarget target, M message) {
        this.channel.send(target, message);
    }

    public <M> void reply(M message, NetworkEvent.Context context) {
        this.channel.reply(message, context);
    }
}
