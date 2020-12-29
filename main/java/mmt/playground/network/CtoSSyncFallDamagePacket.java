package mmt.playground.network;

import java.util.function.Supplier;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class CtoSSyncFallDamagePacket 
{
	public float fD;

	public CtoSSyncFallDamagePacket(float fD) 
	{
		this.fD = fD;
	}
	
	public void serialize(PacketBuffer buf) 
	{
		buf.writeFloat(fD);
	}
	
	public static CtoSSyncFallDamagePacket deserialize(PacketBuffer buf) 
	{
		float fD = buf.readFloat();
		
		return new CtoSSyncFallDamagePacket(fD);
	}
	
	public static void handle(CtoSSyncFallDamagePacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		ServerPlayerEntity sender = contextSupplier.get().getSender();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.SERVER) {
			context.enqueueWork(() -> {
				sender.fallDistance = message.fD;
			});
			context.setPacketHandled(true);
		}
	}
}
