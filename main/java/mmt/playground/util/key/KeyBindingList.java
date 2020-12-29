package mmt.playground.util.key;

import java.awt.event.KeyEvent;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * 
 * @author nenikitov -- thank you for sharing this on the forums!
 *
 */
public class KeyBindingList 
{
    public static KeyBinding[] KeyBindings;
    
    public static void register()
    {
        KeyBindings = new KeyBinding[1];
        
        KeyBindings[0] = new KeyBinding("key.mtp.charms", KeyEvent.VK_I, "key.mtp.category");


        for (int i = 0; i < KeyBindings.length; ++i) 
        {
            ClientRegistry.registerKeyBinding(KeyBindings[i]);
        }
    }
}
