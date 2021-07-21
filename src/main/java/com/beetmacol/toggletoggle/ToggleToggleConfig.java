package com.beetmacol.toggletoggle;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry;
import org.jetbrains.annotations.NotNull;

@Config(name = "toggle-toggle")
public class ToggleToggleConfig implements ConfigData {
	@ConfigEntry.Gui.Tooltip
	@Comment("The way Toggle Toggle will communicate when you change the sneak or sprint modes")
	public CommunicationWay communicationWay = CommunicationWay.ACTION;

	enum CommunicationWay implements SelectionListEntry.Translatable {
		NONE,
		ACTION,
		CHAT;

		@Override
		public @NotNull String getKey() {
			return switch (this) {
				case NONE -> "option.toggle-toggle.communication-way.none";
				case ACTION -> "option.toggle-toggle.communication-way.action-bar";
				case CHAT -> "option.toggle-toggle.communication-way.chat";
			};
		}
	}
}
