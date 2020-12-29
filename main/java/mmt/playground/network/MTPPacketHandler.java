package mmt.playground.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class MTPPacketHandler 
{
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
	    new ResourceLocation("mtplayground", "main"),
	    () -> PROTOCOL_VERSION,
	    PROTOCOL_VERSION::equals,
	    PROTOCOL_VERSION::equals
	);
	
	public void registerPackets()
	{
		int id = -1;
		CHANNEL.messageBuilder(CtoSSyncFallDamagePacket.class, id++)
		.encoder(CtoSSyncFallDamagePacket::serialize).decoder(CtoSSyncFallDamagePacket::deserialize)
		.consumer(CtoSSyncFallDamagePacket::handle)
		.add();
		/*
		CHANNEL.messageBuilder(SendNoteOpenPacket.class, id++)
		.encoder(SendNoteOpenPacket::serialize).decoder(SendNoteOpenPacket::deserialize)
		.consumer(SendNoteOpenPacket::handle)
		.add();
		
		CHANNEL.messageBuilder(SToCAbsorptionSpireParticlePacket.class, id++)
		.encoder(SToCAbsorptionSpireParticlePacket::serialize).decoder(SToCAbsorptionSpireParticlePacket::deserialize)
		.consumer(SToCAbsorptionSpireParticlePacket::handle)
		.add();
		
		CHANNEL.messageBuilder(SToCParticleAtPosPacket.class, id++)
		.encoder(SToCParticleAtPosPacket::serialize).decoder(SToCParticleAtPosPacket::deserialize)
		.consumer(SToCParticleAtPosPacket::handle)
		.add();
		*/
	}
}
