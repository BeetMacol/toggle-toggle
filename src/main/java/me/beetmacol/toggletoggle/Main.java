package me.beetmacol.toggletoggle;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import net.minecraft.text.LiteralText;

public class Main implements ModInitializer {
    private static FabricKeyBinding keyBindSneak, keyBindSprint;

    @Override
    public void onInitialize() {
        keyBindSneak = FabricKeyBinding.Builder.create(
                new Identifier("bmmtt", "toggle-sneak"),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "Toggle Toggle"
        ).build();
        KeyBindingRegistry.INSTANCE.addCategory("Toggle Toggle");
        KeyBindingRegistry.INSTANCE.register(keyBindSneak);
        ClientTickCallback.EVENT.register(e ->
        {
            if(keyBindSneak.wasPressed()) {
                if(MinecraftClient.getInstance().options.sneakToggled)
                {
                    System.out.println("Changed sneak mode to HOLD.");
                    MinecraftClient.getInstance().options.sneakToggled = false;

                } else {
                    System.out.println("Changed sneak mode to TOGGLE.");
                    MinecraftClient.getInstance().options.sneakToggled = true;
                }
            }
        });

        keyBindSprint = FabricKeyBinding.Builder.create(
                new Identifier("bmmtt", "toggle-sprint"),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                "Toggle Toggle"
        ).build();
        KeyBindingRegistry.INSTANCE.addCategory("Toggle Toggle");
        KeyBindingRegistry.INSTANCE.register(keyBindSprint);
        ClientTickCallback.EVENT.register(e ->
        {
            if(keyBindSprint.wasPressed()) {
                if(MinecraftClient.getInstance().options.sprintToggled)
                {
                    System.out.println("Changed sprint mode to HOLD.");
                    MinecraftClient.getInstance().options.sprintToggled = false;
                } else {
                    System.out.println("Changed sprint mode to TOGGLE.");
                    MinecraftClient.getInstance().options.sprintToggled = true;
                }
            }
        });
    }
}
