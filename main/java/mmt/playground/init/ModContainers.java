package mmt.playground.init;

import mmt.playground.Main;
import mmt.playground.client.gui.charms.ContainerCharm;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers 
{

	public static final DeferredRegister<ContainerType<?>> CONTAINERS =  DeferredRegister.create(ForgeRegistries.CONTAINERS, Main.MODID);

	public static final RegistryObject<ContainerType<Container>> CHARM_CONTAINER = CONTAINERS.register("charm_container",
			() -> new ContainerType<>(ContainerCharm::new)
	);
}
