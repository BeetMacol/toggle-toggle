package com.beetmacol.toggletoggle;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class ToggleToggle {
	public static final Logger LOGGER = LogManager.getLogger("Toggle Toggle");
	public static final String MOD_ID = "toggle-toggle";
	public static ToggleToggleConfig config;
	private static KeyBinding keySneakMode, keySprintMode;

	private enum Function {
		SNEAK,
		SPRINT
	}

	private enum Method {
		HOLD,
		TOGGLE
	}

	public static void init() {
		keySneakMode = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.toggle-toggle.toggle-sneak", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B, "Toggle Toggle"));
		keySprintMode = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.toggle-toggle.toggle-sprint", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, "Toggle Toggle"));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (keySneakMode.wasPressed()) {
				if (client.options.getSneakToggled().getValue()) {
					client.options.getSneakToggled().setValue(false);
					announceChange(client, Function.SNEAK, Method.HOLD);
				} else {
					client.options.getSneakToggled().setValue(true);
					announceChange(client, Function.SNEAK, Method.TOGGLE);
				}
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (keySprintMode.wasPressed()) {
				if (client.options.getSprintToggled().getValue()) {
					client.options.getSprintToggled().setValue(false);
					announceChange(client, Function.SPRINT, Method.HOLD);
				} else {
					client.options.getSprintToggled().setValue(true);
					announceChange(client, Function.SPRINT, Method.TOGGLE);
				}
			}
		});

		config = AutoConfig.register(ToggleToggleConfig.class, JanksonConfigSerializer::new).getConfig();
	}

	private static void announceChange(MinecraftClient client, Function function, Method method) {
		LOGGER.info("Changed {} mode to {}", function, method);

		Text text =  Text.translatable("toggle-toggle.switch." + function.toString().toLowerCase() + "." + method.toString().toLowerCase());
		switch (config.communicationWay) {
			case ACTION -> client.player.sendMessage(text, true);
			case CHAT -> client.player.sendMessage(text, false);
		}
	}
}
