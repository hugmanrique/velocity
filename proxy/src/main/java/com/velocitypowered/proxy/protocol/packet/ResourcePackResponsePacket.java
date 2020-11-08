package com.velocitypowered.proxy.protocol.packet;

import com.velocitypowered.api.event.player.PlayerResourcePackStatusEvent.Status;
import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.proxy.connection.MinecraftSessionHandler;
import com.velocitypowered.proxy.protocol.Packet;
import com.velocitypowered.proxy.protocol.ProtocolUtils;
import com.velocitypowered.proxy.protocol.ProtocolDirection;
import io.netty.buffer.ByteBuf;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

public class ResourcePackResponsePacket implements Packet {

  public static final Decoder<ResourcePackResponsePacket> DECODER = Decoder.method(ResourcePackResponsePacket::new);

  private String hash = "";
  private @MonotonicNonNull Status status;

  public Status getStatus() {
    if (status == null) {
      throw new IllegalStateException("Packet not yet deserialized");
    }
    return status;
  }

  @Override
  public void decode(ByteBuf buf, ProtocolDirection direction, ProtocolVersion protocolVersion) {
    if (protocolVersion.lte(ProtocolVersion.MINECRAFT_1_9_4)) {
      this.hash = ProtocolUtils.readString(buf);
    }
    this.status = Status.values()[ProtocolUtils.readVarInt(buf)];
  }

  @Override
  public void encode(ByteBuf buf, ProtocolDirection direction, ProtocolVersion protocolVersion) {
    if (protocolVersion.lte(ProtocolVersion.MINECRAFT_1_9_4)) {
      ProtocolUtils.writeString(buf, hash);
    }
    ProtocolUtils.writeVarInt(buf, status.ordinal());
  }

  @Override
  public boolean handle(MinecraftSessionHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "ResourcePackResponsePacket{"
        + "hash=" + hash + ", "
        + "status=" + status
        + '}';
  }
}